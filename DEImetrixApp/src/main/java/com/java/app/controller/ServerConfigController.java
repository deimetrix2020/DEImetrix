package com.java.app.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.Util;

@Controller
@RequestMapping(value ="/serverconfig")
public class ServerConfigController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(ServerConfigController.class);
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/admincreateRecord", method = RequestMethod.POST)
	public @ResponseBody boolean createRecord(HttpServletRequest request, HttpSession session)
	{
		AdminLoginDO oldadminDO = new AdminLoginDO();
		boolean bStatus = false;
		try
		{
			String sUserName = request.getParameter("username");
			String sPassword = request.getParameter("userpassword");

			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			// set the values
			oldadminDO = fetchingAdminDetails();
			
			String sprim_id = request.getParameter("adminuserid");
			if(null != sprim_id && sprim_id != "")
			{
				oldadminDO.setId(Integer.valueOf(sprim_id));
				oldadminDO.setsUpdatedBy(session_admin.getsUserName());
			} else
			{
				oldadminDO.setsCreatedBy(session_admin.getsUserName());		
			}
			
			RestTemplate restTemplate = new RestTemplate();
			oldadminDO.setsUserName(sUserName);
			if(!sPassword.equals(""))
			{
				String sEncriptPwd = Util.MD5(sPassword);
				oldadminDO.setsPassword(sEncriptPwd);
			}
			
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_ADMIN_USER, oldadminDO, Boolean.class);
			if(bStatus)
				logger.info("Admin password Inserted");
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}

	/**
	 * Fetching server config by id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fetchingServerConfig", method = RequestMethod.POST)
	public @ResponseBody ServerConfigDO fetchingServerConfig(HttpServletRequest request, HttpSession session)
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
	 * Fetching single admin user
	 * 
	 * @return
	 */
	private AdminLoginDO fetchingAdminDetails()
	{
		AdminLoginDO adminLoginDO = new AdminLoginDO();
		try
		{
			adminLoginDO.setId(1);
			RestTemplate restTemplate = new RestTemplate();
			adminLoginDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_ADMIN_USER, adminLoginDO, AdminLoginDO.class);
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return adminLoginDO;
	}

	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/mailcreateRecord", method = RequestMethod.POST)
	public @ResponseBody boolean mailcreateRecord(HttpServletRequest request, HttpSession session)
	{
		ServerConfigDO configDO = new ServerConfigDO();
		boolean bStatus = false;
		try
		{
			String mailid = request.getParameter("frommailid");
			String sPassword = request.getParameter("mailpasswsord");
			String sHostName = request.getParameter("smtphostname");
			String sSMTPPort = request.getParameter("smtpport");
			String sVerifytimeout = request.getParameter("verifytimeout");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			String sprim_id = request.getParameter("serverconfigid");
			if(null != sprim_id && sprim_id != "")
			{
				configDO.setId(Integer.valueOf(sprim_id));
				configDO.setsUpdatedBy(session_admin.getsUserName());
			} else
			{
				configDO.setsCreatedBy(session_admin.getsUserName());
			}
			
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			configDO.setsFromMailId(mailid);
			configDO.setsFromMailPassword(sPassword);
			configDO.setsSMTPHostName(sHostName);
			configDO.setsSMTPHostNumber(sSMTPPort);
			configDO.setiTimeout(Integer.valueOf(sVerifytimeout));
			if(configDO.getdMaintainStartDate() == null)
			{
				Calendar calendar = Calendar.getInstance();
				// Add 1 Year
				calendar.add(Calendar.YEAR, 1);
				
				configDO.setdMaintainStartDate(calendar.getTime());
				configDO.setdMaintainEndDate(calendar.getTime());
				configDO.setiQuestions_Timeout(3);
			}
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_SERVER_CONFIG, configDO, Boolean.class);
			if(bStatus)
				logger.info("Mail config Inserted");
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
	
	@RequestMapping(value = "/surveycreateRecord", method = RequestMethod.POST)
	public @ResponseBody boolean surveycreateRecord(HttpServletRequest request, HttpSession session)
	{
		ServerConfigDO configDO = new ServerConfigDO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		boolean bStatus = false;
		try
		{
			String sVerifytimeout = request.getParameter("ques_timeout");
			String date_range = request.getParameter("question_start_date");
			String sEnd_Date = request.getParameter("question_end_date");
			String sTotalTime = request.getParameter("ques_total_time");
			
			String[] daaterange_split = date_range.split(" - ");
			
			AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
			
			String sprim_id = request.getParameter("configid");
			if(null != sprim_id && sprim_id != "")
			{
				configDO.setId(Integer.valueOf(sprim_id));
				configDO.setsUpdatedBy(session_admin.getsUserName());
			} else
			{
				configDO.setsCreatedBy(session_admin.getsUserName());
			}
			RestTemplate restTemplate = new RestTemplate();
			// set the values
			configDO.setdMaintainStartDate(dateFormat.parse(daaterange_split[0].trim()));
			configDO.setdMaintainEndDate(dateFormat.parse(daaterange_split[1].trim()));
			configDO.setiQuestions_Timeout(Integer.valueOf(sVerifytimeout));
			configDO.setiTotalSurveyTime(Integer.valueOf(sTotalTime));
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_SERVER_CONFIG_MAINTAIN, configDO, Boolean.class);
			if(bStatus)
				logger.info("Survey config Inserted");
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return bStatus;
	}
}
