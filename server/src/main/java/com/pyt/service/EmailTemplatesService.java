package com.pyt.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.pyt.model.Language;
import com.pyt.service.bean.EmailTemplates;

import Enums.EmailTemplateName;



@Stateless
public class EmailTemplatesService {
	
	public static Language ita = new Language("IT","italian",1);
	

	public EmailTemplates getTemplate() {

		EmailTemplates template= new EmailTemplates();
		template.setFooter("Comelit - Iot");
		template.setHeader("Comelit Iot");
		template.getTemplatebodies().put(EmailTemplateName.ACTIVATIONCODE, "${translation[\"ACTIVATION_TEXT\"]} <a href=\"http://localhost:4200/activate?email=${user.email}&hash=${user.hash}\">link</a>");
		template.getTemplatesubjects().put(EmailTemplateName.ACTIVATIONCODE, "${translation[\"ACTIVATION_SUBJECT\"]}");
		template.getTemplatebodies().put(EmailTemplateName.ACTIVATED, "${translation[\"ACTIVATED_TEXT\"]}");
		template.getTemplatesubjects().put(EmailTemplateName.ACTIVATED, "${translation[\"ACTIVATED_SUBJECT\"]}");
		template.getTranslations().put(ita, "{ACTIVATION_TEXT : 'Per attivare il tuo account clicca su questo ', "+
					"ACTIVATION_SUBJECT: 'Comelit Iot - attivazione email'," +
					"ACTIVATED_TEXT : 'Account attivato. ', "+
					"ACTIVATED_SUBJECT : 'Conferma attivazione account'}");
		return template;
	}

}
