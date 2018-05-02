package com.zen.smi.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.zen.smi.dao.RolesDAO;
import com.zen.smi.dao.UsersRolesDAO;
import com.zen.smi.dao.entities.Roles;
import com.zen.smi.dao.entities.UsersRoles;
import com.zen.smi.dao.exception.GenericDAOException;

public class RolesDAOImpl extends BaseDAOImpl<Roles, Serializable> implements RolesDAO {

	public RolesDAOImpl() {
		super(Roles.class);
	}
	
	public Roles getRoles(Integer userRoleId) throws GenericDAOException {
		List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
		expressionList.add(Restrictions.eq("roleId", userRoleId));
		return searchUnique(expressionList, null);
	}

	
}
