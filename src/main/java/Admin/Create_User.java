package Admin;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangehrm.PageObject.Admin_CreateUser;
import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.LoginPage;
import orangehrm.utility.ExcelUtils;

public class Create_User extends BaseClass {

    private static final Logger log = LogManager.getLogger(Create_User.class);

    LoginPage loginPage;
    Admin_CreateUser admin_user;

    @BeforeClass
    public void setUp() {
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and LoginPage initialized.");
    }

    @Test(priority = 1)
    public void loginTest() {
        try {
            String excelPath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/LoginTest.xlsx";
            String sheetName = "Login";
            ExcelUtils.setExcelFile(excelPath, sheetName);

            int rowCount = ExcelUtils.getRowCount();
            log.info("Total rows in Excel: {}", rowCount);

            for (int i = 1; i < rowCount; i++) { // row 0 is header
                String username = ExcelUtils.getCellData(i, 0);
                String password = ExcelUtils.getCellData(i, 1);

                log.info("Testing with Username: {} | Password: {}", username, password);

                log.info("Page Title: {}", loginPage.getPageTitle());
                loginPage.enterUsername(username);
                loginPage.enterPassword(password);
                loginPage.clickLogin();

                if (loginPage.isLoginSuccessful()) {
                    log.info("✅ Login SUCCESS for user: {}", username);
                    loginPage.clickMenu("Admin");
                } else {
                    log.warn("❌ Login FAILED for user: {}", username);
                }
            }

        } catch (Exception e) {
            log.error("❌ Error during loginTest execution.", e);
            Assert.fail("Test execution failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void createUserTest() {
        try {
            String excelPath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/LoginTest.xlsx";
            String sheetName = "Users";
            ExcelUtils.setExcelFile(excelPath, sheetName);

            int rowCount = ExcelUtils.getRowCount();
            log.info("Total rows in CreateUser sheet: {}", rowCount);

            for (int i = 1; i < rowCount; i++) {
                String role = ExcelUtils.getCellData(i, 0);
                String employee = ExcelUtils.getCellData(i, 1);
                String status = ExcelUtils.getCellData(i, 2);
                String username = ExcelUtils.getCellData(i, 3);
                String password = ExcelUtils.getCellData(i, 4);

                log.info("Creating new user -> Role: {}, Employee: {}, Status: {}, Username: {}",
                        role, employee, status, username);

                admin_user = new Admin_CreateUser(BaseClass.driver);

                admin_user.clickAddButton();
                admin_user.selectUserRole(role);
                admin_user.selectEmployee(employee);
                admin_user.selectStatus(status);
                admin_user.enterUsername(username);
                admin_user.enterPassword(password);
                admin_user.clickSave();

                loginPage.verifyToastMessage("Success Successfully Saved");
                log.info("✅ User created successfully: {}", username);
            }

        } catch (Exception e) {
            log.error("❌ Error in createUserTest.", e);
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, dependsOnMethods = {"createUserTest"})
    public void logoutTest() {
        try {
            loginPage.logout();
            log.info("✅ Logout successful.");
        } catch (Exception e) {
            log.error("❌ Error during logout.", e);
        }
    }

    @AfterClass
    public void TearDown() {
        BaseClass.tearDown();
        log.info("Browser closed and test execution completed.");
    }
}