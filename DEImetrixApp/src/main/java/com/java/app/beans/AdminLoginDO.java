package com.java.app.beans;

import java.util.Date;

/**
 * Table with column name declare for User master using hibernate.
 */
public class AdminLoginDO 
{
	private Integer id;
	
	private String sUserName;
	
	private String sPassword;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
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
