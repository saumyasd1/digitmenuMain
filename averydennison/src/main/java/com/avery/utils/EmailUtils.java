package com.avery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
//import com.adeptia.service.BodyPart;
import javax.mail.BodyPart;
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
		//#CSAUGDS-41[Support multiple emailID in CSREmail field of PartnerProfile]
		if(toUserEmailIDList.contains(",")){
			String toMail[]=toUserEmailIDList.split(",");
			for(int i=0;i<toMail.length;i++){
				if(toMail[i] != null && !toMail[i].isEmpty()){
					forwardEML(fromUserEmailID, fromUserPassword, toMail[i],
							emlFilePath, additionalBodyContent, additionalSubjectContent,
							log);
				}
			}
		}else{
			if(toUserEmailIDList != null && !toUserEmailIDList.isEmpty()){
				forwardEML(fromUserEmailID, fromUserPassword, toUserEmailIDList,
						emlFilePath, additionalBodyContent, additionalSubjectContent,
						log);
			}
		}
		

	}
	/**
	 * Method for sending the email with attachments
	 * @param fromUserName
	 * @param fromUserPass
	 * @param toUserName
	 * @param subject
	 * @param mailBody
	 * @param fileNames
	 * @author Rakesh
	 */
	public static boolean sendEmailWithAttachment(final String fromUserName, final String fromUserPass, String toUserName,
			String subject, String mailBody, String[] fileNames) {
		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");

			// Get the Session object.
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromUserName, fromUserPass);
				}
			});

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromUserName));
			
			
			String[] recipientList = toUserName.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient : recipientList) {
			    recipientAddress[counter] = new InternetAddress(recipient.trim());
			    counter++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			// Set To: header field of the header.
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUserName));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setText(mailBody,"utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);
			if (fileNames != null && fileNames.length > 0) {
				for (int i = 0; i < fileNames.length; i++) {
					String fileName = fileNames[i];
					if(fileName!=null && !"".equals(fileName.trim())){
						addAttachment(multipart, fileName);
					}
				}
			}
			message.setContent(multipart);
			Date acknowledgementDate = new Date();
			message.setSentDate(acknowledgementDate);
			Transport.send(message);
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			try {
				throw new MessagingException(
						"Error while sending mail from:\"" + fromUserName + "\" to:\"" + toUserName + "\".", mex);
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method for adding the attachments
	 * @param multipart
	 * @param filename
	 * @throws MessagingException
	 * @author Rakesh
	 */
	private static void addAttachment(Multipart multipart, String filepath) throws MessagingException {
		DataSource source = new FileDataSource(filepath);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		String fileName = "";
			fileName = filepath.substring(filepath.lastIndexOf(new Character(File.separatorChar).toString())+1,filepath.length());
			System.out.println(fileName);
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);
	}

}
