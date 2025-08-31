package com.longtester.hrm.pages;

import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BasePage {
    private By menuAdmin  = By.xpath("//span[normalize-space()='Admin']");
    private By menuPIM = By.xpath("//span[normalize-space()='PIM']");
    private By iconAvatar = By.xpath("//span[contains(@class,'userdropdown')]");
    private By buttonLogout = By.xpath("//li/a[normalize-space()='Logout']");
    private By leftMainMenu = By.xpath("//ul[@class='oxd-main-menu']/li");
    private By toastMessageSuccess = By.xpath("//div[contains(@class,'toast--success')]");

    public void clickMenuAdmin(){
        WebUI.clickElement(menuAdmin);
    }
    public void clickMenuPIM(){
        WebUI.clickElement(menuPIM);
    }
    public void clickLogout(){
        WebUI.clickElement(iconAvatar);
        WebUI.clickElement(buttonLogout);
    }
    public void verifyMainMenuDisplayed(){
        WebUI.waitForAllElementsVisible(leftMainMenu);
        List<WebElement> listmenu = WebUI.getWebElements(leftMainMenu);
        for(WebElement e:listmenu){
            WebUI.verifyDisplay(e,e.isDisplayed(),e.getText() + " not display");
        }
    }
    public void verifySuccessMessageIsDisplayed() {
        WebUI.verifyDisplay(toastMessageSuccess, WebUI.isElementDisplayed(toastMessageSuccess), "Toast message not display");
        WebUI.highlightElement(toastMessageSuccess);
    }

}
