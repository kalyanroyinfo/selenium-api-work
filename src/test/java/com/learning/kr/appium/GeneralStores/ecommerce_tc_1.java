package com.learning.kr.appium.GeneralStores;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kalyanroy on 13/04/21.
 */
public class ecommerce_tc_1
{
	public static AndroidDriver driver;

	public static void main(String args[])
	{

		try
		{

			File app = new File("/Users/kalyanroy/Documents/git/IMS/seamlessaqakt/src/test/resources/app/General-Store.apk");

			AppiumDriverLocalService appiumDriverLocalService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(
					new File("/usr/local/bin/node")).withAppiumJS(new File(
					"/usr/local/Cellar/node/11.10.0/lib/node_modules/appium/build/lib/main.js")).withLogFile(new File(
					System.getProperty("user.dir") + "/src/test/resources/log/automation.log")).withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

			appiumDriverLocalService.start();

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
			cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

			driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();

			driver.quit();

			appiumDriverLocalService.stop();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
