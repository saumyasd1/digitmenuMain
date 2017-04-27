package com.avery.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class AcknowledgementService {
	static Logger log = Logger.getLogger(AcknowledgementService.class.getName());
	public String getAcknowledgementEmailID(int emailqueueid){
		
		String senderID = "";
		String hql = "select senderEmailId from OrderEmailQueue where id= :id";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("id", emailqueueid);
		List result = query.list();
		senderID = (String) result.get(0);
		log.debug("acknowledgement email id : "+senderID);
		session.close();
		return senderID;
	}
	
	
	public void updateAcknowledgementDate(int emailqueueid, Date acknowledgementDate){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		OrderEmailQueue orderEmailQueue = (OrderEmailQueue) session.get(OrderEmailQueue.class, emailqueueid) ;
		orderEmailQueue.setAcknowledgementDate(acknowledgementDate); 
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully Inserted record in the emailqueue table");
		
	}

}
