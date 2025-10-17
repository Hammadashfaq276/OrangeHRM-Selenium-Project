package PIM;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import orangehrm.PageObject.PIM_EditEmployee;

public class Edit_Employee extends BaseClass {

	 LoginPage loginPage;
	    PIM_EditEmployee editemp;
	    private static final Logger log = LogManager.getLogger(Edit_Employee.class);

	    @BeforeClass
	    public void setUp() {
	        log.info("===== Test Setup Started =====");
	        BaseClass.InitializeDriver();
	        BaseClass.maximizeWindow();
	        BaseClass.openUrl();
	        log.info("Browser launched and URL opened successfully.");
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
	        log.info("Navigated to PIM module.");
	        log.info("===== Login Test Completed Successfully =====");
	    }

	    @Test(priority = 2, dependsOnMethods = {"loginTest"})
	    public void editEmployeeTest() {
	        log.info("===== Starting Edit Employee Test =====");
	        editemp = new PIM_EditEmployee(BaseClass.driver);

	        String filePath = "D:\\Web Automation Selenium with java - Rahul Shetty udemy paid course\\FB_IMG_1715625292457.jpg";
	        log.info("Employee image path: {}", filePath);

	        try {
	            editemp.editEmployeeDetails(
	                    filePath,
	                    "Hammad", "Ashfaq", "Sheikh",
	                    "1567", "1283", "LE-2179",
	                    "2026-07-15", "Pakistani", "Single",
	                    "1995-12-09", "B+", "Single"
	            );
	            log.info("Employee details edited successfully.");

	            loginPage.verifyToastMessage("Success Successfully Saved");
	            log.info("Verified success toast message.");

	        } catch (Exception e) {
	            log.error("❌ Edit Employee Test Failed: {}", e.getMessage());
	            throw e;
	        }
	        log.info("===== Edit Employee Test Completed Successfully =====");
	    }

	    @Test(priority = 3, dependsOnMethods = {"editEmployeeTest"})
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
	        log.info("Browser closed and driver terminated.");
	        log.info("===== Test Suite Execution Completed =====");
	    }
       
	
	
	}


