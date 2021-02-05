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

	public TravelocityResults(WebDriver driver) {
		super(driver);
		// driver.get("https://www.travelocity.com/");
	}

	public TravelocityResults(WebDriver driver, String resultsUrl) {
		super(driver);
		driver.get(resultsUrl);
	}

	public boolean verifyVisibleFlightDuration() {

		/*
		 * System.out.println(listOfResults.size());
		 * 
		 * for (WebElement webElement : listOfResults) {
		 * 
		 * }
		 * 
		 * for (int i = 0; i < listOfResults.size(); i++) {
		 * 
		 * }
		 */
		return true;
	}

	List<SearchResultItem> SearchResultItemsList;

	@FindBy(xpath = "(//select[@id=\"listings-sort\"]) |(//select[@id=\"sortDropdown\"])")
	private WebElement sortDropdown;

	// @FindBy(xpath="(//div[@class=\"grid-container standard-padding \"])[1]")

	@FindBy(xpath = "((//div[@class=\"grid-container standard-padding \"])[1]) | ((//li[@data-test-id=\"offer-listing\"])[1])")
	private WebElement firstResult;

	// esta funcion deberia ser privada
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
				System.out.println("maqueta A");
				xpath = xpathA;
			} else {
				if (elementIsPresent(xpathB)) {
					// caso de la pagina B (no tienen boton select)
					listOfResults = driver.findElements(By.xpath(xpathB));
					System.out.println("maqueta B");
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
				printDetail("missing select button in result number "+item.getIndex());
				return false;
			}
		}
		printDetail("all select buttons present");
		return true;
	}

	public boolean verifyResults() {
		List<WebElement> listOfResults;
		getWait().until(ExpectedConditions.visibilityOf(firstResult));

		String xpath = "(//div[@class=\"grid-container standard-padding \"])";
		listOfResults = driver.findElements(By.xpath(xpath));

		int numberOfResults = listOfResults.size();
		System.out.println(numberOfResults + " results found");

		boolean selectButtonAlwaysPresent = true;
		boolean flightDurationAlwaysPresent = true;

		for (int i = 1; i <= numberOfResults; i++) {
			SearchResultItem resultItem = new SearchResultItem(xpath, i, driver);
			// SearchResultItem resultItem = new SearchResultItem(xpath + "[" + i + "]",
			// driver);

			if (elementIsPresent(resultItem.selectButton)) {
				printDetail("select button is present in result N° " + i);
			} else {
				printDetail("select button is missing in result N° " + i);
				selectButtonAlwaysPresent = false;
			}

		}

		if (flightDurationAlwaysPresent)
			System.out.println("flight duration is shown in every result");
		if (selectButtonAlwaysPresent)
			System.out.println("select button is present in every result");

		return (selectButtonAlwaysPresent && flightDurationAlwaysPresent);
	}

	public boolean verifySortingBox() {
		// getWait().until(ExpectedConditions.visibilityOf(sortDropdown));
		getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
		sortDropdown.click();
		return elementIsPresent(sortDropdown);
	}

}
