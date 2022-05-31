package com.test.heroku.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverSetup {
    private static final Logger LOG = Logger.getLogger("WebDriverSetup");
    private WebDriver driver;



    public WebDriver getWebDriver(String browser){
        int retryCntr = 0;
        while(retryCntr++ < 3){
            try{
                switch(browser){

                    case "IE":
                        driver = getIEDriver();
                        windowMaximize();
                        break;
                    case "CH":
                        driver = getChromeDriver();
                        windowMaximize();
                        break;
                    case "FF":
                        driver = getFirefoxDriver();
                        break;

                    default:
                        LOG.warning("Invalid Browser Name" + browser);
                        return null;
                }
                break;
            } catch(Exception e){
                throw new RuntimeException("Failed to fetch the Web Driver!!!");
            }
        }
        return driver;
    }

    private WebDriver getIEDriver(){
        return new InternetExplorerDriver();
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", HerokuConstants.CHROME_EXE);
        return new ChromeDriver();
    }

    private WebDriver getFirefoxDriver(){
        return new FirefoxDriver();
    }

    private void setInitialDriverTimeouts(){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    private void windowMaximize(){
        driver.manage().window().maximize();
    }
}
