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
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerTechnicianListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerTechnicianListTest.class);
	TechnicianListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new TechnicianListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveTechinicianList() throws Exception {
		
		testRetrieveTechnicianInputParameters();
		TechnicianListResult result = service.retrieveTechnicianList(contract);
		testRetrieveTechnicianOutputParameters(result);
		
	}
	
	
	public void testRetrieveTechnicianInputParameters() throws InterruptedException, ParseException {
		String partnerAccountId = "1-AWMXYZ";

		contract.setPartnerAccountId(partnerAccountId);

	}
	
	
	public void testRetrieveTechnicianOutputParameters(TechnicianListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAccountContactList());
		
		
	}
	
}


