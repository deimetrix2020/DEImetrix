package com.java.service.services.transaction;

import java.util.List;

import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDemoQuestionsDO;

public interface TransDemoService
{
	/**
	 * Demo Questions List
	 * @param create_do
	 * @return
	 */
	public List<TransUserDemoQuestionsDO> fetchDemoQuestionsList(Integer user_id, Integer init_id);

	/**
	 * Create DemoQuestion
	 * @param demoQuestionsDO
	 * @return
	 */
	public boolean createDemoQuestionRecord(TransUserDemoQuestionsDO demoQuestionsDO);

	/**
	 * Delete demo questions
	 * @param create_do
	 * @return
	 */
	public boolean deleteDemoQuestionRecord(TransUserDemoQuestionsDO create_do);

	/**
	 * Survey Init record creation
	 * @param create_do
	 * @return
	 */
	public Integer createSurveyInitRecord(TransSurvryInitDO create_do);

	/**
	 * Update survey init record
	 * 
	 * @param create_do
	 * @return
	 */
	public boolean updateSurveyInitRecord(TransSurvryInitDO create_do);

	public String fetchTransDemoDetailsReport(TransUserDemoQuestionsDO surveyCalc_obj);

	public List<TransSurvryInitDO> fetchTransSurveyAllList(TransSurvryInitDO single_user);

}
