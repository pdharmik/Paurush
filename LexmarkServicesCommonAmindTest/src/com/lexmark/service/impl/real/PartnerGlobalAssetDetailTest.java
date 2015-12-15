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
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.GlobalAssetDetailResult;

public class PartnerGlobalAssetDetailTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerGlobalAssetDetailTest.class);
	GlobalAssetDetailContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new GlobalAssetDetailContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveGlobalAssetDetail() throws Exception {
		
		testRetrieveAssetInputParameters();
		GlobalAssetDetailResult result = service.retrieveGlobalAssetDetail(contract);
		testRetrieveAssetOutputParameters(result);
		
	}
	
	
	public void testRetrieveAssetInputParameters() throws InterruptedException, ParseException {
		

		String assetId = "1-2AQH06I";
		contract.setAssetId(assetId);
		
		String mdmId = "1-7S35W7";
		String mdmLevel = "Siebel";
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}
	
	
	public void testRetrieveAssetOutputParameters(GlobalAssetDetailResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAsset());
		
	}
	
	
	@Test
	public void testRetrieveGlobalAssetDetail_defect10118() throws Exception {
		
		contract = new GlobalAssetDetailContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("623331717");
		contract.setAssetId("1-8JA5-1696");
		
		GlobalAssetDetailResult result = service.retrieveGlobalAssetDetail(contract);
		
		Asset asset = result.getAsset();
		
		System.out.println("Customer reporting name: " + asset.getCustomerReportingName());
		
	}
}


