package Admin;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.Admin_SearchUser;
import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;

public class Search_User extends BaseClass {

    private static final Logger log = LogManager.getLogger(Search_User.class);

    LoginPage loginPage;
    Admin_SearchUser adminsearchuser;

    @BeforeClass
    public void setUp() {
        log.info("=== Test Setup Started ===");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and navigated to the application URL.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("Starting Login Test...");
        log.info("Page Title: {}", loginPage.getPageTitle());

        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");

        loginPage.enterPassword("admin123");
        log.info("Entered password.");

        loginPage.clickLogin();
        log.info("Clicked Login button successfully.");

        loginPage.clickMenu("Admin");
        log.info("Navigated to Admin section after login.");

        log.info("✅ Login Test completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void searchUserTest() {
        log.info("Starting Search User Test...");
        adminsearchuser = new Admin_SearchUser(BaseClass.driver);

        log.info("Entering search criteria...");
        adminsearchuser.enterUsername("Hammad123");
        adminsearchuser.selectUserRole("Admin");
        adminsearchuser.selectAutoComplete("ha", "Russel Hamilton");
        adminsearchuser.selectStatus("Enabled");
        adminsearchuser.clickSearch();
        log.info("Search initiated with provided filters.");

        // Validate record found
        adminsearchuser.getRecordText("(1) Record Found");
        log.info("✅ Search result verified successfully.");
    }

    @Test(priority = 3, dependsOnMethods = {"searchUserTest"})
    public void logoutTest() {
        log.info("Starting Logout Test...");
        loginPage.logout();
        log.info("✅ Logged out successfully.");
    }

    @AfterClass
    public void TearDown() {
        log.info("Tearing down browser session...");
        BaseClass.tearDown();
        log.info("=== Test Execution Completed ===");
    }
}


