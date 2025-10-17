package orangehrm.PageObject;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Admin_Edit_User {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Admin_Edit_User.class);

    // --- Constructor ---
    public Admin_Edit_User(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        log.info("Admin_Edit_User page initialized.");
    }

    // --- Locators ---
    @FindBy(xpath = "(//button[@type='button' and .//i[contains(@class,'bi-pencil-fill')]])[2]")
    private WebElement secondEditButton;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[1]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[2]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    private WebElement usernameField;

    @FindBy(xpath = "//label[normalize-space()='Yes']//span[contains(@class,'oxd-checkbox-input')]")
    private WebElement enabledCheckbox;

    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div//input[@type='password']")
    private WebElement confirmPasswordField;

    @FindBy(css = "button.oxd-button.oxd-button--secondary[type='submit']")
    private WebElement saveButton;

    // --- Actions ---
    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(secondEditButton)).click();
        log.info("Clicked Edit (pencil) button for second user record.");
    }

    public void selectUserRole(String role) {
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        log.info("Selecting user role: {}", role);

        List<WebElement> options = driver.findElements(
                By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]"));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(role)) {
                option.click();
                log.info("User role selected: {}", role);
                break;
            }
        }
    }

    public void selectEmployeeName(String partial, String fullName) {
        log.info("Selecting employee using partial: '{}' and full name: '{}'", partial, fullName);

        wait.until(ExpectedConditions.elementToBeClickable(employeeNameField));
        employeeNameField.click();
        employeeNameField.sendKeys(Keys.CONTROL, "a");
        employeeNameField.sendKeys(Keys.DELETE);
        employeeNameField.sendKeys(partial);

        List<WebElement> suggestions = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@role='listbox']//span")
                )
        );

        for (WebElement suggestion : suggestions) {
            String suggestionText = suggestion.getText().replaceAll("\\s+", " ").trim();
            if (suggestionText.equalsIgnoreCase(fullName.trim())) {
                wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
                log.info("Employee selected: {}", suggestionText);
                break;
            }
        }
    }

    public void selectStatus(String status) {
        log.info("Selecting status: {}", status);
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        List<WebElement> options = driver.findElements(
                By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]"));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(status)) {
                option.click();
                log.info("Status selected: {}", status);
                break;
            }
        }
    }

    public void enterUsername(String username) {
        log.info("Updating username to: {}", username);
        WebElement userInput = wait.until(ExpectedConditions.visibilityOf(usernameField));
        userInput.sendKeys(Keys.CONTROL + "a");
        userInput.sendKeys(Keys.BACK_SPACE);
        userInput.sendKeys(username);
        log.info("Username field updated successfully.");
    }

    public void setEnabledCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(enabledCheckbox)).click();
        log.info("Enabled checkbox toggled.");
    }

    public void enterPassword(String password) {
        log.info("Updating password fields.");
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField)).sendKeys(password);
        log.info("Password and Confirm Password fields updated.");
    }

    public void saveUser() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        log.info("Clicked Save button to submit user details.");
    }

    // --- Combined Flow ---
    public void editUser(String role, String partialName, String fullName,
                         String status, String username, String password) {
        log.info("Starting editUser flow for employee: {}", fullName);
        clickEditButton();
        selectUserRole(role);
        selectEmployeeName(partialName, fullName);
        selectStatus(status);
        enterUsername(username);
        setEnabledCheckbox();
        enterPassword(password);
        saveUser();
        log.info("✅ Edit user flow completed successfully for {}", username);
    }
}
