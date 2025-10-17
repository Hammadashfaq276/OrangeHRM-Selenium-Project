package Buzz;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
import orangehrm.PageObject.Buzz_EditPost;
import orangehrm.PageObject.LoginPage;

public class EditPost extends BaseClass {

    private static final Logger log = LogManager.getLogger(EditPost.class);

    LoginPage loginPage;
    Buzz_EditPost editpost;

    @BeforeClass
    public void setUp() {
        log.info("===== Starting EditPost Test Suite =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and navigated to application URL.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("===== Starting loginTest =====");
        log.info("Page title: {}", loginPage.getPageTitle());

        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");

        loginPage.enterPassword("admin123");
        log.info("Entered password.");

        loginPage.clickLogin();
        log.info("Clicked on Login button.");

        loginPage.clickMenu("Buzz");
        log.info("Navigated to Buzz module.");

        log.info("===== loginTest completed successfully =====");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void editPostTest() {
        log.info("===== Starting editPostTest =====");
        editpost = new Buzz_EditPost(BaseClass.driver);

        // Step 1: Click three dots
        editpost.clickThreeDots();
        log.info("Clicked on three dots icon of latest post.");

        // Step 2: Click edit post
        editpost.clickEditPost();
        log.info("Opened Edit Post modal.");

        // Step 3: Edit text
        String newText = "This is the new testing demo text version one";
        editpost.clearAndTypeText(newText);
        log.info("Updated post text: '{}'", newText);

        // Step 4: Upload file
        String filePath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/FB_IMG_1715625292457.jpg";
        editpost.uploadFile(filePath);
        log.info("Uploaded image file from: {}", filePath);

        // Step 5: Save the post
        editpost.clickPostButton();
        log.info("Clicked on Post button to save changes.");

        // Step 6: Verify success toast
        loginPage.verifyToastMessage("Success Successfully Updated");
        log.info("Verified success toast message for post update.");

        log.info("===== editPostTest completed successfully =====");
    }

    @Test(priority = 3, dependsOnMethods = {"editPostTest"})
    public void logoutTest() {
        log.info("===== Starting logoutTest =====");
        loginPage.logout();
        log.info("User logged out successfully.");
        log.info("===== logoutTest completed successfully =====");
    }

    @AfterClass
    public void TearDown() {
        BaseClass.tearDown();
        log.info("Browser closed and resources cleaned up.");
        log.info("===== EditPost Test Suite Finished =====");
    }
}


