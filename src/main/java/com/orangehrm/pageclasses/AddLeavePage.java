package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.*;
import java.util.List;

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
    private String EMPLOYEE_LIST = "xpath=>//div[@class='ac_results']";


    public ViewLeavePage AddEntitlement(String employeeName, String employeeLeaveType, String employeeLeavePeriod, String employeeEntitlement ){
        sleep(2000, "Sleep for 2 seconds");
        WebElement textField = getElement(EMPLOYEE_FIELD, "Employee Field");
        sendData(EMPLOYEE_FIELD, employeeName , "Employee Field" );
        WebElement ulElement = getElement(EMPLOYEE_LIST, "Employee Field");
        List<WebElement> liElements = ulElement.findElements(By.tagName("li"));

        for (WebElement element : liElements) {
            if (element.getText().contains(employeeName)) {
                System.out.println("Selected: " + element.getText());
                elementClick(element, "Element Selected");
                break;
            }
        }
        sleep(2000, "Sleep for 2 seconds");
        WebElement leaveType = getElement(LEAVE_TYPE_FIELD, "Leave Type Field");
        selectOption(leaveType, employeeLeaveType);
        WebElement leavePeriod = getElement(LEAVE_PERIOD, "Leave Period");
        selectOption(leavePeriod, employeeLeavePeriod);
        sendData(ENTITLEMENT_FIELD,employeeEntitlement, "Entitlement Field");
        elementClick(SAVE_BUTTON, "Save Button");
        boolean popupPresent = isElementPresent(ADD_ENTITLEMENT_POPUP, "Add Entitlement Popup");
        sleep(5000, "Sleep for 5 seconds");
        if (popupPresent)
            elementClick(POPUP_CONFIRM, "Popup Confirm Button");
        sleep(5000, "Sleep for 5 seconds");
        return new ViewLeavePage(driver);
    }
}
