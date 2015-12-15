package com.lexmark.service.impl.real.requestService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;

@RunWith(Parameterized.class)
public class RequestTypeListChlNodeIdTest extends RequestTypeServiceBase {
	private final RequestListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "1-P8GRYE" });

		return list;
	}

	public RequestTypeListChlNodeIdTest(String chlNodeId) {
		contract = new RequestListContract();
		contract.setChlNodeId(chlNodeId);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
	}

	@Test
	public void testRetrieveRequestList() throws Exception {
		contract.setSessionHandle(handle);

		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!", list.isEmpty());

		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());

	}

}
