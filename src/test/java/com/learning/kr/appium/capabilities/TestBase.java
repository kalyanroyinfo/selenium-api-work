package com.learning.kr.appium.capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * Created by kalyanroy on 13/11/20.
 */
public class TestBase
{
	public static AndroidDriver CapabilitiesofAppium( boolean mobileBrowser,String browser){
		AndroidDriver<AndroidElement> driver=null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try
		{
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			if(!mobileBrowser)
			{
				String appPath = System.getProperty("user.dir") + "/src/test/resources/app/ApiDemos-debug.apk";
				File app = new File(appPath);
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
				capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			}else{
				if(browser.equalsIgnoreCase("chrome")){
					//System.setProperty("webdriver.chrome.driver","/Users/kalyanroy/Documents/public-git/BLANK-PROJECT/TestProject/src/test/resources/driver/chromedriver");
					//System.setProperty("webdriver.chrome.driver", "/Users/kalyanroy/Documents/git/IMS/seamlessaqakt/src/test/resources/drivers/chromedriver_2.44");
				}
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,browser);
			}
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			System.out.println("Driver initialized Successfully");
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return driver;
	}
}
