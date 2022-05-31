package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPress {

    private static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static void pressKey(By element, Keys key){
        driver().findElement(element).sendKeys(key);
    }
}
