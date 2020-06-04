package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import com.orangehrm.utilities.Util;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPage extends BasePage {
    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public WebDriver driver;
    private JavascriptExecutor js;
    private final String URL = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
    private String ACCOUNT_ICON = "xpath=>//a[contains(text(), 'Welcome')]";
    private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
    private String LEAVE_LINK = "id=>menu_leave_viewLeaveModule";

    public boolean isUserLoggedIn() {
        try {
            List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
            return Util.verifyListNotEmpty(accountImage);
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        elementClick(ACCOUNT_ICON, "User Account Icon");
        WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logoutLink, "Logout Link");
    }

    public boolean verifyHeader() {
        WebElement link = getElement(LEAVE_LINK, "Leave Link");
        return Util.verifyTextContains(link.getText(), "Leave");
    }

}
