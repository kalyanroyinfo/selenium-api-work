package com.learming.kr.testbase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestBase {
    FileInputStream fis;
    public Properties Config=new Properties();;
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

    @BeforeSuite
    public static void initialiseDiver(){
        driverFactoryThreadLocal=ThreadLocal.withInitial(()->{
            DriverFactory df=new DriverFactory();
            webdriverthreadpool.add(df);
            return df;
        });
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
    }

    public void navigate(String url, WebDriver driver){
        driver.navigate().to(url);
    }
}
