package com.avery.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.Partner;

public class DateUtils {

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
   
	  /**
	 * method SetDbTimeZone
	 * purpose set database timezone offset 
	 * return timezone id as string
	 */
	public static String SetDbTimeZone() {
		   
		  	String query ="";
		  	String hour ="";
		  	log.info("Method to set db time zone initialized");
		  	///set custom time zone to test else uncomment default function
		  	//TimeZone timeZone = TimeZone.getTimeZone("America/Eirunepe");
		  	TimeZone timeZone = TimeZone.getDefault();
			timeZone.setDefault(timeZone);
		    Calendar now = Calendar.getInstance();
		    log.info("zone id is \""+now.getTimeZone().getID()+"\".");
		    int hours= timeZone.getRawOffset()/(1000*60*60);
		    int min= timeZone.getRawOffset()/(1000*60)%(60);
		    if (hours >= 0 && min >=0){
		    	 hour ="+"+hours;
		    }else{
		    	 hour =""+hours;
		    }
		    if(min <= 9 && min >=0){
		    	query = "SET GLOBAL time_zone = '"+hour+":0"+min+"';";
		    }else{
		    	query = "SET GLOBAL time_zone = '"+hour+":"+min+"';";
		    }
		    log.info("query to set db time zone is \""+query+"\".");
		   
		    SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery queryToExecute = session.createSQLQuery(query);
			queryToExecute.addEntity(Partner.class);//////change to any pojo class
			if(queryToExecute.executeUpdate()==0){
				log.info("Query executed successfully.");
			}
			session.getTransaction().commit();
	        session.disconnect();
			log.info("session diconnected successfully.");
			return now.getTimeZone().getID();
		  }
    
}