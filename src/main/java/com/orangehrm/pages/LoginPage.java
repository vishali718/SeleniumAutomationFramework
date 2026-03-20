package com.orangehrm.pages;

import org.openqa.selenium.By;

import com.orangehrm.actiondriver.ActionDriver;

public class LoginPage {
	private ActionDriver actionDriver;
	
	private By usernameFeild = By.id("txtUsername");
	private By passwordFeild = By.id("input[name='txtPassword']");
	private By loginButton = By.id("btnLogin");
	private By errorMessage = By.xpath("//span[@id='spanMessage']");
	
	public LoginPage(ActionDriver actionDriver) {
		this.actionDriver = actionDriver;
	}
	
	// method to perform login
	public void login(String username, String password) {
		actionDriver.enterText(usernameFeild, "admin");
		actionDriver.enterText(passwordFeild, "admin123");
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
