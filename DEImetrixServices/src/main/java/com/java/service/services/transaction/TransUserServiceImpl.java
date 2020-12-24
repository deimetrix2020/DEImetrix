package com.java.service.services.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.TransUserDetailsDO;

@Service
@Transactional
public class TransUserServiceImpl implements TransUserService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(TransUserServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public Integer createdRecord(TransUserDetailsDO userObj) throws Exception 
	{
		logger.info("Created User");
		Session session = null;
		Transaction transaction;
		Integer bStatus = null;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bStatus = (Integer) session.save(userObj);
			transaction.commit();
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return bStatus;
	}
	
	@Override
	public boolean updateRecord(TransUserDetailsDO userObj) throws Exception 
	{
		logger.info("Update User");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(userObj);
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
	public TransUserDetailsDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single user Details");
		Session session = null;
		Transaction transaction;
		TransUserDetailsDO singleMaterialObj = new TransUserDetailsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			singleMaterialObj = (TransUserDetailsDO) session.get(TransUserDetailsDO.class, id);
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is: " + session.isOpen());
		return  singleMaterialObj;
	}
	
	@Override
	public TransUserDetailsDO checkAdminCredentials(TransUserDetailsDO single_user) 
	{
		logger.info("Checking username and password.");
		Session session = null;
		Transaction transaction = null;
		TransUserDetailsDO userDetailsObj = new TransUserDetailsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransUserDetailsDO.class);
			criteria.add(Expression.eq("sEmailId", single_user.getsEmailId().trim()).ignoreCase());
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				userDetailsObj = (TransUserDetailsDO) iterator.next();
			}
			transaction.commit();
		} catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is:" + session.isOpen());
		return userDetailsObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransUserDetailsDO> fetchAllUsersList() 
	{
		logger.info("Checking username and password.");
		Session session = null;
		Transaction transaction = null;
		List<TransUserDetailsDO> userDetailsObj = new ArrayList<TransUserDetailsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(TransUserDetailsDO.class);
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			userDetailsObj = criteria.list();
			transaction.commit();
		} catch (Exception excp) 
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		logger.info("Get session connection open is:" + session.isOpen());
		return userDetailsObj;
	}

	@Override
	public boolean deleteRecord(TransUserDetailsDO userObj) 
	{
		logger.info("delete User");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(userObj);
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
}
