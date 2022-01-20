package com.learning.kr.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class TestBase {
    FileInputStream fis;
    public Properties Config=new Properties();

    static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    static String basepath=System.getProperty("user.dir")+"/src/test/resources/reports/";
    static String reportpath=basepath+"/ExtentReportResults.html";
    static String screenshotpath=basepath+"/screenshot";


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
    public static void initialise(){
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

        //extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

    }

    public WebDriver getDriver(String browser){
        return driverFactoryThreadLocal.get().getDriver(browser);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
            logger.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method.
            String screenshotPath = getScreenhot(result.getName());
            //To add it in the extent report
            logger.log(Status.FAIL, String.valueOf(logger.addScreencastFromPath(screenshotPath)));
        } else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
//        extent.removeTest(logger);
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

    public void waitForElement(By path,WebDriver driver) {
        try {
            System.out.println("In wait");
            getWaitThread(driver).until(ExpectedConditions.visibilityOfElementLocated(path));
            getWaitThread(driver).until(ExpectedConditions.elementToBeClickable(path));
            System.out.println("Exit wait");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static String getScreenhot( String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driverFactoryThreadLocal.get().driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File finalDestination = new File(screenshotpath);
        FileUtils.copyFile(source, finalDestination);
        return screenshotpath;
    }
}
