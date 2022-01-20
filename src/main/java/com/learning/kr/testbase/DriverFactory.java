package com.learning.kr.testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    WebDriver driver;

    public WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/driver/chromedriver");
            driver= new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/driver/geckodriver");
            driver= new FirefoxDriver();
        }
        return driver;
    }

    public void QuitDriver(){
        if(driver!=null){
            driver.quit();
        }
    }
}
