/**
 * Purpose : This java class is implemented to fetch base input data.
 * @author : Sheetal Sulay
 * Date : 30/012/2016
*/

package scaleddatamathstests;

import java.io.*;
import java.util.Arrays;
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
							
public class InputBaseData implements Serializable{
	
    Logger log = Logger.getLogger(InputBaseData.class.getName());
	ArrayList<Double> scaledDataList;
	double [] scaledDataArray;
	double min = 0 , max = 0;
	private static String[] testResult = new String[18];
	
	// base data
	private String errorMessage = "None";
	private String gameName = "";
	private String gameVersion = "";
	private String alpha = "";
	private String fileNameUpload = "";
	private String numberType = "";
	private String[] testNames = new String[20];
	private int numbersPerDraw = 0;
		
	
	//Chi-square test;
	private String chiSquareResult = "Not Run";
	
	ChiSquareGizmoTest chiSquare = new ChiSquareGizmoTest();
	
	//KolmogorovSmirnov test;
	private double dMax = 0.0;
	private String ksResult = "Not Run";
	private double kspValue =0.0;
		
	KolmogorovSmirnovGizmoTest kst = new KolmogorovSmirnovGizmoTest();
	
	// Runs up Runs down test
	private String mean = "";
	private String sigmaSquare = "";
	private double zScore = 0.0;
	private String rurdResult = "Not Run";
	private int totalNumberOfRuns = 0;
	private double numberOfSamples = 0.0;
	
	RunsUpRunsDownTest rurdt = new RunsUpRunsDownTest();
	
	//Serial Correlation test
	private double numberOfSamplesSC = 0.0;
	private double correlationCoefficient = 0.0;
	private String serialCorrResult = "Not Run";
	
	SerialCorrelationTest serialCorr = new SerialCorrelationTest();
	
	//Interplay Correlation test
	private double passRate = 0.0;
	private String failRate = "";
	private String interplayCorrResult = "Not Run";
	
	InterplayCorrelationTest interPlayCorr = new InterplayCorrelationTest();
	
	// Median Runs  test
	private String medianMean = "";
	private String medianSigmaSquare = "";
	private double medianZScore = 0.0;
	private String medianResult = "Not Run";
	private int medianTotalNumberOfRuns = 0;
	private double medianNumberOfSamples = 0.0;
	
	MedianRunsTest medianRuns = new MedianRunsTest();
	
	//Equidistribution (Frequency) test
	
	private String frequencyResult = "Not Run";
	
	FrequencyTest ft = new FrequencyTest();
	
	//Gap test
	
	private String gapResult = "Not Run";
	
	GapTest gap = new GapTest();
	
	//Serial test
	
	private String serialResult = "Not Run";
	
	SerialTest serial = new SerialTest();
	
	//Poker test
	
	private String pokerResult = "Not Run";
	
	PokerTest poker = new PokerTest();
	
	//CouponsCollector test
	
	private String couponsCollectorResult = "Not Run";
	
	CouponsCollectorTest couponsCollector = new CouponsCollectorTest();
	
	//Maximum Of t test;
	private double dMax_MTT = 0.0;
	private String ksResult_MTT = "Not Run";
	private double kspValue_MTT =0.0;
		
	MaximumOfTTest mtt = new MaximumOfTTest();
	
	//Permutation test
	
	private String permutationTestResult = "Not Run";
	
	PermutationTest permutation = new PermutationTest();
	
	//Poisson Distribution test
	
	private String poissonDistributionTestResult = "Not Run";
	private String comment = "";
	
	PoissonDistributionTest poisson = new PoissonDistributionTest();
	
	//Collision test
	
	private String collisionTestResult = "Not Run";
	
	CollisionTest collision = new CollisionTest();
	
	//Duplicates test
	
	private String duplicatesTestResult = "Not Run";
	
	DuplicatesTest duplicates = new DuplicatesTest();
	
	//Adjacency blocks test
	
	private String adjacencyBlocksTestResult = "Not Run";
	
	AdjacencyTest adjacency = new AdjacencyTest();
	
	// Test on subsequences
	
	private String subsequencesTestResult = "Not Run";
	private String subSequenceChiSquareResult = "";
	private String subSequencemean = "";
	private String subSequenceSigmaSquare = "";
	private double subSequenceZScore = 0.0;
	private String subSequenceRurdResult = "";
	private int subSequenceTotalNumberOfRuns = 0;
	private double subSequenceNumberOfSamples = 0.0;
	private String subSequenceGapResult = "";
	private String subSequencePoissonDistributionTestResult = "";
	private String subSequenceComment = "";
	private String subSequenceDuplicatesTestResult = "";
	private String subSequenceAdjacencyBlocksTestResult = "";
	
	
	// Getters and setters for all test results
	public static String[] GetTestResult()
	{
		return testResult;
	}
	public void SetTestResult(String testResult[])
	{
		this.testResult = testResult;
	}
	
	// Getters and setters for Base data (Common data with respectie to game)
	
	public String GetErrorMessage()
	{
		return errorMessage;
	}
	public void SetErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	
	public String GetGameName()
	{
		return gameName;
	}
	public void SetGameName(String gameName)
	{
		this.gameName = gameName;
	}
	
	public String GetGameVersion()
	{
		return gameVersion;
	}
	public void SetGameVersion(String gameVersion)
	{
		this.gameVersion = gameVersion;
	}
	
	public String GetAlpha()
	{
		return alpha;
	}
	public void SetAlpha(String alpha)
	{
		this.alpha = alpha;
	}
	
	public String GetFileNameUpload()
	{
		return fileNameUpload;
	}
	public void SetFileNameUpload(String fileNameUpload)
	{
		this.fileNameUpload = fileNameUpload;
	}
	
	public String[] GetTestNames()
	{
		return testNames;
	}
	public void SetTestNames(String[] testNames)
	{
		this.testNames = testNames;
	}
	
	public int GetNumbersPerDraw()
	{
		return numbersPerDraw;
	}
	public void SetNumbersPerDraw(int numbersPerDraw)
	{
		this.numbersPerDraw = numbersPerDraw;
	}
	
	public String GetNumberType()
	{
		return numberType;
	}
	public void SetNumberType(String numberType)
	{
		this.numberType = numberType;
	}
	
	// Getters and setters for Chi-square test
	public String GetChiSquareResult()
	{
		return chiSquareResult;
	}
	public void SetChiSquareResult(String chiSquareResult)
	{
		this.chiSquareResult = chiSquareResult;
	}
	
	// Getters and setters for Kolmoronov-smirnov test
	public double GetDMax()
	{
		return dMax;
	}
	public void SetDMax(double dMax)
	{
		this.dMax = dMax;
	}
	public double GetKspValue()
	{
		return kspValue;
	}
	public void SetKspValue(double kspValue)
	{
		this.kspValue = kspValue;
	}
	public String GetKsResult()
	{
		return ksResult;
	}
	public void SetKsResult(String ksResult)
	{
		this.ksResult = ksResult;
	}
	
	// Getters and setters for Runs up and Runs down test
	public String GetMean()
	{
		return mean;
	}
	public void SetMean(String mean)
	{
		this.mean = mean;
	}
	
	public String GetSigmaSquare()
	{
		return sigmaSquare;
	}
	public void SetSigmaSquare(String sigmaSquare)
	{
		this.sigmaSquare = sigmaSquare;
	}
	
	public double GetZScore()
	{
		return zScore;
	}
	public void SetZScore(double zScore)
	{
		this.zScore = zScore;
	}
	
	public String GetRurdResult()
	{
		return rurdResult;
	}
	public void SetRurdResult(String rurdResult)
	{
		this.rurdResult = rurdResult;
	}
	
	public int GetTotalNumberOfRuns()
	{
		return totalNumberOfRuns;
	}
	public void SetTotalNumberOfRuns(int totalNumberOfRuns)
	{
		this.totalNumberOfRuns = totalNumberOfRuns;
	}
	public double GetNumberOfSamples()
	{
		return numberOfSamples;
	}
	public void SetNumberOfSamples(double numberOfSamples)
	{
		this.numberOfSamples = numberOfSamples;
	}
	
	//Getters and setters for Serial Correlation Test
	
	public double GetNumberOfSamplesSC()
	{
		return numberOfSamplesSC;
	}
	public void SetNumberOfSamplesSC(double numberOfSamplesSC)
	{
		this.numberOfSamplesSC  = numberOfSamplesSC ;
	}
	
	public double GetCorrelationCoefficient()
	{
		return correlationCoefficient ;
	}
	public void SetCorrelationCoefficient(double correlationCoefficient)
	{
		this.correlationCoefficient   = correlationCoefficient  ;
	}
	
	public String GetserialCorrResult()
	{
		return serialCorrResult  ;
	}
	public void SetSerialCorrResult(String serialCorrResult)
	{
		this.serialCorrResult   = serialCorrResult  ;
	}
	
	// /Getters and setters for Interplay Correlation Test
	
	public double GetPassRate()
	{
		return passRate;
	}
	public void SetPassRate(double passRate)
	{
		this.passRate  = passRate;
	}
	
	public String GetFailRate()
	{
		return failRate;
	}
	public void SetFailRate(String failRate)
	{
		this.failRate   = failRate;
	}
	
	public String GetInterplayCorrResult()
	{
		return interplayCorrResult;
	}
	public void SetInterplayCorrResult(String interplayCorrResult)
	{
		this.interplayCorrResult   = interplayCorrResult  ;
	}
	
	// Getters and setters for Median Runs Test
	
	public String GetMedianMean()
	{
		return medianMean;
	}
	public void SetMedianMean(String medianMean)
	{
		this.medianMean = medianMean;
	}
	
	public String GetMedianSigmaSquare()
	{
		return medianSigmaSquare;
	}
	public void SetMedianSigmaSquare(String medianSigmaSquare)
	{
		this.medianSigmaSquare = medianSigmaSquare;
	}
	
	public double GetMedianZScore()
	{
		return medianZScore;
	}
	public void SetMedianZScore(double medianZScore)
	{
		this.medianZScore = medianZScore;
	}
	
	public String GetMedianResult()
	{
		return medianResult;
	}
	public void SetMedianResult(String medianResult)
	{
		this.medianResult = medianResult;
	}
	
	public int GetMedianTotalNumberOfRuns()
	{
		return medianTotalNumberOfRuns;
	}
	public void SetMedianTotalNumberOfRuns(int medianTotalNumberOfRuns)
	{
		this.medianTotalNumberOfRuns = medianTotalNumberOfRuns;
	}
	public double GetMedianNumberOfSamples()
	{
		return medianNumberOfSamples;
	}
	public void SetMedianNumberOfSamples(double medianNumberOfSamples)
	{
		this.medianNumberOfSamples = medianNumberOfSamples;
	}
	
	// Getters and setters for Frequency Test
	public String GetFrequencyResult()
	{
		return frequencyResult;
	}
	public void SetFrequencyResult(String frequencyResult)
	{
		this.frequencyResult = frequencyResult;
	}
	
	// Getters and setters for Gap Test
	public String GetGapResult()
	{
		return gapResult;
	}
	public void SetGapResult(String gapResult)
	{
		this.gapResult = gapResult;
	}
	
	// Getters and setters for Serial Test
	public String GetSerialResult()
	{
		return serialResult;
	}
	public void SetSerialResult(String serialResult)
	{
		this.serialResult = serialResult;
	}
	
	// Getters and setters for Poker Test
	public String GetPokerResult()
	{
		return pokerResult;
	}
	public void SetPokerResult(String pokerResult)
	{
		this.pokerResult = pokerResult;
	}
	
	// Getters and setters for CouponsCollector Test
	public String GetCouponsCollectorResult()
	{
		return couponsCollectorResult;
	}
	public void SetCouponsCollectorResult(String couponsCollectorResult)
	{
		this.couponsCollectorResult = couponsCollectorResult;
	}
	
	// Getters and setters for MaximumOfTTest test
	public double GetDMax_MTT()
	{
		return dMax_MTT;
	}
	public void SetDMax_MTT(double dMax_MTT)
	{
		this.dMax_MTT = dMax_MTT;
	}
	public double GetKspValue_MTT()
	{
		return kspValue_MTT;
	}
	public void SetKspValue_MTT(double kspValue_MTT)
	{
		this.kspValue_MTT = kspValue_MTT;
	}
	public String GetKsResult_MTT()
	{
		return ksResult_MTT;
	}
	public void SetKsResult_MTT(String ksResult_MTT)
	{
		this.ksResult_MTT = ksResult_MTT;
	}
	
	// Getters and setters for Permutation Test
	public String GetPermutationTestResult()
	{
		return permutationTestResult;
	}
	public void SetPermutationTestResult(String permutationTestResult)
	{
		this.permutationTestResult = permutationTestResult;
	}
		
	// Getters and setters for Poisson distribution Test
	public String GetPoissonDistributionTestResult()
	{
		return poissonDistributionTestResult;
	}
	public void SetPoissonDistributionTestResult(String poissonDistributionTestResult)
	{
		this.poissonDistributionTestResult = poissonDistributionTestResult;
	}
	
	public String GetComment()
	{
		return comment;
	}
	public void SetComment(String comment)
	{
		this.comment = comment;
	}
	
	// Getters and setters for collision test
	public String GetCollisionTestResult()
	{
		return collisionTestResult;
	}
	public void SetCollisionTestResult(String collisionTestResult)
	{
		this.collisionTestResult = collisionTestResult;
	}
	
	// Getters and setters for Duplicates Test
	public String GetDuplicatesTestResult()
	{
		return duplicatesTestResult;
	}
	public void SetDuplicatesTestResult(String duplicatesTestResult)
	{
		this.duplicatesTestResult = duplicatesTestResult;
	}
	
	// Getters and setters for adjacency blocks test
	public String GetAdjacencyBlocksTestResult()
	{
		return adjacencyBlocksTestResult;
	}
	public void SetAdjacencyBlocksTestResult(String adjacencyBlocksTestResult)
	{
		this.adjacencyBlocksTestResult = adjacencyBlocksTestResult;
	}
	
	// Getters and setters for Test on subSequences
	public String GetSubsequencesTestResult()
	{
		return subsequencesTestResult;
	}
	public void SetSubsequencesTestResult(String subsequencesTestResult)
	{
		this.subsequencesTestResult = subsequencesTestResult;
	}
	
	public String GetSubSequenceChiSquareResult()
	{
		return subSequenceChiSquareResult;
	}
	public void SetSubSequenceChiSquareResult(String subSequenceChiSquareResult)
	{
		this.subSequenceChiSquareResult = subSequenceChiSquareResult;
	}
	
	public String GetSubSequenceMean()
	{
		return subSequencemean;
	}
	public void SetSubSequenceMean(String subSequencemean)
	{
		this.subSequencemean = subSequencemean;
	}
	
	public String GetSubSequenceSigmaSquare()
	{
		return subSequenceSigmaSquare;
	}
	public void SetSubSequenceSigmaSquare(String subSequenceSigmaSquare)
	{
		this.subSequenceSigmaSquare = subSequenceSigmaSquare;
	}
	
	public double GetSubSequenceZScore()
	{
		return subSequenceZScore;
	}
	public void SetSubSequenceZScore(double subSequenceZScore)
	{
		this.subSequenceZScore = subSequenceZScore;
	}
	
	public String GetSubSequenceRurdResult()
	{
		return subSequenceRurdResult;
	}
	public void SetSubSequenceRurdResult(String subSequenceRurdResult)
	{
		this.subSequenceRurdResult = subSequenceRurdResult;
	}
	
	public int GetSubSequenceTotalNumberOfRuns()
	{
		return subSequenceTotalNumberOfRuns;
	}
	public void SetSubSequenceTotalNumberOfRuns(int subSequenceTotalNumberOfRuns)
	{
		this.subSequenceTotalNumberOfRuns = subSequenceTotalNumberOfRuns;
	}
	
	public double GetSubSequenceNumberOfSamples()
	{
		return subSequenceNumberOfSamples;
	}
	public void SetSubSequenceNumberOfSamples(double subSequenceNumberOfSamples)
	{
		this.subSequenceNumberOfSamples = subSequenceNumberOfSamples;
	}
	
	public String GetSubSequenceGapResult()
	{
		return subSequenceGapResult;
	}
	public void SetSubSequenceGapResult(String subSequenceGapResult)
	{
		this.subSequenceGapResult = subSequenceGapResult;
	}
	
	public String GetSubSequencePoissonDistributionTestResult()
	{
		return subSequencePoissonDistributionTestResult;
	}
	public void SetSubSequencePoissonDistributionTestResult(String subSequencePoissonDistributionTestResult)
	{
		this.subSequencePoissonDistributionTestResult = subSequencePoissonDistributionTestResult;
	}
	
	public String GetSubSequenceComment()
	{
		return subSequenceComment;
	}
	public void SetSubSequenceComment(String subSequenceComment)
	{
		this.subSequenceComment = subSequenceComment;
	}
	
	public String GetSubSequenceDuplicatesTestResult()
	{
		return subSequenceDuplicatesTestResult;
	}
	public void SetSubSequenceDuplicatesTestResult(String subSequenceDuplicatesTestResult)
	{
		this.subSequenceDuplicatesTestResult = subSequenceDuplicatesTestResult;
	}
	
	public String GetSubSequenceAdjacencyBlocksTestResult()
	{
		return subSequenceAdjacencyBlocksTestResult;
	}
	public void SetSubSequenceAdjacencyBlocksTestResult(String subSequenceAdjacencyBlocksTestResult)
	{
		this.subSequenceAdjacencyBlocksTestResult = subSequenceAdjacencyBlocksTestResult;
	}
	
	public InputBaseData()
	{
		
	}
	
	public void performCalculation()
	{
		/*try
		{
			String reportFileName = "C:\\demo1.txt";
			FileWriter fw = new FileWriter(reportFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//bw = new BufferedWriter(fw);
			bw.write("hi");
			fw.close();
			bw.close();
		}
		catch(Exception e)
		{
			log.debug("Exception in writing report file in input base data::>"+e)
		}*/
		String testNames[] = new String[18];
		testNames = GetTestNames();
		
		scaledDataList = interpretScaledData(fileNameUpload);
		if(errorMessage.equals("None"))
		{
			resetResult();
			if(numbersPerDraw > (max-min+1) && numberType.equals("wc_replacement"))
			{
				numbersPerDraw = (int)max-(int)min+1;
				log.debug("Very unusal input was entered by user which is numbers per draw greater than range of the data so we adjusted it to the range");
			}
			
			for(int i=0; i < testNames.length ; i++)
			{
				if(testNames[i] != null)
				{
					String choiceTest = testNames[i];
					switch(choiceTest)
					{
						case "cst": chiSquareTest();
						break;
						case "ft": frequencyTest();
						break;
						case "rurdt": runsUpRunsDownTest();
						break;
						case "kst": kolmogorovSmirnovTest();
						break;
						case "sct": serialCorrelationTest();
						break;
						case "pt": pokerTest();
						break;
						case "ict": interplayCorrelationTest();
						break;
						case "st": serialTest();
						break;
						case "cct": couponsCollectorTest();
						break;
						case "gt": gapTest();
						break;
						case "pert": permutationTest();
						break;
						case "mt": maximumTTest();
						break;
						case "ct": collisionTest();
						break;
						case "ts": testOnSubsequences();
						break;
						case "dt": duplicatesTest();
						break;
						case "pdt": poissionDistributionTest();
						break;
						case "udt": adjacencyTest();
						break;
						case "mrt": medianRunsTest();
						break;
					
					}
				}
			}
			//Prepare final test result array to pass it to pie chart;
			String tempResult = "Not Run";
			
			if(GetChiSquareResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetChiSquareResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[0]  = tempResult;
			tempResult = "Not Run";
			
			if(GetFrequencyResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetFrequencyResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[1]  = tempResult;
			tempResult = "Not Run";
			
			if(GetRurdResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetRurdResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[2]  = tempResult;
			tempResult = "Not Run";
			
			if(GetKsResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetKsResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[3]  = tempResult;
			tempResult = "Not Run";
			
			if(GetserialCorrResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetserialCorrResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[4]  = tempResult;
			tempResult = "Not Run";
			
			if(GetPokerResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetPokerResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[5]  = tempResult;
			tempResult = "Not Run";
			
			if(GetInterplayCorrResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetInterplayCorrResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[6]  = tempResult;
			tempResult = "Not Run";
			
			if(GetDuplicatesTestResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetDuplicatesTestResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[7]  = tempResult;
			tempResult = "Not Run";
			
			if(GetSerialResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetSerialResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[8]  = tempResult;
			tempResult = "Not Run";
			
			if(GetCouponsCollectorResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetCouponsCollectorResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[9]  = tempResult;
			tempResult = "Not Run";
			
			if(GetGapResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetGapResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[10] = tempResult;
			tempResult = "Not Run";
			
			if(GetPermutationTestResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetPermutationTestResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[11] = tempResult;
			tempResult = "Not Run";
			
			if(GetKsResult_MTT().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetKsResult_MTT().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[12] = tempResult;
			tempResult = "Not Run";
			
			if(GetCollisionTestResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetCollisionTestResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[13] = tempResult;
			tempResult = "Not Run";
			
			if(GetSubsequencesTestResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetSubsequencesTestResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[14] = tempResult;
			tempResult = "Not Run";
			
			if(GetMedianResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetMedianResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[15] = tempResult;
			tempResult = "Not Run";
			
			if(GetPoissonDistributionTestResult().equals("Pass (This test is -ve test)"))
			{
				tempResult = "Fail";
			}
			else if(GetPoissonDistributionTestResult().equals("Fail (This test is -ve test)"))
			{
				tempResult = "Pass";
			}
			testResult[16] = tempResult;
			tempResult = "Not Run";

			if(GetAdjacencyBlocksTestResult().equals("Pass"))
			{
				tempResult = "Pass";
			}
			else if(GetAdjacencyBlocksTestResult().equals("Fail"))
			{
				tempResult = "Fail";
			}
			testResult[17] = tempResult;
			
			SetTestResult(testResult);
		}	
	}
	
	public void resetResult()
	{
		SetChiSquareResult("Not Run");
		SetFrequencyResult("Not Run");
		SetRurdResult("Not Run");
		SetKsResult("Not Run");
		SetSerialCorrResult("Not Run");
		SetPokerResult("Not Run");
		SetInterplayCorrResult("Not Run");
		SetDuplicatesTestResult("Not Run");
		SetSerialResult("Not Run");
		SetCouponsCollectorResult("Not Run");
		SetGapResult("Not Run");
		SetPermutationTestResult("Not Run");
		SetKsResult_MTT("Not Run");
		SetCollisionTestResult("Not Run");
		SetSubsequencesTestResult("Not Run");
		SetMedianResult("Not Run");
		SetPoissonDistributionTestResult("Not Run");
		SetAdjacencyBlocksTestResult("Not Run");
	}
	
	public void chiSquareTest()
	{
		String result[] = chiSquare.performChiSquareTest(alpha,scaledDataArray,min,max);
		SetChiSquareResult(result[0]);
	}
	
	public void frequencyTest()
	{
		String result[] = ft.performFrequencyTest(alpha,scaledDataList);
		SetFrequencyResult(result[0]);
	}
	
	public void runsUpRunsDownTest()
	{
		String result[] = rurdt.performRunsUpRunsDownTest(alpha,scaledDataList);
		SetMean(result[0]);
		SetSigmaSquare(result[1]);
		SetZScore(Double.parseDouble(result[2]));
		SetRurdResult(result[3].toString());
		SetTotalNumberOfRuns(Integer.parseInt(result[4]));
		SetNumberOfSamples(Double.parseDouble(result[5]));
	}
	
	public void kolmogorovSmirnovTest()
	{
		String result[] = kst.performKolmogorovSmirnovTest(alpha,scaledDataArray);
		SetDMax(Double.parseDouble(result[0]));
		SetKsResult(result[1]);
		SetKspValue(Double.parseDouble(result[2]));
	}
	
	public void serialCorrelationTest()
	{
		String resultSerial[] = serialCorr.performSerialCorrelationTest(fileNameUpload);
		SetNumberOfSamplesSC(Double.parseDouble(resultSerial[0]));
		SetCorrelationCoefficient(Double.parseDouble(resultSerial[1]));
		SetSerialCorrResult(resultSerial[2]);
		log.debug("here in input base data : serial correlation test function"); 
	}
	
	public void pokerTest()
	{
		String result[] = poker.performPokerTest(alpha,scaledDataArray,min,max);
		SetPokerResult(result[0]);
	}
	
	public void interplayCorrelationTest()
	{
		String resultInterplay[] = interPlayCorr.performInterplayCorrelationTest(fileNameUpload,alpha,min,max,scaledDataArray,numberType,numbersPerDraw);
		SetPassRate(Double.parseDouble(resultInterplay[0]));
		SetFailRate(resultInterplay[1]);
		SetInterplayCorrResult(resultInterplay[2]);
		log.debug("here in input base data : interplay correlation test function"); 
	}
	
	public void serialTest()
	{
		String result[] = serial.performSerialTest(alpha,scaledDataArray,min,max);
		SetSerialResult(result[0]);
	}
	
	public void couponsCollectorTest()
	{
		String result[] = couponsCollector.performCouponsCollectorTest(alpha,scaledDataArray,min,max);
		SetCouponsCollectorResult(result[0]);
	}
	
	public void gapTest()
	{
		String result[] = gap.performGapTest(alpha,scaledDataArray,min,max);
		SetGapResult(result[0]);
	}
	
	public void permutationTest()
	{
		String result[] = permutation.performPermutationTest(alpha,scaledDataArray,min,max,numberType,numbersPerDraw);
		SetPermutationTestResult(result[0]);
	}
	
	public void maximumTTest()
	{
		String result[] = mtt.performMaximumOfTTest(alpha,scaledDataArray,min,max);
		SetDMax_MTT(Double.parseDouble(result[0]));
		SetKsResult_MTT(result[1]);
		SetKspValue_MTT(Double.parseDouble(result[2]));
	}
	
	public void collisionTest()
	{
		//SetAlpha("test zali re: collisionTest test");
		String result[] = collision.performCollisionTest(alpha,scaledDataArray,min,max,numberType,numbersPerDraw);
		SetCollisionTestResult(result[0]);
	}
	
	public void testOnSubsequences()
	{
		int subSequenceLength = 0;
		double minSubSequence = 0;
		double maxSubSequence = 0;
		
		if(scaledDataArray.length >=32)
		{
			subSequenceLength = 32;
		}
		else
		{
			subSequenceLength = scaledDataArray.length;
		}
		
		double[] subSequence = Arrays.copyOfRange(scaledDataArray, 0, subSequenceLength);
		
		// finding min, max of scaled data to find out the range of the data
		if(subSequence.length > 0)
		{
			minSubSequence = subSequence[0];
			maxSubSequence = subSequence[0];
		}
		for(int j = 0; j < subSequence.length; j++ )
		{
			if(subSequence[j] > maxSubSequence)
				maxSubSequence = subSequence[j];
			else if (subSequence[j] < minSubSequence)
				minSubSequence = subSequence[j];
		}
				
		// Chi-square for subsequence
		
		String resultSubChiSquare[] = chiSquare.performChiSquareTest(alpha,subSequence,minSubSequence,maxSubSequence);
		SetSubSequenceChiSquareResult(resultSubChiSquare[0]);
		
		// Runs up runs down test for subsequence
		ArrayList<Double> subSequenceList = new ArrayList<Double>();
		for(int i=0; i<subSequence.length; i++)
		{
			subSequenceList.add(subSequence[i]); 
		}
		
		String resultRunsUpRunsDown[] = rurdt.performRunsUpRunsDownTest(alpha,subSequenceList);
		SetSubSequenceMean(resultRunsUpRunsDown[0]);
		SetSubSequenceSigmaSquare(resultRunsUpRunsDown[1]);
		SetSubSequenceZScore(Double.parseDouble(resultRunsUpRunsDown[2]));
		SetSubSequenceRurdResult(resultRunsUpRunsDown[3].toString());
		SetSubSequenceTotalNumberOfRuns(Integer.parseInt(resultRunsUpRunsDown[4]));
		SetSubSequenceNumberOfSamples(Double.parseDouble(resultRunsUpRunsDown[5]));
		
		// Gap test for subsequence
		
		String resultGap[] = gap.performGapTest(alpha,subSequence,minSubSequence,maxSubSequence);
		SetSubSequenceGapResult(resultGap[0]);
		
		// Duplicates test for subsequence
		
		String resultDuplicates[] = duplicates.performDuplicatesTest(alpha,subSequence,minSubSequence,maxSubSequence,numberType,numbersPerDraw);
		SetSubSequenceDuplicatesTestResult(resultDuplicates[0]);
		
		// Poisson test for subsequence
		
		String resultPoisson[] = poisson.performPoissonDistribution(alpha,subSequence,minSubSequence,maxSubSequence);
		SetSubSequencePoissonDistributionTestResult(resultPoisson[0]);
		SetSubSequenceComment(resultPoisson[1]);
		
		// AdjacencyBlocksTest for subsequence
		
		String resultAdjancency[] = adjacency.performAdjacencyTest(alpha,subSequence,numberType,minSubSequence,maxSubSequence);
		SetSubSequenceAdjacencyBlocksTestResult(resultAdjancency[0]);
		
		if(GetSubSequenceChiSquareResult().equals("Pass") && GetSubSequenceAdjacencyBlocksTestResult().equals("Pass")  
			&& GetSubSequencePoissonDistributionTestResult().equals("Fail (This test is -ve test)") && GetSubSequenceDuplicatesTestResult().equals("Pass")
			&& GetSubSequenceGapResult().equals("Pass") && GetSubSequenceRurdResult().equals("Pass"))
		{
			SetSubsequencesTestResult("Pass");
		}
		else
		{
			SetSubsequencesTestResult("Fail");
		}
	}
	
	public void duplicatesTest()
	{
		String resultDuplicates[] = duplicates.performDuplicatesTest(alpha,scaledDataArray,min,max,numberType,numbersPerDraw);
		SetDuplicatesTestResult(resultDuplicates[0]);
	}
	
	public void poissionDistributionTest()
	{
		String result[] = poisson.performPoissonDistribution(alpha,scaledDataArray,min,max);
		SetPoissonDistributionTestResult(result[0]);
		SetComment(result[1]);
	}
	
	public void adjacencyTest()
	{
		String result[] = adjacency.performAdjacencyTest(alpha,scaledDataArray,numberType,min,max);
		SetAdjacencyBlocksTestResult(result[0]);
	}
	
	public void medianRunsTest()
	{
		String resultMedianRunsTest[] = medianRuns.performMedianRunsTest(alpha,scaledDataList);
		SetMedianMean(resultMedianRunsTest[0]);
		SetMedianSigmaSquare(resultMedianRunsTest[1]);
		SetMedianZScore(Double.parseDouble(resultMedianRunsTest[2]));
		SetMedianResult(resultMedianRunsTest[3].toString());
		SetMedianTotalNumberOfRuns(Integer.parseInt(resultMedianRunsTest[4]));
		SetMedianNumberOfSamples(Double.parseDouble(resultMedianRunsTest[5]));
	}
	
	/**
	 * Purpose : This function is used to prepared arraylist and array of scaleddata from provided random file
	 * we are not sure about size of the scaled data so we cannot used only array to iterate the scaled data
	 * As arraylist dynamically grows I have chosen arraylist. But for some tests we need array so we are simply
	 * converting arraylist to array also.
	 */
	
	public ArrayList<Double> interpretScaledData(String filePath)
    {
		ArrayList<Double> scaledDataList = null;
		double[] scaled;
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
			String filePathUpload = filePath.replace("/","\\");
			
			File file = new File(filePathUpload);
			Scanner sc = new Scanner(file);
			scaledDataList = new ArrayList<Double>();
			try
			{
				SetErrorMessage("None");
				br = new BufferedReader(new FileReader(file));
				String line;
				while(sc.hasNext())
				{
					line = Integer.toString(sc.nextInt());  
					scaledDataList.add(Double.parseDouble(line));
				}
				// Copy arraylist to array which is helpful for some tests like chi-square.
				//scaledDataArray = scaledDataList.toArray(new Double[scaledDataList.size()]);
				
				scaledDataArray = scaledDataList.stream().mapToDouble(Double::doubleValue).toArray(); 
				
				log.debug("size of converted array of scaled data is:Input base data::>"+scaledDataArray.length);
				
				// finding min, max of scaled data to find out the range of the data
				if(scaledDataList.size() > 0)
				{
					min = scaledDataList.get(0).intValue();
					max = scaledDataList.get(0).intValue();
				}
				for(int j = 0; j < scaledDataArray.length; j++ )
				{
					if(scaledDataArray[j] > max)
						max = scaledDataArray[j];
					else if (scaledDataArray[j] < min)
						min = scaledDataArray[j];
				}
				
				log.debug("Input base data - min array::>"+min); 
				log.debug("Input base data - max array::>"+max); 
				if((max-min+1) > 10000 || min < 0)
				{
					SetErrorMessage("This range should be 0 <= scaled numbers < 10000");
				}
			}
			catch(Exception ex)
			{
				SetErrorMessage("Something went wrong with the scaled data: Please enter data in correct format or with in range 0 <= scaled numbers < 10000");
				log.debug("Exception in interpretScaledData function of Input Base Data::>"+ex.getMessage()); 
			} 
		}
        catch(Exception e)
        {
            SetErrorMessage("Something went wrong with the scaled data: Please enter data in correct format or with in range < 2147483658");
			log.debug("Exception in interpretScaledData function of IndputBaseData::>"+e.getMessage()); 
        }
		
		log.debug("In interpretScaledData of Inputbasedata"); 
		
		return scaledDataList;
    }
}
	
	
	