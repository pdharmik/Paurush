package com.lexmark.service.impl.real;



import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.service.impl.real.domain.LBSAsset;


/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 *
 * @author David Tsamalashvili
 * @version 1.0, 2012-07-10
 */
public class AmindLBSFloorPlanAssetServiceTest extends AmindServiceTest {

	AmindLBSFloorPlanService service;
	SiebelAccountListContract contract;

	@Before
	public void setUp() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.setUp_AmindServerTest_static();
		service = new AmindLBSFloorPlanService();
		service.setSessionFactory(statelessSessionFactory);
		contract = new SiebelAccountListContract();
	}

	@After
	public void tearDown() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.tearDown_AmindServerTest_static();
	}

	
	@Test
	public void testRetrieveAddressList_LBSAsset_1() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-5HACH");
		contract.setAssetIds(assets);
		

		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSAsset_2() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("144809");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-5HACH");
		contract.setAssetIds(assets);
		

		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		MiscTest.print(result.getAssetList());
	}
	
	
	@Test
	public void testRetrieveAddressList_LBSAsset_3() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-5TJ2-1062");
		contract.setAssetIds(assets);
		

		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_FloorPlan_AssetDetails() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-8KNP-7492");
		contract.setAssetIds(assets);
		
		
		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_defect17367() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-5AVGVHY");
		contract.setAssetIds(assets);
		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_LBS1_5() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-GFLG8F");
		contract.setAssetIds(assets);
		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		for (LBSAsset asset : result.getAssetList()) {
			System.out.println("LevelOfDetails: " + asset.getLevelOfDetails());
		}
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_addressLevelDetails() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-OVVEWTP");
		contract.setAssetIds(assets);
		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		for (LBSAsset asset : result.getAssetList()) {
			System.out.println("AddressLevelDetails: " +asset.getAddressLevelDetails());
		}
		MiscTest.print(result.getAssetList());
	}
	
	@Test
	public void testRetrieveAddressList_defect18413() throws Exception {
		LBSAssetListContract contract = new LBSAssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setLbsFlag(true);
		List<String> assets = Arrays.asList("1-OVVEWTP");
		contract.setAssetIds(assets);
		LBSFloorPlanListResult result = service.retrieveLBSFloorPlanAssetList(contract);
		for (LBSAsset asset : result.getAssetList()) {
			System.out.println("BuildingType: " + asset.getBuildingType());
		}
		MiscTest.print(result.getAssetList());
	}
	
}
