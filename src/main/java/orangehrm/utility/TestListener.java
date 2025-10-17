package orangehrm.utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
 
	ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        Object currentClass = result.getInstance();
        try {
            // Reflection: expect a public WebDriver field named 'driver' in test class or base class
            java.lang.reflect.Field driverField = currentClass.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(currentClass);
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (NoSuchFieldException nsf) {
            // try in superclass (if your driver is in BaseTest)
            try {
                java.lang.reflect.Field driverField = currentClass.getClass().getSuperclass().getDeclaredField("driver");
                driverField.setAccessible(true);
                WebDriver driver = (WebDriver) driverField.get(currentClass);
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) { test.get().log(Status.SKIP, "Test skipped"); }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    public void onStart(ITestContext context) {}
    public void onFinish(ITestContext context) {
        extent.flush();
    }
	
}
