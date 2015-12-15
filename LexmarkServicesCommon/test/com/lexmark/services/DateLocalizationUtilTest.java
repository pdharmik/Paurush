package com.lexmark.services;

import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.util.DateLocalizationUtil;

public class DateLocalizationUtilTest {
	
	 public Date dDate = new Date();
	 public String sDate = "06/29/2011";
	 
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(DateLocalizationUtilTest.class);
	@Before
	public void setUp() throws Exception {
		 
	}

	@After
	public void tearDown() throws Exception {
		 
	}
	
	@Test
	public void testFormatDateTime() throws Exception{
		dDate = DateLocalizationUtil.parseDateLocale(sDate, Locale.US);
		LOGGER.debug(dDate);
		LOGGER.debug("Chinese "+DateLocalizationUtil.formatDateLocale(dDate, Locale.CHINA));
		LOGGER.debug("FRANCE "+DateLocalizationUtil.formatDateLocale(dDate, Locale.FRANCE));
		LOGGER.debug("GERMAN "+DateLocalizationUtil.formatDateLocale(dDate, Locale.GERMAN));
		LOGGER.debug("ITALIAN "+DateLocalizationUtil.formatDateLocale(dDate, Locale.ITALIAN));
		LOGGER.debug("JAPAN "+DateLocalizationUtil.formatDateLocale(dDate, Locale.JAPAN));
		LOGGER.debug("KOREA "+DateLocalizationUtil.formatDateLocale(dDate, Locale.KOREA));
		LOGGER.debug("UK "+DateLocalizationUtil.formatDateLocale(dDate, Locale.UK));
		LOGGER.debug("English "+DateLocalizationUtil.formatDateLocale(dDate, Locale.ENGLISH));
	}
	

}
