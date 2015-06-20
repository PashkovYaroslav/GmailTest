package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yaroslav_Pashkov
 */
public class StarredPage extends AbstractPage {
    private static final String LATEST_MESSAGE = "(//*[@role='main']//tr)[1]/td[6]//b";

	@FindBy(xpath = LATEST_MESSAGE)
	private WebElement latestMessage;

    public WebElement getLatestMessage() {
        return latestMessage;
    }

    public String getLatestMessageTheme() {
        return latestMessage.getText();
    }

    public StarredPage(Setup setup) {
        super(setup);
    }
}
