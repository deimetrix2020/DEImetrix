package com.java.app.beans;

import java.util.Date;

/**
 * Table with column name declare for status master using hibernate.
 */
public class StatusDO 
{
	private Integer id;
	
	private String sStatusName;
	
	private String sCreatedBy;
	
	private Date dCreatedDate;
	
	private String sUpdatedBy;
	
	private Date dUpdatedDate;
	
	private char cIsActive;
	
	public StatusDO() {}

	public StatusDO(Integer id) 
	{
		this.id = id;
	}
	
	public StatusDO(Integer id, String sStatusName) 
	{
		this.id = id;
		this.sStatusName = sStatusName;
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
	 * @return the sStatusName
	 */
	public String getsStatusName() {
		return sStatusName;
	}

	/**
	 * @param sStatusName the sStatusName to set
	 */
	public void setsStatusName(String sStatusName) {
		this.sStatusName = sStatusName;
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
}
