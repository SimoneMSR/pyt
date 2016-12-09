package com.pyt.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Tag")
public class Tag implements Serializable{
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTag;
    
	@NotNull
	@NotEmpty
	private String name;
    
    
	@ManyToMany(mappedBy = "tags")
	private Collection<Announcement> announcements;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Collection<Announcement> getAnnouncements() {
		return announcements;
	}


	public void setAnnouncements(Collection<Announcement> announcements) {
		this.announcements = announcements;
	}


	public int getIdTag() {
		return idTag;
	}


	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}
	
	public Tag(){}
	
	public Tag(int id){
		this.idTag=id;
	}
}
