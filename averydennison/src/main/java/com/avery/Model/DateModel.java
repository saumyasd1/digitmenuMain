package com.avery.Model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;

import com.avery.dao.GlobalConfiguration;
import com.avery.utils.HibernateUtil;

public class DateModel {

	static Logger log = Logger.getLogger(DateModel.class.getName());

	/**
	 * method to get time zone offset info from database
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getTimeZoneInfo(String dbOffset) throws Exception {

		log.debug("Method to get time zone info started.");
		String result = "";
		Session session = null;

		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.clear();
			session.beginTransaction();
			// session.setCacheMode(CacheMode.IGNORE);
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
				saveOffset(result, dbOffset);
				return result;
			}
		} catch (Exception e) {
			try {
				if (result == "") {
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
						log.info("database timezone offset is \"" + result
								+ "\".");
						saveOffset(result, dbOffset);
						return result;
					}
				}
			} catch (Exception ex) {
				return "";
				// throw new
				// Exception("Error while get time zone from database "
				// + e.getMessage(), e);
			}
		} finally {

			if (session != null && session.isOpen()) {
				session.getTransaction().commit();
				session.clear();
				session.disconnect();
				session.flush();
				session.close();
			}
		}
		log.debug("Method get time zone offset info executed successfully.");
		return result;
	}

	/**
	 * method save/update timezone offset to database
	 * 
	 * @param offset
	 * @param dbOffset
	 * @throws Exception
	 */
	private void saveOffset(String offset, String dbOffset) throws Exception {
		Session session = null;
		log.debug("Method execution start to save time zone.");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			GlobalConfiguration gc = new GlobalConfiguration();
			gc.setTimeZoneOffset(offset);
			gc.setDefaultTimeZone(offset);
			if (dbOffset == "empty") {
				session.save(gc);
				log.debug("Time zone info saved because no entries found.");
			} else {
				log.debug("zone updated to \"" + offset + "\".");
				Query qry = session
						.createQuery("update GlobalConfiguration set defaultTimeZone=:zonename, TimeZoneOffset=:offset ");
				qry.setParameter("zonename", offset);
				qry.setParameter("offset", offset);
				int res = qry.executeUpdate();
				log.debug("Time zone updated  successfully from \"" + offset
						+ "\".");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(
					"Error while saving time zone information to database.,"
							+ e.getMessage(), e);
		} finally {
			if (session != null && session.isOpen()) {
				session.disconnect();
			}
			log.debug("Method save time zone info executed successfully.");
		}

	}

	/**
	 * method fetch offset from database
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchOffset() throws Exception {

		log.debug("Method get timezone offset info started.");
		String offsetString = "no record found";
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria cr = session.createCriteria(GlobalConfiguration.class);
			cr.setMaxResults(1);
			List<GlobalConfiguration> list = cr.list();
			if (list.isEmpty()) {
				log.debug("No time zone info is available.");
				// //call method to insert
				offsetString = getTimeZoneInfo("empty");
				return offsetString;
			}

			Iterator<GlobalConfiguration> iterator = list.iterator();
			while (iterator.hasNext()) {
				GlobalConfiguration gc = iterator.next();
				if (gc != null) {
					if (gc.getDefaultTimeZone() != null
							&& !gc.getDefaultTimeZone().trim().isEmpty()) {
						return gc.getDefaultTimeZone().trim();
					} else if (gc.getDefaultTimeZone() == null
							|| gc.getDefaultTimeZone().trim().isEmpty()) {
						offsetString = getTimeZoneInfo("update");
					}
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception(
					"Error while getting time zone information from database.,"
							+ e.getMessage(), e);
		} finally {

			if (session != null && session.isOpen()) {
				session.disconnect();
			}
			log.debug("Method save time zone info executed successfully.");
		}
		return offsetString;

	}
}
