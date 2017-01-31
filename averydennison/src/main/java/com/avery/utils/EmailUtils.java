package com.avery.utils;

import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;

import com.avery.services.OrderEmailQueueServices;

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
			String emlFilePath, String additionalBodyContent)
			throws MessagingException, IOException {
		Transport transport = null;
		InputStream source = null;
		try {
			Session mailSession = Session.getDefaultInstance(
					System.getProperties(), null);
			File emlFile = new File(emlFilePath);
			source = new FileInputStream(emlFile);
			MimeMessage message = new MimeMessage(mailSession, source);
			transport = mailSession.getTransport();
			transport.connect(fromUserEmailID, fromUserPassword);
			InternetAddress[] toUserIdArray = InternetAddress
					.parse(toUserEmailIDList);
			message.setRecipients(Message.RecipientType.TO, toUserIdArray);
			addMessageContent(message, additionalBodyContent);
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
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
		System.out.println("Message sent successfully.");
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
	 * @throws Exception
	 * 
	 */
	public static void forwardEMLFileAsMail(int emailQueueId,
			String fromUserEmailID, String fromUserPassword,
			String toUserEmailIDList, String additionalBodyContent)
			throws Exception {
		String emlFilePath = OrderEmailQueueServices
				.getEMLFileLocation(emailQueueId);
		forwardEML(fromUserEmailID, fromUserPassword, toUserEmailIDList,
				emlFilePath, additionalBodyContent);

	}

}
