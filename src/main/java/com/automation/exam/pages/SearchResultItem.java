package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultItem extends WebComponent {

	public WebElement webElement;
	private WebElement selectButton;
	private  WebElement flightDuration;
	private  WebElement detailsAndFees;
	private int durationInMinutes = 0;

	private String xpath;
	private int index;

	public SearchResultItem(String xpath, int index, WebDriver driver) {

		xpath += "[" + index + "]";
		this.webElement = driver.findElement(By.xpath(xpath));
		this.index = index;
		this.xpath = xpath;
		this.driver = driver;
		hasFlightDuration();

	}

	public int getIndex() {
		return index;
	}

	public String getXpath() {
		return xpath;
	}

	public WebElement getAsWebElement() {
		return webElement;
	}

	public String getSelectButtonXpath() {
		return xpath + "//button[@data-test-id=\"select-button\"]";
	}

	public WebElement getSelectButton() {
		selectButton = driver.findElement(By.xpath(xpath + "//button[@data-test-id=\"select-button\"]"));
		return selectButton;
	}

	public boolean hasDetailsAndFees() {
		String xpath1 = "//a[@data-test-id=\"flight-details-link\"]";

		if (elementIsPresent(xpath + xpath1)) {
			detailsAndFees = driver.findElement(By.xpath(xpath + xpath1));
			return true;
		}
		return false;
	}

	public WebElement getDetailsAndFees() {
		if (hasDetailsAndFees()) {
			return detailsAndFees;
		}
		return null;
	}

	public void clickDetailsAndFees() {
		if (hasDetailsAndFees()) {
			detailsAndFees.click();
		}
	}

	private int convertToInt(char a) {
		return Character.getNumericValue(a);  
	}

	private int convertToInt(char a, char b) {
		return convertToInt(a)*10+convertToInt(b);
	}

	private void calculateFlightDuration(String value) {
		value+="     ";
		char[] chars = value.toCharArray();

		int hours = 0;
		int minutes = 0;

		if (chars[1] == 'm') {
			minutes = convertToInt(chars[0]);
		}
		if (chars[2] == 'm') {
			minutes = convertToInt(chars[0],chars[1]);
		}

		if (chars[1] == 'h') {
			hours = convertToInt(chars[0]);
			if (chars[4] == 'm') {
				minutes = convertToInt(chars[3]);
			}
			if (chars[5] == 'm') {
				minutes = convertToInt(chars[3] ,chars[4]);
			}
		}
		if (chars[2] == 'h') {
			hours = convertToInt(chars[0],chars[1]);
			if (chars[5] == 'm') {
				minutes = convertToInt(chars[4]);
			}
			if (chars[6] == 'm') {
				minutes = convertToInt(chars[4] ,chars[5]);
			}
		}

		durationInMinutes=hours*60+minutes;
	}

	public int getFlightDurationInMinutes() {
		return durationInMinutes;
	}

	public boolean hasFlightDuration() {

		String xpath1 = "//div[@data-test-id=\"journey-duration\"]";
		String xpath2 = "//span[@data-test-id=\"duration\"]";

		if (elementIsPresent(xpath + xpath1)) {
			flightDuration = driver.findElement(By.xpath(xpath + xpath1));
			//System.out.println(flightDuration.getText());
			calculateFlightDuration(flightDuration.getText());
			//System.out.println(durationInMinutes);
			return true;
		}
		if (elementIsPresent(xpath + xpath2)) {
			flightDuration = driver.findElement(By.xpath(xpath + xpath2));			
			//System.out.println(flightDuration.getText());
			calculateFlightDuration(flightDuration.getText());
			//System.out.println(durationInMinutes);
			return true;
		}
		return false;
	}

	public WebElement getFlightDuration() {

		if (hasFlightDuration())
			return flightDuration;
		return null;
	}

}
