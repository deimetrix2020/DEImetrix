package com.java.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.DemographicsDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;

@Controller
@RequestMapping(value ="/demo_controller")
public class DemographicsController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DemographicsController.class);
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST)
	public @ResponseBody boolean createRecord (HttpServletRequest request, HttpSession session)
	{
		DemographicsDO demoObj = new DemographicsDO();
		boolean bStatus = false;
		try
		{
			String sQuestions = request.getParameter("questions_txt");
			String sCombine_ans = request.getParameter("answer_txt");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			String sprim_id = request.getParameter("demo_id");
			if(null != sprim_id && sprim_id != "")
			{
				Integer id = Integer.valueOf(request.getParameter("demo_id"));
				demoObj.setId(id);
				demoObj.setsUpdatedBy(session_admin.getsUserName());
			} else
			{
				demoObj.setsCreatedBy(session_admin.getsUserName());				
			}
			
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			demoObj.setsQuestions(sQuestions);
			demoObj.setsPrefredOptionsAns(sCombine_ans);
			demoObj.setcIsDeleted('N');
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_DEMOGRAPHICS, demoObj, Boolean.class);
			if(bStatus)
				logger.info("Demo Inserted");
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
		DemographicsDO demoObj = new DemographicsDO();
		boolean bStatus = false;
		try
		{
			Integer id = Integer.valueOf(request.getParameter("demo_id"));
			String sKey = request.getParameter("key");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			demoObj.setId(id);
			demoObj.setcIsDeleted('Y');
			demoObj.setsUpdatedBy(session_admin.getsUserName());
			demoObj.setsKey(sKey);
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_DEMOGRAPHICS, demoObj, Boolean.class);
			if(bStatus)
				logger.info("Demo Inserted");
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetching_all_list", method = RequestMethod.POST)
	public @ResponseBody List<DemographicsDO> getAllCompetenMst(HttpServletRequest request) 
	{
		List<DemographicsDO> list_demo = new ArrayList<DemographicsDO>();
		List<DemographicsDO> list_demo_1 = new ArrayList<DemographicsDO>();
		List<DemographicsDO> convert_list = new ArrayList<DemographicsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DEMOGRAPHICS_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo_1, new TypeToken<ArrayList<DemographicsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			convert_list = Arrays.asList(mapper.readValue(element, DemographicsDO[].class));
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
	public @ResponseBody DemographicsDO retriveRecord(HttpServletRequest request, HttpSession session) 
	{
		DemographicsDO do1 = new DemographicsDO();
		DemographicsDO do1_1 = new DemographicsDO();
		try
		{
			Integer id = Integer.valueOf(request.getParameter("id"));
			do1.setId(id);
			RestTemplate restTemplate = new RestTemplate();
			do1_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DEMOGRAPHICS, do1, DemographicsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1_1;
	}
}
