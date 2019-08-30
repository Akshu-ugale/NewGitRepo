/**
 * Purpose : This java class is implemented to perform Interplay Correlation test on scaled data.
 * @author : Sheetal Sulay.
 * Date : 14/02/2016.
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

public class InterplayCorrelationTest implements Serializable
{
	Logger log = Logger.getLogger(InterplayCorrelationTest.class.getName());
 	ArrayList<Double> dataX;
	ArrayList<Double> dataY;
	
    Boolean interplayCorrelationTest = null;
	String resulttext = "Fail";
	double r, number_of_samples,number_of_symbols_actual,averageR=0.0;
	int passRate = 0, failRate = 0;
	double maxR = 0.0;
	double minR = 0.0;

	public InterplayCorrelationTest() 
	{
		
	}
	
	public String[] performInterplayCorrelationTest(String filePathUpload, String alpha, double min, double max, double [] scaledDataList, String numberType, int numbersPerDrawNumType)
	{
		dataX = new ArrayList<Double>();
		dataY = new ArrayList<Double>();
		
		passRate = 0;
		failRate = 0;
		
		log.debug("Here in Interplay correlation test"); 
		log.debug("FilePathUpload of InterplayCorrelationTest::>"+filePathUpload); 
		log.debug("NmberType::>"+numberType); 
		log.debug("alpha::>"+alpha); 
		log.debug("min::>"+min); 
		log.debug("max::>"+max);
		
		int d = (int)max-(int)min+1;
		int numbersPerDraw = 0;
		
		// if the random data is without replacement then we need to consider the numbers per draw from UI which may be less that the range.
		if(numberType.equals("wc_replacement"))
		{
			numbersPerDraw = numbersPerDrawNumType;
		}
		else
		{
			numbersPerDraw = d;
		}
		
		double n;
		if(numbersPerDraw%2 != 0)
		{
			n = (numbersPerDraw-1)/2;
		}
		else
		{
			n = numbersPerDraw/2;
		}
		maxR = getStandardMaxR(alpha, n);
		minR = getStandardMinR(alpha, n);
		
		int numOfDraws = scaledDataList.length/numbersPerDraw; //we are going to analyze the sequence of length range 
		log.debug("total number per draws:"+numbersPerDraw); 
		
		log.debug("Number of symbols of InterplayCorrelationTest::>"+numbersPerDraw); 
		log.debug("Number  of draws::>"+numOfDraws); 
		averageR = 0.0;
		interpretData(filePathUpload, numbersPerDraw, numOfDraws, alpha);
		
		log.debug("Passrate::>"+passRate); 
		log.debug("Failrate::>"+failRate); 
        
		averageR = averageR/(passRate+failRate);
		
		if(averageR <= maxR && averageR >= minR)
		{
			resulttext = "Pass";
		}
		else
		{
			resulttext = "Fail";	
		}
		
		String graphNature = "";
		if(averageR >= 0)
		{
			graphNature = "Right skewed as mean (0) is left to the peak "+averageR;
		}
		else
		{
			graphNature = "Right skewed as mean (0) is right to the peak "+averageR;
		}
		String result[] = new String[3];
		result[0] = Double.toString(averageR); //average r
		result[1] = graphNature; //left skewed/right skewed
		result[2] = resulttext; //"result";
		
		log.debug("average value of correlation coefficient:"+averageR); 
		
		return result;
	}
	
	/**
	 * Purpose : This function is used to perform basic calculations for Interplay correlation test. This function 
	 * returns result of interplay correlation test for each draw.
	 * 
	 */
	 
    private boolean calculationsInterplayCorrelationTest(String alpha)
    {
        
        double N=0, X=0, Y=0, sigma_X=0, sigma_Y=0, X_Square=0, Y_Square=0, sigmaSquare_X=0, sigmaSquare_Y=0, XY=0, sigma_XY=0;
		
		try
		{	
		
			N = dataX.size();
			
			if (dataX.size() > dataY.size())
			{
				dataY.add(0.0);
			}
				
			for(int i=0; i < dataX.size(); i++)
			{
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
			averageR = averageR + r;
						
			if(r <= maxR && r >= minR)
			{
				interplayCorrelationTest = true;
			}
			else
			{
				interplayCorrelationTest = false;
			}
				
		}
		catch(Exception e)
		{
			System.out.println("Exception::>"+e);
		}
	    return interplayCorrelationTest;
    }
	
	/**
	 * Purpose : This function is used to compute the max possible value of r for given alpha (level of significance)
	 * Formula used is derived from https://projecteuclid.org/download/pdf_1/euclid.aoms/1177731638 and 
	 * Annals of Mathematical Statistics Volume 13, Number 1, March, 1942
	 * Formula is r is = (-1 +/- 1.96 * sqrt (n-2))/(n-1) -- alpha 0.05 (two tail)
	 *            r is = (-1 +/- 2.58 * sqrt (n-2))/(n-1) -- alpha 0.01 (two tail)
	 *            r is = (-1 +/- 1.65 * sqrt (n-2))/(n-1) -- alpha 0.1  (two tail)
	 */
	public double getStandardMaxR(String alpha, double n)
	{
		double zScore = 0.0;
		if(alpha.equals("0.01"))
		{
			zScore = 2.58;
		}
		else if(alpha.equals("0.05"))
		{
			zScore = 1.96;
		}
		else if (alpha.equals("0.1"))
		{
			zScore = 1.65;
		}
		log.debug("result of zScore r:"+zScore);
		
		double r = (-1.0+zScore*Math.sqrt(n-2.0))/(n-1.0);
		
		log.debug("result of max r:"+r);
		
		return r;
	}
	
	/**
	 * Purpose : This function is used to compute the max possible value of r for given alpha (level of significance)
	 * Formula used is derived from https://projecteuclid.org/download/pdf_1/euclid.aoms/1177731638 and 
	 * Annals of Mathematical Statistics Volume 13, Number 1, March, 1942
	 * Formula is r is = (-1 +/- 1.96 * sqrt (n-2))/(n-1) -- alpha 0.05 (two tail)
	 *            r is = (-1 +/- 2.58 * sqrt (n-2))/(n-1) -- alpha 0.01 (two tail)
	 *            r is = (-1 +/- 1.65 * sqrt (n-2))/(n-1) -- alpha 0.1  (two tail)
	 */
	public double getStandardMinR(String alpha, double n)
	{
		double zScore = 0.0;
		if(alpha.equals("0.01"))
		{
			zScore = 2.58;
		}
		else if(alpha.equals("0.05"))
		{
			zScore = 1.96;
		}
		else if (alpha.equals("0.1"))
		{
			zScore = 1.65;
		}
		
		double r = (-1 - (zScore * Math.sqrt(n-2.0)))/(n-1.0);
		
		log.debug("result of min r:"+r); 
		
		return r;
	}
	
	/**
	 * Purpose : This function is used to prepared two arraylist for each single draw's random values from provided random file
	 * 
	 */
	 
    public void interpretData(String filePath, double numOfSymbolsDraws, double numOfDraws, String alpha)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			boolean localInterplayCorrelationTest = true;
			String filePathUpload = filePath.replace("/","\\");
			
			File file = new File(filePathUpload);
			Scanner sc = new Scanner(file);
			
			int subArraySize = 0;
			
			if(numOfSymbolsDraws%2 == 0)
			{
				subArraySize = (int)(numOfSymbolsDraws/2) - 1;
			}
			else
			{
				subArraySize = (int) numOfSymbolsDraws/2 ;
			}
			
			log.debug("subArraySize::>"+subArraySize); 
			
			for(int i=0; i < numOfDraws; i++)
			{
				String line= "";
				for(int j=0; j < numOfSymbolsDraws; j++)
				{
					line= Integer.toString(sc.nextInt());
					
					if(j > subArraySize)
						dataY.add(Double.parseDouble(line));
					else
						dataX.add(Double.parseDouble(line));
				}
			
				if(dataY.size() > dataX.size())
				{
					dataY.remove(dataY.size()-1);
				}
				if(dataX.size() > dataY.size())
				{
					dataX.remove(dataX.size()-1);
				}
				/*for(int y=0; y < dataX.size(); y++)
				{
					log.debug("dataX:"+dataX.get(y)); 
				}
				for(int z=0; z < dataY.size(); z++)
				{
					log.debug("dataY:"+dataY.get(z)); 
				}*/
				localInterplayCorrelationTest = calculationsInterplayCorrelationTest(alpha);
							
				if(localInterplayCorrelationTest == true)
				{
					passRate++;
				}
				else
				{
					failRate++;
				}
				
				dataX.clear();
				dataY.clear();
			}
		}
        catch(Exception e)
        {
            System.out.println("Exception is interpretData of interplayCorrelationTest"+e.getMessage());
			e.printStackTrace();
        }
	}
}