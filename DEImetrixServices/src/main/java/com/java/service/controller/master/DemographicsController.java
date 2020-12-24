package com.java.service.controller.master;

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
import com.java.service.bean.master.DemographicsDO;
import com.java.service.services.masters.DemographicsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DemographicsController {
	
	@Autowired
	private DemographicsService demographicsService;
	
	private static final Logger logger = LoggerFactory.getLogger(DemographicsController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_DEMOGRAPHICS, method = RequestMethod.POST)
	public @ResponseBody DemographicsDO getDemographics(@RequestBody DemographicsDO single_demographics) 
	{
		DemographicsDO demographicsDO = new DemographicsDO();
		try
		{
			logger.info("Start fetching single Demographics ID="+single_demographics.getId());
			demographicsDO = demographicsService.fetchForUpdate(single_demographics.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single demo : " + excp.getMessage());
		}
		return demographicsDO;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_DEMOGRAPHICS_LIST, method = RequestMethod.POST)
	public @ResponseBody List<DemographicsDO> getAllEmployees() 
	{
		logger.info("Start feching all demo.");
		List<DemographicsDO> obj_list = new ArrayList<DemographicsDO>();
		try
		{
			obj_list = demographicsService.fetchList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching all demo list : " + excp.getMessage());
		}
		return obj_list;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_DEMOGRAPHICS, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody DemographicsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create demo.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					DemographicsDO demographicsDO = demographicsService.fetchForUpdate(create_do.getId());
					demographicsDO.setsQuestions(create_do.getsQuestions());
					demographicsDO.setsPrefredOptionsAns(create_do.getsPrefredOptionsAns());
					demographicsDO.setsUpdatedBy(create_do.getsUpdatedBy());
					demographicsDO.setdUpdatedDate(new Date());
					status = demographicsService.updateRecord(demographicsDO);
				} else
				{
					create_do.setdCreatedDate(new Date());
					status = demographicsService.createRecord(create_do);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update demo : " + excp.getMessage());
		}
		return status;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.DELETE_DEMOGRAPHICS, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody DemographicsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start delete demo.");
			if(create_do.getsKey().equals("soft"))
			{
				DemographicsDO demographicsDO = demographicsService.fetchForUpdate(create_do.getId());
				demographicsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				demographicsDO.setdUpdatedDate(new Date());
				demographicsDO.setcIsDeleted('Y');
				status = demographicsService.updateRecord(demographicsDO);
			} else
			{
				status = demographicsService.deleteRecord(create_do);				
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete demo : " + excp.getMessage());
		}
		return status;
	}
	
}
