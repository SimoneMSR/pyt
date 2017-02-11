package com.pyt.service.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.pyt.model.Language;
import com.pyt.model.Member;



public class FreeMarkerDataContainer {

	/**
	 * Utente per i template relativi all'account (creazione, cambio pass, primo
	 * login)
	 */
	private Member user;

	/**
	 * Comuni
	 */
	private Object translation;
	private String resids;
	private String period;
	private Language language;
	private String docid;
	private FieldChange[] updates;

	public FreeMarkerDataContainer(Language language) {
		this.language = language;
	}


	public void setUser(Member user) {
		this.user = user;
	}

	public Member getUser() {
		return user;
	}


	public Object getTranslation() {
		return translation;
	}

	public void setTranslation(Object translation) {
		this.translation = translation;
	}



	public void setResIds(String resids) {
		this.resids = resids;
	}

	public String getResids() {
		return resids;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "FreeMarkerDataContainer [...]";
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public void setUpdates(FieldChange[] updates) {
		this.updates = updates;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(Locale.ITALIAN);
		for (FieldChange fd : updates) {
			if (fd.oldValue instanceof LocalDateTime)
				fd.oldValue = dateFormatter.format((LocalDateTime) fd.oldValue);
			if (fd.newValue instanceof LocalDateTime)
				fd.newValue = dateFormatter.format((LocalDateTime) fd.newValue);
		}

	}

	public FieldChange[] getUpdates() {
		return updates;
	}

}
