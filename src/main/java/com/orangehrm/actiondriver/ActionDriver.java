package com.orangehrm.actiondriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
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

	public void enterText(By by, String text) {
		try {
			waitForElementToBeVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(text);
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
			return "";
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
			boolean displayed = driver.findElement(by).isDisplayed();
			if (displayed) {
				System.out.println("Element is displayed.");
			} else {
				System.out.println("Element is not displayed.");
			}
		} catch (Exception e) {
			System.out.println("Failed to check if element is displayed: " + e.getMessage());
		}
		return false;
	}
}
