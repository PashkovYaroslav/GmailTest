package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yaroslav_Pashkov
 */
public class StartMailPageGmail extends AbstractPage {

    public static final String DRAFT_BUTTON = "//a[contains(text(),'Черновики')]";
    public static final String SENT_BUTTON = "//a[contains(text(),'Отправленные')]";
    public static final String STARRED_BUTTON = "//a[contains(text(),'Помеченные')]";
    public static final String CURRENT_ACCOUNT_TEXT = ".//*[@id='gb']//a[contains(@class,'gb_ga') and contains(@title,'Аккаунт')]";
    public static final String WRITE_LETTER_BUTTON = "//div[text()='НАПИСАТЬ']";
    public static final String LOGOUT_BUTTON_LOCATOR = "//a[contains(text(),'Выйти')]";
    public static final String CHECKBOX_LATEST_LETTER = "(//div[@aria-checked][@role='checkbox']/div)[1]";
    public static final String SEND_TO_SPAM_BUTTON = "//div[@act='9']";
    public static final String SHOW_MORE_MENU_BUTTON = "(//span[text()='Ещё'])[1]";
    public static final String SPAM_BUTTON = "//a[text()='Спам']";
    public static final String SEARCH_INPUT_FIELD = "//input[@aria-label='Поиск']";
    public static final String SEARCH_BUTTON = "//button[@aria-label='Поиск Gmail']";
    public static final String LATEST_INBOX_LETTER = "(//*[@role='main']//tr)[1]/td[6]//b";
    public static final String SETTINGS_BUTTON = "//*[contains(@role,'button') and @title='Настройки']";
    public static final String THEMES_OPTION = "//div[text()='Темы']";

    @FindBy(xpath = DRAFT_BUTTON)
    private WebElement draftButton;

    @FindBy(xpath = SENT_BUTTON)
    private WebElement sentButton;

    @FindBy(xpath = CURRENT_ACCOUNT_TEXT)
    private WebElement currentAccountText;

    @FindBy(xpath = WRITE_LETTER_BUTTON)
    private WebElement writeLetterButton;

    @FindBy(xpath = LOGOUT_BUTTON_LOCATOR)
    private WebElement logoutButtonLocator;

    @FindBy(xpath = CHECKBOX_LATEST_LETTER)
    private WebElement checkboxLatestLetter;

    @FindBy(xpath = SEND_TO_SPAM_BUTTON)
    private WebElement sendToSpamButton;

    @FindBy(xpath = SHOW_MORE_MENU_BUTTON)
    private WebElement showMoreMenu;

    @FindBy(xpath = SPAM_BUTTON)
    private WebElement spamButton;

    @FindBy(xpath = SEARCH_INPUT_FIELD)
    private WebElement searchInputField;

    @FindBy(xpath = SEARCH_BUTTON)
    private WebElement searchButton;

    @FindBy(xpath = STARRED_BUTTON)
    private WebElement starredButton;

    @FindBy(xpath = LATEST_INBOX_LETTER)
    private WebElement latestInboxLetter;

	@FindBy(xpath = SETTINGS_BUTTON)
	private WebElement settingsButton;

    @FindBy(xpath = THEMES_OPTION)
    private WebElement themesOption;

    public WebElement getCheckboxLatestLetter() {
        return checkboxLatestLetter;
    }

    public StartMailPageGmail(Setup setup) {
        super(setup);
    }

    public LetterPageGmail openNewLetterPage() {
        writeLetterButton.click();
        wait.delay(3000);
        return new LetterPageGmail(setup);
    }

    public void markLetterAsSpam(WebElement letterCheckBox){
        letterCheckBox.click();
        wait.delay(6000);
        sendToSpamButton.click();
        wait.delay(6000);
    }

    public SpamPage goToSpamFolder(){
        searchInputField.sendKeys("in:spam");
        searchButton.click();
        wait.delay(5000);
        return new SpamPage(setup);
    }

    public void dragAndDropLatestLetterToStarred(){
        Actions actionDragAndDrop = new Actions(setup.getDriver());
        wait.waitVisibilityOf(setup, latestInboxLetter);
        wait.waitVisibilityOf(setup, starredButton);
        actionDragAndDrop.dragAndDrop(latestInboxLetter, starredButton).perform();
        wait.delay(5000);
    }

    public StarredPage goToStarredPage(){
        starredButton.click();
        return new StarredPage(setup);
    }

    public ThemesPage goToThemesPage(){
        wait.waitVisibilityOf(setup, settingsButton);
        settingsButton.click();
        wait.waitVisibilityOf(setup, themesOption);
        themesOption.click();
        return new ThemesPage(setup);
    }

    public LetterPageGmail openLatestLetter() {
        wait.waitVisibilityOf(setup, latestInboxLetter);
        latestInboxLetter.click();
        return new LetterPageGmail(setup);
    }
}
