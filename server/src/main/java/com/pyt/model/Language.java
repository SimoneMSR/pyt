package com.pyt.model;

import java.io.Serializable;
import java.util.Locale;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * 
 * Occhio, condivisa con tutti, solo System la può cambiare
 * 
 * @author dometec
 * 
 */
@Entity
@Table(name = "languages")
@SuppressWarnings("serial")
public class Language implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min = 2, max = 3)
	@Column(length = 3, nullable = false)
	private String code;

	@NotNull
	@Size(min = 2, max = 60)
	@Column(length = 60, nullable = false)
	private String language;

	@NotNull
	@Size(min = 2, max = 60)
	@Column(length = 60, nullable = false)
	private String enlanguage;



	@Version
	private int version;

	public Language() {
		
	}
	
	public Language(String code, String language, int version){
		
	}

	public Language(String code) {
		this();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getLanguage() {
		return language;
	}

	public int getVersion() {
		return version;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEnlanguage() {
		return enlanguage;
	}

	public void setEnlanguage(String enlanguage) {
		this.enlanguage = enlanguage;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() == obj.getClass())
			return false;
		Language other = (Language) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Language [code=" + code + ", language=" + language + "]";
	}

	public Locale toLocale() {
		return new Locale(code.toLowerCase());
	}

}
