package vinayDammala.libraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestReportsManager {
	
	public static ExtentReports getReportObject() {
		
		String reportPath = System.getProperty("user.dir")+"\\Reports\\report.html";
		
		ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
		
		report.config().setReportName("Web Automation Test Results");
		
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(report);
		
		extent.setSystemInfo("Tester", "Vinay Dammala");
		
		return extent;
		
	}

}
