package com.automation.exam.pages;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sun.jna.platform.win32.WinUser.INPUT;

public class TravelocityHome extends BasePage {

	public TravelocityHome(WebDriver driver) {
		super(driver);
		driver.get("https://www.travelocity.com/");
	}

	enum Tabs {
		Flights, Packages, Hotels
	}

	private Tabs currentTab = Tabs.Hotels;

	// origin and destination buttons
	@FindBy(xpath = "(//button[@data-stid=\"location-field-origin-menu-trigger\"]) | (//button[@data-stid=\"location-field-leg1-origin-menu-trigger\"])")
	private WebElement buttonLeavingFrom;
	@FindBy(xpath = "(//button[@data-stid=\"location-field-destination-menu-trigger\"]) | (//button[@data-stid=\"location-field-leg1-destination-menu-trigger\"])")
	private WebElement buttonGoingTo;

	@FindBy(xpath = "(//*[@id=\"uitk-tabs-button-container\"]/li[2]/a) | ((//ul[@class=\"uitk-flex lobNavigation no-bullet uitk-scroll-horizontal\"]/li)[2])") // @FindBy(xpath
	private WebElement buttonFlight;
	// ="(//a[@class='uitk-tab-anchor'])[2]
	@FindBy(xpath = "(//*[@id=\"uitk-tabs-button-container\"]/li[1]/a) | ((//ul[@class=\"uitk-flex lobNavigation no-bullet uitk-scroll-horizontal\"]/li)[1])") // @FindBy(xpath
	private WebElement buttonHotel;

	@FindBy(xpath = "(//input[@id=\"wizard-package-pwa\"]//parent::div) | (//a[@href=\"?pwaLob=wizard-package-pwa\"]//parent::li)")
	private WebElement buttonPackages;

	@FindBy(id = "d1-btn")
	private WebElement datePickerDeparting;
	@FindBy(id = "d2-btn")
	private WebElement datePickerReturning;
	@FindBy(xpath = "//button[@data-stid=\"apply-date-picker\"]")
	private WebElement buttonDoneDataPicker;
	@FindBy(xpath = "(//button[@data-stid=\"date-picker-paging\"])[2]")
	private WebElement buttonNextPage;

	@FindBy(xpath = "//button[@data-testid=\"submit-button\"]")
	private WebElement buttonSearch;

	@FindBy(xpath = "//button[@data-stid=\"hotel-destination-dialog-trigger\"] | //button[@data-stid=\"location-field-destination-menu-trigger\"]")
	private WebElement hotelInput;

	@FindBy(xpath = "//div[@class=\"uitk-menu-container uitk-menu-open uitk-menu-pos-left uitk-menu-container-text-nowrap\"]")
	private WebElement searchMenuResults;

	private String findDayButton(Integer day, int month, Integer year) {

		month--;
		String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
				"Dec" };// completar todos los meses
		String date = months[month];
		date += " " + day.toString();
		date += ", " + year + ".";
		return date;
	}

	private void pickDate(WebElement dateTimePicker, Integer day, int month, Integer year) {

		String date = findDayButton(day, month, year);
		String dayButtonXpath = "//button[@aria-label=\"" + date
				+ "\" and @class=\"uitk-date-picker-day uitk-new-date-picker-day\"]";

		getWait().until(ExpectedConditions.visibilityOf(dateTimePicker));
		dateTimePicker.click();

		getWait().until(ExpectedConditions.visibilityOf(buttonNextPage));
		for (int i = 0; i < 12; i++) { // 12 porque parece ser el limite de la pagina
			if (elementIsPresent(dayButtonXpath)) {
				WebElement dayButton = driver.findElement(By.xpath(dayButtonXpath));
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
		selectCity(buttonLeavingFrom, city);
		WebElement listItem = driver
				.findElement(By.xpath("//ul[@class=\"uitk-typeahead-results no-bullet\"]/li[1]/button"));
		getWait().until(ExpectedConditions.elementToBeClickable(listItem));
		listItem.click();
	}

	public void selectHotel(String hotel) {
		getWait().until(ExpectedConditions.visibilityOf(hotelInput));
		hotelInput.click();
		// getWait().until(ExpectedConditions.visibilityOf(searchMenuResults));

		// WebElement input =
		// findByXpath("//input[@data-stid=\"hotel-destination-dialog-input\"] |
		// //input[@data-stid=\"location-field-destination-menu-input\"]");
		// getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(input)));
		// input.sendKeys(hotel);
		driver.switchTo().activeElement().sendKeys(hotel);
		driver.switchTo().activeElement().sendKeys(" ");
		// driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		String xpath = "//li[@data-index=\"0\"]/button[not(@disabled)]";
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		findAndClick(xpath);
	}

	public void selectDestinationCity(String city) {
		selectCity(buttonGoingTo, city);
		WebElement listItem = driver
				.findElement(By.xpath("(//ul[@class=\"uitk-typeahead-results no-bullet\"]/li[1]/button)[2]"));
		getWait().until(ExpectedConditions.elementToBeClickable(listItem));
		listItem.click();
	}

	private void selectCity(WebElement button, String city) {

		getWait().until(ExpectedConditions.visibilityOf(button));
		button.click();
		getWait().until(ExpectedConditions.visibilityOf(searchMenuResults));
		driver.switchTo().activeElement().sendKeys(city);
	}

	public void goToFlightsTab() {

		buttonFlight.click();
		currentTab = Tabs.Flights;
	}

	public void goToHotelsTab() {

		buttonHotel.click();
		currentTab = Tabs.Hotels;
	}

	public void goToPackagesTab() {
		buttonPackages.click();
		currentTab = Tabs.Packages;
	}

	public TravelocityResults searchFlight() {
		buttonSearch.click();
		return new TravelocityResults(getDriver());
	}

	// seguir por aca, en vez de buscar vuelos, se buscan paquetes
	public TravelocityPackagesResults searchPackages() {
		buttonSearch.click();
		return new TravelocityPackagesResults(getDriver());
	}

	public TravelocityPackagesResults searchHotels() {
		findAndClick("//button[@data-testid=\"wizard-submit-button\"] | //button[@data-testid=\"submit-button\"]");
		return new TravelocityPackagesResults(getDriver());
	}

	public boolean clickCarPill() {
		if (elementIsPresent("(//*[@class=\"uitk-pill-text\"])[3]")) {
			findAndClick("(//*[@class=\"uitk-pill-text\"])[3]");
			return true;
		}
		return false;
	}

	public void selectPassengers() {
		findAndClick("//a[@data-testid=\"travelers-field\"]");
		findAndClick("//div[@class=\"uitk-flex uitk-flex-item uitk-step-input-controls\"]//button[1]");
		findAndClick("//button[@data-testid=\"guests-done-button\"]");
	}
}
