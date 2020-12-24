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

import com.java.service.bean.master.StatusDO;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
@Entity
@Table(name = "trans_survey_init")
public class TransSurvryInitDO implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prim_id", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="trans_user_id")
	private TransUserDetailsDO user_id;
	
	@Column(name = "first_name")
	@Size(max=100)
	private String sFirstName;
	
	@Column(name = "last_name")
	@Size(max=100)
	private String sLastName;
	
	@Column(name = "email_id")
	@Size(max=100)
	private String sEmailId;
	
	@Column(name = "version")
	private Integer iVersion;
	
	@Column(name = "draft_page_no")
	private Integer iDraftPageNo;
	
	@Column(name = "total_page_no")
	private Integer iTotalPageNo;
	
	@OneToOne
	@JoinColumn(name="status_id")
	private StatusDO status;
	
	@Column(name = "is_leadership")
	@NotNull
	private char cIsLeaderShip;
	
	@Column(name = "flag_timer")
	private Integer flag_timer;
	
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
	
	public TransSurvryInitDO() {}

	public TransSurvryInitDO(Integer id) 
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
	 * @return the flag_timer
	 */
	public Integer getFlag_timer() {
		return flag_timer;
	}

	/**
	 * @param flag_timer the flag_timer to set
	 */
	public void setFlag_timer(Integer flag_timer) {
		this.flag_timer = flag_timer;
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
	 * @return the iTotalPageNo
	 */
	public Integer getiTotalPageNo() {
		return iTotalPageNo;
	}

	/**
	 * @param iTotalPageNo the iTotalPageNo to set
	 */
	public void setiTotalPageNo(Integer iTotalPageNo) {
		this.iTotalPageNo = iTotalPageNo;
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
