package com.automation.exam.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class TravelocityHome extends BasePage {

	public TravelocityHome(WebDriver driver) {
		super(driver);
		driver.get("https://www.travelocity.com/");
	}
	
	@FindBy(xpath = "//button[@data-stid=\"location-field-leg1-origin-dialog-trigger\"]")
	private WebElement inputLeavingFrom;
	@FindBy(xpath = "//button[@data-stid=\"location-field-leg1-destination-dialog-trigger\"]")
	private WebElement inputGoingTo;

	@FindBy(xpath = "//*[@id=\"uitk-tabs-button-container\"]/li[2]/a") //@FindBy(xpath = "(//a[@class='uitk-tab-anchor'])[2] ")
	private WebElement buttonFlight;
	
	@FindBy(xpath="//ul[@class=\"uitk-typeahead-results no-bullet\"]/li[1]/button")
	private WebElement listItem;
	
	@FindBy(id="d1-btn")
	private WebElement datePickerDeparting;
	@FindBy(id="d2-btn")
	private WebElement datePickerReturning;
	
	//@FindBy(xpath="//*[@id=\"date-picker\"]/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr[3]/td[7]/button")
	@FindBy(xpath="//button[@aria-label=\"Feb 6, 2021.\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]")
	private WebElement fechaDePrueba1;
	
	@FindBy(xpath="//button[@aria-label=\"Feb 11, 2021.\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]")
	private WebElement fechaDePrueba2;
	
	@FindBy(xpath="//button[@data-stid=\"apply-date-picker\"]")
	private WebElement buttonDoneDataPicker;

	public void pickDate(WebElement dateTimePicker, String date) {
		getWait().until(ExpectedConditions.visibilityOf(dateTimePicker));
		dateTimePicker.click();		
		getWait().until(ExpectedConditions.visibilityOf(fechaDePrueba1));
		fechaDePrueba1.click();		

	}
	
	public String bookFlight(String leavingFrom, String goingTo, String departingDate, String returningDate) {
			
		//go to flights tab
		buttonFlight.click();
		
		//select departing date
		pickDate(datePickerDeparting, departingDate);
		buttonDoneDataPicker.click();
		
		//select returning date		
		//pickDate(datePickerReturning, returningDate); //remplazar las 4 siguientes lineas por esta 
		getWait().until(ExpectedConditions.visibilityOf(datePickerReturning));
		datePickerReturning.click();	
		getWait().until(ExpectedConditions.visibilityOf(fechaDePrueba2));
		fechaDePrueba2.click();
		
		//click done button in data picker
		buttonDoneDataPicker.click();
					
		//select origin city
    	getWait().until(ExpectedConditions.visibilityOf(inputLeavingFrom));
		inputLeavingFrom.click();
		inputLeavingFrom.sendKeys(leavingFrom);
		getWait().until(ExpectedConditions.visibilityOf(listItem));
		listItem.click();
		
		//select destiny city
		getWait().until(ExpectedConditions.visibilityOf(inputGoingTo));		
		inputGoingTo.click();
		inputGoingTo.sendKeys(goingTo);
		getWait().until(ExpectedConditions.visibilityOf(listItem));
		listItem.click();		
				

		return "hola";
	}

	public void bookFlight(String leavingFrom, String goingTo, String departing) {
		buttonFlight.click();
	}

	public void addCityToFlight(String leavingFrom, String goingTo, String departing) {
		// tocar boton de multi city
		// tocar boton de agregar nueva ciudad
		// seleccionar leaving from, going to, y fecha de departura
	}

	public void goToFlightsTab() {
		buttonFlight.click();
		// getWait().until(ExpectedConditions.elementToBeClickable(searchButton));
		// searchButton.click();
		// return new ArticlePage(getDriver());
	}
}
