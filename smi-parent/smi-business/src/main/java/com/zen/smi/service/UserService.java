package com.zen.smi.service;

import com.zen.smi.bo.UserBO;
import com.zen.smi.exception.BusinessException;
import com.zen.smi.exception.UserException;

public interface UserService {

	/**
	 * Get the user for userId from the database.
	 * @param userId String
	 * @return StaffBO
	 * @throws StaffException 
	 */
	UserBO getUserByUserName(String userName) throws BusinessException;
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws UserException
	 */
	UserBO authenticate(String userName, String password) throws BusinessException;
	
	
	
	String createUser(UserBO userBO) throws BusinessException;
	
	
}
