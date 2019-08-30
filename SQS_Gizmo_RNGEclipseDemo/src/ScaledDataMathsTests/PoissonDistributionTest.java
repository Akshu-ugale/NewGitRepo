/**
 * Purpose : This java class is implemented to perform Runs Up and Runs Down test on scaled data
 * @author : Sheetal Sulay
 * Date : 21/02/2017
  
 * References: http://www.lboro.ac.uk/media/wwwlboroacuk/content/mlsc/downloads/1.4_gofit.pdf
 * http://stattrek.com/probability-distributions/poisson.aspx
 * http://onlinestatbook.com/2/probability/poisson.html
 * http://blog.minitab.com/blog/adventures-in-statistics-2/how-to-test-your-discrete-distribution
 * https://brilliant.org/wiki/poisson-distribution/
*/

package scaleddatamathstests;

import java.io.*;
import java.math.*;
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
import org.apache.commons.math3.stat.descriptive.moment.*;

public class PoissonDistributionTest implements Serializable
{
    Logger log = Logger.getLogger(PoissonDistributionTest.class.getName());
	double alpha, mean;
	
	private ChiSquareTest chiSquareTest;
	Mean m = new Mean();
	
	public PoissonDistributionTest() {
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performPoissonDistribution(String alpha_level, double [] scaledDataList, double min, double max)
	{
		double alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in PoissonDistribution Test"); 
		log.debug("alpha_level::>"+alpha_level); 
       
		boolean resultChi = false;
		resultChi = getResultChiSquareTest(scaledDataList, min, max, alpha);
		
		String resultChiText = "";
		String comment = "";
		
		if(resultChi == true)
		{
			resultChiText = "Pass (This test is -ve test)";
			comment = "Pass means data is not following normal/uniform distribution";
		}
		else
		{
			resultChiText = "Fail (This test is -ve test)";
			comment = "Fail means data is following normal/uniform distribution";
		}
		
		String result[] = new String[2];
		
		result[0] = resultChiText; // final result;
		result[1] = comment; // Comment
		
		return result;
	}
	
	   
	public boolean getResultChiSquareTest(double [] numbers, double min, double max, double alpha) 
	{
		boolean resultChi = false;
		
		try 
		{
			int d = (int)max-(int)min+1;
			int n = numbers.length;
			
			long[] observed = new long[d];
			
			for (double i : numbers) 
			{
				int k = (int) i - (int) min;
				observed[k]++;
				
			}
			
			mean = m.evaluate(numbers,0,numbers.length);
			log.debug("Mean::>"+mean);
			double[] expected = new double[d];
			int ctr = 0;
			for (int j=(int) min;j<=(int) max;j++) 
			{
				expected[ctr] = (((Math.pow(2.71,-mean))*(Math.pow(mean,j)))/factorial(j).doubleValue()) * n;
				ctr++;
			}
			
		/*for (int k=0; k< expected.length;k++) 
			{
				log.debug("expected::>"+expected[k]); 
			}
			for (int l=0; l< observed.length;l++) 
			{
				log.debug("observed::>"+observed[l]); 
			}*/
			
			log.debug("total numbers::>"+d); 
			log.debug("numbers length::>"+n); 
			
			double sumChi = chiSquareTest.chiSquare(expected, observed);
			log.debug("Poisson Distribution test - Sum Chi-square::>"+sumChi);
			
			double P_value = chiSquareTest.chiSquareTest(expected, observed);
			log.debug("Poisson Distribution test - p_value::>"+P_value);
			
			resultChi = !chiSquareTest.chiSquareTest(expected, observed, alpha);
			log.debug("Poisson Distribution test - result Chi-square::>"+resultChi);
			
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of  Poisson Distribution test::>"+e.getMessage()); 
		}
		return resultChi;
	}
	
	/**
	 * Purpose : This function is the customized function for getting factorial using bigDecimal
	 * data type. This data type is wider than regular data type so it overcomes mathematical overflow
	 * limitation of standard factorial function
	 * 
	 */
	public BigDecimal factorial(int number) 
	{
		BigDecimal fact = BigDecimal.valueOf(1);
		for (int i = 1; i <= number; i++)
		{
			fact = fact.multiply(BigDecimal.valueOf(i));
		}
		return fact;
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