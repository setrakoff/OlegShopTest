package com.gmail.setrakovov.pageobjects;

import com.gmail.setrakovov.objects.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public List<ProductCard> productCards;

    /**
     * Constructor
     */
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        productCards = new ArrayList<ProductCard>();
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
     * Wait InVisibility Wrapper Method
     */
    public void waitInVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
    /**
     * Wait TextToBe Wrapper Method
     */
    public void waitTextToBe(WebElement webElement, String text) {
        wait.until(ExpectedConditions.textToBe(By.xpath(webElement.toString()), text));
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
