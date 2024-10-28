package com.web.automation.testcases;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.automation.basepages.InitiateDriver;
import com.web.automation.pages.HomePage;
import com.web.automation.pages.RegisterPage;
import com.web.automation.utils.DataProviderClass;

public class RegisterPageTestCases extends InitiateDriver{
	HomePage homePage = null;
	RegisterPage registerPage = null;
	
	@Test(priority=1)
	public void navigateToCustomerRegisterPage() {
		homePage = new HomePage(driver);
		homePage.myAccountClick().registerClick();
		String registerTitle = driver.getTitle();
		Assert.assertEquals(registerTitle, "Register Account", "Not a Register Account Page!");
	}
	
	@Test(priority=2,dataProvider="dataProvider",dataProviderClass=DataProviderClass.class,enabled=true)
	public void registerAnAccount(String firstName, String lastName, String email, String telephone, String password, String passwordConfirm, String subscribe){
		homePage = new HomePage(driver);
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
		homePage = new HomePage(driver);
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
	
	@Test(priority=4,dataProvider="dataProvider",dataProviderClass=DataProviderClass.class)
	public void registerWithAlreadyRegisteredEmail(String firstName, String lastName, String email, String telephone, String password, String passwordConfirm, String subscribe) {
		homePage = new HomePage(driver);
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
	 
	
}
