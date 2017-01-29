package com.pyt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.pyt.model.Conversation;
import com.pyt.model.Conversation_;
import com.pyt.model.Inbox;
import com.pyt.model.Inbox_;
import com.pyt.model.Message;
import com.pyt.model.Message_;
import com.pyt.model.Outbox;
import com.pyt.model.Outbox_;
import com.pyt.rest.queryParams.BaseQueryParams;

@Stateless
public class ConversationDao extends BaseDao<Conversation, Conversation_>{
	/***
	 * 
	 * @param memberId
	 * @param params
	 * @return sent or received messages of the member, grouped by subject
	 */
	public List<Conversation> getConversationsByMemberId(int memberId, BaseQueryParams params){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = cb.createQuery(Conversation.class);
        Root<Conversation> conversation = criteria.from(Conversation.class);
        
        Subquery<Integer> messageQ= criteria.subquery(Integer.class);
		Root<Message> message = messageQ.from(Message.class);
        
        //subquery for inbox
        Subquery<Integer> inbox= messageQ.subquery(Integer.class);
        	Root<Inbox> inMessage = inbox.from(Inbox.class);
        	inbox.select(inMessage.get(Inbox_.messageId))
        	.where(cb.equal(inMessage.get(Inbox_.receiverId),memberId));        	
		
       //subquery for outbox
        Subquery<Integer> outbox= messageQ.subquery(Integer.class);
		Root<Outbox> outMessage = outbox.from(Outbox.class);
		outbox.select(outMessage.get(Outbox_.messageId))
			.where(cb.equal(outMessage.get(Outbox_.senderId),memberId));

		messageQ.select(message.get("conversationId"))
			.where(cb.or(message.get(Message_.idMessage).in(inbox),message.get(Message_.idMessage).in(outbox)))
			.distinct(true);
		
		criteria.select(conversation)
			.where(conversation.get(Conversation_.idConversation).in(messageQ))
			.distinct(true);
	
		try{
			return 
					super.applyQueryParams(criteria, params, cb).getResultList();
		}catch(NoResultException e ){
			return new ArrayList<Conversation>();
		}
        
	}

	public Conversation getById(Integer conversationId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Conversation> criteria = cb.createQuery(Conversation.class);
        Root<Conversation> conversation = criteria.from(Conversation.class);
        criteria.select(conversation)
        	.where(cb.equal(conversation.get(Conversation_.idConversation),conversationId));
		try{
			return em.createQuery(criteria).getSingleResult();
		}catch(NoResultException e ){
			return null;
		}
        
	}
	
}
