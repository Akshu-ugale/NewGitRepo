package com.org.learningMaven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GizmoApplication extends BestTestJava  {
	
	@Test(priority=1)
	public void verifyTitle()
	{
		
		//launchApplication();
		String expectedTitle="Demo for RNG Tesing by Gaming Testing Laboratory";
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
	@Test(priority=2)
	public void verifyAlphaValue()
	{
		WebElement alpha= driver.findElement(By.xpath("//input[@name='alpha']"));
		alpha.sendKeys("0.05");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String val=alpha.getAttribute("value");
		double value=Double.parseDouble(val);
		//System.out.println(value);
		//alpha.clear();
		
		if(value==0.01 || value==0.05 || value==0.10)
		{
			
			System.out.println("Alpha value verified successfully");
			
		}
		else
		{
			System.out.println("Failed to verfied Alpha value");
		}
		
		//Assert.assertEquals(value, 0.01, 0, "Pass successfully");
		//Assert.assertEquals(value, 0.05, 0, "Pass successfully");
		//Assert.assertEquals(value, 0.10, 0, "Pass successfully");
		
		
		
	}
	
	@Test(priority=4)
	public void totalTestTypes()
	{
		try
		{
		//ArrayList<String> list = new ArrayList<String>();
		int count=0;
		List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		count=checkBoxList.size();
		System.out.println("Number of Tests : "+ count);
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());			
		}
	}
	
	@Test(priority=5)
	public void runWithoutTestType()
	{
		driver.findElement(By.xpath("//input[@name='gName']")).sendKeys("RNG1");
		driver.findElement(By.xpath("//input[@name='VName']")).sendKeys("1");
		WebElement alpha=driver.findElement(By.xpath("//input[@name='alpha']"));
		alpha.clear();
		alpha.sendKeys("0.01");
		driver.findElement(By.xpath("//input[@name='numbersPerDraw']")).sendKeys("2");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@id='filenameupload']"));
		uploadFile.sendKeys("C:\\SeleniumLearning\\DevopsProject\\DevOps\\SQS_Gizmo_RNG\\inputfiles\\3Million_Scaled_1-52.txt");
		uploadFile.sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement submitButton=driver.findElement(By.xpath("//button[@type='submit']"));
		submitButton.click();
		
		
		
	}
}
