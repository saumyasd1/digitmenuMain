package com.avery.services;

//import com.avery.dao.Address_b;
import com.avery.dao.Person;
import com.avery.dao.User;
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
	    User donal = new User("Donald", "Trump");
	
	   // Address_b valley = new Address_b("Steve P Jobs", "San Francisco", "11111");
	   // Address_b newyork = new Address_b("Trump Tower", "New York", "22222");
	  //  Address_b chicago = new Address_b("Trump Tower", "Chicago", "33333");
	
	   // steve.getAddresses().add(valley);
	   // donald.getAddresses().add(newyork);
	   // donald.getAddresses().add(chicago);
	
	    session.persist(steve);
	    session.persist(donal);
	    session.persist(donald);
	
	    session.getTransaction().commit();
	    session.close();
	    return steve.getFirstName();
	}
}
