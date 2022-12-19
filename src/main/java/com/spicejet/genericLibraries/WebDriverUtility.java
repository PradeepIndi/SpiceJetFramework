package com.spicejet.genericLibraries;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility {
	WebDriver driver;

	public WebDriver openApplication(String browser,ChromeOptions option,String url) {

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(option);
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
			System.out.println("Enter valid browser name");

		driver.manage().window().maximize();
		PropertyFileUtility property=new PropertyFileUtility();
		property.openPropertyFile(ResourceConstantPath.PROPERTYPATH);
		String time = property.getPropertyFileData(PropertyKeys.TIMEOUTS.getStringPropertyKey());
		JavaLibrary jv=new JavaLibrary();
		long longTime = jv.converStringToLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTime));
		driver.get(url);
		property.closeProperty();
		return driver;
	}

}
