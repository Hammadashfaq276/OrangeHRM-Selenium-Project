package orangehrm.PageObject;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PIM_SearchEmployee {

	WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(PIM_SearchEmployee.class);

    // ✅ Constructor
    public PIM_SearchEmployee(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("PIM_SearchEmployee page initialized successfully.");
    }

    // ✅ Locators
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement autoCompleteInput;

    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> suggestions;

    @FindBy(css = "div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']")
    private WebElement employeeIdInput;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button--secondary') and text()[contains(., 'Search')]]")
    private WebElement searchButton;

    @FindBy(xpath = "(//span[contains(@class, 'oxd-text') and contains(@class, 'oxd-text--span')])[13]")
    private WebElement recordText;

    // ✅ Actions
    public void enterPartialName(String name) {
        log.info("Entering partial employee name: {}", name);
        try {
            wait.until(ExpectedConditions.visibilityOf(autoCompleteInput)).sendKeys(name);
            log.info("Partial name '{}' entered successfully.", name);
        } catch (Exception e) {
            log.error("❌ Failed to enter partial name: {}", e.getMessage());
            throw e;
        }
    }

    public void selectSuggestion(String expectedName) {
        log.info("Selecting employee name from suggestions: {}", expectedName);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(suggestions));
            boolean found = false;
            for (WebElement suggestion : suggestions) {
                if (suggestion.getText().equalsIgnoreCase(expectedName)) {
                    suggestion.click();
                    log.info("Selected employee name: {}", expectedName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                log.warn("⚠️ Employee name '{}' not found in suggestions.", expectedName);
            }
        } catch (Exception e) {
            log.error("❌ Failed to select suggestion: {}", e.getMessage());
            throw e;
        }
    }

    public void enterEmployeeId(String empId) {
        log.info("Entering employee ID: {}", empId);
        try {
            wait.until(ExpectedConditions.visibilityOf(employeeIdInput)).sendKeys(empId);
            log.info("Employee ID '{}' entered successfully.", empId);
        } catch (Exception e) {
            log.error("❌ Failed to enter employee ID: {}", e.getMessage());
            throw e;
        }
    }

    public void clickSearchButton() {
        log.info("Clicking on the Search button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
            log.info("Clicked on Search button successfully.");
        } catch (Exception e) {
            log.error("❌ Failed to click Search button: {}", e.getMessage());
            throw e;
        }
    }

    public void getRecordText(String expectedText) {
        log.info("Verifying search result text. Expected: {}", expectedText);
        try {
            String actualText = wait.until(ExpectedConditions.visibilityOf(recordText)).getText();
            log.info("Record Text displayed: {}", actualText);
            Assert.assertEquals(actualText, expectedText, "Record text does not match!");
            log.info("✅ Record text matched successfully.");
        } catch (AssertionError ae) {
            log.error("❌ Record text mismatch: {}", ae.getMessage());
            throw ae;
        } catch (Exception e) {
            log.error("❌ Failed to verify record text: {}", e.getMessage());
            throw e;
        }
    }
	
}
