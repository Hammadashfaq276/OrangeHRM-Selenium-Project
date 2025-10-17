package PIM;
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

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import orangehrm.PageObject.PIM_DeleteAllEmployee;

public class Delete_AllEmployee extends BaseClass {

	LoginPage loginPage;
    PIM_DeleteAllEmployee deleteallemp;
    private static final Logger log = LogManager.getLogger(Delete_AllEmployee.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Starting Delete_AllEmployee Test Suite =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and navigated to application URL.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting Login Test =====");
        try {
            String title = loginPage.getPageTitle();
            log.info("Page title: {}", title);

            loginPage.enterUsername("Admin");
            log.info("Entered username: Admin");

            loginPage.enterPassword("admin123");
            log.info("Entered password: admin123");

            loginPage.clickLogin();
            log.info("Clicked on Login button.");

            loginPage.clickMenu("PIM");
            log.info("Navigated to PIM module.");

            log.info("✅ Login Test Passed.");
        } catch (Exception e) {
            log.error("❌ Login Test Failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void deleteAllEmployeesTest() {
        log.info("===== Starting Delete All Employees Test =====");
        deleteallemp = new PIM_DeleteAllEmployee(BaseClass.driver);

        try {
            deleteallemp.selectCheckboxIfNotSelected();
            log.info("Selected all employee checkboxes.");

            deleteallemp.clickDelete();
            log.info("Clicked Delete and confirmed deletion.");

            loginPage.verifyToastMessage("Success Successfully Deleted");
            log.info("✅ Toast message verified: Success Successfully Deleted");

            log.info("✅ Delete All Employees Test Passed.");
        } catch (Exception e) {
            log.error("❌ Delete All Employees Test Failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, dependsOnMethods = {"deleteAllEmployeesTest"})
    public void logoutTest() {
        log.info("===== Starting Logout Test =====");
        try {
            loginPage.logout();
            log.info("✅ Logout successful.");
        } catch (Exception e) {
            log.error("❌ Logout Test Failed: {}", e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void TearDown() {
        log.info("Closing browser and cleaning up...");
        BaseClass.tearDown();
        log.info("===== Delete_AllEmployee Test Suite Completed =====");
    }
       
       
	}


