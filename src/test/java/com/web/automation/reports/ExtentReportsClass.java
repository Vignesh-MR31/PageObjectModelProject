package com.web.automation.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsClass {

	public static void main(String[] args) {
		ExtentReports extentReport = new ExtentReports();
		String folderPath = System.getProperty("user.dir")+"\\test-reports\\ExtentReport.html";
		File file = new File(folderPath);
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		extentReport.attachReporter(spark);
		
		ExtentTest testOne = extentReport.createTest("Testing");
		testOne.log(Status.INFO, "First Test running");
		testOne.pass("Test Passed");
		ExtentTest testTwo = extentReport.createTest("Testing2");
		testTwo.log(Status.FAIL, "Second Test running");
		testTwo.fail("Test Failed");
		ExtentTest testThree = extentReport.createTest("Testing23");
		testThree.skip("Test skipped");
		extentReport.flush();

		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
