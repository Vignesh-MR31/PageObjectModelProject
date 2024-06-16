package com.web.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private WebElement emailField;
	private WebElement passwordField;
	private WebElement loginButton;
	private WebElement warningMessage;
	private WebElement forgotPassword;
	
	public WebElement enterEmail() {
		emailField = driver.findElement(By.id("input-email"));
		return emailField;
	}
	
	public WebElement enterPassword() {
		passwordField = driver.findElement(By.id("input-password"));
		return passwordField;
	}
	
	public void loginButtonClick() {
		loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
		loginButton.click();
	}
	
	public WebElement warningMessage() {
		warningMessage = driver.findElement(By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"));
		return warningMessage;
	}
	
	public void forgotPasswordClick() {
		forgotPassword = driver.findElement(By.linkText("Forgotten Password"));
		forgotPassword.click();
	}
}
