package com.pyt.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.model.Announcement;

@Stateless
public class AnnouncementService {
	@Inject
	private AnnouncementDao dao;
	
	public Collection<Announcement> getByQuaterI(int quarterId){
		return dao.getByQuarterId((long)quarterId);
	}
}
