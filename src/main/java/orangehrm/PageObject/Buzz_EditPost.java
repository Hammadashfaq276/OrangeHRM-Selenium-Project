package orangehrm.PageObject;

import java.time.Duration;
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

public class Buzz_EditPost {

    private static final Logger log = LogManager.getLogger(Buzz_EditPost.class);

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public Buzz_EditPost(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Buzz_EditPost page initialized successfully.");
    }

    // Locators
    @FindBy(xpath = "(//i[@class='oxd-icon bi-three-dots'])[1]")
    WebElement threeDotsIcon;

    @FindBy(xpath = "(//p[normalize-space()='Edit Post'])[1]")
    WebElement editPostOption;

    By textareaInput = By.cssSelector(
        "div.oxd-buzz-post.oxd-buzz-post--active.oxd-buzz-post--composing textarea.oxd-buzz-post-input"
    );

    @FindBy(css = "input[type='file']")
    WebElement fileUploadInput;

    @FindBy(css = "div.oxd-form-actions.orangehrm-buzz-post-modal-actions button[type='submit']")
    WebElement postButton;

    // ================== Actions ==================

    public void clickThreeDots() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(threeDotsIcon)).click();
            log.info("Clicked on the three dots icon of the first post.");
        } catch (Exception e) {
            log.error("❌ Failed to click on three dots icon: {}", e.getMessage());
            throw e;
        }
    }

    public void clickEditPost() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editPostOption)).click();
            log.info("Clicked on 'Edit Post' option.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(textareaInput));
            log.info("Edit textarea is now visible.");
        } catch (Exception e) {
            log.error("❌ Failed to click on Edit Post: {}", e.getMessage());
            throw e;
        }
    }

    public void clearAndTypeText(String text) {
        try {
            WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(textareaInput));
            textarea.click();
            textarea.sendKeys(Keys.CONTROL + "a");
            textarea.sendKeys(Keys.DELETE);
            textarea.clear();
            textarea.sendKeys(text);
            log.info("Edited post text successfully with: '{}'", text);
        } catch (Exception e) {
            log.error("❌ Failed to edit post text: {}", e.getMessage());
            throw e;
        }
    }

    public void uploadFile(String filePath) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            fileUploadInput.sendKeys(filePath);
            log.info("Uploaded file successfully: {}", filePath);
        } catch (Exception e) {
            log.error("❌ Failed to upload file: {}", e.getMessage());
            throw e;
        }
    }

    public void clickPostButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
            log.info("Clicked on Post button to save changes.");
        } catch (Exception e) {
            log.error("❌ Failed to click Post button: {}", e.getMessage());
            throw e;
        }
    }
}
