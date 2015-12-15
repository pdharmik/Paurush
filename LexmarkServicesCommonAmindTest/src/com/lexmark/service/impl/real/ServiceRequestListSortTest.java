package com.lexmark.service.impl.real;

import static java.lang.System.currentTimeMillis;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;

@RunWith(Parameterized.class)
public class ServiceRequestListSortTest extends RequestServiceStatefulBase {

	private final Map<String, Object> sortMap;

	@Parameters
	public static List<Object[]> sortParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "serviceRequestNumber", "DESCENDING" });
		list.add(new Object[] { "serviceRequestStatus", "ASCENDING" });
		return list;
	}

	public ServiceRequestListSortTest(String key, String value) {
		sortMap = new HashMap<String, Object>();
		sortMap.put(key, value);
	}

	@Test
	public void testRetrieveServiceRequestsSortingAccount() throws Exception {
		ServiceRequestListContract contract = new ServiceRequestListContract();
		contract.setSessionHandle(handle);
		contract.setMdmID("T74848");
		contract.setMdmLevel("Account");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		contract.setContactID("1-6IF18T");
		contract.setSortCriteria(sortMap);
		doServiceRequestSortingTest(contract);
	}

	@Test
	public void testRetrieveServiceRequestsSortingLegal() throws Exception {
		ServiceRequestListContract contract = new ServiceRequestListContract();
		contract.setSessionHandle(handle);
		contract.setMdmID("43210");
		contract.setMdmLevel("Legal");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		contract.setContactID("1-6IF18T");
		contract.setSortCriteria(sortMap);
		doServiceRequestSortingTest(contract);
	}

	private void doServiceRequestSortingTest(ServiceRequestListContract contract) throws Exception {
		logger.info("Search criteria: mdm Id is '" + contract.getMdmID() + "' mdm level is '"
				+ contract.getMdmLevel() + "'.");

		int iterationCount = 0;
		long startTime = currentTimeMillis();
		ServiceRequestListResult result = service.retrieveServiceRequestList(contract);
		long endTime = currentTimeMillis();

		logger.info("\nRetrieveServiceRequestList() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));

		assertNotNull("result is null!", result);
		List<ServiceRequest> serviceRequests = result.getServiceRequests();
		assertNotNull("service requests list is null!", serviceRequests);
		assertEquals("result total count doesn't match with list size!", serviceRequests.size(),
				result.getTotalCount());
		assertFalse("service requests list is empty!", serviceRequests.isEmpty());

		logServiceRequestList(serviceRequests);
	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequests) {

		logger.info("Found serviceRequest count = " + serviceRequests.size());
		for (ServiceRequest serviceRequest : serviceRequests) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getAsset().getSerialNumber() + " Address"
					+ serviceRequest.getServiceAddress());
		}
	}
}
