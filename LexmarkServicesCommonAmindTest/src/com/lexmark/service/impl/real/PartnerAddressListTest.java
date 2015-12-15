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
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.result.PartnerAddressListResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerAddressListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerAddressListTest.class);
	PartnerAddressListContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new PartnerAddressListContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveServiceAddressList() throws Exception {
		
		testRetrieveAddressListInputParameters();
		PartnerAddressListResult result = service.retrievePartnerAddressList(contract);
		testRetrieveAddressListOutputParameters(result);
		
	}
	
	
	public void testRetrieveAddressListInputParameters() throws InterruptedException, ParseException {
		String accountID = "1-193HIX" ;
		String contactId = "1-7JETV3";
		boolean  favoriteFlag = false;
		
		contract.setAccountID(accountID);
		contract.setContactId(contactId);
		contract.setFavoriteFlag(favoriteFlag);

	}
	
	
	public void testRetrieveAddressListOutputParameters(PartnerAddressListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAddressList());
		Assert.assertNotNull(result.getTotalCount());
		
	}
	
}


