package com.org.learningMaven;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class Pages extends BestTestJava
{
	
	public By byAlpha= By.xpath("//input[@name='alpha']");
	public By byRNGName = By.xpath("//input[@name='gName']");
	public By byVersionName = By.xpath("//input[@name='VName']");
	//private List<WebElement> checkBoxList= BestTestJava.driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']")); 
	public By byNumbersPerDraw = By.xpath("//input[@name='numbersPerDraw']");
	public By byUploadFile = By.xpath("//input[@id='filenameupload']");
	public By bySubmitButton = By.xpath("//button[@value='Submit']");
	public By byCST=By.xpath("//input[@value='cst']");
	public By byICT=By.xpath("//input[@value='ict']");
	public By byET=By.xpath("//input[@value='ft']");
	public By byKST=By.xpath("//input[@value='kst']");
	public By byDT=By.xpath("//input[@value='dt']");
	public By byPT=By.xpath("//input[@value='pert']");
	public By byCT=By.xpath("//input[@value='ct']");
	public By byAT=By.xpath("//input[@value='udt']");
	public By byNumberTypeDropDownlist = By.id("numberType");
	public By bySelectAllCheckBox=By.xpath("//input[@name='selectAl']");
	public By byChiSquareTestResult=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[3]/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td[2]/b");
	public By byRunUpDown=By.xpath("//input[@value='rurdt']");
	public By byResultName=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[2]/tbody/tr[2]/td[2]");
	public By byResultVersion=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[2]/tbody/tr[3]/td[2]");
	public By byResultAlpha=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[2]/tbody/tr[4]/td[2]");
	public By byResultNumberPerDraw=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[2]/tbody/tr[5]/td[2]");
	public By byResultTestName=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[3]/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr[1]/td[2]/b");
	public By byRunUpDownResult=By.xpath("/html/body/form/table/tbody/tr[1]/td/table[3]/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr[7]/td[2]/b");
	public By byBackSubmitButton=By.xpath("//button[@value='backSubmit']");
	//alpha
	public WebElement GetAlpha()
	{
		WebElement alpha = driver.findElement(byAlpha);
		return 	alpha;
	}
	
	//RNGName
	public WebElement GetRNGName()
	{
		WebElement RNGName= driver.findElement(byRNGName);
		return 	RNGName;
	}
	
	//versionName
	public WebElement GetVersionName()
	{
		WebElement versionName= driver.findElement(byVersionName);
		return 	versionName;
	}
	
	//numbersPerDraw
	public WebElement GetNumbersPerDraw()
	{
		WebElement numbersPerDraw= driver.findElement(byNumbersPerDraw);
		return 	numbersPerDraw;
	}
	
	//uploadFile
	public WebElement GetUploadFile()
	{
		WebElement uploadFile= driver.findElement(byUploadFile);
		return 	uploadFile;
	}
	
	//submitButton
	public WebElement GetSubmitButton()
	{
		WebElement submitButton= driver.findElement(bySubmitButton);
		return 	submitButton;
	}
	
	//CST
	public WebElement GetCST()
	{
		WebElement CST= driver.findElement(byCST);
		return 	CST;
	}
	
	//ICT
	public WebElement GetICT()
	{
		WebElement ICT= driver.findElement(byICT);
		return 	ICT;
	}
	
	//ET
	public WebElement GetET()
	{
		WebElement ET= driver.findElement(byET);
		return 	ET;
	}
	
	//KST
	public WebElement GetKST()
	{
		WebElement KST= driver.findElement(byKST);
		return 	KST;
	}
	
	//DT
	public WebElement GetDT()
	{
		WebElement DT= driver.findElement(byDT);
		return 	DT;
	}
		
	//PT
	public WebElement GetPT()
	{
		WebElement PT= driver.findElement(byPT);
		return 	PT;
	}
	
	//CT
	public WebElement GetCT()
	{
		WebElement CT= driver.findElement(byCT);
		return 	CT;
	}
	
	//AT
	public WebElement GetAT()
	{
		WebElement AT= driver.findElement(byAT);
		return 	AT;
	}
	
	//byNumberTypeDropDownlist
	public WebElement GetNumberTypeDropDownList()
	{
		WebElement numberTypeDropDownlist= driver.findElement(byNumberTypeDropDownlist);
		return 	numberTypeDropDownlist;
	}
	
	//bySelectAllCheckBox
	public WebElement GetSelectAllCheckBox()
	{
		WebElement selectAllCheckBox= driver.findElement(bySelectAllCheckBox);
		return 	selectAllCheckBox;
	}
	
	//byChiSquareTestResult
	public WebElement GetChiSquareTestResult()
	{
		WebElement chiSquareTestResult= driver.findElement(byChiSquareTestResult);
		return 	chiSquareTestResult;
	}	
	
	//byRunUpDown
	public WebElement GetRunUpDown()
	{
		WebElement runUpDown= driver.findElement(byRunUpDown);
		return 	runUpDown;
	}	
	
	//byResultName
	public WebElement GetResultName()
	{
		WebElement resultName= driver.findElement(byResultName);
		return 	resultName;
	}	
	
	//byResultVersion
	public WebElement GetResultVersion()
	{
		WebElement resultVersion= driver.findElement(byResultVersion);
		return 	resultVersion;
	}
	
	//byResultAlpha
	public WebElement GetResultAlpha()
	{
		WebElement resultAlpha= driver.findElement(byResultAlpha);
		return 	resultAlpha;
	}
	
	//byResultNumberPerDraw
	public WebElement GetResultNumberPerDraw()
	{
		WebElement resultNumberPerDraw= driver.findElement(byResultNumberPerDraw);
		return 	resultNumberPerDraw;
	}
	
	//byResultTestName
	public WebElement GetResultTestName()
	{
		WebElement resultTestName= driver.findElement(byResultTestName);
		return 	resultTestName;
	}
	
	//byRunUpDownResult
	public WebElement GetRunUpDownResult()
	{
		WebElement runUpDownResult= driver.findElement(byRunUpDownResult);
		return 	runUpDownResult;
	}
	
	//byBackSubmitButton
	public WebElement GetBackSubmitButton()
	{
		WebElement backSubmitButton= driver.findElement(byBackSubmitButton);
		return 	backSubmitButton;
	}
}