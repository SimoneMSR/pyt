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
		return quarterDao.getById(id); 
	}
	
	public List<Quarter> getAll(){
		return quarterDao.getAll();
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
	
	public void createOrUpdate(Announcement entity){		
		announcementDao.merge(entity);
	}
	
	public void unpublishAnnouncement(int quarterId,int announcementId){
		Quarter quarter = getById(quarterId);
		Set<Announcement> announcements = quarter.getAnnouncements();
		announcements.removeIf(entity -> entity.getIdAnnouncement() == announcementId);
		quarterDao.merge(quarter);
	}

}
