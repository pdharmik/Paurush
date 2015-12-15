package com.lexmark.service.impl.real.requestService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;

@RunWith(Parameterized.class)
public class RequestTypeListDateFilterTest extends RequestTypeServiceBase {
	private final String startDate;
	private final String endDate;

	@Parameters
	public static List<Object[]> filterParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] {"Fri May 25 04:00:00 EDT 2012", "Fri April 25 04:00:00 EDT 2012"} );
		
		return list;
	}

	public RequestTypeListDateFilterTest(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Test
	public void testRequestTypeListFilter() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("1-3S-445");
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
		
		HashMap<String, Object> filterCriteria = new HashMap<String, Object>(2);
		filterCriteria.put("serviceRequest.startDate",startDate);
		filterCriteria.put("serviceRequest.endDate",endDate);
		contract.setFilterCriteria(filterCriteria);

		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!", list.isEmpty());

		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());

//		for (ServiceRequest request : list) {
//		}

	}
}
