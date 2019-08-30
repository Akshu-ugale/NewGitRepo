/**
 * Purpose : This java class is implemented to perform Runs Up and Runs Down test on scaled data
 * @author : Sheetal Sulay
 * Date : 30/08/2016
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
import java.util.Collections; 

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MedianRunsTest implements Serializable{
 
	int totalNumberOfRuns = 0;
	int totalNumberOfRunsAboveMedian = 0;
	int totalNumberOfRunsBelowMedian = 0;
	
	ArrayList<Double> standardZTable;
	ArrayList<Double> scaledData_Original;
	
    Boolean operator = null, rurdtest = null;
	String resulttext = "result";
	
	double number_of_samples, alpha, mean, sigma_square, Z_score;
	
	Logger log = Logger.getLogger(MedianRunsTest.class.getName());

	public MedianRunsTest() {
	}
	
	public String[] performMedianRunsTest(String alpha_level, ArrayList<Double> scaledDataList)
	{
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Median Runs test"); 
		log.debug("alpha_level::>"+alpha_level); 
     	
		standardZTable = new ArrayList<Double>();
		prepareStdCriticalValuesTable();
        calculationsMedianRuns(scaledDataList);
		interpretResult();
		
		if(rurdtest == true)
		{
			resulttext = "Pass";
		}
		else
		{
			resulttext = "Fail";	
		}
		
		String result[] = new String[6];
		result[0] = Double.toString(mean); //"mean";
		result[1] = Double.toString(sigma_square); //"sigma_square";
		result[2] = Double.toString(Z_score); //"4828";
		result[3] = resulttext; //"result";
		result[4] = Integer.toString(totalNumberOfRuns); //"result";
		result[5] = Double.toString(scaledDataList.size()); //"result";
		return result;
	}
	
	/**
	 * Purpose : This function is used to prepare standard table with critical values corresponding to alpha level
	 * 
	 */
	private void prepareStdCriticalValuesTable()
	{
		standardZTable.add(1.645); // alpha level 0.1
		standardZTable.add(1.960); // alpha level 0.05
		standardZTable.add(2.575); // alpha level 0.01
		
	}
    
	/**
	 * Purpose : This function is used to perform basic calculations for median runs test
	 * 
	 */
	 
    private void calculationsMedianRuns(ArrayList<Double> scaledDataList)
    {
        calculateNumberOfRuns(scaledDataList);
        
        double a = totalNumberOfRuns;
        double N = scaledDataList.size();
        double N1 = totalNumberOfRunsAboveMedian;
		double N2 = totalNumberOfRunsBelowMedian;
		
		number_of_samples = N;
        
		mean = ((2.0*N1*N2)/(N1+N2))+1.0;
	    sigma_square = (2.0*N1*N2)*((2.0*N1*N2)-N1-N2)/((N1+N2)*(N1+N2)*(N1+N2-1.0));
	    Z_score = (a-mean)/Math.sqrt(sigma_square);
    }
	
	/**
	 * Purpose : This function is used to calculate total number of runs 
	 * 
	 */
    
    private void calculateNumberOfRuns(ArrayList<Double> scaledData)
    {
		totalNumberOfRuns = 0;
		totalNumberOfRunsAboveMedian = 0;
		totalNumberOfRunsBelowMedian = 0;
		
		scaledData_Original = new ArrayList<Double>(scaledData);
		
		Collections.sort(scaledData);
		int medianIndex = 0;
		double median = 0.0;
		
		if(scaledData.size()%2 != 0)
		{
			medianIndex = scaledData.size()/2;
			median = scaledData.get(medianIndex);
		}
		else
		{
			medianIndex = scaledData.size()/2;
			median = (scaledData.get(medianIndex-1) + scaledData.get(medianIndex))/2;
		}
		log.debug("medianIndex::>"+medianIndex); 
		log.debug("median::>"+median); 
		for(int i=0; i<scaledData_Original.size(); i++)
        {
            double r = scaledData_Original.get(i);
            if(operator!=null)
            {
                if(r>=median)
                {	
					if(!operator)
                    {
                        operator=true;
                        totalNumberOfRuns++;
					}
						totalNumberOfRunsAboveMedian++;
                }
                else
                {
                    if(operator)
                    {
                        operator=false;
                        totalNumberOfRuns++;
					}
					totalNumberOfRunsBelowMedian++;
                }
            }
            else
            {
                if(r>=median)
				{
					operator=true;
					totalNumberOfRunsAboveMedian++;
				}
				else
				{
					operator=false;
					totalNumberOfRunsBelowMedian++;
				}					
			    totalNumberOfRuns++;
			}
            
        }
		log.debug("totalNumberOfRuns::>"+totalNumberOfRuns); 
		log.debug("totalNumberOfRunsAboveMedian::>"+totalNumberOfRunsAboveMedian);
		log.debug("totalNumberOfRunsBelowMediancomm::>"+totalNumberOfRunsBelowMedian);
    }
    
	/**
	 * Purpose : This function is used to compare Z-score values with corresponding critical values and decide whether the test is pass or fail
	 * 
	 */
    
    private void interpretResult()
	{
		if(alpha == 0.10)
		{
			if(Z_score <= standardZTable.get(0) && Z_score >= -standardZTable.get(0))
				rurdtest = true;
			else
				rurdtest = false;
		}
		else if(alpha == 0.05)
		{
			if(Z_score <= standardZTable.get(1) && Z_score >= -standardZTable.get(1))
				rurdtest = true;
			else
				rurdtest = false;
		}
		else if(alpha == 0.01)
		{
			if(Z_score <= standardZTable.get(2) && Z_score >= -standardZTable.get(2))
				rurdtest = true;
			else
				rurdtest = false;
		}
	}
}