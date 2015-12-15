package com.lexmark.service.impl.real.partnerPaymentService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;

public class PartnerPaymentLineItemListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerPaymentLineItemListTest.class);
	AmindPartnerPaymentService service;
	PaymentLineItemDetailsContract contract;
	AmindGlobalService globalService;
	CrmSessionHandle handle;
	
	
     public void setUp() {
		service = new AmindPartnerPaymentService();
		contract = new PaymentLineItemDetailsContract();
		globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		handle = globalService.initCrmSessionHandle(null);
		
     }
	 
	
	 public void tearDown() {
		if (contract.getSessionHandle() != null) {
					try {
						Session session = ((AmindCrmSessionHandle) contract.getSessionHandle()).acquire();
						if (session != null) {
							session.release();
						}
						((AmindCrmSessionHandle) contract.getSessionHandle()).release();
					}
					catch (InterruptedException e) {
						//squash
					}
				}
			}
	
	@Test
	public void testRetrievePaymentLineItemList()  throws Exception {
		
		testRetrievePaymentLineItemInputParameters();
		PaymentLineItemDetailsResult result = service.retreivePaymentLineItemList(contract);
		testRetrieveRetrievePaymentLineItemParameters(result);

	}

	
	public void testRetrievePaymentLineItemInputParameters() throws InterruptedException, ParseException {
		
		setUp();

		String paymentId = "1-FNGXAF";
		boolean newQueryIndicator = true;
		
		contract.setPaymentId(paymentId);

		Map<String,Object> filterMap = new HashMap<String,Object>();

		Map<String,Object> searchMap = null;
		Map<String,Object> sortMap = null;
		int startRecordNumber = 0;
		int increment = 0;
		CrmSessionHandle sessionHandle = handle;



		contract.setFilterCriteria(filterMap);
		contract.setSearchCriteria(searchMap);
		contract.setSortCriteria(sortMap);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setSessionHandle(sessionHandle);
		contract.setNewQueryIndicator(newQueryIndicator);
	
	}
	
	
	public void testRetrieveRetrievePaymentLineItemParameters(PaymentLineItemDetailsResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getActivities());
		tearDown();
		
	}
	
}


