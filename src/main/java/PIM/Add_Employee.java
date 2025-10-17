package PIM;

import java.time.Duration;
import java.util.List;
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
import orangehrm.PageObject.PIM_AddEmployee;

public class Add_Employee extends BaseClass {

	 private static final Logger log = LogManager.getLogger(Add_Employee.class);
	    LoginPage loginPage;
	    PIM_AddEmployee addemp;

	    @BeforeClass
	    public void setUp() {
	        log.info("========== Test Setup Started ==========");
	        BaseClass.InitializeDriver();
	        BaseClass.maximizeWindow();
	        BaseClass.openUrl();
	        loginPage = new LoginPage(BaseClass.driver);
	        log.info("Browser initialized and navigated to URL successfully.");
	    }

	    @Test(priority = 1)
	    public void loginTest() {
	        log.info("===== Starting Login Test =====");
	        log.info("Page Title: {}", loginPage.getPageTitle());

	        loginPage.enterUsername("Admin");
	        log.info("Entered Username: Admin");

	        loginPage.enterPassword("admin123");
	        log.info("Entered Password: *****");

	        loginPage.clickLogin();
	        log.info("Clicked on Login Button.");

	        loginPage.clickMenu("PIM");
	        log.info("Navigated to PIM Module successfully.");
	    }

	    @Test(priority = 2, dependsOnMethods = {"loginTest"})
	    public void addEmployeeTest() {
	        log.info("===== Starting Add Employee Test =====");

	        addemp = new PIM_AddEmployee(BaseClass.driver);
	        String filePath = "D:\\Web Automation Selenium with java - Rahul Shetty udemy paid course\\FB_IMG_1715625292457.jpg";

	        log.info("Uploading employee image from path: {}", filePath);

	        addemp.addEmployee(
	                filePath,
	                "Hammad", "Ashfaq", "Ashfaq",
	                "Hammad160", "Hammad@150", "1283", "2179",
	                "2026-05-25", "Algerian", "Single",
	                "1995-09-12", "B+", "Single"
	        );

	        log.info("Employee details filled and submitted successfully.");

	        loginPage.verifyToastMessage("Success Successfully Saved");
	        log.info("Verified Success Toast Message.");
	    }

	    @Test(priority = 3, dependsOnMethods = {"addEmployeeTest"})
	    public void logoutTest() {
	        log.info("===== Starting Logout Test =====");
	        loginPage.logout();
	        log.info("User logged out successfully.");
	    }

	    @AfterClass
	    public void TearDown() {
	        log.info("========== Test Completed. Closing Browser ==========");
	        BaseClass.tearDown();
	    }
		
    
	}


