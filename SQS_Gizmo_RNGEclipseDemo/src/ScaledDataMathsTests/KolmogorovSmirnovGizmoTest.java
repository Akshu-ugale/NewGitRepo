/**
 * Purpose : This java class is implemented to perform Runs Up and Runs Down test on scaled data
 * @author : Sheetal Sulay
 * Date : 13/03/2017
 
 Refer : https://onlinecourses.science.psu.edu/stat414/book/export/html/234
 https://onlinecourses.science.psu.edu/stat414/node/323
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

public class KolmogorovSmirnovGizmoTest implements Serializable
{
    Logger log = Logger.getLogger(KolmogorovSmirnovGizmoTest.class.getName());
	
	double standardDeviation = 0.0;
	double alpha, mean;
	double dMax[];
	double dMaxFinal[];
	double p_value = 0.0;
	double d_max = 0.0;
			
	private KolmogorovSmirnovTest ksTest;
	
	String[] result = new String[3];
	String resulttext = "result";
	
	StandardDeviation sd = new StandardDeviation(true);
	Mean m = new Mean();
		
	public KolmogorovSmirnovGizmoTest() 
	{
		ksTest = new KolmogorovSmirnovTest();
	}
	
	public String[] performKolmogorovSmirnovTest(String alpha_level, double [] scaledData)
	{
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Kolmogorov-Smirnov test"); 
		log.debug("alpha_level::>"+alpha_level); 
     	
		try
		{
			if(scaledData.length <= 100)
			{
				log.debug("here size less than or equal to 100");
				result = getKSSampleSize100(scaledData);
			}
			else if(scaledData.length > 100 && scaledData.length <= 10000) 
			{
				getKSSampleSize1000(scaledData);
				log.debug("here size greater than 100 and less than 10000");
			}
			else 
			{
				log.debug("here size greater than 10000");
				int from = 0;
				int to = 0;
				int length = 0,ctr=0;
				
				if(scaledData.length%1000 == 0)
				{
					dMax = new double[scaledData.length/1000];
				}
				else
				{
					dMax = new double[scaledData.length/1000+1];
				}
				
				log.debug("dMax::>"+dMax.length);
				
				while(length < scaledData.length)
				{
					to = to + 1000;
					double scaledDataPart1 [] = Arrays.copyOfRange(scaledData, from, to);
					from = to;
					length = length + 1000;
					standardDeviation = sd.evaluate(scaledDataPart1);
					mean = m.evaluate(scaledDataPart1,0,scaledDataPart1.length);
					NormalDistribution unitNormal = new NormalDistribution(mean, standardDeviation);
					d_max =  getResultKolmogorovSmirnovTestDmax(unitNormal, scaledDataPart1, alpha);
					dMax[ctr] = d_max;
					ctr++;
				}
				if(dMax.length <= 100)
				{
					result = getKSSampleSize100(dMax);
				}
				else
				{
					getKSSampleSize1000(dMax);
				}
			}
		}
		catch(Exception e)
		{
			result[0] = "0.0"; 
			result[1] = "Something wrong with scaled data";
			result[2] = "0.0"; 
			log.debug("Exception in performKolmogorovSmirnovTest function of KS test"+e.getMessage()); 
		}
				
		return result;
	}
	
	public String[] getKSSampleSize100(double[] subArray)
	{
		standardDeviation = sd.evaluate(subArray);
		mean = m.evaluate(subArray,0,subArray.length);
		NormalDistribution unitNormal = new NormalDistribution(mean, standardDeviation);
	
		boolean resultkS_b = getResultKolmogorovSmirnovTest(unitNormal, subArray, alpha);
		d_max =  getResultKolmogorovSmirnovTestDmax(unitNormal, subArray, alpha);
		p_value =  getResultKolmogorovSmirnovTestPvalue(unitNormal, subArray, alpha);
		
		log.debug("SubArrayLength::>"+subArray.length); 
		log.debug("StandardDeviation::>"+standardDeviation); 
		log.debug("Mean::>"+mean); 
		log.debug("dMax::>"+d_max); 
		log.debug("p_value::>"+p_value); 
		log.debug("resultkS_b::>"+resultkS_b); 
		String resultKSLocal = "";
		if(resultkS_b == true)
		{
			resultKSLocal = "Pass";
		}
		else
		{
			resultKSLocal = "Fail";
		}
		//String result[] = new String[3];
		result[0] = Double.toString(d_max); 
		result[1] = resultKSLocal;
		result[2] = Double.toString(p_value); 
		
		return result;
	}
	
	public void getKSSampleSize1000(double[] subArray)
	{
		int from = 0;
		int to = 0;
		int length = 0,ctr=0;
		
		if(subArray.length%100 == 0)
		{
			dMax = new double[subArray.length/100];
		}
		else
		{
			dMax = new double[subArray.length/100+1];
		}
		
		log.debug("dMax::>"+dMax.length);
		
		while(length < subArray.length)
		{
			to = to + 100;
			double scaledDataPart1 [] = Arrays.copyOfRange(subArray, from, to);
			from = to;
			
			length = length + 100;
			standardDeviation = sd.evaluate(scaledDataPart1);
			mean = m.evaluate(scaledDataPart1,0,scaledDataPart1.length);
			NormalDistribution unitNormal = new NormalDistribution(mean, standardDeviation);
			d_max =  getResultKolmogorovSmirnovTestDmax(unitNormal, scaledDataPart1, alpha);
			dMax[ctr] = d_max;
			ctr++;
		}
		result = getKSSampleSize100(dMax);
	}
	
	public double getResultKolmogorovSmirnovTestDmax(RealDistribution distribution, double [] numbers, double alpha) 
	{
		double d = 0.0;
		
		try
		{
			d = ksTest.kolmogorovSmirnovStatistic(distribution,numbers);
		} 
		catch (Exception e) 
		{
			result[0] = "0.0"; 
			result[1] = "Something wrong with scaled data";
			result[2] = "0.0"; 
			log.debug("Exception in getResultChiSquareTest function of KS test"+e.getMessage()); 
		}
		return d;
	}
	
	public double getResultKolmogorovSmirnovTestPvalue(RealDistribution distribution, double [] numbers, double alpha) 
	{
		double sign = 0.0;
		
		try
		{
			sign = ksTest.kolmogorovSmirnovTest(distribution,numbers,false);
		}
		catch (Exception e) 
		{
			log.debug("Exception in getResultChiSquareTest function of KS test"+e.getMessage()); 
		}
		return sign;
	}
	   
	public boolean getResultKolmogorovSmirnovTest(RealDistribution distribution, double [] numbers, double alpha) 
	{
		double d = 0.0;
		boolean test = false;
		try
		{
			d = ksTest.kolmogorovSmirnovStatistic(distribution,numbers);
			d_max = d_max + d;
			log.debug("d_max::>"+d_max); 
			log.debug("d::>"+d); 
			test = !ksTest.kolmogorovSmirnovTest(distribution,numbers,alpha);
			double sign = ksTest.kolmogorovSmirnovTest(distribution,numbers,false);
			p_value = p_value + sign;
			log.debug("sign::>"+sign);
			log.debug("p_value::>"+p_value);
		}
		catch (Exception e) 
		{
			result[0] = "0.0"; 
			result[1] = "Something wrong with scaled data";
			result[2] = "0.0"; 
			log.debug("Exception in getResultKolmogorovSmirnovTest function of KS test::>"+e.getMessage()); 
		}
		return test;
	}
 }