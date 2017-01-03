package com.pyt.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.TagDao;
import com.pyt.model.Announcement;
import com.pyt.model.Tag;

import com.pyt.rest.filter.AnnouncementFilter;

@Stateless
public class AnnouncementService {
	@Inject
	private AnnouncementDao dao;
	
	@Inject
	private TagDao tagDao;
	
	public Collection<Announcement> getByQuaterI(int quarterId, AnnouncementFilter filter){
		return dao.getByQuarterId((long)quarterId);
	}
	
	public Announcement createOrUpdate(Announcement entity){		
		return dao.merge(entity);
	}
	
	public Collection<Tag> getAllTagsOrdered(){
		return tagDao.getAllOrderedByName();
	}
}
