package com.pyt.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.LikeDao;
import com.pyt.model.Announcement;
import com.pyt.model.Like;
import com.pyt.model.Member;

@Stateless
public class LikeService {

	@Inject
	AnnouncementDao announcementDao;

	@Inject
	LikeDao likeDao;

	public boolean like(Member member, int announcementId, Boolean dislike) {
		try {
			boolean isDislike=dislike != null && dislike;
			Like present = likeDao.byAnnouncementAndMember(announcementId, member.getId().intValue());
			if (present != null) {
				boolean wasDislike=present.isDislike();
				present.setDislike(isDislike);
				present.setDate(new Date());
				likeDao.merge(present);
				return isDislike != wasDislike;
			} else {
				Like entity = new Like();
				entity.setMember(member);
				entity.setAnnouncement(announcementDao.getById((long) announcementId));
				entity.setDate(new Date());
				entity.setDislike(dislike != null && dislike);
				likeDao.Save(entity);
				return true;
			}
		} catch (PersistenceException e) {
			return false;
		}
	}
	
	public Announcement countLikes(int announcementId){
		Announcement retval = announcementDao.getById((long)announcementId);
		retval.dislikeCount = likeDao.countLike(announcementId, true);
		retval.likesCount = likeDao.countLike(announcementId, false);
		return retval;
		
	}

}
