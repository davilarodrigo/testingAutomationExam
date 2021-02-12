package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CruiseResult extends WebComponent {
	public int index;
	public String xpath;
	
	//xpath:      (//section[@role="main"]//div[@class="flex-card"])[n]
	public CruiseResult(String xpath, WebDriver driver) {	
		this.xpath = xpath;
		this.driver = driver;
	}
		
	//public boolean hasDiscount() {}
	
	private int convertToInt(char a) {
		return Character.getNumericValue(a);
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
