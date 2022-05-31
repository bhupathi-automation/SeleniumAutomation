import com.test.heroku.BaseTest;
import com.test.heroku.ObjectRepo.FileUploaderUIObject;
//import com.test.heroku.common.WebDriverManager;
import com.test.heroku.elements.Operation;
import com.test.heroku.login.LoginToHeroku;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import utils.ThreadWrapper;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest extends BaseTest {

//    private static WebDriver driver(){
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        return driver;
//    }

    @Test
    public void testMethod() throws InterruptedException {
        System.out.println("TestNG method");
        System.out.println("first testNG test");

//        System.setProperty("webdriver.chrome.driver", "D:\\workspace\\drivers\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement headerText = driver.findElement(By.xpath("//*[text()='Welcome to the-internet1']"));
        System.out.println(headerText.getText());

        //clicking on GMAIL
//        driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/?authuser=0&ogbl']")).click();
//        driver.findElement(By.linkText("Gmail")).click();
//
//        System.out.println("title of the page is: "+driver.getTitle());
//        System.out.println("page URL is: "+driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void test1() throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/upload";

        LoginToHeroku.loginToURL(url);
        ThreadWrapper.sleep(5000);
        Operation.click(FileUploaderUIObject.CHOOSE_FILE_BUTTON);


        ThreadWrapper.sleep(3000);
//        driver().close();
    }

    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        System.out.println(driver.getTitle());

        driver.quit();

    }
}
