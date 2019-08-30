/**
 * Purpose : This java class is implemented to perform Maximum of T test
 * @author : Sheetal Sulay
 * Date : 22/05/2017
*/

package scaleddatamathstests;

import java.io.*;
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

import org.apache.commons.math3.distribution.*;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.apache.commons.math3.stat.descriptive.moment.*;

public class MaximumOfTTest implements Serializable
{
    Logger log = Logger.getLogger(MaximumOfTTest.class.getName());
			
	private KolmogorovSmirnovTest ksTest;
	private KolmogorovSmirnovGizmoTest ksTestGizmo;
	
	double maximumOfSublengthList[];
	
	public MaximumOfTTest() 
	{
		ksTest = new KolmogorovSmirnovTest();
		ksTestGizmo = new KolmogorovSmirnovGizmoTest();
	}
	
	public String[] performMaximumOfTTest(String alpha_level, double[] scaledData, double min, double max)
	{
		String[] result = new String[3];
		try
		{
			log.debug("Here in MaximumOfTTest"); 
			log.debug("alpha_level::>"+alpha_level); 
			
			int range = (int) max - (int) min + (int)1;
			
			
			log.debug("Range for maximum of T test::>"+range);
			
			getMaximumOfSublength(scaledData,range);
			
			log.debug("afer function::>"+maximumOfSublengthList.length);
			if (range < 6)
			{
				result[0] = "0.0";
				result[1] = "Range should be minimum 6 for the Maximum of t test";
				result[2] = "0.0";
			}
			else if(maximumOfSublengthList.length < 2)
			{
				result[0] = "0.0";
				result[1] = "Data length should be atleast 2*d(range)";
				result[2] = "0.0";
			}
			else
			{
				result = ksTestGizmo.performKolmogorovSmirnovTest(alpha_level, maximumOfSublengthList);
			}
		}
		catch(Exception e)
		{
			result[0] = "0.0"; 
			result[1] = "Something wrong with scaled data";
			result[2] = "0.0"; 
			log.debug("Exception in performMaximumOfTTest function of MaximumOfTTest"+e.getMessage()); 
		}
				
		return result;
	}
	
	/* This function is used to split the original scaled data into t sub-groups of each range size 
	 * such that range * t = total length of scaled data. In addition to this to prepare array with maximum of each sublength.
	*/
	
	public void getMaximumOfSublength(double[] scaledData, int range)
	{
		int from = 0;
		int to = 0,ctr=0;
		int length = 0;
		
		if(scaledData.length%range == 0)
		{
			maximumOfSublengthList = new double[scaledData.length/range];
		}
		else
		{
			maximumOfSublengthList = new double[scaledData.length/range+1];
		}
		
		while(length < scaledData.length)
		{
			to = to + range;
			double scaledDataPart1 [] = Arrays.copyOfRange(scaledData, from, to);
			from = to;
			length = length + range;
			Arrays.sort(scaledDataPart1);
			double maximumOfSublength = scaledDataPart1[scaledDataPart1.length-1];
		//	log.debug("maximum number is"+maximumOfSublength);
			maximumOfSublengthList[ctr] = maximumOfSublength;
			ctr++;
		}
		
		log.debug("maximumOfSublengthList::>"+maximumOfSublengthList.length);
	}
}