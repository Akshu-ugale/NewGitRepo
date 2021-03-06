package com.org.learningMaven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BestTestJava
{
	public static WebDriver driver;
	public String baseUrl="http://192.168.160.149:8080/SQS_Gizmo_RNG/";
	
	
	//Method Name: launchApplication
	//Method Description: launch the browser with Gizmo application 
	@BeforeSuite
	public void launchApplication()
	{		
		System.out.println("Launching Chrome browser");
		System.setProperty("webdriver.chrome.driver","C:\\SeleniumLearning\\DevopsProject\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();		
		driver.get(baseUrl);
		driver.manage().window().maximize();  
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
	}
	
	//Method Name: QuitApplication
	//Method Description: close the browser
	@AfterSuite
	public void QuitApplication()
	{
		
		driver.quit();
		
	}
	
}
