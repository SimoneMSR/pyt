package com.pyt.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.metamodel.Attribute;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.LikeDao;
import com.pyt.dao.TagDao;
import com.pyt.model.Announcement;
import com.pyt.model.Member;
import com.pyt.model.Tag;
import com.pyt.rest.queryParams.AnnouncementParams;

@Stateless
public class AnnouncementService {
	@Inject
	private AnnouncementDao dao;
	
	@Inject
	private LikeDao likeDao;
	
	@Inject
	private TagDao tagDao;
	
	public Collection<Announcement> getByQuaterId(int quarterId, AnnouncementParams params){
		Collection<Announcement> retval = dao.getByQuarterId((long)quarterId,params);
		for(Announcement a : retval){
			a.likesCount=likeDao.countLike(a.getIdAnnouncement(), false);
			a.dislikeCount = likeDao.countLike(a.getIdAnnouncement(), true);
		}
		return retval;
	}
	
	public Announcement getById(int announcementId){
		return dao.getById((long)announcementId);
	}
	
	public Announcement getById(int announcementId, Attribute<Announcement,?> ... attributes){
		return dao.getById((long)announcementId, attributes);
	}
	
	public void createOrUpdate(Announcement entity){	
		try{
			dao.Save(entity);
		}catch(Exception e){
			dao.merge(entity);			
		}
	}
	
	public Collection<Tag> getAllTagsOrdered(){
		return tagDao.getAllOrderedByName();
	}
	
	public void checkAnnouncementOwnership(Announcement announcement, Member claimer){
		if(announcement.getCreator().getId() != claimer.getId())
			throw new WebApplicationException(Response.Status.FORBIDDEN);
	}

	public Collection<Announcement> getByCreatorId(Integer creatorId, AnnouncementParams p) {
		Collection<Announcement> retval = dao.getByCreatorId((long)creatorId,p);
		for(Announcement a : retval){
			a.likesCount=likeDao.countLike(a.getIdAnnouncement(), false);
			a.dislikeCount = likeDao.countLike(a.getIdAnnouncement(), true);
		}
		return retval;
	}
}
