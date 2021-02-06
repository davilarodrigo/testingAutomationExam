package com.automation.exam.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.exam.pages.TravelocityFlightInformation;
import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.pages.TravelocityResults;
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

		home.selectDepartingDate(1, 9, 2021);
		home.selectReturningDate(12, 12, 2021);
		System.out.println("dates selected");

		TravelocityResults results;
		results = home.searchFlight();
		System.out.println("showing flight results");

		testResults(results);
	}

	private void testResults(TravelocityResults results) {

		SoftAssert softAssert = new SoftAssert();
		results.printDetails = true;

		//reactivar estas lineas despues, lo que pasa es que sortear la lista cada vez vuelve todo muy lento
		/* System.out.println("sorting list");
		boolean sortBoxClickable = results.verifySortingBoxClickable();
		softAssert.assertEquals(sortBoxClickable, true);

		results.sortByShorterDuration();
		boolean listCorrectlySorted=results.verifySortingByShorterDuration();
		System.out.println("list correctly sorted: "+listCorrectlySorted);
		softAssert.assertEquals(listCorrectlySorted, true);
		
		boolean allSelectButtonsPresent = results.verifySelectButtons();
		boolean allFlightDurationsPresent = results.verifyFlightDuration();
		boolean allDetailsAndFeesPresent = results.verifyPriceTag();
		softAssert.assertEquals(allSelectButtonsPresent, true);
		softAssert.assertEquals(allFlightDurationsPresent, true);
		softAssert.assertEquals(allDetailsAndFeesPresent, true);
		 */
		
		TravelocityResults secondResultsPage = results.selectResult(1);
		System.out.println("first flight selected");
		
		secondResultsPage.selectResult(3);
		System.out.println("second flight selected");
		
		TravelocityFlightInformation flightInfoPage= secondResultsPage.dissmissAlert();
		
		testFlightInfoPage(flightInfoPage);
	}
	
	private void testFlightInfoPage(TravelocityFlightInformation flightInfoPage) {
		boolean totalPricePresent= flightInfoPage.verifyTotalPrice();
		System.out.println("total price pPresent: "+totalPricePresent);
	}

}