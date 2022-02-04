package com.learning.kr.testclasses;

import com.learning.kr.pages.OYOHomePage;
import com.learning.kr.testbase.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


/*
Preetam--9007285004

String = "aahtt1296fftbbgst783drr!4@@5"
output =123456789
 */
public class OyoTest extends TestBase {

    @Test
    public void testOyoSearch() throws InterruptedException {
        setupMethod();
        WebDriver driver=getDriver(Config.getProperty("browser"));
        navigate(Config.getProperty("testsiteurl1"),driver);

        Thread.sleep(2000);

        OYOHomePage oyoHomePage=new OYOHomePage(driver);

        oyoHomePage.setPlace("KOLKATA");

        oyoHomePage.addRoom();


        oyoHomePage.selectDate();


        oyoHomePage.clickOnSearch();
        Thread.sleep(10000);
        oyoHomePage.clickOnBook();
        Thread.sleep(10000);

    }
}
