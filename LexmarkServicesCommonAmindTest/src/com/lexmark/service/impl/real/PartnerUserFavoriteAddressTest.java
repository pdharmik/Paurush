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
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.result.PartnerFavoriteAddressUpdateResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerUserFavoriteAddressTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerUserFavoriteAddressTest.class);
	PartnerFavoriteAddressUpdateContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new PartnerFavoriteAddressUpdateContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testUserFavoriteAddress() throws Exception {
		
		testUserFavoriteAddressInputParameters();
		PartnerFavoriteAddressUpdateResult result = service.updatePartnerUserFavoriteAddress(contract);
		testUserFavoriteAddressOutputParameters(result);
		
	}
	
	
	public void testUserFavoriteAddressInputParameters() throws InterruptedException, ParseException {
		String contactId = "1-PV8SJF";
		String favoriteAddressId = "1-B0K5FR";
		boolean favoriteFlag = true;
		String partnerAccountId = "1-7SGGI4";
		
		contract.setContactId(contactId);
		contract.setFavoriteAddressId(favoriteAddressId);
		contract.setFavoriteFlag(favoriteFlag);
		contract.setPartnerAccountId(partnerAccountId);
	}
	
	
	public void testUserFavoriteAddressOutputParameters(PartnerFavoriteAddressUpdateResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.isResult());
		
	}
	
}


