package com.orangehrm.pageclasses;

import com.orangehrm.base.BasePage;
import com.orangehrm.utilities.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.orangehrm.utilities.Util.sleep;

public class AssignLeavePage extends BasePage {
    public AssignLeavePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }
    public WebDriver driver;
    private JavascriptExecutor js;
    public String EMPLOYEE_NAME_FIELD = "id=>assignleave_txtEmployee_empName";
    private String LEAVE_TYPE_FIELD = "id=>assignleave_txtLeaveType";
    private String FROM_DATE_FIELD = "id=>assignleave_txtFromDate";
    private String TO_DATE_FIELD = "id=>assignleave_txtToDate";
    private String ASSIGN_BUTTON = "id=>assignBtn";
    private String EMPLOYEE_LIST = "xpath=>//div[@class='ac_results']";
    private String LEAVE_ASSIGN_MESSAGE = "css=>.message.success.fadable";
    private String URL = "assignLeave";





    public void AssignLeave(String employeeName, String employeeLeaveType,String fromDateField, String toDateField){
        sleep(2000, "Sleep for 2 seconds");
        WebElement textField = getElement(EMPLOYEE_NAME_FIELD, "Employee Field");
        sendData(EMPLOYEE_NAME_FIELD, employeeName , "Employee Field" );
        WebElement ulElement = getElement(EMPLOYEE_LIST, "Employee Field");
        List<WebElement> liElements = ulElement.findElements(By.tagName("li"));

        for (WebElement element : liElements) {
            if (element.getText().contains(employeeName)) {
                System.out.println("Selected: " + element.getText());
                elementClick(element, "Element Selected");
                break;
            }
        }
        WebElement leaveType = getElement(LEAVE_TYPE_FIELD, "Leave Type Field");
        selectOption(leaveType, employeeLeaveType);

        elementClick(FROM_DATE_FIELD, "Click on From Date Field");
        sendData(FROM_DATE_FIELD, fromDateField,"Leave From Date");
        elementClick(FROM_DATE_FIELD, "Click on From Date Field");

        waitForElementToBeClickable(TO_DATE_FIELD, 5);
        elementClick(TO_DATE_FIELD, "Click on To Date Field");
        sendData(TO_DATE_FIELD, toDateField,"Leave To Date");
        elementClick(TO_DATE_FIELD, "Click on To Field");
        waitForElementToBeClickable(ASSIGN_BUTTON, 5);

        WebElement assignBtn = getElement(ASSIGN_BUTTON, "Get Assign Button");
        javascriptClick(assignBtn, "Click on Assign Button");
    }

    public boolean isOpen() {
        return getURL().contains(URL);
    }

    public boolean verifyAssignLeave() {
        boolean result = false;

        WebElement elementPresent = isElementPresent(LEAVE_ASSIGN_MESSAGE, 5);

        Boolean textPresent = Util.verifyTextContains(elementPresent.getText(), "Successfully Assigned");
        if (textPresent) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }


}
