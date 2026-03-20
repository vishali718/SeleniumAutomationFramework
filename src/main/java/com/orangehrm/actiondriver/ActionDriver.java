package com.orangehrm.actiondriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) throws NumberFormatException {
		this.driver = driver;
		int explicitWait = 30; // default fallback
		try {
			String s = BaseClass.getProp().getProperty("explicitWait");
			if (s != null && !s.trim().isEmpty()) {
				explicitWait = Integer.parseInt(s.trim());
			}
		} catch (Exception e) {
			// Log and continue with default
			System.out.println("Could not read explicitWait from properties, using default 30s: " + e.getMessage());
		}
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(explicitWait));
	}

	public void waitForElementtobeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element not clickable: " + e.getMessage());
			throw e; // rethrow to fail the test
		}
	}

	/**
	 * Wait until the element located by the given locator is visible (default
	 * timeout from constructor).
	 */
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element not visible: " + e.getMessage());
			throw e;
		}
	}

	public void click(By by) {
		try {
			waitForElementtobeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Failed to click element: " + e.getMessage());
			throw e;
		}
	}
	public void WaitForPageLoad(int timeoutInSeconds) {
		try {
			wait.withTimeout(java.time.Duration.ofSeconds(timeoutInSeconds)).until(
					webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		}
		catch (Exception e) {
			System.out.println("Page did not load within the expected time: " + e.getMessage());
			throw e ;
		}
	}
	

	public void enterText(By by, String text) {
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.println("Failed to enter text: " + e.getMessage());
			throw e;
		}
	}
	

	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Failed to get text: " + e.getMessage());
			return " ";
		}
	}

	public void compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				System.out.println("Text matches: " + actualText);
			} else {
				System.out.println(
					"Text does not match! Actual: " + actualText + "not equals, Expected: " + expectedText);
			}
		} catch (Exception e) {
			System.out.println("Failed to compare text: " + e.getMessage());
			throw e;
		}
	}

	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("Element not displayed: " + e.getMessage());
			return false;
		}
	}


	public void scrolltoElement(By by) {
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Failed to scroll to element: " + e.getMessage());
			throw e;
		}
	}

	public boolean isElementVisible(By adminTab) {
		return isDisplayed(adminTab);
	}

	public boolean isElementVisible1(By logo) {
		return isDisplayed(logo);
	}
}
