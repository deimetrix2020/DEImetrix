package com.java.app.beans;

/**
 * Table with column name declare for Overhead and profit master using hibernate.
 */
public class SurveyCSVQuestionsDO
{
	private Integer id;
	
	private String domain;
	
	private String dimension;
	
	private String questions;
	
	private String response1;
	
	private String DEIQOriented1;
	
	private String response2;
	
	private String DEIQOriented2;
	
	private String questionforLeader;
	
	private String active;
	
	private String deleted;
	
	public SurveyCSVQuestionsDO() {}

	public SurveyCSVQuestionsDO(Integer id) 
	{
		this.id = id;
	}
	
	public SurveyCSVQuestionsDO(String domain, String dimension, String questions, String response1, 
			String DEIQOriented1, String response2, String DEIQOriented2, String questionforLeader, String active, String deleted)
	{
		this.domain = domain;
		this.dimension = dimension;
		this.questions = questions;
		this.response1 = response1;
		this.DEIQOriented1 = DEIQOriented1;
		this.response2 = response2;
		this.DEIQOriented2 = DEIQOriented2;
		this.questionforLeader = questionforLeader;
		this.active = active;
		this.deleted = deleted;
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
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the questions
	 */
	public String getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(String questions) {
		this.questions = questions;
	}

	/**
	 * @return the response1
	 */
	public String getResponse1() {
		return response1;
	}

	/**
	 * @param response1 the response1 to set
	 */
	public void setResponse1(String response1) {
		this.response1 = response1;
	}

	/**
	 * @return the response2
	 */
	public String getResponse2() {
		return response2;
	}

	/**
	 * @param response2 the response2 to set
	 */
	public void setResponse2(String response2) {
		this.response2 = response2;
	}

	/**
	 * @return the dEIQOriented1
	 */
	public String getDEIQOriented1() {
		return DEIQOriented1;
	}

	/**
	 * @param dEIQOriented1 the dEIQOriented1 to set
	 */
	public void setDEIQOriented1(String dEIQOriented1) {
		DEIQOriented1 = dEIQOriented1;
	}

	/**
	 * @return the dEIQOriented2
	 */
	public String getDEIQOriented2() {
		return DEIQOriented2;
	}

	/**
	 * @param dEIQOriented2 the dEIQOriented2 to set
	 */
	public void setDEIQOriented2(String dEIQOriented2) {
		DEIQOriented2 = dEIQOriented2;
	}

	/**
	 * @return the questionforLeader
	 */
	public String getQuestionforLeader() {
		return questionforLeader;
	}

	/**
	 * @param questionforLeader the questionforLeader to set
	 */
	public void setQuestionforLeader(String questionforLeader) {
		this.questionforLeader = questionforLeader;
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
