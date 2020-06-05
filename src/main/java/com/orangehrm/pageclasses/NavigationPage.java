package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import com.orangehrm.utilities.Util;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
    private String WELCOME_ICON = "xpath=>//a[contains(text(), 'Welcome')]";
    private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
    private String LEAVE_LINK = "id=>menu_leave_viewLeaveModule";
    private String ENTITLEMENTS_LINK = "xpath=>//a[@id='menu_leave_Entitlements']";
    private String ADD_ENITLEMENTS_LINK = "xpath=>//a[contains(text(),'Add Entitlements')]";

    public boolean isUserLoggedIn() {
        try {
            List<WebElement> accountImage = getElementList(WELCOME_ICON, "Account Icon");
            return Util.verifyListNotEmpty(accountImage);
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        elementClick(WELCOME_ICON, "User Account Icon");
        WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logoutLink, "Logout Link");
    }

    public boolean verifyHeader() {
        WebElement link = getElement(LEAVE_LINK, "Leave Link");
        return Util.verifyTextContains(link.getText(), "Leave");
    }

    public AddLeavePage getAddEntitlements() {
        WebElement link = getElement(LEAVE_LINK, "Leave Link");
        elementClick(link, "Leave Link");
        mouseHover(ENTITLEMENTS_LINK, "Entitlements Link");
        WebElement addEntitlements = getElement(ADD_ENITLEMENTS_LINK, "Add Entitlements Link");
        elementClick(addEntitlements, "Add Entitlements Link");
        return new AddLeavePage(driver);
    }


}
