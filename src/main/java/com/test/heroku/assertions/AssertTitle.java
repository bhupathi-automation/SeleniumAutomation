package com.test.heroku.assertions;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertTitle {

    public static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static void assertTitleofPage(String title){
        String actualTitle = driver().getTitle();

        Assert.assertEquals(actualTitle, title);
        System.out.println("Title is verified successfully ");
    }

    public static String getTitle(){  return driver().getTitle();   }

    public static String getCurrentUrl() {return driver().getCurrentUrl();}
}
