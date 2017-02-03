package com.avery.services;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class EmailQueueService {
	
	public int insertData(String subject, String sender, String mailbody, Date receivedDate, Date readDate, String cc, String to, String assignCSR){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = new OrderEmailQueue(subject, sender, mailbody, receivedDate, readDate, cc, to, assignCSR);
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully Inserted record in the emailqueue table");
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
		System.out.println("Successfully updated status in the emailqueue table for emailqueueid\""+emailqueueid+"\".");
		
	}

}
