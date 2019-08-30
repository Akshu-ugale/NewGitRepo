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


public class sanityTests extends BestTestJava  {
	
	Pages page=new Pages();
	CommonMethods commonMethods=new CommonMethods();
	
	@Test(priority=11)
	public void additionInApp()
	{
		try
		{
			String text="";int textLength=0;
			WebElement scrollData = driver.findElement(By.xpath("//marquee[@direction='up']//b"));
			text = scrollData.getText();
			textLength=text.trim().length();
			System.out.println(text);
			System.out.println(textLength);
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
		boolean correct=false;
		String result="";
		String[][]values =new String[3][2];
		values[0][0]="0.01";
		values[0][1]="Pass";
		values[1][0]="0.05";
		values[1][1]="Pass";
		values[2][0]="0.10";
		values[2][1]="Pass";
		
		
		
		String path= System.getProperty("user.dir")+"\\src\\resources\\inputData.txt";
		 commonMethods.ClearAll();
		
         page.GetRNGName().sendKeys("RNG1");
         
         page.GetVersionName().sendKeys("1");
         
         page.GetAlpha().sendKeys("0.01");
         commonMethods.Delay(30,TimeUnit.SECONDS);
         String val=page.GetAlpha().getAttribute("value");
         double value=Double.parseDouble(val);
         WebElement dropDownList = page.GetNumberTypeDropDownList();
         Select dropdown = new Select(dropDownList);
         dropdown.selectByIndex(0);
         
         page.GetNumbersPerDraw().sendKeys("2");
         page.GetUploadFile().sendKeys(path);
         commonMethods.Delay(30,TimeUnit.SECONDS);
        
         page.GetCST().click();
         //Click Submit Button
         page.GetSubmitButton().click();
         
         commonMethods.Delay(30,TimeUnit.SECONDS);
         
         //Get the result
         result= page.GetChiSquareTestResult().getText();
         
         for(int i=0;i<values.length;i++)
         {
        	 for(int j=0;j<values.length;j++)
        	 {
        		 if(Double.parseDouble(values[i][0])==value)
        			 if(values[i][1].equals(result))
        			 {
        				 correct=true;
        				 break;
        			 }
        				 
        	 }
        	 
         }
         
         if(correct==true)
        	 System.out.println("The Test has been successfully passed for the given input data.");
         else
        	 System.out.println("The Test has been failed for the given input data.");
         
         System.out.println("Path = "+ path);
	}
}