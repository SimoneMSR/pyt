package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.pyt.model.Announcement;
import com.pyt.model.Tag;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.rest.dto.TagDto;

public class AnnouncementConverter {

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
		dto.title = entity.getTitle();
		dto.description = entity.getDescription();
		for (Tag t : entity.getTags())
			dto.tags.add(TagConverter.to(t));
		return dto;
	}

}
