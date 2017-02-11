package com.pyt.service;

import java.util.Arrays;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.internet.MimeBodyPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pyt.model.Language;
import com.pyt.model.Member;
import com.pyt.service.bean.Email;
import com.pyt.service.bean.FreeMarkerDataContainer;

import Enums.EmailTemplateName;





@Stateless
public class NotificationService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private FreeMarkerService freeMarkerService;

	@Inject
	private MailService mailService;
	
	@Inject
	private EmailTemplatesService emailTemplatesService;
	
	/**
	 * Invia il link per attivare l'utente
	 * 
	 * @param reservation
	 */
	public void sendActivationLink(Member user) {
		FreeMarkerDataContainer dataContainer = new FreeMarkerDataContainer(emailTemplatesService.ita);
		dataContainer.setUser(user);
		sendEmail( user.getEmail(), null, emailTemplatesService.ita, dataContainer, EmailTemplateName.ACTIVATIONCODE);
	}
	
	public void sendEmail(String to, String cc, Language language, FreeMarkerDataContainer dataContainer,
			EmailTemplateName tname, MimeBodyPart... attachment) {

		try {

			Email email = freeMarkerService.compileForEmail(language, tname, dataContainer);
			String noreplymail = "noreply@comelit.it";

			email.setToAddr(to).setFromAddr(noreplymail).setHtml(true);

			if (!to.equals(cc))
				email.setCcAddr(cc);

			Arrays.asList(attachment).forEach(a -> email.addAttachment(a));

			mailService.send(email);

		} catch (Throwable e) {
			logger.error("Can't sent notification email (" + to + "/" + "/" + dataContainer + "/" + tname + "/" + language.getEnlanguage()
					+ ").", e);
		}

	}

}
