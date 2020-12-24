package com.java.app.beans;

public class InviteUsersCSVDO
{
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	public InviteUsersCSVDO() {}

	public InviteUsersCSVDO(String firstName, String lastName, String emailId) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
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
	
}
