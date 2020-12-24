package com.java.service.services.masters;

import java.util.List;

import com.java.service.bean.master.DemographicsDO;

public interface DemographicsService
{
	/**
	 * Fetch Records
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<DemographicsDO> fetchList() throws Exception;
	
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(DemographicsDO demoObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(DemographicsDO demoObj) throws Exception;

	/**
	 * Delete record
	 * @param demoObj
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRecord(DemographicsDO demoObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public DemographicsDO fetchForUpdate(Integer id) throws Exception;
}
