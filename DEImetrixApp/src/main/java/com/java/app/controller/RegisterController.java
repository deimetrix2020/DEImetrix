package com.java.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.StatusDO;
import com.java.app.beans.trans.TransUserDetailsDO;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.SendingMail;
import com.java.app.utils.Util;

/**
 * User login controller
 */
@Controller
@RequestMapping(value ="/register",  method = RequestMethod.POST)
public class RegisterController
{
	/** The logger. */
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private SendingMail mailController;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpSession session, RedirectAttributes flashAttributes) 
	{
		TransUserDetailsDO trans_user_obj = null;
		ServerConfigDO configDO = new ServerConfigDO();
		String pagename = "redirect:login";
		TransUserDetailsDO trans_user_check = new TransUserDetailsDO();
		try 
		{
			//User Details
			String sFirstName = request.getParameter("firstname");
			String sLastName = request.getParameter("lastname");
			String sEmail = request.getParameter("email_id");
			
			configDO = fetchingServerConfig();
			trans_user_check = fetchingUserDetailsByEmail(sEmail);
			
			if(trans_user_check.getId() != null)
			{
				if(trans_user_check.getcIsEmailVerify() == 'Y')
				{
					flashAttributes.addFlashAttribute("mail_status", "This email is already registered.  Please choose the 'Login' tab and enter your email address to access the survey.");
				} else if(trans_user_check.getcIsActive() == 'N')
				{
					flashAttributes.addFlashAttribute("mail_status", "Sorry, this email id cannot be used for Survey participation!");
				} else
				{
					//Assigning values
					trans_user_obj = trans_user_check;
					sendingMailRegisterUser(flashAttributes, trans_user_obj, request, configDO);
				}
			} else
			{
				//Assigning values
				trans_user_obj = fetchingTransUserRegister(sFirstName, sLastName, sEmail, configDO);
				if(trans_user_obj.getId() != null)
				{
					sendingMailRegisterUser(flashAttributes, trans_user_obj, request, configDO);
				} else
				{
					flashAttributes.addFlashAttribute("mail_status", "Sorry, please try again later.");
				}
			}
			return pagename;
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
			return "redirect:login";
		}
	}
	
	private TransUserDetailsDO fetchingTransUserRegister(String sFirstName, String sLastName, String sEmail, ServerConfigDO configDO) 
	{
		Integer trans_user_obj = null;
		TransUserDetailsDO trans_user_assign = new TransUserDetailsDO();
		try
		{
			trans_user_assign.setsFirstName(sFirstName);
			trans_user_assign.setsLastName(sLastName);
			trans_user_assign.setsEmailId(sEmail);
			trans_user_assign.setcIsEmailVerify('N');
			trans_user_assign.setcIsDeleted('N');
			trans_user_assign.setcIsActive('Y');
			trans_user_assign.setcIsBannedSurvey('N');
			trans_user_assign.setsCreatedBy(sFirstName+" "+sLastName);
			trans_user_assign.setiTimeout(configDO.getiTimeout());
			trans_user_assign.setStatus(new StatusDO(2));
			//Register User
			RestTemplate restTemplate = new RestTemplate();
			trans_user_obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.REGISTER_TRANS_USER, trans_user_assign, Integer.class);
			trans_user_assign.setId(trans_user_obj);
		}catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		}
		return trans_user_assign;
	}

	private boolean sendingMailRegisterUser(RedirectAttributes flashAttributes, TransUserDetailsDO trans_user_obj, HttpServletRequest request, ServerConfigDO configDO)
	{
		boolean bMailStatus = false;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			String prepare_str1 = dateFormat.format(new Date());
			String prepare_str2 = trans_user_obj.getsFirstName()+" "+trans_user_obj.getsLastName();
			String prepare_str3 = trans_user_obj.getId().toString();
			String all_prepare_str = prepare_str1+","+prepare_str2+","+prepare_str3;
			
			String value_encrypt = Util.encrypt(all_prepare_str);
			
			//URL creation
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
			String verification_url = base + "emailverify" + "?verify="+value_encrypt;
			
			bMailStatus = mailController.sendingMail(configDO, trans_user_obj, verification_url);
			if(bMailStatus)
			{
				flashAttributes.addFlashAttribute("mail_status", "A verification link has been sent to your email id. Please login to your email and verify for Survey participation.");
			} else
			{
				flashAttributes.addFlashAttribute("mail_status", "Sorry, please try again later.");
			}
		}catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		}
		return bMailStatus;
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
	 * Fetching Transaction user detail
	 * 
	 * @return
	 */
	private TransUserDetailsDO fetchingUserDetailsByEmail(String email_id)
	{
		TransUserDetailsDO userDetailsDO = new TransUserDetailsDO();
		try
		{
			userDetailsDO.setsEmailId(email_id);
			RestTemplate restTemplate = new RestTemplate();
			userDetailsDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CHECK_TRANS_USER_LOGIN, userDetailsDO, TransUserDetailsDO.class);
		}catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		}
		return userDetailsDO;
	}
}