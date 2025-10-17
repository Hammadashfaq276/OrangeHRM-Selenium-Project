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

public class PIM_EditEmployee {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(PIM_EditEmployee.class);

    // ✅ Constructor
    public PIM_EditEmployee(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        log.info("PIM_EditEmployee page initialized successfully.");
    }

    // ✅ Locators
    @FindBy(xpath = "(//button[@type='button']//i[contains(@class, 'bi-pencil-fill')])[3]")
    WebElement pencilIcon;
    @FindBy(css = "div.orangehrm-edit-employee-image img.employee-image")
    WebElement imageIcon;
    @FindBy(css = "input[type='file']")
    WebElement fileInput;
    @FindBy(css = "button[type='submit']")
    WebElement submitButton;
    @FindBy(css = "div[role='tablist'] div:nth-child(1)")
    WebElement personalDetailsTab;
    @FindBy(name = "firstName")
    WebElement firstName;
    @FindBy(name = "middleName")
    WebElement middleName;
    @FindBy(name = "lastName")
    WebElement lastName;
    @FindBy(xpath = "(//input[contains(@class,'oxd-input')])[5]")
    WebElement employeeId;
    @FindBy(xpath = "(//input[contains(@class,'oxd-input')])[6]")
    WebElement otherId;
    @FindBy(xpath = "(//input[contains(@class,'oxd-input')])[7]")
    WebElement licenseNo;
    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[1]")
    WebElement licenseExpiry;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    WebElement nationalityDropdown;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    WebElement maritalStatusDropdown;
    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    WebElement dobField;
    @FindBy(xpath = "(//div[@class='oxd-radio-wrapper'])[1]//label")
    WebElement genderMale;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[3]")
    WebElement bloodGroupDropdown;
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[7]")
    WebElement customField;
    @FindBy(css = "div[class='orangehrm-custom-fields'] button[type='submit']")
    WebElement saveButton;

    // ✅ Actions
    public void editEmployeeDetails(String imgPath, String fName, String mName, String lName,
                                    String empId, String other, String license,
                                    String licenseExp, String nationality, String marital,
                                    String dob, String blood, String customText) {
        try {
            log.info("Starting to edit employee details...");

            wait.until(ExpectedConditions.elementToBeClickable(pencilIcon)).click();
            log.info("Clicked on pencil icon.");

            wait.until(ExpectedConditions.visibilityOf(imageIcon)).click();
            log.info("Clicked on image icon to upload photo.");

            fileInput.sendKeys(imgPath);
            log.info("Uploaded employee image from path: {}", imgPath);

            submitButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner")));
            log.info("Image upload successful.");

            wait.until(ExpectedConditions.elementToBeClickable(personalDetailsTab)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-form-loader")));
            log.info("Opened Personal Details tab.");

            clearAndType(firstName, fName);
            clearAndType(middleName, mName);
            clearAndType(lastName, lName);
            clearAndType(employeeId, empId);
            log.info("Entered employee name and ID details.");

            otherId.sendKeys(other);
            licenseNo.sendKeys(license);
            clearAndType(licenseExpiry, licenseExp);
            log.info("Entered other ID, license number, and expiry date.");

            selectFromDropdown(nationalityDropdown, nationality);
            selectFromDropdown(maritalStatusDropdown, marital);
            log.info("Selected nationality: {} and marital status: {}", nationality, marital);

            clearAndType(dobField, dob);
            genderMale.click();
            log.info("Set date of birth: {} and selected gender as Male.", dob);

            selectFromDropdown(bloodGroupDropdown, blood);
            customField.sendKeys(customText);
            log.info("Selected blood group: {} and entered custom field text.", blood);

            saveButton.click();
            log.info("Clicked Save button. Employee details update completed successfully.");

        } catch (Exception e) {
            log.error("❌ Error occurred while editing employee details: {}", e.getMessage());
            throw e;
        }
    }

    // ✅ Utility method to clear and type
    private void clearAndType(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
        log.debug("Typed text '{}' into element: {}", text, element);
    }

    // ✅ Utility method for dropdown
    private void selectFromDropdown(WebElement dropdown, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//span"));
        for (WebElement option : options) {
            if (option.getText().equals(value)) {
                option.click();
                log.debug("Selected '{}' from dropdown.", value);
                break;
            }
        }
    }
}
