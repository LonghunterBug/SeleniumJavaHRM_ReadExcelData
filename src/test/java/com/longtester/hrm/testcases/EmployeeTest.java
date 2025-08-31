package com.longtester.hrm.testcases;

import com.longtester.helpers.ExcelHelper;
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
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetEmployee = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetEmployee.setExcelFile("src/test/resources/testdata/HRM.xlsx","Employee");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));
        basePage.clickMenuPIM();
        employeePage.addNewEmployee(
                sheetEmployee.getCellData("First name",2),
                sheetEmployee.getCellData("Middle name",2),
                sheetEmployee.getCellData("Last name",2),
                sheetEmployee.getCellData("ID",2));
        basePage.verifySuccessMessageIsDisplayed();
        basePage.clickMenuPIM();
        employeePage.verifyEmployeeIsDisplayedInTable(sheetEmployee.getCellData("ID",2));
    }
    @Test
    public void testMainFlow(){
        ExcelHelper sheetLogin = new ExcelHelper();
        ExcelHelper sheetEmployee = new ExcelHelper();
        ExcelHelper sheetJobTitle = new ExcelHelper();
        ExcelHelper sheetJobCategory = new ExcelHelper();
        sheetLogin.setExcelFile("src/test/resources/testdata/HRM.xlsx","Login");
        sheetEmployee.setExcelFile("src/test/resources/testdata/HRM.xlsx","Employee");
        sheetJobTitle.setExcelFile("src/test/resources/testdata/HRM.xlsx","Job Title");
        sheetJobCategory.setExcelFile("src/test/resources/testdata/HRM.xlsx","Job Category");
        loginPage.loginHRM(sheetLogin.getCellData("Username",1)
                , sheetLogin.getCellData("Password",1));

        basePage.clickMenuPIM();
        employeePage.addNewEmployee(
                sheetEmployee.getCellData("First name",3),
                sheetEmployee.getCellData("Middle name",3),
                sheetEmployee.getCellData("Last name",3),
                sheetEmployee.getCellData("ID",3));
        basePage.clickMenuPIM();
        employeePage.verifyEmployeeIsDisplayedInTable(sheetEmployee.getCellData("ID",3));
        employeePage.clickEdit();
        employeeDetailPage.updatePersonalDetail(
                sheetEmployee.getCellData("Driver number",3),
                sheetEmployee.getCellData("Gender",3));
        employeeDetailPage.updateJob(
                sheetJobTitle.getCellData("Title",1),
                sheetJobCategory.getCellData("Title",1));
        employeeDetailPage.clickMenuPersonalDetail();
        employeeDetailPage.verifyPersonalDetailUpdated(
                sheetEmployee.getCellData("Driver number",3),
                sheetEmployee.getCellData("Gender",3));
        employeeDetailPage.clickMenuJob();
        employeeDetailPage.verifyJobUpdated(
                sheetJobTitle.getCellData("Title",1),
                sheetJobCategory.getCellData("Title",1));
    }
}
