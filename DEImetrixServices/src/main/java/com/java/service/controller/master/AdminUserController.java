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
import com.java.service.bean.master.AdminLoginDO;
import com.java.service.services.masters.AdminUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@RequestMapping(value = ServiceRestURIConstants.CHECK_ADMIN_LOGIN, method = RequestMethod.POST)
	public @ResponseBody AdminLoginDO checkAdminLogin(@RequestBody AdminLoginDO single_user) 
	{
		AdminLoginDO AdminLoginDO = new AdminLoginDO();
		try
		{
			AdminLoginDO = adminUserService.checkAdminCredentials(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return AdminLoginDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_ADMIN_USER, method = RequestMethod.POST)
	public @ResponseBody AdminLoginDO getEmployee(@RequestBody AdminLoginDO single_user) 
	{
		AdminLoginDO AdminLoginDO = new AdminLoginDO();
		try
		{
			logger.info("Start fetching single user ID="+single_user.getId());
			AdminLoginDO = adminUserService.fetchForUpdate(single_user.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return AdminLoginDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_ADMIN_USER, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody AdminLoginDO create_do) 
	{
		boolean status = false;
		AdminLoginDO adminLoginDO = new AdminLoginDO();
		try
		{
			logger.info("Start create user.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					adminLoginDO = adminUserService.fetchForUpdate(create_do.getId());
					adminLoginDO.setsUserName(create_do.getsUserName());
					adminLoginDO.setsPassword(create_do.getsPassword());
					adminLoginDO.setdUpdatedDate(new Date());
					adminLoginDO.setsUpdatedBy(create_do.getsUpdatedBy());
					status = adminUserService.updateRecord(adminLoginDO);
				} else
				{
					adminLoginDO.setdCreatedDate(new Date());
					status = adminUserService.createRecord(adminLoginDO);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return status;
	}	
}
