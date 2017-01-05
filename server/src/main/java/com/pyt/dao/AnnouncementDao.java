package com.pyt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import com.pyt.model.Announcement;
import com.pyt.model.Announcement_;
import com.pyt.model.Quarter;
import com.pyt.model.Quarter_;
import com.pyt.rest.queryParams.AnnouncementParams;

import Enums.AnnouncementCathegory;

public class AnnouncementDao extends BaseDao<Announcement,Announcement_>{
	
	public Announcement getById(Long id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        criteria.select(announcement).where(cb.equal(announcement.get(Announcement_.idAnnouncement), id));
        return em.createQuery(criteria).getSingleResult();
	}
	
	public List<Announcement> getByQuarterId(Long quarterId, AnnouncementParams params){
		Quarter quarter = new Quarter();
		quarter.setId(quarterId);
		Collection<Quarter> coll = new HashSet<Quarter>();
		coll.add(quarter);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        Predicate whereQuarter = announcement.join(Announcement_.quarters).in(quarter);
        try {
        	List<Predicate> whereParams= applyParamsFilter(cb,criteria,params,announcement);
        	whereParams.add(whereQuarter);
            criteria.select(announcement).where(whereParams.toArray(new Predicate[]{}));
            return applyParams(criteria,params,cb,announcement);
        }catch(Exception e){
        	return null;
        }
	}
	
	public List<Predicate> applyParamsFilter(CriteriaBuilder cb,CriteriaQuery<Announcement> query,AnnouncementParams params,Root<Announcement> announcement){
		List<Predicate> retval = new ArrayList<Predicate>();
		if(params != null){
			//order
			if(params.filterBy !=null && !params.filterBy.isEmpty()){
				List<Integer> cathegories = new ArrayList<Integer>();
				if(params.filterBy.indexOf(AnnouncementCathegory.IDEA.name())>=0)
					cathegories.add(0);
				if(params.filterBy.indexOf(AnnouncementCathegory.PROBLEM.name())>=0)
					cathegories.add(1);
				if(params.filterBy.indexOf(AnnouncementCathegory.PROPOSAL.name())>=0)
					cathegories.add(2);
				retval.add(announcement.get(Announcement_.cathegory).in(cathegories));
			}
			if(params.title != null && !params.title.isEmpty()){
				retval.add(cb.like(announcement.get(Announcement_.title),"%"+params.title+"%"));
			}
		}
		return retval;
	}
	
	public List<Announcement> applyParams(CriteriaQuery<Announcement> query,AnnouncementParams params, CriteriaBuilder cb,Root<Announcement> announcement){
		if(params !=null){
			if(params.orderBy!=null){
				switch (params.orderBy){
					case "title" : {
						query.orderBy(cb.asc(announcement.get(Announcement_.title)));
					} break;
				}
			}
			if(params.skip !=null){
				if(params.top != null)
					return em.createQuery(query).setFirstResult(params.skip).setMaxResults(params.top).getResultList();
				else
					return em.createQuery(query).setFirstResult(params.skip).getResultList();
			}else{
				if(params.top != null)
					return em.createQuery(query).setMaxResults(params.top).getResultList();
				else
					return em.createQuery(query).getResultList();
			}
		}else
			return em.createQuery(query).getResultList();
		
	}

}
