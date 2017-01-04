package com.pyt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.QuarterDao;
import com.pyt.model.Announcement;
import com.pyt.model.Quarter;

public class QuarterService {
	
	@Inject
	private QuarterDao quarterDao;
	
	@Inject
	private AnnouncementDao announcementDao;
	
	public Quarter getById(int id){
		Quarter entity =  quarterDao.getById(id);
		entity.announcementCount = quarterDao.countAnnouncement(id);
		entity.memberCount = quarterDao.countMember(id);
		return entity; 
	}
	
	public List<Quarter> getAll(){
		List<Quarter> entities = quarterDao.getAll();
		for(Quarter entity : entities){
			entity.announcementCount = quarterDao.countAnnouncement(entity.getId().intValue());
			entity.memberCount = quarterDao.countMember(entity.getId().intValue());
		}
		return entities;
	}
	
	public void publishAnnouncement(int quarterId, int announcementId){
		Quarter quarter = getById(quarterId);
		Set<Announcement> announcements = quarter.getAnnouncements();
		if(announcements == null)
			announcements=new HashSet<Announcement>();
		announcements.add(new Announcement(announcementId));
		quarter.setAnnouncements(announcements);
		quarterDao.merge(quarter);
	}
	
	
	public void unpublishAnnouncement(int quarterId,int announcementId){
		Quarter quarter = getById(quarterId);
		Set<Announcement> announcements = quarter.getAnnouncements();
		announcements.removeIf(entity -> entity.getIdAnnouncement() == announcementId);
		quarterDao.merge(quarter);
	}

}
