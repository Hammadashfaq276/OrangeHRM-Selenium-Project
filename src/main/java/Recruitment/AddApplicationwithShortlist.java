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
import orangehrm.PageObject.Recruitment_Addapplicantwithshortlisted;

public class AddApplicationwithShortlist extends BaseClass {

    LoginPage loginPage;
    Recruitment_Addapplicantwithshortlisted addapplicant;
    private static final Logger log = LogManager.getLogger(AddApplicationwithShortlist.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Test Setup Started =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and OrangeHRM URL opened successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting Login Test =====");
        log.info("Page Title: {}", loginPage.getPageTitle());

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        log.info("Successfully logged into OrangeHRM with Admin credentials.");

        loginPage.clickMenu("Recruitment");
        log.info("Navigated to Recruitment section.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void addApplicantWithShortlistTest() {
        log.info("===== Starting Add Applicant with Shortlist Test =====");
        addapplicant = new Recruitment_Addapplicantwithshortlisted(BaseClass.driver);

        addapplicant.clickAddEmployee();
        log.info("Clicked on 'Add Candidate' button.");

        addapplicant.enterFirstName("Hammad");
        addapplicant.enterMiddleName("Ashfaq");
        addapplicant.enterLastName("Ahmed");
        addapplicant.selectJobTitle("Senior QA Lead");
        addapplicant.enterEmail("Hammadashfaq341@gmail.com");
        addapplicant.enterPhone("03214918172");

        String filePath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/Hammad Ashfaq SQA.pdf";
        addapplicant.uploadFile(filePath);
        log.info("Uploaded resume file from path: {}", filePath);

        addapplicant.enterKeywords("345");
        addapplicant.enterDate("2025-26-08");
        addapplicant.enterNotes("This is test notes");
        addapplicant.clickCheckbox();
        log.info("Filled all candidate details and checked consent box.");

        addapplicant.clickSave();
        log.info("Clicked Save to submit the candidate application.");

        loginPage.verifyToastMessage("Success Successfully Saved");
        log.info("✅ Candidate added and shortlisted successfully. Verified success toast message.");
    }

    @Test(priority = 3, dependsOnMethods = {"addApplicantWithShortlistTest"})
    public void logoutTest() {
        log.info("===== Starting Logout Test =====");
        loginPage.logout();
        log.info("User logged out successfully.");
    }

    @AfterClass
    public void TearDown() {
        log.info("===== Test Execution Completed — Closing Browser =====");
        BaseClass.tearDown();
    }
}


