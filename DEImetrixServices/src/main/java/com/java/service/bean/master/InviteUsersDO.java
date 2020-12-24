package com.java.service.bean.master;

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

import com.java.service.bean.master.StatusDO;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
@Entity
@Table(name = "master_invite_users")
public class InviteUsersDO implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prim_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "first_name")
	@Size(max=500)
	private String sFirstName;
	
	@Column(name = "last_name")
	@Size(max=500)
	private String sLastName;
	
	@Column(name = "email_id")
	@Size(max=100)
	private String sEmailId;
	
	@Column(name = "email_sent_status")
	@NotNull
	private char cIsEmailStatus;
	
	@OneToOne
	@JoinColumn(name="reg_status_id")
	private StatusDO reg_status;
	
	@OneToOne
	@JoinColumn(name="survey_status_id")
	private StatusDO survey_status;
	
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
	private String sKey;
	
	public InviteUsersDO() {}

	public InviteUsersDO(Integer id) 
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
	 * @return the cIsEmailStatus
	 */
	public char getcIsEmailStatus() {
		return cIsEmailStatus;
	}

	/**
	 * @param cIsEmailStatus the cIsEmailStatus to set
	 */
	public void setcIsEmailStatus(char cIsEmailStatus) {
		this.cIsEmailStatus = cIsEmailStatus;
	}

	/**
	 * @return the reg_status
	 */
	public StatusDO getReg_status() {
		return reg_status;
	}

	/**
	 * @param reg_status the reg_status to set
	 */
	public void setReg_status(StatusDO reg_status) {
		this.reg_status = reg_status;
	}

	/**
	 * @return the survey_status
	 */
	public StatusDO getSurvey_status() {
		return survey_status;
	}

	/**
	 * @param survey_status the survey_status to set
	 */
	public void setSurvey_status(StatusDO survey_status) {
		this.survey_status = survey_status;
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
