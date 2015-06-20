package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import com.epam.pashkov.helpers.WaiterHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yaroslav_Pashkov
 */
public abstract class AbstractPage {
    protected Setup setup;
    protected WaiterHelper wait = new WaiterHelper();

    public static final String CURRENT_ACCOUNT_TEXT = ".//*[@id='gb']//a[contains(@class,'gb_ga') and contains(@title,'Аккаунт')]";
    public static final String LOGOUT_BUTTON_LOCATOR = "//a[contains(text(),'Выйти')]";

    @FindBy(xpath = CURRENT_ACCOUNT_TEXT)
    private WebElement currentAccountText;

    @FindBy(xpath = LOGOUT_BUTTON_LOCATOR)
    private WebElement logoutButtonLocator;

    public AbstractPage(Setup setup) {
        this.setup = setup;
        PageFactory.initElements(setup.getDriver(), this);
    }

    public void logout() {
        currentAccountText.click();
        wait.waitVisibilityOf(setup, logoutButtonLocator);
        logoutButtonLocator.click();
        wait.delay(3000);
    }
}
