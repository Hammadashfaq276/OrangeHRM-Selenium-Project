package Admin;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.Admin_Edit_User;
import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Edit_User extends BaseClass {

    private static final Logger log = LogManager.getLogger(Edit_User.class);

    LoginPage loginPage;
    Admin_Edit_User adminedit;

    @BeforeClass
    public void setUp() {
        log.info("=== Test Setup Started ===");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched, URL opened, and LoginPage initialized.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("=== Starting Login Test ===");
        log.info("Page Title: {}", loginPage.getPageTitle());
        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");
        loginPage.enterPassword("admin123");
        log.info("Entered password.");
        loginPage.clickLogin();
        log.info("Clicked Login button.");
        loginPage.clickMenu("Admin");
        log.info("Clicked on 'Admin' menu.");
        log.info("✅ Login Test completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void editUserTest() {
        log.info("=== Starting Edit User Test ===");
        adminedit = new Admin_Edit_User(BaseClass.driver);

        log.info("Editing user: Role=ESS, Employee=James A Simon, Status=Disabled, Username=Hammad137");
        adminedit.editUser("ESS", "ha", "Russel Hamilton", "Disabled", "Hammad137", "Hammad@100");

        loginPage.verifyToastMessage("Success Successfully Updated");
        log.info("✅ User edited successfully and verified success message.");
    }

    @Test(priority = 3, dependsOnMethods = {"editUserTest"})
    public void logoutTest() {
        log.info("=== Starting Logout Test ===");
        loginPage.logout();
        log.info("✅ Logout successful.");
    }

    @AfterClass
    public void TearDown() {
        log.info("=== Test Execution Completed. Closing Browser... ===");
        BaseClass.tearDown();
        log.info("Browser closed successfully.");
    }
}


