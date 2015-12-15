package com.lexmark.reportScheduler.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.report.PropertiesUtil;

public class DateUtilTest {

	@Test
	public void testParseGMTDate() {
		
		Date intervalStartDate = DateUtil.parseGMTDate("02/11/1999");
		
		Assert.assertEquals(1999 - 1900, intervalStartDate.getYear());
	}

	@Test
	public void testParseGMTTime() {
		Date time = DateUtil.parseGMTTime("2330");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(time);
		Assert.assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(30, calendar.get(Calendar.MINUTE));
		
		Assert.assertEquals((Integer)8, PropertiesUtil.getDefaultSchedulerFinishHour());
		Assert.assertEquals((Integer)0, PropertiesUtil.getDefaultSchedulerFinishMinute());
	}
	
	@Test
	public void testPlusMinute() {
		Date time = DateUtil.parseGMTTime("2330");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(time);
		
		calendar.add(Calendar.MINUTE, 71 + 60*24);
		

	}
	
	@Test
	public void testGetTimeZone() {
		Date time = DateUtil.parseGMTTime("1330");
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(3));
		calendar.setTime(time);
		Assert.assertEquals(16, calendar.get(Calendar.HOUR_OF_DAY));

		calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(-4));
		calendar.setTime(time);
		Assert.assertEquals(9, calendar.get(Calendar.HOUR_OF_DAY));
	}
	
	@Test
	public void testCompareTimeFromDifferentTimeZone() {
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(0));
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		Date time = calendar.getTime();
		Calendar calendar2 = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(3));
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		Assert.assertTrue(calendar2.getTime().before(time));
		
	}
	
	

}
