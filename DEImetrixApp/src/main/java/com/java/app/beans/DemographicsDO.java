package com.java.app.beans;

import java.util.Date;

public class DemographicsDO 
{
	
	private Integer id;
	
	private String sQuestions;
	
	private String sPrefredOptionsAns;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	private char cIsDeleted;
	
	private String sKey;
	
	public DemographicsDO() {}

	public DemographicsDO(Integer id) 
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
	 * @return the cIsDeleted
	 */
	public char getcIsDeleted() {
		return cIsDeleted;
	}

	/**
	 * @param cIsDeleted the cIsDeleted to set
	 */
	public void setcIsDeleted(char cIsDeleted) {
		this.cIsDeleted = cIsDeleted;
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
	 * @return the sPrefredOptionsAns
	 */
	public String getsPrefredOptionsAns() {
		return sPrefredOptionsAns;
	}

	/**
	 * @param sPrefredOptionsAns the sPrefredOptionsAns to set
	 */
	public void setsPrefredOptionsAns(String sPrefredOptionsAns) {
		this.sPrefredOptionsAns = sPrefredOptionsAns;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

}
