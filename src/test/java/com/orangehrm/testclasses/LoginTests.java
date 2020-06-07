package com.orangehrm.testclasses;

import com.orangehrm.base.BaseTest;
import com.orangehrm.base.CheckPoint;
import com.orangehrm.utilities.Constants;
import com.orangehrm.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @DataProvider(name = "addLeaveEntitlementData")
    public Object[][] getaddLeaveEntitlementData(){
        Object[][] testData = ExcelUtility.getTestData("add_leave_entitlement");
        return testData;
    }

    @DataProvider(name = "assignLeaveData")
    public Object[][] getassignLeaveData(){
        Object[][] testData = ExcelUtility.getTestData("assign_leave");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "AllTests");
    }

    @AfterClass
    public void afterMethod() {
        System.out.println("****** After Method ******");
        if (nav.isUserLoggedIn()) {
            nav.logout();
       //     nav.login();
        }
    }


    @Test(priority = 1)
    public void testLogin() {
        boolean headerResult = nav.verifyHeader();
        CheckPoint.mark("test-01", headerResult, "header verification");
        boolean result = nav.isUserLoggedIn();
        CheckPoint.markFinal("test-01", result, "login verification");
    }

    @Test(priority = 2, dataProvider = "addLeaveEntitlementData")
    public void testAddLeave(String employeeName, String employeeLeaveType, String employeeLeavePeriod, String employeeEntitlement){
        addLv = nav.getAddEntitlements();
        viewLv = addLv.AddEntitlement(employeeName, employeeLeaveType, employeeLeavePeriod, employeeEntitlement);
        boolean entitlementResult = viewLv.verifyAddLeaveEntitlement();
        CheckPoint.markFinal("test-add-entitlement", entitlementResult, "Add Leave Entitlement Verification");
    }

    @Test(priority = 3, dataProvider = "assignLeaveData")
    public void testAssignLeave(String employeeName, String employeeLeaveType,String fromDateField, String toDateField){
        assignLv = nav.getAssignLeave();
        assignLv.AssignLeave(employeeName, employeeLeaveType, fromDateField, toDateField);
        boolean assignResult = assignLv.verifyAssignLeave();
        CheckPoint.markFinal("testAssignLeave", assignResult, "Assign Leave Verification");
    }
    @Test(enabled = false)
    public void testInvalidLogin() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean result = nav.isUserLoggedIn();
        Assert.assertFalse(result);
    }
}
