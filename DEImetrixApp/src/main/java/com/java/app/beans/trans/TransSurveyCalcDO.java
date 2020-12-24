package com.java.app.beans.trans;

import java.util.Date;

public class TransSurveyCalcDO
{
	private Integer id;
	
	private TransSurvryInitDO init_id;
	
	private TransUserDetailsDO user_id;
	
	private String sDomainName;
	
	private String sDimensionName;
	
	private Double dTotalDEIVal;
	
	private Integer iTotalQuestion;
	
	private Double dTotalCalcVal;
	
	private Double dTotalPercVal;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;

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
	 * @return the sDomainName
	 */
	public String getsDomainName() {
		return sDomainName;
	}

	/**
	 * @param sDomainName the sDomainName to set
	 */
	public void setsDomainName(String sDomainName) {
		this.sDomainName = sDomainName;
	}

	/**
	 * @return the sDimensionName
	 */
	public String getsDimensionName() {
		return sDimensionName;
	}

	/**
	 * @param sDimensionName the sDimensionName to set
	 */
	public void setsDimensionName(String sDimensionName) {
		this.sDimensionName = sDimensionName;
	}

	/**
	 * @return the dTotalCalcVal
	 */
	public Double getdTotalCalcVal() {
		return dTotalCalcVal;
	}

	/**
	 * @param dTotalCalcVal the dTotalCalcVal to set
	 */
	public void setdTotalCalcVal(Double dTotalCalcVal) {
		this.dTotalCalcVal = dTotalCalcVal;
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
	 * @return the dTotalDEIVal
	 */
	public Double getdTotalDEIVal() {
		return dTotalDEIVal;
	}

	/**
	 * @param dTotalDEIVal the dTotalDEIVal to set
	 */
	public void setdTotalDEIVal(Double dTotalDEIVal) {
		this.dTotalDEIVal = dTotalDEIVal;
	}

	/**
	 * @return the dTotalPercVal
	 */
	public Double getdTotalPercVal() {
		return dTotalPercVal;
	}

	/**
	 * @param dTotalPercVal the dTotalPercVal to set
	 */
	public void setdTotalPercVal(Double dTotalPercVal) {
		this.dTotalPercVal = dTotalPercVal;
	}

	/**
	 * @return the iTotalQuestion
	 */
	public Integer getiTotalQuestion() {
		return iTotalQuestion;
	}

	/**
	 * @param iTotalQuestion the iTotalQuestion to set
	 */
	public void setiTotalQuestion(Integer iTotalQuestion) {
		this.iTotalQuestion = iTotalQuestion;
	}
}
