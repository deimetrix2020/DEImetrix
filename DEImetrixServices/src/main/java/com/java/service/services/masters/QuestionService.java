package com.java.service.services.masters;

import java.util.List;

import com.java.service.bean.master.SurveyQuestionsDO;
import com.java.service.bean.master.AdminLoginDO;

public interface QuestionService
{
	/**
	 * Fetch Records
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<SurveyQuestionsDO> fetchList() throws Exception;
	
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(SurveyQuestionsDO questObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(SurveyQuestionsDO questObj) throws Exception;

	/**
	 * Delete record
	 * @param questObj
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRecord(SurveyQuestionsDO questObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public SurveyQuestionsDO fetchForUpdate(Integer id) throws Exception;

	/**
	 * Fetching record by Details
	 * 
	 * @param questionsObj
	 * @return
	 */
	public SurveyQuestionsDO fetchForUpdateByDetail(SurveyQuestionsDO questionsObj);

	/**
	 * Fetching list for calc
	 * 
	 * @param questionsObj
	 * @return
	 */
	public List<SurveyQuestionsDO> fetchForUpdateByQuestion(SurveyQuestionsDO questionsObj);
}
