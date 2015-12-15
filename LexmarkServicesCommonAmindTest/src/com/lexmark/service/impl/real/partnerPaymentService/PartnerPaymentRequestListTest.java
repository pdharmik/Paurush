package com.lexmark.service.impl.real.partnerPaymentService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;

public class PartnerPaymentRequestListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerPaymentRequestListTest.class);
	AmindPartnerPaymentService service;
	PaymentRequestListContract contract;
	AmindGlobalService globalService;
	CrmSessionHandle handle;
	
	
     public void setUp() {
		service = new AmindPartnerPaymentService();
		contract = new PaymentRequestListContract();
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
	public void testRetrievePaymentRequestList()  throws Exception {
		
		testRetrievePaymentRequestInputParameters();
		PaymentRequestListResult activityResult = service.retrievePaymentRequestList(contract);
		testRetrievePaymentRequestOutputParameters(activityResult);

	}

	
	public void testRetrievePaymentRequestInputParameters() throws InterruptedException, ParseException {
		
		setUp();

		String mdmId = "50901";
		String mdmLevel = "Legal";
		Map<String,Object> filterMap = new HashMap<String,Object>();
		Map<String,Object> sortMap = new HashMap<String,Object>();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		String date = "10/9/2011";
		Date startDate = dateFormat.parse(date);
		
		String endStringdate = "11/8/2011";
		Date endDate = dateFormat.parse(endStringdate);
		
		Map<String,Object> searchMap = null;
		int startRecordNumber = 0;
		int increment = 100;
		CrmSessionHandle sessionHandle = handle;
		boolean newQueryIndicator = true;
		String paymentStatus = "All";
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		filterMap.put("Activity.startDate", startDate);
		filterMap.put("Activity.endDate", endDate);
		filterMap.put("Activity.payment.paymentStatus", "Draft");
		
		sortMap.put("Activity.totalPayment", "ASCENDING");
		contract.setFilterCriteria(filterMap);
		contract.setSearchCriteria(searchMap);
		contract.setSortCriteria(sortMap);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setSessionHandle(sessionHandle);
		contract.setNewQueryIndicator(newQueryIndicator);
		contract.setPaymentStatus(paymentStatus);
	}
	
	
	public void testRetrievePaymentRequestOutputParameters(PaymentRequestListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPaymentRequestList());
		tearDown();
		
	}
	
}


