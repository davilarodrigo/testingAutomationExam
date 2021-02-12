package com.automation.exam.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.exam.pages.TravelocityHome.Tabs;

public class TravelocityCruisesResults extends BasePage {

	public TravelocityCruisesResults(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
		try {
			getWait().until(ExpectedConditions.visibilityOf(mainSection));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FindBy(xpath = "//section[@role=\"main\"]")
	WebElement mainSection;

	private List<CruiseResult> getResultsList() {

		List<CruiseResult> resultsList = new ArrayList<>();
		List<WebElement> htmlList = new ArrayList<>();

		String xpath = "(//section[@role=\"main\"]//div[@class=\"flex-card\"])";
		htmlList = driver.findElements(By.xpath(xpath));
		getWait().until(ExpectedConditions.elementToBeClickable(htmlList.get(1)));

		WebElement elem;

		for (int i = 1; i <= htmlList.size(); i++) {

			elem = findByXpath(xpath + "[" + i + "]");
			if (elementIsPresent(elem)) {
				CruiseResult item = new CruiseResult(xpath + "[" + i + "]", driver, i);
				// antes aca habia un if que revisaba si el paquete tenia precio, y si no tenia,
				// no lo agregaba(tal vez para los cruceros no sea necesario)
				resultsList.add(item);
			}
		}
		return resultsList;
	}
	// -------------------------------------------------------------------------------------------------------------

	private void clickCruiseLenghtOption(int nights) {
		WebElement radioButton = null;
		if (nights <= 6) {
			radioButton = findAndClick("(//input[@name=\"length-6-9\"])[2]");
			return;
		}

		if (nights <= 10) {
			radioButton = findAndClick("(//input[@name=\"length-10-14\"])[2]");
			return;
		}

		if (nights <= 15) {
			radioButton = findAndClick("(//input[@name=\"length-15\"])[2]");
			return;
		}

	}

	public void selectCruiseLenght(int nights) {
		clickCruiseLenghtOption(nights);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id=\"destination-select\"]")));
		// WebElement elem=findById("destination-toggle");

	}

	public TravelocityCruiseInfo selectCruiseWithBiggestDiscount() {
		List<CruiseResult> list = getResultsList();

		List<CruiseResult> cruisesWithDiscount = new ArrayList<>();

		int biggestDiscount = 0;
		int absDiscount = 0;
 
		CruiseResult selectedCruise = list.get(0);

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).hasDiscount()) {
				absDiscount = list.get(i).getAbsoluteDiscount();
				// System.out.println(absDiscount);
				if (absDiscount > biggestDiscount) {
					biggestDiscount = absDiscount;
					selectedCruise = list.get(i);
				}
			}
		}

		Cruise cruise = new Cruise();

		cruise.price = selectedCruise.getPrice();
		cruise.priceWithoutDiscount = selectedCruise.getNoDiscountPrice();
		cruise.name = selectedCruise.getCruiseName();
		cruise.departingCity = selectedCruise.getDepartingCity();
		cruise.departingDate = selectedCruise.getDepartingDate();
		cruise.ReturningDate = selectedCruise.getReturningDate();

		System.out.println("biggest discount found: " + biggestDiscount);
		String linkXpath = selectedCruise.getContinueButtonXpath();
		findAndClick(linkXpath);

		// printCurrentTab();
		ArrayList<String> tabs2;
		do {
			tabs2 = new ArrayList<String>(driver.getWindowHandles());
		} while (tabs2.size() == 1);
		driver.switchTo().window(tabs2.get(1));
		// printCurrentTab();
		
		return new TravelocityCruiseInfo(getDriver(), cruise);
	}

	// -------------------------------------------------------------------------------------------------------------

	public boolean verifyCruisesWithDiscount() {
		List<CruiseResult> list = getResultsList();

		int cruisesWithDiscount = 0;

		for (int i = 0; i < list.size(); i++) {
			// System.out.println("price: " + list.get(i).getPrice());
			// System.out.println("has discount: " + list.get(i).hasDiscount());
			// System.out.println("no discount price: " + list.get(i).getNoDiscountPrice());
			if (list.get(i).hasDiscount()) {
				cruisesWithDiscount++;
			}
		}

		System.out.println("cruises with discount: " + cruisesWithDiscount);
		return (cruisesWithDiscount != 0);
	}

	public boolean verifyDestinationTogglePresent() {
		return elementIsPresentById("destination-toggle");
	}

	public boolean verifyDatesTogglePresent() {
		return elementIsPresentById("month-toggle");
	}

	public boolean verifyTravelersTogglePresent() {
		return elementIsPresentById("travelers-select");
	}

}
