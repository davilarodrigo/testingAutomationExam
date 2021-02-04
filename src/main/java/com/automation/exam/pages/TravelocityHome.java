package com.automation.exam.pages;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

	// origin and destination buttons
	@FindBy(xpath = "(//button[@data-stid=\"location-field-leg1-origin-dialog-trigger\"]) | (//button[@data-stid=\"location-field-leg1-origin-menu-trigger\"])")
	private WebElement buttonLeavingFrom;
	@FindBy(xpath = "(//button[@data-stid=\"location-field-leg1-destination-dialog-trigger\"]) | (//button[@data-stid=\"location-field-leg1-destination-menu-trigger\"])")
	private WebElement buttonGoingTo;

	// origin and destination inputs (mejorar estos 2 xpaths para que funcionen en
	// cualquier tamaño de pantalla)
	@FindBy(id = "location-field-leg1-origin")
	private WebElement inputLeavingFrom;
	@FindBy(id = "location-field-leg1-destination")
	private WebElement inputGoingTo;

	@FindBy(xpath = "(//*[@id=\"uitk-tabs-button-container\"]/li[2]/a) | ((//ul[@class=\"uitk-flex lobNavigation no-bullet uitk-scroll-horizontal\"]/li)[2])") // @FindBy(xpath ="(//a[@class='uitk-tab-anchor'])[2] ")
	private WebElement buttonFlight;
	
	//*[@id="location-field-leg1-origin-menu"]/div[2]/ul
	//uitk-button uitk-button-small uitk-button-fullWidth uitk-button-typeahead uitk-type-left 
	@FindBy(xpath = "//ul[@class=\"uitk-typeahead-results no-bullet\"]/li[1]/button")
	private WebElement listItem;
	
	@FindBy(id = "d1-btn")
	private WebElement datePickerDeparting;
	@FindBy(id = "d2-btn")
	private WebElement datePickerReturning;
	@FindBy(xpath = "//button[@data-stid=\"apply-date-picker\"]")
	private WebElement buttonDoneDataPicker;
	@FindBy(xpath = "(//button[@data-stid=\"date-picker-paging\"])[2]")
	private WebElement buttonNextPage;

	@FindBy(xpath="//button[@data-testid=\"submit-button\"]")
	private WebElement buttonSearch;
	
	private String findDayButton(Integer day, int month, Integer year) {

		month--;
		String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May","Jun","Jul" ,"Aug","Sep","Oct","Nov","Dec" };// completar todos los meses
		String date = months[month];
		date += " " + day.toString();
		date += ", " + year + ".";
		return date;
	}

	private void pickDate(WebElement dateTimePicker, Integer day, int month, Integer year) {

		String date = findDayButton(day, month, year); 		
		String dayButtonXpath="//button[@aria-label=\""+date+"\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]" ;				
	
		getWait().until(ExpectedConditions.visibilityOf(dateTimePicker));
		dateTimePicker.click();

		getWait().until(ExpectedConditions.visibilityOf(buttonNextPage));
		for (int i = 0; i < 12; i++) { //12 porque parece ser el limite de la pagina
			if (elementIsPresent(dayButtonXpath)) {
				WebElement dayButton= driver.findElement(By.xpath(dayButtonXpath));
				dayButton.click();
				break;
			}			
			buttonNextPage.click();
		}
				
		buttonDoneDataPicker.click();
	}
	
	public void selectDepartingDate(Integer day, int month, Integer year) {
		pickDate(datePickerDeparting, day, month, year);
	}
	
	public void selectReturningDate(Integer day, int month, Integer year) {
		pickDate(datePickerReturning, day, month, year);
	}

	public void selectOriginCity(String city) {
		selectCity(buttonLeavingFrom,inputLeavingFrom,city);
	}
	
	public void selectDestinationCity(String city) {
		selectCity(buttonGoingTo,inputGoingTo,city);
	}
	
	private void selectCity(WebElement button, WebElement input, String city) {
		getWait().until(ExpectedConditions.visibilityOf(button));
		button.click();
		getWait().until(ExpectedConditions.visibilityOf(input));
		input.sendKeys(city);
		getWait().until(ExpectedConditions.elementToBeClickable(listItem));
		listItem.click();
	}

	public void goToFlightsTab() {
		buttonFlight.click();
	}
	
	public TravelocityResults searchFlight() {
		buttonSearch.click();
		return new TravelocityResults(getDriver());
	}
}
