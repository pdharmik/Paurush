package com.lexmark.service.impl.real;

import static java.lang.System.currentTimeMillis;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;

@RunWith(Parameterized.class)
public class ServiceRequestListMdmTest extends RequestServiceStatefulBase {

	private final ServiceRequestListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ServiceRequestListContract contract;

		/*contract = new ServiceRequestListContract();
		contract.setMdmID("T74848");
		contract.setMdmLevel("Account");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serviceRequestNumber", "1-9302583501");
		contract.setFilterCriteria(filterCriteria);
		list.add(new Object[] { contract });*/

		contract = new ServiceRequestListContract();
		contract.setMdmID("023058159");
		contract.setMdmLevel("Global");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serviceRequestNumber", "1-9326680191");
		contract.setFilterCriteria(filterCriteria);
		list.add(new Object[] { contract });

		/*contract = new ServiceRequestListContract();
		contract.setMdmID("L1-MMJPR");
		contract.setMdmLevel("Legal");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		list.add(new Object[] { contract });

		contract = new ServiceRequestListContract();
		contract.setMdmID("T74848");
		contract.setMdmLevel("Account");
		contract.setChlNodeId("1-3F3QGI");
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		list.add(new Object[] { contract });*/

		return list;
	}

	public ServiceRequestListMdmTest(ServiceRequestListContract contract) {
		this.contract = contract;
	}

	@Test
	public void testRetrieveServiceRequests() throws Exception {
		contract.setSessionHandle(handle);
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
		assertNotNull("service requests list is null!", serviceRequests);
		assertEquals("result total count doesn't match with list size!", serviceRequests.size(),
				result.getTotalCount());
		assertFalse("service requests list is empty!", serviceRequests.isEmpty());

		logServiceRequestList(serviceRequests);
	}

	private void logServiceRequestList(List<ServiceRequest> serviceRequests) {
		logger.info("Found serviceRequest count = " + serviceRequests.size());
		for (ServiceRequest serviceRequest : serviceRequests) {
			GenericAddress address = serviceRequest.getServiceAddress();
			String addressText = "(null)";

			if (address != null) {
				addressText = "" + address.getAddressLine1() + ";" + address.getCity() + ";"
						+ address.getState();
			}
			Asset asset = serviceRequest.getAsset();
			String assetText = "(null)";
			if (asset != null) {
				assetText = "" + asset.getAssetId() + ";" + asset.getSerialNumber() + ";"
						+ asset.getModelNumber();
			}

			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getReferenceNumber() + " Address:" + addressText + " Asset:" + assetText);
		}
	}
}
