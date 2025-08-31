package com.longtester.hrm.testcases;

import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.LocationPage;
import com.longtester.hrm.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocationTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    LocationPage locationPage;
    @BeforeMethod
    public void initPage(){
        loginPage = new LoginPage();
        basePage = new BasePage();
        locationPage = new LocationPage();
    }
    @Test(priority = 1)
    public void testAddLocation(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        locationPage.addLocation(DataTest.location_name);
        basePage.verifySuccessMessageIsDisplayed();
        locationPage.verifyLocationIsDisplayedInTable(DataTest.location_name);
    }
    @Test(priority = 2)
    public void testEditLocation(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        locationPage.editCountry(DataTest.location_name);
        basePage.verifySuccessMessageIsDisplayed();
        locationPage.veriyCountryIsUpdatedInTable(DataTest.location_name);
    }
    @Test(priority = 3)
    public void testDeleteLocation(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        locationPage.deleteLocation(DataTest.location_name);
        basePage.verifySuccessMessageIsDisplayed();
        locationPage.verifyLocationNotDisplayedInTable(DataTest.location_name);
    }
}
