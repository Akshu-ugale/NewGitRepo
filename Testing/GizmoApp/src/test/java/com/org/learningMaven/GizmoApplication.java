package com.org.learningMaven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GizmoApplication extends BestTestJava  {

	//Pages page=new Pages();
	//CommonMethods commonMethods = new CommonMethods();
	/*
	@Test(priority=1)
	public void verifyTitle()
	{

		//launchApplication();
		String expectedTitle="Demo for RNG Tesing by Gaming Testing Laboratory";
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

	}
	 */
	@Test(priority=2)
	public void verifyAlphaValue()
	{
		try {
			WebElement RNGName = driver.findElement(By.xpath("//input[@name='gName']"));
			RNGName.clear();
			RNGName.sendKeys("RNG1");

			WebElement VName = driver.findElement(By.xpath("//input[@name='VName']"));
			VName.clear();
			VName.sendKeys("1");

			WebElement alpha= driver.findElement(By.xpath("//input[@name='alpha']"));
			alpha.clear();
			alpha.sendKeys("0.06");


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

			WebElement numberDraw = driver.findElement(By.xpath("//input[@name='numbersPerDraw']"));
			numberDraw.clear();
			numberDraw.sendKeys("2");

			WebElement uploadFile = driver.findElement(By.xpath("//input[@id='filenameupload']"));
			uploadFile.sendKeys("C:\\Users\\6962\\Desktop\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\MyApplication\\DevOps\\SQS_Gizmo_RNG\\inputfiles\\3Million_Scaled_1-52.txt");

			WebElement chiSquareTest=driver.findElement(By.xpath("//input[@value='cst']"));
			chiSquareTest.click();

			WebElement butSub = driver.findElement(By.xpath("//button[@value='Submit']"));
			butSub.click();

			String message=driver.switchTo().alert().getText();
			Assert.assertEquals(message,"Level of alpha should be either 0.01 Or 0.05 Or 0.1");
			driver.switchTo().alert().accept(); 


			//WebElement backSubmit=driver.findElement(By.xpath("//button[@value='backSubmit']"));
			//backSubmit.click();
			RNGName.clear();
			VName.clear();
			alpha.clear();
			numberDraw.clear();
			uploadFile.clear();
			chiSquareTest.click();

		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());		
		}


	}


	@Test(priority=3)
	public void selectCheckbox()
	{


		try {

			WebElement checkbox=driver.findElement(By.xpath("//input[@name='selectAl']"));
			if(checkbox.getAttribute("value").equals("checked"))
			{
				checkbox.click();
				System.out.println("All Test get selected");

			}
			else
			{
				System.out.println("Not checked all the values");

			}
			//WebElement checkbox=driver.findElement(By.xpath("//input[@name='selectAl']"));
			checkbox.click();
		} 
		catch (Exception e) 
		{
			System.out.println("Error : "+e.getMessage());			
		}

		finally {

		}

	}

	/*
	@Test(priority=4)

	public void totalTestTypes()
	{
		try
		{
			int count=0;
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@name='testName']"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			count=checkBoxList.size();
			System.out.println("Number of Tests : "+ count);
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());			
		}
		finally {

		}
	} 
	 */

	@Test(priority=5)
	public void runWithoutTestType()
	{
		try {
			WebElement RNGName = driver.findElement(By.xpath("//input[@name='gName']"));
			RNGName.clear();
			RNGName.sendKeys("RNG1");

			WebElement VName = driver.findElement(By.xpath("//input[@name='VName']"));
			VName.clear();
			VName.sendKeys("1");

			WebElement alpha=driver.findElement(By.xpath("//input[@name='alpha']"));
			alpha.clear();
			alpha.sendKeys("0.05");

			WebElement numberDraw = driver.findElement(By.xpath("//input[@name='numbersPerDraw']"));
			numberDraw.clear();
			numberDraw.sendKeys("2");

			WebElement uploadFile = driver.findElement(By.xpath("//input[@id='filenameupload']"));
			uploadFile.sendKeys("C:\\Users\\6962\\Desktop\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\MyApplication\\DevOps\\SQS_Gizmo_RNG\\inputfiles\\3Million_Scaled_1-52.txt");

			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

			WebElement butSub=driver.findElement(By.xpath("//button[@value='Submit']"));
			butSub.click();

			String message=driver.switchTo().alert().getText();
			Assert.assertEquals(message,"Please enter atleast one test");
			driver.switchTo().alert().accept(); 


			RNGName.clear();
			VName.clear();
			alpha.clear();
			numberDraw.clear();
			uploadFile.clear();


		}
		catch (Exception e) {
			System.out.println("Error : "+e.getMessage());	
		}
		finally {

		}
	} 



	@Test(priority=6)
	public void deSelectTest()
	{
		try {
			boolean selected=false;
			WebElement checkbox=driver.findElement(By.xpath("//input[@name='selectAl']"));
			checkbox.click();
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@name='testName']"));

			for (int i = 0; i < checkBoxList.size(); i++) 
			{
				selected=checkBoxList.get(i).isSelected();

			}
			if (selected==true) {
				System.out.println("All the Test are selected");
			}

			checkbox.click();
			List<WebElement> checkBoxList1 = driver.findElements(By.xpath("//input[@name='testName']"));

			for (int i = 0; i < checkBoxList1.size(); i++) 
			{
				selected=checkBoxList1.get(i).isSelected();

			}
			if (selected==false) {
				System.out.println("All the Test are now Deselected");
			}


		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());	
		}
		finally {

		}
	}


	@Test(priority=7)
	public void numbersTypeWithReplacement()
	{
		try
		{
			Boolean enabled=true; 
			WebElement dropdownList = driver.findElement(By.id("numberType"));
			Select dropdown = new Select(dropdownList);
			dropdown.selectByIndex(0);
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for(int i=0;i<checkBoxList.size();i++)
			{
				enabled=checkBoxList.get(i).isEnabled();
				if(enabled==false)
					break;
			}

			if(enabled==true)
				System.out.println("All the tests are enabled for Numbers Type - With Replacement");
			else
				System.out.println("All the tests are not enabled for Numbers Type - With Replacement");


		}
		catch(Exception t)
		{
			System.out.println("Error : "+ t.getMessage() + " -- "+ t.getStackTrace());
		}  
		finally {

		}
	}


	@Test(priority=8)
	public void numbersTypeWithoutReplacement()
	{
		try
		{
			Boolean disabled=true,enabled=false;
			WebElement CST=driver.findElement(By.xpath("//input[@value='cst']"));
			WebElement ICT=driver.findElement(By.xpath("//input[@value='ict']"));
			WebElement ET=driver.findElement(By.xpath("//input[@value='ft']"));
			WebElement KST=driver.findElement(By.xpath("//input[@value='kst']"));
			WebElement DT=driver.findElement(By.xpath("//input[@value='dt']"));
			WebElement PT=driver.findElement(By.xpath("//input[@value='pert']"));
			WebElement CT=driver.findElement(By.xpath("//input[@value='ct']"));
			WebElement AT=driver.findElement(By.xpath("//input[@value='udt']"));

			WebElement dropdownList = driver.findElement(By.id("numberType"));
			Select dropdown = new Select(dropdownList);
			dropdown.selectByIndex(1);
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for(int i=0;i<checkBoxList.size();i++)
			{
				if(checkBoxList.get(i).isEnabled())
				{
					if(checkBoxList.get(i).equals(CST) || checkBoxList.get(i).equals(ICT) || checkBoxList.get(i).equals(ET) || checkBoxList.get(i).equals(KST) || checkBoxList.get(i).equals(DT) || checkBoxList.get(i).equals(PT) || checkBoxList.get(i).equals(CT) || checkBoxList.get(i).equals(AT))
						enabled=true;
					else
						enabled=false;             
				}
				else
				{
					disabled=false;
				}
			}

			if(enabled==true && disabled==false)
				System.out.println("All the tests for Number Type - Without Replacement are disabled except \"Chi-Square Test\", \"Interplay Correlation Test\", \"Equidistribution Test (Frequency Test)\", \"The Kolmogorov-Smirnov Test\", \"Duplicates Test\", \"Permutation Test\", \"Collosion Test\" and \"Adjacency Test\".");
			else
				System.out.println("Tests other than desired (\"Chi-Square Test\", \"Interplay Correlation Test\", \"Equidistribution Test (Frequency Test)\", \"The Kolmogorov-Smirnov Test\", \"Duplicates Test\", \"Permutation Test\", \"Collosion Test\" and \"Adjacency Test\") are also enabled for Number Type - Without Replacement.");
			//dropdown.selectByIndex(0);
		}
		catch(Exception r)
		{
			System.out.println("Error : "+r.getMessage() + " -- "+ r.getStackTrace());              
		}  

	}

	@Test(priority=9)
	public void numbersTypeShuffledNumber()
	{
		try
		{
			Boolean disabled=true,enabled=false;
			WebElement ICT=driver.findElement(By.xpath("//input[@value='ict']"));
			WebElement DT=driver.findElement(By.xpath("//input[@value='dt']"));
			WebElement PT=driver.findElement(By.xpath("//input[@value='pert']"));
			WebElement AT=driver.findElement(By.xpath("//input[@value='udt']"));

			WebElement dropdownList = driver.findElement(By.id("numberType"));
			Select dropdown = new Select(dropdownList);
			dropdown.selectByIndex(2);
			List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for(int i=0;i<checkBoxList.size();i++)
			{
				if(checkBoxList.get(i).isEnabled())
				{
					if(checkBoxList.get(i).equals(ICT) || checkBoxList.get(i).equals(DT) || checkBoxList.get(i).equals(PT) || checkBoxList.get(i).equals(AT))
						enabled=true;
					else
						enabled=false;             
				}
				else
				{
					disabled=false;
				}
			}

			if(enabled==true && disabled==false)
				System.out.println("All the tests for Number Type - Shuffled Number are disabled except \"Interplay Correlation Test\", \"Duplicates Test\", \"Permutation Test\", and \"Adjacency Test\".");
			else
				System.out.println("Tests other than desired (\"Interplay Correlation Test\", \"Duplicates Test\", \"Permutation Test\", and \"Adjacency Test\") are also enabled for Number Type - Shuffled Number.");
			dropdown.selectByIndex(0);
		}
		catch(Exception r)
		{
			System.out.println("Error : "+r.getMessage() + " -- "+ r.getStackTrace());              
		}   

	}


	@Test(priority=10)
	public void getResult()
	{
		try {
			WebElement RNGName = driver.findElement(By.xpath("//input[@name='gName']"));
			RNGName.clear();
			RNGName.sendKeys("RNG1");

			WebElement VName = driver.findElement(By.xpath("//input[@name='VName']"));
			VName.clear();
			VName.sendKeys("1");

			WebElement alpha=driver.findElement(By.xpath("//input[@name='alpha']"));
			alpha.clear();
			alpha.sendKeys("0.05");

			WebElement numberDraw = driver.findElement(By.xpath("//input[@name='numbersPerDraw']"));
			numberDraw.clear();
			numberDraw.sendKeys("2");

			WebElement uploadFile = driver.findElement(By.xpath("//input[@id='filenameupload']"));
			uploadFile.sendKeys("C:\\Users\\6962\\Desktop\\eclipse-jee-oxygen-3a-win32-x86_64\\eclipse\\MyApplication\\DevOps\\SQS_Gizmo_RNG\\inputfiles\\3Million_Scaled_1-52.txt");

			WebElement runUpdown=driver.findElement(By.xpath("//input[@value='rurdt']"));
			runUpdown.click();

			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			WebElement butSub=driver.findElement(By.xpath("//button[@value='Submit']"));
			butSub.click();

			WebElement backSubmit=driver.findElement(By.xpath("//button[@value='backSubmit']"));
			backSubmit.click();

		}

		catch (Exception e) {
			System.out.println("Error : "+ e.getMessage() + " -- "+ e.getStackTrace());
		}




	}

}
