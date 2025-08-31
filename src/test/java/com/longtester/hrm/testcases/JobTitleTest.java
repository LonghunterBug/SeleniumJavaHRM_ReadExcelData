package com.longtester.hrm.testcases;

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
        loginPage.loginHRM("Admin", "admin123");
        basePage.clickMenuAdmin();
        jobTitlePage.addJobTitle(DataTest.job_title);
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleIsDisplayedInTable(DataTest.job_title);
    }
    @Test(priority = 2)
    public void testEditJobTitle() {
        loginPage.loginHRM("Admin", "admin123");
        basePage.clickMenuAdmin();
        jobTitlePage.editJobTitle(DataTest.job_title);
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleIsDisplayedInTable(DataTest.job_title_edit);

    }
    @Test(priority = 3)
    public void testDeleteJobTitle(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        jobTitlePage.deleteJobTitle(DataTest.job_title_edit);
        basePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleNotDisplayedInTable(DataTest.job_title_edit);

    }
}
