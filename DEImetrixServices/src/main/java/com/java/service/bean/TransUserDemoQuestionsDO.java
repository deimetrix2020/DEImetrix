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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
@Entity
@Table(name = "trans_demo_questions_answer")
public class TransUserDemoQuestionsDO implements java.io.Serializable 
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
	
	@Column(name = "demo_master_id")
	private Integer iDemomasterId;
	
	@Column(name = "questions", columnDefinition="TEXT")
	private String sQuestions;
	
	@Column(name = "answer")
	@Size(max=300)
	private String sAnswer;
	
	@Column(name = "other_specify", columnDefinition="TEXT")
	private String sOtherSpecify;
	
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
	
	@Column(name = "is_leadership")
	@NotNull
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
