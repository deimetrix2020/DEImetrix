package com.java.service.Util;

public class ServiceRestURIConstants 
{
	/**Register start**/
	
	public static final String REGISTER_TRANS_USER = "/rest/trans_user/register_user";
	
	public static final String DELETE_TRANS_USER = "/rest/trans_user/delete_user";
	
	public static final String REGISTER_EMAIL_VERIFY_USER = "/rest/trans_user/email_verify_user";
	
	/**Register end**/

	/**Login checking start**/
	
	public static final String CHECK_ADMIN_LOGIN = "/rest/admin/check_admin_login";
	
	public static final String CHECK_TRANS_USER_LOGIN = "/rest/trans_user/check_trans_user_login";
	
	/**Login checking end**/
	
	/** Admin Users Start **/
	
	public static final String GET_SINGLE_ADMIN_USER = "/rest/admin/single_record";
	
	public static final String CREATE_UPDATE_ADMIN_USER = "/rest/admin/create_update";
	
	/** Admin Users End **/

	/** Trans Users Start **/

	public static final String FETCH_TRANS_USERS_LIST = "/rest/trans_user/trans_all_record";
	
	public static final String GET_TRANS_SINGLE_USER = "/rest/trans_user/trans_single_record";
	
	public static final String GET_SINGLE_TRANS_USER_BY_EMAIL = "/rest/trans_user/trans_single_record_by_email";
	
	public static final String CREATE_UPDATE_TRANS_USER = "/rest/trans_user/trans_create_update";
	
	/** Trans Users End **/
	
	/** Server Config Start **/
	
	public static final String GET_SINGLE_SERVER_CONFIG = "/rest/master/single_record";
	
	public static final String CREATE_UPDATE_SERVER_CONFIG = "/rest/master/create_update";
	
	public static final String CREATE_UPDATE_SERVER_CONFIG_MAINTAIN = "/rest/master/create_update_maintain";
	
	/** Server Config End **/
	
	/** Demographics Start **/
	
	public static final String GET_SINGLE_DEMOGRAPHICS = "/rest/demographics/single_record";
	
	public static final String GET_ALL_DEMOGRAPHICS_LIST = "/rest/demographics/all_record";
	
	public static final String CREATE_UPDATE_DEMOGRAPHICS = "/rest/demographics/create_update";
	
	public static final String DELETE_DEMOGRAPHICS = "/rest/demographics/delete_record";
	
	/** Demographics End **/
	
	/** Domain Start **/
	
	public static final String GET_SINGLE_DOMAIN = "/rest/domain/single_record";
	
	public static final String GET_SINGLE_DOMAIN_BY_NAME = "/rest/domain/single_record_by_name";
	
	public static final String GET_ALL_DOMAIN_LIST = "/rest/domain/all_record";
	
	public static final String CREATE_UPDATE_DOMAIN = "/rest/domain/create_update";
	
	public static final String DELETE_DOMAIN = "/rest/domain/delete_record";
	
	/** Domain End **/
	
	/** Dimension Start **/
	
	public static final String GET_SINGLE_DIMENSIONS = "/rest/dimension/single_record";
	
	public static final String GET_SINGLE_DIMENSIONS_BY_NAME = "/rest/dimension/single_record_by_name";
	
	public static final String GET_ALL_DIMENSIONS_LIST = "/rest/dimension/all_record";
	
	public static final String CREATE_UPDATE_DIMENSIONS = "/rest/dimension/create_update";
	
	public static final String DELETE_DIMENSIONS = "/rest/dimension/delete_record";
	
	/** Dimension End **/
	
	/** Questions Start **/
	
	public static final String GET_SINGLE_QUESTIONS = "/rest/questions/single_record";
	
	public static final String GET_SINGLE_QUESTION_BY_DETAILS = "/rest/questions/single_record_by_details";
	
	public static final String GET_ALL_QUESTIONS_LIST = "/rest/questions/all_record";
	
	public static final String CREATE_UPDATE_QUESTIONS = "/rest/questions/create_update";
	
	public static final String DELETE_QUESTIONS = "/rest/questions/delete_record";
	
	public static final String FETCHING_QUESTIONS_LIST_FOR_CALC = "/rest/questions/single_record_by_calc";
	
	/** Questions End **/
	
	/** Master Invite users Start **/
	
	public static final String CREATE_UPDATE_INVITE_USERS = "/rest/invite_user/create_update";
	
	public static final String GET_ALL_INVITE_USERS_LIST = "/rest/invite_user/all_record";
	
	public static final String GET_SINGLE_INVITE_USER = "/rest/invite_user/single_record";
	
	public static final String DELETE_INVITE_USER = "/rest/invite_user/delete_record";
	
	/** Master Invite users End **/
	
	/** Transaction survey questions Start **/
	
	public static final String GET_ALL_TRANS_DEMO_QUESTION_LIST = "/rest/trans_demo_ques/all_record";
	
	public static final String CREATE_UPDATE_TRANS_DEMO_QUESTION = "/rest/trans_demo_ques/create_update";
	
	public static final String DELETE_TRANS_QUES_DEMO = "/rest/trans_demo_ques/delete_record";
	
	public static final String FETCHING_TRANS_QUES_ANS_DEMO_REPORT = "/rest/trans_demo_ques/demo_ques_ans_report";
	
	/** Transaction survey questions End **/
	
	/** Transaction survey Init Start **/
	
	public static final String CREATE_UPDATE_TRANS_SURVEY_INIT = "/rest/trans_survey_init/create_update";
	
	public static final String UPDATE_TRANS_SURVEY_INIT = "/rest/trans_survey_init/update_status";

	public static final String GET_SINGLE_SERVER_INIT = "/rest/trans_survey_init/single_record";
	
	public static final String GET_ALL_SERVER_INIT_LIST = "/rest/trans_survey_init/fetching_all_survey_list";
	
	/** Transaction survey Init End **/
	
	/** Transaction survey question Start **/
	
	public static final String CREATE_UPDATE_TRANS_SURVEY_QUS_ANS = "/rest/trans_survey_question/create_update";
	
	public static final String FETCHING_ALL_TRANS_SURVEY_QUESTION = "/rest/trans_survey_question/all_record";
	
	public static final String FETCHING_ALL_TRANS_SURVEY_QUESTION_REPORT = "/rest/trans_survey_question/all_record_report";
	
	public static final String FETCHING_TRANS_SURVEY_BY_DOMAIN_NAME = "/rest/trans_survey_question/all_record_domain_name";
	
	public static final String FETCHING_TRANS_SURVEY_BY_DOMAIN_DIMENSION = "/rest/trans_survey_question/all_record_domain_dimnetsion";
	
	public static final String FETCHING_TRANS_QUES_ANS_SURVEY_REPORT = "/rest/trans_survey_question/survey_ques_ans_report";
	
	/** Transaction survey question end **/
	
	/** Trans calc value starts **/
	
	public static final String GET_SINGLE_SURVEY_CALC = "/rest/survey_calc/single_record_by_details";
	
	public static final String CREATE_UPDATE_TRANS_SURVEY_CALC = "/rest/survey_calc/create_update";
	
	public static final String FETCHING_ALL_CALC_VALUE = "/rest/survey_calc/all_record";

	public static final String FETCHING_ALL_CALC_VALUE_BY_TOTSL_VAL = "/rest/survey_calc/all_record_by_total";
}
