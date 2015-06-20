package com.epam.pashkov;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ResourceBundle;

/**
 * Created by Yaroslav_Pashkov
 */
public class BaseTest {
    protected Setup setup;
    protected WebDriver driver;
    protected ResourceBundle config;

    public static final String THEME_OF_LETTER = "Hello login2!";
    public static final String TEXT_OF_LETTER = "Some text here...";
    public String login1;
    public String password1;
    public String login2;
    public String password2;

    @BeforeMethod
    public void preconditions(){
        config = ResourceBundle.getBundle("config");
        setup = new Setup(config.getString("driver"), Integer.valueOf(config.getString("driver.time_out")));
        login1 = config.getString("gmail.com.login1");
        password1 = config.getString("gmail.com.password1");
        login2 = config.getString("gmail.com.login2");
        password2 = config.getString("gmail.com.password2");
    }

    @AfterMethod
    public void postconditions(){
        setup.getDriver().quit();
    }
}
