package com.pyt.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.TagDao;
import com.pyt.model.Announcement;
import com.pyt.model.Tag;

@Stateless
public class AnnouncementService {
	@Inject
	private AnnouncementDao dao;
	
	@Inject
	private TagDao tagDao;
	
	public Collection<Announcement> getByQuaterI(int quarterId){
		return dao.getByQuarterId((long)quarterId);
	}
	
	public void createOrUpdate(Announcement entity){		
		dao.merge(entity);
	}
	
	public Collection<Tag> getAllTagsOrdered(){
		return tagDao.getAllOrderedByName();
	}
}
