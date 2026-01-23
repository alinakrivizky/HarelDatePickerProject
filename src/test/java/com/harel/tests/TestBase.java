package com.harel.tests;
import com.harel.utils.Listener;
import io.github.bonigarcia.wdm.WebDriverManager;
import  org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestBase {
  public WebDriver driver;
  @BeforeMethod
    public void setup() {
      WebDriverManager.chromedriver().setup();

      ChromeOptions options = new ChromeOptions();
      options.addArguments("--headless");
      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
      options.addArguments("--window-size=1920,1080");

      driver = new ChromeDriver(options);
      driver = new EventFiringDecorator<>(new Listener()).decorate(driver);
      driver.get("https://digital.harel-group.co.il/travel-policy/wizard/destination");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterMethod(enabled=false)
    public void teardown() {
    driver.quit();
    }


}
