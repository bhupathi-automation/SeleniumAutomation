package workout;

import com.test.heroku.ObjectRepo.FileUploaderUIObject;
import com.test.heroku.common.WebDriverManager;
import com.test.heroku.elements.Operation;
import org.openqa.selenium.WebDriver;
import utils.ThreadWrapper;

import java.util.Scanner;
import java.util.logging.Logger;

public class practice {

//    private static final Logger LOG = Logger.getLogger("practice");

    private static WebDriver driver(){

        WebDriverManager.setCurrentBrowserName("CH");
        WebDriverManager.setDriver();
        WebDriver driver = WebDriverManager.getDriver();
        return driver;
    }

    public static void main(String[] args) throws InterruptedException {

        String url = "https://the-internet.herokuapp.com/upload";

        driver().get(url);

        Operation.click(FileUploaderUIObject.CHOOSE_FILE_BUTTON);


        ThreadWrapper.sleep(3000);
        driver().close();
    }
}
