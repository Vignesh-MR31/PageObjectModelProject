package com.web.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportClass {
	
	public static ExtentReports extentReport;
	
	public static ExtentReports generateExtentReport() {
		extentReport = new ExtentReports();
		String path = System.getProperty("user.dir")+"/test-output/ExtentReports/ExtentReport.html";
		ExtentSparkReporter extentSpark = new ExtentSparkReporter(path);
		extentSpark.config().setDocumentTitle("ExtentReport-1");
		extentSpark.config().setReportName("Test case - Result");
		extentSpark.config().setTheme(Theme.STANDARD);
		extentSpark.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		extentReport.attachReporter(extentSpark);
		
		return extentReport;
	}

}
