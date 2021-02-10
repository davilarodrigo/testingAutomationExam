package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityCarRental extends BasePage {

	public TravelocityCarRental(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
	}

	public boolean tryToRentCar(int index) {
		try {
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"book-container\"]/a)[1]")));
			findAndClick("(//*[@class=\"book-container\"]/a)["+index+"]");
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public TravelocityPackageInfo continueToPackageInfoPage() {
		return new TravelocityPackageInfo(getDriver());
	}
}
