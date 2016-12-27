package com.avery.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.utils.HibernateUtil;

public class AcknowledgementService {
	
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
		//System.out.println("The result is : "+senderID);
		session.close();
		return senderID;
	}

}
