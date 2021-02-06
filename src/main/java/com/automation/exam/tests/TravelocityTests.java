package com.automation.exam.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

		boolean sortBoxClickable = results.verifySortingBoxClickable();
		softAssert.assertEquals(sortBoxClickable, true);

/*		System.out.println(results.verifySortingByShorterDuration());

		results.sortByShorterDuration();
		System.out.println(results.verifySortingByShorterDuration());
*/
		boolean allSelectButtonsPresent = results.verifySelectButtons();
		boolean allFlightDurationsPresent = results.verifyFlightDuration();
		boolean allDetailsAndFeesPresent = results.verifyPriceTag();
		softAssert.assertEquals(allSelectButtonsPresent, true);
		softAssert.assertEquals(allFlightDurationsPresent, true);
		softAssert.assertEquals(allDetailsAndFeesPresent, true);

	}

}