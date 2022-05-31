package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;



public class Operation {
    public static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static void click(By element){
        UIElement.findElement(element).click();
    }

    public static void enterText(By element, String text){
        UIElement.findElement(element).sendKeys(text);
    }

    public static void selectCheckbox(By element){
        UIElement.findElement(element).click();
    }

    public static void selectRadioButton(By element){
        UIElement.findElement(element).click();
    }


}
