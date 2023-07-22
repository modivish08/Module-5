package com.Demo1;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class KeyDrivenDemo {

	@DataProvider(name="credential")
	public static Object[][] readExcel() throws InvalidFormatException, IOException
	{
		Object[][] data= null;
		
		//Excel filePath
		String filePath = "F:\\vishal\\selenium\\DataDrivenDemo.xlsx";
		
		//intialize in File class
		File file = new File(filePath);
		
		//open Excel File
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		//open perticular sheet
		Sheet sheet = workbook.getSheet("Sheet2");
		
		//Count physical row
		int nrows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Rows....."+nrows);
		
		//used jagged Array for fetch data
		data=new Object[nrows][];
		
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
//				cell.setCellType(CellType.STRING);
				
				//fetch value from Excel
				data[i][j] = cell.getStringCellValue();
			}
		}
		
		
		return data;
	}
	
	WebDriver driver = null;
	
	@Test(dataProvider = "credential")
	public void Login(String Keyword) throws InvalidFormatException, IOException, InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "F:\\vishal\\selenium\\chromedriver_win32\\chromedriver.exe");
		
		if(Keyword.equalsIgnoreCase("open Browser"))
		{
			driver = new ChromeDriver();
		}
		
		else if(Keyword.equalsIgnoreCase("enter url"))
		{
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			Thread.sleep(3000);
		}
		
		else if(Keyword.equalsIgnoreCase("enter username"))
		{
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			Thread.sleep(2000);
		}
		
		else if(Keyword.equalsIgnoreCase("enter password"))
		{
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			Thread.sleep(2000);
		}
		
		else if(Keyword.equalsIgnoreCase("click login"))
		{
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
		}
		
		else if(Keyword.equalsIgnoreCase("close browser"))
		{
			Thread.sleep(2000);
			driver.close();
		}
					

	}

}
