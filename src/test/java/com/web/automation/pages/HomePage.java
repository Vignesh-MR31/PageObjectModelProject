package com.web.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver driver = null;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	private WebElement myAccount;
	private WebElement loginIn;
	private WebElement register;
	
	public HomePage myAccountClick() {
		myAccount = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
		myAccount.click();
		return new HomePage(driver);
	}
	
	public void loginInClick() {
		loginIn = driver.findElement(By.linkText("Login"));
		loginIn.click();
	}
	
	public void registerClick() {
		register = driver.findElement(By.linkText("Register"));
		register.click();
	}
}
