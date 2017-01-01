package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pyt.model.Quarter;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.model.Announcement;
import com.pyt.model.Member;
import com.pyt.rest.dto.QuarterDto;

public class QuarterConverter {
	public static QuarterDto to(Quarter entity){
		QuarterDto dto=new QuarterDto();
		dto.name = entity.getName();
		dto.id = entity.getId().intValue();
		dto.annnouncementsCounter = entity.getAnnouncements().size();
		Collection<Member> mem = entity.getMembers();
		dto.memberCounter = mem!=null ? mem.size() : 0;
		return dto;
	}
	
	public static List<QuarterDto> to(List<Quarter> entities){
		List<QuarterDto> retval = new ArrayList<QuarterDto>();
		for(Quarter q : entities)
			retval.add(to(q));
		return retval;
	}
	
	public static QuarterDto toComplete(Quarter entity){
		QuarterDto dto = to(entity);
		dto.announcements=new HashSet<AnnouncementDto>();
		for(Announcement a : entity.getAnnouncements()){
			dto.announcements.add(AnnouncementConverter.to(a));
		}
		return dto;
	}
}
