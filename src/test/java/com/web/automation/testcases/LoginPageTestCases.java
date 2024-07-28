package com.web.automation.testcases;

import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.automation.basepages.DataProviderClass;
import com.web.automation.basepages.InitiateDriver;
import com.web.automation.pages.HomePage;
import com.web.automation.pages.LoginPage;

public class LoginPageTestCases extends InitiateDriver{

	HomePage homePage = null;
	DataFormatter formatter;
	LoginPage loginPage = null;
	
	@Test(priority=1)
	public void navigateToCustomerLoginPage() {
		homePage = new HomePage(driver);
		homePage.myAccountClick().loginInClick();
		String loginTitle = driver.getTitle();
		Assert.assertEquals(loginTitle, "Account Login", "Not a Login Page!");
	}
	
	@Test(priority=2,dataProvider="Data")
	public void loginWithValidCredentials(String email, String password) {
		homePage = new HomePage(driver);
		homePage.myAccountClick().loginInClick();
		loginPage = new LoginPage(driver);
		loginPage.enterEmail().sendKeys(email);
		loginPage.enterPassword().sendKeys(password);
		loginPage.loginButtonClick();
		String myAccountPage = driver.getTitle();
		Assert.assertEquals(myAccountPage, "My Account", "Login failed");
	}
	
	@Test(priority=3,dataProvider="Data")
	public void loginWithInValidCredentials(String email, String password) {
		homePage = new HomePage(driver);
		homePage.myAccountClick().loginInClick();
		loginPage = new LoginPage(driver);
		loginPage.enterEmail().sendKeys(email);
		loginPage.enterPassword().sendKeys(password);
		loginPage.loginButtonClick();
		String pageName = driver.getTitle();
		Assert.assertEquals(pageName, "Account Login", "Logged In Successfully");
	}
	
	@Test(priority=4)
	public void loginWithEmptyCredentials() {
		homePage = new HomePage(driver);
		homePage.myAccountClick().loginInClick();
		loginPage = new LoginPage(driver);
		loginPage.loginButtonClick();
		String errorMessage = loginPage.warningMessage().getText();
		Assert.assertEquals(errorMessage, "Warning: No match for E-Mail Address and/or Password.", "Logged In Successfully");
	}
	
	@Test(priority=5)
	public void forgotPasswordWithValidEmail() {
		homePage = new HomePage(driver);
		homePage.myAccountClick().loginInClick();
		loginPage = new LoginPage(driver);
		loginPage.forgotPasswordClick();
		String forgotPasswordPage = driver.getTitle();
		Assert.assertEquals(forgotPasswordPage, "Forgot Your Password?", "Forgot Your Password is not displayed");
	}
	
	@DataProvider(name="Data")
	public Object[][] dataProvider(Method met) throws Exception {
		Object[][] credentials = null;
		if(met.getName().equalsIgnoreCase("loginWithValidCredentials")) {
			credentials = DataProviderClass.dataProviderMethod("Valid-Users");
		}
		else if(met.getName().equalsIgnoreCase("loginWithInValidCredentials")) {
			credentials = DataProviderClass.dataProviderMethod("Invalid-Users");
		}
		return credentials;
	}
}
