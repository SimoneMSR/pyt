package com.pyt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="QuarterAnnouncement",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idQuarter"))
	private Collection<Quarter> quarters;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AnnouncementTag",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idTag"))
	private Set<Tag> tags;
	
	@Transient
	@Access(AccessType.PROPERTY)
	private Collection<Integer> tagsIds;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="announcement")
	private List<Comment> comments;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AnnouncementForDepartment",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idDepartment"))
	private Collection<Department> departments;
	
	@Transient
	@Access(AccessType.PROPERTY)
	private Collection<Integer> departmentsIds;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AnnouncementForBeing",
	        joinColumns=@JoinColumn(name="idAnnouncement"),
	        inverseJoinColumns=@JoinColumn(name="idDepartment"))
	private Collection<Being> beings;
	
	@Transient
	@Access(AccessType.PROPERTY)
	private Collection<Integer> beingsIds;
	
	private int cathegory;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creatorId", updatable=false)
    private Member creator;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="announcement")
	private List<Like> likes;
	
	@Transient
	public int likesCount;
	
	@Transient
	public int dislikeCount;
	
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


	public List<Comment> getComments() {
		return comments;
	}


	public List<Like> getLikes() {
		return likes;
	}

	public Collection<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Collection<Department> departments) {
		this.departments = departments;
	}

	public Collection<Being> getBeings() {
		return beings;
	}

	public void setBeings(Collection<Being> beings) {
		this.beings = beings;
	}

	public Collection<Integer> getTagsIds() {
		return tags.stream().map(t -> t.getIdTag()).collect(Collectors.toList());
	}

	public Collection<Integer> getDepartmentsIds() {
		return departments.stream().map(d -> d.getIdDepartment()).collect(Collectors.toList());
	}

	public Collection<Integer> getBeingsIds() {
		return beings.stream().map(b -> b.getIdBeing()).collect(Collectors.toList());
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	
	
	
	
}
