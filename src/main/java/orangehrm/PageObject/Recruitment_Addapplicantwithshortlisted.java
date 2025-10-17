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

public class Recruitment_Addapplicantwithshortlisted {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Recruitment_Addapplicantwithshortlisted.class);

    // ====== Constructor ======
    public Recruitment_Addapplicantwithshortlisted(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Recruitment_Addapplicantwithshortlisted page initialized.");
    }

    // ====== Locators ======
    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addEmployeeBtn;

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
    private WebElement email;

    @FindBy(xpath = "(//input[@placeholder='Type here'])[2]")
    private WebElement phone;

    @FindBy(css = "input[type='file']")
    private WebElement fileUpload;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    private WebElement keywords;

    @FindBy(xpath = "//input[@placeholder='yyyy-dd-mm']")
    private WebElement dateField;

    @FindBy(css = "textarea[placeholder='Type here']")
    private WebElement notes;

    @FindBy(css = ".oxd-icon.bi-check.oxd-checkbox-input-icon")
    private WebElement checkbox;

    @FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
    private WebElement saveBtn;

    // ====== Actions ======

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn)).click();
        log.info("Clicked on 'Add Employee' button.");
    }

    public void enterFirstName(String fname) {
        firstName.sendKeys(fname);
        log.info("Entered first name: {}", fname);
    }

    public void enterMiddleName(String mname) {
        middleName.sendKeys(mname);
        log.info("Entered middle name: {}", mname);
    }

    public void enterLastName(String lname) {
        lastName.sendKeys(lname);
        log.info("Entered last name: {}", lname);
    }

    public void selectJobTitle(String jobTitle) {
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));

        for (WebElement option : dropdownOptions) {
            if (option.getText().trim().equals(jobTitle)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
                log.info("Selected job title: {}", jobTitle);
                return;
            }
        }
        log.warn("Job title '{}' not found in dropdown options.", jobTitle);
    }

    public void enterEmail(String mail) {
        email.sendKeys(mail);
        log.info("Entered email: {}", mail);
    }

    public void enterPhone(String mobile) {
        phone.sendKeys(mobile);
        log.info("Entered phone number: {}", mobile);
    }

    public void uploadFile(String filePath) {
        fileUpload.sendKeys(filePath);
        log.info("Uploaded file: {}", filePath);
    }

    public void enterKeywords(String word) {
        keywords.click();
        keywords.sendKeys(word);
        log.info("Entered keywords: {}", word);
    }

    public void enterDate(String date) {
        dateField.click();
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateField.sendKeys(Keys.DELETE);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.TAB);
        log.info("Entered date: {}", date);
    }

    public void enterNotes(String text) {
        notes.sendKeys(text);
        log.info("Entered notes: {}", text);
    }

    public void clickCheckbox() {
        checkbox.click();
        log.info("Clicked on consent checkbox.");
    }

    public void clickSave() {
        saveBtn.click();
        log.info("Clicked on 'Save' button.");
    }
}
