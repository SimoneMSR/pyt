package com.pyt.service;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pyt.service.bean.Email;




@Stateless
public class MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Asynchronous
	public void send(@Valid @NotNull Email email) {

		try {
			Context initCtx = new InitialContext();
			Session session = (Session) initCtx.lookup("java:jboss/mail/Pyt");

			MimeMessage message = new MimeMessage(session);

			try {
				message.setFrom(new InternetAddress(email.fromAddr));
			} catch (AddressException e) {
				String msg = "Email from non valida: " + email.fromAddr;
				logger.warn(msg);
				if (email.subject != null)
					email.subject += " (" + msg + ")";
				else
					email.subject = "(" + msg + ")";
			}

			InternetAddress[] addressesTo = InternetAddress.parse(email.toAddr);
			message.setRecipients(Message.RecipientType.TO, addressesTo);

			if (email.ccAddr != null) {
				InternetAddress[] addressesCC = InternetAddress.parse(email.ccAddr);
				message.setRecipients(Message.RecipientType.CC, addressesCC);
			}

			if (email.ccnAddr != null) {
				InternetAddress[] addressesCCN = InternetAddress.parse(email.ccnAddr);
				message.setRecipients(Message.RecipientType.BCC, addressesCCN);
			}

			if (email.subject != null)
				message.setSubject(email.subject, "UTF-8");

			Multipart multipart = new MimeMultipart("mixed");

			BodyPart messageHtmlPart = new MimeBodyPart();

			if (email.html)
				messageHtmlPart.setContent(email.body, "text/html; charset=UTF-8");
			else
				messageHtmlPart.setContent(email.body, "text/plain; charset=UTF-8");

			multipart.addBodyPart(messageHtmlPart);
			message.setContent(multipart);

			for (MimeBodyPart part : email.attachments)
				multipart.addBodyPart(part);

			Transport.send(message);

			logger.info("Email sent {}.", email);

		} catch (Exception e) {
			logger.error("Error sending email: " + email.toString(), e);
		}

	}
}
