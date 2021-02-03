package com.automation.exam.pages;

import org.openqa.selenium.WebDriver;
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

	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}
}
