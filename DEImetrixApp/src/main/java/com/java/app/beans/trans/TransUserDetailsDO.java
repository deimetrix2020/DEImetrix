package com.java.app.beans.trans;

import java.util.Date;

import com.java.app.beans.StatusDO;

public class TransUserDetailsDO
{
	private Integer id;
	
	private String sOrgId;
	
	private String sFirstName;
	
	private String sLastName;
	
	private String sEmailId;
	
	private Date sEmailVerifiedAt;
	
	private char cIsEmailVerify;
	
	private Integer iTimeout;
	
	private StatusDO status;
	
	private char cIsActive;
	
	private char cIsBannedSurvey;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	private char cIsDeleted;
	
	private String sKey;
	
	public TransUserDetailsDO() {}

	public TransUserDetailsDO(Integer id) 
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
	 * @return the sOrgId
	 */
	public String getsOrgId() {
		return sOrgId;
	}

	/**
	 * @param sOrgId the sOrgId to set
	 */
	public void setsOrgId(String sOrgId) {
		this.sOrgId = sOrgId;
	}

	/**
	 * @return the sFirstName
	 */
	public String getsFirstName() {
		return sFirstName;
	}

	/**
	 * @param sFirstName the sFirstName to set
	 */
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	/**
	 * @return the sLastName
	 */
	public String getsLastName() {
		return sLastName;
	}

	/**
	 * @param sLastName the sLastName to set
	 */
	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	/**
	 * @return the sEmailId
	 */
	public String getsEmailId() {
		return sEmailId;
	}

	/**
	 * @param sEmailId the sEmailId to set
	 */
	public void setsEmailId(String sEmailId) {
		this.sEmailId = sEmailId;
	}

	/**
	 * @return the sEmailVerifiedAt
	 */
	public Date getsEmailVerifiedAt() {
		return sEmailVerifiedAt;
	}

	/**
	 * @param sEmailVerifiedAt the sEmailVerifiedAt to set
	 */
	public void setsEmailVerifiedAt(Date sEmailVerifiedAt) {
		this.sEmailVerifiedAt = sEmailVerifiedAt;
	}

	/**
	 * @return the cIsEmailVerify
	 */
	public char getcIsEmailVerify() {
		return cIsEmailVerify;
	}

	/**
	 * @param cIsEmailVerify the cIsEmailVerify to set
	 */
	public void setcIsEmailVerify(char cIsEmailVerify) {
		this.cIsEmailVerify = cIsEmailVerify;
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
	 * @return the status
	 */
	public StatusDO getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusDO status) {
		this.status = status;
	}

	/**
	 * @return the cIsActive
	 */
	public char getcIsActive() {
		return cIsActive;
	}

	/**
	 * @param cIsActive the cIsActive to set
	 */
	public void setcIsActive(char cIsActive) {
		this.cIsActive = cIsActive;
	}

	/**
	 * @return the cIsBannedSurvey
	 */
	public char getcIsBannedSurvey() {
		return cIsBannedSurvey;
	}

	/**
	 * @param cIsBannedSurvey the cIsBannedSurvey to set
	 */
	public void setcIsBannedSurvey(char cIsBannedSurvey) {
		this.cIsBannedSurvey = cIsBannedSurvey;
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
	 * @return the sKey
	 */
	public String getsKey() {
		return sKey;
	}

	/**
	 * @param sKey the sKey to set
	 */
	public void setsKey(String sKey) {
		this.sKey = sKey;
	}
}
