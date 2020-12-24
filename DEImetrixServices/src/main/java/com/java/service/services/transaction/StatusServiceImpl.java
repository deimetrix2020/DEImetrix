/**
 * 
 */
package com.java.service.services.transaction;

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

import com.java.service.bean.master.StatusDO;

/**
 * @author thulasiram
 *
 */
@Service
@Transactional
public class StatusServiceImpl implements StatusService 
{

	/** logger */
	private static final Logger logger = Logger.getLogger(StatusServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StatusDO> retriveStatusById() 
	{
		Session session = null;
		Transaction transaction;
		List<StatusDO> listObj = new ArrayList<StatusDO>();
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(StatusDO.class);
			criteria.add(Restrictions.eq("cIsActive", 'Y'));
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
