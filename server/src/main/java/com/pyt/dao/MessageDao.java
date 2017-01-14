package com.pyt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pyt.model.Inbox;
import com.pyt.model.Inbox_;
import com.pyt.model.Member;
import com.pyt.model.Member_;
import com.pyt.model.Message;
import com.pyt.model.Message_;
import com.pyt.model.Outbox;


@Stateless
public class MessageDao extends BaseDao<Message, Message_> {
	

	public List<Message> getInboxOrderebByReceiverId(int memberId){
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Message> criteria = cb.createQuery(Message.class);
	        Root<Message> message = criteria.from(Message.class);
	        Collection<Integer> messagesIds = getInboxIdsByReceiverId(memberId);
	        criteria.select(message)
	        	.orderBy(cb.desc(message.get(Message_.date)))
	        	.where(message.get(Message_.idMessage).in(messagesIds));
			try{
				return em.createQuery(criteria).getResultList();
			}catch(Exception e ){
				return new ArrayList<Message>();
			}
	}
	
	private Collection<Integer> getInboxIdsByReceiverId(int memberId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);
		Root<Inbox> messageTo = criteria.from(Inbox.class);
		criteria.select(messageTo.get(Inbox_.messageId))
			.where(cb.equal(messageTo.get(Inbox_.receiverId),memberId));
		try{
			return em.createQuery(criteria).getResultList();
		}catch(NoResultException e ){
			return new ArrayList<Integer>();
		}
	}
	
	public void insertInInboxes(Message message,Collection<Member> recipients){
		Inbox inbox = new Inbox();
		inbox.setMessageId(message.getIdMessage());
		for(Member to : recipients){
			inbox.setReceiverId(to.getId().intValue());
			this.em.persist(inbox);
		}
	}
	
	public void insertInOutbox(Message message,Member creator){
		Outbox outbox = new Outbox();
		outbox.setMessageId(message.getIdMessage());
		outbox.setSenderId(creator.getId().intValue());
		em.persist(outbox);
	}

}
