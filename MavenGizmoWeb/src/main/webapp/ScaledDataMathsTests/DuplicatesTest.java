/**
 * Purpose : This java class is implemented to duplicates test on scaled data
 * @author : Sheetal Sulay
 * Date : 16/05/2017
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
import java.security.MessageDigest;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import static org.apache.commons.math3.util.CombinatoricsUtils.*;

public class DuplicatesTest implements Serializable
{
    Logger log = Logger.getLogger(DuplicatesTest.class.getName());
	
	int scaledData[];
		
    double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public DuplicatesTest() 
	{
		chiSquareTest = new ChiSquareTest();
	}

	public String[] performDuplicatesTest(String alpha_level, double [] scaledDataList, double min, double max, String numberType,int numbersPerDrawNumType)
	{
		String result[] = new String[1];
			
		try
		{
			alpha = Double.parseDouble(alpha_level);
			
			log.debug("Here in duplicates test"); 
			log.debug("alpha_level::>"+alpha_level); 
			log.debug("numberType::>"+numberType);
			log.debug("min::>"+min); 
			log.debug("max::>"+max); 
		   
			int d = (int)max-(int)min+1;
			
			if( d <= 90)
			{
				int numbersPerDraw = 0;
				
				// if the random data is without replacement then we need to consider the numbers per draw from UI which may be less that the range.
				// for shuffled number type the number per draw would be the range for number type with rplacement we hard code it to 4
				
				if(numberType.equals("shuffled"))
				{
					numbersPerDraw = d;
				}
				else 
				{
					numbersPerDraw = 4; // for numbers we hardcoded it to 4 as per Knuth's book where he is suggesting to test quadruples 
				}
					
				log.debug("numbersPerDraw::>"+numbersPerDraw); 
				int n = scaledDataList.length/numbersPerDraw; //we are going to analyze the sequence of length range 
				
				if(n==0) // n might be zero i.e less than 1 in case of subsequence length where we may have less numbers than range
				{
					n = 1;
				}
				
				log.debug("total number of draws::>"+n); 
				
				double[] theoretical = null;
				long[] actual = null;
				long[] observed =  null;
				
				BigDecimal totalPossiblePermutations = BigDecimal.valueOf(0);
				int possiblePermutations = 0;
				DecimalFormat df2 = new DecimalFormat(".########################################");
				
				if(numberType.equals("shuffled"))
				{
					totalPossiblePermutations = factorial(numbersPerDraw); //for d/numbersPerDraw different numbers we will have d! combinations
					log.debug("totalPossiblePermutations- shuffle:>:"+totalPossiblePermutations); 
					
					BigDecimal expectedProbability = BigDecimal.valueOf(n);
					expectedProbability = expectedProbability.divide(totalPossiblePermutations,35,RoundingMode.CEILING);
					
					possiblePermutations = totalPossiblePermutations.intValue();
					log.debug("totalPossiblePermutations-With int value:>:"+possiblePermutations); 
					
					if(possiblePermutations ==0 || possiblePermutations < 0)
					{
						possiblePermutations = Integer.MAX_VALUE;
					}
					log.debug("totalPossiblePermutations-With after masking value:>:"+possiblePermutations); 
					log.debug("expectedProbability-shuffle:>:"+expectedProbability); 
					if(possiblePermutations > n)
					{
						possiblePermutations = n;
					}
					log.debug("after comparing it with n:>:"+possiblePermutations); 
					theoretical = new double[possiblePermutations];
					for (int j=0;j<possiblePermutations;j++) 
					{
						theoretical[j] = expectedProbability.doubleValue();
					}
					log.debug("theoretical::>"+df2.format(theoretical[0])); 
				}
				else if(numberType.equals("wc_replacement"))
				{
					totalPossiblePermutations = factorial(d).divide(factorial(d-numbersPerDraw),35,RoundingMode.CEILING); //to choose numbers(numberperDraw) from d without repetitions we use formula fact(d)/fact(d-4)
					log.debug("totalPossiblePermutations-Without Replacement:>:"+totalPossiblePermutations); 
					BigDecimal expectedPermutations = BigDecimal.valueOf(n).divide(totalPossiblePermutations,35,RoundingMode.CEILING);
					log.debug("expectedPermutations-With Replacement:>:"+expectedPermutations); 
					
					possiblePermutations = totalPossiblePermutations.intValue();
					log.debug("totalPossiblePermutations-With int value:>:"+possiblePermutations); 
					
					if(possiblePermutations ==0 || possiblePermutations < 0)
					{
						possiblePermutations = Integer.MAX_VALUE;
					}
					
					theoretical = new double[possiblePermutations];
					for (int j=0;j<possiblePermutations;j++) 
					{
						theoretical[j] = expectedPermutations.doubleValue();
					}
					log.debug("theoretical::>"+df2.format(theoretical[0])); 
				}
				else if(numberType.equals("replacement"))
				{
					totalPossiblePermutations = BigDecimal.valueOf(Math.pow(d,numbersPerDraw)); //to choose numbers(numberperDraw) from d with repetitions we use formula d^4 (numberperdraw =4 here)
					log.debug("totalPossiblePermutations-With Replacement:>:"+totalPossiblePermutations); 
					
					BigDecimal expectedPermutations = BigDecimal.valueOf(n).divide(totalPossiblePermutations,35,RoundingMode.CEILING);
					log.debug("expectedPermutations-With Replacement:>:"+expectedPermutations); 
					
					possiblePermutations = totalPossiblePermutations.intValue();
					log.debug("totalPossiblePermutations-With int value:>:"+possiblePermutations); 
					
					if(possiblePermutations ==0 || possiblePermutations < 0)
					{
						possiblePermutations = Integer.MAX_VALUE;
					}
					
					theoretical = new double[possiblePermutations];
					for (int j=0;j<possiblePermutations;j++) 
					{
						theoretical[j] = expectedPermutations.doubleValue();
					}
					log.debug("theoretical::>"+df2.format(theoretical[0])); 
				}
				
				// calculation of observed permutations begins here
				actual = new long[n]; // as max we can have total n permutation for n draws before calculating distinct permuatations
				
				int hash = 0;
				int observedPermutations = 0;
				
				int iterations = 0;
				
				if(scaledDataList.length-numbersPerDraw == 0)
				{
					iterations = 1;
				}
				else
				{
					iterations = scaledDataList.length-numbersPerDraw;
				}
				
				for (int i = 0; i<=iterations; i++) 
				{
					hash = getHashForEachPermutation(Arrays.copyOfRange(scaledDataList, i, i+numbersPerDraw),"SHA-1");
					i = i+numbersPerDraw-1;
					actual[observedPermutations] = hash;
					observedPermutations++;
				}
				
				log.debug("observedPermutations out of possiblePermutations::>"+observedPermutations); 
				
				observed = new long[possiblePermutations];
				
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				
				// calculate counter of each permutations
				for (int i = 0; i < actual.length; i++) 
				{
					int key = (int) actual[i];
					if (map.containsKey(key)) 
					{
						int occurrence = map.get(key);
						occurrence++;
						map.put(key, occurrence);
					} 
					else 
					{
						map.put(key, 1);
					}
				}
				
				log.debug("Hashmap ready!"); 
				
				Iterator iterator = map.keySet().iterator();
				int index = 0 ;
				while (iterator.hasNext()) 
				{
					int key = (Integer) iterator.next();
					int occurrence = map.get(key);
					observed[index] = occurrence;
					if(index != n-1)
					index++;
				}
				
				log.debug("Iterator is done i.e. all permutations are counted!");
				
				
				log.debug("observed final - length::>"+observed.length); 
				log.debug("theoretical final - length::>"+theoretical.length); 
				
				/*for(int i=0; i < observed.length; i++)
				{
					log.debug("observed::>"+observed[i]);
					if(observed[i] == 0) // break the loop once we have displayed all observed permutations here onwards we will have only zero entries
					break;	
				}*/
					
				boolean resultChi = false;
				resultChi = getResultChiSquareTest(theoretical, observed, alpha);
				
				String resultChiText = "";
				
				if(resultChi == true)
				{
					resultChiText = "Pass";
				}
				else
				{
					resultChiText = "Fail";
				}
				
				
				result[0] = resultChiText; // final result;
				
				return result;
			}
			else
			{
				result[0] = "This test would not be possible for d > 90 due to data type limitations"; // final result;
			
				return result;
			}
		}
		catch(Exception e)
		{
			log.debug("Exception in Duplicates test::>"+e.getMessage());
			result[0] = "Some error occur please check the scaled data";
			return result;
		}
	}
	
	/**
	 * Purpose : This function is used to return some unique identifier to each pair of numbers
	 * 
	 */
	 
	public boolean getResultChiSquareTest(double [] theoretical, long[] actual, double alpha) 
	{
		boolean resultChi = false;
		try 
		{
			resultChi = !chiSquareTest.chiSquareTest(theoretical, actual, alpha);
			log.debug("Duplicates test - resultChi::>"+resultChi);
			double sumChi = chiSquareTest.chiSquare(theoretical, actual);
			log.debug("Sum Chi-square for Duplicates test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of Duplicates test::>"+e.getMessage()); 
		}
		return resultChi;
	}
    
	/**
	 * Purpose : This function is used to return some unique hash to each set of numbers
	 * 
	 */
	 
	public int getHashForEachPermutation(double[] scaledDataList, String algorithm)
	{
		byte[] hashedBytes=null;
		try 
		{
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			String message="";
			for(int i=0; i<scaledDataList.length; i++)
			{
				String str = Double.toString(scaledDataList[i]);
				message = message + str;
			}
			
			 hashedBytes = digest.digest(message.getBytes("UTF-8"));
		} 
		catch (Exception e) 
		{
			log.debug("Exception in Duplicates test"+e.getMessage()); 
		}	
		return toInt(hashedBytes);		
	}
	
	/**
	 * Purpose : This function is used to convert byte array to interger
	 * 
	 */
	public int toInt( byte[] bytes ) 
	{
		int result = 0;
		for (int i=0; i<4; i++) 
		{
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
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
}