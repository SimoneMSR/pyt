package com.pyt.rest.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pyt.model.Conversation;
import com.pyt.rest.dto.ConversationDto;

public class ConversationConverter {

	public static ConversationDto to(Conversation entity){
		ConversationDto dto = new ConversationDto();
		dto.idConversation = entity.getIdConversation();
		dto.subject = entity.getSubject();
		dto.lastMessageDate = entity.getLastMessageDate();
		return dto;
	}
	
	public static List<ConversationDto> to(Collection<Conversation> entities){
		List<ConversationDto> retval = new ArrayList<ConversationDto>();
		for(Conversation c : entities){
			retval.add(to(c));
		}
		return retval;
	}
}
