package com.harel.pages;

import com.harel.elements.DatePickerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TravelPage extends BasePage {

    private DatePickerPage datePicker;

    public TravelPage(WebDriver driver) {
        super(driver);
        this.datePicker = new DatePickerPage(driver);
    }

    @FindBy(css = "button[data-hrl-bo='purchase-for-new-customer']")
    public WebElement purchaseForNewCustomer;

    public TravelPage clickOnFirstBuyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseForNewCustomer));
        click(purchaseForNewCustomer);
        return this;
    }

    @FindBy(css = "svg[alt='asia']")
    public WebElement asia;

    public TravelPage clickOnDestinationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(asia));
        click(asia);
        return this;
    }
    @FindBy(css="button[role='button']")
    public WebElement submitButton;

    public TravelPage clickOnSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        click(submitButton);
        return this;
    }

    public TravelPage selectDate(LocalDate localDate) {
        datePicker.selectDate(localDate);
                return this;
    }
    @FindBy(css = "span[data-hrl-bo='total-days']")
    public WebElement totalDays;

    public TravelPage checkTotalDays (LocalDate departureDate, LocalDate arrivalDate) {
        long expectedDays= ChronoUnit.DAYS.between(departureDate, arrivalDate)+1;
        wait.until(ExpectedConditions.visibilityOf(totalDays));
        wait.until(ExpectedConditions.textToBePresentInElement(totalDays, ""));
        String totalDaysText = totalDays.getText();
        long actualDaysLong = Long.parseLong
                (totalDaysText.replaceAll("\\D+",""));
        Assert.assertEquals(expectedDays, actualDaysLong,
        "Total days should be equal to actual days");
        return this;
    }
    @FindBy(id="nextButton")
    public WebElement nextButton;

    public TravelPage clickOnToThePassengersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        click(nextButton);
        return this;

    }
    @FindBy(css = "h2[data-hrl-bo='screen_title']")
    public WebElement screenTitle;
    public boolean isPassengersPageOpen() {
        wait.until(ExpectedConditions.visibilityOf( screenTitle));
        return screenTitle.getText().contains("נשמח להכיר");
    }
}

