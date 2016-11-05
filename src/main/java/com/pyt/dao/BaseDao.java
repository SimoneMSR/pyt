package com.pyt.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class BaseDao<T> {

    @Inject
    protected EntityManager em;
    
    public void Save(T entity){
    	em.persist(entity);
    }
}
