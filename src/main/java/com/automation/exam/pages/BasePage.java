package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver driver;
	private WebDriverWait wait;

	public BasePage(WebDriver pDriver) {
		wait = new WebDriverWait(pDriver, 15);
		driver = pDriver;
		PageFactory.initElements(pDriver, this);
	}

	public WebDriverWait getWait() {
		return wait;
	}

	protected WebDriver getDriver() {

		return driver;
	}
	
	protected boolean elementIsPresent(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	protected boolean elementIsPresent(WebElement element) {
		try {
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}
}
