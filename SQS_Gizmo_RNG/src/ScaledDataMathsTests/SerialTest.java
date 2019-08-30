/**
 * Purpose : This java class is implemented to perform Serial test on scaled data
 * @author : Sheetal Sulay
 * Date : 16/05/2017
*/

package scaleddatamathstests;

import java.io.*;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner; 
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class SerialTest implements Serializable
{
    Logger log = Logger.getLogger(SerialTest.class.getName());
	double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public SerialTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performSerialTest(String alpha_level, double [] scaledData, double min, double max)
	{
		String resultChiText = "";
		
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in serial test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		log.debug("min array::>"+min); 
		log.debug("max array::>"+max); 
		double[] offsetNumbers = offsetNumbers(scaledData, min);
		
		int d = (int)max-(int)min + 1; 
		
		if (offsetNumbers.length >= (5 * d * d)) 
		{
			int n = offsetNumbers.length / 2; //as we are going to analyze the pairs
			log.debug("Serial test:"+n); 
			
			int sampleSize = d*d; //for d different numbers we will have d^2 pairs
			log.debug("sampleSize::>"+sampleSize); 
			
			long[] actual = new long[sampleSize];
			for (int i = 0; i<n; i++) 
			{
				double first = offsetNumbers[2*i];
				double second = offsetNumbers[2*i+1];
				double x = getUniqueIdentyToPair(first, second, (max-min+1));
				int index = (int) x;
				actual[index]++;
			}
			
			double[] theoretical = new double[sampleSize];
			
			for (int j=0;j<sampleSize;j++) 
			{
				theoretical[j] = (double)n / (sampleSize);
			}
			
			log.debug("theoretical::>"+theoretical[0]); 
			
			/*for (int k=0;k<actual.length;k++) 
			{
				log.debug("actual:("+k+")::>"+actual[k]); 
			}*/
			
			boolean resultChi = getResultChiSquareTest(theoretical, actual, alpha);
			
			if(resultChi == true)
			{
				resultChiText = "Pass";
			}
			else
			{
				resultChiText = "Fail";
			}
		}
		else
		{
			resultChiText = "Scaled data length must be at least 5 d*d";
		}
		String result[] = new String[1];
		
		result[0] = resultChiText; // final result;
		
		return result;
	}
	
	   
	public boolean getResultChiSquareTest(double [] theoretical, long[] actual, double alpha) 
	{
		boolean resultChi = false;
		try 
		{
			resultChi = !chiSquareTest.chiSquareTest(theoretical, actual, alpha);
			log.debug("resultChi::>"+resultChi);
			double sumChi = chiSquareTest.chiSquare(theoretical, actual);
			log.debug("Sum Chi-square of serial test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of serial test"+e.getMessage()); 
		}
		return resultChi;
	}
    
	/**
	 * Purpose : This function is used to return some unique identifier to each pair of numbers
	 * 
	 */
	 
	private double getUniqueIdentyToPair(double first, double second, double base)
	{
		return first + second * base;
	}	
	
	/**
	 * offset numbers by - min so that they range from 0 to (max-min) rather than from min to max 
	 */
	protected double[] offsetNumbers(double[] numbers, double min)
	{
		double[] offsetNumbers = new double[numbers.length];
		for (int i=0;i<numbers.length;i++) 
		{
			offsetNumbers[i] = numbers[i]-min;
		}
		return offsetNumbers;
	}
}