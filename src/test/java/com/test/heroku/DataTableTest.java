package com.test.heroku;

import com.test.heroku.ObjectRepo.HerokuMainPageUIObject;
import com.test.heroku.assertions.AssertMaster;
import com.test.heroku.elements.Operation;
import com.test.heroku.elements.UIElement;
import com.test.heroku.login.LoginToHeroku;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.GridReader;

import java.util.ArrayList;
import java.util.List;

import static com.test.heroku.ObjectRepo.DataTablesUIObject.*;

public class DataTableTest extends BaseTest{

    String filePath = new String("D:\\workspace\\SeleniumAutomationTesting\\src\\main\\resources\\TestDoc.xlsx");

    private final AssertMaster ASSERT = new AssertMaster();
    private final GridReader grid = new GridReader();
    private final ExcelReader excelReader = new ExcelReader(filePath);

    @Test
    public void dataTableLabelValidation(){
        LoginToHeroku.launchApp();

        By dataTableLink = HerokuMainPageUIObject.xpathBuilder("Sortable Data Tables");
        Operation.click(dataTableLink);

        ASSERT.assertStep(UIElement.getText(DATA_TABLES_LABEL), "Data Tables", "Assert DataTable label");
        ASSERT.assertStep(UIElement.getText(EXAMPLE1_LABEL), "Example 1", "Assert Example 1 label");
        ASSERT.assertStep(UIElement.getText(EXAMPLE2_LABEL), "Example 2", "Assert Example 2 label");
    }

    @Test
    public void table1HeaderValidation() throws InterruptedException {
        LoginToHeroku.launchApp();

        By dataTableLink = HerokuMainPageUIObject.xpathBuilder("Sortable Data Tables");
        Operation.click(dataTableLink);
        Thread.sleep(1000);

        List<String> header = new GridReader().gridHeader(TABLE1_HEADER);
        System.out.println(header);
    }

    @Test
    public void table1DataRowsValidation()  throws InterruptedException {
        LoginToHeroku.launchApp();

        By dataTableLink = HerokuMainPageUIObject.xpathBuilder("Sortable Data Tables");
        Operation.click(dataTableLink);
        Thread.sleep(1000);

        List<List<String>> tableDataText = grid.gridBodyData(TABLE1_ROWS, "Table1");
        System.out.println(tableDataText);
    }

    @Test
    public void table1FullDataValidation() throws InterruptedException {
        LoginToHeroku.launchApp();

        By dataTableLink = HerokuMainPageUIObject.xpathBuilder("Sortable Data Tables");
        Operation.click(dataTableLink);
        Thread.sleep(1000);

        List<List<String>> tableDataText = grid.gridData(TABLE1_HEADER, TABLE1_ROWS, "Table1");
        List<List<String>> excelDataText = excelReader.excelRead("DataTable1");

        System.out.println(tableDataText);
        System.out.println(excelDataText);

        ASSERT.assertStep(tableDataText, excelDataText, "Assert the Lists");
    }
}
