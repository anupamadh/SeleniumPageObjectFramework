package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.swing.text.View;

public class ViewLeavePage extends BasePage {
    public ViewLeavePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }
    public WebDriver driver;
    private JavascriptExecutor js;
    private String TOTAL_TEXT_DISPLAYED = "css=>tr[class='total'] td:nth-child(2)";
    private String URL = "viewLeaveEntitlements";

    public boolean isOpen() {
        return getURL().contains(URL);
    }

    public boolean isTotalDisplayed(){
        return isDisplayed(TOTAL_TEXT_DISPLAYED, "Total displayed");
    }

    public boolean verifyAddLeaveEntitlement() {
        boolean result = false;
        if (isTotalDisplayed()) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }
}
