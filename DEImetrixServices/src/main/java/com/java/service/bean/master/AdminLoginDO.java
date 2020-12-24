package com.java.service.bean.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Table with column name declare for User master using hibernate.
 */
@Entity
@Table(name = "master_admin_login")
public class AdminLoginDO implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "user_name")
	@Size(max=150)
	private String sUserName;
	
	@Column(name = "password")
	@Size(max=150)
	private String sPassword;
	
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
	
	public AdminLoginDO() {}

	public AdminLoginDO(Integer id) 
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
	 * @return the sUserName
	 */
	public String getsUserName() {
		return sUserName;
	}

	/**
	 * @param sUserName the sUserName to set
	 */
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}

	/**
	 * @return the sPassword
	 */
	public String getsPassword() {
		return sPassword;
	}

	/**
	 * @param sPassword the sPassword to set
	 */
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
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
