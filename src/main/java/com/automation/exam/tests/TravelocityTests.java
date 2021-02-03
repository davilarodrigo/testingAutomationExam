package com.automation.exam.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.tests.BaseTests;

public class TravelocityTests extends BaseTests{
	
	@Test
	public void testFlightBooking() {
		TravelocityHome home = getTravelocityHome();
		String text=home.bookFlight("LAS", "LAX", "20210503", "20210506");		
		System.out.println(text);		
		Assert.assertEquals(text, "hola");				
	 }

}
