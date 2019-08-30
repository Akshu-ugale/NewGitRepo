package com.org.learningMaven;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class CommonMethods extends Pages
{
	//Method Name: Delay
	//Method Description: Wait implicitly for a given period of time
	
	public void Delay(int delayTime,TimeUnit delayunit)
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);                          
	}

	//Method Name: ClearAll
	//Method Description: Clear all the input elements
	
	public void ClearAll()
	{
		boolean selected=false;
		super.GetRNGName().clear();
		super.GetVersionName().clear();
		super.GetAlpha().clear();
		super.GetNumbersPerDraw().clear();
		super.GetUploadFile().clear();
		WebElement numberType=super.GetNumberTypeDropDownList();
		Select dropDown=new Select(numberType);
		dropDown.selectByIndex(0);
		List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
		for(int i=0;i<checkBoxList.size();i++)
			if(checkBoxList.get(i).isSelected())
			{
				selected=true;
				break;
			}
		if(selected==true)
			super.GetSelectAllCheckBox().click();   //will select all checkboxes
		super.GetSelectAllCheckBox().click();   //will deselect all checkboxes
	}
}
