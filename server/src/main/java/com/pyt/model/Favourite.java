package com.pyt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Favourite")
public class Favourite implements Serializable{
	
	/** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFavourite;
    
    @NotNull
    private Date date;
    
    private Date deleted;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="memberId", updatable=true)
    private Member member;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="announcementId", updatable=true)
    private Announcement announcement;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public int getIdFavourite() {
		return idFavourite;
	}
    
    
}
