package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;

@RunWith(Parameterized.class)
public class ServiceRequestHistoryListTest extends RequestServiceStatelessBase {

	private final ServiceRequestHistoryListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ServiceRequestHistoryListContract contract;

		contract = new ServiceRequestHistoryListContract();
		contract.setSessionHandle(null);
		contract.setAssetId("1-4522552869");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setAccountId("1-EEX-144");
		contract.setServiceRequestNumber("");
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestStatusDate", "ASCENDING");
		// contract.setSortCriteria(sortCriteria);
		list.add(new Object[] { contract });

		return list;
	}

	public ServiceRequestHistoryListTest(ServiceRequestHistoryListContract contract) {
		this.contract = contract;
	}

	@Test
	public void testRetrieveServiceRequestHistoryList() throws Exception {
		ServiceRequestListResult result = service.retrieveServiceRequestHistoryList(contract);
		assertNotNull(result);
		List<ServiceRequest> serviceRequestList = result.getServiceRequests();
		assertNotNull("Service request list wasn't instantiated!", serviceRequestList);
		assertEquals("Total count doesn't equal list size!", result.getTotalCount(),
				serviceRequestList.size());
		assertFalse("Service request list is empty!", serviceRequestList.isEmpty());
		logServiceRequestList(serviceRequestList);
	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequestList) {
		logger.info("Found serviceRequest count = " + serviceRequestList.size());
		for (ServiceRequest serviceRequest : serviceRequestList) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getReferenceNumber() + " SR DATE:"
					+ serviceRequest.getServiceRequestDate());
		}
	}
	
	
	@Test
	public void testRetrieveServiceRequestHistoryList_defect8217() throws Exception {
		ServiceRequestHistoryListContract contract2 = new ServiceRequestHistoryListContract();
		contract2.setLocale(new Locale("en_GB"));
		contract2.setMdmLevel("Global");
		contract2.setAccountId("1-17EKMXJ");
		contract2.setMdmID("205529410");
		contract2.setAssetId("1-9XMW-4462");
		contract2.setServiceRequestNumber("1-10337265154");
		contract2.setNewQueryIndicator(true);
		contract2.setIncrement(40);
		contract2.setStartRecordNumber(0);
		contract2.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestStatusDate", "ASCENDING");
		contract2.setSortCriteria(sortCriteria);
		
		ServiceRequestListResult result = service.retrieveServiceRequestHistoryList(contract2);
		
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveServiceRequestHistoryList_defect8217_CountMismatch() throws Exception {
		ServiceRequestHistoryListContract contract2 = new ServiceRequestHistoryListContract();
		contract2.setLocale(new Locale("en_US"));
		contract2.setMdmLevel("Global");
		contract2.setAccountId("1-17EKMXJ");
		contract2.setMdmID("205529410");
		contract2.setAssetId("1-8QJO-2616");
		contract2.setServiceRequestNumber("1-18157322340");
		contract2.setNewQueryIndicator(true);
		contract2.setIncrement(40);
		contract2.setStartRecordNumber(0);
		contract2.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestStatusDate", "ASCENDING");
		contract2.setSortCriteria(sortCriteria);
		
		ServiceRequestListResult result = service.retrieveServiceRequestHistoryList(contract2);
		
		System.out.println("Total count: " + result.getTotalCount());
	}
}
