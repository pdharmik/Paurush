package com.lexmark.service.impl.real.partnerPaymentService;

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
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.service.impl.real.AmindGlobalService;

public class PartnerPaymentDetaiTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerPaymentDetaiTest.class);
	AmindPartnerPaymentService service;
	PaymentDetailsContract contract;
	AmindGlobalService globalService;
	SessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
	
	@Before
    public void setUp() {
		service = new AmindPartnerPaymentService();
		service.setStatelessSessionFactory(sessionFactory);
		contract = new PaymentDetailsContract();
    }
	 
	 @After
	 public void tearDown() {
		 ((StatelessSessionFactory)sessionFactory).releaseAllStatelessSessions();
	 }

	
	@Test
	public void testRetrievePaymentDetail()  throws Exception {
		
		testRetrievePaymentDetailInputParameters();
		PaymentDetailsResult activityResult = service.retrievePaymentDetails(contract);
		testRetrievePaymentDetailOutputParameters(activityResult);

	}

	
	public void testRetrievePaymentDetailInputParameters() throws InterruptedException, ParseException {
		
		setUp();
		String paymentId = "1-FNGXAF";
		contract.setPaymentId(paymentId);
		
	}
	
	
	public void testRetrievePaymentDetailOutputParameters(PaymentDetailsResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPayment());
		tearDown();
		
	}
	
}


