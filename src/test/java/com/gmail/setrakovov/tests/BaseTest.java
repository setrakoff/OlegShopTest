package com.gmail.setrakovov.tests;

import com.gmail.setrakovov.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void setup () {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //Create a Chrome driver
        driver = new ChromeDriver();
        //Maximize Window
        driver.manage().window().maximize();
        //Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Launch main page from properties
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    @AfterClass
    public void teardown () {
        driver.quit();
    }

}
