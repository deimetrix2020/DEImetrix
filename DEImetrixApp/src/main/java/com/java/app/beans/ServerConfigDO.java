package com.java.app.beans;

import java.util.Date;

public class ServerConfigDO 
{
	private Integer id;
	
	private String sFromMailId;
	
	private String sFromMailPassword;
	
	private String sSMTPHostName;
	
	private String sSMTPHostNumber;
	
	private String sSMTPAuth;
	
	private String sSMTPDebug;
	
	private String sSMTPStartTls;
	
	private String sSMTPMechanisms;
	
	private Integer iTimeout;
	
	private Date dMaintainStartDate;
	
	private Date dMaintainEndDate;
	
	private Integer iQuestions_Timeout;

	private Integer iTotalSurveyTime;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	public ServerConfigDO() {}

	public ServerConfigDO(Integer id) 
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
	 * @return the sFromMailId
	 */
	public String getsFromMailId() {
		return sFromMailId;
	}

	/**
	 * @param sFromMailId the sFromMailId to set
	 */
	public void setsFromMailId(String sFromMailId) {
		this.sFromMailId = sFromMailId;
	}

	/**
	 * @return the sFromMailPassword
	 */
	public String getsFromMailPassword() {
		return sFromMailPassword;
	}

	/**
	 * @param sFromMailPassword the sFromMailPassword to set
	 */
	public void setsFromMailPassword(String sFromMailPassword) {
		this.sFromMailPassword = sFromMailPassword;
	}

	/**
	 * @return the sSMTPHostName
	 */
	public String getsSMTPHostName() {
		return sSMTPHostName;
	}

	/**
	 * @param sSMTPHostName the sSMTPHostName to set
	 */
	public void setsSMTPHostName(String sSMTPHostName) {
		this.sSMTPHostName = sSMTPHostName;
	}

	/**
	 * @return the sSMTPHostNumber
	 */
	public String getsSMTPHostNumber() {
		return sSMTPHostNumber;
	}

	/**
	 * @param sSMTPHostNumber the sSMTPHostNumber to set
	 */
	public void setsSMTPHostNumber(String sSMTPHostNumber) {
		this.sSMTPHostNumber = sSMTPHostNumber;
	}

	/**
	 * @return the sSMTPAuth
	 */
	public String getsSMTPAuth() {
		return sSMTPAuth;
	}

	/**
	 * @param sSMTPAuth the sSMTPAuth to set
	 */
	public void setsSMTPAuth(String sSMTPAuth) {
		this.sSMTPAuth = sSMTPAuth;
	}

	/**
	 * @return the sSMTPDebug
	 */
	public String getsSMTPDebug() {
		return sSMTPDebug;
	}

	/**
	 * @param sSMTPDebug the sSMTPDebug to set
	 */
	public void setsSMTPDebug(String sSMTPDebug) {
		this.sSMTPDebug = sSMTPDebug;
	}

	/**
	 * @return the sSMTPStartTls
	 */
	public String getsSMTPStartTls() {
		return sSMTPStartTls;
	}

	/**
	 * @param sSMTPStartTls the sSMTPStartTls to set
	 */
	public void setsSMTPStartTls(String sSMTPStartTls) {
		this.sSMTPStartTls = sSMTPStartTls;
	}

	/**
	 * @return the sSMTPMechanisms
	 */
	public String getsSMTPMechanisms() {
		return sSMTPMechanisms;
	}

	/**
	 * @param sSMTPMechanisms the sSMTPMechanisms to set
	 */
	public void setsSMTPMechanisms(String sSMTPMechanisms) {
		this.sSMTPMechanisms = sSMTPMechanisms;
	}

	/**
	 * @return the iTimeout
	 */
	public Integer getiTimeout() {
		return iTimeout;
	}

	/**
	 * @param iTimeout the iTimeout to set
	 */
	public void setiTimeout(Integer iTimeout) {
		this.iTimeout = iTimeout;
	}

	/**
	 * @return the dMaintainStartDate
	 */
	public Date getdMaintainStartDate() {
		return dMaintainStartDate;
	}

	/**
	 * @param dMaintainStartDate the dMaintainStartDate to set
	 */
	public void setdMaintainStartDate(Date dMaintainStartDate) {
		this.dMaintainStartDate = dMaintainStartDate;
	}

	/**
	 * @return the dMaintainEndDate
	 */
	public Date getdMaintainEndDate() {
		return dMaintainEndDate;
	}

	/**
	 * @param dMaintainEndDate the dMaintainEndDate to set
	 */
	public void setdMaintainEndDate(Date dMaintainEndDate) {
		this.dMaintainEndDate = dMaintainEndDate;
	}

	/**
	 * @return the iQuestions_Timeout
	 */
	public Integer getiQuestions_Timeout() {
		return iQuestions_Timeout;
	}

	/**
	 * @param iQuestions_Timeout the iQuestions_Timeout to set
	 */
	public void setiQuestions_Timeout(Integer iQuestions_Timeout) {
		this.iQuestions_Timeout = iQuestions_Timeout;
	}

	/**
	 * @return the iTotalSurveyTime
	 */
	public Integer getiTotalSurveyTime() {
		return iTotalSurveyTime;
	}

	/**
	 * @param iTotalSurveyTime the iTotalSurveyTime to set
	 */
	public void setiTotalSurveyTime(Integer iTotalSurveyTime) {
		this.iTotalSurveyTime = iTotalSurveyTime;
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
}
