package com.avery.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.avery.Model.DateModel;

public class DateUtility {

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Logger log = Logger.getLogger(DateUtility.class.getName());

	/**
	 * 
	 * Method to set database timezone offset and save time zone to database
	 * 
	 */
	public String SetJVMTimeZone() throws Exception {
		log.debug("Method to set jvm time zone initialized");

		try {
			
			String updatedOffsetTime = "";
			DateModel dataModel = new DateModel();
			String offSetTime = dataModel.fetchOffset();

			if (offSetTime.equals("GMT-00:00")) {
				updatedOffsetTime = offSetTime.replace("-", "+");
			} else if (offSetTime.isEmpty()) {
				log.error("error while fetching db timezone offset.");
				return "";
			} else {
				updatedOffsetTime = offSetTime;
			}
			log.info("Time zone for database  is \"" + updatedOffsetTime
					+ "\".");
			TimeZone timeZone = TimeZone.getTimeZone(updatedOffsetTime);
			timeZone.setDefault(timeZone);
			String jvmTimeZone= timeZone.getDisplayName();
			log.info("Time zone for jvm is updated to \""
					+ timeZone.getDefault() + "\".");
			return jvmTimeZone;
		} catch (Exception e) {
			throw new Exception("Error while set time zone for jvm "
					+ e.getMessage(), e);
		}
	}

}