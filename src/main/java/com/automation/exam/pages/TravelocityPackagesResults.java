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

	@FindBy
	
	@FindBy(xpath = "//select[@id=\"sort\"]")
	WebElement sortDropdown;

	private List<PackagesSearchResultItem> resultsList;

	private void clickSortBoxOption(String value) {
		waitAndClick(sortDropdown);
		String option = "//option[@value=\"" + value + "\"]";
		findAndClick(option);

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

			for (int i = 1; i < htmlList.size(); i++) {
				resultsList.add(new PackagesSearchResultItem(xpath, i, driver));
			}

		}
		return resultsList;
	}

	// -----------------------------------------------------------------------

	public void sortByPrice() {
		clickSortBoxOption("PRICE_LOW_TO_HIGH");
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
