package Recruitment;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import orangehrm.PageObject.Recruitment_Edit;

public class EditRecruitment extends BaseClass {

    private static final Logger log = LogManager.getLogger(EditRecruitment.class);

    LoginPage loginPage;
    Recruitment_Edit edit;

    @BeforeClass
    public void setUp() {
        log.info("===== Test Setup Started =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser initialized, maximized, and URL opened successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting Login Test =====");
        log.info("Page Title: {}", loginPage.getPageTitle());
        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");
        loginPage.enterPassword("admin123");
        log.info("Entered password: admin123");
        loginPage.clickLogin();
        log.info("Clicked on Login button.");
        loginPage.clickMenu("Recruitment");
        log.info("Navigated to Recruitment menu.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void editApplicantTest() {
        log.info("===== Starting Edit Applicant Test =====");
        edit = new Recruitment_Edit(BaseClass.driver);

        edit.clickEyeIcon();
        log.info("Opened applicant details using Eye icon.");

        edit.toggleSwitch();
        log.info("Toggled the active/inactive switch.");

        edit.enterFirstName("Haseeb");
        log.info("Updated first name to: Haseeb");

        edit.enterMiddleName("Maria");
        log.info("Updated middle name to: Maria");

        edit.enterLastName("Jameel");
        log.info("Updated last name to: Jameel");

        edit.selectJobTitle("Software Engineer");
        log.info("Selected job title: Software Engineer");

        edit.enterEmail("Haseeb99@gmail.com");
        log.info("Updated email: Haseeb99@gmail.com");

        edit.enterPhone("03324567894");
        log.info("Updated phone number: 03324567894");

        edit.enterKeywords("389");
        log.info("Entered keywords: 389");

        edit.enterDate("2025-18-08");
        log.info("Entered date: 2025-18-08");

        edit.enterNotes("This is testing notes");
        log.info("Entered notes.");

        edit.checkCheckbox();
        log.info("Checked consent checkbox.");

        edit.clickSave();
        log.info("Clicked Save button.");

        edit.confirmSave();
        log.info("Confirmed save action in popup.");

        loginPage.verifyToastMessage("Success Successfully Updated");
        log.info("✅ Verified toast message: Success Successfully Updated");
    }

    @Test(priority = 3, dependsOnMethods = {"editApplicantTest"})
    public void logoutTest() {
        log.info("===== Starting Logout Test =====");
        loginPage.logout();
        log.info("Logged out successfully.");
    }

    @AfterClass
    public void TearDown() {
        log.info("===== Test Execution Completed — Closing Browser =====");
        BaseClass.tearDown();
    }
}

