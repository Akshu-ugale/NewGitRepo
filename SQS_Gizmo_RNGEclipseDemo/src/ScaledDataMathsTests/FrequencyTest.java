/**
 * Purpose : This java class is implemented to perform Frequency (Equi-distribution test) test on scaled data
 * @author : Sheetal Sulay
 * Date : 04/05/2017
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

public class FrequencyTest implements Serializable
{
    Logger log = Logger.getLogger(FrequencyTest.class.getName());
	int scaledData[];
	
    String resulttext = "result";
	double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public FrequencyTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performFrequencyTest(String alpha_level, ArrayList<Double> scaledDataList)
	{
		int min=0,max=0;
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in frequency test"); 
		log.debug("alpha_level"+alpha_level); 
    
		scaledData = new int[scaledDataList.size()];
		if(scaledDataList.size() > 0)
		{
			min = scaledDataList.get(0).intValue();
			max = scaledDataList.get(0).intValue();
		}
		
		for(int i = 0; i < scaledDataList.size(); i++ )
		{
			scaledData[i] = scaledDataList.get(i).intValue();
			if(scaledData[i] > max)
				max = scaledData[i];
			else if (scaledData[i] < min)
				min = scaledData[i];
		}
		
		log.debug("min array:"+min); 
		log.debug("max array"+max); 
		
		boolean resultChi = getResultChiSquareTest(scaledData, min, max, alpha);
		
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
	
	   
	public boolean getResultChiSquareTest(int [] numbers, int min, int max, double alpha) 
	{
		boolean resultChi = true;
		try 
		{
			int d = max-min+1;
			int n = numbers.length;
			
			long[] observed = new long[d];
			
			for (int i : numbers) {
				observed[i-min]++;
				
			}
			
			double[] expected = new double[d];
			
			for (int j=0;j<d;j++) {
				expected[j] = (double)n / d;
			}
			
			log.debug("total numbers"+d); 
			log.debug("numbers length"+n); 
			
			double sumChi = chiSquareTest.chiSquare(expected, observed);
			log.debug("Sum Chi-square"+sumChi);
			
			double P_value = chiSquareTest.chiSquareTest(expected, observed);
			log.debug("p_value"+P_value);
			
			resultChi = !chiSquareTest.chiSquareTest(expected, observed, alpha);
			
		} catch (Exception e) {
			log.debug("Exception in getResultChiSquareTest function of frequency test"+e.getMessage()); 
		}
		return resultChi;
	}
}