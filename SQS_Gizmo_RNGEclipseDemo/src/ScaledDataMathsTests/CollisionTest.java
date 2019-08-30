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


public class CollisionTest implements Serializable
{
    Logger log = Logger.getLogger(CollisionTest.class.getName());
	private ChiSquareTest chiSquareTest;
	
	public CollisionTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performCollisionTest(String alpha_level, double [] scaledDataList, double min, double max, String numberType, int numbersPerDraw)
	{
		double alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Collision Test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		int d = (int) max-(int) min + 1;
		int m = d ; // value of m 
		log.debug("d::>"+d); 
		log.debug("m::>"+m); 
		String resultChiText = "";
		if(d < 10)
		{
			 resultChiText = "This test for range less than 10 would not be applicable as we will have dense collisions";
		}
		else
		{
			int tsangBalls = (int) (1.245643 * m) ; // tsanBalls means n according to Tsang n should be 1.2456ms
			log.debug("tsangBalls::>"+tsangBalls); 
			
			if((tsangBalls*2) >scaledDataList.length) // in order to apply chi-square test we should have atleast 2 categories of data i.e. 2 * tsangBalls
			{
				resultChiText = "Test data is very less it should be atleast 2 * range *1.245643";
			}
			else
			{
			
				int n = scaledDataList.length / tsangBalls;
				log.debug("n::>"+n); 
				long totalCollision[] = new long[n];
				int ctr = 0;
				
				for(int j=0; j<n; j++)
				{
					int startIndex = tsangBalls*j;
					int endIndex = tsangBalls+tsangBalls*j;
					if(endIndex > scaledDataList.length)
					{
						endIndex = scaledDataList.length;
					}
					double[] subArray = Arrays.copyOfRange(scaledDataList,startIndex,endIndex);
					//log.debug("subArray"+subArray.length); 
					int size = (int) max;
					long[] observed = new long[size+1];
					int k = 0;
					long collision = 0;
					for (double i : subArray) 
					{
						k = (int) i ;
						// if observed count is already one then we have one collision
						if(observed[k] != 0)
						{
							collision++;
						}
						observed[k]++;
					}
					
					if(collision != 0)
					{
						collision--;
					}
					
					totalCollision[ctr] = collision;
					ctr++;
				}
				
				double expected[] = new double[totalCollision.length];
				for(int o=0; o<expected.length; o++)
				{
					expected[o] = (tsangBalls * tsangBalls)/(2.0 * m);// as per knuth page number 83 the formula is n^2/2*m and here n is tsangBalls
				}
				
				/*for(int l=0; l <ctr; l++)
				{
					log.debug("totalCollision::>"+totalCollision[l]); 
					log.debug("expected::>"+expected[l]); 
				}*/
				log.debug("expected::>"+expected[0]); 
				boolean resultChi = getResultChiSquareTest(expected, totalCollision, alpha);
				
				if(resultChi == true)
				{
					resultChiText = "Pass";
				}
				else
				{
					resultChiText = "Fail";
				}
			}
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
			log.debug("Sum Chi-square for Colllision Test::>"+sumChi);
		}
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of collision test::>"+e.getMessage()); 
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
	private long[] partitionCount(int handSize, double[] numbers) {
		if (numbers.length % handSize > 0) {
			throw new IllegalArgumentException("numbers length must be divisible by numPartitions");
		}
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
	private static double[] subset(double[] source, int start, int end) {
		return Arrays.copyOfRange(source, start, end);
	}
	
	private static long countDistinct(double[] hand) {
		return Arrays.stream(hand).distinct().count();
	}
	
	private static long stirlingS2(final int handSize,final int numDistinct) {
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