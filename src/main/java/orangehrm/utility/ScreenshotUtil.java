package orangehrm.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + "_" + timeStamp + ".png";
        try {
            File target = new File(dest);
            target.getParentFile().mkdirs();
            FileUtils.copyFile(src, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
