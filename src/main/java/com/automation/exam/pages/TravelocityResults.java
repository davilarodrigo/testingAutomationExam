package com.automation.exam.pages;



import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityResults extends BasePage  {

	public TravelocityResults(WebDriver driver) {
		super(driver);
		//driver.get("https://www.travelocity.com/");
	}
	
	public boolean verifyVisibleFlightDuration() {
	
		System.out.println(listOfResults.size());
		
		for (WebElement webElement : listOfResults) {
			
		}
		
		for (int i = 0; i < listOfResults.size(); i++) {
			
		}				
		
		return true;
	}
	
	List<WebElement> listOfResults = driver.findElements(By.xpath("//button[@class=\"uitk-card-link\"]"));
	
	
	//@FindBy(id="") //a veces aparece asi...
	@FindBy(xpath= "(//select[@id=\"listings-sort\"]) |(//select[@id=\"sortDropdown\"])")
	private WebElement sortDropdown;
	
	public boolean verifySortingBox() {
		//getWait().until(ExpectedConditions.visibilityOf(sortDropdown));
		getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
		sortDropdown.click();
		return elementIsPresent(sortDropdown);
	}	

}
