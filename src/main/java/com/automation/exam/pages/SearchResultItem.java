package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultItem{

	public WebElement item;
	public WebElement selectButton;
	
	public SearchResultItem (String xpath,WebDriver driver) {
		item = driver.findElement(By.xpath(xpath));		
		selectButton=driver.findElement(By.xpath(xpath+"//button[@data-test-id=\"select-button\"]"));
		
	}
	
	public boolean verifySelectButtonPresent() {
		
	return false;	
	}
}
