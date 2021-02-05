package com.automation.exam.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityResults extends BasePage {

	private List<SearchResultItem> SearchResultItemsList;

	@FindBy(xpath = "(//select[@id=\"listings-sort\"]) |(//select[@id=\"sortDropdown\"])")
	private WebElement sortDropdown;

	@FindBy(xpath = "((//div[@class=\"grid-container standard-padding \"])[1]) | ((//li[@data-test-id=\"offer-listing\"])[1])")
	private WebElement firstResult;

	public TravelocityResults(WebDriver driver) {
		super(driver);
		// driver.get("https://www.travelocity.com/");
	}

	public TravelocityResults(WebDriver driver, String resultsUrl) {
		super(driver);
		driver.get(resultsUrl);
	}

	private List<SearchResultItem> getSearchResultItems() {

		if (SearchResultItemsList == null) {

			List<WebElement> listOfResults = new ArrayList<>();

			String xpathA = "(//div[@class=\"grid-container standard-padding \"])";
			String xpathB = "(//li[@data-test-id=\"offer-listing\"])";
			String xpath = "";

			getWait().until(ExpectedConditions.visibilityOf(firstResult));
			if (elementIsPresent(xpathA)) {
				// caso de la pagina A (los que tienen boton select)
				listOfResults = driver.findElements(By.xpath(xpathA));
				printDetail("maqueta A");
				xpath = xpathA;
			} else {
				if (elementIsPresent(xpathB)) {
					// caso de la pagina B (no tienen boton select)
					listOfResults = driver.findElements(By.xpath(xpathB));
					printDetail("maqueta B");
					xpath = xpathB;
				}
			}

			if (xpath == "")
				return null;

			int numberOfResults = listOfResults.size();
			// System.out.println(numberOfResults + " results found");

			SearchResultItemsList = new ArrayList<>();

			for (int i = 1; i <= numberOfResults; i++) {
				SearchResultItem resultItem = new SearchResultItem(xpath, i, driver);
				SearchResultItemsList.add(resultItem);
			}

			// System.out.println(SearchResultItemsList.size());
		}
		return SearchResultItemsList;
	}

	public boolean verifySelectButtons() {
		List<SearchResultItem> SearchResultItemsList = getSearchResultItems();

		for (SearchResultItem item : SearchResultItemsList) {
			String button = item.getSelectButtonXpath();
			if (!elementIsPresent(button)) {
				printDetail("missing select button in result number " + item.getIndex());
				return false;
			}
		}
		printDetail("all select buttons present");
		return true;
	}

	public boolean verifyFlightDuration() {
		List<SearchResultItem> SearchResultItemsList = getSearchResultItems();

		for (SearchResultItem item : SearchResultItemsList) {

			if (!item.hasFlightDuration()) {
				printDetail("missing flight duration label in result number " + item.getIndex());
				return false;
			}
		}
		printDetail("all flight duration labels present");
		return true;
	}

	public boolean verifyPriceTag() {
		List<SearchResultItem> SearchResultItemsList = getSearchResultItems();

		for (SearchResultItem item : SearchResultItemsList) {

			if (!item.hasPrice()) {
				printDetail("missing price label in result number " + item.getIndex());
				return false;
			}
		}
		printDetail("all price tags present");
		return true;
	}
	
	public boolean verifySortingBox() {
		// getWait().until(ExpectedConditions.visibilityOf(sortDropdown));
		getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
		sortDropdown.click();
		return elementIsPresent(sortDropdown);
	}

}
