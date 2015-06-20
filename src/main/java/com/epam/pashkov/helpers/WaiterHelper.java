package com.epam.pashkov.helpers;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Yaroslav_Pashkov
 */
public class WaiterHelper {
    private final long TIME_OUT = Long.parseLong(ResourceBundle.getBundle("config").getString("driver.time_out"));

    public void waitVisibilityOf(Setup setup, WebElement webElement){
        getWaitDriver(setup).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitVisibilityOfAll(Setup setup, List<WebElement> webElements){
        getWaitDriver(setup).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    private WebDriverWait getWaitDriver(Setup setup) {
        return new WebDriverWait(setup.getDriver(), TIME_OUT);
    }

    public void waitTitleContains(Setup setup, String title){
        getWaitDriver(setup).until(ExpectedConditions.titleContains(title));
    }

    public static void delay(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
