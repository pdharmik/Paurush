package com.lexmark.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.constants.LexmarkConstants;

public class TimezoneUtil {

	private static final BigDecimal minutesInHour = new BigDecimal(LexmarkConstants.MINUTES_IN_HOUR);
	private static final Logger logger = LogManager.getLogger(TimezoneUtil.class);

	/**
	 * 
	 * @param timeZone
	 *            -11 to 12
	 * @return
	 */
	public static TimeZone getTimeBaseonTimezone(int timeZone) {
		if (timeZone < -11 || timeZone > 12) {
			throw new IllegalArgumentException("timeZone is illegal, should between -11 to 12");
		}
		String timeZoneId = "GMT" + (timeZone >= 0 ? "+" : "") + timeZone + ":00";
		return TimeZone.getTimeZone(timeZoneId);
	}

	public static Integer getServerTimeZone() {
		Calendar calendar = Calendar.getInstance();
		return (calendar.get(Calendar.ZONE_OFFSET) / LexmarkConstants.MILLISECONDS_IN_HOUR);
	}
	
	public static void adjustDate(Date originalDate, int timezoneOffset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.add(Calendar.HOUR, timezoneOffset);
		originalDate.setTime(calendar.getTimeInMillis());
	}
	
	/**
	 * adjusts date based on the given timezone offset.
	 * 
	 * @param originalDate
	 * @param timezoneOffset
	 *            could be float, e.g. -5.75
	 */
	public static void adjustDate(Date originalDate, float timezoneOffset) {
		BigDecimal timezoneOffsetDecimal = BigDecimal.valueOf(timezoneOffset);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.add(Calendar.MINUTE, timezoneOffsetDecimal.multiply(minutesInHour).intValue());
		originalDate.setTime(calendar.getTimeInMillis());
	}

	public static Date getCurrentDateInUTC(){
		SimpleDateFormat dateFormatUtc = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		dateFormatUtc.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Local time zone
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

		// Time in UTC
		try {
			return dateFormatLocal.parse(dateFormatUtc.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static Date getCurrentDateInUTC(Date inputDate){
		SimpleDateFormat dateFormatUtc = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		dateFormatUtc.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Local time zone
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

		// Time in UTC
		try {
			return dateFormatLocal.parse(dateFormatUtc.format(inputDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
}
