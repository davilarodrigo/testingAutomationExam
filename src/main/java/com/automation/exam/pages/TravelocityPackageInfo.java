package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityPackageInfo extends TravelocityFlightInformation {

	public TravelocityPackageInfo(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
		pageLoadCorrectly=false;
		if (driver.getTitle().equals("Trip Detail | Travelocity")) {
			try {
				getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"FlightUDPBookNowButton1\"]/button")));
				checkIfClickable("//*[@id=\"FlightUDPBookNowButton1\"]/button");
				pageLoadCorrectly=true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	//page being not correctly loaded means that the browser skips it and loads the "whos traveling page" instead (it happens in one of the layouts)	
	private boolean pageLoadCorrectly;

	public boolean verifyIfPageIsLoaded() {
		return pageLoadCorrectly;
	}
	
	@Override
	public boolean verifyTotalPrice() {

		String xpathA = "(//span[@class=\"packagePriceTotal\"])[2]";
		String xpathB = "(//span[@class=\"uitk-type-500 uitk-type-bold uitk-text-primary-theme\"])[2]";

		printDetail(xpathB);
		printDetail(xpathA);

		return (elementIsPresent(xpathA) || elementIsPresent(xpathB));
	}

	@Override
	public boolean verifyDepartureAndReturn() {

		String departureAndReturnFlight1 = "(//div[@class=\"uitk-type-300 uitk-type-bold uitk-flex-item uitk-text-primary-theme\"])[1]";
		String departureAndReturnFlight2 = "(//div[@class=\"uitk-type-300 uitk-type-bold uitk-flex-item uitk-text-primary-theme\"])[2]";

		if (elementIsPresent(departureAndReturnFlight1) && elementIsPresent(departureAndReturnFlight2)) {
			// añadir una linea para verificar si el boton es visible
			// se puede hacer con element.isDisplayed(); (esto es lo que hace la funcion "is
			// present"
			// cuando se le manda un web element en vez de un xpath)
			// tambien probar poner los xpath en una anotacion @FindBym y unirlos con
			// pipelines

			// puede ser que el driver no este encontrando la pestaña,
			// habria que leer el title de la pagina para determinar si esta en la pestaña
			// correcta
			// porque tal vez por eso no encuentra los elementos

			// printDetail("departure and return details present (layout B)");
			return true;
		}

		String departureFlight1 = "(//div[@class=\"departure\"]//span[@class=\"time type-500\"])[1]";
		String departureFlight2 = "(//div[@class=\"departure\"]//span[@class=\"time type-500\"])[3]";
		String arrivalFlight1 = "(//div[@class=\"arrival\"]//span[@class=\"time type-500\"])[1]";
		String arrivalFlight2 = "(//div[@class=\"arrival\"]//span[@class=\"time type-500\"])[3]";

		if (elementIsPresent(departureFlight1) && elementIsPresent(departureFlight2) && elementIsPresent(arrivalFlight1)
				&& elementIsPresent(arrivalFlight2)) {
			// printDetail("departure and return details present (layout A)");
			return true;
		}
		return false;
	}
	
	public TravelocityWhosTraveling continueToWhosTravelingPage() {

		if (pageLoadCorrectly) {
			findAndClick("//*[@id=\"FlightUDPBookNowButton1\"]/button");			
		}		

		return new TravelocityWhosTraveling(getDriver());
	}
}
