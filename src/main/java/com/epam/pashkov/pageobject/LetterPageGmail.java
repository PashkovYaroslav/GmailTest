package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by Yaroslav_Pashkov
 */
public class LetterPageGmail extends AbstractPage {

    public static final String INBOX_BUTTON = "//a[contains(text(),'Входящие')]";
    public static final String TITLE_INPUT = "//input[@name='subjectbox']";
    public static final String RECIPIENT_INPUT = "//textarea[@aria-label='Кому']";
    public static final String RECIPIENT_LIST = "//form//span[@email]/div[1]";
    public static final String TEXT_INPUT = "//div[@aria-label='Тело письма']";
    public static final String SAVE_LETTER_TO_DRAFT_BUTTON = "//img[@alt='Закрыть']";
    public static final String SEND_LETTER_BUTTON = "//div[text()='Отправить']";
    public static final String RECIPIENT_UI = "//div[@tabindex='1']//span[@email]";
    public static final String SUBJECT_UI = "//input[@name='subjectbox']";
    public static final String INBOX_PAGE_TITLE = "Входящие";
    public static final String VALUE = "value";
    public static final String INNER_HTML = "innerHTML";
    public static final String ATTACH_FILE = "//div[@data-tooltip='Прикрепить файлы']";
    public static final String FILE = "//span[contains(text(), 'Предварительный просмотр файла')]";

    @FindBy(xpath = TITLE_INPUT)
    private WebElement titleInput;

    @FindBy(xpath = RECIPIENT_INPUT)
    private WebElement recipientInput;

    @FindBy(xpath = RECIPIENT_LIST)
    private WebElement recipientList;

    @FindBy(xpath = TEXT_INPUT)
    private WebElement textInput;

    @FindBy(xpath = SAVE_LETTER_TO_DRAFT_BUTTON)
    private WebElement saveLetterToDraftButton;

    @FindBy(xpath = SEND_LETTER_BUTTON)
    private WebElement sendLetterButton;

    @FindBy(xpath = INBOX_BUTTON)
    private WebElement inboxButton;

    @FindBy(xpath = RECIPIENT_UI)
    private WebElement recipientUi;

    @FindBy(xpath = SUBJECT_UI)
    private WebElement subjectUi;

    @FindBy(xpath = ATTACH_FILE)
    private WebElement attachFile;

    @FindBy(xpath = FILE)
    private WebElement file;

    public String getTitle() {
        return titleInput.getAttribute(VALUE);
    }

    public String getRecipient() {
        return recipientList.getAttribute(INNER_HTML);
    }

    public String getLetterText() {
        return textInput.getText();
    }

    public LetterPageGmail(Setup setup) {
        super(setup);
    }

    public void createLetter(String title, String recipient, String text) {
        recipientInput.sendKeys(recipient);
        titleInput.sendKeys(title);
        textInput.sendKeys(text);
    }

    public StartMailPageGmail sendLetter() {
        sendLetterButton.click();
        wait.delay(3000);
        inboxButton.click();
        wait.waitTitleContains(setup, INBOX_PAGE_TITLE);
        return new StartMailPageGmail(setup);
    }

    public String getAttachedFileName(){
        return file.getAttribute("innerHTML");
    }

    public void clickAttachFile() throws AWTException{
        attachFile.click();
        StringSelection ss = new StringSelection("C:\\SomeFile.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait.delay(5000);
    }
}
