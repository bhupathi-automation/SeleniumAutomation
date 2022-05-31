package com.test.heroku;

import com.test.heroku.common.HerokuConstants;
import com.test.heroku.common.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class HomepageTest extends BaseTest{
    private WebDriver driver;

    @Test
    public void test1(){
//        openBrowser();
        driver = WebDriverManager.getDriver();

        driver.get(HerokuConstants.HEROKU_URL);

        System.out.println("title is: "+driver.getTitle());
        takeScreenshot();
        tearDown();
    }
}
