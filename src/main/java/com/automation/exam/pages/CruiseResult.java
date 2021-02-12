package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CruiseResult extends WebComponent {

	public String xpath;
	private int index;
	
	//xpath:      (//section[@role="main"]//div[@class="flex-card"])[n]
	public CruiseResult(String xpath, WebDriver driver, int index) {	
		this.xpath = xpath;
		this.driver = driver;
		this.index=index;
	}
		
	public String getContinueButtonXpath() {
		return "((//section[@role=\"main\"]//div[@class=\"flex-card\"])["+index+"]//a[@class=\"btn btn-secondary btn-action select-sailing-button\"])";		
	}
	
	//public boolean hasDiscount() {}
	
	public String getCruiseName() {
		return driver.findElement(By.xpath( "(//div[@class=\"title-on-ship-image\"])["+index+"]")).getText();
	}
	
	public String getReturningDate() {
		return driver.findElement(By.xpath( "(//div[@class=\"card-content-detail sailing-dates\"])["+index+"]/span[@class=\"sailing-returning-on\"]")).getText();
	}
	
	public String getDepartingDate() {
		return driver.findElement(By.xpath( "(//div[@class=\"card-content-detail sailing-dates\"])["+index+"]/span[@class=\"sailing-departing-on\"]")).getText();
	}
	
	public String getDepartingCity() {
		return driver.findElement(By.xpath("(//div[@class=\"card-content-detail departure-city\"])["+index+"]")).getText();
	}
	
	public int getPrice() {
		String str = driver
				.findElement(By.xpath(xpath + "//span[@class=\"card-price\"]"))
				.getText() + " ";
		
		char[] chars = str.toCharArray();
		int price = 0;

	

		for (int i = 1; i < chars.length - 1; i++) {
			if (" ".contains("" + chars[i])) {
				break;
			}
			if ("0123456789".contains("" + chars[i])) {
				price = price * 10;
				price += convertToInt(chars[i]);
			}
		}

		return price;
	}
	
	public int getNoDiscountPrice() {
		
		if(!this.hasDiscount()) {
			return getPrice();
		}
		
		String str = driver
				.findElement(By.xpath(xpath+"//span[@class=\"strikeout-price-card\"]"))
				.getText() + " ";
	
		char[] chars = str.toCharArray();
		int price = 0;

	

		for (int i = 1; i < chars.length - 1; i++) {
			if (" ".contains("" + chars[i])) {
				break;
			}
			if ("0123456789".contains("" + chars[i])) {
				price = price * 10;
				price += convertToInt(chars[i]);
			}
		}

		return price;
	}
	
	public WebElement getSelectButton() {
		return driver.findElement(By.xpath(xpath+"//a[5]"));
	}
	
	public boolean hasDiscount() {
		return elementIsPresent(xpath+"//span[@class=\"strikeout-price-card\"]");
	}
	
	public int getAbsoluteDiscount() {
		return (getNoDiscountPrice()-getPrice());
	}

	public WebElement getAsWebElement() {
		return driver.findElement(By.xpath(xpath));
	}

}
