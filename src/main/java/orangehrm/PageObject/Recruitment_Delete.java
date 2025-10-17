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

public class Recruitment_Delete {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Recruitment_Delete.class);

    // Constructor
    public Recruitment_Delete(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Recruitment_Delete page initialized successfully.");
    }

    // Locators
    @FindBy(xpath = "(//span[contains(@class,'oxd-checkbox-input')])[2]")
    private WebElement secondCheckbox;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']")
    private WebElement deleteButton;

    @FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
    private WebElement confirmDeleteButton;

    // Actions
    public void selectSecondCheckbox() {
        log.info("Selecting the second applicant checkbox for deletion.");
        secondCheckbox.click();
        log.debug("Second checkbox clicked successfully.");
    }

    public void clickDeleteButton() {
        log.info("Clicking on the Delete button.");
        deleteButton.click();
        log.debug("Delete button clicked, waiting for confirmation popup.");
    }

    public void confirmDelete() {
        log.info("Confirming deletion of selected applicant.");
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        log.info("✅ Applicant deletion confirmed successfully.");
    }
}
