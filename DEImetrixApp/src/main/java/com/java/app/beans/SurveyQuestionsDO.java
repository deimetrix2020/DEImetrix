package com.java.app.beans;

import java.util.Date;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
public class SurveyQuestionsDO
{
	private Integer id;
	
	private DomainsDO domainsDO;
	
	private DimensionsDO dimensionsDO;
	
	private String sQuestions;
	
	private String sResponse1;
	
	private char cIsResponse1;
	
	private String sResponse2;
	
	private char cIsResponse2;
	
	private char cIsLeaderShip;
	
	private char cIsActive;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	private char cIsDeleted;
	
	private String sKey;
	
	private String sDomain;
	
	private String sDimension;
	
	public SurveyQuestionsDO() {}

	public SurveyQuestionsDO(Integer id) 
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
	 * @return the domainsDO
	 */
	public DomainsDO getDomainsDO() {
		return domainsDO;
	}

	/**
	 * @param domainsDO the domainsDO to set
	 */
	public void setDomainsDO(DomainsDO domainsDO) {
		this.domainsDO = domainsDO;
	}

	/**
	 * @return the dimensionsDO
	 */
	public DimensionsDO getDimensionsDO() {
		return dimensionsDO;
	}

	/**
	 * @param dimensionsDO the dimensionsDO to set
	 */
	public void setDimensionsDO(DimensionsDO dimensionsDO) {
		this.dimensionsDO = dimensionsDO;
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
	 * @return the sResponse1
	 */
	public String getsResponse1() {
		return sResponse1;
	}

	/**
	 * @param sResponse1 the sResponse1 to set
	 */
	public void setsResponse1(String sResponse1) {
		this.sResponse1 = sResponse1;
	}

	/**
	 * @return the cIsResponse1
	 */
	public char getcIsResponse1() {
		return cIsResponse1;
	}

	/**
	 * @param cIsResponse1 the cIsResponse1 to set
	 */
	public void setcIsResponse1(char cIsResponse1) {
		this.cIsResponse1 = cIsResponse1;
	}

	/**
	 * @return the sResponse2
	 */
	public String getsResponse2() {
		return sResponse2;
	}

	/**
	 * @param sResponse2 the sResponse2 to set
	 */
	public void setsResponse2(String sResponse2) {
		this.sResponse2 = sResponse2;
	}

	/**
	 * @return the cIsResponse2
	 */
	public char getcIsResponse2() {
		return cIsResponse2;
	}

	/**
	 * @param cIsResponse2 the cIsResponse2 to set
	 */
	public void setcIsResponse2(char cIsResponse2) {
		this.cIsResponse2 = cIsResponse2;
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

	/**
	 * @return the sDomain
	 */
	public String getsDomain() {
		return sDomain;
	}

	/**
	 * @param sDomain the sDomain to set
	 */
	public void setsDomain(String sDomain) {
		this.sDomain = sDomain;
	}

	/**
	 * @return the sDimension
	 */
	public String getsDimension() {
		return sDimension;
	}

	/**
	 * @param sDimension the sDimension to set
	 */
	public void setsDimension(String sDimension) {
		this.sDimension = sDimension;
	}
}
