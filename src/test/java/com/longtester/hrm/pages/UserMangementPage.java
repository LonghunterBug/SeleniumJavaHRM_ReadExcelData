package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class UserMangementPage {

    private By buttonAddNewUser = By.xpath("//button[normalize-space()='Add']");
    private By inputSearchUserName = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[6]//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[6]//button[1]");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By listUsername = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");
    // Add New User form
    private By selectUserRole = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");
    private By selectStatus = By.xpath("//label[text()='Status']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");
    private By inputEmployeeName = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By inputUserName = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By inputPassword = By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input");
    private By inputConfirmPassword = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");






    public void addNewUser(String role, String status, String employee_name, String username, String password, String confirmpassword) {
        WebUI.clickElement(buttonAddNewUser);
        WebUI.clickElement(selectUserRole);
        WebUI.clickElement(By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//span[text()='" + role + "']"));
        WebUI.setText(inputEmployeeName, employee_name);
        WebUI.waitForElementVisible(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + employee_name + "']"));
        WebUI.clickElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + employee_name + "']"));
        WebUI.clickElement(selectStatus);
        WebUI.clickElement(By.xpath("//label[text()='Status']/parent::div/following-sibling::div//span[text()='" + status + "']"));
        WebUI.setText(inputUserName, username);
        WebUI.setText(inputPassword, password);
        WebUI.setText(inputConfirmPassword, confirmpassword);
        WebUI.clickElement(buttonSave);
    }
    public void editEmployeeName(String username) {
        searchByUserName(username);
        WebUI.clickElement(buttonEdit);
        WebUI.clearTextWithKey(inputEmployeeName);
        WebUI.setText(inputEmployeeName, DataTest.update_employeename);
        WebUI.waitForElementVisible(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + DataTest.update_employeename + "']"));
        WebUI.clickElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + DataTest.update_employeename + "']"));
        WebUI.clickElement(buttonSave);
    }
    public void deleteUser(String username) {
        searchByUserName(username);
        WebUI.clickElement(buttonDelete);
        WebUI.clickElement(buttonConfirmDelete);
    }



    public void verifyUserIsDisplayedInTable(String username) {
        WebUI.sleep(5);
        searchByUserName(username);
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + username + "']"));
        By text = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + username + "']");
        WebUI.verifyDisplay(text,WebUI.isElementDisplayed(text),"Username not display");
    }
    public void verifyUserNotDisplayedInTable(String username) {
        WebUI.sleep(5);
        WebUI.clearTextWithKey(inputSearchUserName);
        WebUI.setText(inputSearchUserName, username);
        WebUI.clickElement(buttonSearch);
        WebUI.sleep(2);
        List<WebElement> listusername = WebUI.getWebElements(listUsername);
        WebUI.verifyNotDisplay1(listusername,username,username +" is still displayed in table");
    }

    public void verifyEmployeeNameIsUpdated(String username) {
        WebUI.sleep(5);
        searchByUserName(username);
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[4]"));
        String actual_employeename = WebUI.getElementText(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[4]"));
        WebUI.verifyEqual(actual_employeename, DataTest.employee_firstname + " " + DataTest.employee_lastname, "Employee name is not macthed");
    }


    public void searchByUserName(String username) {
        WebUI.setText(inputSearchUserName, username);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + username + "']"));
    }


}
