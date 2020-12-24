package com.java.service.controller.trans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.Util.ServiceRestURIConstants;
import com.java.service.bean.TransSurvryInitDO;
import com.java.service.services.transaction.TransDemoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TransDemoController {
	
	@Autowired
	private TransDemoService transDemoService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransDemoController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_TRANS_SURVEY_INIT, method = RequestMethod.POST)
	public @ResponseBody Integer createSurveyUnit(@RequestBody TransSurvryInitDO create_do) 
	{
		Integer iPrim_id = null;
		try
		{
			logger.info("Start create quest.");
			if(create_do != null)
			{
				create_do.setdCreatedDate(new Date());
				iPrim_id = transDemoService.createSurveyInitRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return iPrim_id;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.UPDATE_TRANS_SURVEY_INIT, method = RequestMethod.POST)
	public @ResponseBody boolean updateSurveyUnit(@RequestBody TransSurvryInitDO create_do) 
	{
		boolean bStatus = false;
		try
		{
			logger.info("Start create quest.");
			if(create_do != null)
			{
				create_do.setdCreatedDate(new Date());
				bStatus = transDemoService.updateSurveyInitRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return bStatus;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_SERVER_INIT_LIST, method = RequestMethod.POST)
	public @ResponseBody List<TransSurvryInitDO> fetchingAllSurveyQues(@RequestBody TransSurvryInitDO single_user) 
	{
		List<TransSurvryInitDO> initDO = new ArrayList<TransSurvryInitDO>();
		try
		{
			initDO = transDemoService.fetchTransSurveyAllList(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
}
