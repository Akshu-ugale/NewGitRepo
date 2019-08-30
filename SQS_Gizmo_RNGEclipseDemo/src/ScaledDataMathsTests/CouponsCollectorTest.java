/**
 * Purpose : This java class is implemented to perform coupons collector test scaled data
 * @author : Sheetal Sulay
 * Date : 19/05/2017
*/

package scaleddatamathstests;

import java.io.*;
import java.math.*;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; 
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import static org.apache.commons.math3.util.CombinatoricsUtils.*;

public class CouponsCollectorTest implements Serializable
{
    Logger log = Logger.getLogger(CouponsCollectorTest.class.getName());
	
	private ChiSquareTest chiSquareTest;
	
	public CouponsCollectorTest() 
	{
		chiSquareTest = new ChiSquareTest();
	}
	
	public String[] performCouponsCollectorTest(String alpha_level, double [] scaledDataList, double min, double max)
	{
		String result[] = new String[1];;
		try
		{
			double alpha = Double.parseDouble(alpha_level);
			
			log.debug("Here in coupons collector test"); 
			log.debug("alpha_level::>"+alpha_level); 
			
			double[] offsetNumbers = offsetNumbers(scaledDataList, min);
			int d = (int)max-(int)min+1;
			
			int t = d + 21; // we have to define value of t any number which is greater than d as per knuth;
			log.debug("t::>"+t); 
			log.debug("d::>"+d);
			String resultChiText = "";
			
			if(d <= 90)
			{
				long[] observed = getObserved(offsetNumbers, d, t);
			
				long n = getN(observed);
				log.debug("n::>"+n);
				double[] expected = getExpected(t,d,n);
				/*for(int j=d; j < expected.length; j++)
				{
					log.debug("expected::>"+j+"::"+expected[j]); 
				}
				for(int k=d; k < expected.length; k++)
				{
					log.debug("observed::>"+k+"::"+observed[k]); 
				}*/
				// Chi-square test throws exception for "0" values so trim the arrays for expected and observed from index "d"
				expected = Arrays.copyOfRange(expected, d, expected.length); 
				observed = Arrays.copyOfRange(observed, d, observed.length);
				boolean resultChi = getResultChiSquareTest(expected, observed, alpha);
				
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
				resultChiText = "The range "+d+" would be very high to compute this test";
			}
			
			
			result[0] = resultChiText; // final result;
		}
		catch(Exception e)
		{
			log.debug("Exception in performCouponsCollectorTest function of coupons collector test::>"+e.getMessage()); 
		}
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
			log.debug("Sum Chi-square coupons collector test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of coupons collector test::>"+e.getMessage()); 
		}
		return resultChi;
	}
	
	private long[] getObserved(double[] numbers, int d, int t) 
	{
		long[] observed = new long[t+1];
		boolean[] occurs = new boolean[d];
		int c=0,c1 = 0;
		int index = 0;
		for (double num : numbers)
		{
			c++;
			c1++;
			if (num>d-1) 
			{
				throw new IllegalArgumentException("wrong");
			}
			int index_temp = (int) num;
			occurs[index_temp] = true;
			if (completeSet(occurs)) 
			{
				if (c >= t) {
					c = t;
				}
				index = c;
				observed[index]++;  //the quickest possible sequence to complete coupon is in d numbers
				c=0;
				occurs = new boolean[d];  //reset occurs
			}
			else if(c1 >=numbers.length)
			{
				observed[observed.length-1]++;
			}
		}
		return observed;
	}
	
	private double[] getExpected(int t, int d, long n) 
	{
		double[] expected = new double[t+1];
		for (int i=d;i<expected.length;i++) 
		{
			if(!Double.isNaN(getProbability(i,t,d)))
			{
				expected[i] = n * getProbability(i,t,d);
			}
			else
			{
				expected[i] = 0.0;
			}
		}
		return expected;
	}
	
	private double getProbability(int r, int t, int d) 
	{
		if (r<t) 
		{
			return (factorialDouble(d) / Math.pow(d, r)) * StirlingNumbers(r-1, d-1);
		} 
		else 
		{
			return (1-( factorialDouble(d) / Math.pow(d, t-1)) * StirlingNumbers(t-1, d));
		}
	}
	
	private boolean completeSet(boolean [] occurs) 
	{
		for (boolean occur : occurs) 
		{
			if (!occur) 
			{
				return false;
			}
		}
		return true;
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
	
	/**
	 *  @return total number of observations
	 */
	protected long getN(long[] observed) 
	{
		long n = 0;
		for (long o : observed) 
		{
			n += o;
		}
		return n;
	}
	
	/**
	 * Function to return the Stirling numbers 
	 */
	protected double StirlingNumbers(int n, int k)
	{
  		BigInteger[] row;        // a row of Stirling numbers
		int numRows = 20;  // number of rows to generate
		double stirlingNumber = 0;
		BigInteger stirlingNumber_big = BigInteger.valueOf(0);
		
		// First row of "Stirling triangle"
		row = new BigInteger[ 2 ];
		row[ 0 ] = BigInteger.ZERO; // zeros are stored to prevent NullPointerException
		row[ 1 ] = BigInteger.ONE;
   		
		for ( int count = 1; count <= n; count++ )
		{
			BigInteger[] newRow = new BigInteger[ row.length + 1 ];
			newRow[ 0 ] = BigInteger.ZERO;
			for ( int i = 1; i < newRow.length - 1; i++ )
			{
				BigInteger iVal = new BigInteger( i + "" ); 
				newRow[ i ] = row[ i - 1 ].add( iVal.multiply( row[ i ] ) );  // S(n, k) = S(n-1, k-1) + k*S(n-1, k)
			}
			newRow[ newRow.length - 1 ] = BigInteger.ONE;  // last entry is always 1
		  
		   for ( int i = 1; i < newRow.length; i++ )
		   {	
				//log.debug("stirling numbers::>"+newRow[ i ]+"::>"+i+"count::<"+count);
				if(n == (count+1) && k == i)
				{
					stirlingNumber_big = newRow[i];
					break;
				}
		   }
		    row = newRow;
			//log.debug("stirlingNumber::>"+stirlingNumber_big);
		}
		stirlingNumber = stirlingNumber_big.doubleValue();
		//log.debug("StirlingNumber::>("+n+","+k+")"+stirlingNumber);
		return stirlingNumber;
  } 

}