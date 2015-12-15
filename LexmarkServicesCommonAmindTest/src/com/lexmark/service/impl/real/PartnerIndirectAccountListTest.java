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
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerIndirectAccountListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerIndirectAccountListTest.class);
	PartnerIndirectAccountListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new PartnerIndirectAccountListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveIndirectAccountList() throws Exception {
		
		testRetrieveIndirectAccountInputParameters();
		PartnerIndirectAccountListResult result = service.retrievePartnerIndirectAccountList(contract);
		testRetrieveIndirectAccountOutputParameters(result);
		
	}
	
	
	public void testRetrieveIndirectAccountInputParameters() throws InterruptedException, ParseException {
		
		String mdmId = "AC6789";
		String mdmLevel = "Account";
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}
	
	
	public void testRetrieveIndirectAccountOutputParameters(PartnerIndirectAccountListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAccountList());
		
	}
	
	
	@Test
	public void testRetrievePartnerIndirectAccountList_AccountLevelAddressNotHavingData() throws Exception{
		PartnerIndirectAccountListContract contract = new PartnerIndirectAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		
		PartnerIndirectAccountListResult result = service.retrievePartnerIndirectAccountList(contract);
		
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("Accounts count: " + result.getAccountList().size());
		
		GenericAddress address = result.getAccountList().get(0).getAddress();
		System.out.println("Address name: " + address.getAddressName());
		System.out.println("StoreFrontName: " + address.getStoreFrontName());
		System.out.println("Office Number: " + address.getOfficeNumber());
		System.out.println("County: " + address.getCounty());
		System.out.println("State: " + address.getState());
		System.out.println("Region: " + address.getRegion());
		System.out.println("District: " + address.getDistrict());
	}
	
}


