package com.test.heroku;

import com.test.heroku.ObjectRepo.HerokuMainPageUIObject;
import com.test.heroku.assertions.AssertElement;
import com.test.heroku.assertions.AssertMaster;
import com.test.heroku.elements.*;
import com.test.heroku.login.LoginToHeroku;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.List;

import static com.test.heroku.ObjectRepo.HerokuMainPageUIObject.*;

public class BasicAutomationTest extends BaseTest{
    private final AssertMaster ASSERT = new AssertMaster();

    @Test
    public void verifyDropdownValues(){
        LoginToHeroku.launchApp();

        By dropdownLink = HerokuMainPageUIObject.xpathBuilder("Dropdown");
        Operation.click(dropdownLink);
        boolean dropdownStatus = AssertElement.isElementEnabled(DROPDOWN_OPTION);

        ASSERT.assertStep(UIElement.getText(DROPDOWN_PAGE_LABEL), "Dropdown List", "Assert Dropdown label");
        ASSERT.assertStep(dropdownStatus, true, "Assert Dropdown enabled status");

        List<String> dropdownValues = Dropdown.getAllOptions(DROPDOWN_OPTION);
        System.out.println("Available options under dropdown are: "+dropdownValues);
    }

    @Test
    public void verifyDropdownSelection(){
        LoginToHeroku.launchApp();

        By dropdownLink = HerokuMainPageUIObject.xpathBuilder("Dropdown");
        Operation.click(dropdownLink);

        Dropdown.selectByText(DROPDOWN_OPTION, "Option 1");
        System.out.println("First selected option is: "+Dropdown.getSelectedItem(DROPDOWN_OPTION));

        Dropdown.selectByValue(DROPDOWN_OPTION, "2");
        System.out.println("Second selected option is: "+Dropdown.getSelectedItem(DROPDOWN_OPTION));
    }

    @Test
    public void verifyCheckboxSelection(){
        LoginToHeroku.launchApp();

        By checkboxLink = HerokuMainPageUIObject.xpathBuilder("Checkboxes");
        Operation.click(checkboxLink);

        ASSERT.assertStep(UIElement.getText(CHECKBOXES_PAGE_LABEL), "Checkboxes", "Assert checkboxes label");

        ASSERT.assertStep(Checkbox.isSelected(CHECKBOX_ONE), false, "Assert Checkbox One is default selected");
        ASSERT.assertStep(Checkbox.isSelected(CHECKBOX_TWO), true, "Assert Checkbox One is default selected");

        Checkbox.selectCheckbox(CHECKBOX_ONE);
        Checkbox.unSelectCheckbox(CHECKBOX_TWO);

        ASSERT.assertStep(Checkbox.isSelected(CHECKBOX_ONE), true, "Assert Checkbox One is selected");
        ASSERT.assertStep(Checkbox.isSelected(CHECKBOX_TWO), false, "Assert Checkbox Two is selected");
    }

    @Test
    public void verifyKeyPressFromKeyboard(){
        LoginToHeroku.launchApp();

        By keyPressLink = HerokuMainPageUIObject.xpathBuilder("Key Presses");
        Operation.click(keyPressLink);

        ASSERT.assertStep(UIElement.getText(KEYPRESS_PAGE_LABEL), "Key Presses", "Assert Key Presses label");

        KeyPress.pressKey(KEYPRESS_TEXTBOX, Keys.TAB);
        ASSERT.assertStep(UIElement.getText(KEYPRESS_RESULT), "You entered: TAB", "Assert the pressed Key");

        KeyPress.pressKey(KEYPRESS_TEXTBOX, Keys.ALT);
        ASSERT.assertStep(UIElement.getText(KEYPRESS_RESULT), "You entered: ALT", "Assert the pressed Key");

        KeyPress.pressKey(KEYPRESS_TEXTBOX, Keys.SHIFT);
        ASSERT.assertStep(UIElement.getText(KEYPRESS_RESULT), "You entered: SHIFT", "Assert the pressed Key");
    }

}
