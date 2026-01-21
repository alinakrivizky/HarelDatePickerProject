package com.harel.tests;

import com.harel.pages.TravelPage;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TravelTest extends TestBase {
    @Test
    public void travelPositiveTest(){
        new TravelPage(driver).clickOnFirstBuyButton()
                .clickOnDestinationButton().clickOnSubmitButton()
                .selectDate(LocalDate.of(2026, 12, 21));
    }
}
