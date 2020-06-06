package com.orangehrm.base;

import com.orangehrm.pageclasses.AddLeavePage;
import com.orangehrm.pageclasses.LoginPage;
import com.orangehrm.pageclasses.NavigationPage;
import com.orangehrm.pageclasses.ViewLeavePage;
import com.orangehrm.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    public WebDriver driver;
    protected String baseURL;
    protected LoginPage login;
    protected NavigationPage nav;
    protected AddLeavePage addLv;
    protected ViewLeavePage viewLv;
 /*   protected SearchBarPage search;
    protected ResultsPage result;
    protected CategoryFilterPage category;*/

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
  //      nav = new NavigationPage(driver);
        login = new LoginPage(driver);
    }

    @BeforeMethod
    public void methodSetUp() {
        CheckPoint.clearHashMap();
    }

    @AfterClass
    public void commonTearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}
