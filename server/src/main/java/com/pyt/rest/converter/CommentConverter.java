package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;

import com.pyt.model.Comment;
import com.pyt.rest.dto.CommentDto;

public class CommentConverter {

	public static CommentDto to(Comment entity){
		CommentDto dto= new CommentDto();
		dto.content = entity.getContent();
		dto.creator = MemberConverter.to(entity.getCreator());
		dto.date = entity.getDate();
		return dto;
	}
	
	public static Collection<CommentDto> to(Collection<Comment> entities){
		Collection<CommentDto> retval = new ArrayList<CommentDto>();
		for (Comment a : entities) {
			retval.add(to(a));
		}
		return retval;
	}
	
	public static Comment from(CommentDto dto){
		Comment entity = new Comment();
		entity.setContent(dto.content);
		return entity;
	}
}
