package com.avery.bao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.DataConversionUtils;
import com.avery.EmailManager;
import com.avery.dao.OrderEmailQueue;
import com.avery.dao.OrderFileAttachment;
import com.avery.services.EmailQueueService;
import com.avery.utils.HibernateUtil;

public class EmailFetch {

	String uniqueID;
	String messageId;

	public void messageFetch(Message message, Folder folder,
			Object objectContent) throws Exception {
		UIDFolder uf = (UIDFolder) folder;
		String msgId[] = message.getHeader("Message-Id");
		uniqueID = msgId[0];
		// Create the directory for saving the email information
		uniqueID = uniqueID.replaceAll("[\\/:*?\"<>|]", "");
		String dir = createDirectory(uniqueID, EmailManager.directoryLocation);
		try {
			EmailManager.log.debug("Writing CompleteEmail.eml file at:\""
					+ EmailManager.getDate() + "\".");
			message.writeTo(new FileOutputStream(new File(dir + "/"
					+ "CompleteEmail" + ".eml")));
			EmailManager.log
					.debug("CompleteEmail.eml file has been written successfully at:\""
							+ EmailManager.getDate() + "\".");
			DataConversionUtils dataConversionUtils = new DataConversionUtils();
			// writing html file
			EmailManager.log.debug("Writing CompleteEmail.html file at:\""
					+ EmailManager.getDate() + "\".");
			dataConversionUtils.generateHTMLFile(dir, "CompleteEmail", message);
			EmailManager.log
					.debug("CompleteEmail.html file has been written successfully at:\""
							+ EmailManager.getDate() + "\".");
			// writing excel file
			EmailManager.log.debug("Writing CompleteEmail.xls file at:\""
					+ EmailManager.getDate() + "\".");
			dataConversionUtils.generateExcelFile(dir, "CompleteEmail", dir,
					"CompleteEmail", "xls");
			EmailManager.log
					.debug("CompleteEmail.xls file has been written successfully at:\""
							+ EmailManager.getDate() + "\".");
			// writing pdf file
			// dataConversionUtils.generatePDFfromHTML(dir,
			// "CompleteEmail.html");
		} catch (Exception e) {

		}
		Date currentDate = new Date();
		String subject = message.getSubject();
		Address[] froms = message.getFrom();
		String sender = froms == null ? null : ((InternetAddress) froms[0])
				.getAddress();
		String mailbodypath = dir + "/MailBody.pdf";
		Date receivedDate = message.getReceivedDate();
		String cc = "";
		if (message.getRecipients(Message.RecipientType.CC) != null) {
			List ccList = Arrays.asList(message
					.getRecipients(Message.RecipientType.CC));
			for (Iterator iterator = ccList.iterator(); iterator.hasNext();) {
				InternetAddress ccAddress = (InternetAddress) iterator.next();
				cc = cc + ";" + ccAddress.getAddress();
			}
			if (cc.startsWith(";")) {
				cc = cc.substring(cc.indexOf(";") + 1);
			}
		}
		String to = "";
		List toList = Arrays.asList(message
				.getRecipients(Message.RecipientType.TO));
		if (message.getRecipients(Message.RecipientType.TO) != null) {
			for (Iterator iterator = toList.iterator(); iterator.hasNext();) {

				InternetAddress toAddress = (InternetAddress) iterator.next();
				to = to + ";" + toAddress.getAddress();
			}
			if (to.startsWith(";")) {
				to = to.substring(to.indexOf(";") + 1);
			}
		}
		EmailQueueService emailQueueService = new EmailQueueService();
		EmailManager.log
				.debug("Inserting  emailqueue details in table:\"orderemailqueue\" for sender:\""
						+ sender
						+ "\" having subject:\""
						+ subject
						+ " at:\""
						+ EmailManager.getDate() + "\".");
		int emailqueueid = emailQueueService.insertData(subject, sender,
				mailbodypath, receivedDate, currentDate, cc, to, "1");
		EmailManager.log
				.debug("Emailqueue details has been inserted in table:\"orderemailqueue\" for emailqueueid:\""
						+ emailqueueid + "\".");
		// Write the code here for inserting into the emailqueue table and get
		// the email queue id
		AttachmentHandling attachmentHandling = new AttachmentHandling();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = (OrderEmailQueue) session.get(
				OrderEmailQueue.class, emailqueueid);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(
				emailqueueid, "CompleteEmail.html", ".html", dir, "HTML");
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		session.getTransaction().commit();
		session.close();
		attachmentHandling.extractAttachment(dir, emailqueueid, message);
	}

	public String createDirectory(String uniqueID, String directoryLocation)
			throws IOException {
		// TODO Auto-generated method stub
		EmailManager.log.debug("Creating directory:\"" + directoryLocation
				+ File.separatorChar + uniqueID + " at:\""
				+ EmailManager.getDate() + "\".");
		File file = new File(directoryLocation + File.separatorChar + uniqueID);
		if (!file.exists()) {
			if (file.mkdirs()) {
				EmailManager.log.debug("Directory:\"" + directoryLocation
						+ File.separatorChar + uniqueID + " has been created at:\""
						+ EmailManager.getDate() + "\".");
			} else {
				throw new IOException("Failed to create directory:\""
						+ directoryLocation + File.separatorChar + uniqueID
						+ "\".");
			}
		}
		
		return file.getAbsolutePath();
	}

}
