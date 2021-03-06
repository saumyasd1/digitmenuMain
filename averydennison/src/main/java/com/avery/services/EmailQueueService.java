package com.avery.services;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.EmailManager;
import com.avery.bao.EmailFetch;
import com.avery.bao.SiteManagement;
import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class EmailQueueService {
	public int insertData(String subject, String sender, String mailbody, Date receivedDate, Date readDate, String cc, String to, String assignCSR,EmailFetch emailFetch){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = new OrderEmailQueue(subject, sender, mailbody, receivedDate, readDate, cc, to, assignCSR);
		if(emailFetch.siteId != 0){
			orderEmailQueue.setSiteId(emailFetch.siteId);
		}
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		EmailManager.log.debug("Successfully Inserted record in the emailqueue table");
		return orderEmailQueue.getId();
	}
	
	
	public void updateStatus(int emailqueueid, String status){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		OrderEmailQueue orderEmailQueue = (OrderEmailQueue) session.get(OrderEmailQueue.class, emailqueueid) ;
		orderEmailQueue.setStatus(status);; 
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		EmailManager.log.debug("Successfully updated status in the emailqueue table for emailqueueid\""+emailqueueid+"\".");
		
	}

}
