package com.avery.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.OrderEmailQueue;
import com.avery.dao.OrderFileAttachment;
import com.avery.utils.HibernateUtil;

public class EmailAttachmentService {

	static Logger log = Logger.getLogger(EmailAttachmentService.class.getName());
	
	public void insertIntoEmailAttachment(MimeBodyPart part, String filePath,
			int emailqueueid, String fileName, String fileExtension) {

		String contentType = "";
		try {
			contentType = part.getContentType();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		log.debug("save mail attachments in file and name in database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = (OrderEmailQueue) session.get(
				OrderEmailQueue.class, emailqueueid);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(
				emailqueueid, fileName, fileExtension, filePath, contentType);
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		log.debug("successfully saved mail attachments in file and name in database");
		session.getTransaction().commit();
		session.close();
	}

	public void insertUnzippedFile(String fileName, String filePath,
			int emailqueueid) {
		String contentType = "";
		if (fileName.contains("\'")) {
			fileName.replace("\'", "\\'");
		}
		String fileExtension = "";
		if (fileName.contains(".")) {
			fileExtension = fileName.substring(fileName.lastIndexOf("."));
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = (OrderEmailQueue) session.get(
				OrderEmailQueue.class, emailqueueid);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(
				emailqueueid, fileName, fileExtension, filePath, contentType);
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		session.getTransaction().commit();
		session.close();
		log.debug("Unzipped file inserted in the email attachment table");
	}

}
