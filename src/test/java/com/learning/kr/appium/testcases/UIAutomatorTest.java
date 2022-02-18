package com.learning.kr.appium.testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import se.seamless.aqa.appium.capabilities.TestBase;

/**
 * Created by kalyanroy on 13/11/20.
 */
public class UIAutomatorTest extends TestBase
{
	public static void main(String args[]) throws InterruptedException
	{
		AndroidDriver<AndroidElement> driver=CapabilitiesofAppium(false,"");

		UIAutomatorTest(driver);
	}
	public static void  UIAutomatorTest(AndroidDriver<AndroidElement> driver) throws InterruptedException
	{
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		Thread.sleep(2000);
		System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
	}
}
