/**
 * 
 */
package com.java.service.services.masters;

import com.java.service.bean.master.AuditLogDO;

/**
 * @author thulasiram
 *
 */
public interface AuditService 
{
	public boolean createRecord(AuditLogDO auditLog);
}
