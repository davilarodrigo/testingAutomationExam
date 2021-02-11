package com.automation.exam.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.exam.pages.TravelocityCarRental;
import com.automation.exam.pages.TravelocityFlightInformation;
import com.automation.exam.pages.TravelocityHome;
import com.automation.exam.pages.TravelocityHotelInfo;
import com.automation.exam.pages.TravelocityPackageInfo;
import com.automation.exam.pages.TravelocityPackagesResults;
import com.automation.exam.pages.TravelocityResults;
import com.automation.exam.pages.TravelocityWhosTraveling;
import com.automation.exam.tests.BaseTests;

public class TravelocityTests extends BaseTests {


	// @Test(groups = { "Exercises" })
	public void testFlightBooking() {
		System.out.println("Start of Exercise 1");
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

		// System.out.println("sorting list");
		results.sortByShorterDuration();

		softAssert.assertTrue(results.verifySortingByShorterDuration(), "list was not correctly sorted");
		softAssert.assertTrue(results.verifySelectButtons(), "select buttons missing in search results");
		softAssert.assertTrue(results.verifyPriceTag(), "details and fees missing in search results");
		softAssert.assertTrue(results.verifyFlightDuration(), "flight duration missing in search results");

		TravelocityResults secondResultsPage = results.selectDepartureFlight(1);
		// System.out.println("first flight selected");

		TravelocityFlightInformation flightInfoPage = secondResultsPage.selectReturnFlight(3);
		// System.out.println("second flight selected");

		// flightInfoPage.printDetails=true;
		Assert.assertTrue(flightInfoPage.verifyTotalPrice(), "total price missing in flight info page");
		Assert.assertTrue(flightInfoPage.verifyDepartureAndReturn(),
				"Departure and return text missing in flight info page");
		Assert.assertTrue(flightInfoPage.verifyGuaranteeText(), "Guarantee text missing in flight info page");
		TravelocityWhosTraveling whosTravelingPage = flightInfoPage.clickCheckOutButton();

		Assert.assertTrue(whosTravelingPage.verifyFirstNameInput(), "first name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyMiddleNameInput(),
				"middle name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyLastname(), "last name input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyPhoneInput(), "verify phone input missing in whos traveling page");
		Assert.assertTrue(whosTravelingPage.verifyPhoneCodeSelect(),
				"Phone code select missing in whos traveling page");

		System.out.println("End of Exercise 1");
		softAssert.assertAll();
	}

	//@Test(groups = { "Exercises" })
	public void testBookingFlightHotelCar() {
		System.out.println("Start of Exercise 2");
		SoftAssert softAssert = new SoftAssert();
		TravelocityHome home = getTravelocityHome();
		home.goToPackagesTab();

		home.selectOriginCity("LAS");
		home.selectDestinationCity("LAX");
		System.out.println("cities selected");

		softAssert.assertTrue(home.clickCarPill(), "car item pill is missing");
		home.selectPassengers();

		home.selectDepartingDate(31, 7, 2021);
		home.selectReturningDate(13, 8, 2021);
		System.out.println("dates selected");

		TravelocityPackagesResults results = home.searchPackages();

		// 5 verifications
		softAssert.assertTrue(results.verifyDatePickers(), "date time pickers missing in results page");
		softAssert.assertTrue(results.verifyDirectFlightsOnlyCheckbox(), "direct flights only checkbox missing");
		softAssert.assertTrue(results.verifyPreferedClassSelector(), "class selector missing");
		softAssert.assertTrue(results.verifyTravelRestrictionsAlert(), "travel restrictions alert missing");
		softAssert.assertTrue(results.verifySortingBox(), "sorting box failed");
		// System.out.println("verifications done");

		results.sortByPrice();
		softAssert.assertTrue(results.verifyResultsSortedByPrice(), "results were not correctly sorted");
		// System.out.println("list sorted");

		TravelocityHotelInfo hotelInfoPage = results.selectResultWithRating(3);
		System.out.println("hotel selected");

		// verifications in hotel page
		softAssert.assertTrue(hotelInfoPage.verifyHotelNameMatches(), "hotel name does not match previous result");
		softAssert.assertTrue(hotelInfoPage.verifyRatingMatches(), "hotel rating does not match previous result");
		softAssert.assertTrue(hotelInfoPage.verifyHotelPriceMatches(), "hotel price does not match previous result");

		TravelocityResults fightResults = hotelInfoPage.selectRoomOption(1);

		fightResults.selectDepartureFlight(1);
		TravelocityCarRental carRentalPage = fightResults.selectReturnFlightAndCar(3);

		softAssert.assertTrue(carRentalPage.tryToRentCar(1), "could not rent a car");

		TravelocityPackageInfo packageInfoPage = carRentalPage.continueToPackageInfoPage();

		// Verify Trip Details: 5 validations

		if (packageInfoPage.verifyIfPageIsLoaded()) {
			softAssert.assertTrue(packageInfoPage.verifyTotalPrice(), "total price missing in trip info page");
			softAssert.assertTrue(packageInfoPage.verifyDepartureAndReturn(),
					"Departure and return text missing in trip info page");
			softAssert.assertTrue(packageInfoPage.verifyGuaranteeText(), "Guarantee text missing in trip info page");
			softAssert.assertTrue(packageInfoPage.verifyChangeFlightsLinkAvailable(),
					"Change Flights Link not available");
			softAssert.assertTrue(packageInfoPage.verifyQuarantineAlertPresent(),
					"Quarantine Alert missing in trip info page");
		} else {
			softAssert.assertTrue(false, "trip info page is missing");
		}

		TravelocityWhosTraveling whosTravelingPage = packageInfoPage.continueToWhosTravelingPage();

		// Verify the “Who’s travelling” page: 5 validations
		softAssert.assertTrue(whosTravelingPage.verifyFirstNameInput(),
				"first name input missing in whos traveling page");
		softAssert.assertTrue(whosTravelingPage.verifyMiddleNameInput(),
				"middle name input missing in whos traveling page");
		softAssert.assertTrue(whosTravelingPage.verifyLastname(), "last name input missing in whos traveling page");
		softAssert.assertTrue(whosTravelingPage.verifyPhoneInput(),
				"verify phone input missing in whos traveling page");
		softAssert.assertTrue(whosTravelingPage.verifyPhoneCodeSelect(),
				"Phone code select missing in whos traveling page");

		System.out.println("End of Exercise 2");
		softAssert.assertAll();
	}
	

	@Test(groups = { "Exercises" })
	public void testSponsoredHotels() {
		System.out.println("Start of Exercise 3");
		SoftAssert softAssert = new SoftAssert();
		TravelocityHome home = getTravelocityHome();
		
		home.goToHotelsTab();
		home.selectHotel("Montevideo, Uruguay");
		TravelocityPackagesResults results=home.searchHotels();
		
		softAssert.assertTrue(results.verifySponsoredResultsFirst(), "some sponsored results do not appear first");
		softAssert.assertTrue(results.verifyDiscountWithEmail(), "discount option by entering email is missing");
		
		System.out.println("End of Exercise 3");
		softAssert.assertAll();
	}
}