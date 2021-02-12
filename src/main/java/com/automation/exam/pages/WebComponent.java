package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebComponent {
	
	public WebDriver driver;
	protected boolean elementIsPresent(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	protected int convertToInt(char a) {
		return Character.getNumericValue(a);
	}
	
	protected boolean elementIsPresentById(String id) {
		try {
			driver.findElement(By.id(id));			
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

	
}
