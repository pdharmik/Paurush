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
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.result.OpenClaimListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerOpenClaimListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerOpenClaimListTest.class);
	OpenClaimListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new OpenClaimListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveOpenClaimList() throws Exception {
		
		testRetrieveOpenClaimInputParameters();
		OpenClaimListResult result = service.retrieveOpenClaimList(contract);
		
		System.out.println("Size: " + result.getClaimList().size());
		System.out.println("Total count: " + result.getTotalCount());
		
//		testRetrieveOpenClaimOutputParameters(result);
		
	}
	
	
	public void testRetrieveOpenClaimInputParameters() throws InterruptedException, ParseException {
		String mdmId = "50901";
		String mdmLevel = "Legal";
		String assetId = "1-A8REI2";
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setAssetId(assetId);
	}
	
	
	public void testRetrieveOpenClaimOutputParameters(OpenClaimListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getClaimList());
		
	}
	
}


