/**
 * 
 */
package com.java.service.services.masters;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.service.bean.master.AuditLogDO;

/**
 * @author thulasiram
 *
 */
@Service
@Transactional
public class AuditServiceImpl implements AuditService 
{

	/** logger */
	private static final Logger logger = Logger.getLogger(AuditServiceImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean createRecord(AuditLogDO auditLog) 
	{
		Session session = null;
		Transaction transaction;
		boolean status = false;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(auditLog);
			status = true;
			transaction.commit();
		} catch (Exception excp)  
		{
			logger.error(excp.getMessage(), excp);
		} finally 
		{
			session.close();
		}
		return status;
	}
}
