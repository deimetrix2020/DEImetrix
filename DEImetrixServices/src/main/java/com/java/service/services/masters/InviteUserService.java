package com.java.service.services.masters;

import java.util.List;

import com.java.service.bean.master.InviteUsersDO;

public interface InviteUserService
{
	/**
	 * Fetch Records
	 * 
	 * @return {@link List}
	 * @throws Exception
	 */
	public List<InviteUsersDO> fetchList() throws Exception;
	
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(InviteUsersDO demoObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(InviteUsersDO demoObj) throws Exception;

	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public InviteUsersDO fetchForUpdate(Integer id) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public InviteUsersDO fetchForUpdateByEmail(String sEmailid) throws Exception;

	public boolean deleteRecord(InviteUsersDO create_do);
}
