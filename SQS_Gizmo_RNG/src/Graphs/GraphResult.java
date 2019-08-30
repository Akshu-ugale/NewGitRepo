/**
 * Purpose : This java class is implemented to draw graph for Runs Up and Runs Down test
 * @author : Sheetal Sulay
 * Date : 25/01/2016
*/

package graphs;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
//import de.laures.cewolf.links.CategoryItemLinkGenerator;
//import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import scaleddatamathstests.InputBaseData;

public class GraphResult implements DatasetProducer, Serializable 
{
	private static final Log log = LogFactory.getLog(GraphResult.class);
	
	public Object produceDataset(Map params) 
	{
		final String[] categories = { "Pass", "Fail", "Not Run" };
		
		DefaultPieDataset ds = new DefaultPieDataset();
		String testResult[] = InputBaseData.GetTestResult();
		int pass = 0, fail =0, cnr = 0;
		
		log.debug("In Graph Result - Result from tests received::>"+testResult.length); 
		
		for (int i = 0; i < testResult.length; i++) 
		{
			if(testResult[i].equals("Pass"))
			pass++;
			if(testResult[i].equals("Fail"))
			fail++;
			if(testResult[i].equals("Not Run"))
			cnr++;
		}
		
		log.debug("Pass test::>"+pass); 
		log.debug("Fail test::>"+fail);
		log.debug("Not run test::>"+cnr);
		
		ds.setValue(categories[0], pass);
		ds.setValue(categories[1], fail);
		ds.setValue(categories[2], cnr);
		
		return ds;
    }
        
    /**
     * This producer's data is invalidated after 5 seconds. By this method the
     * producer can influence Cewolf's caching behaviour the way it wants to.
     */
	public boolean hasExpired(Map params, Date since) 
	{		
    	return (System.currentTimeMillis() - since.getTime())  > 5000;
	}

	/**
	 * Returns a unique ID for this DatasetProducer
	 */
	public String getProducerId()
	{
		return "GraphResult DatasetProducer";
	}

  /**
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
	
}