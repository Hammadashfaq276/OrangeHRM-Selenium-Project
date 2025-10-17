package orangehrm.PageObject;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Admin_SearchUser {

    private static final Logger log = LogManager.getLogger(Admin_SearchUser.class);

    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
    public Admin_SearchUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Admin_SearchUser Page Initialized.");
    }

    // Locators
    @FindBy(xpath = "(//input[contains(@class, 'oxd-input') and contains(@class, 'oxd-input--active')])[2]")
    WebElement usernameInput;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[1]")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//div[contains(@class,'oxd-select-option')]")
    List<WebElement> dropdownOptions;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement autoCompleteInput;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[2]")
    WebElement statusDropdown;

    @FindBy(xpath = "//button[contains(@class, 'oxd-button--secondary') and text()[contains(., 'Search')]]")
    WebElement searchButton;

    @FindBy(xpath = "(//span[contains(@class, 'oxd-text') and contains(@class, 'oxd-text--span')])[13]")
    WebElement recordText;

    // --- Actions ---
    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
    }

    public void selectUserRole(String roleName) {
        log.info("Selecting user role: {}", roleName);
        userRoleDropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(roleName)) {
                option.click();
                log.info("Role selected: {}", roleName);
                break;
            }
        }
    }

    public void selectAutoComplete(String partialName, String fullName) {
        log.info("Selecting employee from autocomplete. Partial: '{}', Full: '{}'", partialName, fullName);
        autoCompleteInput.sendKeys(partialName);
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span"))
        );
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase(fullName)) {
                suggestion.click();
                log.info("Employee selected: {}", fullName);
                break;
            }
        }
    }

    public void selectStatus(String status) {
        log.info("Selecting status: {}", status);
        statusDropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(status)) {
                option.click();
                log.info("Status selected: {}", status);
                break;
            }
        }
    }

    public void clickSearch() {
        log.info("Clicking on Search button...");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        log.info("Search button clicked successfully.");
    }

    public void getRecordText(String expectedText) {
        log.info("Verifying record text...");
        String actualText = wait.until(ExpectedConditions.visibilityOf(recordText)).getText();
        log.info("Record Text found: {}", actualText);
        Assert.assertEquals(actualText, expectedText, "Record text did not match!");
        log.info("✅ Record text verification passed: {}", expectedText);
    }
}
