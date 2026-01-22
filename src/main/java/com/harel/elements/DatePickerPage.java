package com.harel.elements;

import com.harel.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DatePickerPage extends BasePage {

    public DatePickerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MuiPickersStaticWrapper-staticWrapperRoot")
    private WebElement dateInputWrapper;
    @FindBy(css = "p.MuiTypography-alignCenter")
    private WebElement monthYearHeader;
    @FindBy(xpath = "(//button[@aria-label='לעבור לחודש הבא' and not(@disabled)])[1]")
    private WebElement arrowForwardButton;

    public void openCalendar() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", dateInputWrapper);
    }

    public void selectDate(LocalDate date) {
        openCalendar();

        String targetMonthYear = date.format(DateTimeFormatter
                .ofPattern("MMMM yyyy", new Locale("he")));
        String isoDate = date.toString(); // 2026-12-21

        int attempts = 0;

        while (attempts < 36) { // maximum 3 years
            String currentMonthYear = monthYearHeader.getText().trim();

            if (currentMonthYear.equals(targetMonthYear)) {
                WebElement dayButton = driver.findElement
                        (By.cssSelector("button[data-hrl-bo='" + isoDate + "']"));
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView();" +
                                " arguments[0].click();", dayButton);
                return;
            }
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", arrowForwardButton);
            wait.until(ExpectedConditions
                    .textToBePresentInElement(monthYearHeader, targetMonthYear));
            attempts++;
        }
        throw new RuntimeException("target month had not found " + targetMonthYear);
    }
}
