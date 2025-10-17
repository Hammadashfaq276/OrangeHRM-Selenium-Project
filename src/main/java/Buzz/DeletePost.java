package Buzz;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.Buzz_DeletePost;
import orangehrm.PageObject.LoginPage;

public class DeletePost extends BaseClass {

    private static final Logger log = LogManager.getLogger(DeletePost.class);

    LoginPage loginPage;
    Buzz_DeletePost deletepost;

    @BeforeClass
    public void setUp() {
        log.info("===== Starting DeletePost Test Suite =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser initialized and URL opened successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("Starting loginTest...");
        log.info("Page title: {}", loginPage.getPageTitle());
        loginPage.enterUsername("Admin");
        log.info("Entered username: Admin");
        loginPage.enterPassword("admin123");
        log.info("Entered password.");
        loginPage.clickLogin();
        log.info("Clicked login button.");
        loginPage.clickMenu("Buzz");
        log.info("Navigated to Buzz module successfully.");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void deletePostTest() {
        log.info("Starting deletePostTest...");
        deletepost = new Buzz_DeletePost(BaseClass.driver);

        // Delete latest post
        deletepost.deleteLatestPost();

        // Verify toast message
        loginPage.verifyToastMessage("Success Successfully Deleted");
        log.info("✅ Post deleted successfully and toast verified.");
    }

    @Test(priority = 3, dependsOnMethods = {"deletePostTest"})
    public void logoutTest() {
        log.info("Executing logoutTest...");
        loginPage.logout();
        log.info("User logged out successfully.");
    }

    @AfterClass
    public void TearDown() {
        log.info("Closing browser and ending DeletePost test suite...");
        BaseClass.tearDown();
        log.info("===== DeletePost Test Suite Finished =====");
    }
}

