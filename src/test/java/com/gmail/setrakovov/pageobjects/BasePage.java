package com.gmail.setrakovov.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    final Logger log = LoggerFactory.getLogger(BasePage.class);

    /**
     * Constructor
     */
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Wait Page Load Method
     */
    public void waitPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    /**
     * Wait Visibility Wrapper Method
     */
    public void waitVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    /**
     * Wait Visibility Wrapper Method
     */
    public void waitVisibilityOfAllElements(List<WebElement> webElements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
    /**
     * Wait InVisibility Wrapper Method
     */
    public void waitInVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
    /**
     * Wait TextToBe Wrapper Method
     */
    public void waitTextToBe(WebElement webElement, String text) {
        wait.until(ExpectedConditions.attributeToBe(webElement, "value", text));
    }
    /**
     * Wait URL Contains Wrapper Method
     */
    public void waitURLContains(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }
    /**
     * Click
     */
    public void click (WebElement webElement) {
        waitVisibility(webElement);
        webElement.click();
    }
    /**
     * Move mouse
     */
    public void moveMouse (WebElement webElement) {
        Actions action = new Actions(driver);
        waitVisibility(webElement);
        action.moveToElement(webElement).perform();
    }
    /**
     * Write Text
     */
    public void writeText (WebElement webElement, String text) {
        waitVisibility(webElement);
        webElement.sendKeys(text);
    }
    /**
     * Read Text
     */
    public String readText (WebElement webElement) {
        waitVisibility(webElement);
        return webElement.getText();
    }
    /**
     * Count Of Elements
     */
    public Integer countElements(String xpath) {
        return driver.findElements(By.xpath(xpath)).size();
    }

}
