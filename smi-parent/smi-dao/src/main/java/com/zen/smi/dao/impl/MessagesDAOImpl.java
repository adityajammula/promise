package com.zen.smi.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.zen.smi.dao.MessagesDAO;
import com.zen.smi.dao.UsersDAO;
import com.zen.smi.dao.entities.Messages;
import com.zen.smi.dao.entities.Users;
import com.zen.smi.dao.exception.GenericDAOException;

public class MessagesDAOImpl extends BaseDAOImpl<Messages, Serializable> implements MessagesDAO {

	public MessagesDAOImpl() {
		super(Messages.class);
	}
	public List<Messages> getAllMessages() throws GenericDAOException {
		return retrieveAll();
	}
	
}
