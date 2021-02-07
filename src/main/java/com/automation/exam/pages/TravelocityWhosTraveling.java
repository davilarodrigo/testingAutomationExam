package com.automation.exam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelocityWhosTraveling extends BasePage {

	public TravelocityWhosTraveling(WebDriver pDriver) {
		super(pDriver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id="firstname[0]")
	private WebElement firstNameInput;
	
	@FindBy(id="middlename[0]")
	private WebElement middleNameInput;

	@FindBy(id="lastname[0]")
	private WebElement lastNameInput;
	
	@FindBy(id="phone-number[0]")
	private WebElement phoneInput;
		
	@FindBy(id="country_code[0]")
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

