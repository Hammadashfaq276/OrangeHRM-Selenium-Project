package orangehrm.PageObject;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	protected static WebDriver driver;
	
	 public static void InitializeDriver() {
		 WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    }

    // Open application URL (fixed URL)
    public static void openUrl() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // Maximize window (optional, can be called separately)
    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Quit browser
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    
    
    }
}
