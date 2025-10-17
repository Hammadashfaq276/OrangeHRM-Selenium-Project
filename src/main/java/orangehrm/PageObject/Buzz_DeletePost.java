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

public class Buzz_DeletePost {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(Buzz_DeletePost.class);

    // --- Constructor ---
    public Buzz_DeletePost(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("Buzz_DeletePost page initialized successfully.");
    }

    // --- Locators ---
    @FindBy(xpath = "(//i[@class='oxd-icon bi-three-dots'])[1]")
    private WebElement threeDotsMenu;

    @FindBy(css = "li[class='--active'] li:nth-child(1)")
    private WebElement deleteOption;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
    private WebElement confirmDeleteButton;

    // --- Action Methods ---
    public void deleteLatestPost() {
        try {
            log.info("Attempting to delete the latest post...");

            wait.until(ExpectedConditions.elementToBeClickable(threeDotsMenu)).click();
            log.info("Clicked on three dots menu of latest post.");

            wait.until(ExpectedConditions.elementToBeClickable(deleteOption)).click();
            log.info("Selected 'Delete Post' option.");

            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
            log.info("Clicked on confirm delete button.");

            log.info("✅ Latest post deleted successfully.");
        } catch (Exception e) {
            log.error("❌ Failed to delete the latest post. Error: {}", e.getMessage());
            throw e;
        }
    }
}
