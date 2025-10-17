package orangehrm.PageObject;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
 
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 public LoginPage(WebDriver driver)
	 {
		 this.driver=driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 PageFactory.initElements(driver, this);
	 }
	 
	 // WebElements
	 
	 @FindBy(name="username")
	 private WebElement usernameField;
	 
	 @FindBy(name="password")
	 private WebElement passwordField;
	 
	 @FindBy(className="orangehrm-login-button")
	 private WebElement loginButton;
	 
	// Page Factory locators
	    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	    private WebElement userDropdown;

	    @FindBy(css = "a.oxd-userdropdown-link[href='/web/index.php/auth/logout']")
	    private WebElement logoutLink;
	    
	    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]")
	    private WebElement toastMessage;
	    
	    
	 
	
	 // Actions / Methods
	
	 public void enterUsername(String username)
	 {
		 usernameField.sendKeys(username);
	 }
	 
	    public void enterPassword(String password) {
	        passwordField.sendKeys(password);
	    }

	
	    
	    public void clickLogin()
	    {
	    	loginButton.click();
	    }
	    
	    public String getPageTitle() 
	    {
	    	return driver.getTitle();
	    }
	 
	 // Generic method for menu click
	    public void clickMenu(String menuName) {
	        driver.findElement(By.linkText(menuName)).click();
	    }
	 
	    public void clickUserDropdown() {
	        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
	    }

	    public void clickLogout() {
	        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
	    }

	    // Reusable logout method
	    public void logout() {
	        clickUserDropdown();
	        clickLogout();
	    }
	    
	    // -------- Toast Handling Method --------
	    // Wait + clean toast message
	    public String getToastMessage() {
	        WebElement toast = new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(ExpectedConditions.visibilityOf(toastMessage));
	        String message = toast.getText();
	        return message.replace("×", "").replaceAll("\\s+", " ").trim();
	    }

	    // Assertion method
	    public void verifyToastMessage(String expectedMessage) {
	        String actualMessage = getToastMessage();
	        System.out.println("Toast Message: " + actualMessage);
	        Assert.assertEquals(actualMessage, expectedMessage);
	    }
		public boolean isLoginSuccessful() {
			try {
		        return userDropdown.isDisplayed();
		    } catch (Exception e) {
		        return false;
		    }
		}
	
}
