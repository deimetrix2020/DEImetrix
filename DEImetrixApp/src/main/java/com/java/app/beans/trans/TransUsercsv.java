package com.java.app.beans.trans;

public class TransUsercsv
{
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String emailVerificationDate;
	
	private String emailVerification;
	
	private String active;
	
	private String createdBy;
	
	private String createdDate;
	
	private String modifiedBy;
	
	private String modifiedDate;
	
	private String deleted;
	
	public TransUsercsv() {}
	
	public TransUsercsv(String firstName, String lastName, String emailId, String emailVerificationDate, String emailVerification, String active, String createdBy, 
						String createdDate, String modifiedBy, String modifiedDate, String deleted) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.emailVerificationDate = emailVerificationDate;
		this.emailVerification = emailVerification;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.deleted = deleted;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the emailVerificationDate
	 */
	public String getEmailVerificationDate() {
		return emailVerificationDate;
	}

	/**
	 * @param emailVerificationDate the emailVerificationDate to set
	 */
	public void setEmailVerificationDate(String emailVerificationDate) {
		this.emailVerificationDate = emailVerificationDate;
	}

	/**
	 * @return the emailVerification
	 */
	public String getEmailVerification() {
		return emailVerification;
	}

	/**
	 * @param emailVerification the emailVerification to set
	 */
	public void setEmailVerification(String emailVerification) {
		this.emailVerification = emailVerification;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
