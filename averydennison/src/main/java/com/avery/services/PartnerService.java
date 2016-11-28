package com.avery.services;

//import com.avery.dao.Address_b;
import com.avery.dao.Partner;
import com.avery.dao.User;
import com.avery.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PartnerService {
	public String hibernateTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    
	    session.beginTransaction();
	
	    Partner steve = new Partner("Steve", "Jobs");
	 
	
	   // Address_b valley = new Address_b("Steve P Jobs", "San Francisco", "11111");
	   // Address_b newyork = new Address_b("Trump Tower", "New York", "22222");
	  //  Address_b chicago = new Address_b("Trump Tower", "Chicago", "33333");
	
	   // steve.getAddresses().add(valley);
	   // donald.getAddresses().add(newyork);
	   // donald.getAddresses().add(chicago);
	
	    session.persist(steve);
	   
	
	    session.getTransaction().commit();
	    session.close();
	    return steve.getPartnerName();
	}
}
