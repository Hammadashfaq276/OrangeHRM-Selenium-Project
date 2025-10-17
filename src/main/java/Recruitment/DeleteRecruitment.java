package Recruitment;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import orangehrm.PageObject.Recruitment_Delete;

public class DeleteRecruitment extends BaseClass {

    LoginPage loginPage;
    Recruitment_Delete recruitmentdelete;
    private static final Logger log = LogManager.getLogger(DeleteRecruitment.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Test Setup Started =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and URL opened successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting Login Test =====");
        log.info("Page Title: {}", loginPage.getPageTitle());

        loginPage.enterUsername("Admin");
        log.info("Entered Username: Admin");

        loginPage.enterPassword("admin123");
        log.info("Entered Password: admin123");

        loginPage.clickLogin();
        log.info("Clicked Login button successfully.");

        loginPage.clickMenu("Recruitment");
        log.info("Navigated to Recruitment section.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void deleteApplicantTest() {
        log.info("===== Starting Delete Applicant Test =====");
        recruitmentdelete = new Recruitment_Delete(BaseClass.driver);

        recruitmentdelete.selectSecondCheckbox();
        log.info("Selected the second applicant checkbox.");

        recruitmentdelete.clickDeleteButton();
        log.info("Clicked the delete button.");

        recruitmentdelete.confirmDelete();
        log.info("Confirmed the deletion successfully.");

        loginPage.verifyToastMessage("Success Successfully Deleted");
        log.info("✅ Verified success toast message after deletion.");
    }

    @Test(priority = 3, dependsOnMethods = {"deleteApplicantTest"})
    public void logoutTest() {
        log.info("===== Logging Out =====");
        loginPage.logout();
        log.info("User logged out successfully.");
    }

    @AfterClass
    public void TearDown() {
        log.info("===== Test Execution Completed - Closing Browser =====");
        BaseClass.tearDown();
    }
}

