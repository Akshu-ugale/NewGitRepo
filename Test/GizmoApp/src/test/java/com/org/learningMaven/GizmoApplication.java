package com.org.learningMaven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
}
