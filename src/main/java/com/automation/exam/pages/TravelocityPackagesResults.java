package com.automation.exam.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class TravelocityPackagesResults extends BasePage {

	public TravelocityPackagesResults(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//select[@id=\"sort\"]")
	WebElement sortDropdown;

	private List<PackagesSearchResultItem> resultsList;

	private void clickSortBoxOption(String value) {
		waitAndClick(sortDropdown);
		String option = "//option[@value=\"" + value + "\"]";
		findAndClick(option);

		getWait().until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@data-stid=\"messaging-card\"]")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-stid=\"messaging-card\"]")));
		resultsList = null;

	}

	private List<PackagesSearchResultItem> getSearchResultItems() {

		getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
		// List<SearchResultItem> searchResultItemsList;

		if (resultsList == null) {

			resultsList = new ArrayList<>();
			List<WebElement> htmlList = new ArrayList<>();

			String xpath = "//*[@data-stid=\"section-results\"]//ol//li";
			htmlList = driver.findElements(By.xpath(xpath));
			getWait().until(ExpectedConditions.elementToBeClickable(htmlList.get(1)));

			WebElement elem;

			for (int i = 2; i < htmlList.size(); i++) {

				elem = findByXpath(xpath + "[" + i + "]");
				if (elementIsPresent(elem)) {
					PackagesSearchResultItem item = new PackagesSearchResultItem(xpath + "[" + i + "]", driver);
					if (item.hasPrice()) {
						resultsList.add(item);
					}
				}
			}
		}
		return resultsList;
	}

	// -----------------------------------------------------------------------

	public void sortByPrice() {
		clickSortBoxOption("PRICE_LOW_TO_HIGH");
	}

	public TravelocityHotelInfo selectResultWithRating(float minimumDesiredRating) {
		resultsList = getSearchResultItems();

		for (int i = 0; i < resultsList.size(); i++) {			
			float starRating = resultsList.get(i).getStarRating();
			if (starRating >= minimumDesiredRating) {
				Hotel hotel = new Hotel();
				hotel.starRating=starRating;
				hotel.price=resultsList.get(i).getPrice();
				hotel.name=resultsList.get(i).getName();
				hotel.location=resultsList.get(i).getLocation();
				
				// System.out.println(hotel.location);
				// System.out.println(hotel.name);
				// System.out.println(hotel.price);
				//System.out.println(hotel.starRating);
				
				resultsList.get(i).getAsWebElement().click();
				
				//printCurrentTab();

				ArrayList<String> tabs2;
				do {
					tabs2 = new ArrayList<String>(driver.getWindowHandles());
				} while (tabs2.size() == 1);

				driver.switchTo().window(tabs2.get(1));

				//printCurrentTab();
				
				return new TravelocityHotelInfo(getDriver(),hotel);
			}

		}
		
		return null;
	}

	// -----------------------------------------------------------------------

	public boolean verifyResultsSortedByPrice() {

		List<PackagesSearchResultItem> list = getSearchResultItems();

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).getPrice() < list.get(i - 1).getPrice()) {
				return false;

			}
		}
		return true;
	}

	public boolean verifyPreferedClassSelector() {
		return checkIfClickable("//select[@id=\"cabinClass\"]");
	}

	public boolean verifyDatePickers() {
		return (checkIfClickable("//button[@id=\"hotels-check-in-btn\"]")
				&& checkIfClickable("//button[@id=\"hotels-check-out-btn\"]"));
	}

	public boolean verifySortingBox() {
		return checkIfClickable("//select[@id=\"sort\"]");
	}

	public boolean verifyTravelRestrictionsAlert() {
		return elementIsPresent("//div[@class=\"uitk-flex uitk-experimental-banner\"]");
	}

	public boolean verifyDirectFlightsOnlyCheckbox() {
		return elementIsPresent("//input[@id=\"directFlights\"]");
	}

}
