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

public class Buzz_AddPost {

    private static final Logger log = LogManager.getLogger(Buzz_AddPost.class);

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Constructor ---
    public Buzz_AddPost(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        log.info("Buzz_AddPost page initialized successfully.");
    }

    // --- PageFactory Locators ---
    @FindBy(css = "div[class='orangehrm-buzz-create-post-actions'] button:nth-child(1)")
    private WebElement createPostButton;

    @FindBy(css = "div[class='orangehrm-buzz-post-modal-header-text'] textarea[placeholder='What\\'s on your mind?']")
    private WebElement postTextArea;

    @FindBy(css = "input[type='file']")
    private WebElement fileInput;

    @FindBy(css = "div[class='oxd-form-actions orangehrm-buzz-post-modal-actions'] button:nth-child(1)")
    private WebElement postButton;

    @FindBy(xpath = "(//*[name()='path'][@id='heart'])[1]")
    private WebElement likeButton;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-chat-text-fill'])[1]")
    private WebElement commentIcon;

    @FindBy(css = "input[placeholder='Write your comment...']")
    private WebElement commentInput;

    // --- Actions ---
    public void createPost(String message, String filePath) {
        try {
            log.info("Attempting to create a new post with message: '{}' and file: '{}'", message, filePath);

            int postCountBefore = driver.findElements(By.cssSelector("div.orangehrm-buzz-post-body")).size();
            log.debug("Current number of posts before creating: {}", postCountBefore);

            createPostButton.click();
            log.info("Clicked 'Create Post' button.");

            postTextArea.sendKeys(message);
            log.info("Entered post message.");

            fileInput.sendKeys(filePath);
            log.info("Attached file: {}", filePath);

            postButton.click();
            log.info("Clicked on Post button.");

            wait.until(driver -> driver.findElements(By.cssSelector("div.orangehrm-buzz-post-body")).size() > postCountBefore);
            log.info("✅ Post created successfully and new post is visible.");
        } catch (Exception e) {
            log.error("❌ Failed to create post: {}", e.getMessage());
            throw e;
        }
    }

    public void likeLatestPost() {
        try {
            log.info("Attempting to like the latest post...");
            WebElement latestLike = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//*[name()='path'][@id='heart'])[1]")
            ));
            latestLike.click();
            log.info("✅ Latest post liked successfully.");
        } catch (Exception e) {
            log.error("❌ Failed to like the latest post: {}", e.getMessage());
            throw e;
        }
    }

    public void commentOnLatestPost(String comment) {
        try {
            log.info("Attempting to comment on the latest post with text: '{}'", comment);

            WebElement latestCommentIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//i[@class='oxd-icon bi-chat-text-fill'])[1]")
            ));
            latestCommentIcon.click();
            log.info("Clicked on comment icon.");

            wait.until(ExpectedConditions.visibilityOf(commentInput));
            commentInput.sendKeys(comment + Keys.ENTER);
            log.info("✅ Comment posted successfully: '{}'", comment);
        } catch (Exception e) {
            log.error("❌ Failed to comment on post: {}", e.getMessage());
            throw e;
        }
    }
}
