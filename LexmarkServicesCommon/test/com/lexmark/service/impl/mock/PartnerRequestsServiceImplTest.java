package com.lexmark.service.impl.mock;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.result.CustomerAccountListResult;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;

public class PartnerRequestsServiceImplTest {
	private PartnerRequestsService  service;
	@Before
	public void setUp() throws Exception {
		service = new PartnerRequestsServiceImpl();
	}
	
	@After
	public void destroy() {
		service = null;
	}

	@Test
	public void testRetrieveGlobalPartnerAssetList() {
		GlobalPartnerAssetListContract contract = new GlobalPartnerAssetListContract();
		contract.setSerialNumber("1010004");
		
		GlobalPartnerAssetListResult result = service.
			retrieveGlobalPartnerAssetList(contract);
		//assertEquals(1, result.getTotalCount());
		assertEquals("1010004",result.getAssetList().get(0).getSerialNumber());
		
	}
	
	@Test
	public void testRetrieveCustomerAccountList() {
		CustomerAccountListContract contract = new CustomerAccountListContract();
		
		CustomerAccountListResult result = null;
		try {
			result = service.retrieveCustomerAccountList(contract);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(result.getTotalCount());
		assertNotNull(result.getAccountList());
	}

	@Test
	public void testRetrievePartnerIndirectAccountList(){
		PartnerIndirectAccountListContract contract = new PartnerIndirectAccountListContract();
		PartnerIndirectAccountListResult result = null;
		try{
			result = service.retrievePartnerIndirectAccountList(contract);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(result.getTotalCount());
		assertNotNull(result.getAccountList());
	}
}
