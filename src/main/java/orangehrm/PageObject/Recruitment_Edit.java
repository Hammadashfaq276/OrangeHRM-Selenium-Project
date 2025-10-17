package orangehrm.PageObject;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Recruitment_Edit {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Recruitment_Edit.class);

    // ===== Constructor =====
    public Recruitment_Edit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("Recruitment_Edit page initialized successfully.");
    }

    // ===== Locators =====
    @FindBy(xpath = "(//i[@class='oxd-icon bi-eye-fill'])[4]")
    private WebElement eyeIcon;

    @FindBy(css = ".oxd-switch-input")
    private WebElement toggleSwitch;

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "middleName")
    private WebElement middleName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//div[contains(@class,'oxd-select-text-input')]")
    private WebElement dropdown;

    @FindBy(xpath = "//div[@role='option']")
    private List<WebElement> dropdownOptions;

    @FindBy(xpath = "(//input[@placeholder='Type here'])[1]")
    private WebElement emailField;

    @FindBy(xpath = "(//input[@placeholder='Type here'])[2]")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    private WebElement keywordsField;

    @FindBy(xpath = "//input[@placeholder='yyyy-dd-mm']")
    private WebElement dateField;

    @FindBy(css = "textarea[placeholder='Type here']")
    private WebElement notesField;

    @FindBy(css = ".oxd-icon.bi-check.oxd-checkbox-input-icon")
    private WebElement checkbox;

    @FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
    private WebElement saveBtn;

    @FindBy(xpath = "//button[normalize-space()='Yes, Confirm']")
    private WebElement confirmBtn;

    // ===== Actions =====
    public void clickEyeIcon() {
        log.info("Clicking on the Eye icon to open candidate details...");
        eyeIcon.click();
    }

    public void toggleSwitch() {
        log.info("Toggling the active/inactive switch...");
        toggleSwitch.click();
    }

    public void enterFirstName(String fname) {
        log.info("Editing First Name: {}", fname);
        firstName.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        firstName.sendKeys(fname);
    }

    public void enterMiddleName(String mname) {
        log.info("Editing Middle Name: {}", mname);
        middleName.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        middleName.sendKeys(mname);
    }

    public void enterLastName(String lname) {
        log.info("Editing Last Name: {}", lname);
        lastName.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        lastName.sendKeys(lname);
    }

    public void selectJobTitle(String title) {
        log.info("Selecting Job Title: {}", title);
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));
        for (WebElement option : dropdownOptions) {
            if (option.getText().trim().equals(title)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
                log.info("Job title '{}' selected successfully.", title);
                break;
            }
        }
    }

    public void enterEmail(String email) {
        log.info("Updating Email: {}", email);
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        emailField.sendKeys(email);
    }

    public void enterPhone(String phone) {
        log.info("Updating Phone Number: {}", phone);
        phoneField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        phoneField.sendKeys(phone);
    }

    public void enterKeywords(String keywords) {
        log.info("Updating Keywords: {}", keywords);
        keywordsField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        keywordsField.sendKeys(keywords);
    }

    public void enterDate(String date) {
        log.info("Editing Date: {}", date);
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.TAB);
    }

    public void enterNotes(String notes) {
        log.info("Updating Notes: {}", notes);
        notesField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        notesField.sendKeys(notes);
    }

    public void checkCheckbox() {
        log.info("Checking the consent checkbox...");
        checkbox.click();
    }

    public void clickSave() {
        log.info("Clicking the Save button...");
        saveBtn.click();
    }

    public void confirmSave() {
        log.info("Confirming save action in popup...");
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        log.info("✅ Save confirmed successfully.");
    }
}
