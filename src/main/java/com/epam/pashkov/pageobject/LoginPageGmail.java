package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

/**
 * Created by Yaroslav_Pashkov
 */
public class LoginPageGmail extends AbstractPage {

    public static final String USER_NAME_LOCATOR = ".//input[@id='Email']";
    public static final String NEXT_BUTTON = ".//input[@id='next']";
    public static final String PASSWORD_LOCATOR = ".//*[@id='Passwd']";
    public static final String LOGIN_BUTTON_LOCATOR = ".//*[@id='signIn']";
    public static final String CURRENT_ACCOUNT_TEXT = ".//*[@id='gb']//a[contains(@class,'gb_ga') and contains(@title,'Аккаунт')]";
    public static final String LOGIN_AFTER_ANOTHER_USER_LINK = "//a[@id='account-chooser-link']";
    public static final String ADD_ACCOUNT = "//a[@id='account-chooser-add-account']";

    @FindBy(xpath = USER_NAME_LOCATOR)
    private WebElement userNameLocator;

    @FindBy(xpath = CURRENT_ACCOUNT_TEXT)
    private WebElement currentAccountText;

    @FindBy(xpath = NEXT_BUTTON)
    private WebElement nextButton;

    @FindBy(xpath = PASSWORD_LOCATOR)
    private WebElement passwordLocator;

    @FindBy(xpath = LOGIN_BUTTON_LOCATOR)
    private WebElement loginButtonLocator;

    @FindBy(xpath = LOGIN_AFTER_ANOTHER_USER_LINK)
    private WebElement loginAfterAnotherUserLink;

    @FindBy(xpath = ADD_ACCOUNT)
    private WebElement addAccount;

    public LoginPageGmail(Setup setup) {
        super(setup);
        setup.getDriver().get(ResourceBundle.getBundle("config").getString("homepage.url"));
    }

    public StartMailPageGmail login(String userName, String password) {
        userNameLocator.sendKeys(userName);
        nextButton.click();
        passwordLocator.sendKeys(password);
        loginButtonLocator.click();
        return new StartMailPageGmail(setup);
    }

    public StartMailPageGmail loginAfterAnotherUser(String userName, String password) {
        try {
            loginAfterAnotherUserLink.click();
            setup.getDriver().switchTo().alert().accept();
        } finally {
            wait.waitVisibilityOf(setup, addAccount);
            addAccount.click();
            userNameLocator.sendKeys(userName);
            nextButton.click();
            passwordLocator.sendKeys(password);
            loginButtonLocator.click();
            return new StartMailPageGmail(setup);
        }
    }


}
