package com.pyt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Conversation")
public class Conversation {
	
	/** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConversation;
    
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Message",
	    joinColumns=@JoinColumn(name="conversationId"),
	    inverseJoinColumns=@JoinColumn(name="recipientId"))
    private List<Message> messages;
	
	private Date lastMessageDate;
	
	private String subject;

	public int getIdConversation() {
		return idConversation;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Date getLastMessageDate() {
		return lastMessageDate;
	}

	public void setLastMessageDate(Date lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public boolean equals(Object obj){
		if(obj ==null)
			return false;
		if(obj == this)
			return true;
		if(obj instanceof Conversation){
			return ((Conversation) obj).getIdConversation() == this.idConversation;
		}else
			return false;
	}
	
	

}
