package com.pyt.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeBodyPart;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
public class Email implements Serializable {

	@NotNull
	public String subject;

	@NotNull
	public String body;

	@NotNull
	public String toAddr;

	@NotNull
	public String fromAddr;

	public String ccAddr;
	public String ccnAddr;
	public List<MimeBodyPart> attachments;
	public boolean html;

	public Email() {
		fromAddr = "info@osys.it";
		attachments = new ArrayList<>();
	}

	public Email setBody(String body) {
		this.body = body;
		return this;
	}

	public Email setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public Email setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
		return this;
	}

	public Email setToAddr(String toAddr) {
		this.toAddr = toAddr;
		return this;
	}

	public Email setCcAddr(String ccAddr) {
		this.ccAddr = ccAddr;
		return this;
	}

	public Email setCcnAddr(String ccnAddr) {
		this.ccnAddr = ccnAddr;
		return this;
	}

	public Email addAttachment(MimeBodyPart attachment) {
		this.attachments.add(attachment);
		return this;
	}

	public Email setHtml(boolean html) {
		this.html = html;
		return this;
	}

	@Override
	public String toString() {
		return "Email [subject=" + subject + ", toAddr=" + toAddr + ", fromAddr=" + fromAddr + ", ccAddr=" + ccAddr
				+ ", ccnAddr=" + ccnAddr + ", attachments=" + attachments.size() + ", html=" + html + "]";
	}

}