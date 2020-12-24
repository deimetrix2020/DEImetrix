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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.DimensionsDO;
import com.java.app.beans.DomainsDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.SurveyCSVQuestionsDO;
import com.java.app.beans.SurveyQuestionsDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.Util;

import au.com.bytecode.opencsv.CSVReader;

@Controller
@RequestMapping(value ="/question_controller")
public class QuestionsController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(QuestionsController.class);
	
	private static final Logger question_logger = LoggerFactory.getLogger("DEIQ_Question_Upload");
	
	List<SurveyCSVQuestionsDO> failedQuesList = new ArrayList<SurveyCSVQuestionsDO>();
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST)
	public @ResponseBody Integer createRecord (HttpServletRequest request, HttpSession session)
	{
		SurveyQuestionsDO questionObj = new SurveyQuestionsDO();
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				String sDomain_txt = request.getParameter("domain_val");
				String sDimension_txt = request.getParameter("dimension_val");
				String sQuestion_txt = request.getParameter("survey_ques_txt");
				String sResp1_txt = request.getParameter("response1_txt");
				String sResp2_txt = request.getParameter("response2_txt");
				String sIs_leader_val = request.getParameter("check_val");
				String sIs_resp1_val = request.getParameter("resp1_check_val");
				String sIs_resp2_val = request.getParameter("resp2_check_val");
				String sIs_active_val = request.getParameter("active_check_val");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				String sprim_id = request.getParameter("question_id");
				if(null != sprim_id && sprim_id != "")
				{
					Integer id = Integer.valueOf(request.getParameter("question_id"));
					questionObj.setId(id);
					questionObj.setsUpdatedBy(session_admin.getsUserName());
				} else
				{
					questionObj.setsCreatedBy(session_admin.getsUserName());
				}
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				questionObj.setDomainsDO(new DomainsDO(Integer.valueOf(sDomain_txt)));
				questionObj.setDimensionsDO(new DimensionsDO(Integer.valueOf(sDimension_txt)));
				questionObj.setsQuestions(sQuestion_txt);
				questionObj.setsResponse1(sResp1_txt);
				questionObj.setcIsResponse1(sIs_resp1_val.charAt(0));
				questionObj.setsResponse2(sResp2_txt);
				questionObj.setcIsResponse2(sIs_resp2_val.charAt(0));
				questionObj.setcIsLeaderShip(sIs_leader_val.charAt(0));
				questionObj.setcIsActive(sIs_active_val.charAt(0));
				questionObj.setcIsDeleted('N');
				boolean bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_QUESTIONS, questionObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("question Inserted");					
				} else
				{
					return_key = 2;
				}
			} else
			{
				return_key = 3;
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
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
	
	/**
	 * Update a existing record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
	public @ResponseBody Integer deleteRecord(HttpServletRequest request, HttpSession session) 
	{
		SurveyQuestionsDO questionObj = new SurveyQuestionsDO();
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				Integer id = Integer.valueOf(request.getParameter("question_id"));
				String sKey = request.getParameter("key");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				questionObj.setId(id);
				if(sKey.equals("active"))
				{
					questionObj.setcIsActive('Y');
				} else if(sKey.equals("inactive"))
				{
					questionObj.setcIsActive('N');
				} else
				{
					questionObj.setcIsDeleted('Y');				
				}
				questionObj.setsUpdatedBy(session_admin.getsUserName());
				questionObj.setsKey(sKey);
				boolean bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_QUESTIONS, questionObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("question Deleted");					
				} else
				{
					return_key = 2;
				}
			} else
			{
				return_key = 3;
			}
			
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetching_all_list", method = RequestMethod.POST)
	public @ResponseBody List<SurveyQuestionsDO> getAllCompetenMst(HttpServletRequest request) 
	{
		List<SurveyQuestionsDO> list_demo = new ArrayList<SurveyQuestionsDO>();
		List<SurveyQuestionsDO> list_demo_1 = new ArrayList<SurveyQuestionsDO>();
		List<SurveyQuestionsDO> convert_list = new ArrayList<SurveyQuestionsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_QUESTIONS_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo_1, new TypeToken<ArrayList<SurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			convert_list = Arrays.asList(mapper.readValue(element, SurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return convert_list;
	}
	
	/**
	 * Retire record
	 * 
	 * @param request
	 * @param session
	 * @return {@link ProcessMstDO}
	 */
	@RequestMapping(value = "/retriveRecord", method = RequestMethod.POST)
	public @ResponseBody SurveyQuestionsDO retriveRecord(HttpServletRequest request, HttpSession session) 
	{
		SurveyQuestionsDO do1 = new SurveyQuestionsDO();
		try
		{
			Integer id = Integer.valueOf(request.getParameter("id"));
			do1.setId(id);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_QUESTIONS, do1, SurveyQuestionsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}	
		return do1;
	}
	
	/**
	 * Retire record by forgin key
	 * 
	 * @param request
	 * @param session
	 * @return {@link ProcessMstDO}
	 */
	@RequestMapping(value = "/retriveRecord_by_forginkey", method = RequestMethod.POST)
	public @ResponseBody boolean retriveRecord_by_forginkey(HttpServletRequest request, HttpSession session) 
	{
		SurveyQuestionsDO questionObj = new SurveyQuestionsDO();
		boolean bStauts = false;
		try
		{
			String sDomain_txt = request.getParameter("domain_val");
			String sDimension_txt = request.getParameter("dimension_val");
			String sQuestion_txt = request.getParameter("survey_ques_txt");
			String sResp1_txt = request.getParameter("response1_txt");
			String sResp2_txt = request.getParameter("response2_txt");
			// set the values
			questionObj.setDomainsDO(new DomainsDO(Integer.valueOf(sDomain_txt)));
			questionObj.setDimensionsDO(new DimensionsDO(Integer.valueOf(sDimension_txt)));
			questionObj.setsQuestions(sQuestion_txt);
			questionObj.setsResponse1(sResp1_txt);
			questionObj.setsResponse2(sResp2_txt);
			
			RestTemplate restTemplate = new RestTemplate();
			questionObj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_QUESTION_BY_DETAILS, questionObj, SurveyQuestionsDO.class);
			if(questionObj.getId() != null)
			{
				bStauts = true;
			}
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return bStauts;
		
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
        	rootPath = request.getSession().getServletContext().getRealPath(""+File.separator+"")+File.separator+Util.formatfile();
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
	@RequestMapping(value = "/uploadQuestionsCSV", method = RequestMethod.POST)
	public @ResponseBody String uploadQuestionsCSV(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session)
	{
 		StringBuffer csvFileName = new StringBuffer();
 		Calendar cal = Calendar.getInstance();
 		File serverFile = null;
 		String summary = "2";
 		DomainsDO domainsObj = new DomainsDO();
 		DimensionsDO dimensionsObj = new DimensionsDO();
 		SurveyQuestionsDO questionsObj = new SurveyQuestionsDO();
		ServerConfigDO configDO = new ServerConfigDO();
 		if (!file.isEmpty()) 
 		{
            try 
            {
            	configDO = fetchingServerConfig();
    			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
    			{
    				csvFileName = csvFileName.append(cal.get(Calendar.DATE)+""+(cal.get(Calendar.MONTH)+01));
    				csvFileName.append(""+cal.get(Calendar.YEAR)+cal.get(Calendar.HOUR_OF_DAY));
    				csvFileName.append(""+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND));
    				byte[] bytes = file.getBytes();
    				question_logger.info("Upload Data start "+csvFileName+".csv");
    				question_logger.info("Starting harness upload CSV");
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
    				question_logger.info("CSV file stored at: "+ serverFile.getAbsolutePath());
    				question_logger.info("CSV file uploaded by: Admin, File name: "+ uploadedFileName);
    				question_logger.info("Processing CSV...");
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
    				while((record = csvReader.readNext()) != null)
    				{
    					boolean vaildStatus = false;
    					rowCount = rowCount+1;
    					if((!record[0].trim().equals("") && record[0] != null) && (!record[1].trim().equals("") && record[1] != null) && 
    							(!record[2].trim().equals("") && record[2] != null) && (!record[3].trim().equals("") && record[3] != null) &&
    							(!record[4].trim().equals("") && record[4] != null) && (!record[5].trim().equals("") && record[5] != null) &&
    							(!record[6].trim().equals("") && record[6] != null) && (!record[7].trim().equals("") && record[7] != null) &&
    							(!record[8].trim().equals("") && record[8] != null))
    					{
    						SurveyQuestionsDO questionsDO = new SurveyQuestionsDO();
    						questionsDO.setsDomain(record[0].trim());
    						questionsDO.setsDimension(record[1].trim());
    						questionsDO.setsQuestions(record[2].trim());
    						questionsDO.setsResponse1(record[3].trim());
    						questionsDO.setcIsResponse1(record[4].trim().charAt(0));
    						questionsDO.setsResponse2(record[5].trim());
    						questionsDO.setcIsResponse2(record[6].toUpperCase().trim().charAt(0));
    						questionsDO.setcIsLeaderShip(record[7].toUpperCase().trim().charAt(0));
    						questionsDO.setcIsActive(record[8].trim().toUpperCase().charAt(0));
    						questionsDO.setcIsDeleted('N');
    						
    						domainsObj = fetchingDomainDetailsByName(record[0]);
    						dimensionsObj = fetchingDimensionDetailsByName(record[1], domainsObj.getId());
    						if(domainsObj.getId() != null && dimensionsObj.getId() != null)
    						{
    							questionsDO.setDomainsDO(new DomainsDO(domainsObj.getId()));
    							questionsDO.setDimensionsDO(new DimensionsDO(dimensionsObj.getId()));
    							
    							questionsObj = fetchingQuesDetailsByName(questionsDO);
    							
    							if(questionsObj.getId() != null)
    							{
    								questionsDO.setId(questionsObj.getId());
    								questionsDO.setsKey("Update");
    							} else
    							{
    								questionsDO.setsKey("Insert");		        				
    							}
    							RestTemplate restTemplate = new RestTemplate();
    							vaildStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_QUESTIONS, questionsDO, Boolean.class);
    							if(vaildStatus)
    							{
    								successCount = successCount+1;
    								question_logger.info("Validation passed.");		        				
    							} else
    							{
    								SurveyCSVQuestionsDO questionsDO1 = new SurveyCSVQuestionsDO();
    								questionsDO1.setDomain(record[0].trim());
            						questionsDO1.setDimension(record[1].trim());
            						questionsDO1.setQuestions(record[2].trim());
            						questionsDO1.setResponse1(record[3].trim());
            						questionsDO1.setDEIQOriented1(record[4].trim());
            						questionsDO1.setResponse2(record[5].trim());
            						questionsDO1.setDEIQOriented2(record[6].trim());
            						questionsDO1.setQuestionforLeader(record[7].trim());
            						questionsDO1.setActive(record[8].trim());
    								failedQuesList.add(questionsDO1);
    								failureCount=failureCount+1;
    								question_logger.info("Validation failed for record: " +rowCount);
    							}
    						} else
    						{
    							SurveyCSVQuestionsDO questionsDO1 = new SurveyCSVQuestionsDO();
    							questionsDO1.setDomain(record[0].trim());
        						questionsDO1.setDimension(record[1].trim());
        						questionsDO1.setQuestions(record[2].trim());
        						questionsDO1.setResponse1(record[3].trim());
        						questionsDO1.setDEIQOriented1(record[4].trim());
        						questionsDO1.setResponse2(record[5].trim());
        						questionsDO1.setDEIQOriented2(record[6].trim());
        						questionsDO1.setQuestionforLeader(record[7].trim());
        						questionsDO1.setActive(record[8].trim());
    							failedQuesList.add(questionsDO1);
    							failureCount=failureCount+1;
    							question_logger.info("Validation failed for record: " +rowCount);
    						}
    					} else
    					{
    						SurveyCSVQuestionsDO questionsDO = new SurveyCSVQuestionsDO();
    						questionsDO.setDomain(record[0].trim());
    						questionsDO.setDimension(record[1].trim());
    						questionsDO.setQuestions(record[2].trim());
    						questionsDO.setResponse1(record[3].trim());
    						questionsDO.setDEIQOriented1(record[4].trim());
    						questionsDO.setResponse2(record[5].trim());
    						questionsDO.setDEIQOriented2(record[6].trim());
    						questionsDO.setQuestionforLeader(record[7].trim());
    						questionsDO.setActive(record[8].trim());
    						failedQuesList.add(questionsDO);
    						failureCount=failureCount+1;
    						question_logger.info("Validation failed for record: " +rowCount);
    					}
    				}
    				question_logger.info("Summary...");
    				question_logger.info("Total records: "+rowCount+ " Success: " +successCount+ " Failed: "+failureCount);
    				summary = "Total records: "+rowCount+ " Success: " +successCount+ " Failed: "+failureCount+ " The File name: " +csvFileName;
    				question_logger.info("Closing Questions Upload process.");
    				csvReader.close();
    				serverFile.delete();
    				question_logger.info("Questions Update is end "+csvFileName+".csv");
    			} else
    			{
    				summary = "3";
    			}
            	
	 		} catch(Exception excp)
            {
	 			serverFile.delete();
            	question_logger.error(excp.getMessage(), excp);
            }
 		}
 		return summary;
	}
	
	private DomainsDO fetchingDomainDetailsByName(String domain_name)
	{
		DomainsDO do1 = new DomainsDO();
		try
		{
			do1.setsDomainName(domain_name);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DOMAIN_BY_NAME, do1, DomainsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1;
		
	}
	
	private DimensionsDO fetchingDimensionDetailsByName(String dimension_name, Integer domain_id)
	{
		DimensionsDO do1 = new DimensionsDO();
		try
		{
			do1.setsDimensionsName(dimension_name);
			do1.setDomainsDO(new DomainsDO(domain_id));
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DIMENSIONS_BY_NAME, do1, DimensionsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1;
	}
	
	private SurveyQuestionsDO fetchingQuesDetailsByName(SurveyQuestionsDO bean_Obj)
	{
		SurveyQuestionsDO do1 = new SurveyQuestionsDO();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_QUESTION_BY_DETAILS, bean_Obj, SurveyQuestionsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1;
		
	}
	
	@RequestMapping(value = "/faileddata")
    public void downloadFailedUserCSVFile(HttpServletResponse response)
    {
		List<SurveyCSVQuestionsDO> csvMstDO = null;
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
		        String headerValue = String.format("attachment; filename=\"%s\"", "Skipped_Questions"+csvFileName+".csv");
		        response.setHeader(headerKey, headerValue);
		        // uses the Super CSV API to generate CSV data from the model data
		        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		        // Prepare headers
		        
		        String[] header = { "Domain", "Dimension", "Questions", "Response1", "DEIQOriented1", "Response2", "DEIQOriented2", "QuestionforLeader", "Active"};
		        csvWriter.writeHeader(header);
		        // Insert the values in CSV File
		        for (SurveyCSVQuestionsDO prepareCSV : csvMstDO) 
		        {
		            csvWriter.write(prepareCSV, header);
		        }
		        csvWriter.close();
 			}
 		} catch(Exception excp)
 		{
 			question_logger.error(excp.getMessage(), excp);
 		}
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportallquestions", method=RequestMethod.GET)
    public void exportallquestions(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
		List<SurveyCSVQuestionsDO> csvQuestionsDO = new ArrayList<SurveyCSVQuestionsDO>();
 		List<SurveyQuestionsDO> questionsDO = new ArrayList<SurveyQuestionsDO>();
 		Calendar cal = Calendar.getInstance();
 		StringBuffer csvFileName = null;
 		RestTemplate restTemplate = new RestTemplate();
 		ObjectMapper mapper = new ObjectMapper();
 		try
 		{
 			csvFileName = new StringBuffer(cal.get(Calendar.DATE)+""+(cal.get(Calendar.MONTH)+01));
 			csvFileName.append(cal.get(Calendar.YEAR)+""+cal.get(Calendar.HOUR));
 			csvFileName.append(cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND));
 			List<SurveyQuestionsDO> list_demo_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_QUESTIONS_LIST, questionsDO, List.class);
			String element = new Gson().toJson(list_demo_1, new TypeToken<ArrayList<SurveyQuestionsDO>>() {}.getType());
			questionsDO = Arrays.asList(mapper.readValue(element, SurveyQuestionsDO[].class));
 			for(SurveyQuestionsDO ques_csv : questionsDO)
 			{
 				SurveyCSVQuestionsDO failedMstDO = new SurveyCSVQuestionsDO();
 				failedMstDO.setDomain(ques_csv.getDomainsDO().getsDomainName());
 				failedMstDO.setDimension(ques_csv.getDimensionsDO().getsDimensionsName());
 				failedMstDO.setQuestions(ques_csv.getsQuestions());
        		failedMstDO.setResponse1(ques_csv.getsResponse1());
        		failedMstDO.setResponse2(ques_csv.getsResponse2());
        		failedMstDO.setDEIQOriented1(String.valueOf(ques_csv.getcIsResponse1()));
        		failedMstDO.setDEIQOriented2(String.valueOf(ques_csv.getcIsResponse2()));
        		failedMstDO.setQuestionforLeader(String.valueOf(ques_csv.getcIsLeaderShip()));
        		failedMstDO.setActive(String.valueOf(ques_csv.getcIsActive()));
        		failedMstDO.setDeleted(String.valueOf(ques_csv.getcIsDeleted()));
        		csvQuestionsDO.add(failedMstDO);
 			}
	        response.setContentType("text/csv");
	        // creates mock data
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", "Export_Question_Dump-"+csvFileName+".csv");
	        response.setHeader(headerKey, headerValue);
	        // uses the Super CSV API to generate CSV data from the model data
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        // Prepare headers
	        String[] header = { "Domain", "Dimension", "Questions", "Response1", "DEIQOriented1", "Response2", "DEIQOriented2", "QuestionforLeader", "Active"};
	        csvWriter.writeHeader(header);
	        // Insert the values in CSV File
	        for (SurveyCSVQuestionsDO prepareCSV : csvQuestionsDO) 
	        {
	            csvWriter.write(prepareCSV, header);
	        }
	        csvWriter.close();
 		} catch(Exception excp)
 		{
 			logger.error(excp.getMessage(), excp);
 		}
    }
}
