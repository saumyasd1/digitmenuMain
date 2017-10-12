package com.avery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.EmailManager;
import com.avery.services.OrderEmailQueueServices;

/**
 * @author Vishal
 * 
 */
public class EmailUtils {


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
	public void forwardEML(String fromUserEmailID,
			String fromUserPassword, String toUserEmailIDList,
			String emlFilePath, String additionalBodyContent,
			String additionalSubjectContent, Logger log)
			throws MessagingException, IOException {
		Transport transport = null;
		InputStream source = null;
		final String username = fromUserEmailID;
		final String password = fromUserPassword;
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			
			log.debug("additional body content is:\"" + additionalBodyContent
					+ "\".");
			log.debug("additional subject content is:\""
					+ additionalSubjectContent + "\".");
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
			Message message = new MimeMessage(mailSession, source);

			log.debug("Creating forwarding mail object at:\""
					+ EmailManager.getDate() + "\".");
			Message forward = new MimeMessage(mailSession);
			log.debug("Setting parameterts for forwarding mail object at:\""
					+ EmailManager.getDate() + "\".");
			InternetAddress[] toUserIdArray = InternetAddress
					.parse(toUserEmailIDList);
			forward.setRecipients(Message.RecipientType.TO, toUserIdArray);
			forward.setSubject(additionalSubjectContent + message.getSubject());
			forward.setFrom(new InternetAddress(username));
			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// set custom message
			messageBodyPart2.setText(additionalBodyContent);
			messageBodyPart.setContent(message, "message/rfc822");
			// Add part to multi part
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart);
			// Associate multi-part with message
			forward.setContent(multipart);

			Date acknowledgementDate = new Date();
			forward.setSentDate(acknowledgementDate);
			log.debug("Saving forwarding mail object changes at:\""
					+ EmailManager.getDate() + "\".");
			forward.saveChanges();
			log.debug("Forwarding mail object changes save at:\""
					+ EmailManager.getDate() + "\".");

			log.debug("Forwading eml file at:\"" + EmailManager.getDate()
					+ "\".");
			Transport.send(forward);
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
	 * Method to forward eml file in Email
	 * 
	 * @param emailQueueId
	 * @param fromUserEmailID
	 * @param fromUserPassword
	 * @param toUserEmailIDList
	 * @param additionalBodyContent
	 * @throws Exception
	 */
	public void forwardEMLFileAsMail(int emailQueueId,
			String fromUserEmailID, String fromUserPassword,
			String toUserEmailIDList, String additionalBodyContent,
			String additionalSubjectContent, Logger log) throws Exception {
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
		String fileLocation = emlFilePath.substring(0,
				emlFilePath.lastIndexOf(File.separatorChar));
		String fileName = emilFile.getName();
		String fileNameWithoutExtension = FilenameUtils.getBaseName(fileName);
		String fileextension = FilenameUtils.getExtension(fileName);
		if (fileextension.equalsIgnoreCase("html")) {
			fileextension = "eml";
		}
		emlFilePath = fileLocation + File.separatorChar
				+ fileNameWithoutExtension + "." + fileextension;
		if(toUserEmailIDList.contains(",")){
			String toMail[]=toUserEmailIDList.split(",");
			for(int i=0;i<toMail.length;i++){
				forwardEML(fromUserEmailID, fromUserPassword, toMail[i],
						emlFilePath, additionalBodyContent, additionalSubjectContent,
						log);
			}
		}else{
			forwardEML(fromUserEmailID, fromUserPassword, toUserEmailIDList,
					emlFilePath, additionalBodyContent, additionalSubjectContent,
					log);
		}
		

	}

}
