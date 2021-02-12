package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityCruiseInfo extends BasePage {

	Cruise cruise;

	public TravelocityCruiseInfo(WebDriver pDriver, Cruise cruise) {
		super(pDriver);
		this.cruise = cruise;

		getWait().until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href=\"javascript:void(0)\"])[1]")));
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href=\"javascript:void(0)\"])[1]")));
		// TODO Auto-generated constructor stub
	}

	public boolean verifyVisitingCitiesInfo() {
		return elementIsPresent("//div[@class=\"card-content-detail visiting-cities-detail\"]");
	}

	public boolean verifyDepartingCityInfo() {
		return elementIsPresent("//div[@class=\"card-content-detail departure-city\"]");
	}

	public boolean verifySalingDatesInfo() {
		return (elementIsPresent("//span[@class=\"sailing-returning-on\"]")
				&& elementIsPresent("//span[@class=\"sailing-departing-on\"]"));
	}

	public boolean verifyDiscountPrice() {
		// System.out.println(getPrice());
		// System.out.println(cruise.price);
		// System.out.println(getName().toLowerCase());
		// System.out.println(cruise.name.toLowerCase());
		return (cruise.price == getPrice());
	}

	public boolean verifyName() {
		return (getName().toLowerCase().contains(cruise.name.toLowerCase()));
	}

	private String getName() {
		return findByXpath("(//div[@class=\"title-on-ship-image\"])").getText();
	}

	private int getPrice() {
		String str = driver.findElement(By.xpath("(//div[@class=\"price\"]/span[@class=\"updated-price\"])[1]"))
				.getText() + " ";

		char[] chars = str.toCharArray();
		int price = 0;

		for (int i = 1; i < chars.length - 1; i++) {
			if (" ".contains("" + chars[i])) {
				break;
			}
			if ("0123456789".contains("" + chars[i])) {
				price = price * 10;
				price += convertToInt(chars[i]);
			}
		}

		return price;
	}
}
