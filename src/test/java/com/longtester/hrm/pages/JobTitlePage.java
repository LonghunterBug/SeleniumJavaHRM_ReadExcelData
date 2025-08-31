package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class JobTitlePage {
    private By topbarJob = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Job']");
    private By menuitemJobTitles = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Titles']");
    private By menuitemEmploymentStatus = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Employment Status']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[1]");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By listJobTitle = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");
    // Add New Job Title form
    private By inputJobTitle = By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");


    public void clickMenuJobTitle() {
        WebUI.clickElement(topbarJob);
        WebUI.clickElement(menuitemJobTitles);
    }

    public void addJobTitle(String jobTitle) {
        clickMenuJobTitle();
        WebUI.clickElement(buttonAdd);
        WebUI.setText(inputJobTitle, jobTitle);
        WebUI.clickElement(buttonSave);
    }

    public void editJobTitle(String title) {
        clickMenuJobTitle();
        int index = 0;
        WebUI.sleep(3);
        boolean check = false;
        List<WebElement> jobtitle = WebUI.getWebElements(listJobTitle);
        for (int i = 0; i < jobtitle.size(); i++) {
            if (jobtitle.get(i).getText().equals(title)) {
                WebUI.scrollToElementAtTop(jobtitle,i);
                check = true;
                index = i;
                WebUI.logConsole("Index of job title: " + i);// Adjust index for the button position
                break;
            }
        }
        if (!check) {
            Assert.fail("Job title '" + title + "' not found in the list.");
        }
        List<WebElement> listButtonEdit = WebUI.getWebElements(buttonEdit);
        WebUI.logConsole("Click edit button for job title at index: " + index);
        WebUI.highlightElement(listButtonEdit,index);
        listButtonEdit.get(index).click();
        WebUI.clearTextWithKey(inputJobTitle);
        WebUI.setText(inputJobTitle, DataTest.job_title_edit);
        WebUI.clickElement(buttonSave);
    }

    public void deleteJobTitle(String title) {
        clickMenuJobTitle();
        int index = 0;
        WebUI.sleep(3);
        boolean check = false;
        List<WebElement> jobtitle = WebUI.getWebElements(listJobTitle);
        WebUI.logConsole("Total job titles found: " + jobtitle.size());
        for (int i = 0; i < jobtitle.size(); i++) {
            if (jobtitle.get(i).getText().equals(title)) {
                WebUI.scrollToElementAtTop(jobtitle,i);
                check = true;
                index = i;
                WebUI.logConsole("Index of job title: " + i);// Adjust index for the button position
                break;
            }
        }
        if (!check) {
            Assert.fail("Job title '" + title + "' not found in the list.");
        }
        List<WebElement> listButtonDelete = WebUI.getWebElements(buttonDelete);
        WebUI.logConsole("Click delete button for job title at index: " + index);
        WebUI.highlightElement(listButtonDelete,index);
        listButtonDelete.get(index).click();
        WebUI.clickElement(buttonConfirmDelete);
    }


    public void verifyJobTitleIsDisplayedInTable(String title) {
        WebUI.sleep(5);
        boolean check = false;
        List<WebElement> e = WebUI.getWebElements(listJobTitle);
        for (WebElement element : e) {
            if (element.getText().equals(title)) {
                WebUI.scrollToElementAtTop(element);
                WebUI.highlightElement(element);
                WebUI.sleep(2); // Nếu quay video thì cần sleep
                WebUI.verifyDisplay(element,element.isDisplayed(), title + " is not displayed in the table.");
                check = true;
                break;
            }
        }
        if (!check) {
            Assert.fail(title + " is not found in the table.");
        }
    }

    public void verifyJobTitleNotDisplayedInTable(String title) {
        WebUI.sleep(4);
        List<WebElement> jobtitle = WebUI.getWebElements(listJobTitle);
        WebUI.verifyNotDisplay2(jobtitle,title,title + " is still displayed in the table");
    }


}
