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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
@Entity
@Table(name = "master_organization")
public class OrganizationDO implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prim_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "unique_org_id")
	@Size(max=30)
	private String sUniqueOrgId;
	
	@Column(name = "org_industry_type")
	@Size(max=100)
	private String sOrgIndustryType;
	
	@Column(name = "organization_full_name")
	@Size(max=300)
	private String sOrgFullName;
	
	@Column(name = "org_support_email_id")
	@Size(max=100)
	private String sOrgSupportEmailId;
	
	@Column(name = "org_location")
	@Size(max=100)
	private String sLocation;
	
	@Column(name = "org_contact_no")
	@Size(max=20)
	private String sOrgContactNo;
	
	@Column(name = "org_website_url", columnDefinition="TEXT")
	private String sOrgWebSiteUrl;
	
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
	
	public OrganizationDO() {}

	public OrganizationDO(Integer id) 
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
	 * @return the sUniqueOrgId
	 */
	public String getsUniqueOrgId() {
		return sUniqueOrgId;
	}

	/**
	 * @param sUniqueOrgId the sUniqueOrgId to set
	 */
	public void setsUniqueOrgId(String sUniqueOrgId) {
		this.sUniqueOrgId = sUniqueOrgId;
	}

	/**
	 * @return the sOrgFullName
	 */
	public String getsOrgFullName() {
		return sOrgFullName;
	}

	/**
	 * @param sOrgFullName the sOrgFullName to set
	 */
	public void setsOrgFullName(String sOrgFullName) {
		this.sOrgFullName = sOrgFullName;
	}

	/**
	 * @return the sOrgSupportEmailId
	 */
	public String getsOrgSupportEmailId() {
		return sOrgSupportEmailId;
	}

	/**
	 * @param sOrgSupportEmailId the sOrgSupportEmailId to set
	 */
	public void setsOrgSupportEmailId(String sOrgSupportEmailId) {
		this.sOrgSupportEmailId = sOrgSupportEmailId;
	}

	/**
	 * @return the sLocation
	 */
	public String getsLocation() {
		return sLocation;
	}

	/**
	 * @param sLocation the sLocation to set
	 */
	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}

	/**
	 * @return the sOrgContactNo
	 */
	public String getsOrgContactNo() {
		return sOrgContactNo;
	}

	/**
	 * @param sOrgContactNo the sOrgContactNo to set
	 */
	public void setsOrgContactNo(String sOrgContactNo) {
		this.sOrgContactNo = sOrgContactNo;
	}

	/**
	 * @return the sOrgWebSiteUrl
	 */
	public String getsOrgWebSiteUrl() {
		return sOrgWebSiteUrl;
	}

	/**
	 * @param sOrgWebSiteUrl the sOrgWebSiteUrl to set
	 */
	public void setsOrgWebSiteUrl(String sOrgWebSiteUrl) {
		this.sOrgWebSiteUrl = sOrgWebSiteUrl;
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
	 * @return the sOrgIndustryType
	 */
	public String getsOrgIndustryType() {
		return sOrgIndustryType;
	}

	/**
	 * @param sOrgIndustryType the sOrgIndustryType to set
	 */
	public void setsOrgIndustryType(String sOrgIndustryType) {
		this.sOrgIndustryType = sOrgIndustryType;
	}
}
