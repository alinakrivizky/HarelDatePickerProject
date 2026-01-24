package com.harel.pages;

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
        jsScrollAndClick(dateInputWrapper);
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
                jsScrollAndClick(dayButton);
                return;
            }
            String previousMonth = monthYearHeader.getText().trim();
            jsScrollAndClick(arrowForwardButton);
            wait.until(ExpectedConditions.not(
                    ExpectedConditions.textToBe(By.cssSelector("p.MuiTypography-alignCenter")
                            , previousMonth)));
            attempts++;
        }
        throw new RuntimeException("target month had not found " + targetMonthYear);
    }
}
