package com.pyt.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
        Root<Quarter> quarterR = cb.createQuery(Quarter.class).from(Quarter.class);
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        Join<Announcement,Quarter> announcements = announcement.join(Announcement_.quarters);
        //announcements.
        criteria.select(announcement).where(announcement.join(Announcement_.quarters).in(quarter));
        		//.where(cb.equal(announcements.get(Announcement), quarterId));
        		//.where(cb.and(cb.equal(quarterR.get(Quarter_.idQuarter), quarterId)),
        			//	quarterR.in(announcement.get(Announcement_.quarters)));
        try {
        	return applyParams(criteria,params, cb,announcement);
        	//return em.createQuery(criteria).getResultList();
        }catch(Exception e){
        	return null;
        }
	}
	
	public List<Announcement> applyParams(CriteriaQuery<Announcement> query,AnnouncementParams params, CriteriaBuilder cb,Root<Announcement> announcement){
		if(params != null){
			//order
			if(params.filterBy !=null){
				switch (params.filterBy){
					case IDEA : query.where(cb.equal(announcement.get(Announcement_.cathegory),0)); break;
					case PROBLEM : query.where(cb.equal(announcement.get(Announcement_.cathegory),1)); break;
					case PROPOSAL : query.where(cb.equal(announcement.get(Announcement_.cathegory),2)); break;
				}
			}
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
