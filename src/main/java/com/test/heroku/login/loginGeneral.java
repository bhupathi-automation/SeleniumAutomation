package com.test.heroku.login;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class loginGeneral {
    public static void launchGoogle(){
        WebDriverManager.getDriver().get("https://www.google.co.in/");
        WebDriverManager.getDriver().manage().window().maximize();
    }

}
