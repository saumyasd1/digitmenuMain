package com.avery.Model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.avery.utils.HibernateUtil;

public class DateModel {

	static Logger log = Logger.getLogger(DateModel.class.getName());

	/**
	 * method to get time zone offset info from database
	 * 
	 * @return
	 * @throws Exception
	 */
	public String GetTimeZoneInfo() throws Exception {

		log.debug("Method to get time zone info started.");
		String result = "";
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			String q1 = "SELECT TIMEDIFF( UTC_TIMESTAMP, NOW())";
			SQLQuery query = session.createSQLQuery(q1);
			List<Date> list = query.list();

			Iterator<Date> itr = list.iterator();
			while (itr.hasNext()) {
				Date date = (Date) itr.next();
				String dateToString = date.toString();
				String dateToSubString = dateToString.substring(0,
						dateToString.length() - 3);
				result = "GMT-" + dateToSubString;
				log.info("database timezone offset is \"" + result + "\".");
				return result;
			}
		} catch (Exception e) {
			try {
				String q1 = "SELECT TIMEDIFF( NOW(),UTC_TIMESTAMP)";
				SQLQuery query = session.createSQLQuery(q1);
				List<Date> list = query.list();
				Iterator<Date> itr = list.iterator();
				while (itr.hasNext()) {
					Date date = (Date) itr.next();
					String dateToString = date.toString();
					String dateToSubString = dateToString.substring(0,
							dateToString.length() - 3);
					result = "GMT+" + dateToSubString;
					log.info("database timezone offset is \"" + result + "\".");
					return result;
				}
			} catch (Exception ex) {
				throw new Exception("Error while get time zone from database "
						+ e.getMessage(), e);
			}
		} finally {

			if (session != null && session.isOpen()) {
				session.getTransaction().commit();
				session.close();
			}
		}
		log.debug("Method get time zone offset info executed successfully.");
		return result;
	}
	
}
