package com.pyt.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class BaseDao<T, T_> {

	@PersistenceContext
	protected EntityManager em;

	public void Save(T entity) {
		em.persist(entity);
	}

	public void merge(T object) {
		em.merge(object);
	}
	
	public class QueryParams<T1>{
		CriteriaQuery<T1> criteria;
		Root<T1> root;
	}

}
