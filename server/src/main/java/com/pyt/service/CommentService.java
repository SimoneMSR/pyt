package com.pyt.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.CommentDao;
import com.pyt.model.Announcement;
import com.pyt.model.Comment;
import com.pyt.model.Member;

public class CommentService {

	@Inject
	CommentDao commentDao;
	
	@Inject
	AnnouncementDao announcementDao;
	
    @Inject
    private Logger log;
	
	
	public boolean postComment(int announcementId,Comment comment,Member creator){
		try{
			Announcement announcement = announcementDao.getById((long)announcementId);
			comment.setAnnouncement(announcement);
			comment.setCreator(creator);
			comment.setDate(new Date());
			commentDao.Save(comment);
			return true;
		}catch(Exception e){
			log.info(e.getMessage());
			return false;
		}
	}
}
