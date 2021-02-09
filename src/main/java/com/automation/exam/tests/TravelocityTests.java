package com.automation.exam.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.exam.pages.TravelocityFlightInformation;
import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.pages.TravelocityResults;
import com.automation.exam.pages.TravelocityWhosTraveling;
import com.automation.exam.tests.BaseTests;

public class TravelocityTests extends BaseTests {

	/*
	@Test(groups = { "results" })
	@Parameters({ "resultsUrl" })
	public void testResults(String resultsUrl) {
		// el url de parametro tira error en la suite.xml
		TravelocityResults results = getTravelocityResults(resultsUrl);
		testResults(results);
	}*/

	@Test(groups = { "Exercise1" })
	public void testFlightBooking() {
		SoftAssert softAssert = new SoftAssert();
			
		TravelocityHome home = getTravelocityHome();

		home.goToFlightsTab();
		home.selectOriginCity("LAS");
		home.selectDestinationCity("LAX");
		System.out.println("cities selected");

		home.selectDepartingDate(13, 9, 2021);
		home.selectReturningDate(20, 12, 2021);
		System.out.println("dates selected");

		TravelocityResults results;
		results = home.searchFlight();
		System.out.println("showing flight results");
			
		Assert.assertTrue(results.verifySortingBoxClickable(), "sort box not clickable");
		
		//System.out.println("sorting list");		
		results.sortByShorterDuration();
		
		softAssert.assertTrue(results.verifySortingByShorterDuration(), "list was not correctly sorted");
		softAssert.assertTrue(results.verifySelectButtons(), "select buttons missing in search results");
		softAssert.assertTrue(results.verifyPriceTag(), "details and fees missing in search results");
		softAssert.assertTrue(results.verifyFlightDuration(), "flight duration missing in search results");
		
		TravelocityResults secondResultsPage = results.selectFirstFlight(1);
		//System.out.println("first flight selected");

		TravelocityFlightInformation flightInfoPage = secondResultsPage.selectSecondFlight(3);
		//System.out.println("second flight selected");

		// flightInfoPage.printDetails=true;
		Assert.assertTrue(flightInfoPage.verifyTotalPrice(), "total price missing in flight info page");		
		Assert.assertTrue(flightInfoPage.verifyDepartureAndReturn(), "Departure and return text missing in flight info page");				
		Assert.assertTrue(flightInfoPage.verifyGuaranteeText(), "Guarantee text missing in flight info page");		
		TravelocityWhosTraveling whosTravelingPage= flightInfoPage.clickCheckOutButton();
		
		Assert.assertTrue(whosTravelingPage.verifyFirstNameInput(), "first name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyMiddleNameInput(), "middle name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyLastname(), "last name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyPhoneInput(), "verify phone input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyPhoneCodeSelect(), "Phone code select missing in whos traveling page");
		
		System.out.println("End of Exercise 1");
		softAssert.assertAll();
	}

	@Test(groups = { "Exercise2" })
	public void testBookingFlightHotelCar() {
		SoftAssert softAssert = new SoftAssert();
		TravelocityHome home = getTravelocityHome();
		home.goToPackagesTab();
		
		
		home.selectOriginCity("LAS");
		home.selectDestinationCity("LAX");
		System.out.println("cities selected");
		
		softAssert.assertTrue(home.clickCarPill(), "car item pill is missing");

		home.selectDepartingDate(31, 7, 2021);
		home.selectReturningDate(13, 8, 2021);
		System.out.println("dates selected");
		
		
		System.out.println("End of Exercise 2");
		softAssert.assertAll();
	}
}