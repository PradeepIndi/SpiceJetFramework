package com.spicejet.ticketbooking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spicejet.genericLibraries.ExcelKeys;
import com.spicejet.genericLibraries.ExcelUtility;
import com.spicejet.genericLibraries.JavaLibrary;
import com.spicejet.genericLibraries.PropertyFileUtility;
import com.spicejet.genericLibraries.PropertyKeys;
import com.spicejet.genericLibraries.ResourceConstantPath;
import com.spicejet.genericLibraries.WebDriverUtility;
import com.spicejet.pom.FlightSelectionPage;
import com.spicejet.pom.HomePage;

public class BangaloreToDelhi {

	public static void main(String[] args) throws InterruptedException {
		JavaLibrary jv=new JavaLibrary();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");

		PropertyFileUtility property=new PropertyFileUtility();
		property.openPropertyFile(ResourceConstantPath.PROPERTYPATH);
		String browser = property.getPropertyFileData(PropertyKeys.BROWSER.getStringPropertyKey());
		String url = property.getPropertyFileData(PropertyKeys.URL.getStringPropertyKey());

		ExcelUtility excelUtil=new ExcelUtility();
		excelUtil.openExcel(ResourceConstantPath.EXCELPATH);
		String from = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 1);
		String to = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 2);
		String depMonth = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 5);
		String depYear = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 6);
		String depDate = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 7);
		String returnMonth = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 8);
		String returnYear = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 9);
		String returnDate = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 10);
		String adultsCount = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 3);
		String childCount = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 4);
		String depFlight = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 11);
		String returnFlight = excelUtil.getExcelData(ExcelKeys.SPICEJET.getStringExcelKey(), 2, 12);



		WebDriverUtility webUtil=new WebDriverUtility();
		WebDriver driver = webUtil.openApplication(browser, option, url);

		HomePage hm=new HomePage(driver);
		hm.clickRoundTripRadioBtn();
		hm.enterFromTxtField(from);
		Thread.sleep(3000);
		hm.enterToTxtFild(to);
		//departure date selection
		hm.selectDate(depMonth, depYear, depDate, driver);
		//return date selection
		hm.selectDate(returnMonth, returnYear, returnDate, driver);

		hm.clickOnPassengerDropdown();
		hm.addAdult(jv.converStringToInt(adultsCount));
		hm.addChildren(jv.converStringToInt(childCount));
		hm.clickSearchFlight();

		FlightSelectionPage flightSelect=new FlightSelectionPage(driver);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5000)) ;
		List<WebElement> depWait = flightSelect.getFlightPrices(depFlight, driver);
		WebElement ele = depWait.get(1);
		wait.until(ExpectedConditions.elementToBeClickable(ele));

		//for selecting minimum amount of departure flight
		List<WebElement> depFlightPrices = flightSelect.getFlightPrices(depFlight, driver);
		List<WebElement> depPriceBtns = flightSelect.getPriceButtons(depFlight, driver);


		WebElement depMinPriceBtn = null;


		for(int i=0;i<depFlightPrices.size();i++) {
			int minPrice=flightSelect.getPriceInInteger(depFlightPrices.get(0).getText());
			if(flightSelect.getPriceInInteger(depFlightPrices.get(i).getText())<=minPrice) {
				minPrice=flightSelect.getPriceInInteger(depFlightPrices.get(i).getText());
				depMinPriceBtn=depPriceBtns.get(i);
				System.out.println(minPrice);
			}

		}

		Thread.sleep(7000);
		depMinPriceBtn.click();
		Thread.sleep(7000); 	
		
		List<WebElement> retWait = flightSelect.getFlightPrices(returnFlight, driver);
		WebElement element = retWait.get(1);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
		//to selecting minimum price of eturn flight

		List<WebElement> returnFlightPrices = flightSelect.getFlightPrices(returnFlight, driver);
		List<WebElement> retPriceBtns = flightSelect.getPriceButtons(returnFlight, driver);
		WebElement returnMinPriceBtn=null;
		for(int i=0;i<returnFlightPrices.size();i++) {
			int minPrice=flightSelect.getPriceInInteger(returnFlightPrices.get(0).getText());
			if(flightSelect.getPriceInInteger(returnFlightPrices.get(i).getText())<=minPrice) {
				minPrice=flightSelect.getPriceInInteger(returnFlightPrices.get(i).getText());
				returnMinPriceBtn=retPriceBtns.get(i);
				System.out.println(minPrice);
			}

		}

		returnMinPriceBtn.click();
		Thread.sleep(5000);
		flightSelect.clickOnContinue();
		
		
		





		excelUtil.closeExcel();
		property.closeProperty();
		//driver.close();


	}

}
