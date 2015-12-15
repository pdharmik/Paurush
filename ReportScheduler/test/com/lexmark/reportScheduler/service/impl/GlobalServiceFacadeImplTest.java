package com.lexmark.reportScheduler.service.impl;

import org.junit.Test;
import org.junit.Assert;

import com.lexmark.domain.GlobalAccount;
import com.lexmark.reportScheduler.util.BeanFactoryUtil;
import com.lexmark.service.api.GlobalServiceFacade;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.service.impl.real.GlobalServiceFacadeImpl;

public class GlobalServiceFacadeImplTest {

	@Test
	public void testRetriveGlobalAccount() {
		GlobalServiceFacade globalServiceFacade = (GlobalServiceFacade)BeanFactoryUtil.getBean("globalServiceFacade");
		GlobalAccount account =     globalServiceFacade.retriveGlobalAccount("12864", "Legal");
		Assert.assertNotNull(account);
		}
	
	@Test
	public void testGlobalServiceFacadeNotInBeanFactory() {
		GlobalServiceFacade globalServiceFacade = new GlobalServiceFacadeImpl();
		GlobalAccount account =     globalServiceFacade.retriveGlobalAccount("12864", "Legal");
		Assert.assertNotNull(account);
		
	}

}
