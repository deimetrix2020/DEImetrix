package com.java.service.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
@Entity
@Table(name = "trans_survey_entries_by_user")
public class TransSurveyQuestionsDO implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prim_id", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="trans_init_id")
	private TransSurvryInitDO init_id;
	
	@OneToOne
	@JoinColumn(name="trans_user_id")
	private TransUserDetailsDO user_id;
	
	@Column(name = "question_master_id")
	private Integer iQuestionMasterId;
	
	@Column(name = "domain_master_id")
	private Integer iDomainMasterId;
	
	@Column(name = "dimen_master_id")
	private Integer iDimenMasterId;
	
	@Column(name = "domains")
	@Size(max=300)
	private String sDomains;
	
	@Column(name = "dimensions")
	@Size(max=500)
	private String sDimensions;
	
	@Column(name = "questions", columnDefinition="TEXT")
	private String sQuestions;
	
	@Column(name = "response1", columnDefinition="TEXT")
	private String sResponse1;
	
	@Column(name = "is_positive_response1")
	@NotNull
	private char cIsResponse1;
	
	@Column(name = "response2", columnDefinition="TEXT")
	private String sResponse2;
	
	@Column(name = "is_positive_response2")
	@NotNull
	private char cIsResponse2;
	
	@Column(name = "answer_txt1", columnDefinition="TEXT")
	private String sAnswerText1;
	
	@Column(name = "answer_mark1")
	private Integer sAsnwerMark1;
	
	@Column(name = "answer_txt2", columnDefinition="TEXT")
	private String sAnswerText2;
	
	@Column(name = "answer_mark2")
	private Integer sAsnwerMark2;
	
	@Column(name = "email_id")
	@Size(max=100)
	private String sEmailId;
	
	@Column(name = "version")
	private Integer iVersion;
	
	@Column(name = "is_leader_ship")
	@NotNull
	private char cIsLeaderShip;
	
	@Column(name = "class_name")
	@Size(max=100)
	private String sClassName;

	@Column(name = "draft_pageno")
	private Integer iDraftPageNo;
	
	@Column(name = "created_by")
	@Size(max=100)
	private String sCreatedBy;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dCreatedDate;
	
	@Column(name = "updated_by")
	@Size(max=100)
	private String sUpdatedBy;
	
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dUpdatedDate;
	
	@Column(name = "is_deleted")
	@NotNull
	private char cIsDeleted;
	
	@Transient
	private String sDemoGraphicsValues;
	
	public TransSurveyQuestionsDO() {}

	public TransSurveyQuestionsDO(Integer id) 
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
	 * @return the iQuestionMasterId
	 */
	public Integer getiQuestionMasterId() {
		return iQuestionMasterId;
	}

	/**
	 * @param iQuestionMasterId the iQuestionMasterId to set
	 */
	public void setiQuestionMasterId(Integer iQuestionMasterId) {
		this.iQuestionMasterId = iQuestionMasterId;
	}

	/**
	 * @return the iDomainMasterId
	 */
	public Integer getiDomainMasterId() {
		return iDomainMasterId;
	}

	/**
	 * @param iDomainMasterId the iDomainMasterId to set
	 */
	public void setiDomainMasterId(Integer iDomainMasterId) {
		this.iDomainMasterId = iDomainMasterId;
	}

	/**
	 * @return the iDimenMasterId
	 */
	public Integer getiDimenMasterId() {
		return iDimenMasterId;
	}

	/**
	 * @param iDimenMasterId the iDimenMasterId to set
	 */
	public void setiDimenMasterId(Integer iDimenMasterId) {
		this.iDimenMasterId = iDimenMasterId;
	}

	/**
	 * @return the sDomains
	 */
	public String getsDomains() {
		return sDomains;
	}

	/**
	 * @param sDomains the sDomains to set
	 */
	public void setsDomains(String sDomains) {
		this.sDomains = sDomains;
	}

	/**
	 * @return the sDimensions
	 */
	public String getsDimensions() {
		return sDimensions;
	}

	/**
	 * @param sDimensions the sDimensions to set
	 */
	public void setsDimensions(String sDimensions) {
		this.sDimensions = sDimensions;
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
	 * @return the sAnswerText1
	 */
	public String getsAnswerText1() {
		return sAnswerText1;
	}

	/**
	 * @param sAnswerText1 the sAnswerText1 to set
	 */
	public void setsAnswerText1(String sAnswerText1) {
		this.sAnswerText1 = sAnswerText1;
	}

	/**
	 * @return the sAsnwerMark1
	 */
	public Integer getsAsnwerMark1() {
		return sAsnwerMark1;
	}

	/**
	 * @param sAsnwerMark1 the sAsnwerMark1 to set
	 */
	public void setsAsnwerMark1(Integer sAsnwerMark1) {
		this.sAsnwerMark1 = sAsnwerMark1;
	}

	/**
	 * @return the sAnswerText2
	 */
	public String getsAnswerText2() {
		return sAnswerText2;
	}

	/**
	 * @param sAnswerText2 the sAnswerText2 to set
	 */
	public void setsAnswerText2(String sAnswerText2) {
		this.sAnswerText2 = sAnswerText2;
	}

	/**
	 * @return the sAsnwerMark2
	 */
	public Integer getsAsnwerMark2() {
		return sAsnwerMark2;
	}

	/**
	 * @param sAsnwerMark2 the sAsnwerMark2 to set
	 */
	public void setsAsnwerMark2(Integer sAsnwerMark2) {
		this.sAsnwerMark2 = sAsnwerMark2;
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
	 * @return the iVersion
	 */
	public Integer getiVersion() {
		return iVersion;
	}

	/**
	 * @param iVersion the iVersion to set
	 */
	public void setiVersion(Integer iVersion) {
		this.iVersion = iVersion;
	}

	/**
	 * @return the sClassName
	 */
	public String getsClassName() {
		return sClassName;
	}

	/**
	 * @param sClassName the sClassName to set
	 */
	public void setsClassName(String sClassName) {
		this.sClassName = sClassName;
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
	 * @return the iDraftPageNo
	 */
	public Integer getiDraftPageNo() {
		return iDraftPageNo;
	}

	/**
	 * @param iDraftPageNo the iDraftPageNo to set
	 */
	public void setiDraftPageNo(Integer iDraftPageNo) {
		this.iDraftPageNo = iDraftPageNo;
	}

	/**
	 * @return the sDemoGraphicsValues
	 */
	public String getsDemoGraphicsValues() {
		return sDemoGraphicsValues;
	}

	/**
	 * @param sDemoGraphicsValues the sDemoGraphicsValues to set
	 */
	public void setsDemoGraphicsValues(String sDemoGraphicsValues) {
		this.sDemoGraphicsValues = sDemoGraphicsValues;
	}
}
