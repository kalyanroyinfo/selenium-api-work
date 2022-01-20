package com.learning.kr.testclasses;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.learning.kr.exception.TemparatureDiffException;
import com.learning.kr.testbase.TestBase;
import com.learning.kr.utility.RestAPIRequestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by pk on 16/09/21.
 */
public class TestNgCalling extends TestBase {
	String baseUrl = "https://api.openweathermap.org/";

	RestAPIRequestBuilder restAPIRequestBuilder = RestAPIRequestBuilder.getInstance();


	@Test(groups = {"compare-temparature"})
	public void compareTemparature() throws InterruptedException {
		logger = extent.createTest("Compare Temparature Test");
		setupMethod();
		WebDriver driver = getDriver("chrome");
		navigate(Config.getProperty("testsiteurl"), driver);
		By query = By.xpath("//input[@name='query']");
		WebElement ele = driver.findElement(query);
		waitForElement(query, driver);
		ele.sendKeys("Kolkata");
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		WebElement temp = driver.findElement(By.xpath("//h2[contains(text(),'Current Weather')]//parent::div//div[@class='temp']"));
		String temparr = temp.getText();

		String arr[] = temparr.split("°");

		Float webTemparature = Float.valueOf(arr[0]);

		System.out.println(webTemparature);
		Thread.sleep(10000);
		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type","application/json");

		Map<String, String> params = new HashMap<String, String>();

		params.put("q", "kolkata");
		params.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");
		params.put("units", "metric");

		Response response = restAPIRequestBuilder.hitAPI(baseUrl, RestAssured.given(),
				Method.GET, "/data/2.5/weather", "", headers, params, false);

		Float apiTemparature = response.jsonPath().get("main.temp");

		System.out.println(apiTemparature);

		float diff = Math.abs(apiTemparature - webTemparature);

		System.out.println(diff);

		if (diff >= .1) {
			try {
				logger.log(Status.FAIL, MarkupHelper.createLabel("Temparature diff is greature than 2°", ExtentColor.RED));
				Assert.fail("Temparature diff is greature than 2°");
				throw new TemparatureDiffException("Temparature diff is greature than 2° ");
			} catch (TemparatureDiffException e) {
				e.printStackTrace();
			}
		} else {
			logger.log(Status.PASS, MarkupHelper.createLabel("Temparature diff is within 2° C", ExtentColor.GREEN));
			System.out.println("Success");
		}

		System.out.println("Completedgit init");

	}

	@Test
	public void automateMakeMyTripSingleTrip() throws InterruptedException {
		setupMethod();
		WebDriver driver = getDriver("chrome");
		navigate("https://www.makemytrip.com/", driver);
		Thread.sleep(10000);


		By logo = By.xpath("//a[@data-cy='mmtLogo']");
		Actions action = new Actions(driver);
		WebElement webElement = driver.findElement(logo);
		action.moveToElement(webElement).click(webElement).build().perform();

		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();

		driver.findElement(By.id("fromCity")).click();
		Thread.sleep(1000);
		By form = By.xpath("//input[@placeholder='From']");
		WebElement ele = driver.findElement(form);
		waitForElement(form, driver);
		Thread.sleep(1000);
		ele.sendKeys("KOL");
		Thread.sleep(1000);
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		WebElement to = driver.findElement(By.xpath("//input[@placeholder='To']"));
		to.click();
		Thread.sleep(1000);
		to.sendKeys(Keys.BACK_SPACE);
		to.sendKeys("IXZ");
		Thread.sleep(1000);
		to.sendKeys(Keys.ARROW_DOWN);
		to.sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]")).click();
		Thread.sleep(5000);

		String flag = "False";


		while (flag == "False") {

			if (driver.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Mar 10 2022')]")).size() > 0) {

				driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'Mar 10 2022')]")).click();
				flag = "True";
				Thread.sleep(5000);
			} else {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}

			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
		Thread.sleep(10000);

	}

	@Test
	public void automateMakeMyTripShowAllMenu() throws InterruptedException {
		setupMethod();
		WebDriver driver = getDriver("chrome");
		navigate("https://www.makemytrip.com/", driver);
		Thread.sleep(5000);

		By logo = By.xpath("//a[@data-cy='mmtLogo']");
		Actions action = new Actions(driver);
		WebElement webElement = driver.findElement(logo);
		action.moveToElement(webElement).click(webElement).build().perform();


		List<WebElement> allMenu=driver.findElements(By.xpath("//li[contains(@data-cy,'menu')]//child::a//child::span[2]"));

		for(WebElement element:allMenu){
			System.out.println(element.getAttribute("innerHTML"));
		}

	}
}
