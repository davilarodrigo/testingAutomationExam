package com.automation.exam.tests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.pages.TravelocityResults;
import com.automation.exam.tests.BaseTests;

public class TravelocityTests extends BaseTests{
	
	@Test(groups={"results"})
	@Parameters({ "resultsUrl" })
	public void testResults(String resultsUrl) {
		//el url de parametro tira error en la suite.xml
		TravelocityResults results= getTravelocityResults(resultsUrl);
		testResults(results);
	}
	
	@Test(groups={"search"})
	public void testFlightBooking() {
	
		TravelocityHome home = getTravelocityHome();
		
		home.goToFlightsTab();
		home.selectDestinationCity("LAX");
		home.selectOriginCity("LAS");
		System.out.println("cities selected");
		
		home.selectDepartingDate(1, 9, 2021);
		home.selectReturningDate(12, 12, 2021);
		System.out.println("dates selected");
		
		TravelocityResults results;		
		results=home.searchFlight();
		System.out.println("showing flight results");
		
		testResults(results);
	 }
	
	private void testResults(TravelocityResults results) {
		
		SoftAssert softAssert = new SoftAssert();
		
		
		boolean sortBoxpresent=results.verifySortingBox();
		if (sortBoxpresent) {System.out.println("sorting box present");}
		else {System.out.println("sorting box not present");}
		softAssert.assertEquals(sortBoxpresent, true);
		
		results.getResultsList();
	//	results.verifyResults();
	//	softAssert.assertEquals(results.verifyVisibleFlightDuration(),true);
	
	}
	
}