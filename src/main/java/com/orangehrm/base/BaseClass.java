package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;

	// Ensure properties are loaded once before any tests run
	@BeforeSuite
	public void loadconfig() {
		prop = new Properties();
		try (FileInputStream ip = new FileInputStream("src/main/resources/config.properties")) {
			prop.load(ip);
		} catch (IOException e) {
			// Fail fast with a clear message so TestNG marks configuration as failed
			throw new RuntimeException("Unable to load config.properties", e);
		}
	}

	@BeforeMethod
	public void setup() {
		System.out.println("setting up webdriver for :" + this.getClass().getSimpleName());
		// Defensive: ensure properties are loaded (in case someone ran setup directly)
		if (prop == null) {
			loadconfig();
		}
		launchbrowser();
		configureBrowser();
		staticWait(5);
		//
	}

	private void launchbrowser() {
		String browser = prop.getProperty("browser");
		if (browser == null) {
			throw new RuntimeException("'browser' property not set in config.properties");
		}
		browser = browser.trim();

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Browser not supported: " + browser);
		}
	}

	//
	private void configureBrowser() {
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait").trim());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		driver.manage().window().maximize();

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Unable to launch url: " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}