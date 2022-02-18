package com.learning.kr.appium.testcases;

import com.learning.kr.appium.capabilities.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

/**
 * Created by kalyanroy on 13/11/20.
 */
public class GesturesTest extends TestBase
{
	public static void main(String args[]) throws InterruptedException
	{
		AndroidDriver<AndroidElement> driver=CapabilitiesofAppium(false,"");


		geusterDragandDropTest(driver);
	}

	public static void  geusterTapTest(AndroidDriver<AndroidElement> driver) throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		Thread.sleep(1000);
		TouchAction t=new TouchAction(driver);
		AndroidElement element=driver.findElement(By.xpath("//android.widget.TextView[@text='Expandable Lists']"));
		t.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='1. Custom Adapter']")).click();
		Thread.sleep(1000);
		AndroidElement PeopleNames=driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(PeopleNames)).withDuration(ofSeconds(2))).release().perform();
		Thread.sleep(1000);
	}

	public static void  geusterSwappingTest(AndroidDriver<AndroidElement> driver) throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Date Widgets']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='2. Inline']")).click();
//		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//android.widget.TextView[@text='1. CHANGE THE TIME']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@content-desc='9']")).click();
		Thread.sleep(1000);

		TouchAction t=new TouchAction(driver);
		//long press //on element// 2 sec// move to another element and you release
		WebElement first=driver.findElementByXPath("//*[@content-desc='15']");
		WebElement second=driver.findElementByXPath("//*[@content-desc='45']");


		t.longPress(longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second)).release().perform();

	}

	public static void  geusterScrollingTest(AndroidDriver<AndroidElement> driver) throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		Thread.sleep(1000);
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
	}

	public static void  geusterDragandDropTest(AndroidDriver<AndroidElement> driver) throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
		Thread.sleep(1000);

		TouchAction t=new TouchAction(driver);

		WebElement first=driver.findElementsByClassName("android.view.View").get(0);
		WebElement second=driver.findElementsByClassName("android.view.View").get(1);

		//t.longPress(longPressOptions().withElement(element(first))).moveTo(element(second)).release().perform();
		t.longPress(element(first)).moveTo(element(second)).release().perform();

		Thread.sleep(1000);
	}
}
