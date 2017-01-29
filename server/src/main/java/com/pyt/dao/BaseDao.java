package com.pyt.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.pyt.rest.queryParams.BaseQueryParams;

@Stateless
public class BaseDao<T, T_> {

	@PersistenceContext
	protected EntityManager em;

	public void Save(T entity) {
		em.persist(entity);
	}
	
	public T insert(T entity){
		em.persist(entity);
		em.refresh(entity);
		return entity;
	}

	public void merge(T object) {
		em.merge(object);
	}
	
	public class QueryParams<T1>{
		CriteriaQuery<T1> criteria;
		Root<T1> root;
	}
	
	public TypedQuery<T> applyQueryParams(CriteriaQuery<T> query,BaseQueryParams params, CriteriaBuilder cb){
		if(params !=null){
			if(params.skip !=null){
				if(params.top != null)
					return em.createQuery(query).setFirstResult(params.skip).setMaxResults(params.top);
				else
					return em.createQuery(query).setFirstResult(params.skip);
			}else{
				if(params.top != null)
					return em.createQuery(query).setMaxResults(params.top);
				else
					return em.createQuery(query);
			}			
		}else{
			return em.createQuery(query);
		}
	}

}
