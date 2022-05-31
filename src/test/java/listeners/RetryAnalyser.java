package listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class RetryAnalyser implements IRetryAnalyzer {
    private final Logger LOG = Logger.getLogger("RetryAnalyser");

    public int retryCount=1;
    public int maxCount =1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount <= maxCount){
            LOG.info("Re-executing the Failed Test: " + iTestResult.getInstanceName() +"-"+ iTestResult.getName()+ " | Retry Count: "+retryCount);
            retryCount ++;

            return true;    // This actually Re-Executes the failed test
        }
        return false;   // This when the Retry stops
    }
}
