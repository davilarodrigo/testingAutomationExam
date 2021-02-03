package com.automation.exam.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automation.exam.MyDriver;
import com.automation.exam.pages.TravelocityHome;

public class BaseTests {

	MyDriver myDriver;
	private TravelocityHome travelocityHome;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser" })
	public void beforeSuite(String browser) {
		myDriver = new MyDriver(browser);
		travelocityHome = new TravelocityHome(myDriver.getDriver());
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		travelocityHome.dispose();
	}
	
	public TravelocityHome getTravelocityHome() {
		return travelocityHome;		
	}
}