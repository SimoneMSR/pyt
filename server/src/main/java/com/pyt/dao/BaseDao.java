package com.pyt.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class BaseDao<T,T_> {

    @Inject
    protected EntityManager em;
    
    public void Save(T entity){
    	em.persist(entity);
    }
    
	public T merge(T object) {
		return em.merge(object);
	}
    
}
