package com.java.service.bean.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Table with column name declare for User master using hibernate.
 */
@Entity
@Table(name = "master_audit_log")
public class AuditLogDO 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prim_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "login_type")
	private String slogin_type;
	
	@Column(name = "module")
	private String sModule;
	
	@Column(name = "opreation")
	private String sOpreation;
	
	@Lob
	@Column(name = "old_data")
	private String sOldData;
	
	@Lob
	@Column(name = "new_data")
	private String sNewData;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dCreatedDate;
	
	@Column(name = "created_emp_no")
	@Size(max=20)
	private String sCreatedEmpNo;
	
	public AuditLogDO() { };
	
	public AuditLogDO(Integer id) 
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
	 * @return the slogin_type
	 */
	public String getSlogin_type() {
		return slogin_type;
	}

	/**
	 * @return the sModule
	 */
	public String getsModule() {
		return sModule;
	}

	/**
	 * @return the sOpreation
	 */
	public String getsOpreation() {
		return sOpreation;
	}

	/**
	 * @return the sOldData
	 */
	public String getsOldData() {
		return sOldData;
	}

	/**
	 * @return the sNewData
	 */
	public String getsNewData() {
		return sNewData;
	}

	/**
	 * @return the dCreatedDate
	 */
	public Date getdCreatedDate() {
		return dCreatedDate;
	}

	/**
	 * @return the sCreatedEmpNo
	 */
	public String getsCreatedEmpNo() {
		return sCreatedEmpNo;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param slogin_type the slogin_type to set
	 */
	public void setSlogin_type(String slogin_type) {
		this.slogin_type = slogin_type;
	}

	/**
	 * @param sModule the sModule to set
	 */
	public void setsModule(String sModule) {
		this.sModule = sModule;
	}

	/**
	 * @param sOpreation the sOpreation to set
	 */
	public void setsOpreation(String sOpreation) {
		this.sOpreation = sOpreation;
	}

	/**
	 * @param sOldData the sOldData to set
	 */
	public void setsOldData(String sOldData) {
		this.sOldData = sOldData;
	}

	/**
	 * @param sNewData the sNewData to set
	 */
	public void setsNewData(String sNewData) {
		this.sNewData = sNewData;
	}

	/**
	 * @param dCreatedDate the dCreatedDate to set
	 */
	public void setdCreatedDate(Date dCreatedDate) {
		this.dCreatedDate = dCreatedDate;
	}

	/**
	 * @param sCreatedEmpNo the sCreatedEmpNo to set
	 */
	public void setsCreatedEmpNo(String sCreatedEmpNo) {
		this.sCreatedEmpNo = sCreatedEmpNo;
	}	
}

