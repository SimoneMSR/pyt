package com.pyt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pyt.model.Announcement_;
import com.pyt.model.Like;
import com.pyt.model.Like_;

public class LikeDao extends BaseDao<Like, Like_>{
	
	public int countLike2(int announcementId, boolean dislike){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Like> query = cb.createQuery(Like.class);
		Root<Like> like = query.from(Like.class);
		List<Predicate> conditions= new ArrayList<Predicate>();
		conditions.add(cb.equal(like.get(Like_.dislike),dislike));
		conditions.add(cb.equal(like.get(Like_.announcement).get(Announcement_.idAnnouncement), announcementId));
		query.where(conditions.toArray(new Predicate[]{}));
		CriteriaQuery<Long> count = cb.createQuery(Long.class);
		Root<Like> rootCount = count.from(Like.class);
		count.select(cb.count(rootCount));
	    count.where(query.getRestriction());
	    rootCount.alias(like.getAlias());
	    return em.createQuery(count).getSingleResult().intValue();
	}
	
	public int countLike(int announcementId, Boolean dislike){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Like> like = query.from(Like.class);
		List<Predicate> conditions= new ArrayList<Predicate>();
		conditions.add(cb.equal(like.get(Like_.dislike),dislike));
		conditions.add(cb.equal(like.get(Like_.announcement).get(Announcement_.idAnnouncement), announcementId));
		query.select(cb.count(like));
		query.where(conditions.toArray(new Predicate[]{}));
		return em.createQuery(query).getSingleResult().intValue();
	}

}
