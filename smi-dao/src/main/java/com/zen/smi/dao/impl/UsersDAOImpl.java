package com.zen.smi.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.zen.smi.dao.UsersDAO;
import com.zen.smi.dao.entities.Users;
import com.zen.smi.dao.entities.UsersRoles;
import com.zen.smi.dao.exception.GenericDAOException;import javassist.expr.Instanceof;

public class UsersDAOImpl extends BaseDAOImpl<Users, Serializable> implements UsersDAO {

	public UsersDAOImpl() {
		super(Users.class);
	}
	public Users getUserByUserName(String userName) throws GenericDAOException {
		
		List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
		expressionList.add(Restrictions.eq("userName", userName));
		
		return searchUnique(expressionList, null);
	}
	
    public Users authenticate(String userName, String password) throws GenericDAOException {
		
		List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
		expressionList.add(Restrictions.eq("userName", userName));
		expressionList.add(Restrictions.eq("password", password));
		return searchUnique(expressionList, null);
	}
	
	
	public static void main(String[] args) throws Exception {
		UsersDAOImpl dao = new UsersDAOImpl();
		Users user = dao.getUserByUserName("john.doe");
		System.out.println("First Name : "+user.getFirstName());
	}
	public  void createUser(Users user) throws GenericDAOException {
		
		saveOrUpdate(user);
		
	}
	
}
