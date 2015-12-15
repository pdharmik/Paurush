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
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerActivityDetailTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerActivityDetailTest.class);
	ActivityDetailContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new ActivityDetailContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveActivityDetail() throws Exception {
		
		testRetrieveActivityDetailInputParameters();
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		testRetrieveActivityDetailOutputParameters(result);
		
	}
	
	
	public void testRetrieveActivityDetailInputParameters() throws InterruptedException, ParseException {
		String activityId = "1-2SMUQ58";
		String serviceRequestId = "";
		boolean debriefFlag = false;

        ServicesUser user = new ServicesUser();
        //TODO: Fix account number.
        user.getAccountids().add("1-12345");
        contract.setServicesUser(user);
		contract.setActivityId(activityId);
		contract.setServiceRequestId(serviceRequestId);
		contract.setDebriefFlag(debriefFlag);
	}
	
	
	public void testRetrieveActivityDetailOutputParameters(ActivityDetailResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getActivity());
		
	}
	
}


