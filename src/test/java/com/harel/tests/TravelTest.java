package com.harel.tests;

import com.harel.pages.TravelPage;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TravelTest extends TestBase {
    @Test
    public void travelPositiveTest(){
        LocalDate departureDate = LocalDate.of(2026, 1, 25);
        LocalDate returnDate = departureDate.plusDays(30);

        TravelPage travelPage = new TravelPage(driver);

        new TravelPage(driver).clickOnFirstBuyButton()
                .clickOnDestinationButton().clickOnSubmitButton()
                .selectDate(departureDate);
        travelPage.selectDate(returnDate);
    }
}
