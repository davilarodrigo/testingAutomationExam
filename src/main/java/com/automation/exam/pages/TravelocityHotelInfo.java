package com.automation.exam.pages;

import org.openqa.selenium.WebDriver;

public class TravelocityHotelInfo extends BasePage{

	Hotel hotelInfo;
	
	public TravelocityHotelInfo(WebDriver pDriver, Hotel hotel) {
		super(pDriver);
		hotelInfo=hotel;
		// TODO Auto-generated constructor stub
	}
	
	/*public boolean verifyHotelNameMatches() {
		
	}*/

}
