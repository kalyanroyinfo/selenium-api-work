package com.learning.kr.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey.*;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.swing.*;
import java.io.File;
import java.net.URL;

/**
 * Created by kalyanroy on 11/06/20.
 */
public class TestHardwareKeys {

    private static AndroidDriver driver;
    private static AppiumDriverLocalService appiumDriverLocalService;

    public static void main(String args[]){

        try {

            File app=new File("/Users/kalyanroy/Documents/git/IMS/seamlessaqakt/src/test/resources/app/selendroid-test-app-0.17.0.apk");

            appiumDriverLocalService=AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                            .withAppiumJS(new File("/usr/local/Cellar/node/11.10.0/lib/node_modules/appium/build/lib/main.js"))
                            .withLogFile(new File(System.getProperty("user.dir")+"/src/test/resources/log/automation.log"))
                            .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
            );

            appiumDriverLocalService.start();

            DesiredCapabilities cap=new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
            cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".HomeScreenActivity");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();





            //This method only available in AndroidDriver not in AppiumDriver
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            Thread.sleep(1000);

//            driver.longPressKey(new KeyEvent(AndroidKey.HOME));
//            Thread.sleep(5000);

//            driver.findElementById("io.selendroid.testapp:id/my_text_field").click();
//            Thread.sleep(2000);
//
//            Actions action=new Actions(driver);
//            action.sendKeys("appium").perform();
//
//            Thread.sleep(5000);

//            driver.toggleWifi();
//            driver.toggleLocationServices();
//            driver.toggleData();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally
        {
            driver.quit();
            appiumDriverLocalService.stop();
        }
    }
}
