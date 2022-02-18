package com.learning.kr.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by kalyanroy on 10/06/20.
 */
public class TestWebBrowser {

    public static AppiumDriver driver;

    public static void main(String args[]){

        try {
            DesiredCapabilities cap=new DesiredCapabilities();
            cap.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
            cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            System.setProperty("webdriver.chrome.driver", "/Users/kalyanroy/Documents/git/IMS/seamlessaqakt/src/test/resources/drivers/chromedriver_2.44");

            driver.get("http://google.com");

            driver.findElement(By.name("q")).sendKeys("Hello Appium");
            Thread.sleep(5000);
            driver.quit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
