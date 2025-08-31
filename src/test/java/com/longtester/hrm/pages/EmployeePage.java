package com.longtester.hrm.pages;

import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePage {
    private By buttonAddNewEmployee = By.xpath("//button[normalize-space()='Add']");
    private By inputSearchID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[9]//button[1]");
    // Add New Employee form
    private By inputFirstName = By.xpath("//input[@placeholder='First Name']");
    private By inputMiddleName = By.xpath("//input[@placeholder='Middle Name']");
    private By inputLastName = By.xpath("//input[@placeholder='Last Name']");
    private By inputID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");


    public void addNewEmployee(String firstname,String middlename,String lastname,String id){
        WebUI.clickElement(buttonAddNewEmployee);
        WebUI.setText(inputFirstName,firstname);
        WebUI.setText(inputMiddleName,middlename);
        WebUI.setText(inputLastName,lastname);
        WebUI.clearTextWithKey(inputID);
        WebUI.setText(inputID,id);
        WebUI.clickElement(buttonSave);
    }

    public void searchByID(String id) {
        WebUI.setText(inputSearchID, id);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + id + "']"));
    }
    public void clickEdit(){
        WebUI.clickElement(buttonEdit);
    }
    public void verifyEmployeeIsDisplayedInTable(String id){
        //WebUI.sleep(3);
        WebUI.waitForElementVisible(inputSearchID);
        searchByID(id);
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + id + "']"));
        By textfirtname_middlename = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[3]/div");
        WebUI.verifyDisplay(textfirtname_middlename,WebUI.isElementDisplayed(textfirtname_middlename),"FirstName & MiddleName not display");
    }
}
