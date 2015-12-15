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
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerActivityHistoryListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerActivityHistoryListTest.class);
	ServiceActivityHistoryListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new ServiceActivityHistoryListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveHistoryList() throws Exception {
		
		testRetrieveServiceHistoryInputParameters();
		ServiceActivityHistoryListResult result = service.retrieveServiceActivityHistoryList(contract);
		testRetrieveServiceHistoryOutputParameters(result);
		
	}
	
	
	public void testRetrieveServiceHistoryInputParameters() throws InterruptedException, ParseException {
		String assetId = "1-B37P-2988";
		String serviceRequestId = "1-2QAS02D";
		contract.setAssetId(assetId);
		contract.setServiceRequestId(serviceRequestId);
	}
	
	
	public void testRetrieveServiceHistoryOutputParameters(ServiceActivityHistoryListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getActivityList());
		
	}
	
}


