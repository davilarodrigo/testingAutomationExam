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

	private List<SearchResultItem> searchResultItemsList;

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
	
	public TravelocityResults selectFirstFlight(int index) {
		selectFlight(index);	
		return new TravelocityResults(getDriver());
	}
	
	public TravelocityFlightInformation selectSecondFlight(int index) {
		selectFlight(index);
//		dissmissAlert();	
						
		return new TravelocityFlightInformation(getDriver());
	}
	
	private void selectFlight(int index) {

		index--;
		
		searchResultItemsList = getSearchResultItems();
		SearchResultItem selectedFlight = searchResultItemsList.get(index);
		WebElement continueButton;

		if (selectedFlight.hasSelectButton()) {
			String selectButton = selectedFlight.getSelectButtonXpath();
			printDetail("selectButton: "+selectButton );
			//la anterior funcion wait and click podria haber estado generando problemas
			
			WebElement button=driver.findElement(By.xpath(selectButton));
			button.click();					
		
		/*	List<WebElement> continueButtons = driver.findElements(By.xpath("(//li[@data-test-id=\"offer-listing\"])//button[@data-test-id=\"select-button-1\"]"));			
			continueButton=continueButtons.get(index);			
			printDetail("antes del wait");
			getWait().until(ExpectedConditions.elementToBeClickable(continueButton));
			continueButton.click();
			printDetail("button clicked!");
		 */
		//	String xpathContinueButton = "(//li[@data-test-id=\"offer-listing\"])[" + index + "]//button[@data-test-id=\"select-button-1\"]";
			//printDetail("xpath continue button: " + xpathContinueButton);			
			//probar despues esta linea
		} else {
			printDetail("Select button is missing in the selected flight, clicking the flight result instead");
			selectedFlight.webElement.click();
			continueButton = findAndClick("//button[@data-test-id=\"select-button\"]");

		}
		
		printDetail("se encontro el continue button");

	}

	private List<SearchResultItem> getSearchResultItems() {

		// List<SearchResultItem> searchResultItemsList;
		if (searchResultItemsList == null) {

			getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));

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
			searchResultItemsList = new ArrayList<>();

			for (int i = 1; i <= numberOfResults; i++) {
				SearchResultItem resultItem = new SearchResultItem(xpath, i, driver);
				searchResultItemsList.add(resultItem);
			}

			printDetail(searchResultItemsList.size() + " flights found");
		}
		return searchResultItemsList;
	}

	public boolean verifySelectButtons() {
		List<SearchResultItem> SearchResultItemsList = getSearchResultItems();

		for (SearchResultItem item : SearchResultItemsList) {

			if (!item.hasSelectButton()) {
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

			if (!item.hasDetailsAndFees()) {
				printDetail("details and fees missing in result number " + item.getIndex());
				return false;
			}

			// item.clickDetailsAndFees();
		}
		printDetail("all details and fees present in every result");
		return true;
	}

	private void clickSortBoxOption(String valueA, String valueB) {
		searchResultItemsList = null;

		getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
		sortDropdown.click();

		String xpathA = "//option[@data-opt-id=\"" + valueA + "\"]";
		String xpathB = "//option[@data-opt-id=\"" + valueB + "\"]";
		WebElement option;

		if (elementIsPresent(xpathA)) {
			option = driver.findElement(By.xpath(xpathA));
		} else {
			if (elementIsPresent(xpathB)) {
				option = driver.findElement(By.xpath(xpathB));
			} else {
				printDetail("results not sorted");
				return;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(option));
		option.click();
		printDetail("results sorted");
	}

	public void sortByShorterDuration() {
		clickSortBoxOption("DURATION_INCREASING", "sort-DURATION_INCREASING");
	}

	public void sortByLongerDuration() {
		clickSortBoxOption("DURATION_DECREASING", "sort-DURATION_DECREASING");
	}

	public boolean verifySortingByShorterDuration() {
		List<SearchResultItem> list = getSearchResultItems();

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getFlightDurationInMinutes() < list.get(i - 1).getFlightDurationInMinutes()) {
				return false;
			}
		}
		return true;
	}

	public boolean verifySortingBoxClickable() {
		if (elementIsPresent(sortDropdown)) {
			getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));
			printDetail("sorting box present and clickable");
			return true;
		}
		return false;
	}

	@FindBy(xpath="//a[@data-test-id=\"forcedChoiceNoThanks\"]")
	WebElement buttonCancelAlert;
	
	public void dissmissAlert() {

		getWait().until(ExpectedConditions.elementToBeClickable(buttonCancelAlert));
		
		if (elementIsPresent("//a[@data-test-id=\"forcedChoiceNoThanks\"]")) {
			findAndClick("//a[@data-test-id=\"forcedChoiceNoThanks\"]");
			System.out.println("alert dismissed");
		}
	}

}
