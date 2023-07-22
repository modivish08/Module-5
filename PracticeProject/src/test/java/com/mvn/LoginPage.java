package com.mvn;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.broweser.AllBrowser;
import com.model.AddtoCart;
import com.model.LoginForm;

public class LoginPage {
	
	
	WebDriver driver=null;
	LoginForm loginForm = null;
	AddtoCart cart=null;
	
	@BeforeClass
	public void Beforeclass() throws InterruptedException
	{
		driver = new AllBrowser().SelectBrowser("chrome", "https://www.saucedemo.com/");
		Thread.sleep(2000);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void BeforeMethod()
	{
		loginForm = new LoginForm();
		cart=new AddtoCart();
	}
	 
	@Test(priority=0)
	public void loginDetails() throws InterruptedException 
	{
		loginForm.enterUsername(driver, "standard_user");
		loginForm.enterPassword(driver, "secret_sauce");
		loginForm.clickLogin(driver);
	}
	
	@Test(priority=1)
	public void cartDetails() throws InterruptedException 
	{
		cart.clickCart(driver);
		cart.ContinueShopping(driver);
		cart.ClickAddtoCart(driver);
		cart.checkout(driver);
	}
	
	
	
	@AfterClass
	public void afterClass() throws InterruptedException 
	{
		Thread.sleep(2000);
		loginForm.Logout(driver);
		driver.close();
	}

}
