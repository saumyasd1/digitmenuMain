package com.avery.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.Model.DateModel;



public class DateUtility {

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger log = Logger.getLogger(DateUtility.class.getName());
   
	  /**
	 * 
	 * Method to set database timezone offset and save time zone to database 
	 *  
	 */
	public static void SetDbTimeZone()throws Exception {
		   
		  	String query ="";
		  	String hour ="";
		  	Session session=null;
		  	try{
		  		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
				session=sessionFactory.openSession();
				session.beginTransaction();
			  	log.debug("Method to set db time zone initialized");
			  	///set custom time zone to test else uncomment default function
			  	//TimeZone timeZone = TimeZone.getTimeZone("America/Eirunepe");
			  	TimeZone timeZone = TimeZone.getDefault();
				//timeZone.setDefault(timeZone);
			    Calendar now = Calendar.getInstance();
			    log.debug("time zone id is \""+now.getTimeZone().getID()+"\".");
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
			    log.debug("query to set db time zone is \""+query+"\".");
			    DateModel dm= new DateModel();
			    if(dm.GetTimeZoneInfo()=="no record found"){
			    	String offset= ""+timeZone.getRawOffset();
			    	dm.SaveTimeZoneInfo(dm.GetTimeZoneInfo(), now.getTimeZone().getID(), offset);
			    }else if(!dm.GetTimeZoneInfo().trim().equals( now.getTimeZone().getID().trim())){
			    	String offset= ""+timeZone.getRawOffset();
			    	dm.SaveTimeZoneInfo(dm.GetTimeZoneInfo(), now.getTimeZone().getID(), offset);
			    }else if(dm.GetTimeZoneInfo().trim().equals( now.getTimeZone().getID().trim())){
			    	log.debug("same time zone found no need to update.");
			    }else{
			    	log.error("Unexpected result for time zone from Date model class");
			    }
				
				SQLQuery setTimeZoneQuery = session.createSQLQuery(query);
				if(setTimeZoneQuery.executeUpdate()==0){
					log.debug("Query executed successfully.");
				}else{
					throw  new Exception("error while executing qury to set database time zone.");
				}
				session.getTransaction().commit();
		  	}catch(Exception e){
		  		throw  new Exception("Error while set time zone for databse and inserting value in table,"+e.getMessage(), e);
		  	}finally{
		  		if(session !=  null && session.isOpen()){
		  			session.disconnect();
		  		}
		    }
			
	}
    
}