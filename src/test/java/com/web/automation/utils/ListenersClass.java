package com.web.automation.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersClass implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReportClass.generateExtentReport();
		System.out.println("Test");
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" EXECUTION STARTED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" SUCESSFULLY EXECUTED");
		//extentTest.pass(result.getName()+" SUCESSFULLY EXECUTED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		LocalDateTime now = LocalDateTime.now(); // Get current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");// Define a formatter for the date-time
		String formattedTimestamp = now.format(formatter);// Format the current date-time
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		TakesScreenshot screen  = (TakesScreenshot)driver;
		File srcFile = screen.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"/test-reports/"+result.getName()+formattedTimestamp+".png";
		File desFile = new File(screenshotPath);
		try {
			FileHandler.copy(srcFile, desFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(screenshotPath);
		extentTest.log(Status.FAIL, result.getName()+" EXECUTION FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName()+" EXECUTION SKIPPED");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
