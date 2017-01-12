package com.pyt.dao;

import java.util.ArrayList;
import java.util.List;

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

public class MessageDao extends BaseDao<Message, Message_>{

		public List<Message> getInboxOrderebByMemberId(int memberId){
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Message> criteria = cb.createQuery(Message.class);
	        Root<Inbox> messageTo = criteria.from(Inbox.class);
	        Root<Message> message = criteria.from(Message.class);
	        List<Predicate> conditions= new ArrayList<Predicate>();
	        conditions.add(cb.equal(messageTo.get(Inbox_.receiverId),memberId));
	        conditions.add(cb.equal(arg0, arg1))
	        criteria.select(message).where();
	        return null;
		}
}
