package com.avery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.adeptia.indigo.logging.Logger;
import com.avery.EmailManager;
import com.avery.services.OrderEmailQueueServices;

/**
 * @author Vishal
 *
 */
public class EmailUtils {

	static {
		System.setProperty("mail.smtp.host", "smtp.gmail.com");
		System.setProperty("mail.smtp.port", "465");
		System.setProperty("mail.transport.protocol", "smtp");
		System.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
	}

	/**
	 * Method to forward eml file as Email
	 * 
	 * @param fromUserEmailID
	 * @param fromUserPassword
	 * @param toUserEmailIDList
	 * @param emlFilePath
	 * @param additionalBodyContent
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void forwardEML(String fromUserEmailID,
			String fromUserPassword, String toUserEmailIDList,
			String emlFilePath, String additionalBodyContent, Logger log)
			throws MessagingException, IOException {
		Transport transport = null;
		InputStream source = null;
		try {
			log.debug("additional body content is:\"" + additionalBodyContent
					+ "\".");
			log.debug("Creating session at:\"" + EmailManager.getDate() + "\".");
			Session mailSession = Session.getDefaultInstance(
					System.getProperties(), null);
			log.debug("Session has been created at:\"" + EmailManager.getDate()
					+ "\".");
			File emlFile = new File(emlFilePath);
			source = new FileInputStream(emlFile);
			MimeMessage message = new MimeMessage(mailSession, source);
			transport = mailSession.getTransport();
			log.debug("Connecting transport for  user:\"" + fromUserEmailID
					+ " session at:\"" + EmailManager.getDate() + "\".");
			transport.connect(fromUserEmailID, fromUserPassword);
			log.debug("Transport connected successsfully for  user:\""
					+ fromUserEmailID + " session at:\""
					+ EmailManager.getDate() + "\".");
			InternetAddress[] toUserIdArray = InternetAddress
					.parse(toUserEmailIDList);
			message.setRecipients(Message.RecipientType.TO, toUserIdArray);
			addMessageContent(message, additionalBodyContent);
			message.saveChanges();
			log.debug("Forwading eml file at:\"" + EmailManager.getDate()
					+ "\".");
			transport.sendMessage(message, message.getAllRecipients());
			log.debug("eml file has been forwarded successfully at:\""
					+ EmailManager.getDate() + "\".");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
				}
			}
			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}

	/**
	 * Method to add custom content in mail body
	 * 
	 * @param mail
	 * @param forwardMailBody
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void addMessageContent(MimeMessage mail,
			String forwardMailBody) throws MessagingException, IOException {
		Object content = mail.getContent();
		if (content.getClass().isAssignableFrom(MimeMultipart.class)) {
			MimeMultipart mimeMultipart = (MimeMultipart) content;
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(forwardMailBody);
			mimeMultipart.addBodyPart(messageBodyPart, 0);
		}
	}

	/**
	 * Method to forward eml file in Email
	 * 
	 * @param emailQueueId
	 * @param fromUserEmailID
	 * @param fromUserPassword
	 * @param toUserEmailIDList
	 * @param additionalBodyContent
	 * @throws Exception
	 */
	public static void forwardEMLFileAsMail(int emailQueueId,
			String fromUserEmailID, String fromUserPassword,
			String toUserEmailIDList, String additionalBodyContent, Logger log)
			throws Exception {
		log.debug("getting eml file location for emailQueueId:\""
				+ emailQueueId + "\" at:\"" + EmailManager.getDate() + "\".");
		String emlFilePath = OrderEmailQueueServices
				.getEMLFileLocation(emailQueueId);
		log.debug("Got eml file location:\"" + emlFilePath
				+ "\" for emailQueueId:\"" + emailQueueId + "\" at:\""
				+ EmailManager.getDate() + "\".");
		log.debug("Forwarding eml file in Email for fromUserEmailID:\""
				+ fromUserEmailID + "\" to toUserEmailIDList:\""
				+ toUserEmailIDList + "\" at:\"" + EmailManager.getDate()
				+ "\".");
		forwardEML(fromUserEmailID, fromUserPassword, toUserEmailIDList,
				emlFilePath, additionalBodyContent, log);

	}

}
