package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends WebComponent {

	private WebDriverWait wait;
	public boolean printDetails = false;

	protected void printDetail(String string) {
		if (printDetails) {
			System.out.println(string);
		}
	}

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

	protected WebElement findById(String id) {
		return driver.findElement(By.xpath("*//[@id=\"" + id + "\"]"));
	}

	protected WebElement findByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	protected WebElement findAndClick(String xpath) {
		WebElement webElement = driver.findElement(By.xpath(xpath));
		getWait().until(ExpectedConditions.visibilityOf(webElement));
		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
		return webElement;
	}

	protected void tryToClick(String xpath) {
		try {
			findAndClick(xpath);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void tryToClick(String xpath, String msg) {
		try {
			findAndClick(xpath);
			printDetail(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void waitAndClick(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void printCurrentTab() {
		System.out.println("current tab title: " + driver.getTitle());
	}

	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}
}
