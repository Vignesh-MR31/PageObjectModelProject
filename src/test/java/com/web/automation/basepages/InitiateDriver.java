package com.web.automation.basepages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class InitiateDriver {
	protected WebDriver driver;

	@BeforeMethod
	public void startBrowser() throws IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./Properties/Application.properties")));
		
		String browser = prop.getProperty("Browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("Implicity_wait"))));
		driver.get(prop.getProperty("URL"));
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
