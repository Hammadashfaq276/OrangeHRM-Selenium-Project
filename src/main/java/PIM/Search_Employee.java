package PIM;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import orangehrm.PageObject.PIM_SearchEmployee;

public class Search_Employee extends BaseClass {

	LoginPage loginPage;
    PIM_SearchEmployee searchemp;
    private static final Logger log = LogManager.getLogger(Search_Employee.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Test Setup Started =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        log.info("Browser initialized and URL opened successfully.");
        loginPage = new LoginPage(BaseClass.driver);
        log.info("LoginPage object created.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting Login Test =====");
        String title = loginPage.getPageTitle();
        log.info("Page title: {}", title);

        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");

        loginPage.enterPassword("admin123");
        log.info("Entered password.");

        loginPage.clickLogin();
        log.info("Clicked on Login button.");

        loginPage.clickMenu("PIM");
        log.info("Navigated to PIM section.");

        log.info("===== Login Test Completed Successfully =====");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void searchEmployeeTest() {
        log.info("===== Starting Search Employee Test =====");
        searchemp = new PIM_SearchEmployee(BaseClass.driver);

        try {
            searchemp.enterPartialName("ha");
            log.info("Entered partial employee name: 'ha'");

            searchemp.selectSuggestion("Russel Hamilton");
            log.info("Selected employee: 'Russel Hamilton' from suggestions.");

            searchemp.enterEmployeeId("0034");
            log.info("Entered employee ID: 0034");

            searchemp.clickSearchButton();
            log.info("Clicked on Search button.");

            searchemp.getRecordText("(1) Record Found");
            log.info("Verified that (1) Record Found successfully.");

        } catch (Exception e) {
            log.error("❌ Search Employee Test Failed: {}", e.getMessage());
            throw e;
        }

        log.info("===== Search Employee Test Completed Successfully =====");
    }

    @Test(priority = 3, dependsOnMethods = {"searchEmployeeTest"})
    public void logoutTest() {
        log.info("===== Starting Logout Test =====");
        try {
            loginPage.logout();
            log.info("User logged out successfully.");
        } catch (Exception e) {
            log.error("❌ Logout failed: {}", e.getMessage());
            throw e;
        }
        log.info("===== Logout Test Completed Successfully =====");
    }

    @AfterClass
    public void TearDown() {
        BaseClass.tearDown();
        log.info("Browser closed and driver quit successfully.");
        log.info("===== Test Suite Execution Completed =====");
    }
       
	
	}

