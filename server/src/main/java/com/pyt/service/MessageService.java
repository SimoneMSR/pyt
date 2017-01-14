package com.pyt.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.MessageDao;
import com.pyt.model.Member;
import com.pyt.model.Message;
import com.pyt.rest.converter.MessageConverter;
import com.pyt.rest.dto.MessageDto;

@Stateless
public class MessageService {
	
	@Inject
	private MemberService memberService;
	
	@Inject 
	private MessageDao dao;
	
	
	

	public void sendMessage(MessageDto message, Member creator) {
		Collection<Member> recipients = memberService.getMultipleByIds(message.recipients);
		Message toInsert = MessageConverter.from(message, recipients, creator);
		toInsert = dao.insert(toInsert);
		dao.insertInInboxes(toInsert, recipients);
		dao.insertInOutbox(toInsert, creator);
	}
	
	public List<Message> getInboxOrderebByReceiverId(int memberId){
		return dao.getInboxOrderebByReceiverId(memberId);
	}

}
