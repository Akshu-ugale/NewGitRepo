/**
 * Purpose : This java class is implemented to perform Runs Up and Runs Down test on scaled data
 * @author : Sheetal Sulay
 * Date : 12/05/2017
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

public class GapTest implements Serializable
{
    Logger log = Logger.getLogger(GapTest.class.getName());
	
	int GAP = 32; // We will determine only 32 gap lengths. This is more than sufficient as for online gambling used range is limited.
	//int scaledData[];
	
	double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public GapTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performGapTest(String alpha_level, double [] scaledDataList, double min, double max)
	{
		double d=0, gap_alpha=0, gap_beta=0;
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Gap test"); 
		log.debug("alpha_level::>"+alpha_level); 
        log.debug("min array::>"+min); 
		log.debug("max array::>"+max); 
		
		d = max-min+1;
		gap_alpha = min;
		gap_beta = (int)Math.floor(d/2);
		
		long[] actual = getActual(min, max, gap_alpha, gap_beta, scaledDataList);
	
		double[] theoretical = getTheoretical(min, max, gap_alpha, gap_beta,scaledDataList.length);
		log.debug("gap_alpha::>"+gap_alpha); 
		log.debug("gap_beta::>"+gap_beta);
		
		/*for (int i=0;i<theoretical.length;i++) 
		{
			log.debug("theoretical::>"+theoretical[i]);
		}
			for (int j=0;j<actual.length;j++) 
		{
			log.debug("actual::>"+actual[j]);
		}*/
		boolean resultChi = getResultChiSquareTest(theoretical, actual, alpha);
		
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
	
	public long[] getActual(double min, double max, double alpha, double beta, double[] numbers)
	{
		long[] actual = new long[GAP + 1];
		int gap = 0;
		for (int i=0;i<numbers.length;i++) 
		{
			if (alpha <= numbers[i] && beta >= numbers[i]) 
			{
				if (gap > GAP) 
				{
					gap = GAP;
				}
				actual[gap]++;
				gap = 0;
			}
			else 
			{
				gap=gap+1;
			}
		}
		return actual;
	}
	
	private double[] getTheoretical(double min, double max, double alpha, double beta, long n) 
	{
		log.debug("n::>"+n);
		double[] probabilities = getProbabilities(min, max, alpha, beta);
		double[] expected = new double[probabilities.length];
		for (int i=0;i<expected.length;i++) 
		{
			expected[i] = n * probabilities[i];
		}
		return expected;
	}
	
	private double[] getProbabilities(double min, double max, double alpha, double beta) 
	{
		double[] probabilities = new double[GAP + 1];
		
		for (int k=0;k<=GAP;k++) 
		{
			probabilities[k] = getProbability(min, max, alpha, beta, k);
		//	log.debug("probabilities::>"+probabilities[k]);
		}
		return probabilities;
	}
	
	private double getProbability(double min, double max, double alpha, double beta, double gapLength) 
	{ 
		double p = probability(min, max, alpha, beta);
		if (gapLength<GAP) {
			return p * Math.pow((1-p),gapLength);
		} else {
			return Math.pow((1-p),GAP);
		}
	}

	/**
	 * probability of a number landing between alpha and beta
	 */
	private double probability(double min, double max, double alpha, double beta) {
		return ((double)(beta - alpha + 1) / (double)(max - min + 1));
	}

	
	public boolean getResultChiSquareTest(double [] theoretical, long[] actual, double alpha) 
	{
		boolean resultChi = true;
		try 
		{
			resultChi = !chiSquareTest.chiSquareTest(theoretical, actual, alpha);
			log.debug("resultChi::>"+resultChi);
			double sumChi = chiSquareTest.chiSquare(theoretical, actual);
			log.debug("Sum Chi-square of Gap test::>"+sumChi);
			
			
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of gap test"+e.getMessage()); 
		}
		return resultChi;
	}
}