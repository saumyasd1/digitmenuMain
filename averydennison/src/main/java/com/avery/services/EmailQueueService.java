package com.avery.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class EmailQueueService {
	
	public int insertData(String subject, String mailbody){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = new OrderEmailQueue(subject, mailbody);
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully Inserted");
		return orderEmailQueue.getId();
	}

}
