/**
 * Purpose : This java class is implemented to perform Poker test on scaled data
 * @author : Sheetal Sulay
 * Date : 06/06/2017
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
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.util.CombinatoricsUtils;


public class PokerTest implements Serializable
{
    Logger log = Logger.getLogger(PokerTest.class.getName());
	private static final int HANDSIZE = 5;
	private ChiSquareTest chiSquareTest;
	
	public PokerTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performPokerTest(String alpha_level, double [] scaledDataList, double min, double max)
	{
		double alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Poker test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		int d = (int) max-(int) min + 1;
		int n = scaledDataList.length / HANDSIZE;
		long[] observed = partitionCount(HANDSIZE, scaledDataList);
		
		double[] expected = expectedCount(HANDSIZE, n, d);
		
		boolean resultChi = getResultChiSquareTest(expected, observed, alpha);
		
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
	
	   
	public boolean getResultChiSquareTest(double [] theoretical, long[] actual, double alpha) 
	{
		boolean resultChi = false;
		try 
		{
			resultChi = !chiSquareTest.chiSquareTest(theoretical, actual, alpha);
			log.debug("resultChi::>"+resultChi);
			double sumChi = chiSquareTest.chiSquare(theoretical, actual);
			log.debug("Sum Chi-square for Poker Test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of poker test::>"+e.getMessage()); 
		}
		return resultChi;
	}
	
	/**
	 * divides the numbers up into hands of size=handSize, and counts the number of hands having r distinct values where
	 * r ranges from 1 to handSize  
	 * @param handSize
	 * @param numbers
	 * @return frequency of occurrence of 
	 */
	private long[] partitionCount(int handSize, double[] numbers) 
	{
		long[] observed = new long[handSize];
		int n = numbers.length / handSize;
		
		for (int i = 0; i<n; i++) {
			double[] hand = subset(numbers, handSize * i, handSize * (i+1));
			
			int x = (int)countDistinct(hand);
			observed[x-1]++;
		}
		
		return observed;	
	}
	
	/**
	 * @param source array  
	 * @param start inclusive
	 * @param end exclusive
	 * @return subset of array
	 */
	private static double[] subset(double[] source, int start, int end) 
	{
		return Arrays.copyOfRange(source, start, end);
	}
	
	private static long countDistinct(double[] hand) 
	{
		return Arrays.stream(hand).distinct().count();
	}
	
	private static long stirlingS2(final int handSize,final int numDistinct) 
	{
		return CombinatoricsUtils.stirlingS2(handSize, numDistinct);
	}
	
	private double[] expectedCount(int k, int n, int d) 
	{
		double[] expected = new double[k];
		double dpowk = Math.pow(d, k);
		for (int r=1;r<=k;r++) 
		{
			double num = d;
			for (int mult=d-1;mult>=(d-r+1);mult--) 
			{
				num*=mult;
			}
			expected[r-1]=n * num * stirlingS2(k, r) / dpowk;
		}
		return expected;
	}
}