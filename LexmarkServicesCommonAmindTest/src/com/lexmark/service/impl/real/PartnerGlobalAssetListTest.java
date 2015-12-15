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
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerGlobalAssetListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerGlobalAssetListTest.class);
	GlobalPartnerAssetListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new GlobalPartnerAssetListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveGlobalAssetList() throws Exception {
		
		testRetrieveAssetInputParameters();
		GlobalPartnerAssetListResult result = service.retrieveGlobalPartnerAssetList(contract);
		
		System.out.println("Size: " + result.getAssetList().size());
		System.out.println("Total count: " + result.getTotalCount());
		
		testRetrieveAssetOutputParameters(result);
		
	}
	
	
	public void testRetrieveAssetInputParameters() throws InterruptedException, ParseException {
		

		String serialNumber = "1234567";
		contract.setSerialNumber(serialNumber);
	}
	
	
	public void testRetrieveAssetOutputParameters(GlobalPartnerAssetListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAssetList());
		
	}
	
}


