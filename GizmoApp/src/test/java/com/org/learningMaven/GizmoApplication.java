package com.org.learningMaven;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.io.File;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.util.URI;


public class GizmoApplication extends BestTestJava  {
	
	Pages page = new Pages();
	CommonMethods commonMethods=new CommonMethods();

    @Test(priority=3)
    public void verifyAlphaValue()
    {
           try
           {        	   
        	      
        	      String path= System.getProperty("user.dir")+"\\src\\resources\\out_scaled_set_2.txt";        	     
        	      String alphaValue="0.06";
        	      
		          commonMethods.ClearAll();                
		          
                  page.GetRNGName().sendKeys("RNG1");

                  page.GetVersionName().sendKeys("1");

                  page.GetAlpha().sendKeys(alphaValue);

                  commonMethods.Delay(30,TimeUnit.SECONDS);
                  String val=page.GetAlpha().getAttribute("value");
                  double value=Double.parseDouble(val);
                  
                  if(value==0.01 || value==0.05 || value==0.10)
                  {

                        System.out.println("Alpha value verified successfully");
                  }
                  else
                  {
                        System.out.println("The given alpha value "+alphaValue+" does not match values \"0.01\" or \"0.05\" or \"0.10\".");
                  }
                  
                  page.GetNumbersPerDraw().sendKeys("2");

                  page.GetUploadFile().sendKeys(path); 

                  page.GetCST().click(); 

                  page.GetSubmitButton().click();

                  String message=driver.switchTo().alert().getText();
                  Assert.assertEquals(message,"Level of alpha should be either 0.01 Or 0.05 Or 0.1");
                  driver.switchTo().alert().accept(); 

		          commonMethods.ClearAll();                
           } catch (Exception e) 
           {
                  System.out.println("Error : "+e.getMessage());              
           }
    }


    @Test(priority=4)
    public void selectCheckbox()
    {
           try
           {
        	   boolean selected=false;
              
              if(page.GetSelectAllCheckBox().isSelected())
              {
            	  System.out.println("\"Select All\" checkBox is selected");
              }
              else
              {
                    page.GetSelectAllCheckBox().click();
                    System.out.println("\"Select All\" checkBox is selected");
              }
              
              List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@name='testName']"));
        	  for(int i=0;i<checkBoxList.size();i++)
        		  if(checkBoxList.get(i).isSelected())                			  
        		  {
        			  selected=true;
        		  }
        		  else
        		  {
        			  selected=false;
        			  break;
        		  }

              if(selected==true)
            	  System.out.println("All the Tests are selected by checking \"Select All\" checkBox");
              else
            	  System.out.println("All the Tests are not selected by checking \"Select All\" checkBox");
              
              page.GetSelectAllCheckBox().click();
           } 
           catch (Exception e) 
           {
                  System.out.println("Error : "+e.getMessage());                    
           }
    }

    @Test(priority=5)
    public void runWithoutTestType()
    {
           try {
        	      
        	      String path= System.getProperty("user.dir")+"\\src\\resources\\out_scaled_set_2.txt";
        	      commonMethods.ClearAll();
     
                  page.GetRNGName().sendKeys("RNG1");

                  page.GetVersionName().sendKeys("1");

                  page.GetAlpha().sendKeys("0.05");

                  page.GetNumbersPerDraw().sendKeys("2");

                  page.GetUploadFile().sendKeys(path);

                  commonMethods.Delay(30,TimeUnit.SECONDS);

                  page.GetSubmitButton().click();

                  String message=driver.switchTo().alert().getText();
                  Assert.assertEquals(message,"Please enter atleast one test");
                  driver.switchTo().alert().accept();                   
                  
                  commonMethods.ClearAll();
           }
           catch (Exception e) {
                  System.out.println("Error : "+e.getMessage());       
           }
    } 



    @Test(priority=6)
    public void deSelectTest()
    {
           try {
                  boolean selected=false;
                  
                  page.GetSelectAllCheckBox().click();
                  List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@name='testName']"));

                  for (int i = 0; i < checkBoxList.size(); i++) 
                  {
                        selected=checkBoxList.get(i).isSelected();
                        if(selected==false)
                        	break;
                  }
                  if (selected==true) {
                        System.out.println("All the Tests are selected.");
                  }
                  
                  page.GetSelectAllCheckBox().click();
                  List<WebElement> checkBoxList1 = driver.findElements(By.xpath("//input[@name='testName']"));

                  for (int i = 0; i < checkBoxList1.size(); i++) 
                  {
                        selected=checkBoxList1.get(i).isSelected();

                  }
                  if (selected==false) {
                        System.out.println("All the Test are now Deselected.");
                  }
                  
                  page.GetSelectAllCheckBox().click(); 
           } catch (Exception e) {
                  System.out.println("Error : "+e.getMessage());       
           }
    }
    
    
	 @Test(priority=7)
	 public void numbersTypeWithReplacement()
	 {
	        try
	        {
	               Boolean enabled=true; 
	               WebElement dropDownList = page.GetNumberTypeDropDownList();
	               Select dropdown = new Select(dropDownList);
	               dropdown.selectByIndex(0);
	               List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
	               commonMethods.Delay(30,TimeUnit.SECONDS);
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
	 }

	
	@Test(priority=8)
	public void numbersTypeWithoutReplacement()
	{
		try
		{
			Boolean disabled=true,enabled=false;
		
			WebElement dropdownList = page.GetNumberTypeDropDownList();
			Select dropdown = new Select(dropdownList);
			dropdown.selectByIndex(1);
			List<WebElement> checkBoxList = BestTestJava.driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
			commonMethods.Delay(30,TimeUnit.SECONDS); 
			for(int i=0;i<checkBoxList.size();i++)
			{
				if(checkBoxList.get(i).isEnabled())
				{
					if(checkBoxList.get(i).equals(page.GetCST()) || checkBoxList.get(i).equals(page.GetICT()) || checkBoxList.get(i).equals(page.GetET()) || checkBoxList.get(i).equals(page.GetKST()) || checkBoxList.get(i).equals(page.GetDT()) || checkBoxList.get(i).equals(page.GetPT()) || checkBoxList.get(i).equals(page.GetCT()) || checkBoxList.get(i).equals(page.GetAT()))
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
			dropdown.selectByIndex(0);
			
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
			
			WebElement dropdownList = page.GetNumberTypeDropDownList();
			Select dropdown = new Select(dropdownList);
			dropdown.selectByIndex(2);
			List<WebElement> checkBoxList = BestTestJava.driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']"));
			commonMethods.Delay(30,TimeUnit.SECONDS); 
			for(int i=0;i<checkBoxList.size();i++)
			{
				if(checkBoxList.get(i).isEnabled())
				{
					if(checkBoxList.get(i).equals(page.GetICT()) || checkBoxList.get(i).equals(page.GetDT()) || checkBoxList.get(i).equals(page.GetPT()) || checkBoxList.get(i).equals(page.GetAT()))
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
       try 
       {
              String rng="RNG1",ver="1",numdraw="2",alph="0.05",test="Runs Up And Runs Down Test";
              String path= System.getProperty("user.dir")+"\\src\\resources\\out_scaled_set_2.txt";
              commonMethods.ClearAll();
            
              page.GetRNGName().sendKeys("RNG1");

              page.GetVersionName().sendKeys("1");

              page.GetAlpha().sendKeys("0.05");

              page.GetNumbersPerDraw().sendKeys("2");

              page.GetUploadFile().sendKeys(path);
              page.GetRunUpDown().click();

              commonMethods.Delay(30, TimeUnit.SECONDS);
        
              page.GetSubmitButton().click();

              String gName=page.GetResultName().getText();

              String verName=page.GetResultVersion().getText();

              String alphaValues=page.GetResultAlpha().getText();

              String numPerDraw=page.GetResultNumberPerDraw().getText();
      
              String testName=page.GetResultTestName().getText();
         
              String Result=page.GetRunUpDownResult().getText();
         
              if (gName.equals(rng) && verName.equals(ver) &&  alphaValues.equals(alph) && numPerDraw.equals(numdraw) && testName.equals(test)) {
                    System.out.println("Result for given test 'Runs Up And Runs Down Test' has been verified successfuly.");
                    if(Result.equals("Pass"))
                     {
                           System.out.println("Test is Passed.");
                    }
                    else
                    {
                           System.out.println("Test is Failed.");
                    }

              } 
              else 
              {
                    System.out.println("Failed to verify the given test 'Runs Up And Runs Down Test'.");
              }
 
              page.GetBackSubmitButton().click();

              commonMethods.ClearAll();
       }

       catch (Exception e) {
              System.out.println("Error : "+ e.getMessage() + " -- "+ e.getStackTrace());
       }
    }

}
