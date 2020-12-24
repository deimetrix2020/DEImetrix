package com.java.service.services.masters;

import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.master.ServerConfigDO;

@Service
@Transactional
public class ServerConfigServiceImpl implements ServerConfigService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(ServerConfigServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean createRecord(ServerConfigDO demoObj) throws Exception 
	{
		logger.info("Adding New Server config");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(demoObj);
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
	public boolean updateRecord(ServerConfigDO demoObj) throws Exception 
	{
		logger.info("Update Server config");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(demoObj);
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
	public ServerConfigDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single Server config Details");
		Session session = null;
		Transaction transaction;
		ServerConfigDO single_obj = new ServerConfigDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(ServerConfigDO.class);
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				single_obj = (ServerConfigDO) iterator.next();
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
}
