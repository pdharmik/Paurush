package com.lexmark.service.impl.real;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerPartnerNotificationTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerPartnerNotificationTest.class);
	PartnerNotificationsContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new PartnerNotificationsContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveNotificationList() throws Exception {
		
		testRetrieveNotificationInputParameters();
		PartnerNotificationsResult result = service.retrievePartnerNotifications(contract);
		testRetrieveNotificationOutputParameters(result);
		
	}
	
	
	public void testRetrieveNotificationInputParameters() throws InterruptedException, ParseException {
		String serviceRequestId = "1-2S3OX0V";
		String emailAddress = "DTR.logistics@walgreens.com";

		contract.setServiceRequestId(serviceRequestId);
		contract.setEmailAddress(emailAddress);
	}
	
	
	public void testRetrieveNotificationOutputParameters(PartnerNotificationsResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getServiceRequestActivityList());
		
	}
	
}


