package com.harel.tests;
import  org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestBase {
  WebDriver driver;
  @BeforeMethod
    public void setup() {
    driver = new ChromeDriver();
    driver.get("https://digital.harel-group.co.il/travel-policy/wizard/destination");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test

    @AfterMethod(enabled = false)
    public void teardown() {
    driver.quit();
    }

}
