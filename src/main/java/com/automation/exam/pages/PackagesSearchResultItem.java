package com.automation.exam.pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PackagesSearchResultItem extends WebComponent {

	public int index;
	public String xpath;

	public PackagesSearchResultItem(String xpath, int index, WebDriver driver) {
		this.index = index;
		index++;
		xpath += "[" + index + "]";
		this.xpath = xpath;
		this.driver = driver;

		// System.out.println(xpath);
		// System.out.println(this.index);
		// System.out.println(getPrice());
	}

	private int convertToInt(char a) {
		return Character.getNumericValue(a);
	}

	public WebElement getPriceAsWebElement() {		
		return driver.findElement(By.xpath(xpath + "//span[@class=\"uitk-cell loyalty-display-price all-cell-shrink\"]"));
	}
	
	public String getPricexpath() {		
		return xpath + "//span[@class=\"uitk-cell loyalty-display-price all-cell-shrink\"]";
	}

	public int getPrice() {
		String str = driver
				.findElement(By.xpath(xpath + "//span[@class=\"uitk-cell loyalty-display-price all-cell-shrink\"]"))
				.getText() + " ";
		char[] chars = str.toCharArray();
		int price = 0;

		boolean a = false;

		for (int i = 1; i < chars.length - 1; i++) {
			if ("$".contains("" + chars[i])) {
				if (a)
					break;
				a = true;
			}
			if (a && "0123456789".contains("" + chars[i])) {
				price = price * 10;
				price += convertToInt(chars[i]);
			}
		}

		return price;
	}

	public WebElement getAsWebElement() {
		return driver.findElement(By.xpath(xpath));

	}

}
