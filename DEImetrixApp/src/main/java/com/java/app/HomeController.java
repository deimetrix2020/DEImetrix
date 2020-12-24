package com.java.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.DemographicsDO;
import com.java.app.beans.DimensionsDO;
import com.java.app.beans.DomainsDO;
import com.java.app.beans.InviteUsersDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.SessionValues;
import com.java.app.beans.StatusDO;
import com.java.app.beans.SurveyQuestionsDO;
import com.java.app.beans.trans.TransSurveyCalcDO;
import com.java.app.beans.trans.TransSurveyQuestionsDO;
import com.java.app.beans.trans.TransSurvryInitDO;
import com.java.app.beans.trans.TransUserDemoQuestionsDO;
import com.java.app.beans.trans.TransUserDetailsDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;
import com.java.app.utils.Util;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpSession session) 
	{
		HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
		return "common/login";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request, HttpSession session) 
	{
		try
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			String invite_name = request.getParameter("invite");
			if(null != invite_name)
			{
				model.addAttribute("key_name", invite_name);
			}else
			{
				model.addAttribute("key_name", "");
			}
		}catch (Exception e) {
			
		}
		return "common/login";
	}
	
	@RequestMapping(value = "/deimetrixcontrolpanel", method = RequestMethod.GET)
	public String adminlogin(Model model, HttpServletRequest request, HttpSession session) 
	{
		HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
		return "common/adminlogin";
	}
	
	@RequestMapping(value = "/emailverify", method = RequestMethod.GET)
	public String emailVerify(Model model, HttpServletRequest request, HttpSession session) 
	{
		String sPage = "common/login";
		TransUserDetailsDO fetching_user_details = new TransUserDetailsDO();
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			String request_name = request.getParameter("verify").replace(" ", "+");
			String sDecryptEmail = Util.decrypt(request_name);
			String[] sSplitDecrypt = sDecryptEmail.split(",");
			if(sSplitDecrypt.length == 3)
			{
				Integer prim_id = Integer.valueOf(sSplitDecrypt[2]);
			
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
				Date mail_sent_date = dateFormat.parse(sSplitDecrypt[0]);
				Date current_date = new Date();
				//in milliseconds
				long time_diff = current_date.getTime() - mail_sent_date.getTime();
				long diff_Days = time_diff / (24 * 60 * 60 * 1000);
				long diff_Hours = time_diff / (60 * 60 * 1000) % 24;
				long diff_Minutes = time_diff / (60 * 1000) % 60;
				
				fetching_user_details = fetchingUserDetailsByID(prim_id);
				
				if(diff_Days == 0 && diff_Hours == 0 && diff_Minutes > fetching_user_details.getiTimeout())
				{
					model.addAttribute("register_msg", "This verification link has expired, please get a fresh link by entering email id again on the Login page.");
				} else
				{
					if(fetching_user_details.getcIsEmailVerify() == 'N')
					{
						TransUserDetailsDO trans_user_obj = new TransUserDetailsDO();
						trans_user_obj.setId(prim_id);
						boolean bStatus = updateEmailVerification(trans_user_obj);
						if(bStatus)
						{
							List<InviteUsersDO> usersListObj = new ArrayList<InviteUsersDO>();
							InviteUsersDO usersDO = new InviteUsersDO();
							usersDO = retriveInviteUserRecord(fetching_user_details.getsEmailId());
							if(usersDO.getId() != null)
							{
								usersDO.setcIsEmailStatus('Y');
								usersDO.setReg_status(new StatusDO(3));
								usersDO.setSurvey_status(new StatusDO(5));
								usersListObj.add(usersDO);
								RestTemplate restTemplate = new RestTemplate();
								bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, usersListObj, Boolean.class);
							}
							model.addAttribute("register_msg", "Email has been verified successfully, please login.");
						} else
						{
							model.addAttribute("register_msg", "Sorry, please try again later.");
						}
					} else
					{
						model.addAttribute("register_msg", "This email was already verified, please login.");
					}
				}					
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * Demographics page list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//demography page
	@RequestMapping(value = "/demographics", method = RequestMethod.GET)
	public String adminusers(Model model, HttpServletRequest request, HttpSession session) {
		List<DemographicsDO> list_demo = new ArrayList<DemographicsDO>();
		String sPage = "admin/demographics";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					list_demo = fetchingAllDemoGraphics();
					model.addAttribute("demo_list", list_demo);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * Master domains page list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//Domains
	@RequestMapping(value = "/domains", method = RequestMethod.GET)
	public String survey_category(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<DomainsDO> list_demo = new ArrayList<DomainsDO>();
		String sPage = "admin/domains";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					list_demo = fetchingAllDomains();
					model.addAttribute("domain_list", list_demo);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * master Dimensions list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//survey sub category page
	@RequestMapping(value = "/dimensions", method = RequestMethod.GET)
	public String dimensions(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<DomainsDO> list_demo = new ArrayList<DomainsDO>();
		List<DimensionsDO> list_dimension = new ArrayList<DimensionsDO>();
		String sPage = "admin/dimensions";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					//Domains
					list_demo = fetchingAllDomains();
					//dimensions
					list_dimension = fetchingDimensions();
					model.addAttribute("domain_list", list_demo);
					model.addAttribute("dimnesions_list", list_dimension);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * master Survey questions list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//survey questions page
	@RequestMapping(value = "/survey_questions", method = RequestMethod.GET)
	public String survey_questions(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<DomainsDO> list_demo = new ArrayList<DomainsDO>();
		List<SurveyQuestionsDO> ques_list = new ArrayList<SurveyQuestionsDO>();
		String sPage = "admin/survey_questions";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					list_demo = fetchingAllDomains();
					//Questions
					ques_list = fetchingQuestionsList();
					
					model.addAttribute("domain_list", list_demo);
					model.addAttribute("questions_list", ques_list);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * fetching users page list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/reg_user_list", method = RequestMethod.GET)
	public String transuserdetails(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<TransUserDetailsDO> list_Obj = new ArrayList<TransUserDetailsDO>();
		String sPage = "admin/trans_user_details";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					list_Obj = fetchingAllUserList();
					model.addAttribute("users_list", list_Obj);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}

	/**
	 * fetching users page list
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//Domains
	@RequestMapping(value = "/invite_list", method = RequestMethod.GET)
	public String inviteuserdetails(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<InviteUsersDO> list_Obj = new ArrayList<InviteUsersDO>();
		String sPage = "admin/invite_users";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					list_Obj = fetchingAllInviteUserList();
					model.addAttribute("invite_list", list_Obj);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * Master server config page
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//server configuration page
	@RequestMapping(value = "/server_config", method = RequestMethod.GET)
	public String server_config(Model model, HttpServletRequest request, HttpSession session) 
	{
		ServerConfigDO configDO = new ServerConfigDO();
		AdminLoginDO adminlogin = new AdminLoginDO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		String sPage = "admin/serverdetails";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist(request)) 
			{
				sPage = "redirect:deimetrixcontrolpanel";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				if (userObj == null) 
				{
					sPage = "redirect:deimetrixcontrolpanel";
				} else if (userObj.getsLoginType() == 0) 
				{
					configDO = fetchingServerConfig();
					adminlogin = fetchingAdminDetails();
					model.addAttribute("serverconfig", configDO);
					model.addAttribute("adminlogindetails", adminlogin);
					model.addAttribute("server_date", dateFormat.format(new Date()));
				} else 
				{
					sPage = "common/permission";
				}
			}
		} catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return sPage;
	}
	
	/**
	 * Transaction slide page
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//trans survey page
	@RequestMapping(value = "/trans_survey", method = RequestMethod.GET)
	public String trans_survey(Model model, HttpServletRequest request, HttpSession session) 
	{
		List<DemographicsDO> list_demo = new ArrayList<DemographicsDO>();
		List<SurveyQuestionsDO> ques_list = new ArrayList<SurveyQuestionsDO>();
		List<TransUserDemoQuestionsDO> trans_demo_list = new ArrayList<TransUserDemoQuestionsDO>();
		List<TransSurveyQuestionsDO> trans_survey_ques_list = new ArrayList<TransSurveyQuestionsDO>();
		ServerConfigDO configDO = new ServerConfigDO();
		TransSurvryInitDO initDO = new TransSurvryInitDO();
		SimpleDateFormat change_dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		String sPage = "trans/takesurvey";
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			if (!Util.checkSessionExist_user(request)) 
			{
				sPage = "redirect:login";
			} else 
			{
				SessionValues userObj = (SessionValues) session.getAttribute(AppConstants.COMMON);
				TransUserDetailsDO trans_userObj = (TransUserDetailsDO) session.getAttribute(AppConstants.USER);
				if (userObj == null) 
				{
					sPage = "redirect:login";
				} else if (userObj.getsLoginType() == 1) 
				{
					list_demo = fetchingAllDemoGraphics();
					//Questions
					ques_list = fetchingQuestionsList();
					// server config
					configDO = fetchingServerConfig();
					//init survey
					initDO = fetchingTranSurveyInit(userObj.getiUserPrimId(), 2);
					if(initDO.getId() == null && trans_userObj.getcIsBannedSurvey() == 'Y')
					{
						initDO = fetchingTranSurveyInit(userObj.getiUserPrimId(), 3);
					}
					// tran demo question and answer list
					if(null != userObj.getiSurveyInitPrimId() && null != userObj.getiUserPrimId())
					{
						trans_demo_list = fetchingAllTransDemoList(userObj.getiSurveyInitPrimId(), userObj.getiUserPrimId());
						//trans survey question
						trans_survey_ques_list = fetchingAllTransSurveyQuesList(userObj.getiSurveyInitPrimId(), userObj.getiUserPrimId());						
					}
					
					// Start Date time
					Calendar before_calendar = Calendar.getInstance();
					before_calendar.setTime(configDO.getdMaintainStartDate());
					before_calendar.add(Calendar.HOUR_OF_DAY, -configDO.getiQuestions_Timeout());
					Date start_date = before_calendar.getTime();
					
					// End Date time
					Calendar after_calendar = Calendar.getInstance();
					after_calendar.setTime(configDO.getdMaintainStartDate());
					Date end_date = after_calendar.getTime();

					if(new Date().after(start_date) && new Date().before(end_date))
					{
						model.addAttribute("info_val", 0);
						model.addAttribute("info_date", change_dateFormat.format(configDO.getdMaintainStartDate()));
						model.addAttribute("info_end_date", change_dateFormat.format(configDO.getdMaintainEndDate()));
					} else
					{
						model.addAttribute("info_val", 1);
					}
					if(initDO.getcIsLeaderShip() == 'Y')
					{
						model.addAttribute("question_count", 27);
					} else
					{
						model.addAttribute("question_count", 18);
					}
					model.addAttribute("demo_list", list_demo);
					model.addAttribute("survey_init", initDO);
					model.addAttribute("survry_questions_list", ques_list);
					model.addAttribute("trans_uesr_demo_obj", trans_demo_list);
					model.addAttribute("trans_survey_ques_list", trans_survey_ques_list);
				} else 
				{
					sPage = "common/permission";
				}
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * report users
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//Domains
	@RequestMapping(value = "/profile_report", method = RequestMethod.GET)
	public String reportuseras(Model model, HttpServletRequest request, HttpSession session) 
	{
		String sPage = "trans/profile_report";
		List<TransSurveyCalcDO> list_obj = new ArrayList<TransSurveyCalcDO>();
		Integer d_dimensaion_count = 0;
		Integer e_dimensaion_count = 0;
		Integer i_dimensaion_count = 0;
		
		Double d_total = 0.0;
		Double e_total = 0.0;
		Double i_total = 0.0;
		List<String> max_builder = new ArrayList<String>();
		List<String> min_builder = new ArrayList<String>();
		String diversity_text = "Diversity";
		String equity_text = "Equity";
		String inclusion_text = "Inclusion";
		String none_text = "N/A";
		
		//dimension highest
		List<String> high_fir_builder = new ArrayList<String>();
		List<String> high_sec_builder = new ArrayList<String>();
		List<String> high_thre_builder = new ArrayList<String>();
		//dimension lowset
		List<String> low_fir_builder = new ArrayList<String>();
		List<String> low_sec_builder = new ArrayList<String>();
		List<String> low_thre_builder = new ArrayList<String>();
		//Map<String, Double> all_report_map = new HashMap<String, Double>();
		Map<String, Double> high_report_map = new HashMap<String, Double>();
		Map<String, Double> low_report_map = new HashMap<String, Double>();
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			SessionValues sessionValues = (SessionValues) session.getAttribute(AppConstants.COMMON);
			if (sessionValues == null) 
			{
				sPage = "redirect:login";
			} else if (sessionValues.getsLoginType() == 1) 
			{
				// tran demo question and answer list
				if(null != sessionValues.getiSurveyInitPrimId() && null != sessionValues.getiUserPrimId())
				{
					list_obj = fetchingAllCalcValues(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId());
					
					logger.info("Start to prepare profile report list...");
					for(TransSurveyCalcDO surveyCalc : list_obj)
					{
						if(surveyCalc.getsDomainName().equalsIgnoreCase("diversity"))
						{
							if(surveyCalc.getInit_id().getcIsLeaderShip() == 'Y')
							{
								model.addAttribute("leader_val", "Leader");
							} else
							{
								model.addAttribute("leader_val", "Individual Contributor");
							}
							d_total += surveyCalc.getdTotalCalcVal();
							d_dimensaion_count++;
						} else if(surveyCalc.getsDomainName().equalsIgnoreCase("equity"))
						{
							e_total += surveyCalc.getdTotalCalcVal();
							e_dimensaion_count++;
						} else if(surveyCalc.getsDomainName().equalsIgnoreCase("inclusion"))
						{
							i_total += surveyCalc.getdTotalCalcVal();
							i_dimensaion_count++;
						}
						if(surveyCalc.getdTotalCalcVal() >= 1.5)
						{
							high_report_map.put(surveyCalc.getsDomainName()+"|"+surveyCalc.getsDimensionName(), surveyCalc.getdTotalCalcVal());	
						} else if(surveyCalc.getdTotalCalcVal() >= 0 && surveyCalc.getdTotalCalcVal() <= 1.4)
						{
							low_report_map.put(surveyCalc.getsDomainName()+"|"+surveyCalc.getsDimensionName(), surveyCalc.getdTotalCalcVal());
						}
						//all_report_map.put(surveyCalc.getsDomainName()+"|"+surveyCalc.getsDimensionName(), surveyCalc.getdTotalCalcVal());
					}
					
					//Map<String, Double> sort_asc_all_dimen = entriesSortedByValues(all_report_map);
					Map<String, Double> sort_asc_high_dimen = entriesSortedByValues(high_report_map);
					Map<String, Double> sort_asc_low_dimen = entriesSortedByValues(low_report_map);
					
					/*List<Double> dimen_list = new ArrayList<Double>();
					Set<Double> valuesSet = new HashSet<Double>(sort_asc_all_dimen.values());
					dimen_list.clear();
					dimen_list.addAll(valuesSet);
					Collections.sort(dimen_list, Collections.reverseOrder());*/
					
					//high
					List<Double> high_dimen_list = new ArrayList<Double>();
					Set<Double> high_valuesSet = new HashSet<Double>(sort_asc_high_dimen.values());
					high_dimen_list.clear();
					high_dimen_list.addAll(high_valuesSet);
					Collections.sort(high_dimen_list, Collections.reverseOrder());
					
					//log
					List<Double> low_dimen_list = new ArrayList<Double>();
					Set<Double> low_valuesSet = new HashSet<Double>(sort_asc_low_dimen.values());
					low_dimen_list.clear();
					low_dimen_list.addAll(low_valuesSet);
					Collections.sort(low_dimen_list);
					
					Integer high_fir_count = 0;
					Integer high_sec_count = 0;
					Integer high_thre_count = 0;
					Integer low_fir_count = 0;
					Integer low_sec_count = 0;
					Integer low_thre_count = 0;
					
					//first high
					for (Double dimen_total : high_dimen_list) 
					{ 
						List<TransSurveyCalcDO> transSurveyCalList = new ArrayList<TransSurveyCalcDO>();
						if(high_fir_builder.isEmpty() && high_fir_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								high_fir_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							high_fir_count++;
						}
						else if(!high_fir_builder.isEmpty() && high_sec_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								high_sec_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							high_sec_count++;
						}
						else if(!high_fir_builder.isEmpty() && !high_sec_builder.isEmpty() && high_thre_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								high_thre_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							high_thre_count++;
						}
					}

					for(Double dimen_total : low_dimen_list)
					{
						List<TransSurveyCalcDO> transSurveyCalList = new ArrayList<TransSurveyCalcDO>();
						if(low_fir_builder.isEmpty()  && low_fir_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								low_fir_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							low_fir_count++;
						}
						else if(!low_fir_builder.isEmpty()  && low_sec_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								low_sec_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							low_sec_count++;
						}
						else if(!low_fir_builder.isEmpty() && !low_sec_builder.isEmpty() && low_thre_count == 0)
						{
							transSurveyCalList = fetchingAllCalcValuesByTotal(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId(), dimen_total);
							for (TransSurveyCalcDO values_list : transSurveyCalList) 
							{
								low_thre_builder.add(values_list.getsDimensionName()+ " ("+values_list.getsDomainName()+")");
							}
							low_thre_count++;
						}
					}
					
					/*for (Map.Entry<String, Double> en : all_report_map.entrySet()) 
					{ 
						String[] fir_dimen_name = en.getKey().split("\\|");
						if(en.getValue() >= 2.1)
						{
							high_fir_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						} else if(en.getValue() >= 1.6 && en.getValue() <= 2)
						{
							high_sec_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						} else if(en.getValue() >= 1.1 && en.getValue() <= 1.5)
						{
							high_thre_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						} else if(en.getValue() >= 0 && en.getValue() <= 0.4)
						{
							low_fir_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						}  else if(en.getValue() >= 0.5 && en.getValue() <= 0.8)
						{
							low_sec_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						}  else if(en.getValue() >= 0.9 && en.getValue() <= 1.4)
						{
							low_thre_builder.add(fir_dimen_name[1]+ " ("+fir_dimen_name[0]+")");
						}
					}*/
					
					if(high_fir_builder.isEmpty())
					{
						high_fir_builder.add("N/A");
					}
					if(high_sec_builder.isEmpty())
					{
						high_sec_builder.add("N/A");
					}
					if(high_thre_builder.isEmpty())
					{
						high_thre_builder.add("N/A");
					}
					if(low_fir_builder.isEmpty())
					{
						low_fir_builder.add("N/A");
					}
					if(low_sec_builder.isEmpty())
					{
						low_sec_builder.add("N/A");
					}
					if(low_thre_builder.isEmpty())
					{
						low_thre_builder.add("N/A");
					}
					
					Double d_calc_val = d_total / d_dimensaion_count;
					Double d_calc_percent_per = (d_calc_val/3) * 100;
					Double d_calc_percent = Double.valueOf(Math.round(d_calc_percent_per));
					
					Double e_calc_val = e_total / e_dimensaion_count;
					Double e_calc_percent_per = (e_calc_val/3) * 100;
					Double e_calc_percent = Double.valueOf(Math.round(e_calc_percent_per));
					
					Double i_calc_val = i_total / i_dimensaion_count;
					Double i_calc_percent_per = (i_calc_val/3) * 100;
					Double i_calc_percent = Double.valueOf(Math.round(i_calc_percent_per));
					
					Double t_calc_val = (d_calc_val + e_calc_val + i_calc_val) / 3;
					Double t_calc_percent_per = (t_calc_val/3) * 100;
					Double t_calc_percent = Double.valueOf(Math.round(t_calc_percent_per));
				
					/*########################### random text ############################*/

					// If all scores are unequal
					if((d_calc_percent.intValue() != e_calc_percent.intValue()) && ((e_calc_percent.intValue() != i_calc_percent.intValue()))
							&& ((d_calc_percent.intValue() != i_calc_percent.intValue())))
					{
						//find max val
						Integer max_val1 = Math.max(d_calc_percent.intValue(), e_calc_percent.intValue());
						Integer max_val2 = Math.max(i_calc_percent.intValue(), max_val1);
						
						if(max_val2 == d_calc_percent.intValue())
						{
							max_builder.add(diversity_text);
						}
						if(max_val2 == e_calc_percent.intValue())
						{
							max_builder.add(equity_text);
						}
						if(max_val2 == i_calc_percent.intValue())
						{
							max_builder.add(inclusion_text);
						}
						//find min val
						Integer min_val1 = Math.min(d_calc_percent.intValue(), e_calc_percent.intValue());
						Integer min_val2 = Math.min(i_calc_percent.intValue(), min_val1);
						
						if(min_val2 == d_calc_percent.intValue())
						{
							min_builder.add(diversity_text);
						}
						if(min_val2 == e_calc_percent.intValue())
						{
							min_builder.add(equity_text);
						}
						if(min_val2 == i_calc_percent.intValue())
						{
							min_builder.add(inclusion_text);
						}
					} 
					
					//If all scores are equal
					else if((d_calc_percent.intValue() == e_calc_percent.intValue()) && ((e_calc_percent.intValue() == i_calc_percent.intValue()))
							&& ((d_calc_percent.intValue() == i_calc_percent.intValue())))
					{
						//If all scores are equal and higher than 50%
						if(d_calc_percent.intValue() >= 50 && e_calc_percent.intValue() >= 50 && i_calc_percent.intValue() >= 50)
						{
							max_builder.add(equity_text);
							max_builder.add(diversity_text);
							max_builder.add(inclusion_text);
							min_builder.add(none_text);
						}
						//If all scores are equal and lower than 50%
						else if(d_calc_percent.intValue() < 50 && e_calc_percent.intValue() < 50 && i_calc_percent.intValue() < 50)
						{
							min_builder.add(equity_text);
							min_builder.add(diversity_text);
							min_builder.add(inclusion_text);
							max_builder.add(none_text);
						}
					}
					
					//If two scores are equal
					else if((d_calc_percent.intValue() == e_calc_percent.intValue()) || ((e_calc_percent.intValue() == i_calc_percent.intValue()))
							|| ((d_calc_percent.intValue() == i_calc_percent.intValue())))
					{
						if((d_calc_percent.intValue() == e_calc_percent.intValue()))
						{
							Integer max_val1 = Math.max(d_calc_percent.intValue(), i_calc_percent.intValue());

							if(max_val1 == d_calc_percent.intValue())
							{
								max_builder.add(diversity_text);
								max_builder.add(equity_text);
								min_builder.add(inclusion_text);
							} else
							{
								max_builder.add(inclusion_text);
								min_builder.add(diversity_text);
								min_builder.add(equity_text);
							}
							
						} else if((e_calc_percent.intValue() == i_calc_percent.intValue()))
						{
							Integer max_val1 = Math.max(e_calc_percent.intValue(), d_calc_percent.intValue());

							if(max_val1 == e_calc_percent.intValue())
							{
								max_builder.add(inclusion_text);
								max_builder.add(equity_text);								
								min_builder.add(diversity_text);
							} else  
							{
								max_builder.add(diversity_text);
								min_builder.add(inclusion_text);
								min_builder.add(equity_text);
							}
						} else if((d_calc_percent.intValue() == i_calc_percent.intValue()))
						{
							Integer max_val1 = Math.max(i_calc_percent.intValue(), e_calc_percent.intValue());
							
							if(max_val1 == i_calc_percent.intValue())
							{
								max_builder.add(diversity_text);
								max_builder.add(inclusion_text);
								min_builder.add(equity_text);
							} else 
							{
								max_builder.add(equity_text);
								min_builder.add(diversity_text);
								min_builder.add(inclusion_text);
							}
						}
					}
					
					//If all scores are equal and higher than 50%
					else if(d_calc_percent.intValue() >= 50 && e_calc_percent.intValue() >= 50 && i_calc_percent.intValue() >= 50)
					{
						max_builder.add(equity_text);
						max_builder.add(diversity_text);
						max_builder.add(inclusion_text);
						min_builder.add(none_text);
					}
					//If all scores are equal and lower than 50%
					else if(d_calc_percent.intValue() < 50 && e_calc_percent.intValue() < 50 && i_calc_percent.intValue() < 50)
					{
						min_builder.add(equity_text);
						min_builder.add(diversity_text);
						min_builder.add(inclusion_text);
						max_builder.add(none_text);
					}
					
					/*########################## random text #############################*/
					
					// diversity
					diversity_images(model, d_calc_percent);
					
					//Equity
					equity_images(model, e_calc_percent);
					
					//Inclusion
					inclusion_images(model, i_calc_percent);
					
					//total
					total_images(model, t_calc_percent);
					
					//setting % value
					model.addAttribute("total_score_per", t_calc_percent.intValue()+"%");
					//domain
					Collections.sort(max_builder);
					Collections.sort(min_builder);
					//high dimension
					Collections.sort(high_fir_builder);
					Collections.sort(high_sec_builder);
					Collections.sort(high_thre_builder);
					// low dimension
					Collections.sort(low_fir_builder);
					Collections.sort(low_sec_builder);
					Collections.sort(low_thre_builder);
					// random domain text
					model.addAttribute("max_domain", String.join(", ", max_builder));
					model.addAttribute("min_domain", String.join(", ", min_builder));
					// random high dimension text
					model.addAttribute("high_first_dimen", String.join(", ", high_fir_builder));
					model.addAttribute("high_sec_dimen", String.join(", ", high_sec_builder));
					model.addAttribute("high_three_dimen", String.join(", ", high_thre_builder));
					// random low dimension text
					model.addAttribute("low_first_dimen", String.join(", ", low_fir_builder));
					model.addAttribute("low_sec_dimen", String.join(", ", low_sec_builder));
					model.addAttribute("low_three_dimen", String.join(", ", low_thre_builder));
				}
			} else 
			{
				sPage = "common/permission";
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	/**
	 * report users
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	//Domains
	@RequestMapping(value = "/___old_report_work", method = RequestMethod.GET)
	public String reportuser_new(Model model, HttpServletRequest request, HttpSession session) 
	{
		String sPage = "trans/profile_report_new";
		List<TransSurveyCalcDO> list_obj = new ArrayList<TransSurveyCalcDO>();
		Integer d_dimensaion_count = 0;
		Integer e_dimensaion_count = 0;
		Integer i_dimensaion_count = 0;
		
		Double d_total = 0.0;
		Double e_total = 0.0;
		Double i_total = 0.0;
		
		try 
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
			SessionValues sessionValues = (SessionValues) session.getAttribute(AppConstants.COMMON);
			if (sessionValues == null) 
			{
				sPage = "redirect:login";
			} else if (sessionValues.getsLoginType() == 1) 
			{
				// tran demo question and answer list
				if(null != sessionValues.getiSurveyInitPrimId() && null != sessionValues.getiUserPrimId())
				{
					list_obj = fetchingAllCalcValues(sessionValues.getiSurveyInitPrimId(), sessionValues.getiUserPrimId());
					logger.info("Start to prepare profile report list...");
					for(TransSurveyCalcDO surveyCalc : list_obj)
					{
						if(surveyCalc.getsDomainName().equalsIgnoreCase("diversity"))
						{
							if(surveyCalc.getInit_id().getcIsLeaderShip() == 'Y')
							{
								model.addAttribute("leader_val", "Leader");
							} else
							{
								model.addAttribute("leader_val", "Individual Contributor");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Comfortable with Differences"))
							{
								model.addAttribute("diver1", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Appreciates Diverse Thought"))
							{
								model.addAttribute("diver2", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Develops Cultural Intelligence"))
							{
								model.addAttribute("diver3", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							d_total += surveyCalc.getdTotalCalcVal();
							d_dimensaion_count++;
						} else if(surveyCalc.getsDomainName().equalsIgnoreCase("equity"))
						{
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Acts with Fairness"))
							{
								model.addAttribute("equity1", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Engages Respectfully"))
							{
								model.addAttribute("equity2", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Fosters Stewardship"))
							{
								model.addAttribute("equity3", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							e_total += surveyCalc.getdTotalCalcVal();
							e_dimensaion_count++;
						} else if(surveyCalc.getsDomainName().equalsIgnoreCase("inclusion"))
						{
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Creates a Feeling of Belonging"))
							{
								model.addAttribute("inclu1", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Interacts with Humility"))
							{
								model.addAttribute("inclu2", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							if(surveyCalc.getsDimensionName().equalsIgnoreCase("Promotes Participation"))
							{
								model.addAttribute("inclu3", Math.round(surveyCalc.getdTotalPercVal())+"%");
							}
							i_total += surveyCalc.getdTotalCalcVal();
							i_dimensaion_count++;
						}
					}
					
					Double d_calc_val = d_total / d_dimensaion_count;
					Double d_calc_percent_per = (d_calc_val/3) * 100;
					Double d_calc_percent = Double.valueOf(Math.round(d_calc_percent_per));
					
					Double e_calc_val = e_total / e_dimensaion_count;
					Double e_calc_percent_per = (e_calc_val/3) * 100;
					Double e_calc_percent = Double.valueOf(Math.round(e_calc_percent_per));
					
					Double i_calc_val = i_total / i_dimensaion_count;
					Double i_calc_percent_per = (i_calc_val/3) * 100;
					Double i_calc_percent = Double.valueOf(Math.round(i_calc_percent_per));
					
					Double t_calc_val = (d_calc_val + e_calc_val + i_calc_val) / 3;
					Double t_calc_percent_per = (t_calc_val/3) * 100;
					Double t_calc_percent = Double.valueOf(Math.round(t_calc_percent_per));
				
					
					/*########################## random text #############################*/
 					
					// diversity
					diversity_images(model, d_calc_percent);
					
					//Equity
					equity_images(model, e_calc_percent);
					
					//Inclusion
					inclusion_images(model, i_calc_percent);
					
					//total
					total_images(model, t_calc_percent);
					
					//setting % value
					model.addAttribute("total_score_per", t_calc_percent.intValue()+"%");
				}
			} else 
			{
				sPage = "common/permission";
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return sPage;
	}
	
	private void diversity_images(Model model, Double d_calc_percent)
	{
		try
		{
			if(d_calc_percent.intValue() == 0)
			{
				model.addAttribute("diversity_img", 0);
			} 
			else if(d_calc_percent.intValue() >= 1 && d_calc_percent.intValue() <= 5)
			{
				model.addAttribute("diversity_img", 5);
			} 
			else if(d_calc_percent.intValue() >= 6 && d_calc_percent.intValue() <= 10)
			{
				model.addAttribute("diversity_img", 10);
			} 
			else if(d_calc_percent.intValue() >= 11 && d_calc_percent.intValue() <= 15)
			{
				model.addAttribute("diversity_img", 15);
			} 
			else if(d_calc_percent.intValue() >= 16 && d_calc_percent.intValue() <= 20)
			{
				model.addAttribute("diversity_img", 20);
			}
			else if(d_calc_percent.intValue() >= 21 && d_calc_percent.intValue() <= 25)
			{
				model.addAttribute("diversity_img", 25);
			}
			else if(d_calc_percent.intValue() >= 26 && d_calc_percent.intValue() <= 30)
			{
				model.addAttribute("diversity_img", 30);
			}
			else if(d_calc_percent.intValue() >= 31 && d_calc_percent.intValue() <= 33)
			{
				model.addAttribute("diversity_img", 35);
			}
			else if(d_calc_percent.intValue() >= 34 && d_calc_percent.intValue() <= 40)
			{
				model.addAttribute("diversity_img", 40);
			}
			else if(d_calc_percent.intValue() >= 41 && d_calc_percent.intValue() <= 45)
			{
				model.addAttribute("diversity_img", 45);
			}
			else if(d_calc_percent.intValue() >= 46 && d_calc_percent.intValue() <= 50)
			{
				model.addAttribute("diversity_img", 50);
			}
			else if(d_calc_percent.intValue() >= 51 && d_calc_percent.intValue() <= 55)
			{
				model.addAttribute("diversity_img", 55);
			}
			else if(d_calc_percent.intValue() >= 56 && d_calc_percent.intValue() <= 60)
			{
				model.addAttribute("diversity_img", 60);
			}
			else if(d_calc_percent.intValue() >= 61 && d_calc_percent.intValue() <= 67)
			{
				model.addAttribute("diversity_img", 65);
			}
			else if(d_calc_percent.intValue() >= 68 && d_calc_percent.intValue() <= 70)
			{
				model.addAttribute("diversity_img", 70);
			}
			else if(d_calc_percent.intValue() >= 71 && d_calc_percent.intValue() <= 75)
			{
				model.addAttribute("diversity_img", 75);
			}
			else if(d_calc_percent.intValue() >= 76 && d_calc_percent.intValue() <= 80)
			{
				model.addAttribute("diversity_img", 80);
			}
			else if(d_calc_percent.intValue() >= 81 && d_calc_percent.intValue() <= 85)
			{
				model.addAttribute("diversity_img", 85);
			}
			else if(d_calc_percent.intValue() >= 86 && d_calc_percent.intValue() <= 90)
			{
				model.addAttribute("diversity_img", 90);
			}
			else if(d_calc_percent.intValue() >= 91 && d_calc_percent.intValue() <= 95)
			{
				model.addAttribute("diversity_img", 95);
			}
			else if(d_calc_percent.intValue() >= 96)
			{
				model.addAttribute("diversity_img", 100);
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
	}
	
	private void equity_images(Model model, Double e_calc_percent)
	{
		try
		{
			if(e_calc_percent.intValue() == 0)
			{
				model.addAttribute("equity_img", 0);
			} 
			else if(e_calc_percent.intValue() >= 1 && e_calc_percent.intValue() <= 5)
			{
				model.addAttribute("equity_img", 5);
			} 
			else if(e_calc_percent.intValue() >= 6 && e_calc_percent.intValue() <= 10)
			{
				model.addAttribute("equity_img", 10);
			} 
			else if(e_calc_percent.intValue() >= 11 && e_calc_percent.intValue() <= 15)
			{
				model.addAttribute("equity_img", 15);
			} 
			else if(e_calc_percent.intValue() >= 16 && e_calc_percent.intValue() <= 20)
			{
				model.addAttribute("equity_img", 20);
			}
			else if(e_calc_percent.intValue() >= 21 && e_calc_percent.intValue() <= 25)
			{
				model.addAttribute("equity_img", 25);
			}
			else if(e_calc_percent.intValue() >= 26 && e_calc_percent.intValue() <= 30)
			{
				model.addAttribute("equity_img", 30);
			}
			else if(e_calc_percent.intValue() >= 31 && e_calc_percent.intValue() <= 33)
			{
				model.addAttribute("equity_img", 35);
			}
			else if(e_calc_percent.intValue() >= 34 && e_calc_percent.intValue() <= 40)
			{
				model.addAttribute("equity_img", 40);
			}
			else if(e_calc_percent.intValue() >= 41 && e_calc_percent.intValue() <= 45)
			{
				model.addAttribute("equity_img", 45);
			}
			else if(e_calc_percent.intValue() >= 46 && e_calc_percent.intValue() <= 50)
			{
				model.addAttribute("equity_img", 50);
			}
			else if(e_calc_percent.intValue() >= 51 && e_calc_percent.intValue() <= 55)
			{
				model.addAttribute("equity_img", 55);
			}
			else if(e_calc_percent.intValue() >= 56 && e_calc_percent.intValue() <= 60)
			{
				model.addAttribute("equity_img", 60);
			}
			else if(e_calc_percent.intValue() >= 61 && e_calc_percent.intValue() <= 67)
			{
				model.addAttribute("equity_img", 65);
			}
			else if(e_calc_percent.intValue() >= 68 && e_calc_percent.intValue() <= 70)
			{
				model.addAttribute("equity_img", 70);
			}
			else if(e_calc_percent.intValue() >= 71 && e_calc_percent.intValue() <= 75)
			{
				model.addAttribute("equity_img", 75);
			}
			else if(e_calc_percent.intValue() >= 76 && e_calc_percent.intValue() <= 80)
			{
				model.addAttribute("equity_img", 80);
			}
			else if(e_calc_percent.intValue() >= 81 && e_calc_percent.intValue() <= 85)
			{
				model.addAttribute("equity_img", 85);
			}
			else if(e_calc_percent.intValue() >= 86 && e_calc_percent.intValue() <= 90)
			{
				model.addAttribute("equity_img", 90);
			}
			else if(e_calc_percent.intValue() >= 91 && e_calc_percent.intValue() <= 95)
			{
				model.addAttribute("equity_img", 95);
			}
			else if(e_calc_percent.intValue() >= 96)
			{
				model.addAttribute("equity_img", 100);
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
	}
	
	private void inclusion_images(Model model, Double i_calc_percent)
	{
		try
		{
			if(i_calc_percent.intValue() == 0)
			{
				model.addAttribute("inclusion_img", 0);
			} 
			else if(i_calc_percent.intValue() >= 1 && i_calc_percent.intValue() <= 5)
			{
				model.addAttribute("inclusion_img", 5);
			} 
			else if(i_calc_percent.intValue() >= 6 && i_calc_percent.intValue() <= 10)
			{
				model.addAttribute("inclusion_img", 10);
			} 
			else if(i_calc_percent.intValue() >= 11 && i_calc_percent.intValue() <= 15)
			{
				model.addAttribute("inclusion_img", 15);
			} 
			else if(i_calc_percent.intValue() >= 16 && i_calc_percent.intValue() <= 20)
			{
				model.addAttribute("inclusion_img", 20);
			}
			else if(i_calc_percent.intValue() >= 21 && i_calc_percent.intValue() <= 25)
			{
				model.addAttribute("inclusion_img", 25);
			}
			else if(i_calc_percent.intValue() >= 26 && i_calc_percent.intValue() <= 30)
			{
				model.addAttribute("inclusion_img", 30);
			}
			else if(i_calc_percent.intValue() >= 31 && i_calc_percent.intValue() <= 35)
			{
				model.addAttribute("inclusion_img", 35);
			}
			else if(i_calc_percent.intValue() >= 36 && i_calc_percent.intValue() <= 40)
			{
				model.addAttribute("inclusion_img", 40);
			}
			else if(i_calc_percent.intValue() >= 41 && i_calc_percent.intValue() <= 45)
			{
				model.addAttribute("inclusion_img", 45);
			}
			else if(i_calc_percent.intValue() >= 46 && i_calc_percent.intValue() <= 50)
			{
				model.addAttribute("inclusion_img", 50);
			}
			else if(i_calc_percent.intValue() >= 51 && i_calc_percent.intValue() <= 55)
			{
				model.addAttribute("inclusion_img", 55);
			}
			else if(i_calc_percent.intValue() >= 56 && i_calc_percent.intValue() <= 60)
			{
				model.addAttribute("inclusion_img", 60);
			}
			else if(i_calc_percent.intValue() >= 61 && i_calc_percent.intValue() <= 67)
			{
				model.addAttribute("inclusion_img", 65);
			}
			else if(i_calc_percent.intValue() >= 68 && i_calc_percent.intValue() <= 70)
			{
				model.addAttribute("inclusion_img", 70);
			}
			else if(i_calc_percent.intValue() >= 71 && i_calc_percent.intValue() <= 75)
			{
				model.addAttribute("inclusion_img", 75);
			}
			else if(i_calc_percent.intValue() >= 76 && i_calc_percent.intValue() <= 80)
			{
				model.addAttribute("inclusion_img", 80);
			}
			else if(i_calc_percent.intValue() >= 81 && i_calc_percent.intValue() <= 85)
			{
				model.addAttribute("inclusion_img", 85);
			}
			else if(i_calc_percent.intValue() >= 86 && i_calc_percent.intValue() <= 90)
			{
				model.addAttribute("inclusion_img", 90);
			}
			else if(i_calc_percent.intValue() >= 91 && i_calc_percent.intValue() <= 95)
			{
				model.addAttribute("inclusion_img", 95);
			}
			else if(i_calc_percent.intValue() >= 96)
			{
				model.addAttribute("inclusion_img", 100);
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
	}
	
	private void total_images(Model model, Double t_calc_percent)
	{
		try
		{
			if(t_calc_percent.intValue() == 0)
			{
				model.addAttribute("total_img", 0);
			} 
			else if(t_calc_percent.intValue() >= 1 && t_calc_percent.intValue() <= 5)
			{
				model.addAttribute("total_img", 5);
			} 
			else if(t_calc_percent.intValue() >= 6 && t_calc_percent.intValue() <= 10)
			{
				model.addAttribute("total_img", 10);
			} 
			else if(t_calc_percent.intValue() >= 11 && t_calc_percent.intValue() <= 15)
			{
				model.addAttribute("total_img", 15);
			} 
			else if(t_calc_percent.intValue() >= 16 && t_calc_percent.intValue() <= 20)
			{
				model.addAttribute("total_img", 20);
			}
			else if(t_calc_percent.intValue() >= 21 && t_calc_percent.intValue() <= 25)
			{
				model.addAttribute("total_img", 25);
			}
			else if(t_calc_percent.intValue() >= 26 && t_calc_percent.intValue() <= 30)
			{
				model.addAttribute("total_img", 30);
			}
			else if(t_calc_percent.intValue() >= 31 && t_calc_percent.intValue() <= 33)
			{
				model.addAttribute("total_img", 35);
			}
			else if(t_calc_percent.intValue() >= 34 && t_calc_percent.intValue() <= 40)
			{
				model.addAttribute("total_img", 40);
			}
			else if(t_calc_percent.intValue() >= 41 && t_calc_percent.intValue() <= 45)
			{
				model.addAttribute("total_img", 45);
			}
			else if(t_calc_percent.intValue() >= 46 && t_calc_percent.intValue() <= 50)
			{
				model.addAttribute("total_img", 50);
			}
			else if(t_calc_percent.intValue() >= 51 && t_calc_percent.intValue() <= 55)
			{
				model.addAttribute("total_img", 55);
			}
			else if(t_calc_percent.intValue() >= 56 && t_calc_percent.intValue() <= 60)
			{
				model.addAttribute("total_img", 60);
			}
			else if(t_calc_percent.intValue() >= 61 && t_calc_percent.intValue() <= 67)
			{
				model.addAttribute("total_img", 65);
			}
			else if(t_calc_percent.intValue() >= 68 && t_calc_percent.intValue() <= 70)
			{
				model.addAttribute("total_img", 70);
			}
			else if(t_calc_percent.intValue() >= 71 && t_calc_percent.intValue() <= 75)
			{
				model.addAttribute("total_img", 75);
			}
			else if(t_calc_percent.intValue() >= 76 && t_calc_percent.intValue() <= 80)
			{
				model.addAttribute("total_img", 80);
			}
			else if(t_calc_percent.intValue() >= 81 && t_calc_percent.intValue() <= 85)
			{
				model.addAttribute("total_img", 85);
			}
			else if(t_calc_percent.intValue() >= 86 && t_calc_percent.intValue() <= 90)
			{
				model.addAttribute("total_img", 90);
			}
			else if(t_calc_percent.intValue() >= 91 && t_calc_percent.intValue() <= 95)
			{
				model.addAttribute("total_img", 95);
			}
			else if(t_calc_percent.intValue() >= 96)
			{
				model.addAttribute("total_img", 100);
			}
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
	}

    // function to sort hashmap by values 
    public static HashMap<String, Double> entriesSortedByValues(Map<String, Double> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Double> > list = 
               new LinkedList<Map.Entry<String, Double> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() 
        { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
	
	/**
	 * Invite user list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InviteUsersDO> fetchingAllInviteUserList() 
	{
		List<InviteUsersDO> list_Obj = new ArrayList<InviteUsersDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_INVITE_USERS_LIST, list_Obj, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<InviteUsersDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, InviteUsersDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/**
	 * fetching all calc values
	 * 
	 * @param getiSurveyInitPrimId
	 * @param getiUserPrimId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TransSurveyCalcDO> fetchingAllCalcValues(Integer getiSurveyInitPrimId, Integer getiUserPrimId) 
	{
		List<TransSurveyCalcDO> list_demo = new ArrayList<TransSurveyCalcDO>();
		
		TransSurveyCalcDO surveyCalc_obj = new TransSurveyCalcDO();
		try
		{
			surveyCalc_obj.setInit_id(new TransSurvryInitDO(getiSurveyInitPrimId));
			surveyCalc_obj.setUser_id(new TransUserDetailsDO(getiUserPrimId));
			
			RestTemplate restTemplate = new RestTemplate();
			list_demo = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_ALL_CALC_VALUE, surveyCalc_obj, List.class);
			String element = new Gson().toJson(list_demo, new TypeToken<ArrayList<TransSurveyCalcDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_demo = Arrays.asList(mapper.readValue(element, TransSurveyCalcDO[].class));
		}catch (Exception e) 
		{
			logger.info("error :"+ e.getMessage());
		}
		return list_demo;
	}
	
	/**
	 * fetching all calc values by total
	 * 
	 * @param getiSurveyInitPrimId
	 * @param getiUserPrimId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TransSurveyCalcDO> fetchingAllCalcValuesByTotal(Integer getiSurveyInitPrimId, Integer getiUserPrimId, Double total_calc_val) 
	{
		List<TransSurveyCalcDO> list_demo = new ArrayList<TransSurveyCalcDO>();
		
		TransSurveyCalcDO surveyCalc_obj = new TransSurveyCalcDO();
		try
		{
			surveyCalc_obj.setInit_id(new TransSurvryInitDO(getiSurveyInitPrimId));
			surveyCalc_obj.setUser_id(new TransUserDetailsDO(getiUserPrimId));
			surveyCalc_obj.setdTotalCalcVal(total_calc_val);
			
			RestTemplate restTemplate = new RestTemplate();
			list_demo = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_ALL_CALC_VALUE_BY_TOTSL_VAL, surveyCalc_obj, List.class);
			String element = new Gson().toJson(list_demo, new TypeToken<ArrayList<TransSurveyCalcDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_demo = Arrays.asList(mapper.readValue(element, TransSurveyCalcDO[].class));
		}catch (Exception e) 
		{
			logger.info("error :"+ e.getMessage());
		}
		return list_demo;
	}
	
	/**
	 * Fetching all tran demo list
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransUserDemoQuestionsDO> fetchingAllTransDemoList(Integer init_id, Integer user_id) 
	{
		List<TransUserDemoQuestionsDO> list_Obj = new ArrayList<TransUserDemoQuestionsDO>();
		TransUserDemoQuestionsDO transUserDemo = new TransUserDemoQuestionsDO();
		try
		{
			transUserDemo.setInit_id(new TransSurvryInitDO(init_id));
			transUserDemo.setUser_id(new TransUserDetailsDO(user_id));
			
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_TRANS_DEMO_QUESTION_LIST, transUserDemo, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransUserDemoQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, TransUserDemoQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/* ############################################################################### */
	
	/**
	 * Fetching all tran survey questions list ny domain name
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> fetchingAllTransSurveyQuesListByName(Integer init_id, Integer user_id, String sDomainName) 
	{
		List<String> list_Obj = new ArrayList<String>();
		TransSurveyQuestionsDO transUserDemo = new TransSurveyQuestionsDO();
		try
		{
			transUserDemo.setInit_id(new TransSurvryInitDO(init_id));
			transUserDemo.setUser_id(new TransUserDetailsDO(user_id));
			transUserDemo.setsDomains(sDomainName);
			
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_TRANS_SURVEY_BY_DOMAIN_NAME, transUserDemo, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransSurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, String[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/**
	 * Fetching all tran survey questions list ny domain name dimension
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransSurveyQuestionsDO> fetchingAllTransSurveyQuesListByDomain(Integer init_id, Integer user_id, String sDomainName, String dimension) 
	{
		List<TransSurveyQuestionsDO> list_Obj = new ArrayList<TransSurveyQuestionsDO>();
		TransSurveyQuestionsDO transUserDemo = new TransSurveyQuestionsDO();
		try
		{
			transUserDemo.setInit_id(new TransSurvryInitDO(init_id));
			transUserDemo.setUser_id(new TransUserDetailsDO(user_id));
			transUserDemo.setsDomains(sDomainName);
			transUserDemo.setsDimensions(dimension);
			
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_TRANS_SURVEY_BY_DOMAIN_DIMENSION, transUserDemo, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransSurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, TransSurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/**
	 * Fetching all tran survey questions list
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransSurveyQuestionsDO> fetchingAllTransSurveyQuesList(Integer init_id, Integer user_id) 
	{
		List<TransSurveyQuestionsDO> list_Obj = new ArrayList<TransSurveyQuestionsDO>();
		TransSurveyQuestionsDO transUserDemo = new TransSurveyQuestionsDO();
		try
		{
			transUserDemo.setInit_id(new TransSurvryInitDO(init_id));
			transUserDemo.setUser_id(new TransUserDetailsDO(user_id));
			
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_ALL_TRANS_SURVEY_QUESTION, transUserDemo, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransSurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, TransSurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/* ############################################################################### */
	
	/**
	 * Fetching all reg user list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransUserDetailsDO> fetchingAllUserList() 
	{
		List<TransUserDetailsDO> list_Obj = new ArrayList<TransUserDetailsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCH_TRANS_USERS_LIST, list_Obj, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransUserDetailsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, TransUserDetailsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/**
	 * Fetching Transaction user detail
	 * 
	 * @return
	 */
	private TransUserDetailsDO fetchingUserDetailsByID(Integer id)
	{
		TransUserDetailsDO userDetailsDO = new TransUserDetailsDO();
		try
		{
			userDetailsDO.setId(id);
			RestTemplate restTemplate = new RestTemplate();
			userDetailsDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_TRANS_SINGLE_USER, userDetailsDO, TransUserDetailsDO.class);
		}catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		}
		return userDetailsDO;
	}
	
	/**
	 * Update Email verification detials
	 * 
	 * @param trans_user_obj
	 * @return
	 */
	private boolean updateEmailVerification(TransUserDetailsDO trans_user_obj)
	{
		boolean bStatus = false;
		try
		{
			//Email verification User
			RestTemplate restTemplate = new RestTemplate();
			bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.REGISTER_EMAIL_VERIFY_USER, trans_user_obj, Boolean.class);
		}catch (Exception excp) 
		{
			logger.info("error :", excp.getMessage());
		}
		return bStatus;
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
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return serverConfigDO;
	}
	
	/**
	 * fetching Admin credentials
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
	 * Fetching all demographics
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<DemographicsDO> fetchingAllDemoGraphics()
	{
		List<DemographicsDO> list_demo = new ArrayList<DemographicsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DEMOGRAPHICS_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo, new TypeToken<ArrayList<DemographicsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_demo = Arrays.asList(mapper.readValue(element, DemographicsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_demo;
	}
	
	/**
	 * Fetching all domains
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<DomainsDO> fetchingAllDomains() 
	{
		List<DomainsDO> list_demo = new ArrayList<DomainsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DOMAIN_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo, new TypeToken<ArrayList<DomainsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_demo = Arrays.asList(mapper.readValue(element, DomainsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_demo;
	}
	
	/**
	 * Fetching dimensions list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<DimensionsDO> fetchingDimensions() 
	{
		List<DimensionsDO> list_dimension = new ArrayList<DimensionsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			//dimensions
			list_dimension = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DIMENSIONS_LIST, list_dimension, List.class);
			String element_dimen = new Gson().toJson(list_dimension, new TypeToken<ArrayList<DimensionsDO>>() {}.getType());
			ObjectMapper mapper_dimen = new ObjectMapper();
			list_dimension = Arrays.asList(mapper_dimen.readValue(element_dimen, DimensionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_dimension;
	}
	
	/**
	 * Fetching Questions list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SurveyQuestionsDO> fetchingQuestionsList() 
	{
		List<SurveyQuestionsDO> ques_list = new ArrayList<SurveyQuestionsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			//Questions
			ques_list = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_QUESTIONS_LIST, ques_list, List.class);
			String element_ques = new Gson().toJson(ques_list, new TypeToken<ArrayList<SurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper_ques = new ObjectMapper();
			ques_list = Arrays.asList(mapper_ques.readValue(element_ques, SurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return ques_list;
	}
	
	/**
	 * fetching invite user record by mail id
	 * 
	 * 
	 * @param email_id
	 * @return
	 */
	public InviteUsersDO retriveInviteUserRecord(String email_id) 
	{
		InviteUsersDO do1 = new InviteUsersDO();
		try
		{
			do1.setsEmailId(email_id);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_INVITE_USER, do1, InviteUsersDO.class);
		} catch (Exception e) 
		{
			logger.info("error :", e.getMessage());
		}
		return do1;
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
	
	/**
	 * Logout.
	 *
	 * @param session
	 * @return string
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) 
	{
		HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
		session.invalidate();
		return "redirect:deimetrixcontrolpanel";
	}
	
	/**
	 * Logout.
	 *
	 * @param session
	 * @return string
	 */
	@RequestMapping(value = "/userlogout", method = RequestMethod.GET)
	public String userlogout(HttpSession session) 
	{
		HttpsURLConnection.setDefaultHostnameVerifier(new Util.NullHostnameVerifier());
		session.invalidate();
		return "redirect:login";
	}
}
