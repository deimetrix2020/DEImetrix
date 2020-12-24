package com.java.app.beans.trans;

import java.util.Date;

public class TransUserDemoQuestionsDO 
{
	private Integer id;
	
	private TransSurvryInitDO init_id;
	
	private TransUserDetailsDO user_id;
	
	private Integer iDemomasterId;
	
	private String sQuestions;
	
	private String sAnswer;
	
	private String sOtherSpecify;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	private char cIsLeaderShip;
	
	public TransUserDemoQuestionsDO() {}

	public TransUserDemoQuestionsDO(Integer id) 
	{
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the sQuestions
	 */
	public String getsQuestions() {
		return sQuestions;
	}

	/**
	 * @param sQuestions the sQuestions to set
	 */
	public void setsQuestions(String sQuestions) {
		this.sQuestions = sQuestions;
	}

	/**
	 * @return the sAnswer
	 */
	public String getsAnswer() {
		return sAnswer;
	}

	/**
	 * @param sAnswer the sAnswer to set
	 */
	public void setsAnswer(String sAnswer) {
		this.sAnswer = sAnswer;
	}

	/**
	 * @return the sOtherSpecify
	 */
	public String getsOtherSpecify() {
		return sOtherSpecify;
	}

	/**
	 * @param sOtherSpecify the sOtherSpecify to set
	 */
	public void setsOtherSpecify(String sOtherSpecify) {
		this.sOtherSpecify = sOtherSpecify;
	}

	/**
	 * @return the init_id
	 */
	public TransSurvryInitDO getInit_id() {
		return init_id;
	}

	/**
	 * @param init_id the init_id to set
	 */
	public void setInit_id(TransSurvryInitDO init_id) {
		this.init_id = init_id;
	}

	/**
	 * @return the user_id
	 */
	public TransUserDetailsDO getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(TransUserDetailsDO user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the iDemomasterId
	 */
	public Integer getiDemomasterId() {
		return iDemomasterId;
	}

	/**
	 * @param iDemomasterId the iDemomasterId to set
	 */
	public void setiDemomasterId(Integer iDemomasterId) {
		this.iDemomasterId = iDemomasterId;
	}

	/**
	 * @return the sCreatedBy
	 */
	public String getsCreatedBy() {
		return sCreatedBy;
	}

	/**
	 * @param sCreatedBy the sCreatedBy to set
	 */
	public void setsCreatedBy(String sCreatedBy) {
		this.sCreatedBy = sCreatedBy;
	}

	/**
	 * @return the dCreatedDate
	 */
	public Date getdCreatedDate() {
		return dCreatedDate;
	}

	/**
	 * @param dCreatedDate the dCreatedDate to set
	 */
	public void setdCreatedDate(Date dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	/**
	 * @return the sUpdatedBy
	 */
	public String getsUpdatedBy() {
		return sUpdatedBy;
	}

	/**
	 * @param sUpdatedBy the sUpdatedBy to set
	 */
	public void setsUpdatedBy(String sUpdatedBy) {
		this.sUpdatedBy = sUpdatedBy;
	}

	/**
	 * @return the dUpdatedDate
	 */
	public Date getdUpdatedDate() {
		return dUpdatedDate;
	}

	/**
	 * @param dUpdatedDate the dUpdatedDate to set
	 */
	public void setdUpdatedDate(Date dUpdatedDate) {
		this.dUpdatedDate = dUpdatedDate;
	}

	/**
	 * @return the cIsLeaderShip
	 */
	public char getcIsLeaderShip() {
		return cIsLeaderShip;
	}

	/**
	 * @param cIsLeaderShip the cIsLeaderShip to set
	 */
	public void setcIsLeaderShip(char cIsLeaderShip) {
		this.cIsLeaderShip = cIsLeaderShip;
	}
}
