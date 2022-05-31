package com.test.heroku.ObjectRepo;

import org.openqa.selenium.By;

public class DataTablesUIObject {
    public static final By TABLE1 = By.xpath("//table[@id='table1']");
    public static final By TABLE2 = By.xpath("//table[@id='table2']");
    public static final By TABLE1_HEADER = By.xpath("//*[@id='table1']/thead/tr/th");
    public static final By TABLE2_HEADER = By.xpath("//*[@id='table2']/thead/tr/th");
    public static final By TABLE1_ROWS = By.xpath("//*[@id='table1']/tbody/tr");
    public static final By TABLE2_ROWS = By.xpath("//*[@id='table2']/tbody/tr");

    public static final By DATA_TABLES_LABEL = By.xpath("//*[text()='Data Tables']");
    public static final By EXAMPLE1_LABEL = By.xpath("//*[text()='Example 1']");
    public static final By EXAMPLE2_LABEL = By.xpath("//*[text()='Example 2']");

    public static final By xpathBuilder(String elementName){
        String xpathOfElement = String.format("//*[text()='%s']", elementName);
        return By.xpath(xpathOfElement);
    }
    public static final By xpathBuilderForGridRowData(String tableName, int index){
        String table = tableName.toLowerCase();
        String xpathOfElement;
        if(tableName.equalsIgnoreCase("Table1")){
            xpathOfElement = String.format("//*[@id='%s']/tbody/tr[%s]/td", table, String.valueOf(index));
        } else {
            xpathOfElement = String.format("//*[@id='t%s']/tbody/tr[%s]/td", table, String.valueOf(index));
        }
        return By.xpath(xpathOfElement);
    }


}
