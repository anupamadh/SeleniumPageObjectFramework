package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /***
     * Variables
     * Locators
     * URL
     */
    public WebDriver driver;
    private String USERNAME_FIELD = "id=>txtUsername";
    private String PASSWORD_FIELD = "id=>txtPassword";
    private String LOG_IN_BUTTON = "name=>Submit";

    /***
     * Methods
     */

    public NavigationPage signInWith(String username, String password) {
        sendData(USERNAME_FIELD, username, "Email Field");
        sendData(PASSWORD_FIELD, password, "Password Field");
        elementClick(LOG_IN_BUTTON, "Login Button");
        return new NavigationPage(driver);
    }
}
