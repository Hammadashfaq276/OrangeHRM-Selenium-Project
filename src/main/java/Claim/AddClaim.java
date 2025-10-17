package Claim;

import java.io.IOException;
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
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import orangehrm.PageObject.BaseClass;
import orangehrm.PageObject.Claim_AssignClaim;
import orangehrm.PageObject.LoginPage;
import orangehrm.utility.ExcelUtils;

public class AddClaim extends BaseClass {

    private static final Logger log = LogManager.getLogger(AddClaim.class);

    LoginPage loginPage;
    Claim_AssignClaim assignclaim;

    @BeforeClass
    public void setUp() {
        log.info("===== Starting AddClaim Test =====");
        BaseClass.InitializeDriver();
        BaseClass.maximizeWindow();
        BaseClass.openUrl();
        loginPage = new LoginPage(BaseClass.driver);
        log.info("Browser launched and URL opened successfully.");
    }

    @Test(priority = 1)
    public void loginTest() {
        log.info("Starting login test...");
        String pageTitle = loginPage.getPageTitle();
        log.info("Page title: {}", pageTitle);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        loginPage.clickMenu("Claim");

        log.info("Login successful and navigated to 'Claim' menu.");
    }

    @DataProvider(name = "claimDataFromExcel")
    public Object[][] getClaimData() throws Exception {
        log.info("Fetching claim data from Excel...");

        String excelPath = "D:/Web Automation Selenium with java - Rahul Shetty udemy paid course/LoginTest.xlsx";
        String sheetName = "Claims";

        ExcelUtils.setExcelFile(excelPath, sheetName);
        int rowCount = ExcelUtils.getRowCount();
        int colCount = 5;

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0);
            data[i - 1][1] = ExcelUtils.getCellData(i, 1);
            data[i - 1][2] = ExcelUtils.getCellData(i, 2);
            data[i - 1][3] = ExcelUtils.getCellData(i, 3);
            data[i - 1][4] = ExcelUtils.getCellData(i, 4);
        }

        log.info("Excel data loaded successfully with {} rows.", rowCount - 1);
        return data;
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"}, dataProvider = "claimDataFromExcel")
    public void assignClaimTest(String empCode, String empName, String allowance, String currency, String remarks) {
        log.info("===== Starting Assign Claim Test for Employee: {} =====", empName);

        assignclaim = new Claim_AssignClaim(BaseClass.driver);

        assignclaim.clickAddExpense();
        log.info("Clicked on 'Add Expense' button.");

        assignclaim.selectEmployee(empCode, empName);
        log.info("Selected employee: {} ({})", empName, empCode);

        assignclaim.selectAllowance(allowance);
        log.info("Selected allowance: {}", allowance);

        assignclaim.selectCurrency(currency);
        log.info("Selected currency: {}", currency);

        assignclaim.enterRemarks(remarks);
        log.info("Entered remarks: {}", remarks);

        assignclaim.clickCreate();
        log.info("Clicked on 'Create' button.");

        assignclaim.clickConfirm();
        log.info("Clicked on 'Confirm' button.");

        loginPage.verifyToastMessage("Success Successfully Saved");
        log.info("Toast message verified: Claim saved successfully.");

        assignclaim.clickBackButton();
        log.info("Clicked on 'Back' button.");

        log.info("===== Assign Claim Test Completed for Employee: {} =====", empName);
    }

    @Test(priority = 3, dependsOnMethods = {"assignClaimTest"})
    public void logoutTest() {
        log.info("Logging out...");
        loginPage.logout();
        log.info("Logout successful.");
    }

    @AfterClass
    public void TearDown() {
        BaseClass.tearDown();
        log.info("Browser closed. ===== AddClaim Test Completed =====");
    }
}


