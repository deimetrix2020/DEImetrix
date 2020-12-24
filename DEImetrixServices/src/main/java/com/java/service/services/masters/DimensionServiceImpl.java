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

@Service
@Transactional
public class DimensionServiceImpl implements DimensionService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(DimensionServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DimensionsDO> fetchList() throws Exception 
	{
		logger.info("Retrieving all dimen list");
		Session session = null;
		Transaction transaction;
		List<DimensionsDO> listObj = new ArrayList<DimensionsDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(DimensionsDO.class);
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			criteria.addOrder(Order.asc("domainsDO"));
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
	public boolean createRecord(DimensionsDO dimenObj) throws Exception 
	{
		logger.info("Adding New dimen");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(dimenObj);
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
	public boolean updateRecord(DimensionsDO dimenObj) throws Exception 
	{
		logger.info("Update dimen");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(dimenObj);
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
	public DimensionsDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single dimen Details");
		Session session = null;
		Transaction transaction;
		DimensionsDO single_obj = new DimensionsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			single_obj = (DimensionsDO) session.get(DimensionsDO.class, id);
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
	public boolean deleteRecord(DimensionsDO dimenObj) throws Exception {
		logger.info("delete dimen");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(dimenObj);
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

	@SuppressWarnings("deprecation")
	@Override
	public DimensionsDO fetchForUpdateByName(DimensionsDO single_dimensions) 
	{
		Session session = null;
		Transaction transaction;
		DimensionsDO listObj = new DimensionsDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(DimensionsDO.class);
			criteria.add(Restrictions.eq("domainsDO", new DomainsDO(single_dimensions.getDomainsDO().getId())));
			criteria.add(Expression.eq("sDimensionsName", single_dimensions.getsDimensionsName().trim()).ignoreCase());
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				listObj = (DimensionsDO) iterator.next();
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
