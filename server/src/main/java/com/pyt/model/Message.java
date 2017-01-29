package com.pyt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Message")
public class Message implements Serializable{

	/** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMessage;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creatorId", updatable=false)
    private Member creator;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="MessageRecipient",
	        joinColumns=@JoinColumn(name="messageId"),
	        inverseJoinColumns=@JoinColumn(name="recipientId"))
	private Collection<Member> recipients;
    
    @NotNull
    private String subject;
    
    private String object;
    
    @NotNull
    private Date date;
    
    private Integer conversationId;
   
    

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public Member getCreator() {
		return creator;
	}

	public void setCreator(Member creator) {
		this.creator = creator;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public Collection<Member> getRecipients() {
		return recipients;
	}

	public void setRecipients(Collection<Member> recipients) {
		this.recipients = recipients;
	}
	
	
    
    
}
