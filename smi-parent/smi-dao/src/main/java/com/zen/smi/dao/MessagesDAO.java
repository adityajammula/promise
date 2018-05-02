package com.zen.smi.dao;

import java.io.Serializable;
import java.util.List;

import com.zen.smi.dao.entities.Messages;
import com.zen.smi.dao.entities.Users;
import com.zen.smi.dao.exception.GenericDAOException;

public interface MessagesDAO extends BaseDAO<Messages, Serializable> {

	List<Messages> getAllMessages() throws GenericDAOException;
}
