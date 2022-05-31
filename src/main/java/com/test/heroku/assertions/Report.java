package com.test.heroku.assertions;

import java.util.logging.Logger;

public class Report {
    private final Logger LOG = Logger.getLogger("Report");
    private final String SEPERATOR = " | ";

    public enum Status{
        PASS, FAIL
    }

    public void pass(String actual, String expected, String description){
        report(actual, expected, description, Status.PASS);
    }

    public void fail(String actual, String expected, String description){
        report(actual, expected, description, Status.FAIL);
    }


    private void report(String actual, String expected, String description, Status status){
        if(status.equals(Status.PASS)){
            LOG.info(description + SEPERATOR + Status.PASS + SEPERATOR + expected + SEPERATOR + actual);
        } else if (status.equals(Status.FAIL)){
            LOG.warning(description + SEPERATOR + Status.FAIL + SEPERATOR + expected + SEPERATOR + actual);
        }
    }
}
