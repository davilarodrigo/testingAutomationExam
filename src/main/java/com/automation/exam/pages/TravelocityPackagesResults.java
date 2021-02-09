package com.automation.exam.pages;

import org.openqa.selenium.WebDriver;

public class TravelocityPackagesResults extends BasePage {

	public TravelocityPackagesResults(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyPreferedClassSelector() {		
		return checkIfClickable("//select[@id=\"cabinClass\"]");
	}
		
	public boolean verifyDatePickers() {
		return (checkIfClickable("//button[@id=\"hotels-check-in-btn\"]") && checkIfClickable("//button[@id=\"hotels-check-out-btn\"]"));
	}
	
	public boolean verifySortingBox() {
		return checkIfClickable("//select[@id=\"sort\"]");
	}
	
	public boolean verifyTravelRestrictionsAlert() {
		return elementIsPresent("//div[@class=\"uitk-flex uitk-experimental-banner\"]");		
	}
	
	public boolean verifyDirectFlightsOnlyCheckbox() {
		return elementIsPresent("//input[@id=\"directFlights\"]");
	}

}
