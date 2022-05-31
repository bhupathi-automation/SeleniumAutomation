package com.test.heroku.login;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.test.heroku.common.HerokuConstants.HEROKU_URL;
import static com.test.heroku.common.HerokuConstants.HEROKU_URL_LOGIN;

public class LoginToHeroku {


    private static WebDriver driver(){
        WebDriver driver = WebDriverManager.getDriver();
        return driver;
    }

    public static void login(){
        driver().get(HEROKU_URL_LOGIN);
        driver().manage().window().maximize();
    }

    public static void launchLoginPage(){
        driver().manage().window().maximize();
        driver().get(HEROKU_URL_LOGIN);
        driver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public static void launchApp(){
        driver().manage().window().maximize();
        driver().get(HEROKU_URL);
        driver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public static void loginToURL(String url){
        driver().get(url);
    }

}
