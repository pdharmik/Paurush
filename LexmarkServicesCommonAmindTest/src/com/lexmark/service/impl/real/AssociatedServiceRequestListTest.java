package com.lexmark.service.impl.real;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;
import static java.lang.System.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;

import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;

@RunWith(Parameterized.class)
public class AssociatedServiceRequestListTest extends RequestServiceStatelessBase {

	private ServiceRequestAssociatedListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		ServiceRequestAssociatedListContract contract;

		contract = new ServiceRequestAssociatedListContract();
		contract.setSessionHandle(null);
		contract.setServiceRequestNumber("1-XY4XZ");// Parent SR
		list.add(new Object[] { contract });

		contract = new ServiceRequestAssociatedListContract();
		contract.setSessionHandle(null);
		contract.setServiceRequestNumber("1-9742303484");// Child SR
		list.add(new Object[] { contract });

		return list;
	}
	

	public AssociatedServiceRequestListTest(ServiceRequestAssociatedListContract contract) {
		this.contract = contract;
	}

	@Test
	public void testAssociatedRetrieveServiceRequestList() throws Exception {
		// TODO what for this iterationCount?
		int iterationCount = 0;

		long startTime = currentTimeMillis();
		ServiceRequestListResult result = service.retrieveAssociatedServiceRequestList(contract);
		long endTime = currentTimeMillis();

		logger.info("\nRetrieveServiceReuqestList() -- " + "iteration " + iterationCount + " "
				+ "elapsed Time(ms): " + (endTime - startTime));

		List<ServiceRequest> serviceRequests = result.getServiceRequests();
		assertNotNull(serviceRequests);
		assertEquals("Total count don't equals size of the list!", result.getTotalCount(), result
				.getServiceRequests().size());

		logServiceRequestList(result);
	}

	private void logServiceRequestList(ServiceRequestListResult result) {
		List<ServiceRequest> serviceRequestList = result.getServiceRequests();

		logger.info("Found serviceRequest count = " + serviceRequestList.size());
		for (ServiceRequest serviceRequest : serviceRequestList) {
			logger.info("SR Number:" + serviceRequest.getServiceRequestNumber() + " Status:"
					+ serviceRequest.getServiceRequestStatus() + " serialNumber:"
					+ serviceRequest.getReferenceNumber() + " Address" + serviceRequest.getServiceAddress());
		}
	}
}
