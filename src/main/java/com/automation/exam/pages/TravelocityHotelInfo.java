package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityHotelInfo extends BasePage{

	Hotel hotelInfo;
	
	public TravelocityHotelInfo(WebDriver pDriver, Hotel hotel) {
		super(pDriver);
		hotelInfo=hotel;
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-stid=\"content-hotel-title\"]//h1")));
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyHotelNameMatches() {
		String name=findByXpath("//div[@data-stid=\"content-hotel-title\"]//h1").getText();
		System.out.println(name);
		System.out.println(hotelInfo.name);
		return (name==hotelInfo.name);
	}

	public boolean verifyRatingMatches() {
		String rating=findByXpath("//div[@data-stid=\"content-hotel-reviewsummary\"]//div/h3").getText();
		
		char[] chars = rating.toCharArray();
		float stars = 0;
		
		stars=Character.getNumericValue(chars[2]);		
		stars=stars/10;		
		stars+=Character.getNumericValue(chars[0]);

		System.out.println(rating);
		System.out.println(stars);
		System.out.println(hotelInfo.starRating);
		return(stars==hotelInfo.starRating);
	}
}
