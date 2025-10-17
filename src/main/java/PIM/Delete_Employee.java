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
import orangehrm.PageObject.PIM_DeleteEmployee;
public class Delete_Employee extends BaseClass  {

	    LoginPage loginPage;
	    PIM_DeleteEmployee deleteemp;
	    private static final Logger log = LogManager.getLogger(Delete_Employee.class);

	    @BeforeClass
	    public void setUp() {
	        log.info("===== Test Setup Started =====");
	        BaseClass.InitializeDriver();
	        BaseClass.maximizeWindow();
	        BaseClass.openUrl();
	        log.info("Browser initialized and OrangeHRM URL opened successfully.");
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
	    public void deleteEmployeeTest() {
	        log.info("===== Starting Delete Employee Test =====");
	        deleteemp = new PIM_DeleteEmployee(BaseClass.driver);

	        try {
	            deleteemp.deleteFirstEmployee();
	            log.info("Triggered employee deletion successfully.");

	            loginPage.verifyToastMessage("Success Successfully Deleted");
	            log.info("Verified success toast message after deletion.");
	        } catch (Exception e) {
	            log.error("❌ Delete Employee Test Failed: {}", e.getMessage());
	            throw e;
	        }

	        log.info("===== Delete Employee Test Completed Successfully =====");
	    }

	    @Test(priority = 3, dependsOnMethods = {"deleteEmployeeTest"})
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
	    public void cleanUp() {
	        BaseClass.tearDown();
	        log.info("Browser closed and WebDriver terminated successfully.");
	        log.info("===== Test Suite Execution Completed =====");
	    }
		
    
	}


