package com.learning.kr.appium.testcases;

import com.learning.kr.appium.capabilities.TestBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

/**
 * Created by kalyanroy on 14/11/20.
 */
public class BrowserTest extends TestBase
{
	public static void main(String args[]) throws InterruptedException
	{
		AndroidDriver<AndroidElement> driver=CapabilitiesofAppium(true,"chrome");

		driver.get("http://google.com");

		driver.findElement(By.name("q")).sendKeys("Hello Appium");
		Thread.sleep(5000);
	}
}
