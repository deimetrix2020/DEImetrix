package com.java.service.services.masters;

import com.java.service.bean.master.AdminLoginDO;

public interface AdminUserService
{
	/**
	 * Create Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean createRecord(AdminLoginDO userObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(AdminLoginDO userObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link AdminLoginDO}
	 * @throws Exception
	 */
	public AdminLoginDO fetchForUpdate(Integer id) throws Exception;

	/**
	 * check admin login
	 * @param single_user
	 * @return
	 */
	public AdminLoginDO checkAdminCredentials(AdminLoginDO single_user);
}
