package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Action {

    private static Actions act = new Actions(WebDriverManager.getDriver());

    public static void moveTo(By element){
        act.moveToElement(UIElement.findElement(element)).perform();
    }

    public static void doubleClick(By by){
        act.doubleClick(UIElement.findElement(by)).build().perform();
    }

    public static void rightClick(By by){
        act.contextClick(UIElement.findElement(by)).build().perform();
    }

//    public static void clickAction(By by){
//        act.moveToElement(UIElement.findElement(by))
//    }

}
