package com.learning.kr.appium.testcases;

import com.learning.kr.appium.capabilities.TestBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;


/**
 * Created by kalyanroy on 13/11/20.
 */
public class BasicOperation extends TestBase
{

	public static void main(String args[]) throws InterruptedException
	{
		AndroidDriver<AndroidElement> driver=CapabilitiesofAppium(false,"");

		WifiAutomation(driver);


	}
	 public static void  WifiAutomation(AndroidDriver<AndroidElement> driver) throws InterruptedException
	 {
       driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		 Thread.sleep(1000);
       driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("android:id/checkbox")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.className("android.widget.EditText")).sendKeys("Kalyan Roy");
		 Thread.sleep(1000);
		 driver.findElement(By.id("android:id/button1")).click();
       Thread.sleep(5000);
	 }
}
