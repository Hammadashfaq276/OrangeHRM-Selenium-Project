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

public class PIM_AddEmployee {

	 WebDriver driver;
	    WebDriverWait wait;
	    private static final Logger log = LogManager.getLogger(PIM_AddEmployee.class);

	    // Constructor
	    public PIM_AddEmployee(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        log.info("PIM_AddEmployee Page Initialized Successfully.");
	    }

	    // Locators
	    @FindBy(xpath = "//button[contains(., 'Add')]")
	    WebElement addButton;

	    @FindBy(css = "input[type='file']")
	    WebElement fileInput;

	    @FindBy(name = "firstName")
	    WebElement firstName;

	    @FindBy(name = "middleName")
	    WebElement middleName;

	    @FindBy(name = "lastName")
	    WebElement lastName;

	    @FindBy(css = "span.oxd-switch-input.oxd-switch-input--active")
	    WebElement loginToggle;

	    @FindBy(xpath = "(//input[contains(@class, 'oxd-input') and contains(@class, 'oxd-input--active')])[6]")
	    WebElement username;

	    @FindBy(xpath = "//label[contains(normalize-space(), 'Enabled')]/span")
	    WebElement enabledStatus;

	    @FindBy(xpath = "(//input[@type='password' and contains(@class, 'oxd-input')])[1]")
	    WebElement password;

	    @FindBy(xpath = "(//input[@type='password' and contains(@class, 'oxd-input')])[2]")
	    WebElement confirmPassword;

	    @FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	    WebElement saveButton;

	    @FindBy(css = "div.oxd-loading-spinner")
	    WebElement loader;

	    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
	    WebElement employeeId;

	    @FindBy(xpath = "//label[text()=\"Driver's License Number\"]/following::input[1]")
	    WebElement licenseNumber;

	    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[1]")
	    WebElement licenseExpiry;

	    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
	    WebElement nationalityDropdown;

	    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
	    WebElement maritalStatusDropdown;

	    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
	    WebElement dobField;

	    @FindBy(xpath = "(//div[@class='oxd-radio-wrapper'])[1]")
	    WebElement genderMale;

	    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[3]")
	    WebElement bloodTypeDropdown;

	    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[7]")
	    WebElement customField;

	    @FindBy(css = "div[class='orangehrm-custom-fields'] button[type='submit']")
	    WebElement finalSubmit;

	    // ------------------ Methods ---------------------
	    public void addEmployee(String filePath, String fName, String mName, String lName,
	                            String uname, String pwd, String empIdVal, String licenseVal, 
	                            String licenseExp, String nationality, String maritalStatus,
	                            String dob, String bloodGroup, String customVal) {

	        log.info("Starting Add Employee Process...");

	        addButton.click();
	        log.info("Clicked on Add button.");

	        fileInput.sendKeys(filePath);
	        log.info("Uploaded file: {}", filePath);

	        firstName.sendKeys(fName);
	        middleName.sendKeys(mName);
	        lastName.sendKeys(lName);
	        log.info("Entered Employee Name: {} {} {}", fName, mName, lName);

	        loginToggle.click();
	        log.info("Toggled Login Access ON.");

	        username.sendKeys(uname);
	        enabledStatus.click();
	        password.sendKeys(pwd);
	        confirmPassword.sendKeys(pwd);
	        log.info("Entered login credentials: Username={}, Password=****", uname);

	        saveButton.click();
	        log.info("Clicked Save button for basic details.");

	        // Wait for loader to disappear
	        wait.until(ExpectedConditions.invisibilityOf(loader));
	        log.info("Loader disappeared — proceeding with personal details.");

	        wait.until(ExpectedConditions.visibilityOf(employeeId)).sendKeys(empIdVal);
	        licenseNumber.sendKeys(licenseVal);
	        licenseExpiry.sendKeys(licenseExp);
	        log.info("Entered Employee ID: {}, License: {}, Expiry: {}", empIdVal, licenseVal, licenseExp);

	        selectFromDropdown(nationalityDropdown, nationality);
	        selectFromDropdown(maritalStatusDropdown, maritalStatus);
	        log.info("Selected Nationality: {} and Marital Status: {}", nationality, maritalStatus);

	        dobField.sendKeys(dob, Keys.TAB);
	        genderMale.click();
	        log.info("Entered DOB: {} and selected Gender: Male", dob);

	        selectFromDropdown(bloodTypeDropdown, bloodGroup);
	        customField.sendKeys(customVal);
	        log.info("Selected Blood Group: {} and entered Custom Field: {}", bloodGroup, customVal);

	        finalSubmit.click();
	        log.info("Clicked Final Submit. ✅ Employee added successfully.");
	    }

	    private void selectFromDropdown(WebElement dropdown, String value) {
	        dropdown.click();
	        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//span"));
	        wait.until(ExpectedConditions.visibilityOfAllElements(options));

	        for (WebElement option : options) {
	            if (option.getText().equals(value)) {
	                option.click();
	                log.info("Selected option from dropdown: {}", value);
	                break;
	            }
	        }
	    }
	
	
}
