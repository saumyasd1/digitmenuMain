package com.avery.Model;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.dao.GlobalConfiguration;
import com.avery.utils.HibernateUtil;



public class DateModel {

	static Logger log = Logger.getLogger(DateModel.class.getName());
	 	
	
/**
 * method to save or update time zone information to database
 * @param zone
 * @param zoneId
 * @param offset
 */
	public void SaveTimeZoneInfo(String zone, String zoneId, String offset) throws Exception{

		
		Session session=null;
		log.debug("Method execution start to save time zone.");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			GlobalConfiguration gc = new GlobalConfiguration();
			gc.setTimeZoneOffset(offset);
			gc.setDefaultTimeZone(zoneId);
			if (zone == "no record found") {
				session.save(gc);
				log.debug("Time zone info saved because no entries found.");
			} else {
				log.debug("zone updated to \"" + zoneId + " \"from zone \""
						+ zone + "\".");
				 Query qry = session.createQuery("update GlobalConfiguration set defaultTimeZone=:zonename, TimeZoneOffset=:offset where defaultTimeZone=:zone");
				 qry.setParameter("zonename",zoneId);
				 qry.setParameter("offset",offset);
				 qry.setParameter("zone",zone);
				 int res = qry.executeUpdate();
				log.debug("Time zone updated  successfully from \"" + zoneId + " \"to \""
						+ zone + "\".");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			throw  new Exception("Error while saving time zone information to database.,"+e.getMessage(), e);
		}
		finally{
			if(session !=  null && session.isOpen()){
	  			session.disconnect();
	  		}
			log.debug("Method save time zone info executed successfully.");
		}

	}
	/**
	 * method to get time zone info from database
	 * @return
	 * @throws Exception
	 */
	public String GetTimeZoneInfo( )throws Exception{
		
		log.debug("Method get zone info started.");
		String result="no record found";
		Session session=null;
		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(GlobalConfiguration.class);
			cr.setMaxResults(1);
			List<GlobalConfiguration> list = cr.list();
	 		if(list.isEmpty()){
	 			log.debug("No time zone info is available.");
				return "no record found";
			}
			
	         Iterator<GlobalConfiguration> iterator = list.iterator(); 
	         while (iterator.hasNext()){
	        	 GlobalConfiguration gc =  iterator.next(); 
	        	 if(gc!= null){
	        		if(gc.getDefaultTimeZone()!= null && gc.getDefaultTimeZone()!= "" ){
	        			return gc.getDefaultTimeZone().trim();
	        		}
	        	}else{
	        		result = "no record found";
	        	}
			  }
	         session.getTransaction().commit();
		}catch(Exception e){
			throw  new Exception("Error while getting time zone information from database.,"+e.getMessage(), e);
		}
		finally{
			
			if(session !=  null && session.isOpen()){
	  			session.disconnect();
	  		}
			log.debug("Method save time zone info executed successfully.");
		}
		return result;
	}
}
