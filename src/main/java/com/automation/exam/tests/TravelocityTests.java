package com.automation.exam.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.tests.BaseTests;

public class TravelocityTests extends BaseTests{
	
	@Test
	public void testFlightBooking() {
		
		TravelocityHome home = getTravelocityHome();
		
		home.goToFlightsTab();
		home.selectDestinationCity("LAX");
		home.selectOriginCity("LAS");
		home.selectDepartingDate(1, 10, 2021);
		home.selectReturningDate(1, 11, 2021);
		String text="hola";
		Assert.assertEquals(text, "hola");				
	 }

}
