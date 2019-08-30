/**
 * Purpose : This java class is implemented to perform permutations test on scaled data
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

public class PermutationTest implements Serializable
{
    Logger log = Logger.getLogger(PermutationTest.class.getName());
	
	int scaledData[];
		
    double alpha;
	
	private ChiSquareTest chiSquareTest;
	
	public PermutationTest() {
		chiSquareTest = new ChiSquareTest();
	}

	public String[] performPermutationTest(String alpha_level, double [] scaledDataList, double min, double max, String numberType,int numbersPerDrawNumType)
	{
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Permutation test"); 
		log.debug("alpha_level::>"+alpha_level); 
		log.debug("numberType::>"+numberType);
		log.debug("min::>"+min); 
		log.debug("max::>"+max); 
       
		int d = (int)max-(int)min+1;
		int numbersPerDraw = 0;
		
		numbersPerDraw = 3; // for numbers we hardcoded it to 4 as per Knuth's book where he is suggesting to test quadruples 
		
		log.debug("numbersPerDraw::>"+numbersPerDraw); 
		int n = scaledDataList.length/numbersPerDraw; //we are going to analyze the sequence of length range 
		
		if(n==0) // n might be zero i.e less than 1 in case of subsequence length where we may have less numbers than range
		{
			n = 1;
		}
		
		log.debug("total number of draws::>"+n); 
		
		double[] theoretical = null;
		int possiblePermutations = 0;
		
		if(numberType.equals("shuffled") || numberType.equals("wc_replacement"))
		{
			possiblePermutations = 6; //for 1,2,3 we have 3! i.e 6 possible permutations
		}
		else
		{
			//for 1,2,3 we have 3! i.e 27 possible permutations including duplicates like 111,222,333 etc. even if we are not considering 
			//these combinations but as they will be discarded later  
			possiblePermutations = 27; 
		}
		
		log.debug("totalPossiblePermutations::>"+possiblePermutations); 
	
		double expectedProbability = (double) n/possiblePermutations;
		log.debug("expectedProbability::>"+expectedProbability); 
		
		theoretical = new double[possiblePermutations];
		
		for (int j=0;j<possiblePermutations;j++) 
		{
			theoretical[j] = expectedProbability;
		}

		// calculation of observed permutations begins here
		long[] actual = new long[n]; // as max we can have total n permutation for n draws before calculating distinct permutations
		
		 // there is possiblity to get duplicates as we are dividing the sequence into 3 for example for range 12345 we may have 1234554321 here
		 // we have 455 in second block so even if we discard these repitations for safer side
		long[] observed = new long[27];
		
		int hash = 0;
		int observedPermutations = 0;
		
		for (int i = 0; i<n; i++) 
		{
			double[] offsetArray = null;
		
			offsetArray = getOffsetArrayWithReplacement(Arrays.copyOfRange(scaledDataList, i*numbersPerDraw, ((i*numbersPerDraw)+numbersPerDraw)));
			if(offsetArray.length == 3)
			{
				hash = getHashForEachPermutation(offsetArray,"SHA-1");
				actual[observedPermutations] = hash;
				observedPermutations++;
			}
		}
		actual = Arrays.copyOfRange(actual,  0, observedPermutations);
		
		log.debug("observedPermutations out of possiblePermutations::>"+observedPermutations); 
		
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

        Iterator iterator = map.keySet().iterator();
		int index = 0 ;
        while (iterator.hasNext()) 
		{
            int key = (Integer) iterator.next();
            int occurrence = map.get(key);
			observed[index] = occurrence;
			index++;
		}
		
		DecimalFormat df2 = new DecimalFormat(".########################################");
		
		log.debug("theoretical::>"+df2.format(theoretical[0])); 
		
		observed = Arrays.copyOfRange(observed, 0, 6);
		theoretical = Arrays.copyOfRange(theoretical, 0, 6);
		
		log.debug("observed final - length::>"+observed.length); 
		log.debug("theoretical final - length::>"+theoretical.length); 
		
		/*for(int i=0; i < observed.length; i++)
		{
			log.debug("observed::>"+observed[i]);
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
		
		String result[] = new String[1];
		
		result[0] = resultChiText; // final result;
		
		return result;
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
			log.debug("Permutation test - resultChi::>"+resultChi);
			double sumChi = chiSquareTest.chiSquare(theoretical, actual);
			log.debug("Sum Chi-square for Permutation Test::>"+sumChi);
		} 
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of Permutation test::>"+e.getMessage()); 
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
			log.debug("Exception in permutation test"+e.getMessage()); 
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
	 * Purpose : This function is used to convert the numbers to 1,2,3 as per kunth's book
	 * 
	 */
	 
	public double[] getOffsetArray(double[] scaledDataList)
	{
		double [] offsetArray = new double[3];
		
		int minIndex = 0;
		int maxIndex = 0;
		
		offsetArray[0] = 2;
		offsetArray[1] = 2;
		offsetArray[2] = 2;
		
		try 
		{
			double min = scaledDataList[0];
			double max = scaledDataList[0];
		
			
			for(int i=0 ; i<scaledDataList.length; i++)
			{
				if(scaledDataList[i] > max)
				{
					max = scaledDataList[i];
					maxIndex = i;
				}
				else if (scaledDataList[i] < min)
				{
					min = scaledDataList[i];
					minIndex = i;
				}
			}
			
			
			offsetArray[minIndex] = 1;
			offsetArray[maxIndex] = 3;
			
		} 
		catch (Exception e) 
		{
			log.debug("Exception in permutation test"+e.getMessage()); 
		}	
		return offsetArray;		
	}
	
	/**
	 * Purpose : This function is used to convert the numbers to 1,2,3 as per kunth's book
	 * 
	 */
	 
	public double[] getOffsetArrayWithReplacement(double[] scaledDataList)
	{
		double [] offsetArray = null;
		double [] blockUnique = Arrays.stream(scaledDataList).distinct().toArray();
		
		if(blockUnique.length == 1)
		{
			offsetArray = new double[1];
			offsetArray[0] = 1;
		}
		else if(blockUnique.length == 2)
		{
			offsetArray = new double[2];
			offsetArray[0] = 1;
			offsetArray[1] = 2;
		}
		else if(blockUnique.length == 3)
		{
			offsetArray = new double[3];
			offsetArray = getOffsetArray(scaledDataList);
		}
		
		return offsetArray;		
	}
}