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


public class SanityTests extends BestTestJava  {

//	Pages page=new Pages();
	//CommonMethods commonMethods=new CommonMethods();

	@Test(priority=11)
	public void additionInApp()
	{
		try
		{
			String text="";
			int textLength=0;
			WebElement scrollData = driver.findElement(By.xpath("//marquee[@direction='up']//b"));
			text = scrollData.getText();
			textLength=text.trim().length();
			System.out.println(text);
			System.out.println(textLength);
			//commonMethods.Delay(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(textLength!=525)
			{
				System.out.println("The data in the information given has been changed.");
			}
			else
			{
				System.out.println("The data in the information given is not changed.");
			}                    
		}
		catch(Exception r)
		{
			System.out.println("Error : "+ r.getMessage() + " --- " + r.getStackTrace());              
		}
	}




	@Test(priority=12)
	public void ChiSquareTest()
	{
		
		
	}

}
