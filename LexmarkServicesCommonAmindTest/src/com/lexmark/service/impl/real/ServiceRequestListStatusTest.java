package com.lexmark.service.impl.real;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
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
// TODO (pkozlov) need to separate tests to pagination/search/filter/sort tests
public class ServiceRequestListStatusTest extends RequestServiceStatefulBase {

	private final ServiceRequestListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ServiceRequestListContract contract;

		contract = new ServiceRequestListContract();
		contract.setMdmID("6681");
		contract.setMdmLevel("Legal");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setContactID("");
		contract.setAssetType("All");
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setNewQueryIndicator(true);
		list.add(new Object[] { contract });

		contract = new ServiceRequestListContract();
		contract.setMdmID("6681");
		contract.setMdmLevel("Legal");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setContactID("");
		contract.setAssetType("All");
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("asset.serialNumber", "Lex");
		contract.setFilterCriteria(filterCriteria);
		contract.setNewQueryIndicator(true);
		list.add(new Object[] { contract });
		
		contract = new ServiceRequestListContract();
		contract.setMdmID("236295");
		contract.setMdmLevel("Legal");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setContactID("1-P3YKE");
		contract.setNewQueryIndicator(true);
		Map<String,Object> searchCriteria = new HashMap<String,Object>();
		searchCriteria.put("serviceRequestStatus", "InProcess");
		contract.setSearchCriteria(searchCriteria);
		list.add(new Object[] { contract });
		
		contract = new ServiceRequestListContract();
		contract.setMdmID("T74848");
		contract.setMdmLevel("Account");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setContactID("1-P3YKE");
		contract.setNewQueryIndicator(true);
		list.add(new Object[] { contract });

		return list;
	}

	public ServiceRequestListStatusTest(ServiceRequestListContract contract) {
		this.contract = contract;
	}

	@Test
	public void testRetrieveServiceRequestsPagination() throws Exception {
		contract.setSessionHandle(handle);

		int iterationCount = 0;
		logger.info("Search criteria: mdm Id is '" + contract.getMdmID() + "' mdm level is '"
				+ contract.getMdmLevel() + "'.");

		logger.debug("handle: " + handle);
		logger.debug("contract handle: " + contract.getSessionHandle());

		long startTime = currentTimeMillis();
		ServiceRequestListResult result = service.retrieveServiceRequestList(contract);
		long endTime = currentTimeMillis();

		logger.info("\nRetrieveServiceReuqestList() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));

		assertNotNull("result is null!", result);
		List<ServiceRequest> serviceRequestList = result.getServiceRequests();

		assertNotNull("service requests list is null!", serviceRequestList);
		assertFalse(
				"No records found for mdmId: " + contract.getMdmID() + " and mdmLevel: "
						+ contract.getMdmLevel(), serviceRequestList.isEmpty());
		assertEquals("result total count doesn't match with size of the list!", result.getTotalCount(),
				serviceRequestList.size());

		logServiceRequestList(serviceRequestList);
	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequestList) {

		for (ServiceRequest serviceRequest : serviceRequestList) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus());
			if (serviceRequest.getServiceAddress() != null) {
				logger.info("Address Name" + serviceRequest.getServiceAddress().getAddressName());
			}
		}
	}
}
