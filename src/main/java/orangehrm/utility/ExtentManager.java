package orangehrm.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	
	 public static synchronized ExtentReports getInstance() {
	        if (extent == null) {
	            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
	            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	            spark.config().setDocumentTitle("OrangeHRM Automation Report");
	            spark.config().setReportName("Automation Test Results");
	            spark.config().setTheme(Theme.STANDARD);

	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	            extent.setSystemInfo("Project", "OrangeHRM Web Automation");
	            extent.setSystemInfo("Tester", "Hammad Ashfaq");
	            extent.setSystemInfo("Environment", "QA");
	        }
	        return extent;
	    }
}
