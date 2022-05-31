package com.test.heroku.elements;

import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {
    private WebDriver driver(){
        WebDriver driver  = WebDriverManager.getDriver();;
        return driver;
    }

    public Alert alert(){
        return driver().switchTo().alert();
    }

    public void acceptAlert(){
        alert().accept();
    }

    public void dismissAlert(){
        alert().dismiss();
    }

    public String textOnAlert(){
        return alert().getText();
    }

    public Alerts enterText(String s){
        alert().sendKeys(s);
        return this;
    }

    public boolean isAlertPresent(){
        WebDriverWait waitForAlert = new WebDriverWait(driver(), 10);
        try{
            waitForAlert.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert is present on the Page");
            return true;
        }catch (TimeoutException ex){
            System.out.println("Alert is NOT present on the Page");
            return false;
        }

    }


}
