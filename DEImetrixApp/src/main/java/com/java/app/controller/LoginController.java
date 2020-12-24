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

import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.SessionValues;
import com.java.app.beans.StatusDO;
import com.java.app.beans.trans.TransSurvryInitDO;
import com.java.app.beans.trans.TransUserDetailsDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.SendingMail;
import com.java.app.utils.Util;

/**
 * User login controller
 */
@Controller
@RequestMapping(value ="/logincontroller",  method = RequestMethod.POST)
public class LoginController
{
	/** The logger. */
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private SendingMail mailController;
	
	/**
	 * Checking User name and Password
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @param flashAttributes
	 * @param username 
	 * @return Page
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, RedirectAttributes flashAttributes) 
	{
		AdminLoginDO admin_user_obj = new AdminLoginDO();
		TransUserDetailsDO trans_user_obj = new TransUserDetailsDO();
		ServerConfigDO configDO = new ServerConfigDO();
		boolean bMailStatus = false;
		SessionValues sessionValues = new SessionValues();
		String pagename = "redirect:login";
		TransSurvryInitDO survryInitDO = new TransSurvryInitDO();
		try 
		{
			//admin
			String sUsername = request.getParameter("adminusername");
			String sPassword = request.getParameter("adminpasswordname");
			//User
			String suserLogin = request.getParameter("trans_email_id");
			if((sUsername != null && sUsername != "") && (sPassword != null && sPassword != ""))
			{
				AdminLoginDO admin_dbcheck_obj = new AdminLoginDO();
				admin_dbcheck_obj.setsUserName(sUsername);
				String encrypt  = Util.MD5(sPassword);
				admin_dbcheck_obj.setsPassword(encrypt);

				//checking admin username & password
				RestTemplate restTemplate = new RestTemplate();
				admin_user_obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CHECK_ADMIN_LOGIN, admin_dbcheck_obj, AdminLoginDO.class);
				if(admin_user_obj.getId() != null)
				{
					sessionValues.setsLoginType(0);
					sessionValues.setsName(admin_user_obj.getsUserName());
					session.setAttribute(AppConstants.COMMON, sessionValues);
					session.setAttribute(AppConstants.ADMIN, admin_user_obj);
					pagename =  "redirect:reg_user_list";
				} else
				{
					flashAttributes.addFlashAttribute("invalidusername", "Please enter valid Admin credentials.");
					pagename =  "redirect:deimetrixcontrolpanel";
				}
			}else if((suserLogin != null && suserLogin != ""))
			{
				TransUserDetailsDO user_dbcheck_obj = new TransUserDetailsDO();
				user_dbcheck_obj.setsEmailId(suserLogin);

				SimpleDateFormat change_dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

				configDO = fetchingServerConfig();
				
				//checking admin username & password
				RestTemplate restTemplate = new RestTemplate();
				trans_user_obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CHECK_TRANS_USER_LOGIN, user_dbcheck_obj, TransUserDetailsDO.class);
				if(trans_user_obj.getId() != null)
				{
					if(trans_user_obj.getcIsEmailVerify() == 'Y')
					{
						if(trans_user_obj.getcIsActive() == 'N')
						{
							flashAttributes.addFlashAttribute("mail_status", "Sorry, this email id cannot be used for Survey participation!");
						} else if(configDO.getdMaintainStartDate() != null)
						{
							if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
							{
								flashAttributes.addFlashAttribute("invalidusername", "The Server is down for maintenance. Please try after "+ change_dateFormat.format(configDO.getdMaintainEndDate()));								
							} else
							{
								survryInitDO = fetchingTranSurveyInit(trans_user_obj.getId(), 2);
								if(survryInitDO.getId() == null && trans_user_obj.getcIsBannedSurvey() == 'Y')
								{
									survryInitDO = fetchingTranSurveyInit(trans_user_obj.getId(), 3);
								}
								sessionValues.setiSurveyInitPrimId(survryInitDO.getId());
								sessionValues.setiUserPrimId(trans_user_obj.getId());
								sessionValues.setsLoginType(1);
								sessionValues.setsName(trans_user_obj.getsFirstName()+" "+trans_user_obj.getsLastName());
								sessionValues.setiDisplayTimer(configDO.getiTotalSurveyTime());
								session.setAttribute(AppConstants.COMMON, sessionValues);
								session.setAttribute(AppConstants.USER, trans_user_obj);	
								pagename =  "redirect:trans_survey";
							}
						}
					}else 
					{
						Date mail_sent_date = trans_user_obj.getdCreatedDate();
						Date current_date = new Date();
						//in milliseconds
						long time_diff = current_date.getTime() - mail_sent_date.getTime();
						
						long diff_Days = time_diff / (24 * 60 * 60 * 1000);
						long diff_Hours = time_diff / (60 * 60 * 1000) % 24;
						long diff_Minutes = time_diff / (60 * 1000) % 60;
						
						if(diff_Days == 0 && diff_Hours == 0 && diff_Minutes < trans_user_obj.getiTimeout())
						{
							flashAttributes.addFlashAttribute("invalidusername", "A verification link has been sent to your email id. Please login to your email and verify for Survey participation.");
						} else
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
								flashAttributes.addFlashAttribute("invalidusername", "A verification link has been sent to your email id. Please login to your email and verify for Survey participation.");
							} else
							{
								flashAttributes.addFlashAttribute("invalidusername", "Sorry, please try again later.");
							}
						}
						pagename =  "redirect:login";
					}
				} else
				{
					flashAttributes.addFlashAttribute("invalidusername", "This email id is not present in our records. Please register your details.");
				}
			}
			return pagename;
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
			return "redirect:login";
		}
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
	 * Fetching trans survey init
	 * @param status_id 
	 * 
	 * @return
	 */
	private TransSurvryInitDO fetchingTranSurveyInit(Integer user_details_id, Integer status_id)
	{
		TransSurvryInitDO survryInitDO = new TransSurvryInitDO();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			survryInitDO.setUser_id(new TransUserDetailsDO(user_details_id));
			survryInitDO.setStatus(new StatusDO(status_id));
			survryInitDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_SERVER_INIT, survryInitDO, TransSurvryInitDO.class);
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return survryInitDO;
	}
	
	/*if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
	{
		System.out.println("pass");
	} else
	{
		System.out.println("failed");
	}
	
	if(new Date().compareTo(configDO.getdMaintainStartDate()) >=0 && new Date().compareTo(configDO.getdMaintainEndDate()) <= 0)
	{
		System.out.println("pass");
	} else
	{
		System.out.println("failed");
	}*/
}