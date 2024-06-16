package com.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstName;
	
	@FindBy(id="input-lastname")
	private WebElement lastName;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirm;
	
	@FindBy(xpath="(//input[@name='newsletter'])[1]")
	private WebElement subscribeYes;
	
	@FindBy(xpath="(//input[@name='newsletter'])[2]")
	private WebElement subscribeNo;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="(//div[@class='text-danger'])[1]")
	private WebElement firstNameError;
	
	@FindBy(xpath="(//div[@class='text-danger'])[2]")
	private WebElement lastNameError;
	
	@FindBy(xpath="(//div[@class='text-danger'])[3]")
	private WebElement emailError;
	
	@FindBy(xpath="(//div[@class='text-danger'])[4]")
	private WebElement telephoneError;
	
	@FindBy(xpath="(//div[@class='text-danger'])[5]")
	private WebElement passwordError;
	
	@FindBy(xpath="//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")
	private WebElement warningMessagePrivacyPolicy;
	
	@FindBy(xpath="//div[contains(text(),'Warning: E-Mail Address is already registered!')]")
	private WebElement warningMessageEmailAlreadyRegistered;
	
	public WebElement firstNameField() {
		return firstName;
	}
	
	public WebElement lastNameField() {
		return lastName;
	}
	
	public WebElement emailField() {
		return email;
	}
	
	public WebElement telephoneField() {
		return telephone;
	}
	
	public WebElement password() {
		return password;
	}
	
	public WebElement passwordConfirmField() {
		return passwordConfirm;
	}
	
	public void subscribeRadioButton(String option) {
		if(option.equalsIgnoreCase("yes")) {
			subscribeYes.click();
		}
		else {
			subscribeNo.click();
		}
	}
	
	public void privacyPolicyCheckbox() {
		privacyPolicy.click();
	}
	
	public void continueButton() {
		continueButton.click();
	}
	
	public WebElement firstNameError() {
		return firstNameError;
	}
	
	public WebElement lastNameError() {
		return lastNameError;
	}
	
	public WebElement emailError() {
		return emailError;
	}
	
	public WebElement telephoneError() {
		return telephoneError;
	}
	
	public WebElement passwordError() {
		return passwordError;
	}
	
	public WebElement warningMessagePrivacyPolicy() {
		return warningMessagePrivacyPolicy;
	}
	
	public WebElement warningMessageEmailAlreadyRegistered() {
		return warningMessageEmailAlreadyRegistered;
	}
}
