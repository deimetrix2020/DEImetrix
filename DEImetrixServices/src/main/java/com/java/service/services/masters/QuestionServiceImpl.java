package com.java.service.services.masters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.master.DimensionsDO;
import com.java.service.bean.master.DomainsDO;
import com.java.service.bean.master.SurveyQuestionsDO;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyQuestionsDO> fetchList() throws Exception 
	{
		logger.info("Retrieving all quest list");
		Session session = null;
		Transaction transaction;
		List<SurveyQuestionsDO> listObj = new ArrayList<SurveyQuestionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SurveyQuestionsDO.class);
			//criteria.add(Restrictions.eq("cIsActive", 'Y'));
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			criteria.addOrder(Order.asc("id"));
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
	public boolean createRecord(SurveyQuestionsDO questObj) throws Exception 
	{
		logger.info("Adding New quest");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(questObj);
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
	public boolean updateRecord(SurveyQuestionsDO questObj) throws Exception 
	{
		logger.info("Update quest");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(questObj);
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
	public SurveyQuestionsDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single quest Details");
		Session session = null;
		Transaction transaction;
		SurveyQuestionsDO single_obj = new SurveyQuestionsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			single_obj = (SurveyQuestionsDO) session.get(SurveyQuestionsDO.class, id);
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
	public boolean deleteRecord(SurveyQuestionsDO questObj) throws Exception {
		logger.info("delete quest");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(questObj);
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
	public SurveyQuestionsDO fetchForUpdateByDetail(SurveyQuestionsDO questionsObj) {
		Session session = null;
		Transaction transaction;
		SurveyQuestionsDO listObj = new SurveyQuestionsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SurveyQuestionsDO.class);
			criteria.add(Restrictions.eq("domainsDO", new DomainsDO(questionsObj.getDomainsDO().getId())));
			criteria.add(Restrictions.eq("dimensionsDO", new DimensionsDO(questionsObj.getDimensionsDO().getId())));
			criteria.add(Expression.eq("sQuestions", questionsObj.getsQuestions().trim()).ignoreCase());
			criteria.add(Expression.eq("sResponse1", questionsObj.getsResponse1().trim()).ignoreCase());
			criteria.add(Expression.eq("sResponse2", questionsObj.getsResponse2().trim()).ignoreCase());
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			//criteria.add(Restrictions.eq("cIsActive", 'Y'));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				listObj = (SurveyQuestionsDO) iterator.next();
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
		return listObj;
	}
	
	@Override
	public List<SurveyQuestionsDO> fetchForUpdateByQuestion(SurveyQuestionsDO questionsObj) {
		Session session = null;
		Transaction transaction;
		List<SurveyQuestionsDO> obj_list = new ArrayList<SurveyQuestionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SurveyQuestionsDO.class);
			criteria.add(Restrictions.eq("domainsDO", new DomainsDO(questionsObj.getDomainsDO().getId())));
			criteria.add(Restrictions.eq("dimensionsDO", new DimensionsDO(questionsObj.getDimensionsDO().getId())));
			if(questionsObj.getcIsLeaderShip() == 'N')
			{
				criteria.add(Restrictions.eq("cIsLeaderShip", questionsObj.getcIsLeaderShip()));				
			}
			criteria.add(Restrictions.eq("cIsActive", 'Y'));
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			obj_list = criteria.list();
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return obj_list;
	}
}
