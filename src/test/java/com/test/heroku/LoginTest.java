package com.test.heroku;

import com.test.heroku.assertions.AssertElement;
import com.test.heroku.assertions.AssertMaster;
import com.test.heroku.assertions.AssertTitle;
import com.test.heroku.elements.Operation;
import com.test.heroku.elements.UIElement;
import com.test.heroku.login.LoginToHeroku;

import org.testng.annotations.Test;
import static com.test.heroku.ObjectRepo.HerokuMainPageUIObject.*;
import static com.test.heroku.ObjectRepo.HerokuMainPageUIObject.LOGIN_PAGE_LABEL;
import static com.test.heroku.common.HerokuConstants.PASSWORD;
import static com.test.heroku.common.HerokuConstants.USERNAME;

public class LoginTest extends BaseTest{

    private final AssertMaster ASSERT = new AssertMaster();
    public static final String loginSuccessText = "Welcome to the Secure Area. When you are done click logout below.";
    String loginSuccessPageUrl = "https://the-internet.herokuapp.com/secure";

    @Test
    public void loginPageText(){
        LoginToHeroku.launchLoginPage();

        String loginText = UIElement.getText(LOGIN_PAGE_LABEL);

        ASSERT.assertStep(loginText, "Login Page", "Assert Login Page Label");
//        ASSERT.assertStep(loginText, "Login Page1", "Assert Login Page Label");
    }

    @Test
    public void loginSuccessValidation(){
        LoginToHeroku.launchLoginPage();

        Operation.enterText(USER_NAME_TEXTBOX, USERNAME);
        Operation.enterText(PASSWORD_TEXTBOX, PASSWORD);
        Operation.click(LOGIN_BUTTON);

        //Validation on successful Login
        String actualTextAfterLogin = UIElement.getText(LOGIN_SUCCESS_TEXT);
        String currentUrl = AssertTitle.getCurrentUrl();
        boolean isLogoutEnabled = AssertElement.isElementEnabled(LOGOUT_BUTTON);

        ASSERT.assertStep(actualTextAfterLogin, loginSuccessText, "Validate the login success Text ");
        ASSERT.assertStep(currentUrl, loginSuccessPageUrl, "Validate the login success page URL ");
        ASSERT.assertStep(isLogoutEnabled, true, "Validate the Logout button is enabled ");

        //Click on Logout
        Operation.click(LOGOUT_BUTTON);
        ASSERT.assertStep(UIElement.getText(LOGIN_PAGE_LABEL), "Login Page", "Verify user is on Login Page");
    }
}
