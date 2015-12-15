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
import com.lexmark.contract.PaymentListContract;
import com.lexmark.result.PaymentListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.AmindGlobalService;

public class PartnerPaymentListTest {
	
	protected static final Log logger = 
		LogFactory.getLog(PartnerPaymentListTest.class);
	AmindPartnerPaymentService service;
	PaymentListContract contract;
	AmindGlobalService globalService;
	CrmSessionHandle handle;
	
	
     public void setUp() {
		service = new AmindPartnerPaymentService();
		contract = new PaymentListContract();
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
	public void testRetrievePaymentList()  throws Exception {
		
		testRetrievePaymentInputParameters();
		PaymentListResult activityResult = service.retreivePaymentList(contract);
		testRetrievePaymentOutputParameters(activityResult);

	}

	
	public void testRetrievePaymentInputParameters() throws InterruptedException, ParseException {
		
		setUp();

		String mdmId = "50901";
		String mdmLevel = "Legal";
		Map<String,Object> filterMap = new HashMap<String,Object>();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
		String date = "02/10/2011";
		Date startDate = dateFormat.parse(date);
		
		String endStringdate = "08/25/2011";
		Date endDate = dateFormat.parse(endStringdate);
		
		Map<String,Object> searchMap = null;
		Map<String,Object> sortMap = null;
		int startRecordNumber = 0;
		int increment = 0;
		CrmSessionHandle sessionHandle = handle;
		boolean newQueryIndicator = true;
		
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setFilterCriteria(filterMap);
		contract.setSearchCriteria(searchMap);
		contract.setSortCriteria(sortMap);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setSessionHandle(sessionHandle);
		contract.setNewQueryIndicator(newQueryIndicator);
		
	}
	
	
	public void testRetrievePaymentOutputParameters(PaymentListResult result) {
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPaymentList());
		tearDown();
		
	}
	
}


