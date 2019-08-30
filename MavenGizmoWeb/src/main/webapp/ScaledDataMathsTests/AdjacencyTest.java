/**
 * Purpose : This java class is implemented to perform Serial test on scaled data
 * @author : Sheetal Sulay
 * Date : 14/07/2017
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

public class AdjacencyTest implements Serializable
{
    Logger log = Logger.getLogger(AdjacencyTest.class.getName());
	double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public AdjacencyTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performAdjacencyTest(String alpha_level, double [] scaledData, String numberType, double min, double max)
	{				
		String resultText = "";
		
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Adjacency Test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		log.debug("min array::>"+min); 
		log.debug("max array::>"+max); 
		double[] offsetNumbers = offsetNumbers(scaledData, min);
		
		int d = (int)max-(int)min + 1; 
		
		
		int n = offsetNumbers.length / 2; //as we are going to analyze the pairs
		log.debug("n::>"+n); 
		int totalPossiblePairs = d * d; //for d different numbers we will have d^2 pairs
		
		
		int possibleAdjacentPairs = (d - 1) * 2; // for adjacency test we will consider only adjacent pairs
		log.debug("possibleAdjacentPairs::>"+possibleAdjacentPairs); 
		
		
		long[] actual = new long[totalPossiblePairs];
		for (int i = 0; i<n; i++) 
		{
			double first = offsetNumbers[2*i];
			double second = offsetNumbers[2*i+1];
			if(first == second + 1.0 || first == second - 1.0 ) // for adjacency test we are interested only in adjacent numbers like 1,2 or 3,2 etc.
			{
				double x = getUniqueIdentyToPair(first, second, (max-min+1));
				int index = (int) x;
				actual[index]++;
			}
		}
		
		double[] theoretical = new double[possibleAdjacentPairs];
		
		int eachPairProbability = 0;
		if(numberType.equals("wc_replacement"))
		{
			eachPairProbability = totalPossiblePairs - d; //repetitions are not possible for without replacement.
		}
		else
		{
			eachPairProbability = totalPossiblePairs;
		}
		log.debug("totalPossiblePairs::>"+totalPossiblePairs);
		for (int j=0;j<possibleAdjacentPairs;j++) 
		{
			theoretical[j] = (double)n / (eachPairProbability);
		}
		
		int ctr = 0;
		long observed[] = new long[possibleAdjacentPairs];
		for (int k=0;k<totalPossiblePairs;k++) 
		{
			if(actual[k] == 0)
				continue;
			observed[ctr] = actual[k];
			ctr++;
			//log.debug("actual::>"+actual[k]); 
		}
		
		/*for (int l=0;l<possibleAdjacentPairs;l++) 
		{
			if(observed[l]==0)
				break;
			//log.debug("observed::>"+observed[l]); 
		}*/
		
		log.debug("theoretical::>"+theoretical[0]); 
		
		//theoretical = Arrays.copyOfRange(theoretical, 0 , ctr);
		log.debug("theoretical length ::>"+theoretical.length); 
		boolean resultChi = getResultChiSquareTest(theoretical, observed, alpha);
		
		if(resultChi == true)
		{
			resultText = "Pass";
		}
		else
		{
			resultText = "Fail";
		}
		
		String result[] = new String[1];
		
		result[0] = resultText; // final result;
		
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
			log.debug("Sum Chi-square of adjacency test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of adjacency test"+e.getMessage()); 
		}
		return resultChi;
	}
    
	/**
	 * Purpose : This function is used to return some unique identifier to each pair of numbers
	 * 
	 */
	 
	private double getUniqueIdentyToPair(double first, double second, double base) {
		return first + second * base;
	}	
	
	/**
	 * offset numbers by - min so that they range from 0 to (max-min) rather than from min to max 
	 */
	protected double[] offsetNumbers(double[] numbers, double min) {
		double[] offsetNumbers = new double[numbers.length];
		for (int i=0;i<numbers.length;i++) {
			offsetNumbers[i] = numbers[i]-min;
		}
		return offsetNumbers;
	}	
}