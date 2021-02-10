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
	
	
	public TravelocityResults selectRoomOption(int option) {
		findAndClick("(//button[@data-stid=\"submit-hotel-reserve\"])["+option+"]");
		return new TravelocityResults(getDriver());
	}
	
	//-------------------------------------------
	
	public boolean verifyHotelPriceMatches() {
		String price=findByXpath("//span[@class=\"uitk-lockup-price\"]").getText();
		
		price = price.replace(",", "");
		
		//System.out.println("$"+hotelInfo.price);
		//System.out.println(price);
		
		return price.equals("$"+hotelInfo.price);
	}
		
	public boolean verifyHotelNameMatches() {
		String name=findByXpath("//div[@data-stid=\"content-hotel-title\"]//h1").getText();		
		return name.equals(hotelInfo.name);

	}

	public boolean verifyRatingMatches() {
		String rating=findByXpath("//div[@data-stid=\"content-hotel-reviewsummary\"]//div/h3").getText();
		
		char[] chars = rating.toCharArray();
		float stars = 0;
		
		stars=Character.getNumericValue(chars[2]);		
		stars=stars/10;		
		stars+=Character.getNumericValue(chars[0]);

		return(stars==hotelInfo.starRating);
	}
}
