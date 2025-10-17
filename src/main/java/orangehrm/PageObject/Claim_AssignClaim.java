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

public class Claim_AssignClaim {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Claim_AssignClaim.class);

    // 🔹 Constructor
    public Claim_AssignClaim(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("Claim_AssignClaim page initialized successfully.");
    }

    // 🔹 Locators
    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addExpenseBtn;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement autoCompleteInput;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    private WebElement allowanceDropdown;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    private WebElement currencyDropdown;

    @FindBy(css = ".oxd-textarea.oxd-textarea--active.oxd-textarea--resize-vertical")
    private WebElement remarksTextarea;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement createBtn;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-sm-button']")
    private WebElement confirmBtn;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-sm-button']")
    private WebElement backButton;

    // 🔹 Actions (Methods)

    public void clickAddExpense() {
        log.info("Clicking 'Add Expense' button...");
        addExpenseBtn.click();
    }

    public void selectEmployee(String partialText, String fullName) {
        log.info("Selecting employee with partial text '{}' and full name '{}'", partialText, fullName);
        autoCompleteInput.sendKeys(partialText);
        List<WebElement> suggestions = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase(fullName)) {
                suggestion.click();
                log.info("Selected employee: {}", fullName);
                break;
            }
        }
    }

    public void selectAllowance(String allowance) {
        log.info("Selecting allowance: {}", allowance);
        allowanceDropdown.click();
        List<WebElement> options = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(allowance)) {
                option.click();
                log.info("Allowance '{}' selected.", allowance);
                break;
            }
        }
    }

    public void selectCurrency(String currency) {
        log.info("Selecting currency: {}", currency);
        currencyDropdown.click();
        List<WebElement> options = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(currency)) {
                option.click();
                log.info("Currency '{}' selected.", currency);
                break;
            }
        }
    }

    public void enterRemarks(String remarks) {
        log.info("Entering remarks: {}", remarks);
        remarksTextarea.sendKeys(remarks);
    }

    public void clickCreate() {
        log.info("Clicking 'Create' button...");
        createBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='oxd-loading-spinner']")));
        log.info("Create button clicked and spinner disappeared.");
    }

    public void clickConfirm() {
        log.info("Clicking 'Confirm' button...");
        confirmBtn.click();
        log.info("Confirmed successfully.");
    }

    public void clickBackButton() {
        log.info("Clicking 'Back' button...");
        backButton.click();
        log.info("Navigated back successfully.");
    }
}
