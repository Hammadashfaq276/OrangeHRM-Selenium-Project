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
import orangehrm.PageObject.Buzz_AddPost;
import orangehrm.PageObject.LoginPage;

public class AddPost extends BaseClass {

    private static final Logger log = LogManager.getLogger(AddPost.class);

    LoginPage loginPage;
    Buzz_AddPost buzzaddpost;

    @BeforeClass
    public void setUp() {
        log.info("===== Starting AddPost Test Suite =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and navigated to URL successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        try {
            log.info("Starting login test...");
            String pageTitle = loginPage.getPageTitle();
            log.info("Page title fetched: {}", pageTitle);

            loginPage.enterUsername("Admin");
            log.info("Entered username.");

            loginPage.enterPassword("admin123");
            log.info("Entered password.");

            loginPage.clickLogin();
            log.info("Clicked on login button.");

            loginPage.clickMenu("Buzz");
            log.info("Navigated to Buzz page successfully.");

        } catch (Exception e) {
            log.error("❌ Login test failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void addLikeCommentTest() {
        try {
            log.info("Starting add/like/comment test...");
            buzzaddpost = new Buzz_AddPost(BaseClass.driver);

            // File path
            String filePath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/FB_IMG_1715625292457.jpg";
            log.info("Using image file path: {}", filePath);

            // Create post
            buzzaddpost.createPost("This is Testing demo", filePath);
            log.info("Post created successfully with message and image.");

            // Like latest post
            buzzaddpost.likeLatestPost();
            log.info("Liked the latest post successfully.");

            // Comment on latest post
            buzzaddpost.commentOnLatestPost("Test comment");
            log.info("Added comment on latest post successfully.");

            // Verify toast
            loginPage.verifyToastMessage("Success Successfully Saved");
            log.info("✅ Verified toast message successfully: 'Success Successfully Saved'");

        } catch (Exception e) {
            log.error("❌ Add/Like/Comment test failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, dependsOnMethods = {"addLikeCommentTest"})
    public void logoutTest() {
        try {
            log.info("Starting logout test...");
            loginPage.logout();
            log.info("✅ Logout successful.");
        } catch (Exception e) {
            log.error("❌ Logout failed: {}", e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void TearDown() {
        BaseClass.tearDown();
        log.info("===== AddPost Test Suite Finished Successfully =====");
    }
}

