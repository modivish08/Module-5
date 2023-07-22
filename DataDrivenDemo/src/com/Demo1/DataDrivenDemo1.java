package com.Demo1;


import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DataDrivenDemo1 {
	
	@Test
	public static String[][] readExcel() throws InvalidFormatException, IOException
	{
		String[][] data= null;
		
		//Excel filePath
		String filePath = "F:\\vishal\\selenium\\DataDrivenDemo.xlsx";
		
		//intialize in File class
		File file = new File(filePath);
		
		//open Excel File
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		//open perticular sheet
		Sheet sheet = workbook.getSheet("Sheet1");
		
		//Count physical row
		int nrows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Rows....."+nrows);
		
		//used jagged Array for fetch data
		data=new String[nrows][];
		
		for(int i=0;i<nrows;i++) 
		{
			Row row = sheet.getRow(i);
			
			//count no. of columns
			int ncolumns = row.getPhysicalNumberOfCells();
			System.out.println("no of columns..."+ncolumns);
			
			// column initialize as per Row
			data[i] = new String[ncolumns];
			
			for(int j=0;j<ncolumns;j++)
			{
				//select perticular cell
				Cell cell = row.getCell(j);
				
				// make every cell as String data type
				cell.setCellType(CellType.STRING);
				
				//fetch value from Excel
				data[i][j] = cell.getStringCellValue();
			}
		}
		
		
		return data;
	}
	
	WebDriver driver = null;
	
	@Test
	public void Test() throws InvalidFormatException, IOException, InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "F:\\vishal\\selenium\\chromedriver_win32\\chromedriver.exe");
		
		String[][] data = readExcel();
		
		for(int i=0;i<data.length;i++) 
		{
			driver = new ChromeDriver();
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			Thread.sleep(3000);
			
			driver.findElement(By.id("user-name")).sendKeys(data[i][0]);
			Thread.sleep(2000);

			driver.findElement(By.id("password")).sendKeys(data[i][1]);
			Thread.sleep(2000);

			driver.findElement(By.id("login-button")).click();
			Thread.sleep(2000);
			
			if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"))
			{
				System.out.println("Your Login Test has been passed");

				driver.findElement(By.id("react-burger-menu-btn")).click();
				Thread.sleep(2000);

				driver.findElement(By.id("logout_sidebar_link")).click();
				Thread.sleep(2000);
			}else
			{
				System.out.println("Your Login Test has been failed");
				fail("login failed");
			}
			
			Thread.sleep(2000);
			driver.close();
			
		
		

		}
			

}
}
