package com.java.service.controller.trans;

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
import com.java.service.bean.TransSurveyCalcDO;
import com.java.service.bean.TransSurveyQuestionsDO;
import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDemoQuestionsDO;
import com.java.service.services.transaction.TransSurveyEntryService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TransSurveyController {
	
	@Autowired
	private TransSurveyEntryService surveyEntryService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransSurveyController.class);
	

	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_TRANS_SURVEY_QUS_ANS, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateDemoQuestion(@RequestBody List<TransSurveyQuestionsDO> create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create quest.");
			String element = new Gson().toJson(create_do, new TypeToken<ArrayList<TransSurveyQuestionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			create_do = Arrays.asList(mapper.readValue(element, TransSurveyQuestionsDO[].class));
			for(TransSurveyQuestionsDO demoQuestionsDO : create_do)
			{
				TransSurveyQuestionsDO update_do = new TransSurveyQuestionsDO();
				update_do = surveyEntryService.fetchSurveyQuestion(demoQuestionsDO);
				if(update_do.getId() != null)
				{
					update_do.setsAnswerText1(demoQuestionsDO.getsAnswerText1());
					update_do.setcIsResponse1(demoQuestionsDO.getcIsResponse1());
					update_do.setsAsnwerMark1(demoQuestionsDO.getsAsnwerMark1());
					update_do.setsAnswerText2(demoQuestionsDO.getsAnswerText2());
					update_do.setcIsResponse2(demoQuestionsDO.getcIsResponse2());
					update_do.setsAsnwerMark2(demoQuestionsDO.getsAsnwerMark2());
					
					update_do.setdUpdatedDate(new Date());
					status = surveyEntryService.updateRecord(update_do);
				} else
				{
					demoQuestionsDO.setdCreatedDate(new Date());
					status = surveyEntryService.createRecord(demoQuestionsDO);					
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_ALL_TRANS_SURVEY_QUESTION, method = RequestMethod.POST)
	public @ResponseBody List<TransSurveyQuestionsDO> fetchingAllSurveyQues(@RequestBody TransSurveyQuestionsDO single_user) 
	{
		List<TransSurveyQuestionsDO> initDO = new ArrayList<TransSurveyQuestionsDO>();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyList(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_ALL_TRANS_SURVEY_QUESTION_REPORT, method = RequestMethod.POST)
	public @ResponseBody List<TransSurveyQuestionsDO> fetchingAllSurveyQuesReport(@RequestBody TransSurveyQuestionsDO single_user) 
	{
		List<TransSurveyQuestionsDO> initDO = new ArrayList<TransSurveyQuestionsDO>();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyListReport(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_TRANS_SURVEY_BY_DOMAIN_NAME, method = RequestMethod.POST)
	public @ResponseBody List<String> fetchingTransSurveyByDomainName(@RequestBody TransSurveyQuestionsDO single_user) 
	{
		List<String> initDO = new ArrayList<String>();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyListByDomainName(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_TRANS_SURVEY_BY_DOMAIN_DIMENSION, method = RequestMethod.POST)
	public @ResponseBody Integer fetchTransSurveyListByDomainDimensions(@RequestBody TransSurveyQuestionsDO single_user) 
	{
		Integer initDO = 0;
		try
		{
			Long initDO_int = surveyEntryService.fetchTransSurveyListByDomainDimensions(single_user);
			initDO = initDO_int.intValue();
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_SERVER_INIT, method = RequestMethod.POST)
	public @ResponseBody TransSurvryInitDO getSingleServerInit(@RequestBody TransSurvryInitDO single_user) 
	{
		TransSurvryInitDO initDO = new TransSurvryInitDO();
		try
		{
			logger.info("Start fetching single trans user ID="+single_user);
			initDO = surveyEntryService.fetchForSurveyInit(single_user);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_SURVEY_CALC, method = RequestMethod.POST)
	public @ResponseBody TransSurveyCalcDO getSingleSuveyInit(@RequestBody TransSurveyCalcDO single_survey_calc) 
	{
		TransSurveyCalcDO single_obj = new TransSurveyCalcDO();
		try
		{
			single_obj = surveyEntryService.fetchForSurveyCalc(single_survey_calc);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return single_obj;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_TRANS_SURVEY_CALC, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateSurveyCalc(@RequestBody TransSurveyCalcDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start create.");
			if(create_do.getId() != null)
			{
				create_do.setdUpdatedDate(new Date());
				status = surveyEntryService.updateRecordSurveyCalc(create_do);
			} else
			{
				create_do.setdCreatedDate(new Date());
				status = surveyEntryService.createRecordSurveyCalc(create_do);					
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_ALL_CALC_VALUE, method = RequestMethod.POST)
	public @ResponseBody List<TransSurveyCalcDO> fetchTransSurveyList(@RequestBody TransSurveyCalcDO surveyCalc_obj) 
	{
		List<TransSurveyCalcDO> initDO = new ArrayList<TransSurveyCalcDO>();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyCalcList(surveyCalc_obj);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_ALL_CALC_VALUE_BY_TOTSL_VAL, method = RequestMethod.POST)
	public @ResponseBody List<TransSurveyCalcDO> fetchTransSurveyListByTotalVal(@RequestBody TransSurveyCalcDO surveyCalc_obj) 
	{
		List<TransSurveyCalcDO> initDO = new ArrayList<TransSurveyCalcDO>();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyCalcListByTotalVal(surveyCalc_obj);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_TRANS_QUES_ANS_SURVEY_REPORT, method = RequestMethod.POST)
	public @ResponseBody StringBuilder fetchTransSurveyListReport(@RequestBody TransUserDemoQuestionsDO surveyCalc_obj) 
	{
		StringBuilder initDO = new StringBuilder();
		try
		{
			initDO = surveyEntryService.fetchTransSurveyDetailsReport(surveyCalc_obj);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single trans server init : " + excp.getMessage());
		}
		return initDO;
	}
}
