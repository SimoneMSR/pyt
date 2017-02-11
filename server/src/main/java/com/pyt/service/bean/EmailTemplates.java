package com.pyt.service.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.pyt.model.Language;

import Enums.EmailTemplateName;





/**
 * 
 * Template email
 * 
 * @author dometec
 * 
 */

@Table(name = "emailtemplates")
@SuppressWarnings("serial")
public class EmailTemplates implements Serializable {
	
	private String name;


	@NotNull
	@Column(length = 1000, nullable = false)
	private String header;

	@NotNull
	@Column(length = 1000, nullable = false)
	private String footer;

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyJoinColumn(name = "name")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "body", length = 6000, nullable = false)
	@CollectionTable(name = "emailtemplates_bodies", joinColumns = @JoinColumn(name = "partnerid", referencedColumnName = "partnerid") )
	private Map<EmailTemplateName, String> templatebodies;

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyJoinColumn(name = "name")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "subject", length = 600, nullable = false)
	@CollectionTable(name = "emailtemplates_subjects", joinColumns = @JoinColumn(name = "partnerid", referencedColumnName = "partnerid") )
	private Map<EmailTemplateName, String> templatesubjects;

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyJoinColumn(name = "langcode")
	@Column(name = "yaml", length = 4000, nullable = false)
	@CollectionTable(name = "emailtemplates_translation", joinColumns = @JoinColumn(name = "partnerid", referencedColumnName = "partnerid") )
	private Map<Language, String> translations;



	@Version
	private int version;

	public EmailTemplates() {
		header = "";
		footer = "";
		templatesubjects = new HashMap<>();
		templatebodies = new HashMap<>();
		translations = new HashMap<>();
	}
	
	


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getFooter() {
		return footer;
	}

	public String getHeader() {
		return header;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Map<EmailTemplateName, String> getTemplatebodies() {
		return templatebodies;
	}

	public Map<Language, String> getTranslations() {
		return translations;
	}

	public void setTemplatebodies(Map<EmailTemplateName, String> templatebodies) {
		this.templatebodies = templatebodies;
	}

	public void setTranslations(Map<Language, String> translations) {
		this.translations = translations;
	}



	public Map<EmailTemplateName, String> getTemplatesubjects() {
		return templatesubjects;
	}

	public void setTemplatesubjects(Map<EmailTemplateName, String> templatesubjects) {
		this.templatesubjects = templatesubjects;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result ;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		return name.equals(((EmailTemplates)obj).name);
	}

	@Override
	public String toString() {
		return "EmailTemplates [header=" + header + ", footer=" + footer + ", templatebodies="
				+ templatebodies + ", translations=" + translations  + ", version="
				+ version + "]";
	}

}
