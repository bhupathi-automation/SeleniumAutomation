package utils;

import com.test.heroku.ObjectRepo.DataTablesUIObject;
import com.test.heroku.elements.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.test.heroku.ObjectRepo.DataTablesUIObject.TABLE1_HEADER;
import static com.test.heroku.ObjectRepo.DataTablesUIObject.TABLE1_ROWS;

public class GridReader {

    public List<String> gridHeader(By headerObject){
        List<WebElement> headerElements;
        List<String> headerText = new ArrayList<String>();

        headerElements= UIElement.findElements(headerObject);

        for(WebElement element : headerElements){
            headerText.add(UIElement.getText(element));
        }
        return headerText;
    }

    public List<List<String>> gridBodyData(By dataRows, String table){
        List<List<String>> tableDataText = new ArrayList<List<String>>();

        int rowCount = UIElement.findElements(dataRows).size();

        for(int i=0; i<rowCount; i++){
            tableDataText.add(i, new ArrayList<String>());
            List<WebElement> rowDataElements;

            if(table.equalsIgnoreCase("Table1")){
                rowDataElements = UIElement.findElements(DataTablesUIObject.xpathBuilderForGridRowData("Table1", i+1));
            } else {
                rowDataElements = UIElement.findElements(DataTablesUIObject.xpathBuilderForGridRowData("Table2", i+1));
            }

            for(WebElement element : rowDataElements){
                tableDataText.get(i).add(element.getText());
            }
        }
        return tableDataText;
    }

    public List<List<String>> gridData(By headerObj, By dataRows, String table){
        List<String> header;
        List<List<String>> tableDataText;

        header = gridHeader(headerObj);
        tableDataText = gridBodyData(dataRows, "Table1");
        tableDataText.add(0, header);

        return tableDataText;
    }


}
