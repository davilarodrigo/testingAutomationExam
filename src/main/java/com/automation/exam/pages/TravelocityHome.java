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

	@FindBy(xpath = "//*[@id=\"uitk-tabs-button-container\"]/li[2]/a") // @FindBy(xpath =
																		// "(//a[@class='uitk-tab-anchor'])[2] ")
	private WebElement buttonFlight;
	@FindBy(xpath = "//ul[@class=\"uitk-typeahead-results no-bullet\"]/li[1]")

	private WebElement listItem;

	// data pickers
	@FindBy(id = "d1-btn")
	private WebElement datePickerDeparting;
	@FindBy(id = "d2-btn")
	private WebElement datePickerReturning;
	@FindBy(xpath = "//button[@data-stid=\"apply-date-picker\"]")
	private WebElement buttonDoneDataPicker;
	@FindBy(xpath = "(//button[@data-stid=\"date-picker-paging\"])[2]")
	private WebElement buttonNextPage;

	// fechas
	@FindBy(xpath = "//button[@aria-label=\"Dec 14, 2021.\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]")
	private WebElement fechaDePrueba1;
	@FindBy(xpath = "//button[@aria-label=\"Dec 28, 2021.\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]")
	private WebElement fechaDePrueba2;
	@FindBy(xpath = "//button[@aria-label=\"Feb 21, 2021.\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]")
	private WebElement fechaDePrueba3;

	private WebElement findDayButton(Integer day, int month, Integer year) {

		month--;
		String[] months = new String[] { "Jan", "Feb", "Mar" };// completar todos los meses
		String date = months[month];
		date += " " + day.toString();
		date += ", " + year + ".";

		return driver.findElement(By.xpath(
				"//button[@aria-label=\"" + date + "\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]"));
	}

	private boolean elementIsPresent(WebElement element) {
		try {
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void pickDate(WebElement dateTimePicker, int day, Integer month, Integer year) {

		// WebElement dayButton = findDayButton(day, month, year); //habilitar esta
		// linea al descartar otros errores
		WebElement dayButton = fechaDePrueba1;
		// WebElement dayButton =
		// driver.findElement(By.xpath("//button[@aria-label=\"Feb 7,2021.\" and
		// @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]"));

		getWait().until(ExpectedConditions.visibilityOf(dateTimePicker));

		dateTimePicker.click();

		for (int i = 0; i < 20; i++) {
			if (elementIsPresent(dayButton)) {
				break;
			}			
			getWait().until(ExpectedConditions.visibilityOf(buttonNextPage));
			buttonNextPage.click();
		}
	
		dayButton.click();
		buttonDoneDataPicker.click();

	}

	public String bookFlight(String leavingFrom, String goingTo, String departingDate, String returningDate) {

		// go to flights tab
		buttonFlight.click();

		// select departing date
		pickDate(datePickerDeparting, 7, 2, 2021);
		// buttonDoneDataPicker.click();

		// select returning date
		// pickDate(datePickerReturning, returningDate); //remplazar las 4 siguientes
		// lineas por esta
		getWait().until(ExpectedConditions.visibilityOf(datePickerReturning));
		datePickerReturning.click();
		getWait().until(ExpectedConditions.visibilityOf(fechaDePrueba2));
		fechaDePrueba2.click();

		// click done button in data picker
		buttonDoneDataPicker.click();

		// select origin city
		getWait().until(ExpectedConditions.visibilityOf(buttonLeavingFrom));
		buttonLeavingFrom.click();
		getWait().until(ExpectedConditions.visibilityOf(inputLeavingFrom));
		inputLeavingFrom.sendKeys(leavingFrom);
		getWait().until(ExpectedConditions.visibilityOf(listItem));
		listItem.click();

		// select destiny city
		getWait().until(ExpectedConditions.visibilityOf(buttonGoingTo));
		buttonGoingTo.click();
		getWait().until(ExpectedConditions.visibilityOf(inputGoingTo));
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
