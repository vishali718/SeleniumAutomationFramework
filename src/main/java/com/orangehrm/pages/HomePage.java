package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;

public class HomePage {
 private ActionDriver actionDriver;
  private By AdminTab = By.id("menu_admin_viewAdminModule");
  private By PIMTab = By.id("menu_pim_viewPimModule");
  private By LeaveTab = By.id("menu_leave_viewLeaveModule");
  
  public HomePage(ActionDriver actionDriver) {
	  this.actionDriver = actionDriver;
  }

  // Convenience constructor that accepts WebDriver
  public HomePage(WebDriver driver) {
      this.actionDriver = new ActionDriver(driver);
  }
  
  public void isadminTabVisible() {
	  actionDriver.isElementVisible(AdminTab);
  }
  
  public boolean verifyOrangeHRMLogo()
  {
	  By logo = By.xpath("//div[@id='branding']/a/img");
	  return actionDriver.isElementVisible(logo);
  }
 
  public void logout() {
	  By welcomeMenu = By.id("welcome");
	  By logoutLink = By.linkText("Logout");
	  
	  actionDriver.click(welcomeMenu);
	  actionDriver.click(logoutLink);
  }
}