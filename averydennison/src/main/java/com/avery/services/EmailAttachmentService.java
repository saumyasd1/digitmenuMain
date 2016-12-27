package com.avery.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.OrderEmailAttachment;
import com.avery.dao.OrderEmailQueue;
import com.avery.dao.OrderFileAttachment;
import com.avery.utils.HibernateUtil;

public class EmailAttachmentService {
	
	public void insertIntoEmailAttachment(MimeBodyPart part, String filePath, int emailqueueid){
		
		/*String contentType = part.getContentType().getMediaType().toString();
		String fileName = part.getName();*/
		String contentType = "";
		String fileName = "";
		try {
			contentType = part.getContentType();
			fileName = part.getFileName(); 
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String fileExtension = "";
		if(fileName.contains(".")){
			fileExtension = fileName.substring(fileName.lastIndexOf("."));
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue=(OrderEmailQueue)session.get(OrderEmailQueue.class, emailqueueid);
		//System.out.println("Content Type : "+contentType);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(emailqueueid, fileName, fileExtension, filePath, contentType);
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		session.getTransaction().commit();
		session.close();
		System.out.println("Data inserted in the email attachment table");
		
	}
	
	
	public void insertUnzippedFile(String fileName, String filePath, int emailqueueid){
		String contentType = "";
		if(fileName.contains("\'")){
			fileName.replace("\'", "\\'");
		}
		String fileExtension = "";
		if(fileName.contains(".")){
			fileExtension = fileName.substring(fileName.lastIndexOf("."));
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue=(OrderEmailQueue)session.get(OrderEmailQueue.class, emailqueueid);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(emailqueueid, fileName, fileExtension, filePath, contentType);
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		session.getTransaction().commit();
		session.close();
		System.out.println("Unzipped file inserted in the email attachment table");
	}

}
