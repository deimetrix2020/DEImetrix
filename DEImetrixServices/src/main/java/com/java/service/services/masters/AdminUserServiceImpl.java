package com.java.service.services.masters;

import java.util.Iterator;

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

import com.java.service.bean.master.AdminLoginDO;

@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(AdminUserServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean createRecord(AdminLoginDO userObj) throws Exception 
	{
		logger.info("Update User");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(userObj);
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
	public boolean updateRecord(AdminLoginDO userObj) throws Exception 
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
	public AdminLoginDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single user Details");
		Session session = null;
		Transaction transaction;
		AdminLoginDO singleMaterialObj = new AdminLoginDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(AdminLoginDO.class);
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				singleMaterialObj = (AdminLoginDO) iterator.next();
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
		return  singleMaterialObj;
	}
	
	@Override
	public AdminLoginDO checkAdminCredentials(AdminLoginDO single_user) 
	{
		logger.info("Checking username and password.");
		Session session = null;
		Transaction transaction = null;
		AdminLoginDO userDetailsObj = new AdminLoginDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(AdminLoginDO.class);
			criteria.add(Expression.eq("sUserName", single_user.getsUserName().trim()).ignoreCase());
			criteria.add(Restrictions.eq("sPassword", single_user.getsPassword()));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				userDetailsObj = (AdminLoginDO) iterator.next();
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
}
