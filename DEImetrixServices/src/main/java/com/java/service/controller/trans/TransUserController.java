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
import com.java.service.bean.TransUserDetailsDO;
import com.java.service.services.transaction.TransUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TransUserController {
	
	@Autowired
	private TransUserService transUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransUserController.class);

	@RequestMapping(value = ServiceRestURIConstants.FETCH_TRANS_USERS_LIST, method = RequestMethod.POST)
	public @ResponseBody List<TransUserDetailsDO> allusersList() 
	{
		List<TransUserDetailsDO> list = new ArrayList<TransUserDetailsDO>();
		try
		{
			list = transUserService.fetchAllUsersList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.CHECK_TRANS_USER_LOGIN, method = RequestMethod.POST)
	public @ResponseBody TransUserDetailsDO checkAdminLogin(@RequestBody TransUserDetailsDO single_user) 
	{
		TransUserDetailsDO TransUserDetailsDO = new TransUserDetailsDO();
		try
		{
			TransUserDetailsDO = transUserService.checkAdminCredentials(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return TransUserDetailsDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_TRANS_SINGLE_USER, method = RequestMethod.POST)
	public @ResponseBody TransUserDetailsDO getEmployee(@RequestBody TransUserDetailsDO single_user) 
	{
		TransUserDetailsDO TransUserDetailsDO = new TransUserDetailsDO();
		try
		{
			logger.info("Start fetching single user ID="+single_user.getId());
			TransUserDetailsDO = transUserService.fetchForUpdate(single_user.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return TransUserDetailsDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.REGISTER_TRANS_USER, method = RequestMethod.POST)
	public @ResponseBody Integer createUpdateUser(@RequestBody TransUserDetailsDO create_do) 
	{
		Integer status = null;
		try
		{
			logger.info("Start create user.");
			create_do.setdCreatedDate(new Date());
			status = transUserService.createdRecord(create_do);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return status;
	}
	
	/**
	 * Register updation user
	 * 
	 * @param create_do
	 * @return
	 */
	@RequestMapping(value = ServiceRestURIConstants.REGISTER_EMAIL_VERIFY_USER, method = RequestMethod.POST)
	public @ResponseBody boolean verfyEmailUpdate(@RequestBody TransUserDetailsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start email verify update user.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
					userDetailsDO.setcIsEmailVerify('Y');
					userDetailsDO.setsEmailVerifiedAt(new Date());
					userDetailsDO.setdUpdatedDate(new Date());
					userDetailsDO.setsUpdatedBy(userDetailsDO.getsCreatedBy());
					status = transUserService.updateRecord(userDetailsDO);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error fetching single User : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.DELETE_TRANS_USER, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody TransUserDetailsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start delete quest.");
			if(create_do.getsKey().equals("soft"))
			{
				TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
				userDetailsDO.setcIsDeleted('Y');
				userDetailsDO.setdUpdatedDate(new Date());
				userDetailsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = transUserService.updateRecord(userDetailsDO);
			} else if(create_do.getsKey().equals("active"))
			{
				TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
				userDetailsDO.setcIsActive('Y');
				userDetailsDO.setdUpdatedDate(new Date());
				userDetailsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = transUserService.updateRecord(userDetailsDO);
			} else if(create_do.getsKey().equals("inactive"))
			{
				TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
				userDetailsDO.setcIsActive('N');
				userDetailsDO.setdUpdatedDate(new Date());
				userDetailsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = transUserService.updateRecord(userDetailsDO);
			} else if(create_do.getsKey().equals("banned"))
			{
				TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
				userDetailsDO.setcIsBannedSurvey('Y');
				userDetailsDO.setdUpdatedDate(new Date());
				userDetailsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = transUserService.updateRecord(userDetailsDO);
			} else if(create_do.getsKey().equals("unbanned"))
			{
				TransUserDetailsDO userDetailsDO = transUserService.fetchForUpdate(create_do.getId());
				userDetailsDO.setcIsBannedSurvey('N');
				userDetailsDO.setdUpdatedDate(new Date());
				userDetailsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = transUserService.updateRecord(userDetailsDO);
			} else
			{
				status = transUserService.deleteRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete quest : " + excp.getMessage());
		}
		return status;
	}
}
