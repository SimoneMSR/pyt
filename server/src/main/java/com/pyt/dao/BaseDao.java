package com.pyt.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
