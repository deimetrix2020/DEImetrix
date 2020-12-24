package com.java.service.services.transaction;

import java.util.List;

import com.java.service.bean.TransUserDetailsDO;

public interface TransUserService
{
	/**
	 * Created Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public Integer createdRecord(TransUserDetailsDO userObj) throws Exception;
	
	/**
	 * Update Record
	 * 
	 * @param userObj
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateRecord(TransUserDetailsDO userObj) throws Exception;
	
	/**
	 * Fetch record for Fetch record using id for update.
	 * 
	 * @param id
	 * @return {@link TransUserDetailsDO}
	 * @throws Exception
	 */
	public TransUserDetailsDO fetchForUpdate(Integer id) throws Exception;

	/**
	 * check admin login
	 * @param single_user
	 * @return
	 */
	public TransUserDetailsDO checkAdminCredentials(TransUserDetailsDO single_user);

	/**
	 * Fetching all list
	 * 
	 * @return
	 */
	public List<TransUserDetailsDO> fetchAllUsersList();

	/**
	 * Delete User details
	 * 
	 * @param create_do
	 * @return
	 */
	public boolean deleteRecord(TransUserDetailsDO create_do);
}
