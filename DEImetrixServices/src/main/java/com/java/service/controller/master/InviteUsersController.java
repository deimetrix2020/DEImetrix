package com.java.service.controller.master;

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
import com.java.service.bean.master.InviteUsersDO;
import com.java.service.services.masters.InviteUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class InviteUsersController {
	
	@Autowired
	private InviteUserService inviteUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(InviteUsersController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_INVITE_USER, method = RequestMethod.POST)
	public @ResponseBody InviteUsersDO getDemographics(@RequestBody InviteUsersDO single_Obj) 
	{
		InviteUsersDO InviteUsersDO = new InviteUsersDO();
		try
		{
			InviteUsersDO = inviteUserService.fetchForUpdateByEmail(single_Obj.getsEmailId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single demo : " + excp.getMessage());
		}
		return InviteUsersDO;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_INVITE_USERS_LIST, method = RequestMethod.POST)
	public @ResponseBody List<InviteUsersDO> getAllInviteList() 
	{
		logger.info("Start feching all demo.");
		List<InviteUsersDO> obj_list = new ArrayList<InviteUsersDO>();
		try
		{
			obj_list = inviteUserService.fetchList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching all demo list : " + excp.getMessage());
		}
		return obj_list;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_INVITE_USERS, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody List<InviteUsersDO> create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create Invite users.");
			String element = new Gson().toJson(create_do, new TypeToken<ArrayList<InviteUsersDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			create_do = Arrays.asList(mapper.readValue(element, InviteUsersDO[].class));
			for(InviteUsersDO usersDO : create_do)
			{
				if(!usersDO.getsEmailId().trim().isEmpty())
				{
					if(usersDO.getId() != null)
					{
						InviteUsersDO inviteUsersDO = inviteUserService.fetchForUpdate(usersDO.getId());
						inviteUsersDO.setcIsEmailStatus(usersDO.getcIsEmailStatus());
						inviteUsersDO.setReg_status(usersDO.getReg_status());
						inviteUsersDO.setSurvey_status(usersDO.getSurvey_status());
						inviteUsersDO.setsUpdatedBy(usersDO.getsUpdatedBy());
						inviteUsersDO.setdUpdatedDate(new Date());
						status = inviteUserService.updateRecord(inviteUsersDO);
					} else
					{
						usersDO.setdCreatedDate(new Date());
						status = inviteUserService.createRecord(usersDO);
					}					
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update invite users : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.DELETE_INVITE_USER, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody InviteUsersDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start delete domain.");
			if(create_do.getsKey().equals("soft"))
			{
				InviteUsersDO domainsDO = inviteUserService.fetchForUpdate(create_do.getId());
				domainsDO.setcIsDeleted('Y');
				domainsDO.setdUpdatedDate(new Date());
				domainsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = inviteUserService.updateRecord(domainsDO);
			} else
			{
				status = inviteUserService.deleteRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete domain : " + excp.getMessage());
		}
		return status;
	}
}
