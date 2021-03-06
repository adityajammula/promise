package com.zen.smi.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

import com.zen.smi.dao.exception.GenericDAOException;

public interface BaseDAO<T,PK extends Serializable> {

	T findById(Serializable id) throws GenericDAOException;

    void saveOrUpdate(T t) throws GenericDAOException;

    List<T> retrieve(String queryName, Map<String, String> parameters)
			throws GenericDAOException;
    /* added to load all the user entities without any parameters*/
    List<T> load(String queryName)
			throws GenericDAOException;

    List<T> search(List<SimpleExpression> expressionList, List<Order> orderByList)
    		throws GenericDAOException;

    T searchUnique(List<SimpleExpression> expressionList, List<Order> orderByList)
    		throws GenericDAOException;

    void delete(T t) throws GenericDAOException;
    
    List<T> retrieveAll() throws GenericDAOException;
}
