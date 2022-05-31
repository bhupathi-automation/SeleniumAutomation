package steps;

//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import org.junit.Before;
import com.test.heroku.ObjectRepo.GeneralUIObject;
import com.test.heroku.assertions.AssertTitle;
import com.test.heroku.elements.Operation;
import com.test.heroku.login.loginGeneral;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ThreadWrapper;

public class mySteps {

    WebDriver driver;

//    @Before
//    public void setup(){
//        System.setProperty("webdriver.chrome.driver", "D:\\workspace\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//    }

    @Given("^user launches google$")
    public void userLaunchesGoogle() {

        loginGeneral.launchGoogle();
    }

    @When("^user clicks on Gmail link$")
    public void userClicksOnGmailLink() throws InterruptedException {
//        driver.findElement(By.linkText("Gmail")).click();
        Operation.click(GeneralUIObject.GMAIL_LINK);
        ThreadWrapper.sleep(2000);

    }

    @Then("^user validates$")
    public void userValidates() {

        String title = AssertTitle.getTitle();
        System.out.println(title);
        Assert.assertEquals(true, false);
        System.out.println("THIS IS END OF TEST");
//        System.out.println("title of the page is: "+driver.getTitle());
//        System.out.println("page URL is: "+driver.getCurrentUrl());
    }


}
