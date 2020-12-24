package com.java.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.app.beans.AdminLoginDO;
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
import com.java.app.beans.trans.TransUsercsv;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;

@Controller
@RequestMapping(value ="/trans_controller")
public class TranSurveyController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(TranSurveyController.class);
	
	@RequestMapping(value = "/fetching_all_list", method = RequestMethod.POST)
	public @ResponseBody List<TransUserDetailsDO> getAllUserList(HttpServletRequest request) 
	{
		List<TransUserDetailsDO> list_Obj = new ArrayList<TransUserDetailsDO>();
		try
		{
			list_Obj = fetchingTranUserList();
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createDemographRecord", method = RequestMethod.POST)
	public @ResponseBody Integer createRecord (HttpServletRequest request, HttpSession session)
	{
		TransSurvryInitDO survryInitDO = new TransSurvryInitDO();
		List<TransUserDemoQuestionsDO> demo_ques_list = new ArrayList<TransUserDemoQuestionsDO>();
		boolean bStatus = false;
		Integer survey_init_prim_id = null;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				return_key = 3;				
			} else
			{
				String radio_report_val = request.getParameter("report_val");
				String dynamic_ans = request.getParameter("dynamic_ans");
				Integer total_page = Integer.valueOf(request.getParameter("total_page"));
				Integer current_page = Integer.valueOf(request.getParameter("currentIndex_page"));
				
				TransUserDetailsDO session_trans_user = (TransUserDetailsDO) session.getAttribute(AppConstants.USER);
				SessionValues sessionValues = (SessionValues) session.getAttribute(AppConstants.COMMON);
				
				JSONArray jsonArray = new JSONArray(dynamic_ans); 
	
				RestTemplate restTemplate = new RestTemplate();
				
				if(null != sessionValues.getiSurveyInitPrimId())
				{
					survey_init_prim_id = sessionValues.getiSurveyInitPrimId();
					
					TransSurvryInitDO update_survryInitDO = fetchingTranSurveyInit(sessionValues.getiUserPrimId());
					update_survryInitDO.setiDraftPageNo(current_page);
					update_survryInitDO.setiTotalPageNo(total_page);
					update_survryInitDO.setcIsLeaderShip(radio_report_val.charAt(0));
					
					bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.UPDATE_TRANS_SURVEY_INIT, update_survryInitDO, Boolean.class);
				} else
				{
					// set the values
					survryInitDO.setiVersion(1);
					survryInitDO.setsEmailId(session_trans_user.getsEmailId());
					survryInitDO.setsFirstName(session_trans_user.getsFirstName());
					survryInitDO.setsLastName(session_trans_user.getsLastName());
					survryInitDO.setStatus(new StatusDO(2));
					survryInitDO.setiDraftPageNo(current_page);
					survryInitDO.setiTotalPageNo(total_page);
					survryInitDO.setsCreatedBy(session_trans_user.getsFirstName()+ " "+session_trans_user.getsLastName());
					survryInitDO.setcIsLeaderShip(radio_report_val.charAt(0));
					survryInitDO.setcIsDeleted('N');
					survryInitDO.setUser_id(new TransUserDetailsDO(session_trans_user.getId()));
					
					survey_init_prim_id = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_TRANS_SURVEY_INIT, survryInitDO, Integer.class);
				}
				
				if(null !=  survey_init_prim_id)
				{
					TransUserDemoQuestionsDO delete_exists = new TransUserDemoQuestionsDO();
					delete_exists.setInit_id(new TransSurvryInitDO(survey_init_prim_id));
					delete_exists.setUser_id(new TransUserDetailsDO(session_trans_user.getId()));
					
					bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_TRANS_QUES_DEMO, delete_exists, Boolean.class);
					
					for (int i = 0; i < jsonArray.length(); i++) 
					{
						JSONObject explrObject = jsonArray.getJSONObject(i);
						
						String questions_txt = explrObject.getString("question");
						String answer_txt = explrObject.getString("answer");
						String other_answer = explrObject.getString("other_specify");
						String demo_master_id = explrObject.getString("demo_master_id");
						
						TransUserDemoQuestionsDO demoQuestionsDO = new TransUserDemoQuestionsDO();
						demoQuestionsDO.setInit_id(new TransSurvryInitDO(survey_init_prim_id));
						demoQuestionsDO.setUser_id(new TransUserDetailsDO(session_trans_user.getId()));
						demoQuestionsDO.setsQuestions(questions_txt);
						demoQuestionsDO.setsAnswer(answer_txt);
						demoQuestionsDO.setiDemomasterId(Integer.valueOf(demo_master_id));
						demoQuestionsDO.setcIsLeaderShip(radio_report_val.charAt(0));
						if(answer_txt.equals("Others"))
						{
							demoQuestionsDO.setsOtherSpecify(other_answer);						
						}
						demoQuestionsDO.setsCreatedBy(session_trans_user.getsFirstName()+ " "+session_trans_user.getsLastName());
	
						demo_ques_list.add(demoQuestionsDO);
					}
					bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_TRANS_DEMO_QUESTION, demo_ques_list, Boolean.class);
				}
				if(bStatus)
				{
					List<InviteUsersDO> usersListObj = new ArrayList<InviteUsersDO>();
					InviteUsersDO usersDO = new InviteUsersDO();
					usersDO = retriveInviteUserRecord(session_trans_user.getsEmailId());
					if(usersDO.getId() != null)
					{
						usersDO.setcIsEmailStatus('Y');
						usersDO.setReg_status(new StatusDO(3));
						usersDO.setSurvey_status(new StatusDO(2));
						usersListObj.add(usersDO);
						bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, usersListObj, Boolean.class);
					}
					sessionValues.setiSurveyInitPrimId(survey_init_prim_id);
					sessionValues.setiUserPrimId(session_trans_user.getId());
					session.setAttribute(AppConstants.COMMON, sessionValues);
					logger.info("Trans survey demographics Inserted");
				}
				if(bStatus)
				{
					return_key = 1;
					logger.info("Trans survey demo inserted.");
				} else
				{
					return_key = 2;
				}
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}
	
	@RequestMapping(value = "/createUserSurveyRecord", method = RequestMethod.POST)
	public @ResponseBody Integer createSurveyRecord(HttpServletRequest request, HttpSession session)
	{
		List<TransSurveyQuestionsDO> survey_user_list = new ArrayList<TransSurveyQuestionsDO>();
		boolean bStatus = false;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				return_key = 3;				
			} else
			{
				String dynamic_ans = request.getParameter("survey_ans");
				
				TransUserDetailsDO session_trans_user = (TransUserDetailsDO) session.getAttribute(AppConstants.USER);
				
				SessionValues sessionValues = (SessionValues) session.getAttribute(AppConstants.COMMON);
				
				JSONArray jsonArray = new JSONArray(dynamic_ans);
				
				RestTemplate restTemplate = new RestTemplate();
				Integer survey_init_prim_id = sessionValues.getiSurveyInitPrimId();
				
				if(null !=  survey_init_prim_id)
				{
					TransSurveyQuestionsDO transSurveyQuestionsDO = new TransSurveyQuestionsDO();
					for (int i = 0; i < jsonArray.length(); i++) 
					{					
						JSONObject explrObject = jsonArray.getJSONObject(i);
						
						String questions_txt = explrObject.getString("survey_question");
						String answer_txt = explrObject.getString("selected_name");
						String answer_val = explrObject.getString("selected_section");
						String domain_txt = explrObject.getString("selected_domain_name");
						String dimension_txt = explrObject.getString("selected_dimension_name");
						String classname_txt = explrObject.getString("selected_class_name");
						
						String[] questions_txt_split = questions_txt.split("\\|");
						String ques_txt = questions_txt_split[0];
						String[] responce_txt = questions_txt_split[1].split(":");
						if(responce_txt[0].equals("res1"))
						{
							transSurveyQuestionsDO.setsResponse1(responce_txt[1]);
							transSurveyQuestionsDO.setcIsResponse1(questions_txt_split[2].charAt(0));
							transSurveyQuestionsDO.setsAnswerText1(answer_txt);
							transSurveyQuestionsDO.setsAsnwerMark1(Integer.valueOf(answer_val));
						} else if(responce_txt[0].equals("res2"))
						{
							transSurveyQuestionsDO.setsResponse2(responce_txt[1]);
							transSurveyQuestionsDO.setcIsResponse2(questions_txt_split[2].charAt(0));
							transSurveyQuestionsDO.setsAnswerText2(answer_txt);
							transSurveyQuestionsDO.setsAsnwerMark2(Integer.valueOf(answer_val));
						}
						
						transSurveyQuestionsDO.setInit_id(new TransSurvryInitDO(survey_init_prim_id));
						transSurveyQuestionsDO.setUser_id(new TransUserDetailsDO(session_trans_user.getId()));
						transSurveyQuestionsDO.setiDomainMasterId(Integer.valueOf(questions_txt_split[4]));
						transSurveyQuestionsDO.setiDimenMasterId(Integer.valueOf(questions_txt_split[5]));
						transSurveyQuestionsDO.setiQuestionMasterId(Integer.valueOf(questions_txt_split[6]));
						transSurveyQuestionsDO.setsQuestions(ques_txt);
						transSurveyQuestionsDO.setsDimensions(dimension_txt);
						transSurveyQuestionsDO.setsDomains(domain_txt);
						transSurveyQuestionsDO.setsEmailId(session_trans_user.getsEmailId());
						transSurveyQuestionsDO.setiVersion(1);
						transSurveyQuestionsDO.setsCreatedBy(session_trans_user.getsFirstName()+ " "+session_trans_user.getsLastName());
						transSurveyQuestionsDO.setcIsLeaderShip(questions_txt_split[3].charAt(0));
						transSurveyQuestionsDO.setsClassName(classname_txt);
						transSurveyQuestionsDO.setcIsDeleted('N');
					}
					survey_user_list.add(transSurveyQuestionsDO);
					bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_TRANS_SURVEY_QUS_ANS, survey_user_list, Boolean.class);
					if(bStatus)
					{
						TransSurvryInitDO survryInitDO = fetchingTranSurveyInit(session_trans_user.getId());
						/**************** Calcuation Starts *****************************************************/
						TransSurveyCalcDO surveyCalc_ins = new TransSurveyCalcDO();
						TransSurveyCalcDO fetching_survey_obj = new TransSurveyCalcDO();
						
						surveyCalc_ins.setInit_id(new TransSurvryInitDO(survey_init_prim_id));
						surveyCalc_ins.setUser_id(new TransUserDetailsDO(session_trans_user.getId()));
						surveyCalc_ins.setsDomainName(survey_user_list.get(0).getsDomains().trim());
						surveyCalc_ins.setsDimensionName(survey_user_list.get(0).getsDimensions().trim());
						fetching_survey_obj = fetchingSurveyObjVal(surveyCalc_ins);
						if(fetching_survey_obj.getId() != null)
						{
							survey_user_list.get(0).setiVersion(1);
							Integer total_val_exis_res1 = fetchingAllTransSurveyQuesListByDomain(survey_user_list.get(0));
							survey_user_list.get(0).setiVersion(2);
							Integer total_val_exis_res2 = fetchingAllTransSurveyQuesListByDomain(survey_user_list.get(0));
							Integer total_val_exis = total_val_exis_res1 + total_val_exis_res2;
							fetching_survey_obj.setdTotalDEIVal(Double.valueOf(total_val_exis));	
							//total value
							Double calc_val = fetching_survey_obj.getdTotalDEIVal() / Double.valueOf(fetching_survey_obj.getiTotalQuestion());
							fetching_survey_obj.setdTotalCalcVal(roundofval(calc_val, 2));
							Double calc_percent = (calc_val/3) * 100;
							fetching_survey_obj.setdTotalPercVal(roundofval(calc_percent, 2));
							
							bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_TRANS_SURVEY_CALC, fetching_survey_obj, Boolean.class);
						} else
						{
							List<SurveyQuestionsDO> master_question_list = new ArrayList<SurveyQuestionsDO>();
							//Fetching surveyquestion list
							SurveyQuestionsDO calc_survey_do = new SurveyQuestionsDO();
							calc_survey_do.setDomainsDO(new DomainsDO(survey_user_list.get(0).getiDomainMasterId()));
							calc_survey_do.setDimensionsDO(new DimensionsDO(survey_user_list.get(0).getiDimenMasterId()));
							if(survryInitDO.getcIsLeaderShip() == 'N')
							{
								calc_survey_do.setcIsLeaderShip(survey_user_list.get(0).getcIsLeaderShip());								
							} else
							{
								calc_survey_do.setcIsLeaderShip('A');
							}
							master_question_list = fetchingListforCalc(calc_survey_do);
							
							surveyCalc_ins.setiTotalQuestion(master_question_list.size());
							if(survey_user_list.get(0).getcIsResponse1() == 'Y')
							{
								surveyCalc_ins.setdTotalDEIVal(Double.valueOf(survey_user_list.get(0).getsAsnwerMark1()));						
							} else if(survey_user_list.get(0).getcIsResponse2() == 'Y')
							{
								surveyCalc_ins.setdTotalDEIVal(Double.valueOf(survey_user_list.get(0).getsAsnwerMark2()));
							}
							Double calc_val = surveyCalc_ins.getdTotalDEIVal() / Double.valueOf(master_question_list.size());
							surveyCalc_ins.setdTotalCalcVal(roundofval(calc_val, 2));
							Double calc_percent = (calc_val/3) * 100;
							surveyCalc_ins.setdTotalPercVal(roundofval(calc_percent, 2));
							bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_TRANS_SURVEY_CALC, surveyCalc_ins, Boolean.class);
						}
						/**************** Calcuation End *****************************************************/
						
						Integer total_page = Integer.valueOf(request.getParameter("total_page"));
						Integer current_page = Integer.valueOf(request.getParameter("currentIndex_page"));
						
						if(total_page == current_page)
						{
							survryInitDO.setiDraftPageNo(current_page);
							survryInitDO.setiTotalPageNo(total_page);
							survryInitDO.setStatus(new StatusDO(3));
							bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.UPDATE_TRANS_SURVEY_INIT, survryInitDO, Boolean.class);
							if(bStatus)
							{
								session_trans_user.setcIsBannedSurvey('Y');
								session_trans_user.setsKey("banned");
								bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_TRANS_USER, session_trans_user, Boolean.class);
							}
						} else
						{
							survryInitDO.setiDraftPageNo(current_page);
							survryInitDO.setiTotalPageNo(total_page);
							bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.UPDATE_TRANS_SURVEY_INIT, survryInitDO, Boolean.class);
						}
						
						List<InviteUsersDO> usersListObj = new ArrayList<InviteUsersDO>();
						InviteUsersDO usersDO = new InviteUsersDO();
						usersDO = retriveInviteUserRecord(session_trans_user.getsEmailId());
						if(usersDO.getId() != null)
						{
							usersDO.setcIsEmailStatus('Y');
							usersDO.setReg_status(new StatusDO(3));
							usersDO.setSurvey_status(new StatusDO(3));
							usersListObj.add(usersDO);
							bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_INVITE_USERS, usersListObj, Boolean.class);
						}
					}
				}
				if(bStatus)
				{
					return_key = 1;
					logger.info("Trans survey inserted.");
				} else
				{
					return_key = 2;
				}
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}

	/**
	 * Update a existing record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
	public @ResponseBody Integer deleteRecord(HttpServletRequest request, HttpSession session) 
	{
		TransUserDetailsDO detailsDO = new TransUserDetailsDO();
		boolean bStatus = false;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				Integer id = Integer.valueOf(request.getParameter("user_id"));
				String sKey = request.getParameter("key");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				detailsDO.setId(id);
				if(sKey.equals("active"))
				{
					detailsDO.setcIsActive('Y');
				} else if(sKey.equals("inactive"))
				{
					detailsDO.setcIsActive('N');
				} else if(sKey.equals("banned"))
				{
					detailsDO.setcIsBannedSurvey('Y');
				} else if(sKey.equals("unbanned"))
				{
					detailsDO.setcIsBannedSurvey('N');
				} else
				{
					detailsDO.setcIsDeleted('Y');				
				}
				detailsDO.setsUpdatedBy(session_admin.getsUserName());
				detailsDO.setsKey(sKey);
				bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_TRANS_USER, detailsDO, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("survey users Deleted");
				} else
				{
					return_key = 2;
				}
			} else
			{
				return_key = 3;
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}
	
	/**
	 * Update a existing record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/itext_sample_pdf", method = RequestMethod.POST)
	public void pdf_gen(HttpServletRequest request, HttpSession session, HttpServletResponse response) 
	{
		try
		{
			//URL creation
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
			
			String html_code = request.getParameter("html_code");
			
			String code_conv = "<html>"+html_code.replaceAll("resources/", base+"resources/")+"</html>";
			
			
			//HtmlConverter.convertToPdf(code_conv, new FileOutputStream("C:\\Users\\thulasiram\\Downloads\\sample\\string-to-pdf.pdf"));
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
	}
	
	/**
	 * Fetching trans survey init
	 * 
	 * @return
	 */
	private TransSurvryInitDO fetchingTranSurveyInit(Integer user_details_id)
	{
		TransSurvryInitDO survryInitDO = new TransSurvryInitDO();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			survryInitDO.setUser_id(new TransUserDetailsDO(user_details_id));
			survryInitDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_SERVER_INIT, survryInitDO, TransSurvryInitDO.class);
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return survryInitDO;
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
	
	@SuppressWarnings("unchecked")
	public List<SurveyQuestionsDO> fetchingListforCalc(SurveyQuestionsDO list_demo) 
	{
		List<SurveyQuestionsDO> covert_list = new ArrayList<SurveyQuestionsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			covert_list = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_QUESTIONS_LIST_FOR_CALC, list_demo, List.class);
			String element = new Gson().toJson(covert_list, new TypeToken<ArrayList<SurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			covert_list = Arrays.asList(mapper.readValue(element, SurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return covert_list;
	}
	
	private TransSurveyCalcDO fetchingSurveyObjVal(TransSurveyCalcDO surveyCalc_ins) 
	{
		TransSurveyCalcDO surveyCalcDO = new TransSurveyCalcDO();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			surveyCalcDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_SURVEY_CALC, surveyCalc_ins, TransSurveyCalcDO.class);
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return surveyCalcDO;
	}

	/**
	 * Fetching all tran survey questions list ny domain name dimension
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	public Integer fetchingAllTransSurveyQuesListByDomain(TransSurveyQuestionsDO surveyCalc_ins) 
	{
		Integer list_Obj = 0;
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_TRANS_SURVEY_BY_DOMAIN_DIMENSION, surveyCalc_ins, Integer.class);
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	public Double roundofval(double value, int places) 
	{
		Double round_val = 0.0; 
		try
		{
			if (places < 0) throw new IllegalArgumentException();
			
			long factor = (long) Math.pow(10, places);
			value = value * factor;
			long tmp = Math.round(value);
			round_val = (double) tmp / factor;
		}catch(Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return round_val;
	}
	
	@RequestMapping(value = "/exportallusers", method=RequestMethod.GET)
    public void exportallusers(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
		List<TransUsercsv> csv_user_obj = new ArrayList<TransUsercsv>();
 		List<TransUserDetailsDO> user_list_obj = new ArrayList<TransUserDetailsDO>();
 		Calendar cal = Calendar.getInstance();
 		StringBuffer csvFileName = null;
 		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
 		try
 		{
 			csvFileName = new StringBuffer(cal.get(Calendar.DATE)+""+(cal.get(Calendar.MONTH)+01));
 			csvFileName.append(cal.get(Calendar.YEAR)+""+cal.get(Calendar.HOUR));
 			csvFileName.append(cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND));
 			user_list_obj = fetchingTranUserList();
 			for(TransUserDetailsDO user_csv : user_list_obj)
 			{
 				TransUsercsv failedMstDO = new TransUsercsv();
 				failedMstDO.setFirstName(user_csv.getsFirstName());
 				failedMstDO.setLastName(user_csv.getsFirstName());
 				failedMstDO.setEmailId(user_csv.getsEmailId());
 				if(null != user_csv.getsEmailVerifiedAt())
 				{
 					failedMstDO.setEmailVerificationDate(dateFormat.format(user_csv.getsEmailVerifiedAt())); 					
 				} else
 				{
 					failedMstDO.setEmailVerificationDate("");
 				}
 				if(user_csv.getcIsEmailVerify() == 'Y')
 				{
 					failedMstDO.setEmailVerification("Yes");
 				} else
 				{
 					failedMstDO.setEmailVerification("No");
 				}
 				if(user_csv.getcIsActive() == 'Y')
 				{
 					failedMstDO.setActive("Yes");
 				} else
 				{
 					failedMstDO.setActive("No");
 				}
 				if(null != user_csv.getdCreatedDate())
 				{
 					failedMstDO.setCreatedDate(dateFormat.format(user_csv.getdCreatedDate())); 					
 				} else
 				{
 					failedMstDO.setCreatedDate("");
 				}
 				failedMstDO.setCreatedBy(user_csv.getsCreatedBy());
 				if(null != user_csv.getdUpdatedDate())
 				{
 					failedMstDO.setModifiedDate(dateFormat.format(user_csv.getdUpdatedDate())); 					
 				} else
 				{
 					failedMstDO.setModifiedDate("");
 				}
 				failedMstDO.setModifiedBy(user_csv.getsCreatedBy());
 				if(user_csv.getcIsDeleted() == 'Y')
 				{
 					failedMstDO.setDeleted("Yes");
 				} else
 				{
 					failedMstDO.setDeleted("No");
 				}
 				csv_user_obj.add(failedMstDO);
 			}
	        response.setContentType("text/csv");
	        // creates mock data
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", "Export_RegisteredUsersList_Dump-"+csvFileName+".csv");
	        response.setHeader(headerKey, headerValue);
	        // uses the Super CSV API to generate CSV data from the model data
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        // Prepare headers
	        String[] header = { "FirstName", "LastName", "EmailId", "EmailVerificationDate", "EmailVerification", "Active", "CreatedDate", "CreatedBy", "ModifiedDate", "ModifiedBy", "Deleted"};
	        csvWriter.writeHeader(header);
	        // Insert the values in CSV File
	        for (TransUsercsv prepareCSV : csv_user_obj) 
	        {
	            csvWriter.write(prepareCSV, header);
	        }
	        csvWriter.close();
 		} catch(Exception excp)
 		{
 			logger.error(excp.getMessage(), excp);
 		}
    }
	
	@RequestMapping(value = "/export_survey_dump", method=RequestMethod.GET)
    public ModelAndView exportDownload_goal(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
		ModelAndView andView = null;
		List<TransSurvryInitDO> survey_init_list = new ArrayList<TransSurvryInitDO>();
//		List<TransUserDemoQuestionsDO> trans_demo_list = new ArrayList<TransUserDemoQuestionsDO>();
		List<TransSurveyQuestionsDO> trans_survey_ques_list = new ArrayList<TransSurveyQuestionsDO>();
		Map<Integer,List<TransSurveyQuestionsDO>> total_survey_list = new HashMap<Integer,List<TransSurveyQuestionsDO>>();
		String first_name = "";
		String second_name = "";
		String trans_demo_list = "";
 		try
 		{
 			first_name = "survey_excel";
			second_name = "survey_dump_list";
			survey_init_list = fetchingAllSurveyInitList(3);
			for(TransSurvryInitDO survryInit : survey_init_list)
			{
				List<TransSurveyQuestionsDO> trans_survey_ques = new ArrayList<TransSurveyQuestionsDO>();
				//demographics
				trans_demo_list = fetchingAllTransDemoList(survryInit.getId(), survryInit.getUser_id().getId());
				//trans survey question
				trans_survey_ques_list = fetchingAllTransSurveyQuesList(survryInit.getId(), survryInit.getUser_id().getId());
				for(TransSurveyQuestionsDO surveyQuestions_trans : trans_survey_ques_list)
				{
					surveyQuestions_trans.setsDemoGraphicsValues(trans_demo_list);
					trans_survey_ques.add(surveyQuestions_trans);
				}
				total_survey_list.put(survryInit.getId(), trans_survey_ques);
			}
 			andView = new ModelAndView(first_name,second_name, total_survey_list);
 		} catch(Exception excp)
 		{
 			logger.error(excp.getMessage(), excp);
 		}
		return andView;
    }
	
	/**
	 * Fetching all tran survey list
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransSurvryInitDO> fetchingAllSurveyInitList(Integer status_id) 
	{
		List<TransSurvryInitDO> survey_init_list = new ArrayList<TransSurvryInitDO>();
		TransSurvryInitDO single_obj = new TransSurvryInitDO();
		try
		{
			single_obj.setStatus(new StatusDO(status_id));
			
			RestTemplate restTemplate = new RestTemplate();
			survey_init_list = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_SERVER_INIT_LIST, single_obj, List.class);
			String element = new Gson().toJson(survey_init_list, new TypeToken<ArrayList<TransUserDemoQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			survey_init_list = Arrays.asList(mapper.readValue(element, TransSurvryInitDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return survey_init_list;
	}
	
	
	/**
	 * Fetching all tran demo list
	 * @param integer2 
	 * @param init_id 
	 * 
	 * @return
	 */
	public String fetchingAllTransDemoList(Integer init_id, Integer user_id) 
	{
		String list_Obj = "";
		StringBuilder list_Obj_builder = new StringBuilder();
		TransUserDemoQuestionsDO transUserDemo = new TransUserDemoQuestionsDO();
		try
		{
			transUserDemo.setInit_id(new TransSurvryInitDO(init_id));
			transUserDemo.setUser_id(new TransUserDetailsDO(user_id));
			
			RestTemplate restTemplate = new RestTemplate();
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_TRANS_QUES_ANS_DEMO_REPORT, transUserDemo, String.class);
			list_Obj_builder.append(list_Obj);
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	/*@SuppressWarnings("unchecked")
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
	}*/
	
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
			list_Obj = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.FETCHING_ALL_TRANS_SURVEY_QUESTION_REPORT, transUserDemo, List.class);
			String element = new Gson().toJson(list_Obj, new TypeToken<ArrayList<TransSurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_Obj = Arrays.asList(mapper.readValue(element, TransSurveyQuestionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_Obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<TransUserDetailsDO> fetchingTranUserList()
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
}
