package com.avery.bao;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.avery.EmailManager;
import com.avery.services.AcknowledgementService;

public class SendingAcknowledgement {

	public void sendAcknowledgement(int emailqueueid, EmailManager emailManager) throws MessagingException {
		String to = "";// get the email address by calling a method
		AcknowledgementService acknowledgementService = new AcknowledgementService();
		to = acknowledgementService.getAcknowledgementEmailID(emailqueueid);
		String host = emailManager.smtpHost;
		int port = emailManager.smtpPort;
		final String username = emailManager.username;
		final String password = emailManager.password;

		String subject = "Order Acknowledgement";
		String body = "This is a system generated Email, please do not reply.\n\n"
				+ "Dear Customer,\n\n"
				+ "Thank you for placing your orders to Avery Dennison. Our customer service team is processing your orders and will contact "
				+ "you for more details or delivery schedule. Should have any enquiry, please feel free to contact our customer service "
				+ "representatives for assistance.\n\n"
				+ "Your EmailQueue Id for Order Tracking is : "
				+ emailqueueid
				+ "\n\nBest Regards,\nAvery Dennison Customer Service Team\n\n"
				+ "---------------------------------------------------------------------\n"
				+ "The information transmitted is intended only for the person or entity to which it is addressed and may contain confidential "
				+ "and/or privileged material. Any review, retransmission, dissemination or other use of, or taking of any action in reliance "
				+ "upon, this information by persons or entities other than the intended recipient is prohibited. If you received this in error,"
				+ " please contact the sender and delete the material from any computer."
				+ "\n---------------------------------------------------------------------";

		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");

			// Get the Session object.
			Session session = Session.getInstance(properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);

			Date acknowledgementDate = new Date();

			message.setSentDate(acknowledgementDate);
			// Send message
			Transport.send(message);
			EmailManager.log.debug("Sent message successfully.");
			acknowledgementService.updateAcknowledgementDate(emailqueueid,
					acknowledgementDate);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw new MessagingException("Error while sending mail from:\""
					+ username + "\" to:\"" + to + "\".", mex);
		}
	}
}
