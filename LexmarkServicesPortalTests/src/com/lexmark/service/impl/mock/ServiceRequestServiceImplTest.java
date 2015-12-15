package com.lexmark.service.impl.mock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.Pagination;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.service.util.SearchContractUtil;

public class ServiceRequestServiceImplTest {
	private ServiceRequestServiceImpl target;
	
	@Before
	public void setUp() throws Exception {
		target = new ServiceRequestServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieveServiceRequestList() throws Exception {
		Pagination pagination = new Pagination();
		//pagination.getSearchCriteria().put("serviceRequestNumber", "12");
		//pagination.getSearchCriteria().put("asset.serialNumber", "79");
		pagination.getSearchCriteria().put("returnShipDate", "08/13/2010");
		
		
		ServiceRequestListContract contract =  new ServiceRequestListContract();
		SearchContractUtil.copyPaginationToContract(pagination, contract);

		ServiceRequestListResult serviceRequestListResult = target.retrieveServiceRequestList(contract);
		Assert.assertEquals(909, serviceRequestListResult.getTotalCount());
	}

}
