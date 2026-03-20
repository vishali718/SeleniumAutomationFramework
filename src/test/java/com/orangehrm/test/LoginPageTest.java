package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		// initialize page objects using the test's WebDriver instance
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
@Test
	public void verifyValidLogin()
	{
		loginPage.login("Admin", "admin123");
		Assert.assertTrue(homePage.verifyOrangeHRMLogo(), "Login failed or logo not visible");
		homePage.logout();
	    staticWait(3);
	}
}