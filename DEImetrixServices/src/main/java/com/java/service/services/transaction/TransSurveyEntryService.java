package com.java.service.services.transaction;

import java.util.List;

import com.java.service.bean.TransSurveyCalcDO;
import com.java.service.bean.TransSurveyQuestionsDO;
import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDemoQuestionsDO;

public interface TransSurveyEntryService
{
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(TransSurveyQuestionsDO demoObj) throws Exception;

	/**
	 * Fetching existing details
	 * 
	 * @param id
	 * @return
	 */
	public TransSurveyQuestionsDO fetchSurveyQuestion(TransSurveyQuestionsDO questionsDO);
	
	/**
	 * Fetching Survey init by user id
	 * 
	 * @param single_user
	 * @return
	 */
	public TransSurvryInitDO fetchForSurveyInit(TransSurvryInitDO single_user);

	/**
	 * Feching all survey init list
	 * 
	 * @return
	 */
	public List<TransSurvryInitDO> fetchSurveyInitList();

	/**
	 * Update transquestion details
	 * 
	 * @param domainObj
	 * @return
	 * @throws Exception
	 */
	public boolean updateRecord(TransSurveyQuestionsDO domainObj) throws Exception;

	/**
	 * Fetching all survey questuon list
	 * 
	 * @param single_user
	 * @return
	 */
	public List<TransSurveyQuestionsDO> fetchTransSurveyList(TransSurveyQuestionsDO single_user);

	/**
	 * Fetching all survey questuon list by domain name
	 * 
	 * @param questionsDO
	 * @return
	 */
	public List<String> fetchTransSurveyListByDomainName(TransSurveyQuestionsDO questionsDO);

	/**
	 * Fetching all survey questuon list by domain name and dimension
	 * 
	 * @param questionsDO
	 * @return
	 */
	public Long fetchTransSurveyListByDomainDimensions(TransSurveyQuestionsDO questionsDO);

	/**
	 * fetching for suvey calc
	 * 
	 * @param single_survey_calc
	 * @return
	 */
	public TransSurveyCalcDO fetchForSurveyCalc(TransSurveyCalcDO single_survey_calc);

	/**
	 * update for suvey calc
	 * 
	 * @param single_survey_calc
	 * @return
	 */
	public boolean updateRecordSurveyCalc(TransSurveyCalcDO create_do);

	/**
	 * create for suvey calc
	 * 
	 * @param single_survey_calc
	 * @return
	 */
	public boolean createRecordSurveyCalc(TransSurveyCalcDO create_do);

	/**
	 * survey Calc list
	 * 
	 * @param surveyCalc_obj
	 * @return
	 */
	public List<TransSurveyCalcDO> fetchTransSurveyCalcList(TransSurveyCalcDO surveyCalc_obj);

	/**
	 * survey Calc list by total val
	 * 
	 * @param surveyCalc_obj
	 * @return
	 */
	public List<TransSurveyCalcDO> fetchTransSurveyCalcListByTotalVal(TransSurveyCalcDO surveyCalc_obj);

	public StringBuilder fetchTransSurveyDetailsReport(TransUserDemoQuestionsDO surveyCalc_obj);

	public List<TransSurveyQuestionsDO> fetchTransSurveyListReport(TransSurveyQuestionsDO questionsDO);
	
}
