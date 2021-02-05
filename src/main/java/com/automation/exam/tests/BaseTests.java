package com.automation.exam.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.exam.MyDriver;
import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.pages.TravelocityResults;

public class BaseTests {

	protected MyDriver myDriver;
	private TravelocityHome travelocityHome;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser" })
	public void beforeSuite(String browser) {
		myDriver = new MyDriver(browser);
		myDriver.getDriver().manage().window().maximize();
		travelocityHome = new TravelocityHome(myDriver.getDriver());
	}
	
	@BeforeMethod
	public void beforeTest() {
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		travelocityHome.dispose();
	}
	
	public TravelocityHome getTravelocityHome() {
		return travelocityHome;		
	}
	
	public TravelocityResults getTravelocityResults(String resultsUrl) {
		
		return new TravelocityResults(myDriver.getDriver(),resultsUrl);
	}

	

}