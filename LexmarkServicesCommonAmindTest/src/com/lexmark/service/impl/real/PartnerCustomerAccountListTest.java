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
import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.result.CustomerAccountListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerCustomerAccountListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerCustomerAccountListTest.class);
	CustomerAccountListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new CustomerAccountListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveCustomerAccountList() throws Exception {
		
		testRetrieveCustomerAccountInputParameters();
		CustomerAccountListResult result = service.retrieveCustomerAccountList(contract);
		testRetrieveCustomerAccountOutputParameters(result);
		
	}
	
	
	public void testRetrieveCustomerAccountInputParameters() throws InterruptedException, ParseException {
		
		String mdmId = "50901";
		String mdmLevel = "Legal";
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}
	
	
	public void testRetrieveCustomerAccountOutputParameters(CustomerAccountListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAccountList());
		
	}
	
}


