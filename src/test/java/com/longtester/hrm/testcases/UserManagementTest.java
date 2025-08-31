package com.longtester.hrm.testcases;

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
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        userMangementPage.addNewUser(DataTest.role,DataTest.status,DataTest.employee_name,DataTest.username_addnew,DataTest.password_addnew,DataTest.confirmpassword_addnew);
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyUserIsDisplayedInTable(DataTest.username_addnew);
    }
    @Test(priority = 2)
    public void testLoginSuccessWithRegisteredAccount(){
        loginPage.loginHRM(DataTest.username_addnew,DataTest.password_addnew);
        basePage.verifyMainMenuDisplayed();
    }
    @Test(priority = 3)
    public void testLoginFailWithInvalidCredential(){
        loginPage.loginHRM(DataTest.username_addnew,"");
        loginPage.verifyErrorInvalidCredentialDisplayed();
    }
    @Test(priority = 4)
    public void testEditUser(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        userMangementPage.editEmployeeName(DataTest.username_addnew);
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyEmployeeNameIsUpdated(DataTest.username_addnew);
    }
    @Test(priority = 5)
    public void testDeleteUser(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        userMangementPage.deleteUser(DataTest.username_addnew);
        basePage.verifySuccessMessageIsDisplayed();
        userMangementPage.verifyUserNotDisplayedInTable(DataTest.username_addnew);
    }
}
