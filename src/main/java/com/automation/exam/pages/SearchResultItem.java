package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultItem {

	public WebElement item;
	public WebElement selectButton;
	private WebDriver driver;
	private String xpath;
	private int index;

	public SearchResultItem(String xpath, int index, WebDriver driver) {

		xpath += "[" + index + "]";
		this.index = index;
		item = driver.findElement(By.xpath(xpath));
		this.xpath = xpath;
	}
	
	public int getIndex() {
		return index;
	}

	public String getXpath() {
		return xpath;
	}

	public WebElement getAsWebElement() {
		return item;
	}

	public String getSelectButtonXpath() {
		return xpath + "//button[@data-test-id=\"select-button\"]";
	}

	public WebElement getSelectButton() {
		selectButton = driver.findElement(By.xpath(xpath + "//button[@data-test-id=\"select-button\"]"));
		return selectButton;
	}

	public boolean verifySelectButtonPresent() {

		return false;
	}
}
