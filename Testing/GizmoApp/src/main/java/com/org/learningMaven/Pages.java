package com.org.learningMaven;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Pages extends BestTestJava
{
	protected WebElement alpha = BestTestJava.driver.findElement(By.xpath("//input[@name='alpha']"));
	protected  WebElement RNGName = BestTestJava.driver.findElement(By.xpath("//input[@name='gName']"));
	protected WebElement versionName = BestTestJava.driver.findElement(By.xpath("//input[@name='VName']"));
	//private List<WebElement> checkBoxList= BestTestJava.driver.findElements(By.xpath("//input[@type='checkbox' and @name='testName']")); 
	protected WebElement numbersPerDraw = BestTestJava.driver.findElement(By.xpath("//input[@name='numbersPerDraw']"));
	protected WebElement uploadFile = BestTestJava.driver.findElement(By.xpath("//input[@id='filenameupload']"));
	protected WebElement submitButton = BestTestJava.driver.findElement(By.xpath("//button[@value='Submit']"));
	protected WebElement CST=BestTestJava.driver.findElement(By.xpath("//input[@value='cst']"));
	protected WebElement ICT=BestTestJava.driver.findElement(By.xpath("//input[@value='ict']"));
	protected WebElement ET=BestTestJava.driver.findElement(By.xpath("//input[@value='ft']"));
	protected WebElement KST=BestTestJava.driver.findElement(By.xpath("//input[@value='kst']"));
	protected WebElement DT=BestTestJava.driver.findElement(By.xpath("//input[@value='dt']"));
	protected WebElement PT=BestTestJava.driver.findElement(By.xpath("//input[@value='pert']"));
	protected WebElement CT=BestTestJava.driver.findElement(By.xpath("//input[@value='ct']"));
	protected WebElement AT=BestTestJava.driver.findElement(By.xpath("//input[@value='udt']"));
	protected WebElement numberTypeDropDownlist = BestTestJava.driver.findElement(By.id("numberType"));
	protected WebElement selectAllCheckBox=driver.findElement(By.xpath("//input[@name='selectAl']"));
	protected WebElement chiSquareTestResult=driver.findElement(By.xpath("//input[@name='isChi']/td//td[@width='75']/b"));

	//alpha
	public WebElement GetAlpha()
	{
		return   this.alpha;
	}

	//RNGName
	public WebElement GetRNGName()
	{
		return   this.RNGName;
	}

	//versionName
	public WebElement GetVersionName()
	{
		return   this.versionName;
	}

	//numbersPerDraw
	public WebElement GetNumbersPerDraw()
	{
		return   this.numbersPerDraw;
	}

	//uploadFile
	public WebElement GetUploadFile()
	{
		return   this.uploadFile;
	}

	//submitButton
	public WebElement GetSubmitButton()
	{
		return   this.submitButton;
	}

	//CST
	public WebElement GetCST()
	{
		return   this.CST;
	}

	//ICT
	public WebElement GetICT()
	{
		return   this.ICT;
	}

	//ET
	public WebElement GetET()
	{
		return   this.ET;
	}

	//KST
	public WebElement GetKST()
	{
		return   this.KST;
	}

	//DT
	public WebElement GetDT()
	{
		return   this.DT;
	}

	//PT
	public WebElement GetPT()
	{
		return   this.PT;
	}

	//CT
	public WebElement GetCT()
	{
		return   this.CT;
	}

	//AT
	public WebElement GetAT()
	{
		return   this.AT;
	}

	//dropDownList
	public WebElement GetNumberTypeDropDownList()
	{
		return   this.numberTypeDropDownlist;
	}

	//selectAllCheckbox
	public WebElement GetSelectAllCheckBox()
	{
		return   this.selectAllCheckBox;
	}

	//chiSquareTestResult
	public WebElement GetChiSquareTestResult()
	{
		return   this.chiSquareTestResult;
	}
}
