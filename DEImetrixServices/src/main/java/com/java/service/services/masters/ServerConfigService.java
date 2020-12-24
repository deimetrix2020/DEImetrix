package com.java.service.services.masters;

import com.java.service.bean.master.AdminLoginDO;
import com.java.service.bean.master.ServerConfigDO;

public interface ServerConfigService
{
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(ServerConfigDO demoObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(ServerConfigDO demoObj) throws Exception;

	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public ServerConfigDO fetchForUpdate(Integer id) throws Exception;
}
