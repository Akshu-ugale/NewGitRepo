/**
 * Purpose : This java class is implemented to perform Serial Correlation test on scaled data
 * @author : Sheetal Sulay
 * Date : 09/02/2016
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

public class SerialCorrelationTest implements Serializable{
	Logger log = Logger.getLogger(SerialCorrelationTest.class.getName());
 	ArrayList<Double> dataX;
	ArrayList<Double> dataY;
    Boolean operator = null, serialCorrelationTest = null;
	String resulttext = "result";
    double r, number_of_samples;

	public SerialCorrelationTest() 
	{
		
	}
	
	public String[] performSerialCorrelationTest(String filePathUpload)
	{
		dataX = new ArrayList<Double>();
		dataY = new ArrayList<Double>();
		
		log.debug("Here in serial correlation test"); 
		log.debug("FilePathUpload of serialCorrelationTest::>"+filePathUpload); 
		
		interpretData(filePathUpload);
        calculationsSerialCorrelationTest();
		interpretResult();
		
		String strDouble = String.format("%.15f", r);
		
		
      	if(serialCorrelationTest == true)
		{
			resulttext = "Pass";
		}
		else
		{
			resulttext = "Fail";	
		}
		
		String result[] = new String[3];
		result[0] = Double.toString(number_of_samples); //Number Of  Samples
		result[1] = strDouble; //Correlation Coefficient
		result[2] = resulttext; //"result";
		
		log.debug("result of correlation coefficient:"+strDouble); 
		log.debug("result of serialCorrelationTest:"+resulttext);
		
		return result;
	}
	
	/**
	 * Purpose : This function is used to perform basic calculations for serial correlation test
	 * 
	 */
	 
    private void calculationsSerialCorrelationTest()
    {
        double N=0, X=0, Y=0, sigma_X=0, sigma_Y=0, X_Square=0, Y_Square=0, sigmaSquare_X=0, sigmaSquare_Y=0, XY=0, sigma_XY=0;
		
		try{	
		
			N = dataX.size();
			if (dataX.size() > dataY.size())
			{
				dataY.add(0.0);
			}
				
			for(int i=0; i < dataX.size(); i++){
				X = dataX.get(i);
				Y = dataY.get(i);
				
				sigma_X = sigma_X + X;
				sigma_Y = sigma_Y + Y;
				
				X_Square = dataX.get(i) * dataX.get(i);
				Y_Square = dataY.get(i) * dataY.get(i);
				
				sigmaSquare_X = sigmaSquare_X + X_Square;
				sigmaSquare_Y = sigmaSquare_Y + Y_Square;
				
				XY = dataX.get(i)* dataY.get(i);
				sigma_XY = sigma_XY + XY ;
			}
			
			r = ((N*sigma_XY)-(sigma_X * sigma_Y))/Math.sqrt((N*sigmaSquare_X - (sigma_X*sigma_X))*(N*sigmaSquare_Y - (sigma_Y*sigma_Y)));
			number_of_samples = N;
			
			log.debug("In calculationsSerialCorrelationTest : r::>"+r);
			log.debug("In calculationsSerialCorrelationTest : number_of_samples::>"+number_of_samples);
		}
		catch(Exception e)
		{
			log.debug("Exception in calculationsSerialCorrelationTest function of Serial correlation test"+e);
		}
	    
    }
	
	   
	/**
	 * Purpose : This function is used to prepare two separate arraylists of scaleddata from provided random file which will be used
	 * to calculate serialCorrelationTest
	 * 
	 */
	 
    public void interpretData(String filePath)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
			String filePathUpload = filePath.replace("/","\\");
			
			File file = new File(filePathUpload);
			
			double d;
			
			br = new BufferedReader(new FileReader(file));
			String line= br.readLine();    //Every number at odd positions
			String line1 = br.readLine();  //Every number at even positions  
			log.debug("here in interpretdata value of line1"+line1);
			log.debug("filepath"+filePath);
			while(line !=null){
			
				dataX.add(Double.parseDouble(line));
				
				if(line1 != null)
				{
				  dataY.add(Double.parseDouble(line1));
				}
				
				line=br.readLine();
				line1=br.readLine();
				
			}
		}
		catch(Exception ex)
		{
				log.debug("Exception in interpretData of serialCorrelationTest"+ex.getMessage());
		} 
	}
	
	/**
	 * Purpose : This function is used to compare r (correlation coefficient) value with corresponding expected values and decide whether the test is pass or fail
	 * 
	 */
    
    private void interpretResult()
	{
		
		if(r < 0.5 && r > -0.5)
		{
			serialCorrelationTest = true;
		}
		else{
			serialCorrelationTest = false;
		}
		
		
	}	
}