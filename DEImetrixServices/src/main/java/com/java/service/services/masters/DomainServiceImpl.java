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

import com.java.service.bean.master.DomainsDO;

@Service
@Transactional
public class DomainServiceImpl implements DomainService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(DomainServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DomainsDO> fetchList() throws Exception 
	{
		logger.info("Retrieving all domain list");
		Session session = null;
		Transaction transaction;
		List<DomainsDO> listObj = new ArrayList<DomainsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(DomainsDO.class);
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			criteria.addOrder(Order.asc("sDomainName"));
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
	public boolean createRecord(DomainsDO domainObj) throws Exception 
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
	public boolean updateRecord(DomainsDO domainObj) throws Exception 
	{
		logger.info("Update domain");
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
	public DomainsDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single domain Details");
		Session session = null;
		Transaction transaction;
		DomainsDO single_obj = new DomainsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			single_obj = (DomainsDO) session.get(DomainsDO.class, id);
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
	public boolean deleteRecord(DomainsDO deomObj) throws Exception {
		logger.info("delete domain");
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
	
	@Override
	public DomainsDO fetchForUpdateByName(DomainsDO single_dimensions) 
	{
		Session session = null;
		Transaction transaction;
		DomainsDO listObj = new DomainsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(DomainsDO.class);
			criteria.add(Expression.eq("sDomainName", single_dimensions.getsDomainName().trim()).ignoreCase());
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				listObj = (DomainsDO) iterator.next();
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
}
