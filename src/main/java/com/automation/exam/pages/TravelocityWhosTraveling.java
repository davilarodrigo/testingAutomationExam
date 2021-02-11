package com.automation.exam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityWhosTraveling extends BasePage {

	public TravelocityWhosTraveling(WebDriver pDriver) {
		super(pDriver);
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(By.id("firstname\"")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated constructor stub
	}

	@FindBy(id="firstname[0]")
	private WebElement firstNameInput;
	
	@FindBy(xpath="//span[contains(text(),\"Middle name\")]/following-sibling::input")
	private WebElement middleNameInput;

	@FindBy(id="lastname[0]")
	private WebElement lastNameInput;
	
	@FindBy(xpath="//*[@data-cko-rfrr-id=\"MCKO.CKO.Phone.NumberEntered\"] | //*[@id=\"phone-number[0]\"]")
	private WebElement phoneInput;
		
	@FindBy(xpath="//*[@data-cko-rfrr-id=\"MCKO.CKO.PHONECOUNTRYCODE\"] | //*[@id=\"country_code[0]\"] ")
	private WebElement phoneCodeSelect;
	
	public boolean verifyMiddleNameInput() {
		return elementIsPresent(middleNameInput);
	}
	
	public boolean verifyLastname() {
		return elementIsPresent(lastNameInput);
	}
	
	public boolean verifyPhoneInput() {
		return elementIsPresent(phoneInput);
	}
	
	public boolean verifyPhoneCodeSelect() {
		return elementIsPresent(phoneCodeSelect);
	}
	
	public boolean verifyFirstNameInput() {
		return elementIsPresent(firstNameInput);
	}
	
	
}

