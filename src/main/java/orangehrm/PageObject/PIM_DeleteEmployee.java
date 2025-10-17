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

public class PIM_DeleteEmployee {

	 WebDriver driver;
	    WebDriverWait wait;
	    private static final Logger log = LogManager.getLogger(PIM_DeleteEmployee.class);

	    // ✅ Constructor
	    public PIM_DeleteEmployee(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        log.info("PIM_DeleteEmployee page initialized successfully.");
	    }

	    // ✅ Locators
	    @FindBy(xpath = "(//i[contains(@class,'bi-trash')]/parent::button)[1]")
	    WebElement deleteButton;

	    @FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	    WebElement confirmDeleteButton;

	    // ✅ Actions
	    public void deleteFirstEmployee() {
	        log.info("Attempting to delete the first employee record.");
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
	            log.info("Clicked on delete icon for the first employee.");

	            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
	            log.info("Confirmed employee deletion by clicking the delete confirmation button.");

	            log.info("✅ Employee record deleted successfully.");
	        } catch (Exception e) {
	            log.error("❌ Failed to delete employee record: {}", e.getMessage());
	            throw e;
	        }
	    }
	
}
