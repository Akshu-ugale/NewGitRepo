/**
 * Purpose : This java class is implemented to perform Chi-square test on scaled data
 * @author : Sheetal Sulay
 * Date : 21/02/2017
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

public class ChiSquareGizmoTest implements Serializable
{
    Logger log = Logger.getLogger(ChiSquareGizmoTest.class.getName());
	
	private ChiSquareTest chiSquareTest;
	
	public ChiSquareGizmoTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performChiSquareTest(String alpha_level, double [] scaledDataList, double min, double max)
	{
		double alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in chi-square test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		boolean resultChi = getResultChiSquareTest(scaledDataList, min, max, alpha);
		
		String resultChiText = "";
		
		if(resultChi == true)
		{
			resultChiText = "Pass";
		}
		else
		{
			resultChiText = "Fail";
		}
		
		String result[] = new String[1];
		
		result[0] = resultChiText; // final result;
		
		return result;
	}
	
	   
	public boolean getResultChiSquareTest(double [] numbers, double min, double max, double alpha) 
	{
		boolean resultChi = true;
		try 
		{
			int d = (int)max-(int)min+1;
			int n = numbers.length;
			
			long[] observed = new long[d];
			for (double i : numbers) {
				int k = (int) i - (int) min;
				observed[k]++;
				
			}
			
			double[] expected = new double[d];
			
			for (int j=0;j<d;j++) {
				expected[j] = (double)n / d;
			}
			
			log.debug("total numbers::>"+d); 
			log.debug("numbers length::>"+n); 
			
			double sumChi = chiSquareTest.chiSquare(expected, observed);
			log.debug("Sum Chi-square::>"+sumChi);
			
			double P_value = chiSquareTest.chiSquareTest(expected, observed);
			log.debug("p_value::>"+P_value);
			
			resultChi = !chiSquareTest.chiSquareTest(expected, observed, alpha);
			
		} catch (Exception e) {
			log.debug("Exception in getResultChiSquareTest function of chi-square test"+e.getMessage()); 
		}
		return resultChi;
	}
}