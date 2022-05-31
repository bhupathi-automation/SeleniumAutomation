package runner;


//import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
//import cucumber.api.CucumberOptions;

//@RunWith(Cucumber.class)  -- it is used when cucumberoptions are being run with Junit
@CucumberOptions(
        features = {"src/test/java/features/sampleFeature.feature"},
//        features = {"file:src/test/java/features/sampleFeature.feature:4"},
//        features = {"@reports/Cucumber/rerun_feature.txt"},
        glue = "steps",
//        tags =  "@parallel" ,
//        plugin = {"pretty", "html:reports/Cucumber/zRun", "json:reports/Cucumber/zRun/cucumber.json","rerun:reports/Cucumber/zRun//rerun_feature.txt"},
        plugin = {"pretty", "json:reports/Cucumber/zRun/cucumber.json","rerun:reports/Cucumber/zRun//rerun_feature.txt"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false
        )

public class TestRunner extends AbstractTestNGCucumberTests {

        public static final String FIRST_BROWSER_NAME = "CH";
        public static final String SECOND_BROWSER_NAME = "IE";
        static Thread threadToCreateHTMLReport;


        @AfterClass
        public static void createHTMLReport(){
                threadToCreateHTMLReport = new HTMLReporterThread();
                Runtime.getRuntime().addShutdownHook(threadToCreateHTMLReport);
        }


        @Override
        @DataProvider (parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

}
