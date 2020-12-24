package com.java.service.controller.master;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.Util.ServiceRestURIConstants;
import com.java.service.bean.master.ServerConfigDO;
import com.java.service.services.masters.ServerConfigService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ServerConfigController {
	
	@Autowired
	private ServerConfigService serverConfigService;
	
	private static final Logger logger = LoggerFactory.getLogger(ServerConfigController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_SERVER_CONFIG, method = RequestMethod.POST)
	public @ResponseBody ServerConfigDO getDemographics(@RequestBody ServerConfigDO single_serverconfig) 
	{
		ServerConfigDO serverConfigDO = new ServerConfigDO();
		try
		{
			logger.info("Start fetching single Server config ID="+single_serverconfig.getId());
			serverConfigDO = serverConfigService.fetchForUpdate(single_serverconfig.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single Server config : " + excp.getMessage());
		}
		return serverConfigDO;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_SERVER_CONFIG, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody ServerConfigDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create Server config.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					ServerConfigDO serverConfigDO = serverConfigService.fetchForUpdate(create_do.getId());
					serverConfigDO.setsFromMailId(create_do.getsFromMailId());
					serverConfigDO.setsFromMailPassword(create_do.getsFromMailPassword());
					serverConfigDO.setsSMTPHostName(create_do.getsSMTPHostName());
					serverConfigDO.setsSMTPHostNumber(create_do.getsSMTPHostNumber());
					serverConfigDO.setiTimeout(create_do.getiTimeout());
					serverConfigDO.setdUpdatedDate(new Date());
					serverConfigDO.setsUpdatedBy(create_do.getsUpdatedBy());
					status = serverConfigService.updateRecord(serverConfigDO);
				} else
				{
					create_do.setdCreatedDate(new Date());
					status = serverConfigService.createRecord(create_do);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update Server config : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_SERVER_CONFIG_MAINTAIN, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateQuestionMaintain(@RequestBody ServerConfigDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create Server config.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					ServerConfigDO serverConfigDO = serverConfigService.fetchForUpdate(create_do.getId());
					serverConfigDO.setdMaintainStartDate(create_do.getdMaintainStartDate());
					serverConfigDO.setdMaintainEndDate(create_do.getdMaintainEndDate());
					serverConfigDO.setiQuestions_Timeout(create_do.getiQuestions_Timeout());
					serverConfigDO.setiTotalSurveyTime(create_do.getiTotalSurveyTime());
					serverConfigDO.setdUpdatedDate(new Date());
					serverConfigDO.setsUpdatedBy(create_do.getsUpdatedBy());
					status = serverConfigService.updateRecord(serverConfigDO);
				} else
				{
					create_do.setdCreatedDate(new Date());
					status = serverConfigService.createRecord(create_do);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update Server config : " + excp.getMessage());
		}
		return status;
	}
}
