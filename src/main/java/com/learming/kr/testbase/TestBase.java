package com.learming.kr.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.*;

public class TestBase {
    FileInputStream fis;
    public Properties Config=new Properties();

    static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    static String basepath=System.getProperty("user.dir")+"/src/test/resources/reports/";
    static String reportpath=basepath+"/ExtentReportResults.html";
    String screenshotpath=basepath+"/screenshot";


    public void setupMethod(){
        try{
            fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
            Config.load(fis);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static List<DriverFactory> webdriverthreadpool=new ArrayList<DriverFactory>();
    private static ThreadLocal<DriverFactory> driverFactoryThreadLocal;
    private static ThreadLocal<FluentWait<WebDriver>> waitThread = new ThreadLocal<>();

    @BeforeSuite
    public static void initialiseDiver(){
        driverFactoryThreadLocal=ThreadLocal.withInitial(()->{
            DriverFactory df=new DriverFactory();
            webdriverthreadpool.add(df);
            return df;
        });
        htmlReporter = new ExtentHtmlReporter(reportpath);
        extent = new ExtentReports ();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Kalyan Roy");
        htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
        htmlReporter.config().setReportName("Name of the Report Comes here");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);

    }

    public WebDriver getDriver(String browser){
        return driverFactoryThreadLocal.get().getDriver(browser);
    }

    @AfterSuite
    public static void destroyDiver(){
        for(DriverFactory df:webdriverthreadpool)
        {
            df.QuitDriver();
        }
        extent.flush();
    }

    public void navigate(String url, WebDriver driver){
        driver.navigate().to(url);
    }

    public static void setWaitThread(WebDriver driver) {

        waitThread.set(new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(ElementNotVisibleException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(NotFoundException.class));
    }

   public static FluentWait<WebDriver> getWaitThread(WebDriver driver) {
        if (waitThread.get() == null) {
            setWaitThread(driver);
        }
        return waitThread.get();
    }

    public WebElement visibilityOf(WebElement element,WebDriver driver) {
        try {
            System.out.println("In wait");
            getWaitThread(driver).until(ExpectedConditions.visibilityOf(element));
            System.out.println("Exit wait");
        } catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }
}
