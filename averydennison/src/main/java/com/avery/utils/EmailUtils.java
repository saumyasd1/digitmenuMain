package com.avery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.EmailManager;
import com.avery.services.OrderEmailQueueServices;

/**
 * @author Vishal
 * 
 */
public class EmailUtils {

	static Properties properties;
	static {
		properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
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
		final String username = fromUserEmailID;
		final String password = fromUserPassword;
		try {
			log.debug("additional body content is:\"" + additionalBodyContent
					+ "\".");
			log.debug("Creating session at:\"" + EmailManager.getDate() + "\".");

			// Get the Session object.
			Session mailSession = Session.getInstance(properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			log.debug("Session has been created at:\"" + EmailManager.getDate()
					+ "\".");
			File emlFile = new File(emlFilePath);
			source = new FileInputStream(emlFile);
			MimeMessage message = new MimeMessage(mailSession, source);
			InternetAddress[] toUserIdArray = InternetAddress
					.parse(toUserEmailIDList);
			message.setRecipients(Message.RecipientType.TO, toUserIdArray);
			addMessageContent(message, additionalBodyContent);
			
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));
			Date acknowledgementDate = new Date();
			message.setSentDate(acknowledgementDate); 		
						
			message.saveChanges();
			log.debug("Forwading eml file at:\"" + EmailManager.getDate()
					+ "\".");
			Transport.send(message);
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
		
		File emilFile = new File(emlFilePath);
		String fileLocation = emlFilePath.substring(0, emlFilePath.lastIndexOf(File.separatorChar)); 
		String fileName = emilFile.getName();
		String fileNameWithoutExtension = FilenameUtils.getBaseName(fileName);
		String fileextension = FilenameUtils.getExtension(fileName); 
		if(fileextension.equalsIgnoreCase("html")){
			fileextension = "eml";
		}
		emlFilePath = fileLocation+File.separatorChar+fileNameWithoutExtension+"."+fileextension; 
		forwardEML(fromUserEmailID, fromUserPassword, toUserEmailIDList,
				emlFilePath, additionalBodyContent, log);

	}

}
