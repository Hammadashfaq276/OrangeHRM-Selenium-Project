package Admin;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.Admin_DeleteUser;
import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;


public class Delete_User extends BaseClass {

    private static final Logger log = LogManager.getLogger(Delete_User.class);

    LoginPage loginPage;
    Admin_DeleteUser admindelete;

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
        log.info("Clicked on Login button.");

        loginPage.clickMenu("Admin");
        log.info("Navigated to Admin menu after login.");

        log.info("✅ Login Test completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void deleteUserTest() {
        log.info("Starting Delete User Test...");
        admindelete = new Admin_DeleteUser(BaseClass.driver);

        // delete 3rd record
        log.info("Attempting to delete 3rd user record...");
        admindelete.deleteThirdRecord();

        // verify toast
        loginPage.verifyToastMessage("Success Successfully Deleted");
        log.info("✅ User deleted successfully and toast message verified.");
    }

    @Test(priority = 3, dependsOnMethods = {"deleteUserTest"})
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

