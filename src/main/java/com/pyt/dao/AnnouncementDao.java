package com.pyt.dao;

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

public class AnnouncementDao extends BaseDao<Announcement>{
	
	public Announcement getById(Long id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcement = criteria.from(Announcement.class);
        criteria.select(announcement).where(cb.equal(announcement.get(Announcement_.id), id));
        return em.createQuery(criteria).getSingleResult();
	}
	
	public Set<Announcement> getByQuarterId(Long quarterId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Quarter> criteria = cb.createQuery(Quarter.class);
        Root<Quarter> quarter = criteria.from(Quarter.class);
        criteria.select(quarter).where(cb.equal(quarter.get(Quarter_.id), quarterId));
        try {
        	return em.createQuery(criteria).getSingleResult().getAnnouncements();
        }catch(Exception e){
        	return null;
        }
	}

}
