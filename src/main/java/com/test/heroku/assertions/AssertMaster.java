package com.test.heroku.assertions;

import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

public class AssertMaster {
    private final Logger LOG = Logger.getLogger("AssertMaster");
    private final Report report = new Report();

    public void assertStep(String actualValue, String expectedValue, String description){
        if(actualValue == null && expectedValue == null){
            report.pass("", "", description);
        } else {
            if (actualValue.equalsIgnoreCase(expectedValue)){
                report.pass(actualValue, expectedValue, description);
            } else {
                report.fail(actualValue, expectedValue, description);
                Assert.assertEquals(actualValue, expectedValue, description);
            }
        }
    }

    public void assertStep(int actualValue, int expectedValue, String description){
        if(actualValue == expectedValue){
            report.pass(String.valueOf(actualValue), String.valueOf(expectedValue), description);
        } else {
            report.fail(String.valueOf(actualValue), String.valueOf(expectedValue), description);
            Assert.assertEquals(String.valueOf(actualValue), String.valueOf(expectedValue), description);
        }
    }

    public void assertStep(boolean actualValue, boolean expectedValue, String description){
        if(actualValue == expectedValue){
            report.pass(String.valueOf(actualValue), String.valueOf(expectedValue), description);
        } else {
            report.fail(String.valueOf(actualValue), String.valueOf(expectedValue), description);
            Assert.assertEquals(String.valueOf(actualValue), String.valueOf(expectedValue), description);
        }
    }

    //TODO -- work on list Comparision
    public void assertStep(List actualValue, List expectedValue, String description){
        if(actualValue.size() == expectedValue.size()) {
            if (actualValue.equals(expectedValue)) {
                report.pass(String.valueOf(actualValue), String.valueOf(expectedValue), description);
            } else {
                report.fail(String.valueOf(actualValue), String.valueOf(expectedValue), description);
                Assert.assertEquals(String.valueOf(actualValue), String.valueOf(expectedValue), description);
            }
        } else {
            LOG.warning("The count of actual & expected data doesn't Match!!!");
        }
    }



}
