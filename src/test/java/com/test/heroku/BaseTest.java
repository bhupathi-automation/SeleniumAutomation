package com.test.heroku;

import com.test.heroku.common.HerokuConstants;
import com.test.heroku.common.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BaseTest {
    private static final Logger LOG = Logger.getLogger("BaseTest");
    public String firstBrowser;
    public String secondBrowser;

    @BeforeSuite
    public void beforeSuite(){     }

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.setCurrentBrowserName("CH");
        WebDriverManager.setDriver();
    }

//    @BeforeMethod (enabled = false)
    public void openBrowser(ITestContext ctx){
        if(ctx.getCurrentXmlTest().getThreadCount() == 1){
            //code to delete temp files in project
        }

        String browserName = ctx.getCurrentXmlTest().getParameter("browser");
        WebDriverManager.setCurrentBrowserName(browserName);
        WebDriverManager.setDriver();

        if(WebDriverManager.getDriver() == null){
            throw new RuntimeException("Unable to Fetch browser for: "+browserName);
        }
    }

//    @BeforeMethod (enabled = true)
//    @Parameters("browser")
    public void openBrowser(String browser1){
        WebDriverManager.setCurrentBrowserName(browser1);
        WebDriverManager.setDriver();
    }



    /* User this method for CUCUMBER tests */
//    @BeforeMethod
//    public void openBrowser(){
//
//        String browserName = "CH";
//        WebDriverManager.setCurrentBrowserName(browserName);
//        WebDriverManager.setDriver();
//
//        if(WebDriverManager.getDriver() == null){
//            throw new RuntimeException("Unable to Fetch browser for: "+browserName);
//        }
//    }

    @AfterMethod (alwaysRun = true)
    public void closeBrowser(){
        tearDown();
    }

    public void tearDown(){
        WebDriverManager.getDriver().quit();
    }

    public void takeScreenshot(){
        WebDriver driver = WebDriverManager.getDriver();
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenShot, new File(HerokuConstants.SCREENSHOT_DIR +"_screenshot1.png"));
        }catch (Exception e){

        }


    }
}
