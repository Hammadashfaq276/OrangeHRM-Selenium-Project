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



public class Admin_CreateUser {
	private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Admin_CreateUser.class);

    // Constructor
    public Admin_CreateUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Admin_CreateUser page initialized.");
    }

    // Elements
    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[1]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "(//div[contains(@class, 'oxd-select-wrapper')])[2]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(css = "input.oxd-input.oxd-input--active[autocomplete='off']")
    private WebElement usernameInput;

    @FindBy(css = "input.oxd-input.oxd-input--active[type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "(//input[@type='password' and contains(@class,'oxd-input')])[2]")
    private WebElement confirmPasswordInput;

    @FindBy(css = "button.oxd-button.oxd-button--secondary[type='submit']")
    private WebElement saveButton;

    // Actions
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        log.info("Clicked Add button.");
    }

    public void selectUserRole(String role) {
        userRoleDropdown.click();
        log.info("Selecting user role: {}", role);

        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]"));
        for (WebElement option : options) {
            if (option.getText().equals(role)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                log.info("Role selected: {}", role);
                break;
            }
        }
    }

    public void selectEmployee(String employeeName) {
        log.info("Selecting employee: {}", employeeName);
        employeeNameInput.clear();
        employeeNameInput.sendKeys(employeeName.substring(0, 2));

        List<WebElement> suggestions = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='listbox']//span")
            )
        );

        for (WebElement suggestion : suggestions) {
            String suggestionText = suggestion.getText().replaceAll("\\s+", " ").trim();
            if (suggestionText.toLowerCase().contains(employeeName.trim().toLowerCase())) {
                suggestion.click();
                log.info("Employee selected: {}", suggestionText);
                break;
            }
        }
    }

    public void selectStatus(String status) {
        log.info("Selecting status: {}", status);
        statusDropdown.click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]")
        ));

        for (WebElement option : options) {
            if (option.getText().equals(status)) {
                option.click();
                log.info("Status selected: {}", status);
                break;
            }
        }
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
        log.info("Entered username: {}", username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        log.info("Entered and confirmed password.");
    }

    public void clickSave() {
        saveButton.click();
        log.info("Clicked Save button.");
    }
  
    
}
