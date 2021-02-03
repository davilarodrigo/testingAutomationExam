package com.automation.exam;
import org.apache.xerces.util.URI.MalformedURIException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class MyDriver {

	private WebDriver driver;

	public MyDriver(String browser) {
		switch (browser) {
		case "opera":		
			System.setProperty("webdriver.opera.driver", "C:\\Users\\Familia\\AppData\\Local\\Programs\\Opera\\operadriver.exe");
			driver = new OperaDriver();			
			break;
		case "firefox":
			//codigo web driver firefox
			break;			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			break;
		}
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
}