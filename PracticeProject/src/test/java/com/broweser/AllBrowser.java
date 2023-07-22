package com.broweser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AllBrowser {
	
	WebDriver driver = null;
	
	public WebDriver SelectBrowser(String browser,String Url)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "F:\\vishal\\selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(Url);
		}
		
		return driver;
		
	}

}
