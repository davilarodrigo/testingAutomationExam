package com.automation.exam.pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PackagesSearchResultItem extends WebComponent {

	public int index;
	public String xpath;
	
	public boolean isSponsored() {
		return elementIsPresent(xpath+"//span[@class=\"uitk-badge uitk-badge-small uitk-badge-sponsored uitk-badge-has-text all-t-margin-two\"]");
	}

	//*[@data-stid="section-results"]//ol//li[4]
	public PackagesSearchResultItem(String xpath, WebDriver driver) {
		//this.index = index;		
		
		this.xpath = xpath;
		this.driver = driver;

		// System.out.println(xpath);
		// System.out.println(this.index);
		// System.out.println(getPrice());
	}
		
	public String getName() {
		String str = driver.findElement(By.xpath(xpath+"//*[@data-stid=\"content-hotel-title\"]")).getText();
		return str;
	}
	
	public String getLocation() {
		String str = driver.findElement(By.xpath(xpath+"//*[@data-test-id=\"content-hotel-neighborhood\"]")).getText();
		return str;
	}	
	
	
	public float getStarRating() {
		String str = driver.findElement(By.xpath(xpath+"//span[@data-stid=\"content-hotel-reviews-rating\"]")).getText();
		
		char[] chars = str.toCharArray();
		float stars = 0;
		
		stars=convertToInt(chars[2]);		
		stars=stars/10;		
		stars+=convertToInt(chars[0]);
				
	//	System.out.println(stars);
		return stars;
	}	
	


	public boolean hasPrice() {		
		return elementIsPresent(xpath + "//span[@class=\"uitk-cell loyalty-display-price all-cell-shrink\"]");
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
