package com.lexmark.service.impl.real;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceActivityHistoryDetailResult;

public class PartnerActivityHistoryDetailTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerActivityHistoryDetailTest.class);
	ServiceActivityHistoryDetailContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new ServiceActivityHistoryDetailContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveActivityHistoryDetail() throws Exception {
		
		testRetrieveServiceHistoryInputParameters();
		ServiceActivityHistoryDetailResult result = service.retrieveServiceActivityHistoryDetail(contract);
		testRetrieveServiceHistoryOutputParameters(result);
		
	}
	
	
	public void testRetrieveServiceHistoryInputParameters() throws InterruptedException, ParseException {
		String serviceRequestId = "1-2RR8JUK";
		contract.setServiceRequestId(serviceRequestId);
	}
	
	
	public void testRetrieveServiceHistoryOutputParameters(ServiceActivityHistoryDetailResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getActivity());
		
	}
	
	
	
	@Test
	public void testRetrieveActivityHistoryDetail_defect10118() throws Exception {
		
		ServiceActivityHistoryDetailContract contract = new ServiceActivityHistoryDetailContract();
		contract.setServiceRequestId("1-7FZOSV1");
		
		ServiceActivityHistoryDetailResult result = service.retrieveServiceActivityHistoryDetail(contract);
		
		Activity activity = result.getActivity();
		
		ServiceRequest serviceRequest = activity.getServiceRequest();
		
		Asset asset = serviceRequest.getAsset();
		
		System.out.println("Customer reporting name: " + asset.getCustomerReportingName());
		
	}
	
	
	@Test
	public void testRetrieveActivityHistoryDetail_defect10888() throws Exception {
		
		ServiceActivityHistoryDetailContract contract = new ServiceActivityHistoryDetailContract();
		contract.setActivityId("1-77NKMCV");
		contract.setServiceRequestId("1-77DOXYH");
		
		ServiceActivityHistoryDetailResult result = service.retrieveServiceActivityHistoryDetail(contract);
		
		Activity activity = result.getActivity();
		
		List<PartLineItem>  parts = activity.getOrderPartList();
		
		System.out.println(parts.size());
		
		for (PartLineItem part : parts) {
			System.out.println(part.getErrorCode2());
		}
		
	}
	
}


