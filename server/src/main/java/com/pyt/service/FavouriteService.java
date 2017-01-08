package com.pyt.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.AnnouncementDao;
import com.pyt.dao.FavouriteDao;
import com.pyt.model.Announcement;
import com.pyt.model.Favourite;
import com.pyt.model.Member;
import com.pyt.rest.dto.AnnouncementDto;

@Stateless
public class FavouriteService {
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private AnnouncementService announcementService;
	
	@Inject
	private FavouriteDao favouriteDao;

	public void setFavourite(int announcementId, Member currentUser) {
		Announcement toFavourite = announcementService.getById(announcementId);
		Favourite wasFavourite = favouriteDao.getByMemberIdAndAnnouncementId(currentUser.getId().intValue(), announcementId);
		if(wasFavourite==null){
			Favourite favourite = new Favourite();
			favourite.setAnnouncement(toFavourite);
			favourite.setMember(currentUser);
			favourite.setDate(new Date());
			favouriteDao.Save(favourite);			
		}else{
			wasFavourite.setDeleted(new Date());
			favouriteDao.merge(wasFavourite);
		}
	}
	
	public List<Integer> getFavouritesIds(Member member){
		return favouriteDao.getIdsByMemberId(member.getId().intValue());
	}

	public List<Announcement> getFavourites(Member currentUser) {
		return favouriteDao.getByMemberId(currentUser.getId().intValue());
	}

}
