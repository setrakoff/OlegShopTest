package com.gmail.setrakovov.tests;

import com.gmail.setrakovov.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void setup () {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //Configure specific options
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        //Create a Chrome driver
        driver = new ChromeDriver(ops);
        //Maximize Window
        driver.manage().window().maximize();
        //Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void getURL() throws InterruptedException {
        //Launch main page from properties
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @AfterClass
    public void teardown () {
        driver.quit();
    }

}
