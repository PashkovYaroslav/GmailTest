package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

/**
 * Created by Yaroslav_Pashkov
 */
public class ThemesPage extends AbstractPage {
    private int currentThemeIndex;

    public static final String OPTIONS_TITLE = "//h2[text()='Настройки']";
    public static final String THEMES_LIST = "//div[@class='J-J5-Ji sj']";
    public static final String THEMES_LIST_SELECTOR = "//div[@class='J-J5-Ji sj']/div/div";

	@FindBy(xpath = OPTIONS_TITLE)
	private WebElement optionsTitle;

    @FindBy(xpath = THEMES_LIST)
    private List<WebElement> themesList;

    @FindBy(xpath = THEMES_LIST_SELECTOR)
    private List<WebElement> themesListSelector;

    public ThemesPage(Setup setup) {
        super(setup);
    }

    public int getCurrentThemeIndex(){
        for(int i = 0; i < themesListSelector.size(); i++) {
            if (!themesListSelector.get(i).getAttribute("class").equals("si")){
                currentThemeIndex = i;
                break;
            }
        }
        return currentThemeIndex;
    }

    public void selectRandomTheme(){
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(themesList.size());
        while(randomIndex == getCurrentThemeIndex()) {
            randomIndex = rnd.nextInt(themesList.size());
        }
        themesList.get(randomIndex).click();
        wait.delay(6000);
    }
}
