package com.longtester.hrm.pages;

import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    private By inputUserName = By.xpath("//input[@placeholder='Username']");
    private By inputPassword = By.xpath("//input[@placeholder='Password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By alertErrorInvalidCredential = By.xpath("//p[normalize-space()='Invalid credentials']");
    private By alertErrorInputRequire = By.xpath("//span[contains(@class,'input-field-error-message')]");

    public void loginHRM(String username, String password){
        WebUI.openURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebUI.setText(inputUserName,username);
        WebUI.setText(inputPassword,password);
        WebUI.clickElement(buttonLogin);
    }
    public void verifyErrorInvalidCredentialDisplayed(){
        WebUI.verifyDisplay(alertErrorInputRequire,WebUI.isElementDisplayed(alertErrorInputRequire),"Error input required not display");
        WebUI.verifyEqual(WebUI.getElementText(alertErrorInputRequire),"Required","Error input required not match with expected");
    }
}
