package orangehrm.PageObject;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Admin_DeleteUser {

    private static final Logger log = LogManager.getLogger(Admin_DeleteUser.class);

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public Admin_DeleteUser(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        log.info("Admin_DeleteUser page initialized successfully.");
    }

    // --- Locators ---
    @FindBy(xpath = "(//button[@type='button' and .//i[contains(@class,'bi-trash')]])[3]")
    private WebElement thirdTrashButton;

    @FindBy(css = "button.oxd-button--label-danger")
    private WebElement confirmDeleteButton;

    // --- Actions ---

    public void clickThirdTrashButton() {
        log.info("Clicking the 3rd trash (delete) button...");
        wait.until(ExpectedConditions.elementToBeClickable(thirdTrashButton)).click();
        log.info("Clicked the 3rd trash button successfully.");
    }

    public void confirmDelete() {
        log.info("Confirming the delete action...");
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        log.info("Delete confirmed successfully.");
    }

    // --- Combined flow (helper method) ---
    public void deleteThirdRecord() {
        log.info("=== Starting delete process for 3rd record ===");
        clickThirdTrashButton();
        confirmDelete();
        log.info("✅ 3rd record deleted successfully.");
    }
}
