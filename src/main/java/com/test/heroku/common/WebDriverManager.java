package com.test.heroku.common;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> IE_WEB_DRIVER = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<WebDriver> CH_WEB_DRIVER = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<WebDriver> FF_WEB_DRIVER = new ThreadLocal<WebDriver>();

    private static final Map<String, ThreadLocal<WebDriver>> DRIVER_MAP = new HashMap<String, ThreadLocal<WebDriver>>();

    static{
        DRIVER_MAP.put("IE", IE_WEB_DRIVER);
        DRIVER_MAP.put("CH", CH_WEB_DRIVER);
        DRIVER_MAP.put("FF", FF_WEB_DRIVER);
    }

    public static ThreadLocal<String> CURRENT_BROWSER_NAME = new ThreadLocal<String>();

    public static void setCurrentBrowserName(String browserName){
        CURRENT_BROWSER_NAME.set(browserName);
    }

    public static String getCurrentBrowserName(){
        return CURRENT_BROWSER_NAME.get();
    }

    public static void setDriver(){
        WebDriver driver = new WebDriverSetup().getWebDriver(getCurrentBrowserName());
        DRIVER_MAP.get(getCurrentBrowserName()).set(driver);
    }

    public static void setDriver(String browserName){
        setCurrentBrowserName(browserName);
        WebDriver driver = new WebDriverSetup().getWebDriver(getCurrentBrowserName());
        DRIVER_MAP.get(getCurrentBrowserName()).set(driver);
    }
    public static WebDriver getDriver(){
        return DRIVER_MAP.get(getCurrentBrowserName()).get();
    }
}
