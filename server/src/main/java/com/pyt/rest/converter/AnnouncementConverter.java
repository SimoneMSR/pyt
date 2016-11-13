package com.pyt.rest.converter;

import com.pyt.model.Announcement;
import com.pyt.rest.dto.AnnouncementDto;

public class AnnouncementConverter {
	
	public static Announcement from(AnnouncementDto dto){
		Announcement entity = new Announcement();
		entity.setDescription(dto.description);
		entity.setTitle(dto.title);
		return  entity;
	}
	
	public static AnnouncementDto to(Announcement entity){
		AnnouncementDto dto = new AnnouncementDto();
		dto.title=entity.getTitle();
		dto.description = entity.getDescription();
		return dto;
	}

}
