package com.java.service.services.masters;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.master.DemographicsDO;

@Service
@Transactional
public class DemographicsServiceImpl implements DemographicsService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(DemographicsServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DemographicsDO> fetchList() throws Exception 
	{
		logger.info("Retrieving all demo list");
		Session session = null;
		Transaction transaction;
		List<DemographicsDO> listObj = new ArrayList<DemographicsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(DemographicsDO.class);
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
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
	public boolean createRecord(DemographicsDO demoObj) throws Exception 
	{
		logger.info("Adding New Demo");
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
	public boolean updateRecord(DemographicsDO demoObj) throws Exception 
	{
		logger.info("Update Demo");
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
	public DemographicsDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single demo Details");
		Session session = null;
		Transaction transaction;
		DemographicsDO single_obj = new DemographicsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			single_obj = (DemographicsDO) session.get(DemographicsDO.class, id);
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
	public boolean deleteRecord(DemographicsDO deomObj) throws Exception {
		logger.info("delete Demo");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(deomObj);
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
