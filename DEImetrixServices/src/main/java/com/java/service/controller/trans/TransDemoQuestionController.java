package com.java.service.controller.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.service.Util.ServiceRestURIConstants;
import com.java.service.bean.TransUserDemoQuestionsDO;
import com.java.service.services.transaction.TransDemoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TransDemoQuestionController {
	
	@Autowired
	private TransDemoService transDemoService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransDemoQuestionController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_TRANS_DEMO_QUESTION_LIST, method = RequestMethod.POST)
	public @ResponseBody List<TransUserDemoQuestionsDO> getAllTransDemoQuestion(@RequestBody TransUserDemoQuestionsDO create_do) 
	{
		logger.info("Start feching all quest.");
		List<TransUserDemoQuestionsDO> obj_list = new ArrayList<TransUserDemoQuestionsDO>();
		try
		{
			obj_list = transDemoService.fetchDemoQuestionsList(create_do.getUser_id().getId(), create_do.getInit_id().getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching all quest list : " + excp.getMessage());
		}
		return obj_list;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_TRANS_DEMO_QUESTION, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateDemoQuestion(@RequestBody List<TransUserDemoQuestionsDO> create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create quest.");
			String element = new Gson().toJson(create_do, new TypeToken<ArrayList<TransUserDemoQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			create_do = Arrays.asList(mapper.readValue(element, TransUserDemoQuestionsDO[].class));
			for(TransUserDemoQuestionsDO demoQuestionsDO : create_do)
			{
				demoQuestionsDO.setdCreatedDate(new Date());
				status = transDemoService.createDemoQuestionRecord(demoQuestionsDO);
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.DELETE_TRANS_QUES_DEMO, method = RequestMethod.POST)
	public @ResponseBody boolean deleteDemoQuestion(@RequestBody TransUserDemoQuestionsDO create_do) 
	{
		boolean status = false;
		List<TransUserDemoQuestionsDO> obj_list = new ArrayList<TransUserDemoQuestionsDO>();
		try
		{
			logger.info("Start delete quest.");
			obj_list = transDemoService.fetchDemoQuestionsList(create_do.getUser_id().getId(), create_do.getInit_id().getId());
			for(TransUserDemoQuestionsDO demoQuestionsDO : obj_list)
			{
				status = transDemoService.deleteDemoQuestionRecord(demoQuestionsDO);
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete quest : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_TRANS_QUES_ANS_DEMO_REPORT, method = RequestMethod.POST)
	public @ResponseBody String fetchTransDemoListReport(@RequestBody TransUserDemoQuestionsDO surveyCalc_obj) 
	{
		String initDO = "";
		try
		{
			initDO = transDemoService.fetchTransDemoDetailsReport(surveyCalc_obj);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
}
