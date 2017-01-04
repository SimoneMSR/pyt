package com.pyt.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.LikeDao;
import com.pyt.model.Like;
import com.pyt.model.Member;

@Stateless
public class LikeService {
	
	@Inject
	AnnouncementDao announcementDao;
	
	@Inject
	LikeDao likeDao;

	public boolean like(Member member,int announcementId, Boolean dislike) {
		try{
			Like entity=new Like();
			entity.setMember(member);
			entity.setAnnouncement(announcementDao.getById((long)announcementId));
			entity.setDate(new Date());
			entity.setDislike(dislike!=null && dislike);
			likeDao.Save(entity);
			return true;
		}catch(PersistenceException e){
			return false;
		}
	}

}
