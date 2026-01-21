package com.harel.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {


    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }

    }

    public void waitUntilClickable(WebElement element, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.elementToBeClickable(element));

    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}

