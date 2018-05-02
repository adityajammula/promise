package com.zen.smi.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.zen.smi.dao.UsersRolesDAO;
import com.zen.smi.dao.entities.Roles;
import com.zen.smi.dao.entities.UsersRoles;
import com.zen.smi.dao.exception.GenericDAOException;

public class UsersRolesDAOImpl extends BaseDAOImpl<UsersRoles, Serializable> implements UsersRolesDAO {

	public UsersRolesDAOImpl() {
		super(UsersRoles.class);
	}
	
	public List<UsersRoles> getUserRoles(int userId) throws GenericDAOException {
		
		List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
		expressionList.add(Restrictions.eq("users.userId", userId));
		
		return search(expressionList, null);
	}
	
	public static void main(String[] args) throws Exception {
		UsersRolesDAOImpl dao = new UsersRolesDAOImpl();
		List<UsersRoles> roleList = dao.getUserRoles(1);
		if(roleList != null)
		{
			for(UsersRoles userRoles:roleList)
			{
			    Roles roles = userRoles.getRoles();
				System.out.println("roles: "+roles);
				if(roles != null)
				{
					System.out.println("Role Name : "+roles.getRoleName());
				}
			}
		}
	}
}
