package com.epam.pashkov;

import com.epam.pashkov.pageobject.*;
import org.testng.annotations.Test;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Yaroslav_Pashkov
 */
public class TestGmail extends BaseTest {
    @Test
    public void starredLetter(){
        LoginPageGmail loginPageGmail = new LoginPageGmail(setup);
        StartMailPageGmail startMailPageGmail = loginPageGmail.login(login1, password1);
        LetterPageGmail letterPageGmail = startMailPageGmail.openNewLetterPage();
        letterPageGmail.createLetter(THEME_OF_LETTER, login2, TEXT_OF_LETTER);
        letterPageGmail.sendLetter();
        letterPageGmail.logout();

        loginPageGmail = new LoginPageGmail(setup);
        startMailPageGmail = loginPageGmail.loginAfterAnotherUser(login2, password2);
        startMailPageGmail.dragAndDropLatestLetterToStarred();
        StarredPage starredPage = startMailPageGmail.goToStarredPage();
        assertThat(starredPage.getLatestMessageTheme(), is(THEME_OF_LETTER));
    }

    @Test
    public void letterWithAttachments() throws AWTException {
        LoginPageGmail loginPageGmail = new LoginPageGmail(setup);
        StartMailPageGmail startMailPageGmail = loginPageGmail.login(login1, password1);
        LetterPageGmail letterPageGmail = startMailPageGmail.openNewLetterPage();
        letterPageGmail.createLetter(THEME_OF_LETTER, login2, TEXT_OF_LETTER);
        letterPageGmail.clickAttachFile();
        letterPageGmail.sendLetter();
        letterPageGmail.logout();

        loginPageGmail = new LoginPageGmail(setup);
        startMailPageGmail = loginPageGmail.loginAfterAnotherUser(login2, password2);
        letterPageGmail = startMailPageGmail.openLatestLetter();
        assertThat(letterPageGmail.getAttachedFileName(), containsString("SomeFile.txt"));
    }

    @Test
    public void selectAnotherTheme(){
        LoginPageGmail loginPageGmail = new LoginPageGmail(setup);
        StartMailPageGmail startMailPageGmail = loginPageGmail.login(login1, password1);
        ThemesPage themesPage = startMailPageGmail.goToThemesPage();
        int defaultThemeIndex = themesPage.getCurrentThemeIndex();
        themesPage.selectRandomTheme();
        int selectedThemeIndex = themesPage.getCurrentThemeIndex();
        assertThat(defaultThemeIndex, not(selectedThemeIndex));
    }

    @Test
    public void verifySpam(){
        LoginPageGmail loginPageGmail = new LoginPageGmail(setup);
        StartMailPageGmail startMailPageGmail = loginPageGmail.login(login1, password1);
        LetterPageGmail letterPageGmail = startMailPageGmail.openNewLetterPage();
        letterPageGmail.createLetter(THEME_OF_LETTER, login2, TEXT_OF_LETTER);
        letterPageGmail.sendLetter();
        letterPageGmail.logout();

        loginPageGmail = new LoginPageGmail(setup);
        startMailPageGmail = loginPageGmail.loginAfterAnotherUser(login2, password2);
        startMailPageGmail.markLetterAsSpam(startMailPageGmail.getCheckboxLatestLetter());
        startMailPageGmail.logout();

        startMailPageGmail = loginPageGmail.loginAfterAnotherUser(login1, password1);
        letterPageGmail = startMailPageGmail.openNewLetterPage();
        letterPageGmail.createLetter(THEME_OF_LETTER, login2, TEXT_OF_LETTER);
        letterPageGmail.sendLetter();
        letterPageGmail.logout();

        startMailPageGmail = loginPageGmail.loginAfterAnotherUser(login2, password2);
        SpamPage spamPage = startMailPageGmail.goToSpamFolder();
        assertThat(spamPage.getLatestMessageTheme(), is(THEME_OF_LETTER));
    }
}
