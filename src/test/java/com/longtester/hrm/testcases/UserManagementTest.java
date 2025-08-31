package com.longtester.hrm.testcases;

import com.longtester.helpers.ExcelHelper;
import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.LoginPage;
import com.longtester.hrm.pages.UserMangementPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserManagementTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    UserMangementPage userMangementPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage();
        basePage = new BasePage();
        userMangementPage = new UserMangementPage();
    }
    @Test(priority = 1)
    public void testAddNewUser(){
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetUM = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetUM.setExcelFile("src/test/resources/testdata/HRM.xlsx","User Management");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1), sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        userMangementPage.addNewUser
                (sheetUM.getCellData("Role",1),
                sheetUM.getCellData("Status",1),
                sheetUM.getCellData("Employee Name",1),
                sheetUM.getCellData("Username",1),
                sheetUM.getCellData("Password",1),
                sheetUM.getCellData("Confirm Password",1));
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyUserIsDisplayedInTable(sheetUM.getCellData("Username",1));
    }
    @Test(priority = 2)
    public void testLoginSuccessWithRegisteredAccount(){
        ExcelHelper sheetLogin = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        loginPage.loginHRM(sheetLogin.getCellData("Username",2)
                , sheetLogin.getCellData("Password",2));
        basePage.verifyMainMenuDisplayed();
    }
    @Test(priority = 3)
    public void testLoginFailWithInvalidCredential(){
        ExcelHelper sheetLogin = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        loginPage.loginHRM(sheetLogin.getCellData("Username",3)
                , sheetLogin.getCellData("Password",3));
        loginPage.verifyErrorInvalidCredentialDisplayed();
    }
    @Test(priority = 4)
    public void testEditUser(){
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetUM = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetUM.setExcelFile("src/test/resources/testdata/HRM.xlsx","User Management");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        userMangementPage.editEmployeeName(sheetUM.getCellData("Username",1));
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyEmployeeNameIsUpdated(sheetUM.getCellData("Username",1));
    }
    @Test(priority = 5)
    public void testDeleteUser(){
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetUM = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetUM.setExcelFile("src/test/resources/testdata/HRM.xlsx","User Management");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        userMangementPage.deleteUser(sheetUM.getCellData("Username",1));
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyUserNotDisplayedInTable(sheetUM.getCellData("Username",1));
    }
}
