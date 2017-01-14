/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pyt.dao;

import com.pyt.model.Member;
import com.pyt.model.Member_;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class MemberDao extends BaseDao<Member,Member_> {

    @Inject
    private Logger log;
    
    public Member getById(Long id) {
        return em.find(Member.class, id);
    }

    public Member getByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).where(cb.equal(member.get(Member_.email), email));
        try{
        	return em.createQuery(criteria).getSingleResult();        	
        }catch(Exception e){
        	log.warning(e.getMessage());
        	return null;
        }
    }
    
    public Collection<Member> getMultipleById(int ids[]){
    	Long[] objLong = new Long[ids.length];
    	for(int index = 0; index < ids.length; index++)
    	{
    		objLong[index] = (long)ids[index];
    	}
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).where(member.get(Member_.id).in(objLong));
        try{
        	return em.createQuery(criteria).getResultList();
        }catch(NoResultException e){
        	return new HashSet<Member>();
        }
    }

    public List<Member> getAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        return em.createQuery(criteria).getResultList();
    }
}
