package com.learning.kr.appium;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kalyanroy on 26/06/20.
 */
public class TestFindingElements {
    public static AndroidDriver driver;
    public static void main(String args[]){

        try {


            File app=new File("/Users/kalyanroy/Documents/git/IMS/seamlessaqakt/src/test/resources/app/selendroid-test-app-0.17.0.apk");

            AppiumDriverLocalService appiumDriverLocalService=AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                            .withAppiumJS(new File("/usr/local/Cellar/node/11.10.0/lib/node_modules/appium/build/lib/main.js"))
                            .withLogFile(new File(System.getProperty("user.dir")+"/src/test/resources/log/automation.log"))
                            .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
            );

            appiumDriverLocalService.start();

            DesiredCapabilities cap=new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel-kalyan");
            cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".HomeScreenActivity");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);


            driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();

            System.out.println("*************Following is the CPU Info***********");

            List<List<Object>> performanceCPUData = driver.getPerformanceData("io.selendroid.testapp", "cpuinfo", 5);


            for (List<Object> lst:performanceCPUData) {
                for(Object obj:lst){
                    System.out.println(obj);
                }
            }


            System.out.println("*************Following is the Network Info***********");
                        List<List<Object>> performanceNetworkData = driver.getPerformanceData("io.selendroid.testapp", "networkinfo", 5);


                        for (List<Object> lst:performanceNetworkData) {
                            for(Object obj:lst){
                                System.out.println(obj);
                            }
                        }

            System.out.println("*************Following is the Memory Info***********");
            HashMap<String, Integer> readableMemoryData=getMemoryInfo("io.selendroid.testapp",driver);

            //System.out.println(readableMemoryData);

            readableMemoryData.forEach((k, v) -> System.out.println(k + " : " + v));

            System.out.println("*************Following is the PerformanceDataTypes Info***********");
            List<String> performanceTypes = driver.getSupportedPerformanceDataTypes();

            for (String datatypes:performanceTypes) {

                    System.out.println(datatypes);

            }
            Thread.sleep(10000);

            driver.quit();

            appiumDriverLocalService.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, Integer> getMemoryInfo(String apppackageName,AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(apppackageName, "memoryinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }
}
