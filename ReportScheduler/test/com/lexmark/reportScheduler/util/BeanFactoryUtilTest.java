package com.lexmark.reportScheduler.util;

import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.service.api.ReportScheduleService;

public class BeanFactoryUtilTest {

	@Test
	public void testGetBean() {
		ReportScheduleService service = (ReportScheduleService)BeanFactoryUtil.getBean("reportScheduleService");
		Assert.assertNotNull(service);
	}

}
