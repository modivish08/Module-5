package com.model;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AddtoCart {
	
	public void clickCart(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
		Thread.sleep(2000);
	}
	
	public void ContinueShopping(WebDriver driver) throws InterruptedException 
	{
		driver.findElement(By.cssSelector("button#continue-shopping")).click();
		Thread.sleep(2000);
	}
	
	public void ClickAddtoCart(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		Thread.sleep(2000);
	}
	
	public void checkout(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("first-name")).sendKeys("abc");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input#last-name")).sendKeys("abc");
		Thread.sleep(2000);
		driver.findElement(By.id("postal-code")).sendKeys("1245");
		Thread.sleep(2000);
		
		
		scrollDown(driver);
		Thread.sleep(2000);
		
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		
		String total=driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']")).getText();
		System.out.println("Total Amount..........."+total);
		Thread.sleep(2000);
		
		scrollDown(driver);
		
		driver.findElement(By.id("finish")).click();
		Thread.sleep(2000);
		
		String Complete=driver.findElement(By.xpath("//*[text()='Checkout: Complete!']")).getText();
		if(Complete.equalsIgnoreCase("Checkout: Complete!"))
		{
			driver.findElement(By.id("back-to-products")).click();
			Thread.sleep(2000);
		}
	}
	
	public void scrollUp(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,0)", "");
		Thread.sleep(5000);
	}
	public void scrollDown(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		Thread.sleep(5000);
	}

}
