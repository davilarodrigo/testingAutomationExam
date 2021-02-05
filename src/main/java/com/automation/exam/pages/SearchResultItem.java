package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultItem extends WebComponent {

	public WebElement item;
	public WebElement selectButton;
	public WebElement flightDuration;
	public WebElement detailsAndFees;
	
	private String xpath;
	private int index;

	public SearchResultItem(String xpath, int index, WebDriver driver) {

		xpath += "[" + index + "]";
		this.item = driver.findElement(By.xpath(xpath));
		this.index = index;
		this.xpath = xpath;
		this.driver = driver;
		
		
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
	
	public boolean hasDetailsAndFees() {
		String xpath1="//a[@data-test-id=\"flight-details-link\"]";
		
		if (elementIsPresent(xpath+xpath1)) {
			detailsAndFees=driver.findElement(By.xpath(xpath+xpath1));
			return true;
		}		
		return false;
	}
	
	public WebElement getDetailsAndFees() {
		if (hasDetailsAndFees()) {
			return detailsAndFees;
		}
		return null;
	}
	
	public void clickDetailsAndFees() {
		if (hasDetailsAndFees()) {			
			detailsAndFees.click();
		}
	}
	
	

	public boolean hasFlightDuration() {

		String xpath1= "//div[@data-test-id=\"journey-duration\"]";
		String xpath2="//span[@data-test-id=\"duration\"]";
				
		if (elementIsPresent(xpath+xpath1)) {
			flightDuration=driver.findElement(By.xpath(xpath+xpath1));
			return true;
		}
		if (elementIsPresent(xpath+xpath2)) {
			flightDuration=driver.findElement(By.xpath(xpath+xpath2));
			return true;
		}
		return false;
	}

	public WebElement getFlightDuration() {
		
		if(hasFlightDuration()) return flightDuration;
		return null;		
	}

}
