package com.java.service.services.masters;

import java.util.List;

import com.java.service.bean.master.DomainsDO;

public interface DomainService
{
	/**
	 * Fetch Records
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<DomainsDO> fetchList() throws Exception;
	
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(DomainsDO demoObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(DomainsDO demoObj) throws Exception;

	/**
	 * Delete record
	 * @param demoObj
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRecord(DomainsDO demoObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public DomainsDO fetchForUpdate(Integer id) throws Exception;

	/**
	 * Fetching record by name
	 * 
	 * @param single_dimensions
	 * @return
	 */
	public DomainsDO fetchForUpdateByName(DomainsDO single_dimensions);
}
