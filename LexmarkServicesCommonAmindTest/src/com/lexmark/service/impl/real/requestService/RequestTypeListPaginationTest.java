package com.lexmark.service.impl.real.requestService;

import static org.junit.Assert.assertEquals;
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
public class RequestTypeListPaginationTest extends RequestTypeServiceBase {
	
	private final int startRecordNumber;
	private final int increment;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		
		list.add(new Object[] {0, 10});
		list.add(new Object[] {0, 20});
		list.add(new Object[] {10, 20});
		list.add(new Object[] {15, 20});
		
		return list;
	}
	
	public RequestTypeListPaginationTest(int startRecordNumber, int increment) {
		this.startRecordNumber = startRecordNumber;
		this.increment = increment;
	}
	
	@Test
	public void testRequestTypeListPagination() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("1-3S-445");
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		
		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!",list.isEmpty());
		
		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());
		
		assertEquals("pagination fails!",increment,list.size());
	}

}
