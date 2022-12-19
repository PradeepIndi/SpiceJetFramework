package com.spicejet.pom;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.spicejet.genericLibraries.JavaLibrary;


public class FlightSelectionPage {
	
	@FindBy(xpath = "//div[@data-testid='continue-search-page-cta']") private WebElement continueBtn;
	
	//dynamic xpath for prices with respect to flight name
	String priceXpath="//div[text()='%s']/../../../..//span/parent::div";
	String priceBtn="//div[text()='%s']/../../../..//span/parent::div/preceding-sibling::div";


	public FlightSelectionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	/**
	 * This method useful for getting the prices with respect to flight name
	 * @param flightName
	 * @param driver
	 * @return
	 */
	public List<WebElement> getFlightPrices(String flightName,WebDriver driver) {
		String actXpath = String.format(priceXpath, flightName);
		return driver.findElements(By.xpath(actXpath));
		
	}
	
	public List<WebElement> getPriceButtons(String flightName,WebDriver driver) {
		String actXpath = String.format(priceBtn, flightName);
		return driver.findElements(By.xpath(actXpath));
		
	}
	/**
	 * in this method pass String from the price List and it will reove charecters and spaces and convers it into Interger
	 * @param price
	 */
	public int getPriceInInteger(String price) {
		
		String conv = price.replaceAll(" ", "").replaceAll(",", "").substring(1);
		JavaLibrary jv=new JavaLibrary();
		return jv.converStringToInt(conv);
		
	}
	public void clickOnContinue() {
		continueBtn.click();
	}
	
	
}
