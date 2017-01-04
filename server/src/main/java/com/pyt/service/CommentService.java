package com.pyt.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.CommentDao;
import com.pyt.model.Announcement;
import com.pyt.model.Comment;
import com.pyt.model.Member;
import com.pyt.rest.converter.CommentConverter;
import com.pyt.rest.dto.CommentDto;

@Stateless
public class CommentService {

	@Inject
	private CommentDao commentDao;

	@Inject
	private AnnouncementDao announcementDao;

	@Inject
	private Logger log;

	public boolean postComment(int announcementId, Comment comment, Member creator) {
		try {
			Announcement announcement = announcementDao.getById((long) announcementId);
			comment.setAnnouncement(announcement);
			comment.setCreator(creator);
			comment.setDate(new Date());
			commentDao.Save(comment);
			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			return false;
		}
	}

	public List<CommentDto> getByAnnouncement(int announcementId) {
		Announcement announcement = announcementDao.getById((long) announcementId);
		return (List<CommentDto>) CommentConverter.to(announcement.getComments());
	}
}
