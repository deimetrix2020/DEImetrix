package com.java.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.InviteUsersCSVDO;
import com.java.app.beans.InviteUsersDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.StatusDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.SendingMail;
import com.java.app.utils.Util;

import au.com.bytecode.opencsv.CSVReader;

@Controller
@RequestMapping(value ="/invite_controller")
public class InviteUserController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(InviteUserController.class);
	
	@Autowired
	private SendingMail mailController;
	
	List<InviteUsersCSVDO> failedQuesList = new ArrayList<InviteUsersCSVDO>();
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST)
	public @ResponseBody boolean createRecord (HttpServletRequest request, HttpSession session)
	{
		List<InviteUsersDO> inviteUsersObj = new ArrayList<InviteUsersDO>();
		List<InviteUsersDO> mail_status_update = new ArrayList<InviteUsersDO>();
		boolean bStatus = false;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			String sfirstname = request.getParameter("firstname");
			String slastname = request.getParameter("lastname");
			String sEmailid = request.getParameter("emailid");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			InviteUsersDO invite_obj = new InviteUsersDO();
			
			invite_obj.setsFirstName(sfirstname);
			invite_obj.setsLastName(slastname);
			invite_obj.setsEmailId(sEmailid);
			invite_obj.setReg_status(new StatusDO(4));
			invite_obj.setSurvey_status(new StatusDO(5));
			invite_obj.setcIsEmailStatus('N');
			invite_obj.setcIsDeleted('N');
			invite_obj.setsCreatedBy(session_admin.getsUserName());	
			
			inviteUsersObj.add(invite_obj);

			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, inviteUsersObj, Boolean.class);
			if(bStatus)
			{
				logger.info("Invite User Inserted");
				configDO = fetchingServerConfig();
				
				//URL creation
				StringBuffer url = request.getRequestURL();
				String uri = request.getRequestURI();
				String ctx = request.getContextPath();
				String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/login?invite=register";
				String verification_url = base + "";
				
				for(InviteUsersDO usersDO : inviteUsersObj)
				{
					bStatus = mailController.invite_sendingMail(configDO, usersDO, verification_url);
					InviteUsersDO invite_obj_single = new InviteUsersDO();
					if(bStatus)
					{
						invite_obj_single = retriveRecord(usersDO.getsEmailId());
						
						invite_obj_single.setcIsEmailStatus('Y');
						invite_obj_single.setReg_status(new StatusDO(4));
						invite_obj_single.setSurvey_status(new StatusDO(5));
						invite_obj_single.setsUpdatedBy(session_admin.getsUserName());
						
						mail_status_update.add(invite_obj_single);
						logger.info("Mail sent successed.");
					} else
					{
						logger.info("Mail send failed.");
					}					
				}
				bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, mail_status_update, Boolean.class);
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
	
	/**
	 * Update a existing record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
	public @ResponseBody boolean deleteRecord(HttpServletRequest request, HttpSession session) 
	{
		InviteUsersDO inviteObj = new InviteUsersDO();
		boolean bStatus = false;
		try
		{
			Integer id = Integer.valueOf(request.getParameter("invite_id"));
			String sKey = request.getParameter("key");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			inviteObj.setId(id);
			inviteObj.setcIsDeleted('Y');
			inviteObj.setsUpdatedBy(session_admin.getsUserName());
			inviteObj.setsKey(sKey);
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_INVITE_USER, inviteObj, Boolean.class);
			if(bStatus)
				logger.info("Invite Deleted");
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
	
	/**
	 * Download File format for questions
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadformat", method = RequestMethod.GET)
	public void downloadCSVfileformat(HttpServletRequest request, HttpServletResponse response)  throws IOException
	{
		int BUFFER_SIZE = 4096;
	    File dir = null;
	    String filename = "";
	    String rootPath = "";
        try 
        {
        	// get absolute path of the application
        	rootPath = request.getSession().getServletContext().getRealPath(""+File.separator+"")+File.separator+Util.formatuserfile();
        	dir = new File(rootPath);
        	SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
        	filename= dir.getName();
            if(filename != null)
            {
	            // construct the complete absolute path of the file
	            FileInputStream inputStream = new FileInputStream(dir);
	             
	            response.setContentType("text/csv");
	            response.setContentLength((int) dir.length());
	     
	            String headerKey = "Content-Disposition";
	            String headerValue = String.format("attachment; filename=\"%s\"", dir.getName().replace(".csv", "")+"_"+dateFormat.format(new Date())+".csv");
	            response.setHeader(headerKey, headerValue);
	            
	            // get output stream of the response
	            OutputStream outStream = response.getOutputStream();
	            byte[] buffer = new byte[BUFFER_SIZE];
	            int bytesRead = -1;
	            // write bytes read from the input stream into the output stream
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outStream.write(buffer, 0, bytesRead);
	            }
	            inputStream.close();
	            outStream.close();
            }
 		} catch(Exception excp)
        {
        	logger.error(excp.getMessage(), excp);
        }
	}
	
	/**
	 * Parse the each line uploaded CSV file and Insert the records. 
	 *
	 * @param file
	 * @param request
	 * @return string
	 */
	@RequestMapping(value = "/uploadInviteUsersCSV", method = RequestMethod.POST)
	public @ResponseBody String uploadInviteUsersCSV(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session)
	{
 		StringBuffer csvFileName = new StringBuffer();
 		Calendar cal = Calendar.getInstance();
 		File serverFile = null;
 		String summary = "";
 		List<InviteUsersDO> inviteUsersObj = new ArrayList<InviteUsersDO>();
		ServerConfigDO configDO = new ServerConfigDO();
 		if (!file.isEmpty()) 
 		{
            try 
            {
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				RestTemplate restTemplate = new RestTemplate();
				csvFileName = csvFileName.append(cal.get(Calendar.DATE)+""+(cal.get(Calendar.MONTH)+01));
				csvFileName.append(""+cal.get(Calendar.YEAR)+cal.get(Calendar.HOUR_OF_DAY));
				csvFileName.append(""+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND));
				byte[] bytes = file.getBytes();
				logger.info("Upload Data start "+csvFileName+".csv");
				logger.info("Starting harness upload CSV");
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home").replace("tmp", "");
				File dir = new File(rootPath + File.separator + "uploads/masters");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				//Preparing File Name
				String uploadedFileName = csvFileName+".csv";
				serverFile = new File(dir.getAbsolutePath()+ File.separator + uploadedFileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				logger.info("CSV file stored at: "+ serverFile.getAbsolutePath());
				logger.info("CSV file uploaded by: Admin, File name: "+ uploadedFileName);
				logger.info("Processing CSV...");
				//create CSVReader object
				CSVReader csvReader = new CSVReader(new FileReader(serverFile.getAbsolutePath()), ',');
				//read line by line
				String[] record = null;
				//skip header row
				csvReader.readNext();
				int successCount = 0;
				int failureCount = 0;
				int rowCount = 0;
				failedQuesList.clear();
				inviteUsersObj.clear();
				boolean vaildStatus = false;
				while((record = csvReader.readNext()) != null)
				{
					rowCount = rowCount+1;
					if((!record[0].trim().equals("") && record[0] != null) && (!record[1].trim().equals("") && record[1] != null) && 
							(!record[2].trim().equals("") && record[2] != null))
					{
						if(isValid(record[2].trim()))
						{
							InviteUsersDO invite_obj = new InviteUsersDO();
							
							invite_obj.setsFirstName(record[0].trim());
							invite_obj.setsLastName(record[1].trim());
							invite_obj.setsEmailId(record[2].trim());
							invite_obj.setReg_status(new StatusDO(4));
							invite_obj.setSurvey_status(new StatusDO(5));
							invite_obj.setcIsDeleted('N');
							invite_obj.setcIsEmailStatus('N');
							invite_obj.setsCreatedBy(session_admin.getsUserName());	
							
							inviteUsersObj.add(invite_obj);
							successCount = successCount+1;
						} else
						{
							InviteUsersCSVDO questionsDO = new InviteUsersCSVDO();
							questionsDO.setFirstName(record[0].trim());
							questionsDO.setLastName(record[1].trim());
							questionsDO.setEmailId(record[2].trim());
							failedQuesList.add(questionsDO);
							failureCount=failureCount+1;
							logger.info("Validation failed for record: " +rowCount);
						}
					} else
					{
						InviteUsersCSVDO questionsDO = new InviteUsersCSVDO();
						questionsDO.setFirstName(record[0].trim());
						questionsDO.setLastName(record[1].trim());
						questionsDO.setEmailId(record[2].trim());
						failedQuesList.add(questionsDO);
						failureCount=failureCount+1;
						logger.info("Validation failed for record: " +rowCount);
					}
				}
				vaildStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, inviteUsersObj, Boolean.class);
				
				if(vaildStatus)
				{
					logger.info("Invite User Inserted");
					configDO = fetchingServerConfig();
					
					sendingMail(configDO, request, inviteUsersObj, session_admin);
				}
				logger.info("Summary...");
				logger.info("Total records: "+rowCount+ " Success: " +successCount+ " Failed: "+failureCount);
				summary = "Total records: "+rowCount+ " Success: " +successCount+ " Failed: "+failureCount+ " The File name: " +csvFileName;
				logger.info("Closing Questions Upload process.");
				csvReader.close();
				serverFile.delete();
				logger.info("Questions Update is end "+csvFileName+".csv");
	 		} catch(Exception excp)
            {
	 			serverFile.delete();
            	logger.error(excp.getMessage(), excp);
            }
 		}
 		return summary;
	}
	
	private boolean sendingMail(ServerConfigDO configDO, HttpServletRequest request, List<InviteUsersDO> inviteUsersObj, AdminLoginDO session_admin)
	{
		List<InviteUsersDO> mail_status_update = new ArrayList<InviteUsersDO>();
		boolean bStatus = false;
		RestTemplate restTemplate = new RestTemplate();
		try
		{
			logger.info("Invite User Inserted");
			configDO = fetchingServerConfig();
			
			//URL creation
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/login?invite=register";
			String verification_url = base + "";
			
			for(InviteUsersDO usersDO : inviteUsersObj)
			{
				bStatus = mailController.invite_sendingMail(configDO, usersDO, verification_url);
				InviteUsersDO invite_obj = new InviteUsersDO();
				if(bStatus)
				{
					invite_obj = retriveRecord(usersDO.getsEmailId());
					
					invite_obj.setcIsEmailStatus('Y');
					invite_obj.setReg_status(new StatusDO(4));
					invite_obj.setSurvey_status(new StatusDO(5));
					invite_obj.setsUpdatedBy(session_admin.getsUserName());	
					
					mail_status_update.add(invite_obj);
					logger.info("Mail sent successed.");
				} else
				{
					logger.info("Mail send failed.");
				}					
			}
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, mail_status_update, Boolean.class);
		}catch (Exception excp) {
			
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
	
	@RequestMapping(value = "/faileddata")
    public void downloadFailedUserCSVFile(HttpServletResponse response)
    {
		List<InviteUsersCSVDO> csvMstDO = null;
 		Calendar cal = Calendar.getInstance();
 		StringBuffer csvFileName = null;
 		try
 		{
 			if(!failedQuesList.isEmpty())
 			{
	 			csvFileName = new StringBuffer(cal.get(Calendar.DATE)+""+(cal.get(Calendar.MONTH)+01));
	 			csvFileName.append(cal.get(Calendar.YEAR)+""+cal.get(Calendar.HOUR));
	 			csvFileName.append(cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND));
	 			csvMstDO = failedQuesList;
		        response.setContentType("text/csv");
		        // creates mock data
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", "Skipped_InviteUser_"+csvFileName+".csv");
		        response.setHeader(headerKey, headerValue);
		        // uses the Super CSV API to generate CSV data from the model data
		        CsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		        // Prepare headers
		        
		        String[] header = { "FirstName", "LastName", "EmailId"};
		        csvWriter.writeHeader(header);
		        // Insert the values in CSV File
		        for (InviteUsersCSVDO prepareCSV : csvMstDO) 
		        {
		            csvWriter.write(prepareCSV, header);
		        }
		        csvWriter.close();
 			}
 		} catch(Exception excp)
 		{
 			logger.error(excp.getMessage(), excp);
 		}
    }
	
	/**
	 * Fetching server config
	 * 
	 * @return
	 */
	private ServerConfigDO fetchingServerConfig()
	{
		ServerConfigDO serverConfigDO = new ServerConfigDO();
		try
		{
			serverConfigDO.setId(1);
			RestTemplate restTemplate = new RestTemplate();
			serverConfigDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_SERVER_CONFIG, serverConfigDO, ServerConfigDO.class);
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return serverConfigDO;
	}
	
	public InviteUsersDO retriveRecord(String email_id) 
	{
		InviteUsersDO do1 = new InviteUsersDO();
		try
		{
			do1.setsEmailId(email_id);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_INVITE_USER, do1, InviteUsersDO.class);
		} catch (Exception e) 
		{
			logger.info("error :", e.getMessage());
		}
		return do1;
	}
	
	public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
}
