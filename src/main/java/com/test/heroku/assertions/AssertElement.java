package com.test.heroku.assertions;

import com.test.heroku.common.WebDriverManager;
import com.test.heroku.elements.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AssertElement {

    public static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static boolean isElementEnabled(By element){
        return UIElement.findElement(element).isEnabled();
    }

    public static boolean isElementDisplayed(By element){
        return UIElement.findElement(element).isDisplayed();
    }

    public static boolean isElementSelected(By element){
        return UIElement.findElement(element).isSelected();
    }
}
