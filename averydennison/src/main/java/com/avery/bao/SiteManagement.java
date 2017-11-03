package com.avery.bao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.avery.EmailManager;
import com.avery.dao.Site;
import com.avery.utils.HibernateUtil;


/**
 * @author Rakesh
 * Java Class to Identify multiple site
 *
 */
public class SiteManagement {

/**
 * Method to get siteId using sender email id
 * @param sender
 * @return
 */
public int getSiteId(String toEmail)throws Exception {
	int siteId = 0;
	Session session=null;
	try{
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Site.class);
			cr.add(Restrictions.eq("emailId", toEmail));
     		cr.setMaxResults(1);
     		@SuppressWarnings("unchecked")
			List<Site> list = cr.list();
     		EmailManager.log.info("List<Site>=\""+list+"\"");
     		System.out.println("List<Site>=\""+list+"\"");
     		Iterator<Site> iterator = list.iterator(); 
	        while (iterator.hasNext()){
	        	Site site =  iterator.next(); 
	        	 if(site!=null){
	        		 siteId = (int) site.getId();
	        	 }
			}
         session.getTransaction().commit();
         	 	
	}catch(HibernateException  e){
		e.printStackTrace();
		throw  e;
	}catch(Exception  e){
		e.printStackTrace();
		throw  e;
	}
	finally{
		if(session !=  null && session.isOpen()){
  			session.disconnect();
  		}
	}
	return siteId;
	}


}
