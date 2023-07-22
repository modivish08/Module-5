package com.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginForm 
{
	public void enterUsername(WebDriver driver, String Username) throws InterruptedException 
	{
		driver.findElement(By.id("user-name")).sendKeys(Username);
		Thread.sleep(2000);
	}
	
	public void enterPassword(WebDriver driver, String Password) throws InterruptedException 
	{
		driver.findElement(By.id("password")).sendKeys(Password);
		Thread.sleep(2000);
	}
	
	public void clickLogin(WebDriver driver) throws InterruptedException 
	{
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(2000);
		
//		if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"))
//		{
//			System.out.println("Your Login Test has been passed");
//
//			driver.findElement(By.id("react-burger-menu-btn")).click();
//			Thread.sleep(2000);
//
//			driver.findElement(By.id("logout_sidebar_link")).click();
//			Thread.sleep(2000);
//		}else
//		{
//			System.out.println("Your Login Test has been failed");
//			fail("login failed");
//		}
	}
	
	
	public void Logout(WebDriver driver) throws InterruptedException 
	{
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("logout_sidebar_link")).click();
		Thread.sleep(2000);
	}
	public void closeBrowser(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}


}
