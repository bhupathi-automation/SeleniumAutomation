package com.test.heroku;

import com.test.heroku.ObjectRepo.HerokuMainPageUIObject;
import com.test.heroku.assertions.AssertMaster;
import com.test.heroku.elements.Alerts;
import com.test.heroku.elements.Operation;
import com.test.heroku.elements.UIElement;
import com.test.heroku.login.LoginToHeroku;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.test.heroku.ObjectRepo.HerokuMainPageUIObject.*;
import static com.test.heroku.common.HerokuTextConstants.BASIC_AUTH_SUCCESS_TEXT;
import static com.test.heroku.common.HerokuTextConstants.BASIC_AUTH_TEXT;

public class AlertTest extends BaseTest{
    private final AssertMaster ASSERT = new AssertMaster();

//    @Test(retryAnalyzer = listeners.RetryAnalyser.class)
//    @Test(invocationCount =3)
    @Test (enabled = true,priority = 1,groups = "smoke")
    public void basicAuthAlertTest() throws InterruptedException {
        /* Check below blog for help
        *  https://www.lambdatest.com/blog/handling-login-popup-in-selenium-webdriver-using-java/#:~:text=To%20handle%20the%20basic%20authentication,with%20the%20web%20page's%20URL.&text=When%20the%20login%20pop%2Dup,successfully%20logged%20into%20the%20website.
        */
        LoginToHeroku.launchApp();
        By xpath = HerokuMainPageUIObject.xpathBuilder("Basic Auth");
        Operation.click(xpath);
        Thread.sleep(2000);

        String temp_url="the-internet.herokuapp.com/basic_auth";
        String username="admin";
        String password="admin";

        //URL =  https://admin:admin@the-internet.herokuapp.com/basic_auth
        String URL = "https://" + username +":"+password +"@"+ temp_url;
        LoginToHeroku.loginToURL(URL);
        Thread.sleep(1000);

        ASSERT.assertStep(UIElement.getText(BASIC_AUTH_LABEL), BASIC_AUTH_TEXT, "Assert Basic Auth success header label");
        ASSERT.assertStep(UIElement.getText(CREDENTIALS_SUCCESS), BASIC_AUTH_SUCCESS_TEXT, "Assert Basic Auth credentials success message");
    }

    @Test (enabled = false)
    public void jsAlertTest() throws InterruptedException {
        LoginToHeroku.launchApp();
        By xpathForAlertLink = HerokuMainPageUIObject.xpathBuilder("JavaScript Alerts");
        By xpathForAlertPageText = HerokuMainPageUIObject.xpathBuilder("JavaScript Alerts");
        Alerts alerts = new Alerts();
        String stringToEnter = "Test11";

        Operation.click(xpathForAlertLink);

        String alertPageText = UIElement.getText(xpathForAlertPageText);
        Thread.sleep(1000);
        ASSERT.assertStep(alertPageText, "JavaScript Alerts", "Assert Alert page label");

        Operation.click(JS_ALERT_PROMPT);

        Thread.sleep(2000);
        System.out.println("Text on Prompt Alert is: " + alerts.textOnAlert());
        alerts.enterText(stringToEnter).acceptAlert();

        ASSERT.assertStep(alertPageText, "JavaScript Alerts", "Assert Alert page label");
        String alertPageRestultText = UIElement.getText(JS_ALERT_RESULT);

        ASSERT.assertStep(alertPageRestultText, "You entered: "+stringToEnter, "Assert the text Entered on Alert");
    }

}
