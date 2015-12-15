package com.lexmark.service.impl.real.requestService;

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

import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;
import com.lexmark.service.impl.real.MiscTest;

@RunWith(Parameterized.class)
public class RequestTypeListTest extends RequestTypeServiceBase {

	private final RequestListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		
		list.add(new Object[] {"1-3S-445", "Siebel"});
//		list.add(new Object[] {"1-AHZ6X5", "Siebel"});
		
		return list;
	}

	public RequestTypeListTest(String mdmId, String mdmLevel) {
		contract = new RequestListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
	}
	
	@Test
	public void testRetrieveRequestListWithFavoriteFlag() throws Exception {
		contract.setSessionHandle(handle);
		contract.setAssetFavoriteFlag(true);
		contract.setMdmId("236295");
		contract.setContactId("1-PE7QX");
		contract.setMdmLevel("Siebel");
		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		assertNotNull("Service Request is null",list);
		assertFalse("Service Request list is empty",list.isEmpty());
	}

	@Test(timeout=100000)
	public void testRetrieveRequestList() throws Exception {
		contract.setSessionHandle(handle);
		
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("serviceRequest.startDate","06/01/2012 08:00:00");
	    filter.put("serviceRequest.endDate","06/25/2012 08:00:00");
		
        contract.setFilterCriteria(filter);
        contract.setAssetType("MPS");
        

		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		MiscTest.print(list);
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!", list.isEmpty());

		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());

//		debugRequests(list);

	}

}
