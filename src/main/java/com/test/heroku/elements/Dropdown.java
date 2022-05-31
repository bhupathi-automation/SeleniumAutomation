package com.test.heroku.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Dropdown {

    public static Select selectObject(By element){
        Select select = new Select(UIElement.findElement(element));
        return select;
    }

    public static void selectByValue(By element, String value){
        selectObject(element).selectByValue(value);
    }

    public static void selectByText(By element, String text){
        selectObject(element).selectByVisibleText(text);
    }

    public static void selectByIndex(By element, int i){
        selectObject(element).selectByIndex(i);
    }

    public static String getSelectedItem(By element){
        String selectedItem = selectObject(element).getFirstSelectedOption().getText();
        return selectedItem;
    }

    public static List<String> getAllOptions(By element){
        WebElement dropdown = UIElement.findElement(element);
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        List<String> values = new ArrayList<String>();

        for(WebElement option : options){
            values.add(option.getText().trim());
        }
        return values;
    }

    public static void multiSelect(By element, List<String> values){
        Select select = selectObject(element);

        for(String value : values){
            select.selectByVisibleText(value);
        }
    }

}
