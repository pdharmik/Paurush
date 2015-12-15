package com.lexmark.reportScheduler.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Test;

public class SchedulerParametersTest {

	@Test(expected=RuntimeException.class)
	public void testSchedulerParametersFail() {

		String[] arguments = {"-IntervalStartDate", "02/03/1999"};
		SchedulerParameters  paramters = new SchedulerParameters(arguments);
	}
	
	@Test
	public void testSchedulerParametersSuccessful() {
		String[] arguments = {"-IntervalStartDate", "02/03/1999", "-IntervalEndTime", "2330"};
		SchedulerParameters  paramters = new SchedulerParameters(arguments);
		Assert.assertEquals(paramters.getSelectionFlag(), "NORMAL"); 
		Assert.assertEquals(1999 - 1900, paramters.getIntervalStartDate().getYear());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTime(paramters.getIntervalEndTime());
		Assert.assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(30, calendar.get(Calendar.MINUTE));
	}

	@Test(expected=IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        ArrayList emptyList = new ArrayList();
	Object o = emptyList.get(0);
    }

}
