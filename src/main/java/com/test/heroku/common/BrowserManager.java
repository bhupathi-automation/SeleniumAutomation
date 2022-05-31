package com.test.heroku.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import utils.ThreadWrapper;

import java.util.logging.Logger;

public class BrowserManager {
    private static Logger LOG = Logger.getLogger("BrowserManager");

    public static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static void closeAlertsIfExist() {
        try {
            Alert alert = driver().switchTo().alert();
            alert.dismiss();
            Thread.sleep(1000);
        } catch (NoAlertPresentException | NoSuchWindowException | InterruptedException e){
            LOG.info("No Alerts present WebPage");
        }
    }


}
