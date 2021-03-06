package com.pyt.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Quarter")
public class Quarter implements Serializable{

    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuarter;
    
    @NotNull
    @NotEmpty
    private String name;
    
	@ManyToMany(mappedBy = "quarters", fetch=FetchType.LAZY)
	private Set<Announcement> announcements;
	
	@OneToMany(mappedBy="quarter",fetch=FetchType.LAZY)
	private Set<Member> members;
	
	@Transient
	public int memberCount;
	
	@Transient
	public int announcementCount;

	public Long getId() {
		return idQuarter;
	}

	public void setId(Long id) {
		this.idQuarter = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Set<Announcement> announcements) {
		this.announcements = announcements;
	}
	
	
	
	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public Quarter(){}
	public Quarter(int id){
		this.idQuarter = (long)id;
	}
}
