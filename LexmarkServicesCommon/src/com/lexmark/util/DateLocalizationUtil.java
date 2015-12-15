package com.lexmark.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import net.sf.cglib.core.Local;

import com.lexmark.constants.LexmarkConstants;

public class DateLocalizationUtil {

	private static final SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	public static String formatDateLocale(Calendar calendar, Locale locale) {
		Date date = calendar == null ? null : calendar.getTime();
		return formatDateLocale(date, locale);
	}

	public static String formatDateLocale(Date date, Locale locale) {
		String s = "";
		if (date != null) {
			try {
				s = formatDateShort(date, locale);	
			} catch (Exception e) {
				s = "Format Failed!";
			}
		}
		return s;
	}
	
//	added by nelson for CI5
	/**
	 * @author nelson
	 * 
	 * this was written for displaying date/time in 
	 * desired format (MM/dd/yyyy HH:mm:ss AM/PM)
	 * on the SR grid column
	 */
	public static String formatDateTimeOnSRGridColumn(Date date, Locale locale) {
		String onlyDate, onlyTime, dateTime;
		if(date == null) {
			dateTime = "";
		} else {
			try {
//				DateFormat dateFrmt = new SimpleDateFormat("MM/dd/yyyy", locale);
				DateFormat dateFrmt = new SimpleDateFormat(getDateFormatByLanguage(locale.getLanguage()), locale);
//				DateFormat dateFrmt = DateFormat.getDateInstance(DateFormat.SHORT, locale);
				onlyDate = dateFrmt.format(date);
				DateFormat timeFrmt = DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
				onlyTime = timeFrmt.format(date);
				dateTime = onlyDate + " " + onlyTime;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				dateTime = "Format Failed";
			}
		}
		
		return dateTime;
	}
//	end of addition by nelson for CI5

	public static Date parseDateLocale(String sDate, Locale locale) throws ParseException {
		if (sDate == null || sDate.trim().length() == 0)
			return null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		return df.parse(sDate);
	}

	public static String formatDateShort(Date date, Locale locale) {
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		return format.format(date);
	}

	public static String formatDateTimeShort(Date date, Locale locale) {
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
		return format.format(date);
	}

	public static String formatDateTimeShort(String sDate, Locale locale) throws Exception {
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.DEFAULT, locale);
		return format.format(parseDateLocale(sDate, Locale.US));
	}

	public static String formatDateTimeMedium(Date date, Locale locale) {
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, locale);
		return format.format(date);
	}

	public static String formatDateToStandardFormat(Date date) {
		if (date == null)
			return "";
		return STANDARD_DATE_FORMAT.format(date);
	}

	public static String formatDateTimeToLocale(Date date, Locale locale) {
		if (date == null)
			return "";

		return formatDateLocale(date, locale) + " " + formatTime(date, locale);
	}

	public static String formatTime(Date date, Locale locale) {
		if (date == null)
			return "";

		return new SimpleDateFormat("HH:mm:ss", locale).format(date);
	}

	/**
	 * Gets localized date fomart based on given language.
	 * @param language
	 * @return
	 */
	public static String getDateFormatByLanguage(String language) {
		String dateFormat = null;

		if (language.equals("en")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_ENGLISH;
		} else if (language.equals("de")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_GERMAN;
		} else if (language.equals("es")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_SPANISH;
		} else if (language.equals("fr")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_FRANCE;
		} else if (language.equals("it")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_ITALIAN;
		} else if (language.equals("ja")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_JAPAN;
		} else if (language.equals("ko")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_KOREA;
		} else if (language.equals("pt")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_PORTUGAL;
		} else if (language.equals("ru")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_RUSSIAN;
		} else if (language.equals("zh")) {
			dateFormat = LexmarkConstants.DATE_FORMAT_CHINESE;
		} else if (language.equals("pl")) { 					//Added by sankha for LEX:AIR00066285
			dateFormat = LexmarkConstants.DATE_FORMAT_POLISH;   //Added by sankha for LEX:AIR00066285
		} else if (language.equals("tr")) { 					//Added by Arunava for LEX:AIR00071181
			dateFormat = LexmarkConstants.DATE_FORMAT_TURKISH;   //Added by Arunava for LEX:AIR00071181
		} else {
			dateFormat = LexmarkConstants.DATE_FORMAT_ENGLISH;
		}

		return dateFormat;
	}
	
	/**
	 * Generates localized date/time based on given locale.
	 * @param date
	 * @param showTime
	 * @param locale
	 * @return
	 */
	public static String localizeDateTime (Date date, boolean showTime, Locale locale) {
		if(date==null)
			return "";
		String dateFormat = getDateFormatByLanguage(locale.getLanguage());
		if (showTime) {
			dateFormat += " hh:mm:ss a";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	/* The below code takes a date Object & convert the same to 
	 * Date Object
	 * 
	 */
	
	public static String localizeDateTimeObject (Object dateObject, boolean showTime, Locale locale) {
		Date date = (Date)dateObject;
		if(date==null)
			return "";
		String dateFormat = getDateFormatByLanguage(locale.getLanguage());
		if (showTime) {
			dateFormat += " hh:mm:ss a";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * Parses date (formatted as MM/dd/yyyy) from String to Date
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateStandardFormat(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.trim().length() == 0)
			return null;
		return new SimpleDateFormat(LexmarkConstants.DATE_FORMAT_ENGLISH).parse(dateStr);
	}
	
	public static Date parseDate(String dateStr, boolean showTime, Locale locale) throws ParseException {
		if (dateStr == null || dateStr.trim().length() == 0)
			return null;
		String dateFormatStr = getDateFormatByLanguage(locale.getLanguage());
		if (showTime) {
			dateFormatStr += " HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.parse(dateStr);
	}
}