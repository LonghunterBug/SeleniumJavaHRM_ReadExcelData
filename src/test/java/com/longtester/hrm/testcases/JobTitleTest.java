package com.longtester.hrm.testcases;

import com.longtester.helpers.ExcelHelper;
import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.JobTitlePage;
import com.longtester.hrm.pages.LoginPage;
import com.longtester.hrm.pages.UserMangementPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JobTitleTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    JobTitlePage jobTitlePage;
    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage();
        basePage = new BasePage();
        jobTitlePage = new JobTitlePage();
    }
    @Test(priority = 1)
    public void testAddJobTitle() {
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetJobTitle = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetJobTitle.setExcelFile("src/test/resources/testdata/HRM.xlsx","Job Title");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        jobTitlePage.addJobTitle(sheetJobTitle.getCellData("Title",1));
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleIsDisplayedInTable(sheetJobTitle.getCellData("Title",1));
    }
    @Test(priority = 2)
    public void testEditJobTitle() {
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetJobTitle = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetJobTitle.setExcelFile("src/test/resources/testdata/HRM.xlsx","Job Title");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        jobTitlePage.editJobTitle(sheetJobTitle.getCellData("Title",1));
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleIsDisplayedInTable(sheetJobTitle.getCellData("Title",2));

    }
    @Test(priority = 3)
    public void testDeleteJobTitle(){
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetJobTitle = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetJobTitle.setExcelFile("src/test/resources/testdata/HRM.xlsx","Job Title");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuAdmin();
        jobTitlePage.deleteJobTitle(sheetJobTitle.getCellData("Title",2));
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleNotDisplayedInTable(sheetJobTitle.getCellData("Title",2));

    }
}
