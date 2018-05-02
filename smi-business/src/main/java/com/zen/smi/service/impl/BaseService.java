package com.zen.smi.service.impl;

import com.zen.smi.dao.MessagesDAO;
import com.zen.smi.dao.RolesDAO;
import com.zen.smi.dao.UsersDAO;
import com.zen.smi.dao.UsersRolesDAO;


public class BaseService {

	private UsersDAO userDAO;	
	private UsersRolesDAO usersRolesDAO;
	private RolesDAO rolesDAO;
	
	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UsersRolesDAO getUsersRolesDAO() {
		return usersRolesDAO;
	}

	public void setUsersRolesDAO(UsersRolesDAO usersRolesDAO) {
		this.usersRolesDAO = usersRolesDAO;
	}

	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}

	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}

	private MessagesDAO messagesDAO; 
	
	
	public MessagesDAO getMessagesDAO() {
		return messagesDAO;
	}

	public void setMessagesDAO(MessagesDAO messagesDAO) {
		this.messagesDAO = messagesDAO;
	}

	

}
