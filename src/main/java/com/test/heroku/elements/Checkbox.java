package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Checkbox {

    private static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static boolean isSelected(By by){
        return driver().findElement(by).isSelected();
    }

    public static boolean isEnabled(By by){
        return driver().findElement(by).isEnabled();
    }

    public static void selectCheckbox(By by){
        driver().findElement(by).click();
    }

    public static void unSelectCheckbox(By by){
        if(isSelected(by)){
            selectCheckbox(by);
        } else {
            throw new RuntimeException("Checkbox is already unSelected!!!");
        }
    }
}
