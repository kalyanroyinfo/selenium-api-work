package com.learming.kr.testclasses;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.learming.kr.exception.TemparatureDiffException;
import com.learming.kr.testbase.TestBase;
import com.learming.kr.utility.RestAPIRequestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pk on 16/09/21.
 */
public class TestNgCalling extends TestBase
{
	String baseUrl = "https://api.openweathermap.org/";

	RestAPIRequestBuilder restAPIRequestBuilder = RestAPIRequestBuilder.getInstance();


	@Test(groups = {"compare-temparature"})
	public void compareTemparature() throws InterruptedException {
		logger = extent.createTest("passTest");
		setupMethod();
		WebDriver driver=getDriver("chrome");
		navigate(Config.getProperty("testsiteurl"),driver);
		WebElement ele=driver.findElement(By.xpath("//input[@name='query']"));
		visibilityOf(ele,driver);
		ele.sendKeys("Kolkata");
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		WebElement temp=driver.findElement(By.xpath("//h2[contains(text(),'Current Weather')]//parent::div//div[@class='temp']"));
		String temparr= temp.getText();

		String arr[]=temparr.split("°");

		Float webTemparature=Float.valueOf(arr[0]);

		System.out.println(webTemparature);
		Thread.sleep(10000);
		Map<String,String> headers=new HashMap<String, String>();
//		headers.put("Content-Type","application/json");

		Map<String,String> params=new HashMap<String, String>();

		params.put("q","kolkata");
		params.put("appid","7fe67bf08c80ded756e598d6f8fedaea");
		params.put("units","metric");

		Response response= restAPIRequestBuilder.hitAPI(baseUrl, RestAssured.given(),
				Method.GET,"/data/2.5/weather","",headers,params,false );

		Float apiTemparature=response.jsonPath().get("main.temp");

		System.out.println(apiTemparature);

		float diff = Math.abs(apiTemparature-webTemparature);

		System.out.println(diff);

		if(diff>=2){
			try {
				logger.log(Status.FAIL, MarkupHelper.createLabel("Temparature diff is greature than 2°", ExtentColor.RED));
				throw new TemparatureDiffException("Temparature diff is greature than 2° ");
			} catch (TemparatureDiffException e) {
				e.printStackTrace();
			}
		}else{
			logger.log(Status.PASS, MarkupHelper.createLabel("Temparature diff is within 2° C", ExtentColor.RED));
			System.out.println("Success");
		}

		System.out.println("Completedgit init");


	}
}
