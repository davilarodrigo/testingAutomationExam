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

	@Test(groups = { "results" })
	@Parameters({ "resultsUrl" })
	public void testResults(String resultsUrl) {
		// el url de parametro tira error en la suite.xml
		TravelocityResults results = getTravelocityResults(resultsUrl);
		testResults(results);
	}

	@Test(groups = { "search" })
	public void testFlightBooking() {

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

		testResults(results);
	}

	private void testResults(TravelocityResults results) {

		SoftAssert softAssert = new SoftAssert();
		
		//results.printDetails = true;		
		softAssert.assertEquals(results.verifySortingBoxClickable(), true);
		
		System.out.println("sorting list");		
		results.sortByShorterDuration();
		
		softAssert.assertEquals(results.verifySortingByShorterDuration(), true);
		softAssert.assertEquals(results.verifySelectButtons(), true);
		softAssert.assertEquals(results.verifyPriceTag(), true);
		softAssert.assertEquals(results.verifyFlightDuration(), true);

		TravelocityResults secondResultsPage = results.selectFirstFlight(1);
		System.out.println("first flight selected");

		// secondResultsPage.printDetails=true;
		TravelocityFlightInformation flightInfoPage = secondResultsPage.selectSecondFlight(3);
		System.out.println("second flight selected");

		// flightInfoPage.printDetails=true;
		// flight info verifications
		softAssert.assertEquals(flightInfoPage.verifyTotalPrice(), true);		
		softAssert.assertEquals(flightInfoPage.verifyDepartureAndReturn(), true);				
		softAssert.assertEquals(flightInfoPage.verifyGuaranteeText(), true);		
		TravelocityWhosTraveling whosTravelingPage= flightInfoPage.clickCheckOutButton();
		System.out.println("Check out button clicked!");

		// whos traveling page verifications
		softAssert.assertEquals(whosTravelingPage.verifyFirstNameInput(), true);
		softAssert.assertEquals(whosTravelingPage.verifyLastname(), true);
		softAssert.assertEquals(whosTravelingPage.verifyMiddleNameInput(), true);
		softAssert.assertEquals(whosTravelingPage.verifyPhoneInput(), true);
		softAssert.assertEquals(whosTravelingPage.verifyPhoneCodeSelect(), true);		
		
	}

}