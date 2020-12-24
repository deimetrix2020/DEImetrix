package com.java.service.services.transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.TransSurveyCalcDO;
import com.java.service.bean.TransSurveyQuestionsDO;
import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDemoQuestionsDO;
import com.java.service.bean.TransUserDetailsDO;
import com.java.service.bean.master.StatusDO;

@Service
@Transactional
public class TransSurveyEntryServiceImpl implements TransSurveyEntryService
{
	/** logger */
	private static final Logger logger = Logger.getLogger(TransSurveyEntryServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean createRecord(TransSurveyQuestionsDO domainObj) throws Exception 
	{
		logger.info("Adding New domain");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(domainObj);
			transaction.commit();
		} catch (Exception excp)  
		{
			bStatus = false;
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return bStatus;
	}
	
	@Override
	public boolean updateRecord(TransSurveyQuestionsDO domainObj) throws Exception 
	{
		logger.info("Adding New domain");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(domainObj);
			transaction.commit();
		} catch (Exception excp)  
		{
			bStatus = false;
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return bStatus;
	}

	@Override
	public TransSurvryInitDO fetchForSurveyInit(TransSurvryInitDO survryInitDO) 
	{
		logger.info("Retrieving Single Survey INit Details");
		Session session = null;
		Transaction transaction;
		TransSurvryInitDO single_obj = new TransSurvryInitDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurvryInitDO.class);
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(survryInitDO.getUser_id().getId())));
			if(survryInitDO.getStatus() != null)
			{
				criteria.add(Restrictions.eq("status", new StatusDO(survryInitDO.getStatus().getId())));
				criteria.addOrder(Order.asc("id"));
			} else
			{
				criteria.add(Restrictions.eq("status", new StatusDO(2)));
			}
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				single_obj = (TransSurvryInitDO) iterator.next();
			}
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TransSurvryInitDO> fetchSurveyInitList()
	{
		logger.info("Retrieving all dimen list");
		Session session = null;
		Transaction transaction;
		List<TransSurvryInitDO> listObj = new ArrayList<TransSurvryInitDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurvryInitDO.class);
			criteria.add(Restrictions.eq("status", new StatusDO(2)));
			listObj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  listObj;
	}

	@Override
	public TransSurveyQuestionsDO fetchSurveyQuestion(TransSurveyQuestionsDO questionsDO) 
	{
		logger.info("Retrieving Single Survey question Details");
		Session session = null;
		Transaction transaction;
		TransSurveyQuestionsDO single_obj = new TransSurveyQuestionsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyQuestionsDO.class);
			criteria.add(Restrictions.eq("iQuestionMasterId", questionsDO.getiQuestionMasterId()));
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(questionsDO.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(questionsDO.getUser_id().getId())));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				single_obj = (TransSurveyQuestionsDO) iterator.next();
			}
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransSurveyQuestionsDO> fetchTransSurveyList(TransSurveyQuestionsDO questionsDO) 
	{
		logger.info("Retrieving Single Survey question Details");
		Session session = null;
		Transaction transaction;
		List<TransSurveyQuestionsDO> single_obj = new ArrayList<TransSurveyQuestionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyQuestionsDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(questionsDO.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(questionsDO.getUser_id().getId())));
			single_obj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TransSurveyQuestionsDO> fetchTransSurveyListReport(TransSurveyQuestionsDO questionsDO) 
	{
		logger.info("Retrieving Single Survey question Details");
		Session session = null;
		Transaction transaction;
		List<TransSurveyQuestionsDO> single_obj = new ArrayList<TransSurveyQuestionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyQuestionsDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(questionsDO.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(questionsDO.getUser_id().getId())));
			criteria.addOrder(Order.asc("iQuestionMasterId"));
			criteria.addOrder(Order.asc("cIsLeaderShip"));
			single_obj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchTransSurveyListByDomainName(TransSurveyQuestionsDO questionsDO) 
	{
		logger.info("Retrieving Single Survey question Details");
		Session session = null;
		Transaction transaction;
		List<String> single_obj = new ArrayList<String>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyQuestionsDO.class);
			criteria.setProjection(Projections.distinct(Projections.property("sDimensions")));
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(questionsDO.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(questionsDO.getUser_id().getId())));
			criteria.add(Expression.eq("sDomains", questionsDO.getsDomains().trim()).ignoreCase());
			single_obj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}
	
	@Override
	public Long fetchTransSurveyListByDomainDimensions(TransSurveyQuestionsDO questionsDO) 
	{
		logger.info("Retrieving Single Survey question Details");
		Session session = null;
		Transaction transaction;
		Long single_obj = null;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyQuestionsDO.class);
			if(questionsDO.getiVersion() == 1)
			{
				criteria.setProjection(Projections.sum("sAsnwerMark1"));
				criteria.add(Restrictions.eq("cIsResponse1", 'Y'));
			} else if(questionsDO.getiVersion() == 2)
			{
				criteria.setProjection(Projections.sum("sAsnwerMark2"));
				criteria.add(Restrictions.eq("cIsResponse2", 'Y'));
			}
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(questionsDO.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(questionsDO.getUser_id().getId())));
			criteria.add(Restrictions.eq("sDomains", questionsDO.getsDomains()));
			criteria.add(Restrictions.eq("sDimensions", questionsDO.getsDimensions()));
			List total_val = criteria.list();
			if(!total_val.isEmpty())
			{
				if(null != total_val.get(0))
				{
					single_obj = (Long) total_val.get(0);
				}
			}
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}

	@Override
	public TransSurveyCalcDO fetchForSurveyCalc(TransSurveyCalcDO single_survey_calc) 
	{
		Session session = null;
		Transaction transaction;
		TransSurveyCalcDO single_obj = new TransSurveyCalcDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyCalcDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(single_survey_calc.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(single_survey_calc.getUser_id().getId())));
			criteria.add(Restrictions.eq("sDomainName", single_survey_calc.getsDomainName()));
			criteria.add(Restrictions.eq("sDimensionName", single_survey_calc.getsDimensionName()));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				single_obj = (TransSurveyCalcDO) iterator.next();
			}
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}

	@Override
	public boolean updateRecordSurveyCalc(TransSurveyCalcDO create_do) {
		logger.info("update New cacl");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(create_do);
			transaction.commit();
		} catch (Exception excp)  
		{
			bStatus = false;
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return bStatus;
	}

	@Override
	public boolean createRecordSurveyCalc(TransSurveyCalcDO create_do) {
		logger.info("Adding New calc");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(create_do);
			transaction.commit();
		} catch (Exception excp)  
		{
			bStatus = false;
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return bStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransSurveyCalcDO> fetchTransSurveyCalcList(TransSurveyCalcDO surveyCalc_obj) 
	{
		Session session = null;
		Transaction transaction;
		List<TransSurveyCalcDO> single_obj = new ArrayList<TransSurveyCalcDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyCalcDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(surveyCalc_obj.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(surveyCalc_obj.getUser_id().getId())));
			single_obj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}

	@Override
	public List<TransSurveyCalcDO> fetchTransSurveyCalcListByTotalVal(TransSurveyCalcDO surveyCalc_obj) 
	{
		Session session = null;
		Transaction transaction;
		List<TransSurveyCalcDO> single_obj = new ArrayList<TransSurveyCalcDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurveyCalcDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(surveyCalc_obj.getInit_id().getId())));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(surveyCalc_obj.getUser_id().getId())));
			criteria.add(Restrictions.eq("dTotalCalcVal", surveyCalc_obj.getdTotalCalcVal()));
			single_obj = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  single_obj;
	}

	@Override
	public StringBuilder fetchTransSurveyDetailsReport(TransUserDemoQuestionsDO surveyCalc_obj) 
	{
		Session session = null;
		StringBuilder return_result = new StringBuilder();
		// value assing
		String responce1 = "";
		String responce2 = "";
		String responce1_leader = "";
		String responce2_leader = "";
		try 
		{
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			
			//Response 1
			String responce1_sql = "select STRING_AGG('#',questions || '|' || answer_mark1 order by questions asc) questions "
									+ "from trans_survey_entries_by_user "
									+ "where trans_init_id = '"+surveyCalc_obj.getInit_id().getId()+"' "
									+ "and trans_user_id = '"+surveyCalc_obj.getUser_id().getId()+"' and " 
									+ "is_positive_response1 = 'Y' and "
									+ "is_leader_ship = 'N' "
									+ "group by trans_init_id, trans_user_id";
			
			Statement res1_statement = conn.createStatement();
			ResultSet res1_result = res1_statement.executeQuery(responce1_sql);
			while (res1_result.next())
			{
				responce1 = res1_result.getString(1);
			}
			
			//response1
			String responce2_sql = "select STRING_AGG('#',questions || '|' || answer_mark2 order by questions asc) questions "
							+ "from trans_survey_entries_by_user "
							+ "where trans_init_id = '"+surveyCalc_obj.getInit_id().getId()+"' "
							+ "and trans_user_id = '"+surveyCalc_obj.getUser_id().getId()+"' and " 
							+ "is_positive_response2 = 'Y' and "
							+ "is_leader_ship = 'N' "
							+ "group by trans_init_id, trans_user_id";

			Statement res2_statement = conn.createStatement();
			ResultSet res2_result = res2_statement.executeQuery(responce2_sql);
			while (res2_result.next())
			{
				responce2 = res2_result.getString(1);
			}
			
			String responce1_leader_sql = "select STRING_AGG('#',questions || '|' || answer_mark1 order by questions asc) questions "
							+ "from trans_survey_entries_by_user "
							+ "where trans_init_id = '"+surveyCalc_obj.getInit_id().getId()+"' "
							+ "and trans_user_id = '"+surveyCalc_obj.getUser_id().getId()+"' and " 
							+ "is_positive_response1 = 'Y' and "
							+ "is_leader_ship = 'Y' "
							+ "group by trans_init_id, trans_user_id";
			
			Statement res1_le_statement = conn.createStatement();
			ResultSet res1_le_result = res1_le_statement.executeQuery(responce1_leader_sql);
			while (res1_le_result.next())
			{
				responce1_leader = res1_le_result.getString(1);
			}
			
			String responce2_leader_sql = "select STRING_AGG('#',questions || '|' || answer_mark2 order by questions asc) questions "
							+ "from trans_survey_entries_by_user "
							+ "where trans_init_id = '"+surveyCalc_obj.getInit_id().getId()+"' "
							+ "and trans_user_id = '"+surveyCalc_obj.getUser_id().getId()+"' and " 
							+ "is_positive_response2 = 'Y' and "
							+ "is_leader_ship = 'Y' "
							+ "group by trans_init_id, trans_user_id";
			
			Statement res2_le_statement = conn.createStatement();
			ResultSet res2_le_result = res2_le_statement.executeQuery(responce2_leader_sql);
			while (res2_le_result.next())
			{
				responce1_leader = res2_le_result.getString(1);
			}
			
			return_result.append(responce1);
			return_result.append(responce2);
			return_result.append(responce1_leader);
			return_result.append(responce2_leader);
		} catch (Exception excp)
		{
			logger.info(excp.getMessage()+" ::: "+excp);
			
		} finally 
		{
			session.close();
		}
		logger.info("Get Connection: " + session.isOpen());
		return return_result;
	}	
}