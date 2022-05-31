package com.test.heroku;

import com.test.heroku.ObjectRepo.HerokuMainPageUIObject;
import com.test.heroku.assertions.AssertMaster;
import com.test.heroku.elements.Action;
import com.test.heroku.elements.Operation;
import com.test.heroku.elements.UIElement;
import com.test.heroku.login.LoginToHeroku;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.test.heroku.ObjectRepo.HerokuMainPageUIObject.*;

public class ActionsTest extends BaseTest{
    private final AssertMaster ASSERT = new AssertMaster();

    @Test
    public void verifyMouseHover(){
        LoginToHeroku.launchApp();

        By hoversLink = HerokuMainPageUIObject.xpathBuilder("Hovers");
        Operation.click(hoversLink);

        ASSERT.assertStep(UIElement.getText(HOVERS_PAGE_LABEL), "Hovers", "Assert Hovers label");

        Action.moveTo(HOVERS_IMAGE1);
        Operation.click(HOVERS_IMAGE1_LINK);

        ASSERT.assertStep(UIElement.getText(NOT_FOUND_TEXT), "Not Found", "Assert Not Found label");
    }
}
