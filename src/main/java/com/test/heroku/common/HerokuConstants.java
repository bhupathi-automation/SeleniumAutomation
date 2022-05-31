package com.test.heroku.common;

public class HerokuConstants {
    //Folders
    public static final String BASE_DIR = System.getProperty("user.dir");
    public static final String SCREENSHOT_DIR = String.format( "%s\\src\\test\\screenshot", BASE_DIR);

    //URL
    public static final String HEROKU_URL = String.format("https://the-internet.herokuapp.com/");
    public static final String HEROKU_URL_LOGIN = String.format("https://the-internet.herokuapp.com/login");

    //driver.exe files
    public static final String CHROME_EXE = String.format("D:\\workspace\\drivers\\chromedriver.exe");  //V99

    //Login credentials
    public static final String USERNAME = "tomsmith";
    public static final String PASSWORD = "SuperSecretPassword!";

}
