package com.pyt.rest.converter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pyt.model.Member;
import com.pyt.model.Message;
import com.pyt.rest.dto.MessageDto;

public class MessageConverter {
	
	public static MessageDto to(Message entity){
		MessageDto dto = new MessageDto();
		dto.messageId = entity.getIdMessage();
		dto.creatorId = entity.getCreator().getId().intValue();
		dto.object=entity.getObject();
		dto.subject = entity.getSubject();
		dto.date = entity.getDate();
		dto.conversationId = entity.getConversationId();
		return dto;
	}
	
	public static Message from(MessageDto dto, Collection<Member> recipients, Member creator){
		Message entity = new Message();
		entity.setDate(dto.date);
		entity.setObject(dto.object);
		entity.setSubject(dto.subject);
		entity.setRecipients(recipients);
		entity.setCreator(creator);
		return entity;
	}

	public static List<MessageDto> to(java.util.List<Message> inboxOrderebByReceiverId) {
		ArrayList<MessageDto> retval = new ArrayList<MessageDto>();
		for(Message m : inboxOrderebByReceiverId){
			retval.add(to(m));
		}
		return retval;
	}

}
