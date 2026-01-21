package com.harel.pages;

import com.harel.elements.DatePickerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

public class TravelPage extends BasePage {

    private DatePickerPage datePicker;

    public TravelPage(WebDriver driver) {
        super(driver);
        this.datePicker = new DatePickerPage(driver);
    }

    @FindBy(css = "button[data-hrl-bo='purchase-for-new-customer']")
    public WebElement purchaseForNewCustomer;

    public TravelPage clickOnFirstBuyButton() {
        click(purchaseForNewCustomer);
        return this;
    }

    @FindBy(css = "svg[alt='asia']")
    public WebElement asia;

    public TravelPage clickOnDestinationButton() {
        click(asia);
        return this;
    }
    @FindBy(css="button[role='button']")
    public WebElement submitButton;

    public TravelPage clickOnSubmitButton() {
        click(submitButton);
        return this;
    }

    public TravelPage selectDate(LocalDate localDate) {
        datePicker.openCalendar();
        datePicker.selectDate(localDate);
                return this;
    }
}

