package com.lexmark.util;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.framework.logging.LEXLogger;



/**
 * Date Utility Class for localization
 * @author Sourav
 *
 */
public class DateUtil {

	private static Logger LOGGER = LEXLogger.getLogger(DateUtil.class);
	
	private static final String TIMEZONE="UTC";
	//private static final String DATEFORMAT="yyyy-MM-dd HH:mm:ss";
	private static final String DATEFORMAT="MM/dd/yyyy HH:mm:ss";
	
	//MM/DD/YYYY HH:MM:SEC AM/PM (UTC DateTime)
	private static final String DATE_FORMAT_EN = "MM/dd/yyyy";
	private static final String DATE_FORMAT_DE = "dd.MM.yyyy";
	private static final String DATE_FORMAT_ES = "dd/MM/yyyy";
	private static final String DATE_FORMAT_FR = "dd/MM/yyyy";
	private static final String DATE_FORMAT_IT = "dd/MM/yyyy";
	private static final String DATE_FORMAT_JA = "yyyy/MM/dd";
	private static final String DATE_FORMAT_KO = "MM/dd/yyyy";
	private static final String DATE_FORMAT_KO_PARTNER = "yyyy.mm.dd";
	private static final String DATE_FORMAT_PT = "dd-MM-yyyy";	
	private static final String DATE_FORMAT_PT_PARTNER = "dd/mm/yyy";
	private static final String DATE_FORMAT_RU = "dd-MM-yyyy";
	private static final String DATE_FORMAT_ZH = "yyyy-mm-dd";//Changed the date format for LBS 17304 defect
	private static final String DATE_FORMAT_ZH_PARTNER="yyyy-mm-dd";
	private static final String DATE_FORMAT_TR = "dd.MM.yyyy";
	private static final String DATE_FORMAT_TR_PARTNER = "dd/mm/yyyy";
	private static final BigDecimal millsecondsInHour = new BigDecimal(LexmarkConstants.MILLISECONDS_IN_HOUR);
	
	public static String convertDateStringToGMTDateString(String dateString, String lang){
		
		String retString = dateString;
		
		if(dateString != null && !"".equals(dateString)){
		
			if(lang.equals("de")){
				String []strArr = dateString.split("\\.");
			
				if(strArr.length==3){
					retString = strArr[1]+"/"+strArr[0]+"/"+strArr[2];
				
				}
			}else if(lang.equals("ru") || lang.equals("pt")){
				String []strArr = dateString.split("-");
			
				if(strArr.length==3){
					retString = strArr[1]+"/"+strArr[0]+"/"+strArr[2];
				
				}	
			}else if(lang.equals("es") || lang.equals("fr")|| lang.equals("it")||lang.equals("tr")){
				String []strArr = dateString.split("/");
			
				if(strArr.length==3){
			
					retString = strArr[1]+"/"+strArr[0]+"/"+strArr[2];
			
				}	
			}
		}
		
		return retString;
	}
	
	public static String convertDateToGMTString(final Date localeSpecificDate)
	{
		
		String gmtDateString=null;
		
		
		/*if(localeSpecificDate!=null)
		{
			SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT);
			
			Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, TIMEZONE));
			format.setCalendar(cal);
			
			try {
				LOGGER.debug("date is "+format.format(localeSpecificDate));
				date = format.parse(format.format(localeSpecificDate));
				
				LOGGER.debug("Date after conversion is " + date.toString());
				
			} catch (ParseException e) {
				
				e.printStackTrace();//This is done for the time being
				date=null;			
			}
			LOGGER.debug("gmt date is " + date);
		}*/
		
		if(localeSpecificDate!=null)
		{
			try{
			SimpleDateFormat dateFormatGMT = new SimpleDateFormat(DATEFORMAT);
			
			dateFormatGMT.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
			
			gmtDateString=dateFormatGMT.format(localeSpecificDate);
			
			LOGGER.debug("Gmt date string is " + gmtDateString);
					
			} catch (Exception e) {
				LOGGER.error("Not parsable date-->>>"+localeSpecificDate);
				//e.printStackTrace();//This is done for the time being
				
			}
			
		}
				
		return gmtDateString;
	}
	
	public static String getDateFormatByLang(String language) {
		String dateFormat = null;

		if (language.equals("en")) {
			dateFormat = DATE_FORMAT_EN;
		} else if (language.equals("de")) {
			dateFormat = DATE_FORMAT_DE;
		} else if (language.equals("es")) {
			dateFormat = DATE_FORMAT_ES;
		} else if (language.equals("fr")) {
			dateFormat = DATE_FORMAT_FR;
		} else if (language.equals("it")) {
			dateFormat = DATE_FORMAT_IT;
		} else if (language.equals("ja")) {
			dateFormat = DATE_FORMAT_JA;
		} else if (language.equals("ko")) {
			dateFormat = DATE_FORMAT_KO;
		} else if (language.equals("pt")) {
			dateFormat = DATE_FORMAT_PT;
		} else if (language.equals("ru")) {
			dateFormat = DATE_FORMAT_RU;
		} else if (language.equals("zh")) {
			dateFormat = DATE_FORMAT_ZH;
		}else if (language.equals("tr")) {
			dateFormat = DATE_FORMAT_TR;
		} 
		else {
			dateFormat = DATE_FORMAT_EN;
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
	public static String getDateFormatByLang_partner(String language) {
		String dateFormat = null;

		if (language.equals("en")) {
			dateFormat = DATE_FORMAT_EN;
		} else if (language.equals("de")) {
			dateFormat = DATE_FORMAT_DE;
		} else if (language.equals("es")) {
			dateFormat = DATE_FORMAT_ES;
		} else if (language.equals("fr")) {
			dateFormat = DATE_FORMAT_FR;
		} else if (language.equals("it")) {
			dateFormat = DATE_FORMAT_IT;
		} else if (language.equals("ja")) {
			dateFormat = DATE_FORMAT_JA;
		} else if (language.equals("ko")) {
			dateFormat = DATE_FORMAT_KO_PARTNER;
		} else if (language.equals("pt")) {
			dateFormat =DATE_FORMAT_PT_PARTNER;
		} else if (language.equals("ru")) {
			dateFormat = DATE_FORMAT_RU;
		} else if (language.equals("zh")) {
			dateFormat = DATE_FORMAT_ZH_PARTNER;
		}else if (language.equals("tr")) {
			dateFormat = DATE_FORMAT_TR_PARTNER;
		} 
		else {
			dateFormat = DATE_FORMAT_EN;
		}

		return dateFormat;
	}
	
	public static String localizeDateTime (Date date, boolean showTime, Locale locale) {
		if(date==null)
			return "";
		String dateFormat = getDateFormatByLang(locale.getLanguage());
		if (showTime) {
			dateFormat += " HH:mm:ss";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * Converts String to Date in the localized format used for DM
	 * @throws ParseException 
	 */
	public static Date getStringToLocalizedDate(String date, boolean showTime, Locale locale) throws ParseException
	{
		if(date==null)
		{
			return null;
		}
		
		String dateFormat= getDateFormatByLang(locale.getLanguage());
		
		LOGGER.debug("dateformat is -----> " + dateFormat);
		if (showTime) {
			dateFormat += " HH:mm:ss";
			LOGGER.debug("dateformat is after showtime -----> " + dateFormat);
		}
		
		DateFormat sdateFmt=new SimpleDateFormat(dateFormat);
		
		Date newDate=sdateFmt.parse(date);
		
		LOGGER.debug("After conversion from String to Date -----> "+ newDate);
		
		return newDate;
	}
	
	/**
	 * This method takes into account the timezoneoffset value and adjusting with the date entered in the history left nav
	 * @param originalDate
	 * @param timezoneOffset
	 */
	public static Date adjustDateWithOffset(Date originalDate, float timezoneOffset)
	{	
		if(originalDate==null)
			return null;
		LOGGER.debug("Date in date dateutil---------> "+new Date(originalDate.getTime() - new BigDecimal(timezoneOffset).multiply(millsecondsInHour).intValue()));
		return (new Date(originalDate.getTime() - new BigDecimal(timezoneOffset).multiply(millsecondsInHour).intValue()));
	}
	
	//brmandal added the below methods for Page count changes - MPS phase 2.1
	public static String getCurrentDateInGMT()
	{
		TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE));  
		Calendar cal=Calendar.getInstance(TimeZone.getDefault());  
		Date dateGMT=cal.getTime(); 
		String strDate = dateGMT.toString();
		return strDate;
		
	}
	
	public static String getGivenDateInGMT(String givenDate) throws ParseException
	{
		SimpleDateFormat converter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String defaultTimeZoneID = converter.getTimeZone().getID();
		LOGGER.debug(defaultTimeZoneID);
		Date edtDate;// = new Date();
		edtDate = converter.parse(givenDate);
		LOGGER.debug("DateUtil : givenDate: "+givenDate);
		LOGGER.debug("DateUtil gmtDate "+edtDate);
		converter.setTimeZone(TimeZone.getTimeZone("America/Louisville"));
		String edtTime = converter.format(edtDate);
		edtTime = edtTime.concat(" EDT");
		LOGGER.debug("edt time "+edtTime);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z");
		Date gmtDate1 = sdf1.parse(edtTime);
		TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE));
	    Calendar cal=Calendar.getInstance(TimeZone.getDefault());  
		
	    cal.setTime(gmtDate1);
	    Date gmtDate11 = cal.getTime();
	    String strDateinGMT = gmtDate11.toString();
	    LOGGER.debug("Dateutil : strDateinGMT "+strDateinGMT);
	    //TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
	    //Date postprocessdate = new Date();
	    
	    
	    return strDateinGMT;
		
		
	}
	
	public static String getDateInStringFormat(String givenDate)
	{
	    String yyyy = givenDate.substring(givenDate.length() - 4);
	    String mm = givenDate.substring(4, 7);
	    String dd = givenDate.substring(8, 10);
	  
	    int MM = 0;
	    if(mm.equalsIgnoreCase("Jan")){
	    	 MM = 1;
	    }
	    if(mm.equalsIgnoreCase("Feb")){
	    	MM = 2;
	    }
	    if(mm.equalsIgnoreCase("Mar")){
	    	 MM = 3;
	    }
	    if(mm.equalsIgnoreCase("Apr")){
	    	 MM = 4;
	    }
	    
	    if(mm.equalsIgnoreCase("May")){
	    	 MM = 5;
	    }
	    if(mm.equalsIgnoreCase("Jun")){
	    	 MM = 6;
	    }
	    if(mm.equalsIgnoreCase("Jul")){
	    	 MM = 7;
	    }
	    if(mm.equalsIgnoreCase("Aug")){
	    	 MM = 8;
	    }
	    if(mm.equalsIgnoreCase("Sep")){
	    	 MM = 9;
	    }
	    if(mm.equalsIgnoreCase("Oct")){
	    	 MM = 10;
	    }
	    if(mm.equalsIgnoreCase("Nov")){
	    	 MM = 11;
	    }
	    if(mm.equalsIgnoreCase("Dec")){
	    	MM = 12;
	    }
	    
	    String strDate =  MM + "/" + dd + "/" + yyyy;
	    
	    return strDate;
	  
	}

	public static String getDateInStringFormatWithTimestamp(String givenDate)
	{
	    String yyyy = givenDate.substring(givenDate.length() - 4);
	    String mm = givenDate.substring(4, 7);
	    String dd = givenDate.substring(8, 10);
	  
	    int MM = 0;
	    if(mm.equalsIgnoreCase("Jan")){
	    	 MM = 1;
	    }
	    if(mm.equalsIgnoreCase("Feb")){
	    	MM = 2;
	    }
	    if(mm.equalsIgnoreCase("Mar")){
	    	 MM = 3;
	    }
	    if(mm.equalsIgnoreCase("Apr")){
	    	 MM = 4;
	    }
	    
	    if(mm.equalsIgnoreCase("May")){
	    	 MM = 5;
	    }
	    if(mm.equalsIgnoreCase("Jun")){
	    	 MM = 6;
	    }
	    if(mm.equalsIgnoreCase("Jul")){
	    	 MM = 7;
	    }
	    if(mm.equalsIgnoreCase("Aug")){
	    	 MM = 8;
	    }
	    if(mm.equalsIgnoreCase("Sep")){
	    	 MM = 9;
	    }
	    if(mm.equalsIgnoreCase("Oct")){
	    	 MM = 10;
	    }
	    if(mm.equalsIgnoreCase("Nov")){
	    	 MM = 11;
	    }
	    if(mm.equalsIgnoreCase("Dec")){
	    	MM = 12;
	    }
	    
	    String hms = givenDate.substring(11, 19);
		String strDate =  MM + "/" + dd + "/" + yyyy + " " + hms;
		 
	    return strDate;
	  
	}
	
	//brmandal ended for Page count changes - MPS phase 2.1


	/**
	 * Author:sbag
	 * The below method converts a date from GMT format to user specific locale format
	  */
	
	public static String convertToLocaleSpecificDate(Date date,Locale locale)
	{
		String convertedDate = "";
		try
		{
		String gmtDateString = DateFormatUtils.format(date, "MM/dd/yyyy");
		DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		Date  defaultDateINGMT = parser.parse(gmtDateString);
		LOGGER.debug("defaultDateINGMT--->>>"+defaultDateINGMT.toString());
		DateFormat localeFormatter = new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()).replace("mm", "MM"));						
		convertedDate = localeFormatter.format(defaultDateINGMT);
		}
		catch(Exception e)
		{ 
		  LOGGER.error("Not parsable date-->>>"+date);
			//e.printStackTrace();
		}
		LOGGER.debug("The converted date is---->>>"+convertedDate);
		return convertedDate;
	}
	
	public static String convertToLocaleSpecificDateTime(Date date,Locale locale)
	{
		String convertedDate = "";
		try
		{
		String gmtDateString = DateFormatUtils.format(date, "MM/dd/yyyy HH:mm:ss");
		DateFormat parser = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date  defaultDateINGMT = parser.parse(gmtDateString);
		//LOGGER.debug("defaultDateINGMT--->>>"+defaultDateINGMT.toString());
		DateFormat localeFormatter = new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()).replace("mm", "MM")+" HH:mm:ss");						
		convertedDate = localeFormatter.format(defaultDateINGMT);
		}
		catch(Exception e)
		{
		   LOGGER.error("Not parsable date-->>>"+date);
			//e.printStackTrace();
		}
		LOGGER.debug("The converted date is---->>>"+convertedDate);
		return convertedDate;
	}
	/*
	 * This will only work if the String date pattern is MM/dd/yyyy HH:mm:ss
	 */
	public static Date convertToGMTDate(String dateInString)
	{
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");			 
		try {	 
		 date = formatter.parse(dateInString);
						
	 
		} catch (Exception e) {
			LOGGER.error("Not parsable date-->>>"+dateInString);
			//e.printStackTrace();
		}
		return date;
	}
	public static String converDateToGMTString(final Date localizedDate,float timezoneOffset){
		
		if(localizedDate==null){
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMAT);
		String dateString=simpleDateFormat.format(new Date(localizedDate.getTime() + 
				new BigDecimal(timezoneOffset).multiply(millsecondsInHour).intValue()));
		//String dateString=simpleDateFormat.format(localizedDate);
		LOGGER.debug("date String ="+(dateString));
		
		return dateString;
		
	}
	/*
	 * This will convert the current time to equivalent GMT time
	 */
public static Date converCurrentTimeStampToEquivalentGMTTime(final Date currentDate){
	Date formattedDate=null; 
	try{
	SimpleDateFormat gmtFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	SimpleDateFormat gmtFormatLocal = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    formattedDate = gmtFormatLocal.parse(gmtFormat.format(currentDate));	
	}
	catch(Exception e)
	{
		LOGGER.error("Not parsable date-->>>"+currentDate);
		//e.printStackTrace();
	}
	return formattedDate;
	}
		
}