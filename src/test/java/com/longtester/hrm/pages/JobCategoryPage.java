package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class JobCategoryPage {

    private By topbarJob = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Job']");
    private By menuitemJobCategories = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Categories']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div//button[1]");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By listJobCategory = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");
    // Add New Job Title form
    private By inputJobCategory = By.xpath("//label[text()='Name']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");


    public void clickMenuJobCategory() {
        WebUI.clickElement(topbarJob);
        WebUI.clickElement(menuitemJobCategories);
    }
    public void addJobCategory(String jobCategory) {
        clickMenuJobCategory();
        WebUI.clickElement(buttonAdd);
        WebUI.setText(inputJobCategory, jobCategory);
        WebUI.clickElement(buttonSave);
    }
    public void editJobCategory(String category) {
        clickMenuJobCategory();
        int index = 0;
        WebUI.sleep(3);
        boolean check = false;
        List<WebElement> jobcategory = WebUI.getWebElements(listJobCategory);
        for (int i = 0; i < jobcategory.size(); i++) {
            if (jobcategory.get(i).getText().equals(category)) {
                WebUI.scrollToElementAtTop(jobcategory,i);
                check = true;
                index = i;
                WebUI.logConsole("Index of job category: " + i);// Adjust index for the button position
                break;
            }
        }
        if (!check) {
            Assert.fail("Job category '" + category + "' not found in the list.");
        }
        List<WebElement> listButtonEdit = WebUI.getWebElements(buttonEdit);
        WebUI.logConsole("Click edit button for job category at index: " + index);
        WebUI.highlightElement(listButtonEdit,index);
        listButtonEdit.get(index).click();
        WebUI.clearTextWithKey(inputJobCategory);
        WebUI.setText(inputJobCategory, DataTest.job_category_edit);
        WebUI.clickElement(buttonSave);
    }
    public void deleteJobCategory(String category) {
        clickMenuJobCategory();
        int index = 0;
        WebUI.sleep(3);
        boolean check = false;
        List<WebElement> jobcategory = WebUI.getWebElements(listJobCategory);
        for (int i = 0; i < jobcategory.size(); i++) {
            if (jobcategory.get(i).getText().equals(category)) {
                WebUI.scrollToElementAtTop(jobcategory,i);
                check = true;
                index = i;
                WebUI.logConsole("Index of job category: " + i);// Adjust index for the button position
                break;
            }
        }
        if (!check) {
            Assert.fail("Job category '" + category + "' not found in the list.");
        }
        List<WebElement> listButtonDelete = WebUI.getWebElements(buttonDelete);
        WebUI.logConsole("Click delete button for job category at index: " + index);
        WebUI.highlightElement(listButtonDelete,index);
        listButtonDelete.get(index).click();
        WebUI.clickElement(buttonConfirmDelete);
    }

    public void verifyJobCategoryIsDisplayedInTable(String category) {
        WebUI.sleep(5);
        boolean check = false;
        List<WebElement> e = WebUI.getWebElements(listJobCategory);
        for (WebElement element : e) {
            if (element.getText().equals(category)) {
                WebUI.scrollToElementAtTop(element);
                WebUI.highlightElement(element);
                WebUI.sleep(2); // Nếu quay video thì cần sleep
                WebUI.verifyDisplay(element,element.isDisplayed(), category + " is not displayed in the table.");
                check = true;
                break;
            }
        }
        if (!check) {
            Assert.fail(category + " is not found in the table.");
        }
    }
    public void verifyJobCategoryNotDisplayedInTable(String category) {
        WebUI.sleep(4);
        List<WebElement> jobcategory = WebUI.getWebElements(listJobCategory);
       WebUI.verifyNotDisplay2(jobcategory,category,category + " is still displayed in the table");
    }

}
