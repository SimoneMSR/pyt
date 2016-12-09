package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;

import com.pyt.model.Tag;
import com.pyt.rest.dto.TagDto;

public class TagConverter {
	
	public static TagDto to(Tag entity){
		return new TagDto(entity.getIdTag(),entity.getName());
	}
	
	public static Collection<TagDto> to(Collection<Tag> entity){
		Collection<TagDto> retval = new ArrayList<TagDto>();
		for(Tag t : entity){
			retval.add(to(t));
		}
		return retval;
	}

}
