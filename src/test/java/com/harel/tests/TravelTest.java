package com.harel.tests;

import com.harel.pages.TravelPage;
import com.harel.utils.Listener;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
public class TravelTest extends TestBase {
    @Test
    public void travelPositiveTest(){
        LocalDate departureDate = LocalDate.now().plusDays(7);
        LocalDate returnDate = departureDate.plusDays(30);

        TravelPage travelPage = new TravelPage(driver);
        travelPage.clickOnFirstBuyButton().clickOnDestinationButton()
                .clickOnSubmitButton().selectDate(departureDate)
                .selectDate(returnDate).checkTotalDays(departureDate,returnDate)
                .clickOnToThePassengersButton();
        Assert.assertTrue(travelPage.isPassengersPageOpen());
    }
}
