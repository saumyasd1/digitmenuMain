package com.avery.services;

import com.avery.dao.Address;
import com.avery.dao.Person;
import com.avery.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonService {
	public String hibernateTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    
	    session.beginTransaction();
	
	    Person steve = new Person("Steve", "Jobs");
	    Person donald = new Person("Donald", "Trump");
	
	    Address valley = new Address("Steve P Jobs", "San Francisco", "11111");
	    Address newyork = new Address("Trump Tower", "New York", "22222");
	    Address chicago = new Address("Trump Tower", "Chicago", "33333");
	
	    steve.getAddresses().add(valley);
	    donald.getAddresses().add(newyork);
	    donald.getAddresses().add(chicago);
	
	    session.persist(steve);
	    session.persist(donald);
	
	    session.getTransaction().commit();
	    session.close();
	    return steve.getFirstName();
	}
}
