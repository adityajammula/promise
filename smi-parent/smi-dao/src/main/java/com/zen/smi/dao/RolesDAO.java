package com.zen.smi.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.zen.smi.dao.entities.Roles;
import com.zen.smi.dao.entities.Users;
import com.zen.smi.dao.entities.UsersRoles;
import com.zen.smi.dao.exception.GenericDAOException;

public interface RolesDAO extends BaseDAO<Roles, Serializable> {

	Roles getRoles(Integer userRoleIds) throws GenericDAOException;
}
