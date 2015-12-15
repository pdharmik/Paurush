package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;
import static org.junit.Assert.*;
import static java.lang.System.*;
import static junit.framework.Assert.assertEquals;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;

@RunWith(Parameterized.class)
public class ServiceRequestListSearchTest extends RequestServiceStatefulBase {

	private final Map<String, Object> searchMap;

	@Parameters
	public static List<Object[]> searchParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "serviceRequestNumber", "1-33" });
		list.add(new Object[] { "asset.serialNumber", "16" });
		return list;
	}

	public ServiceRequestListSearchTest(String key, String value) {
		searchMap = new HashMap<String, Object>();
		searchMap.put(key, value);
	}

	@Test
	public void testRetrieveServiceRequestsSearchAccount() throws Exception {
		ServiceRequestListContract requestContract = new ServiceRequestListContract();

		requestContract.setSessionHandle(handle);
		requestContract.setMdmID("T74848");
		requestContract.setMdmLevel("Account");
		requestContract.setStartRecordNumber(0);
		requestContract.setIncrement(100);
		requestContract.setNewQueryIndicator(true);
		requestContract.setSearchCriteria(searchMap);

		doRetreiveServiceRequestsSearchTest(requestContract);
	}

	@Test
	public void testRetrieveServiceRequestsSearchLegal() throws Exception {
		ServiceRequestListContract requestContract = new ServiceRequestListContract();

		requestContract.setSessionHandle(handle);
		requestContract.setMdmID("43210");
		requestContract.setMdmLevel("Legal");
		requestContract.setStartRecordNumber(0);
		requestContract.setIncrement(100);
		requestContract.setNewQueryIndicator(true);
		requestContract.setSearchCriteria(searchMap);

		doRetreiveServiceRequestsSearchTest(requestContract);
	}

	private void doRetreiveServiceRequestsSearchTest(ServiceRequestListContract contract) throws Exception {
		logger.info("Search criteria: mdm Id is '" + contract.getMdmID() + "' mdm level is '"
				+ contract.getMdmLevel() + "'.");
		int iterationCount = 0;
		long startTime = currentTimeMillis();
		ServiceRequestListResult result = service.retrieveServiceRequestList(contract);
		long endTime = currentTimeMillis();

		logger.info("\nRetrieveServiceReuqestList() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));

		assertNotNull("result is null!", result);
		List<ServiceRequest> serviceRequests = result.getServiceRequests();
		assertNotNull("requests list is null!", serviceRequests);
		assertEquals("result total count doesn't match with list size!", serviceRequests.size(),result.getTotalCount());
		assertFalse("requests list is empty!", serviceRequests.isEmpty());

		logServiceRequestList(serviceRequests);
	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequestList) {
		logger.info("Found serviceRequest count = " + serviceRequestList.size());
		for (ServiceRequest serviceRequest : serviceRequestList) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getAsset().getSerialNumber() + " Address"
					+ serviceRequest.getServiceAddress());
		}
	}
}
