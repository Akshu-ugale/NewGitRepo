package com.org.learningMaven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SmokeTests extends BestTestJava  
{

	//Pages page=new Pages();
	//CommonMethods commonMethods = new CommonMethods();
	
	@Test(priority=1)
	public void verifyTitle()
	{         
		String expectedTitle="Demo for RNG Tesing by Gaming Testing Laboratory";
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}

	@Test(priority=2)

	public void totalTestTypes()
	{
		try
		{
			int count=0;
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@name='testName']"));
			//commonMethods.Delay(30, TimeUnit.SECONDS);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			count=checkBoxList.size();
			System.out.println("Total Number of Tests Types : "+ count);
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());                    
		}
	} 

}
