package steps;

import com.test.heroku.common.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import static runner.TestRunner.FIRST_BROWSER_NAME;

public class BaseSteps {

    @Before
    public void startBrowser(){

        WebDriverManager.setCurrentBrowserName(FIRST_BROWSER_NAME);
        WebDriverManager.setDriver();

        if(WebDriverManager.getDriver() == null){
            throw new RuntimeException("Unable to Fetch browser for: "+FIRST_BROWSER_NAME);
        }
    }

    @After
    public void closeBrowser(){
        WebDriverManager.getDriver().quit();
    }

    @BeforeStep
    public void runBeforeEveryStep(){
    }

    @AfterStep
    public void runAfterEveryStep(){
    }

    /* Notes:
    // Multiple Before/After hooks to run
    @Before(order = int) : This runs in increment order, means value 0 would run first and 1 would be after 0.
    @After(order = int) : This runs in decrements order, means apposite of @Before. Value 1 would run first and 0 would be after 1.

    * @Before(order=0) -->@Before(order=1) --> @Before(order=2) ----> Scenarios ----> @After
    * @After(order=2) --> @After(order=1) --> @After(order=0)

    //TAGGED Hooks
    @Before("@scenario1") --> this runs only while @scenario1 gets executed
    @Before("@scenario1 and not @scenario2")

    sequence :
        @Before("@scenario1") --> @Before   [Tagged hooks run First for @Before]
        @After --> @After("@scenario1")     [Tagged hooks run Last for @After]

    */
}
