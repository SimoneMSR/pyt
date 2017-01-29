package com.pyt.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pyt.dao.ConversationDao;
import com.pyt.dao.MessageDao;
import com.pyt.model.Conversation;
import com.pyt.model.Member;
import com.pyt.model.Message;
import com.pyt.rest.converter.MessageConverter;
import com.pyt.rest.dto.MessageDto;
import com.pyt.rest.queryParams.BaseQueryParams;

@Stateless
public class MessageService {
	
	@Inject
	private MemberService memberService;
	
	@Inject 
	private MessageDao dao;
	
	@Inject
	private ConversationDao conversationDao;
	
	
	public List<Conversation> getConversationByMemberId(int memberId, BaseQueryParams params){
		return conversationDao.getConversationsByMemberId(memberId, params);
	}
	

	public void sendMessage(MessageDto message, Member creator) {
		Collection<Member> recipients = memberService.getMultipleByIds(message.recipients);
		Message toInsert = MessageConverter.from(message, recipients, creator);
		toInsert = dao.insert(toInsert);
		dao.insertInInboxes(toInsert, recipients);
		dao.insertInOutbox(toInsert, creator);
		
		Conversation conversation;
		if(message.conversationId == null){
			conversation= new Conversation();
			conversation.setSubject(message.subject);
			conversation.setLastMessageDate(toInsert.getDate());
			conversationDao.Save(conversation);
		}else{
			conversation = conversationDao.getById(message.conversationId);
			conversation.setLastMessageDate(toInsert.getDate());
		}
		toInsert.setConversationId(conversation.getIdConversation());
		dao.merge(toInsert);
	}
	
	public List<Message> getInboxOrderebByReceiverId(int memberId){
		return dao.getInboxOrderebByReceiverId(memberId);
	}
	
	public int inboxCount(int memberId){
		return dao.countInboxMessages(memberId);
	}

}
