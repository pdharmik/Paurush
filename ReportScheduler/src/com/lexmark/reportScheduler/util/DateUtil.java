package com.lexmark.reportScheduler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private static final String SHORT_DATE_FORMAT = "MM/dd/yyyy";
	private static final String SHORT_TIME_FORMAT = "HHmm";
	private static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	public static Date parseGMTDate(String strDate) {
		Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
		if(strDate == null) {
			return date;
		}
		SimpleDateFormat dateFormatter =
			new SimpleDateFormat(SHORT_DATE_FORMAT);
		try {
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			date = dateFormatter.parse(strDate);
		}catch (ParseException pe) {

		}
		return date;
	}
	
	public static Date parseGMTTime(String strDate) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		if(strDate == null) {
			return date;
		}
		SimpleDateFormat dateFormatter =
			new SimpleDateFormat(SHORT_TIME_FORMAT);
		try {
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			date = dateFormatter.parse(strDate);
		}catch (ParseException pe) {
		}
		return date;
	}

	
	public static String formatGMTDate(Date date) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
		dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormatter.format(date);
	}
	
	public static String formatGMTTime(Date date) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(SHORT_TIME_FORMAT);
		dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormatter.format(date);
	}
	
	public static String formatDateTime(Date date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return datetimeFormatter.format(date);
	}
}
