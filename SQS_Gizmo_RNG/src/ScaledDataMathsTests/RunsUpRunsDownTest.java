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

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class RunsUpRunsDownTest implements Serializable{
	
    Logger log = Logger.getLogger(RunsUpRunsDownTest.class.getName());
	
	int totalNumberOfRuns = 0;
	
	ArrayList<Double> standardZTable;
    
	Boolean operator = null, rurdtest = false;
	
	String resulttext = "result";
    
	double number_of_samples, alpha, mean, sigma_square, Z_score;

	public RunsUpRunsDownTest() {
	}
	
	public String[] performRunsUpRunsDownTest(String alpha_level, ArrayList<Double> scaledData)
	{
		alpha = Double.parseDouble(alpha_level);
		
		log.debug("Here in Runs up runs down test"); 
		log.debug("alpha_level"+alpha_level); 
    
		standardZTable = new ArrayList<Double>();
        
		prepareStdCriticalValuesTable();
        calculationsRunsUpDown(scaledData);
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
		result[0] = Double.toString(mean); //" value of mean;
		result[1] = Double.toString(sigma_square); //value of sigma_square;
		result[2] = Double.toString(Z_score); //value of z score;
		result[3] = resulttext; // final result;
		result[4] = Integer.toString(totalNumberOfRuns); // total number of runs";
		result[5] = Double.toString(scaledData.size()); //number of samples;
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
	 * Purpose : This function is used to perform basic calculations for runs up and runs down test
	 * 
	 */
	 
    private void calculationsRunsUpDown(ArrayList<Double> scaledData)
    {
        calculateNumberOfRuns(scaledData);
        
        double a = totalNumberOfRuns;
        double N = scaledData.size();
        
		number_of_samples = N;
        mean = (2.0*N-1.0)/3.0;
        sigma_square = (16.0*N-29.0)/90.0;
        Z_score = (a-mean)/Math.sqrt(sigma_square);
		
		log.debug("totalNumberOfRuns:"+a); 
		log.debug("scaled data size"+N);
		log.debug("mean:"+mean); 
		log.debug("sigma_square"+sigma_square);
		log.debug("Z_score:"+Z_score); 
	}
	
	/**
	 * Purpose : This function is used to calculate total number of runs (runs up and run down)
	 * 
	 */
    
    private void calculateNumberOfRuns(ArrayList<Double> scaledData)
    {
		totalNumberOfRuns = 0;
		for(int i=1; i<scaledData.size(); i++)
        {
            double r = scaledData.get(i);
            if(operator!=null)
            {
                if(r>scaledData.get(i-1))
                {
                    if(!operator)
                    {
                        operator=true;
                        totalNumberOfRuns++;
						//log.debug("In calculate number of runs:1"); 
                    }
                }
                else
                {
                    if(operator)
                    {
                        operator=false;
                        totalNumberOfRuns++;
					//	log.debug("In calculate number of runs:2"); 
                    }
                }
            }
            else
            {
                if(r>scaledData.get(i-1))
				{
					operator=true;
					totalNumberOfRuns++;
					//log.debug("In calculate number of runs:3"); 
				}
					
                else 
				{
					operator=false;
					//log.debug("In calculate number of runs:4"); 
				}
               
				
            }
            
        }
		log.debug("In calculate number of runs:"); 
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
		
		log.debug("In interpretResult of runs up and runs down"); 
	}
	
}