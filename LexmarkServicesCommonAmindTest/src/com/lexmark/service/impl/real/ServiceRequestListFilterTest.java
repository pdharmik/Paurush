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
public class ServiceRequestListFilterTest extends RequestServiceStatefulBase {

	private final Map<String, Object> filterMap;

	@Parameters
	public static List<Object[]> filterParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "asset.serialNumber", "6" });
		list.add(new Object[] { "serviceRequestNumber", "-33" });

		return list;
	}

	public ServiceRequestListFilterTest(String key, String value) {
		filterMap = new HashMap<String, Object>();
		filterMap.put(key, value);
	}

	@Test
	public void testRetrieveServiceRequestsFilter() throws Exception {
		ServiceRequestListContract contract = new ServiceRequestListContract();
		contract.setSessionHandle(handle);
		contract.setMdmID("T74848");
		contract.setMdmLevel("Account");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		// contract.setContactID("1-6IF18T");
		contract.setFilterCriteria(filterMap);

		doTestRetrieveServiceRequestsFilter(contract);
	}

	private void doTestRetrieveServiceRequestsFilter(ServiceRequestListContract contract) throws Exception {
		String mdmId = contract.getMdmID();
		String mdmLevel = contract.getMdmLevel();

		logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel + "'.");
		int iterationCount = 0;
		long startTime = currentTimeMillis();
		ServiceRequestListResult result = service.retrieveServiceRequestList(contract);
		long endTime = currentTimeMillis();
		logger.info("\nRetrieveServiceReuqestList() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));

		assertNotNull("result is null!", result);
		List<ServiceRequest> serviceRequests = result.getServiceRequests();
		assertNotNull("service requests list is null!", serviceRequests);
		assertEquals("result total count doesn't match with list size!", serviceRequests.size(),
				result.getTotalCount());
		assertFalse("No records found for mdmId: " + mdmId + " and mdmLevel: " + mdmLevel,
				serviceRequests.isEmpty());

		logServiceRequestList(serviceRequests);

		logger.info("We're done, found total of " + contract.getStartRecordNumber() + " records.");

	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequests) {
		logger.info("Found serviceRequest count = " + serviceRequests.size());
		for (ServiceRequest serviceRequest : serviceRequests) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getReferenceNumber() + " Address" + serviceRequest.getServiceAddress());
		}
	}
}
