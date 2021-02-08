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

	@Test(groups = { "search" })
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
	
		//results.printDetails = true;		
		Assert.assertTrue(results.verifySortingBoxClickable(), "sort box not clickable");
		
		//System.out.println("sorting list");		
		results.sortByShorterDuration();
		
		Assert.assertTrue(results.verifySortingByShorterDuration(), "list not sorted");
		Assert.assertEquals(results.verifySelectButtons(), true);
		Assert.assertEquals(results.verifyPriceTag(), true);
		Assert.assertEquals(results.verifyFlightDuration(), true);
		
		TravelocityResults secondResultsPage = results.selectFirstFlight(1);
		System.out.println("first flight selected");

		// secondResultsPage.printDetails=true;
		TravelocityFlightInformation flightInfoPage = secondResultsPage.selectSecondFlight(3);
		System.out.println("second flight selected");

		// flightInfoPage.printDetails=true;
		softAssert.assertEquals(flightInfoPage.verifyTotalPrice(), true);		
		softAssert.assertEquals(flightInfoPage.verifyDepartureAndReturn(), true);				
		softAssert.assertEquals(flightInfoPage.verifyGuaranteeText(), true);		
		TravelocityWhosTraveling whosTravelingPage= flightInfoPage.clickCheckOutButton();
		System.out.println("Check out button clicked!");
		
		Assert.assertEquals(whosTravelingPage.verifyFirstNameInput(), true);
		Assert.assertEquals(whosTravelingPage.verifyLastname(), true);
		Assert.assertEquals(whosTravelingPage.verifyMiddleNameInput(), true);
		Assert.assertEquals(whosTravelingPage.verifyPhoneInput(), true);
		Assert.assertEquals(whosTravelingPage.verifyPhoneCodeSelect(), true);
				
		
	}

}