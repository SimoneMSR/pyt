package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.pyt.model.Announcement;
import com.pyt.model.Quarter;
import com.pyt.model.Tag;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.rest.dto.TagDto;

import Enums.AnnouncementCathegory;

public class AnnouncementConverter {

	private static Logger log = Logger.getLogger("org.jboss.as.config");

	public static Announcement from(AnnouncementDto dto) {
		Announcement entity = new Announcement();
		entity.setIdAnnouncement(dto.id);
		entity.setDescription(dto.description);
		entity.setTitle(dto.title);
		Set<Tag> tags = new HashSet<Tag>();
		if(dto.tags !=null){
			for (TagDto t : dto.tags) {
				tags.add(new Tag(t.id));
			}
			entity.setTags(tags);			
		}
		Set<Quarter> quarters = new HashSet<Quarter>();
		if(dto.quarters!=null){
			dto.quarters.forEach(x -> quarters.add(new Quarter(x)));
			entity.setQuarters(quarters);
		}
		switch(dto.cathegory){
		case IDEA : entity.setCathegory(0); break;
		case PROBLEM : entity.setCathegory(1); break;
		case PROPOSAL : entity.setCathegory(2); break;
	}
		return entity;
	}
	

	public static Collection<AnnouncementDto> to(Collection<Announcement> entities) {
		Collection<AnnouncementDto> retval = new ArrayList<AnnouncementDto>();
		for (Announcement a : entities) {
			retval.add(to(a));
		}
		return retval;
	}

	public static AnnouncementDto to(Announcement entity) {
		AnnouncementDto dto = new AnnouncementDto();
		dto.id= entity.getIdAnnouncement();
		dto.tags = new ArrayList<TagDto>();
		dto.quarters = new ArrayList<Integer>();
		dto.title = entity.getTitle();
		dto.description = entity.getDescription();
		dto.likes =entity.likesCount;
		dto.dislikes = entity.dislikeCount;
		dto.creator = MemberConverter.to(entity.getCreator());
		for (Tag t : entity.getTags())
			dto.tags.add(TagConverter.to(t));
		switch(entity.getCathegory()){
			case 0 : dto.cathegory=AnnouncementCathegory.IDEA; break;
			case 1 : dto.cathegory=AnnouncementCathegory.PROBLEM; break;
			case 2 : dto.cathegory=AnnouncementCathegory.PROPOSAL; break;
		}
		
		return dto;
	}
	
	public static AnnouncementDto toComplete(Announcement entity){
		AnnouncementDto dto = to(entity);
		for (Quarter q : entity.getQuarters())
			dto.quarters.add(q.getId().intValue());
		return dto;
	}

}
