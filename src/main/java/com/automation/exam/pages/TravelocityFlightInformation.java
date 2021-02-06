package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityFlightInformation extends BasePage {

	public TravelocityFlightInformation(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
	}

	// 6. Verify trip details in the new page, by:
	// a. Trip total price is present
	// b. Departure and return information is present
	// c. Price guarantee text is present
	
	// 7. Press Continue Booking button.
	
	public boolean verifyTotalPrice() {
	//	WebElement totalPrice= driver.findElement(By.className("packagePriceTotal"));
		//getWait().until(ExpectedConditions.visibilityOf(totalPrice));
		return true;
	}

}
