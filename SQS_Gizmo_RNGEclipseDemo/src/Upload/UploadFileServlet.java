/**
 * Purpose : This servlet is implemented to upload input file with scaled data
 * @author : Sheetal Sulay
 * Date : 25/11/2016
*/
package upload;
 
import java.io.*;
import java.util.*;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UploadFileServlet extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(UploadFileServlet.class.getName());
	
	// location to store file uploaded
    private static String UPLOAD_DIRECTORY = "";
	
	// upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 90;  // 90MB
    private static final long MAX_FILE_SIZE      = 1024 * 1024 * 90; // 90MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 90; // 90MB
	
	String filePath = "";
	
    /**
     * When input file with scaled data is uploaded this function saves that file to server 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        // validates if the request actually has uploaded file
        if (!ServletFileUpload.isMultipartContent(request)) 
		{
            // if not then show error message
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
		
		Properties prop=new Properties();
		
		// Load properties file which have paths for uploading input files and log4j config file
		prop.load(getServletContext().getResourceAsStream("/my.properties"));
		
		String log4jConfigFile =  (String) prop.get("logfilepath");
		PropertyConfigurator.configure(log4jConfigFile);
		
		
		// constructs the directory path to store uploaded input file
       		
		UPLOAD_DIRECTORY = (String) prop.get("inputpath");
		
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        
		// sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        
		// sets temporary location to store files
		factory.setRepository(new File(UPLOAD_DIRECTORY));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
		String testName = "";
		int testCounter = 0;
		
		// creates the directory if it does not exist
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) 
		{
            uploadDir.mkdir();
        }
 
        try 
		{
            List<FileItem> formItems = upload.parseRequest(request);
			Iterator i = formItems.iterator();
			String testNames[] = new String[20];
						
            if (formItems != null && formItems.size() > 0) 
			{
                // iterates over form's fields
                
				 while ( i.hasNext () )
				 {
				           FileItem item = (FileItem)i.next();
                
					// processes only fields that are not form fields
                    if (!item.isFormField()) 
					{
                        String fileName = new File(item.getName()).getName();
                        filePath = UPLOAD_DIRECTORY + fileName;
                        File storeFile = new File(filePath);
						request.setAttribute("filenameupload",filePath);
                        // saves the file on server
                        item.write(storeFile);
					}
					else if(item.isFormField())
					{
						String inputName = item.getFieldName(); 
						if(inputName.equalsIgnoreCase("gName"))
						{ 
							String gameName = item.getString(); 
							request.setAttribute("gName",gameName);
							log.debug("Upload file servlet here: Game name::>"+gameName); 
						}
						if(inputName.equalsIgnoreCase("VName"))
						{ 
							String versionName = item.getString(); 
							request.setAttribute("VName",versionName);
							log.debug("Upload file servlet: versionName name::>"+versionName); 
						}
						if(inputName.equalsIgnoreCase("alpha"))
						{ 
							String alpha = item.getString(); 
							request.setAttribute("alpha",alpha);
							log.debug("Upload file servlet: alpha name::>"+alpha);
						}
						if(inputName.equalsIgnoreCase("numbersPerDraw"))
						{ 
							String numbersPerDraw = item.getString(); 
							request.setAttribute("numbersPerDraw",numbersPerDraw);
							log.debug("Upload file servlet: numbersPerDraw name::>"+numbersPerDraw);
						}
						if(inputName.equalsIgnoreCase("testName"))
						{ 
							testName = item.getString(); 
							testNames[testCounter] = testName;
							testCounter++;
							
						}
						if(inputName.equalsIgnoreCase("numberType"))
						{ 
							String numberType = item.getString(); 
							request.setAttribute("numberType",numberType);
							log.debug("numberType::>"+numberType);
							
						}
						if(inputName.equalsIgnoreCase("selectAl"))
						{ 
							String selectAll = item.getString(); 
							request.setAttribute("selectAl",selectAll);
							log.debug("selectAll::>"+selectAll);
							
						}
					}
					else{
						request.setAttribute("errorMessage",
                            "There is some error during uploading");
					}
						
                }
            }
			request.setAttribute("testName",testNames);
			log.debug("Upload file servlet: testName name::>"+testNames);
        } 
		catch (Exception ex) 
		{
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirecting to result page
        getServletContext().getRequestDispatcher("/jsp/result.jsp").forward(
                request, response);
    }
}