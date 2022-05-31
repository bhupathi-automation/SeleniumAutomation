package com.test.heroku.ObjectRepo;

import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class HerokuMainPageUIObject {

    public static final Class<HerokuMainPageUIObject> thisClass = HerokuMainPageUIObject.class;

    //LOGIN elements
    public static final By LOGIN_PAGE_LABEL = By.xpath("//*[text()='Login Page']");
    public static final By USER_NAME_LABEL = By.xpath("//label[text()='Username']");
    public static final By PASSWORD_LABEL = By.xpath("//label[text()='Password']");

    public static final By USER_NAME_TEXTBOX = By.id("username");
    public static final By PASSWORD_TEXTBOX = By.id("password");
    public static final By LOGIN_BUTTON = By.xpath("//i[@class='fa fa-2x fa-sign-in']");      //By.className("fa fa-2x fa-sign-in");

    public static final By LOGIN_SUCCESS_LABEL = By.id("flash");
    public static final By LOGIN_FAILURE_LABEL = By.id("flash");
    public static final By LOGIN_SUCCESS_TEXT = By.xpath("//h4[text()='Welcome to the Secure Area. When you are done click logout below.']");
    public static final By LOGOUT_BUTTON = By.xpath("//i[text()=' Logout']");

    /* Basic Auth */
    public static final By BASIC_AUTH_LABEL = By.xpath("//div[@class='example']/child::h3");
    public static final By CREDENTIALS_SUCCESS = By.xpath("//div[@class='example']/child::p");

    //Homepage elements
    public static final By WELCOME_TEXT = By.xpath("//h1[text()='Welcome to the-internet']");
    public static final By AVAILABLE_EXAMPLES = By.xpath("//h2[text()='Available Examples']");
    public static final By LIST_OF_ELEMENTS = By.xpath("//ul/descendant::li/descendant::a");

    //JavaScript Alert
    public static final By JS_ALERT = By.xpath("//button[text()='Click for JS Alert']");
    public static final By JS_ALERT_CONFIRM = By.xpath("//button[text()='Click for JS Confirm']");
    public static final By JS_ALERT_PROMPT = By.xpath("//button[text()='Click for JS Prompt']");
    public static final By JS_ALERT_RESULT = By.xpath("//p[@id='result']");

    //Dropdown page objects
    public static final By DROPDOWN_PAGE_LABEL = By.xpath("//*[text()='Dropdown List']");
    public static final By DROPDOWN_OPTION = By.id("dropdown");

    //Checkboxes page objects
    public static final By CHECKBOXES_PAGE_LABEL = By.xpath("//*[text()='Checkboxes']");
    public static final By CHECKBOX_ONE = By.xpath("//input[@type='checkbox'][1]");
    public static final By CHECKBOX_TWO = By.xpath("//input[@type='checkbox'][2]");

    //Hovers page Objects
    public static final By HOVERS_PAGE_LABEL = By.xpath("//div[@class='example']/h3");
    public static final By HOVERS_IMAGE1 = By.xpath("(//*[@src='/img/avatar-blank.jpg'])[1]");
    public static final By HOVERS_IMAGE1_LINK = By.xpath("//*[text()='name: user1']/following-sibling::*");
    public static final By NOT_FOUND_TEXT = By.xpath("//*[text()='Not Found']");

    //Key Press page objects
    public static final By KEYPRESS_PAGE_LABEL = By.xpath("//*[text()='Key Presses']");
    public static final By KEYPRESS_TEXTBOX = By.id("target");
    public static final By KEYPRESS_RESULT = By.id("result");

    public static final By xpathBuilder(String elementName){
        String xpathOfElement = String.format("//*[text()='%s']", elementName);
        return By.xpath(xpathOfElement);
    }

    //this method is used to pass the By type variable directly from Feature file
    public static By getObject(String name) {
        try {
            Field field = thisClass.getField(name);
            return (By) field.get(null);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
//            e.printStackTrace();
            throw new RuntimeException("Object Not Found!!! " + e.getMessage());
        }
    }
}
