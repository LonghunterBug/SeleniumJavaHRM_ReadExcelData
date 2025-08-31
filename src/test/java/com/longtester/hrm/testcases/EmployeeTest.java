package com.longtester.hrm.testcases;

import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.EmployeeDetailPage;
import com.longtester.hrm.pages.EmployeePage;
import com.longtester.hrm.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    EmployeePage employeePage;
    EmployeeDetailPage employeeDetailPage;
    @BeforeMethod
    public void initPage(){
        loginPage = new LoginPage();
        basePage = new BasePage();
        employeePage = new EmployeePage();
        employeeDetailPage = new EmployeeDetailPage();
    }
    @Test
    public void testAddNewEmployee(){
        loginPage.loginHRM(DataTest.username_login,DataTest.password_login);
        basePage.clickMenuPIM();
        employeePage.addNewEmployee(DataTest.employee_firstname,DataTest.employee_middlename,DataTest.employee_lastname,DataTest.employee_id);
        basePage.verifySuccessMessageIsDisplayed();
        basePage.clickMenuPIM();
        employeePage.verifyEmployeeIsDisplayedInTable(DataTest.employee_id);
    }
    @Test
    public void testMainFlow(){
        loginPage.loginHRM(DataTest.username_login,DataTest.password_login);
        basePage.clickMenuPIM();
        employeePage.addNewEmployee(DataTest.employee_firstname,DataTest.employee_middlename,DataTest.employee_lastname,DataTest.employee_id);
        basePage.clickMenuPIM();
        employeePage.verifyEmployeeIsDisplayedInTable(DataTest.employee_id);
        employeePage.clickEdit();
        employeeDetailPage.updatePersonalDetail(DataTest.driverNumber,DataTest.gender);
        employeeDetailPage.updateJob(DataTest.job_title,DataTest.job_category);
        employeeDetailPage.clickMenuPersonalDetail();
        employeeDetailPage.verifyPersonalDetailUpdated(DataTest.driverNumber,DataTest.gender);
        employeeDetailPage.clickMenuJob();
        employeeDetailPage.verifyJobUpdated(DataTest.job_title,DataTest.job_category);
    }
}
