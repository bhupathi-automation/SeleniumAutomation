package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UIElement {

    public static WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public static void driverWait(By by){
        WebDriverWait wait = new WebDriverWait(driver(),30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement findElement(By by){
        return driver().findElement(by);
    }

    public static List<WebElement> findElements(By element) {
        return driver().findElements(element);
    }

    public static String getText(By by){
        return findElement(by).getText();
    }
    public static String getText(WebElement element){
        return element.getText();
    }
}
