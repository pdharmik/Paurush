package com.lexmark.service.impl.real;



import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.contract.LBSCHLContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.LBSCHLListResult;
import com.lexmark.result.LBSFloorPlanListResult;


/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 *
 * @author David Tsamalashvili
 * @version 1.0, 2012-07-10
 */
public class AmindLBSCHLMappingServiceTest extends AmindServiceTest {

	AmindCHLMappingService service;
	SiebelAccountListContract contract;

	@Before
	public void setUp() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.setUp_AmindServerTest_static();
		service = new AmindCHLMappingService();
		service.setSessionFactory(statelessSessionFactory);
		contract = new SiebelAccountListContract();
	}

	@After
	public void tearDown() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.tearDown_AmindServerTest_static();
	}

	
	@Test
	public void testRetrieveCHLtList_CHL_Mapping_1() throws Exception {
		LBSCHLContract contract = new LBSCHLContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
//		contract.setChildID("1-15P2I9X");
		contract.setChildID("1-2HGB0O1");
		
		LBSCHLListResult result = service.retrieveCHLtList(contract);
		
		System.out.println(result);
	}
	
}
