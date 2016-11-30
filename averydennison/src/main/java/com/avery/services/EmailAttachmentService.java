package com.avery.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aspose.email.Attachment;
import com.avery.dao.OrderEmailAttachment;
import com.avery.utils.HibernateUtil;

public class EmailAttachmentService {
	
	public void insertIntoEmailAttachment(Attachment attachment, String filePath, int emailqueueid){
		
		String fileName = attachment.getName();
		String fileExtension = "";
		if(fileName.contains(".")){
			fileExtension = fileName.substring(fileName.lastIndexOf("."));
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailAttachment orderEmailAttachment = new OrderEmailAttachment(emailqueueid+"", fileName, fileExtension, filePath);
		session.persist(orderEmailAttachment);
		session.getTransaction().commit();
		session.close();
		System.out.println("Data inserted in the email attachment table");
		
	}
	
	
	public void insertUnzippedFile(String fileName, String filePath, int emailqueueid){
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
		OrderEmailAttachment orderEmailAttachment = new OrderEmailAttachment(emailqueueid+"", fileName, fileExtension, filePath);
		session.persist(orderEmailAttachment);
		session.getTransaction().commit();
		session.close();
		System.out.println("Unzip file inserted in the email attachment table");
	}

}
