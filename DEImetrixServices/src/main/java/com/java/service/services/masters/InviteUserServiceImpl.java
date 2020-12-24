package com.java.service.services.masters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.master.InviteUsersDO;

@Service
@Transactional
public class InviteUserServiceImpl implements InviteUserService 
{
	/** logger */
	private static final Logger logger = Logger.getLogger(InviteUserServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InviteUsersDO> fetchList() throws Exception 
	{
		logger.info("Retrieving all demo list");
		Session session = null;
		Transaction transaction;
		List<InviteUsersDO> listObj = new ArrayList<InviteUsersDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(InviteUsersDO.class);
			criteria.add(Restrictions.eq("cIsDeleted", 'N'));
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
	
	@Override
	public boolean createRecord(InviteUsersDO demoObj) throws Exception 
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
	public boolean updateRecord(InviteUsersDO demoObj) throws Exception 
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
	public InviteUsersDO fetchForUpdate(Integer id) throws Exception 
	{
		logger.info("Retrieving Single demo Details");
		Session session = null;
		Transaction transaction;
		InviteUsersDO single_obj = new InviteUsersDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			single_obj = (InviteUsersDO) session.get(InviteUsersDO.class, id);
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
	public InviteUsersDO fetchForUpdateByEmail(String sEmailid) throws Exception 
	{
		Session session = null;
		Transaction transaction;
		InviteUsersDO listObj = new InviteUsersDO();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(InviteUsersDO.class);
			criteria.add(Restrictions.eq("sEmailId", sEmailid.trim()));
			criteria.addOrder(Order.desc("id"));
			for (Iterator<?> iterator = criteria.list().iterator(); iterator.hasNext();)
			{
				listObj = (InviteUsersDO) iterator.next();
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
	public boolean deleteRecord(InviteUsersDO data_obj) {
		logger.info("delete invite");
		Session session = null;
		Transaction transaction;
		boolean bStatus = true;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(data_obj);
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
