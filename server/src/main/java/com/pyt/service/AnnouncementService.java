package com.pyt.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.TagDao;
import com.pyt.model.Announcement;
import com.pyt.model.Tag;
import com.pyt.rest.queryParams.AnnouncementParams;

@Stateless
public class AnnouncementService {
	@Inject
	private AnnouncementDao dao;
	
	@Inject
	private TagDao tagDao;
	
	public Collection<Announcement> getByQuaterId(int quarterId, AnnouncementParams params){
		return dao.getByQuarterId((long)quarterId,params);
	}
	
	public Announcement getById(int announcementId){
		return dao.getById((long)announcementId);
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
}
