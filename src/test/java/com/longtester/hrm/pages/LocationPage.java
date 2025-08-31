package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class LocationPage {
    private By topbarOrganization = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Organization']");
    private By menuitemLocations = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Locations']");
    private By inputSearchLocation = By.xpath("//label[text()='Name']/parent::div/following-sibling::div/input");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div//button[1]");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By listLocation = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");

    // Add New Location form
    private By inputLocationName = By.xpath("//label[text()='Name']/parent::div/following-sibling::div/input");
    private By selectCountry = By.xpath("//label[text()='Country']/parent::div/following-sibling::div//div[contains(@class,'select-wrapper')]");
    private By optionCountry1 = By.xpath("//div[@role='option']/span[text()='" + DataTest.country + "']");
    private By optionCountry2 = By.xpath("//div[@role='option']/span[text()='" + DataTest.country_edit + "']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");


    public void clickMenuLocation() {
        WebUI.clickElement(topbarOrganization);
        WebUI.clickElement(menuitemLocations);
    }
    public void searchByLocationName(String locationName) {
        WebUI.setText(inputSearchLocation, locationName);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + locationName + "']"));
    }
    public void addLocation(String locationName) {
        clickMenuLocation();
        WebUI.clickElement(buttonAdd);
        WebUI.setText(inputLocationName, locationName);
        WebUI.clickElement(selectCountry);
        WebUI.scrollToElementAtTop(optionCountry1);
        WebUI.highlightElement(optionCountry1);
        WebUI.clickElement(optionCountry1);
        WebUI.clickElement(buttonSave);
    }
    public void editCountry(String locationName){
        clickMenuLocation();
        searchByLocationName(locationName);
        WebUI.clickElement(buttonEdit);
        WebUI.clickElement(selectCountry);
        WebUI.scrollToElementAtTop(optionCountry2);
        WebUI.highlightElement(optionCountry2);
        WebUI.clickElement(optionCountry2);
        WebUI.clickElement(buttonSave);
    }
    public void deleteLocation(String locationName){
        clickMenuLocation();
        searchByLocationName(locationName);
        WebUI.clickElement(buttonDelete);
        WebUI.clickElement(buttonConfirmDelete);
    }


    public void verifyLocationIsDisplayedInTable(String locationName) {
        WebUI.sleep(5);
        WebUI.setText(inputSearchLocation, locationName);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + locationName + "']"));
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + locationName + "']"));
        By text = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + locationName + "']");
        WebUI.verifyDisplay(text,WebUI.isElementDisplayed(text),"Location not display");
    }
    public void verifyLocationNotDisplayedInTable(String locationName){
        WebUI.sleep(5);
        WebUI.clearTextWithKey(inputSearchLocation);
        WebUI.setText(inputSearchLocation, locationName);
        WebUI.clickElement(buttonSearch);
        WebUI.sleep(2);
        List<WebElement> listlocation = WebUI.getWebElements(listLocation);
        WebUI.verifyNotDisplay1(listlocation,locationName,locationName +" is still displayed in table");
    }
    public void veriyCountryIsUpdatedInTable(String locationName){
        WebUI.sleep(5);
        WebUI.setText(inputSearchLocation, locationName);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + locationName + "']"));
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[4]"));
        String actual_country = WebUI.getElementText(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[4]"));
        WebUI.verifyEqual(actual_country,DataTest.country_edit,"Country is not match");
    }


}
