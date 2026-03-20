package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class LoginPage {
	private ActionDriver actionDriver;
	
	// Updated locators to match newer OrangeHRM demo (name attributes)
	private By usernameFeild = By.name("username");
	private By passwordFeild = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	private By errorMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
	
	// Constructor that accepts an ActionDriver (existing usage)
	public LoginPage(ActionDriver actionDriver) {
		this.actionDriver = actionDriver;
	}

	// Convenience constructor that accepts WebDriver and creates an ActionDriver
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	// method to perform login
	public void login(String username, String password) {
		actionDriver.enterText(usernameFeild, username);
		actionDriver.enterText(passwordFeild, password);
		actionDriver.click(loginButton);
	}
	
	public String getErrorMessage() {
		return actionDriver.getText(errorMessage);
	}
	public void geterrorMessageText()
	
		{
		String errorText = actionDriver.getText(errorMessage);
		System.out.println("Error Message: " + errorText);
	}
	
	public void verifyErrorMessage(String expectedMessage) {
		String actualMessage = getErrorMessage();
		if (actualMessage.equals(expectedMessage)) {
			System.out.println("Error message verification passed.");
		} else {
			System.out.println("Error message verification failed. Expected: '" + expectedMessage + "', but got: '" + actualMessage + "'.");
		}
	}
	
}