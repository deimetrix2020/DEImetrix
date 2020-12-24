package com.java.service.services.transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.TransSurvryInitDO;
import com.java.service.bean.TransUserDemoQuestionsDO;
import com.java.service.bean.TransUserDetailsDO;
import com.java.service.bean.master.StatusDO;

@Service
@Transactional
public class TransDemoServiceImpl implements TransDemoService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(TransDemoServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<TransUserDemoQuestionsDO> fetchDemoQuestionsList(Integer user_id, Integer init_id) 
	{
		logger.info("Retrieving all domain list");
		Session session = null;
		Transaction transaction;
		List<TransUserDemoQuestionsDO> listObj = new ArrayList<TransUserDemoQuestionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransUserDemoQuestionsDO.class);
			criteria.add(Restrictions.eq("init_id", new TransSurvryInitDO(init_id)));
			criteria.add(Restrictions.eq("user_id", new TransUserDetailsDO(user_id)));
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
	public boolean createDemoQuestionRecord(TransUserDemoQuestionsDO demoQuestionsDO) {
		logger.info("Adding New domain");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(demoQuestionsDO);
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
	public boolean deleteDemoQuestionRecord(TransUserDemoQuestionsDO create_do) {
		logger.info("delete domain");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(create_do);
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
	public Integer createSurveyInitRecord(TransSurvryInitDO create_do) 
	{
		logger.info("Adding New domain");
		Session session = null;
		Transaction transaction;
		Integer iPrim_id = null;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			iPrim_id = (Integer) session.save(create_do);
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return iPrim_id;
	}
	
	@Override
	public boolean updateSurveyInitRecord(TransSurvryInitDO create_do) 
	{
		logger.info("Adding New domain");
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
	public String fetchTransDemoDetailsReport(TransUserDemoQuestionsDO surveyCalc_obj) 
	{
		Session session = null;
		Statement statement = null;
		String return_result = "";
		try 
		{
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			
			String sql = "select STRING_AGG(questions || '|' || answer || '|' || is_leadership, '#' order by questions asc) questions_ans " + 
							"from trans_demo_questions_answer "
							+ "where trans_init_id = '"+surveyCalc_obj.getInit_id().getId()+"' "
							+ "and trans_user_id = '"+surveyCalc_obj.getUser_id().getId()+"' " + 
							"group by " + 
							"trans_init_id, trans_user_id";
			
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next())
			{
				return_result = result.getString(1);
			}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TransSurvryInitDO> fetchTransSurveyAllList(TransSurvryInitDO single_user) 
	{
		logger.info("Retrieving all domain list");
		Session session = null;
		Transaction transaction;
		List<TransSurvryInitDO> listObj = new ArrayList<TransSurvryInitDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransSurvryInitDO.class);
			if(single_user.getStatus().getId() != 1)
			{
				criteria.add(Restrictions.eq("status", new StatusDO(single_user.getStatus().getId())));				
			}
			criteria.addOrder(Order.desc("cIsLeaderShip"));
			criteria.addOrder(Order.desc("dCreatedDate"));
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
}
