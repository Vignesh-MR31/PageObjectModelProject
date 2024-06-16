package com.web.automation.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.automation.pages.HomePage;
import com.web.automation.pages.RegisterPage;

public class RegisterPageTestCases {
	WebDriver driver = null;
	HomePage homePage = null;
	DataFormatter formatter;
	RegisterPage registerPage = null;
	
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
		
		homePage = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void navigateToCustomerRegisterPage() {
		homePage.myAccountClick().registerClick();
		String registerTitle = driver.getTitle();
		Assert.assertEquals(registerTitle, "Register Account", "Not a Register Account Page!");
	}
	
	@Test(priority=2,dataProvider="Data",enabled=false)
	public void registerAnAccount(String firstName, String lastName, String email, String telephone, String password, String passwordConfirm, String subscribe) {
		homePage.myAccountClick().registerClick();
		registerPage = new RegisterPage(driver);
		registerPage.firstNameField().sendKeys(firstName);
		registerPage.lastNameField().sendKeys(lastName);
		registerPage.emailField().sendKeys(email);
		registerPage.telephoneField().sendKeys(telephone);
		registerPage.password().sendKeys(password);
		registerPage.passwordConfirmField().sendKeys(passwordConfirm);
		registerPage.subscribeRadioButton(subscribe);
		registerPage.privacyPolicyCheckbox();
		registerPage.continueButton();
		String yourAccountRegistered = driver.getTitle();
		Assert.assertEquals(yourAccountRegistered, "Your Account Has Been Created!", "account is not registered");
	}
	
	@Test(priority=3)
	public void invalidRegisterAccount() {
		homePage.myAccountClick().registerClick();
		registerPage = new RegisterPage(driver);
		registerPage.continueButton();
		Assert.assertEquals(registerPage.warningMessagePrivacyPolicy().getText(), "Warning: You must agree to the Privacy Policy!", "No Error Message dislayed");
		Assert.assertEquals(registerPage.firstNameError().getText(), "First Name must be between 1 and 32 characters!", "No Error Message dislayed");
		Assert.assertEquals(registerPage.lastNameError().getText(), "Last Name must be between 1 and 32 characters!", "No Error Message dislayed");
		Assert.assertEquals(registerPage.emailError().getText(), "E-Mail Address does not appear to be valid!", "No Error Message dislayed");
		Assert.assertEquals(registerPage.telephoneError().getText(), "Telephone must be between 3 and 32 characters!", "No Error Message dislayed");
		Assert.assertEquals(registerPage.passwordError().getText(), "Password must be between 4 and 20 characters!", "No Error Message dislayed");	
	}
	
	@Test(priority=4,dataProvider="Data")
	public void registerWithAlreadyRegisteredEmail(String firstName, String lastName, String email, String telephone, String password, String passwordConfirm, String subscribe) {
		homePage.myAccountClick().registerClick();
		registerPage = new RegisterPage(driver);
		registerPage.firstNameField().sendKeys(firstName);
		registerPage.lastNameField().sendKeys(lastName);
		registerPage.emailField().sendKeys(email);
		registerPage.telephoneField().sendKeys(telephone);
		registerPage.password().sendKeys(password);
		registerPage.passwordConfirmField().sendKeys(passwordConfirm);
		registerPage.subscribeRadioButton(subscribe);
		registerPage.privacyPolicyCheckbox();
		registerPage.continueButton();
		Assert.assertEquals(registerPage.warningMessageEmailAlreadyRegistered().getText(), "Warning: E-Mail Address is already registered!", "No Error Message dislayed");
	}
	
	@DataProvider(name="Data")
	public Object[][] dataProvider(Method met) throws IOException {
		int rows;
		int columns;
		Object[][] credentials = null;
		if(met.getName().equalsIgnoreCase("registerAnAccount")|| met.getName().equalsIgnoreCase("registerWithAlreadyRegisteredEmail")) {
			FileInputStream fileInput = new FileInputStream("./test-data/Users-Data.xlsx");
			XSSFWorkbook workBook = new XSSFWorkbook(fileInput);
			XSSFSheet sheet = workBook.getSheet("Register-Account");
			rows = sheet.getLastRowNum();
			columns = sheet.getRow(rows).getLastCellNum();
			credentials = new Object[rows][columns];
			for(int i=0;i<rows;i++) {
				XSSFRow row = sheet.getRow(i+1);
				for(int j=0;j<columns;j++) {
					XSSFCell cell = row.getCell(j);
					formatter = new DataFormatter();
					credentials[i][j] = formatter.formatCellValue(cell);
				}
			}
			return credentials;
		}
		else {
			return null;
		}
	}
	
	@AfterMethod
	public void closeBrowser() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
}
