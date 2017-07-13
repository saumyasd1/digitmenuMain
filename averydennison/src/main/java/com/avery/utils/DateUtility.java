package com.avery.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			//TimeZone timeZone = TimeZone.getDefault();
			TimeZone timeZone = TimeZone.getTimeZone(updatedOffsetTime);
			//timeZone.setID(updatedOffsetTime);
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
	
	
	/**
	 * Method to convert Date according to Site ID
	 * @param stringDate
	 * @param stringSiteId
	 * @return String
	 * @author Rakesh
	 */
	public String convertDateUsingSite(String stringDate, String stringSiteId) {
		Date date;
		String siteDate = null;
		try {
			date = formatter.parse(stringDate);
		int siteId=Integer.parseInt(stringSiteId);
		TimeZone timezone = TimeZone.getDefault();
		long currentDateOffset = date.getTime();
		long utcOffset = currentDateOffset - timezone.getRawOffset();
				if (siteId == 2 || siteId == 3) {
			long Offset = 480 * 60 * 1000;
			siteDate = formatter.format(new Date(utcOffset + Offset));
		} else if (siteId == 4) {
			long Offset = 420 * 60 * 1000;
			siteDate = formatter.format(new Date(utcOffset + Offset));
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NumberFormatException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return siteDate;
	}
}