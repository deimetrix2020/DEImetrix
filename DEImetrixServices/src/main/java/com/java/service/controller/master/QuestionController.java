package com.java.service.controller.master;

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
import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDetailsDO;
import com.java.service.bean.master.StatusDO;
import com.java.service.bean.master.SurveyQuestionsDO;
import com.java.service.services.masters.QuestionService;
import com.java.service.services.transaction.TransDemoService;
import com.java.service.services.transaction.TransSurveyEntryService;
import com.java.service.services.transaction.TransUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TransSurveyEntryService surveyEntryService;
	
	@Autowired
	private TransDemoService transDemoService;
	
	@Autowired
	private TransUserService transUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_QUESTIONS, method = RequestMethod.POST)
	public @ResponseBody SurveyQuestionsDO getquest(@RequestBody SurveyQuestionsDO single_quest) 
	{
		SurveyQuestionsDO SurveyQuestionsDO = new SurveyQuestionsDO();
		try
		{
			logger.info("Start fetching single quest ID="+single_quest.getId());
			SurveyQuestionsDO = questionService.fetchForUpdate(single_quest.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single quest : " + excp.getMessage());
		}
		return SurveyQuestionsDO;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_QUESTIONS_LIST, method = RequestMethod.POST)
	public @ResponseBody List<SurveyQuestionsDO> getAllEmployees() 
	{
		logger.info("Start feching all quest.");
		List<SurveyQuestionsDO> obj_list = new ArrayList<SurveyQuestionsDO>();
		try
		{
			obj_list = questionService.fetchList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching all quest list : " + excp.getMessage());
		}
		return obj_list;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_QUESTION_BY_DETAILS, method = RequestMethod.POST)
	public @ResponseBody SurveyQuestionsDO getQuestiionByDetails(@RequestBody SurveyQuestionsDO questionsObj) 
	{
		SurveyQuestionsDO single_obj = new SurveyQuestionsDO();
		try
		{
			single_obj = questionService.fetchForUpdateByDetail(questionsObj);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single quest : " + excp.getMessage());
		}
		return single_obj;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_QUESTIONS, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody SurveyQuestionsDO create_do) 
	{
		boolean status = false;
		List<TransSurvryInitDO> initList = new ArrayList<TransSurvryInitDO>();
		List<TransUserDetailsDO> userList = new ArrayList<TransUserDetailsDO>();
		try
		{
			logger.info("Start create quest.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					SurveyQuestionsDO surveyQuestionsDO = questionService.fetchForUpdate(create_do.getId());
					surveyQuestionsDO.setDomainsDO(create_do.getDomainsDO());
					surveyQuestionsDO.setDimensionsDO(create_do.getDimensionsDO());
					surveyQuestionsDO.setsQuestions(create_do.getsQuestions());
					surveyQuestionsDO.setsResponse1(create_do.getsResponse1());
					surveyQuestionsDO.setcIsResponse1(create_do.getcIsResponse1());
					surveyQuestionsDO.setsResponse2(create_do.getsResponse2());
					surveyQuestionsDO.setcIsResponse2(create_do.getcIsResponse2());
					surveyQuestionsDO.setcIsLeaderShip(create_do.getcIsLeaderShip());
					surveyQuestionsDO.setcIsActive(create_do.getcIsActive());
					surveyQuestionsDO.setcIsDeleted(create_do.getcIsDeleted());
					surveyQuestionsDO.setdUpdatedDate(new Date());
					surveyQuestionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
					status = questionService.updateRecord(surveyQuestionsDO);
				} else
				{
					create_do.setdCreatedDate(new Date());
					status = questionService.createRecord(create_do);
				}
			}
			if(status)
			{
				initList = surveyEntryService.fetchSurveyInitList();
				for(TransSurvryInitDO initDO : initList)
				{
					initDO.setStatus(new StatusDO(6));
					transDemoService.updateSurveyInitRecord(initDO);
				}
				userList = transUserService.fetchAllUsersList();
				for(TransUserDetailsDO userDetailsDO : userList)
				{
					userDetailsDO.setcIsBannedSurvey('N');
					transUserService.updateRecord(userDetailsDO);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update quest : " + excp.getMessage());
		}
		return status;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.DELETE_QUESTIONS, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody SurveyQuestionsDO create_do) 
	{
		boolean status = false;
		List<TransSurvryInitDO> initList = new ArrayList<TransSurvryInitDO>();
		try
		{
			logger.info("Start delete quest.");
			if(create_do.getsKey().equals("soft"))
			{
				SurveyQuestionsDO surveyQuestionsDO = questionService.fetchForUpdate(create_do.getId());
				surveyQuestionsDO.setcIsDeleted('Y');
				surveyQuestionsDO.setdUpdatedDate(new Date());
				surveyQuestionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = questionService.updateRecord(surveyQuestionsDO);
			} else if(create_do.getsKey().equals("active"))
			{
				SurveyQuestionsDO surveyQuestionsDO = questionService.fetchForUpdate(create_do.getId());
				surveyQuestionsDO.setcIsActive('Y');
				surveyQuestionsDO.setdUpdatedDate(new Date());
				surveyQuestionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = questionService.updateRecord(surveyQuestionsDO);
			} else if(create_do.getsKey().equals("inactive"))
			{
				SurveyQuestionsDO surveyQuestionsDO = questionService.fetchForUpdate(create_do.getId());
				surveyQuestionsDO.setcIsActive('N');
				surveyQuestionsDO.setdUpdatedDate(new Date());
				surveyQuestionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = questionService.updateRecord(surveyQuestionsDO);
			} else
			{
				status = questionService.deleteRecord(create_do);
			}
			if(status)
			{
				initList = surveyEntryService.fetchSurveyInitList();
				for(TransSurvryInitDO initDO : initList)
				{
					initDO.setStatus(new StatusDO(6));
					transDemoService.updateSurveyInitRecord(initDO);
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete quest : " + excp.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.FETCHING_QUESTIONS_LIST_FOR_CALC, method = RequestMethod.POST)
	public @ResponseBody List<SurveyQuestionsDO> fetchinglistforcalc(@RequestBody SurveyQuestionsDO create_do) 
	{
		logger.info("Start feching all quest.");
		List<SurveyQuestionsDO> obj_list = new ArrayList<SurveyQuestionsDO>();
		try
		{
			obj_list = questionService.fetchForUpdateByQuestion(create_do);
		} catch (Exception excp) 
		{
			logger.error("Error fetching all quest list : " + excp.getMessage());
		}
		return obj_list;
	}
}
