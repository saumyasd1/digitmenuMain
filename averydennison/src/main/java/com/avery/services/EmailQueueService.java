package com.avery.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.bao.SiteManagement;
import com.avery.dao.OrderEmailQueue;
import com.avery.utils.HibernateUtil;

public class EmailQueueService {
	static Logger log = Logger.getLogger(EmailQueueService.class.getName());
	public int insertData(String subject, String sender, String mailbody, Date receivedDate, Date readDate, String cc, String to, String assignCSR){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue = new OrderEmailQueue(subject, sender, mailbody, receivedDate, readDate, cc, to, assignCSR);
		SiteManagement siteManagement=new SiteManagement();
		log.info("*****************to Mail Id=\" "+sender+"\" *******************************");
		int siteId=siteManagement.getSiteId(to);
		log.info("*******************Site Id=\"  "+siteId+"\"  *********************");
		if(siteId != 0){
			orderEmailQueue.setSiteId(siteId);
		}
		session.persist(orderEmailQueue);
		session.getTransaction().commit();
		session.close();
		log.debug("Successfully Inserted record in the emailqueue table");
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
		log.debug("Successfully updated status in the emailqueue table for emailqueueid\""+emailqueueid+"\".");
		
	}

}
