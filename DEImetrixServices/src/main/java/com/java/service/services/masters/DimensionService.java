package com.java.service.services.masters;

import java.util.List;

import com.java.service.bean.master.DimensionsDO;

public interface DimensionService
{
	/**
	 * Fetch Records
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<DimensionsDO> fetchList() throws Exception;
	
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(DimensionsDO dimenObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(DimensionsDO dimenObj) throws Exception;

	/**
	 * Delete record
	 * @param dimenObj
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRecord(DimensionsDO dimenObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public DimensionsDO fetchForUpdate(Integer id) throws Exception;

	/**
	 * Dimension getting by name
	 * 
	 * @param single_dimensions
	 * @return
	 */
	public DimensionsDO fetchForUpdateByName(DimensionsDO single_dimensions);
}
