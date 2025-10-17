package orangehrm.PageObject;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIM_DeleteAllEmployee {

	WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(PIM_DeleteAllEmployee.class);

    // ====== Constructor ======
    public PIM_DeleteAllEmployee(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("PIM_DeleteAllEmployee page initialized successfully.");
    }

    // ====== Locators ======
    @FindBy(xpath = "//span[contains(@class, 'oxd-checkbox-input')]")
    private WebElement checkbox;

    @FindBy(css = "button.oxd-button--label-danger")
    private WebElement deleteButton;

    @FindBy(css = "button.oxd-button--label-danger.orangehrm-button-margin")
    private WebElement confirmDeleteButton;

    // ====== Methods ======
    public void selectCheckboxIfNotSelected() {
        log.info("Checking if the main checkbox is already selected.");
        try {
            String checkboxClass = checkbox.getAttribute("class");
            boolean isChecked = checkboxClass.contains("oxd-checkbox-input--focus");

            if (!isChecked) {
                log.info("Main checkbox not selected. Clicking to select it.");
                checkbox.click();
                wait.until(ExpectedConditions.attributeContains(checkbox, "class", "oxd-checkbox-input--focus"));
                log.info("Main checkbox selected successfully.");
            } else {
                log.info("Main checkbox is already selected.");
            }
        } catch (Exception e) {
            log.error("❌ Failed to select main checkbox: {}", e.getMessage());
            throw e;
        }
    }

    public void clickDelete() {
        log.info("Attempting to delete all selected employees.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
            log.info("Clicked on Delete button.");

            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
            log.info("Clicked on Confirm Delete button.");

            log.info("✅ All selected employees deleted successfully.");
        } catch (Exception e) {
            log.error("❌ Failed to delete all employees: {}", e.getMessage());
            throw e;
        }
    }
	
}
