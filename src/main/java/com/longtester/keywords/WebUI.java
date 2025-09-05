package com.longtester.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {
    private static WebDriver driver;

    private static int TIMEOUT = 20;
    private static double STEP_TIME = 0.5;
    private static int PAGE_LOAD_TIMEOUT = 30;

    public WebUI(WebDriver driver) {
        this.driver = driver;
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static void openURL(String url) {
        driver.get(url);
        sleep(STEP_TIME);
        logConsole("\uD83C\uDF10 Open URL: " + url);
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        logConsole("Click on element " + by);
    }

    public static void clearTextWithKey(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        WebElement element = getWebElement(by);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        logConsole("Clear text on element " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static void scrollToElementAtTop(By by) {
        sleep(STEP_TIME);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementAtTop(WebElement element) {
        sleep(STEP_TIME);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToElementAtTop(List<WebElement> elements, int index) {
        sleep(STEP_TIME);
        WebElement element = elements.get(index);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void highlightElement(By by) {
        // Highlight the element using JavaScript
        String script = "arguments[0].style.border='3px solid red';";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, driver.findElement(by));
    }

    public static void highlightElement(WebElement element) {
        // Highlight the element using JavaScript
        String script = "arguments[0].style.border='3px solid red';";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }

    public static void highlightElement(List<WebElement> elements, int index) {
        // Highlight the element using JavaScript
        String script = "arguments[0].style.border='3px solid red';";
        WebElement element = elements.get(index);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        String text = getWebElement(by).getText();
        logConsole("==> TEXT: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        System.out.println("Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        System.out.println("==> Attribute value: " + value);
        return value;
    }

    public static void hoverMouse(List<WebElement> elements, int index) {
        try {
            sleep(STEP_TIME);
            Actions action = new Actions(driver);
            WebElement element = elements.get(index);
            action.moveToElement(element).perform();
            logConsole("Hover mouse on element " + element.getText());
        } catch (Exception e) {
            logConsole("Error hovering mouse on element " + e + ": " + e.getMessage());
            Assert.fail("Error hovering mouse on element " + e + ": " + e.getMessage());
        }
    }
    public static void hoverMouse(WebElement element) {
        try {
            sleep(STEP_TIME);
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
            logConsole("Hover mouse on element " + element.getText());
        } catch (Exception e) {
            logConsole("Error hovering mouse on element " + e + ": " + e.getMessage());
            Assert.fail("Error hovering mouse on element " + e + ": " + e.getMessage());
        }
    }
    public static void hoverMouse(By by) {
        try {
            waitForElementVisible(by);
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(by)).perform();
            logConsole("Hover mouse on element " + by);
        } catch (Exception e) {
            logConsole("Error hovering mouse on element " + by + ": " + e.getMessage());
            Assert.fail("Error hovering mouse on element " + by + ": " + e.getMessage());
        }
    }

    public static boolean isElementDisplayed(By by) {
        try {
            waitForElementVisible(by);
            WebElement element = getWebElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementSelected(By by) {
        try {
            WebElement element = getWebElement(by);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementEnabled(By by) {
        try {
            waitForElementVisible(by);
            WebElement element = getWebElement(by);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    //Wait for Element
    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForAllElementsVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            //Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForAllElementsPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void verifySelect(By by, boolean check, String message) {
        String text = getWebElement(by).getText();
        logConsole("Verify " + text + " is checked");
        Assert.assertTrue(check, message);
    }

    public static void verifyEqual(Object actual, Object expected, String message) {
        logConsole("Verify equal: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static void verifyDisplay(By by, boolean check, String message) {
        String text = getWebElement(by).getText();
        logConsole("Verify " + text + " is displayed");
        Assert.assertTrue(check, message);
    }

    public static void verifyDisplay(WebElement element, boolean check, String message) {
        String text = element.getText();
        logConsole("Verify " + text + " is displayed");
        Assert.assertTrue(check, message);
    }

    // Trường hợp page có inputSearch, dùng hàm này
    public static void verifyNotDisplay1(List<WebElement> element, Object expected, String message) {
        logConsole("Verify " + expected + " is not displayed");
        Assert.assertTrue(element.isEmpty(), message);
    }

    // Trường hợp page không có inputSearch, phải dùng hàm này
    public static void verifyNotDisplay2(List<WebElement> element, Object expected, String message) {
        logConsole("Verify " + expected + " is not displayed");
        for (WebElement e : element) {
            Assert.assertTrue(!e.getText().equals(expected));
        }
    }


}
