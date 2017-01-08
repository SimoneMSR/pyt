package com.pyt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pyt.model.Announcement;
import com.pyt.model.Announcement_;
import com.pyt.model.Favourite;
import com.pyt.model.Favourite_;
import com.pyt.model.Member_;

@ApplicationScoped
public class FavouriteDao extends BaseDao<Favourite,Favourite_>{
	
	public List<Announcement> getByMemberId(int memberId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
		Root<Favourite> favourite = query.from(Favourite.class);
		List<Predicate> conditions= new ArrayList<Predicate>();
		conditions.add(cb.equal(favourite.get(Favourite_.member).get(Member_.id), memberId));
		conditions.add(cb.isNull(favourite.get(Favourite_.deleted)));
		query.select(favourite.get(Favourite_.announcement))
			.where(conditions.toArray(new Predicate[]{}));
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			return new ArrayList<Announcement>();
		}
	}
	
	public Favourite getByMemberIdAndAnnouncementId(int memberId, int announcementId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Favourite> query = cb.createQuery(Favourite.class);
		Root<Favourite> favourite = query.from(Favourite.class);
		List<Predicate> conditions= new ArrayList<Predicate>();
		conditions.add(cb.equal(favourite.get(Favourite_.announcement).get(Announcement_.idAnnouncement), announcementId));
		conditions.add(cb.equal(favourite.get(Favourite_.member).get(Member_.id), memberId));
		conditions.add(cb.isNull(favourite.get(Favourite_.deleted)));
		query.select(favourite)
			.where(conditions.toArray(new Predicate[]{}));
		try{
			return em.createQuery(query).getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Integer> getIdsByMemberId(int memberId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
		Root<Favourite> favourite = query.from(Favourite.class);
		List<Predicate> conditions= new ArrayList<Predicate>();
		conditions.add(cb.equal(favourite.get(Favourite_.member).get(Member_.id), memberId));
		conditions.add(cb.isNull(favourite.get(Favourite_.deleted)));
		query.select(favourite.get(Favourite_.announcement).get(Announcement_.idAnnouncement))
			.where(conditions.toArray(new Predicate[]{}));
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			return new ArrayList<Integer>();
		}
	}

}
