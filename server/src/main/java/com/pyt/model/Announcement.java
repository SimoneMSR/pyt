package com.pyt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Announcement")
public class Announcement implements Serializable{
	
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnnouncement;
	
	@NotNull
	@NotEmpty
	private String title;
	
	@NotNull
	@NotEmpty
	private String description;
	
	@ManyToMany
	@JoinTable(name="QuarterAnnouncement",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idQuarter"))
	private Collection<Quarter> quarters;
	
	@ManyToMany
	@JoinTable(name="AnnouncementTag",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idTag"))
	private Set<Tag> tags;
	
	private int cathegory;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creatorId", insertable=false)
    private Member creator;
	
	public Member getCreator() {
		return creator;
	}


	public void setCreator(Member creator) {
		this.creator = creator;
	}


	public int getCathegory() {
		return cathegory;
	}


	public void setCathegory(int cathegory) {
		this.cathegory = cathegory;
	}


	public int getIdAnnouncement() {
		return idAnnouncement;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Quarter> getQuarters() {
		return quarters;
	}

	public void setQuarters(Collection<Quarter> quarters) {
		this.quarters = quarters;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	public void setIdAnnouncement(int idAnnouncement) {
		this.idAnnouncement = idAnnouncement;
	}
	
	public Announcement(){}
	
	public Announcement(int id){
		this.idAnnouncement = id;
	}
	
	
	
}
