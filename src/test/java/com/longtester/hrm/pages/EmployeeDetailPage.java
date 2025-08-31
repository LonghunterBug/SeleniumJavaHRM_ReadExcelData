package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.util.List;

public class EmployeeDetailPage {
    private By inputDriverLicenseNumber = By.xpath("//label[contains(text(),'License Number')]/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By menuPersonalDetail = By.xpath("//div[@role='tablist']/div[1]");
    private By menuJob = By.xpath("//div[@role='tablist']/div[6]");
    private By selectJobTitle = By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");
    private By listOption = By.xpath("//div[@role='option']/span");
    private By selectJobCategory = By.xpath("//label[text()='Job Category']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");


    public void updatePersonalDetail(String driverLicense, String gender) {
        WebUI.sleep(2);
        WebUI.setText(inputDriverLicenseNumber, driverLicense);
        WebUI.clickElement(By.xpath("//label[text()='" + gender + "']/span"));
        WebUI.clickElement(buttonSave);
    }

    public void clickMenuJob() {
        WebUI.clickElement(menuJob);
    }

    public void clickMenuPersonalDetail() {
        WebUI.clickElement(menuPersonalDetail);
    }

    public void updateJob(String jobTitle, String jobCategory) {
        clickMenuJob();
        WebUI.sleep(2);
        selectJobTitle(jobTitle);
        selectJobCategory(jobCategory);
        WebUI.clickElement(buttonSave);
    }

    private void selectJobCategory(String jobCategory) {
        WebUI.clickElement(selectJobCategory);
        List<WebElement> listCategory = WebUI.getWebElements(listOption);
        boolean found = false;// Kiềm tra đã tìm thấy chưa
        for (WebElement e : listCategory) {
            if (e.getText().equals(jobCategory)) {
                found = true;
                WebUI.scrollToElementAtTop(e);
                WebUI.highlightElement(e);
                WebUI.logConsole("Select " + jobCategory);
                e.click();
                break;
            }
        } // Tìm thấy rồi thì found = true
        //!found = !true = false => Không chạy code dưới
        if (!found) {
            WebUI.logConsole(jobCategory + " không thấy trong list. Chọn mặc định: Craft Workers");
            for (WebElement e : listCategory) {
                if (e.getText().equalsIgnoreCase("Craft Workers")) {
                    WebUI.scrollToElementAtTop(e);
                    WebUI.highlightElement(e);
                    e.click();
                    break;
                }
            }
        }
    }


    private void selectJobTitle(String jobTitle) {
        WebUI.clickElement(selectJobTitle);
        List<WebElement> listTitle = WebUI.getWebElements(listOption);
        boolean found = false;// Kiềm tra đã tìm thấy chưa
        for (WebElement e : listTitle) {
            if (e.getText().equals(jobTitle)) {
                found = true;
                WebUI.scrollToElementAtTop(e);
                WebUI.highlightElement(e);
                WebUI.logConsole("Select " + jobTitle);
                e.click();
                break;
            }
        } // Tìm thấy rồi thì found = true
        //!found = !true = false => Không chạy code dưới
        if (!found) {
            WebUI.logConsole(jobTitle + " không thấy trong list. Chọn mặc định: Account Assistant");
            for (WebElement e : listTitle) {
                if (e.getText().equalsIgnoreCase("Account Assistant")) {
                    WebUI.scrollToElementAtTop(e);
                    WebUI.highlightElement(e);
                    e.click();
                    break;
                }
            }
        }
    }

    public void verifyPersonalDetailUpdated(String driverNumber, String gender) {
        WebUI.sleep(1);
        String actual_valueLicenseNumber = WebUI.getElementAttribute(inputDriverLicenseNumber, "value");
        boolean check = WebUI.isElementSelected(By.xpath("//label[text()='" + gender + "']/input[@type='radio']"));
        WebUI.verifyEqual(actual_valueLicenseNumber, driverNumber, "Driver License Number not updated");
        WebUI.verifySelect(By.xpath("//label[text()='" + gender + "']"), check, "Gender not checked");
    }
    public void verifyJobUpdated(String jobTitle, String jobCategory){
        WebUI.sleep(1);
        String actual_jobTitle = WebUI.getElementText(selectJobTitle);
        String actual_jobCategory = WebUI.getElementText(selectJobCategory);
        WebUI.verifyEqual(actual_jobTitle,jobTitle,"Job title not matched");
        WebUI.verifyEqual(actual_jobCategory,jobCategory,"Job category not matched");
    }
}

