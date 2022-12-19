package com.spicejet.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class HomePage {
	@FindBy(xpath = "//div[text()='round trip']/../..//*[name()='svg']") private WebElement roundTripRadioBtn;
	@FindBy(xpath = "//div[text()='From']/..//input") private WebElement fromTxtBx;
	@FindBy(xpath = "//div[text()='To']/..//input") private WebElement toTxtBX;
	@FindBy(xpath = "//div[text()='Departure Date']") private WebElement departureDate;
	
	@FindBy(xpath = "//div[text()='Passengers']/..//*[name()='svg' and @data-testid='svg-img']") private WebElement passengerDropDown;
	
	@FindBy(xpath = "//div[@data-testid='Adult-testID-plus-one-cta']") private WebElement addAdult;
	@FindBy(xpath = "//div[@data-testid='Children-testID-plus-one-cta']") private WebElement addChildren;
	@FindBy(xpath = "//div[text()='Search Flight']/..") private WebElement searchFlight;
	
	//dynamic xpath for selecting the departure and return date
	String datePath="//div[@data-testid='undefined-month-%s-%s']//div[@data-testid='undefined-calendar-day-%s']";
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method converts dynamix xpath to actual xpath and useful for selecting departure date and return date
	 * @param month
	 * @param year
	 * @param date
	 * @param driver
	 */
	public void selectDate(String month,String year,String date,WebDriver driver) {
		String actualXpath = String.format(datePath, month,year,date);
		driver.findElement(By.xpath(actualXpath)).click();
	}
	
	public void clickRoundTripRadioBtn() {
		roundTripRadioBtn.click();
	}

	public void enterFromTxtField(String from) {
		fromTxtBx.sendKeys(from);
	}

	public void enterToTxtFild(String to) {
		toTxtBX.sendKeys(to);
	}

	public void clickDepartureDate() {
		departureDate.click();
	}
	
	public void clickOnPassengerDropdown() {
		passengerDropDown.click();
	}
	/**
	 * This method will click on the add adults element multiple times
	 * in the method argument pass How Many adult passengers need to add 
	 * @param j
	 */
	public void addAdult(int countOfAdults) {
		for(int i=1;i<=(countOfAdults-1);i++) {
			addAdult.click();
		}
	}
	/**
	 * This method will click on the add childrens element multiple times
	 * 
	 * @param countOfChildrens
	 */
	public void addChildren(int countOfChildrens) {
		for(int i=1;i<=countOfChildrens;i++) {
			addChildren.click();
		}
	}
	
	public void clickSearchFlight() {
		passengerDropDown.click();
		searchFlight.click();
	}
	

}
