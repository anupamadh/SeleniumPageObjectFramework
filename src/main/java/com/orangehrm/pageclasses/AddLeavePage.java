package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.orangehrm.utilities.Util.sleep;


public class AddLeavePage extends BasePage {
    public AddLeavePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }
    public WebDriver driver;
    private JavascriptExecutor js;
    private String EMPLOYEE_FIELD = "css=>#entitlements_employee_empName";
    private String LEAVE_TYPE_FIELD = "css=>#entitlements_leave_type";
    private String LEAVE_PERIOD = "css=>#period";
    private String ENTITLEMENT_FIELD = "css=>#entitlements_entitlement";
    private String SAVE_BUTTON = "css=>#btnSave";
    private String ADD_ENTITLEMENT_POPUP = "id=>employeeEntitlement";
    private String POPUP_CONFIRM = "id=>dialogUpdateEntitlementConfirmBtn";

    public void AddEntitlement(){
        WebElement employee = getElement(EMPLOYEE_FIELD, "Employee Field");
        sendData(employee,"Russel" , "Employee Field" );
        sleep(2000, "Sleep for 2 seconds");
        sendData(employee,"Russel" + Keys.ARROW_DOWN + Keys.ENTER, "Employee Field" );
        WebElement leaveType = getElement(LEAVE_TYPE_FIELD, "Leave Type Field");
        selectOption(leaveType, "FMLA US");
        WebElement leavePeriod = getElement(LEAVE_PERIOD, "Leave Period");
        selectOption(leavePeriod, "2021-01-01 - 2021-12-31");
        sendData(ENTITLEMENT_FIELD,"20", "Entitlement Field");
        elementClick(SAVE_BUTTON, "Save Button");
        boolean popupPresent = isElementPresent(ADD_ENTITLEMENT_POPUP, "Add Entitlement Popup");
        if (popupPresent)
            elementClick(POPUP_CONFIRM, "Popup Confirm Button");
    }
}
