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
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.service.impl.real.AmindPartnerRequestsService;

public class PartnerClaimDetailTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerClaimDetailTest.class);
	ClaimDetailContract contract;
	AmindPartnerRequestsService service;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	 @Before
     public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new ClaimDetailContract();
     }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }
	
	@Test
	public void testRetrieveActivityDetail() throws Exception {
		
		testRetrieveClaimDetailInputParameters();
		ClaimDetailResult result = service.retrieveClaimDetail(contract);
		testRetrieveClaimDetailOutputParameters(result);
		
	}
	
	
	public void testRetrieveClaimDetailInputParameters() throws InterruptedException, ParseException {
		String activityId = "1-2QUVFKQ";//"1-2QUVFKQ";//"1-2RV0TGX";
		String serviceRequestId = "";
		boolean debriefFlag = true;
		
		contract.setActivityId(activityId);
		contract.setServiceRequestId(serviceRequestId);
		contract.setDebriefFlag(debriefFlag);
	}
	
	
	public void testRetrieveClaimDetailOutputParameters(ClaimDetailResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getActivity());
		
	}
	
}


