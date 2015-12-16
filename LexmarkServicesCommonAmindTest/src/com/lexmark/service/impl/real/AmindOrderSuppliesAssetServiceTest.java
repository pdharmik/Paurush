package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.StatefulSessionFactory;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.PageCountsResult;
import com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDo;
import com.lexmark.util.LangUtil;

/**
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetDo()
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, Mar 15, 2012
 */
public class AmindOrderSuppliesAssetServiceTest extends AmindServiceTest {

	private static AmindOrderSuppliesAssetService service;

	@BeforeClass
	public static void setUpBeforeClass() {
		service = new AmindOrderSuppliesAssetService();
		service.setStatelessSessionFactory(statelessSessionFactory);
	}

	@Test
	public void testRetrieveDeviceList() {
		AssetListContract contract = defaultAssetListContract();
		AssetListResult result = service.retrieveDeviceList(contract);
		for (Asset a : LangUtil.notNull(result.getAssets())) {
			System.out.println(str(a));
		}
		System.out.println("totalCount=" + result.getTotalCount());
	}

	@Test
	public void testRetrieveDeviceList_QA_Defect1722() {
		AssetListContract contract = new AssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setStartRecordNumber(1);
		contract.setIncrement(12);
		contract.setNewQueryIndicator(true);
		contract.setMdmId("023058159");
		contract.setMdmLevel("Global");
		contract.setContactId("1-42M3K0F");
		AssetListResult result;
		long t0 = System.currentTimeMillis();
		try {
			result = service.retrieveDeviceList(contract);
			MiscTest.print("", result.getAssets());
			System.out.println("totalCount=" + result.getTotalCount());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveDeviceList_Prod_Defect6596() {
		AssetListContract contract = new AssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setStartRecordNumber(0);
		contract.setIncrement(12);
		contract.setNewQueryIndicator(true);
		contract.setMdmId("30281");
		contract.setMdmLevel("Legal");
		contract.setContactId("1-5BIE4M7");
		contract.setChlNodeId("1-1LIUDOT");
		AssetListResult result;
		long t0 = System.currentTimeMillis();
		try {
			result = service.retrieveDeviceList(contract);
			MiscTest.print("", result.getAssets());
			System.out.println("totalCount=" + result.getTotalCount());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

    @Test
    public void testRetrieveDeviceList_QA_Defect10038() {
    	AssetListContract contract = new AssetListContract();
    	contract.setSessionHandle(crmSessionHandle);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setNewQueryIndicator(true);
    	contract.setMdmId("205529410");
    	contract.setMdmLevel("Global");
    	contract.setContactId("1-526QV0B");
    	contract.setEntitlementEndDate("12/04/2013");
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"serialNumber", "ASCENDING"));
    	AssetListResult result;
    	long t0 = System.currentTimeMillis();
    	try {
    		result = service.retrieveDeviceList(contract);
    		for (Asset asset : result.getAssets()) {
    			System.out.println("Email: " + asset.getAssetContact().getEmailAddress());
    		}
    		System.out.println("totalCount=" + result.getTotalCount());
    	} finally {
    		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
    	}
    }
    
    @Test
    public void testRetrieveDeviceList_QA_NullPointer() {
    	AssetListContract contract = new AssetListContract();
    	contract.setSessionHandle(crmSessionHandle);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setNewQueryIndicator(true);
    	contract.setMdmId("2895");
    	contract.setMdmLevel("Account");
    	contract.setContactId("1-6DV03JT");
//    	contract.setChlNodeId("1-1S402KV");
    	contract.setEntitlementEndDate("11/22/2013");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
                "serialNumber", "ASCENDING")); 
    	AssetListResult result;
    	long t0 = System.currentTimeMillis();
    	try {
    		result = service.retrieveDeviceList(contract);
    		MiscTest.print("", result.getAssets());
    		for (Asset asset : result.getAssets()) {
    			System.out.println("AssetId: " + asset.getAssetId());
    		}
    		System.out.println("totalCount=" + result.getTotalCount());
    	} finally {
    		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
    	}
    }
    
    @Test
    public void testRetrieveDeviceList_QA_EEMPS_Defect9286() {
        AssetListContract contract = new AssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setNewQueryIndicator(true);
        contract.setMdmId("43800");
        contract.setMdmLevel("Legal");
        contract.setContactId("1-6AH086J");
        contract.setEntitlementEndDate("10/10/2013");
        AssetListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveDeviceList(contract);
            MiscTest.print("", result.getAssets());
            System.out.println("totalCount=" + result.getTotalCount());
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    
    @Test
    public void testRetrieveDeviceList_QA_Defect4143() {
        AssetListContract contract = new AssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(12);
        contract.setNewQueryIndicator(true);
        contract.setMdmId("006961700");
        contract.setMdmLevel("Global");
        contract.setContactId("1-4ADJPG9");
        contract.setFavoriteFlag(true);
        AssetListResult result;
        long t0 = System.currentTimeMillis();
        try {
            result = service.retrieveDeviceList(contract);
            MiscTest.print("", result.getAssets());
            
            for (Asset a : result.getAssets()) {
                System.out.println("assetContact = " + str(a.getAssetContact()));
                System.out.println("installAddress = " + str(a.getInstallAddress()));
                System.out.println("favFlag = " +   a.getUserFavoriteFlag());
                System.out.println("consumableAssetFlag = " +   a.isConsumableAssetFlag());
                System.out.println("======");
                System.out.println("asset = " + str(a));
            }
            System.out.println("totalCount=" + result.getTotalCount());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    

	@Test
	public void testRetrieveDeviceList_QA_assetFavFlagTrue() {
		AssetListContract contract = defaultAssetListContract();
		contract.setFavoriteFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setContactId("1-1SU00J3");

		AssetListResult result = service.retrieveDeviceList(contract);
		MiscTest.print(result.getAssets());
		System.out.println("totalCount=" + result.getTotalCount());
	}

	@Test
	public void testRetrieveDeviceList_QA_chlNodeId() {
		AssetListContract contract = defaultAssetListContract();
		// contract.setFavoriteFlag(true);
		contract.setNewQueryIndicator(false);
		contract.setChlNodeId("mock-chlNodeId");
		contract.setContactId("1-1SU00J3");

		AssetListResult result = service.retrieveDeviceList(contract);
		for (Asset a : LangUtil.notNull(result.getAssets())) {
			System.out.println(str(a));

		}
		System.out.println("totalCount=" + result.getTotalCount());
	}

	/**
	 * Mapping file: do-ordersuppliesassetdetaildo-mapping.xml
	 */
	@Test
	public void testRetrieveDeviceDetail_middleName() {
		AssetContract contract = new AssetContract();
		// contract.setAssetId("1-TY0FYQ");
		contract.setAssetId("1-363P26");
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
		System.out.println("assetContact.fistName="
				+ result.getAsset().getAssetContact().getFirstName());
		System.out.println("assetContact.middleName="
				+ result.getAsset().getAssetContact().getMiddleName());
	}

	/**
	 * Mapping files: do-ordersuppliesassetdetaildo-mapping.xml
	 * do-mpsmeterread-mapping.xml
	 */
	@Test
	public void testRetrieveDeviceDetail_pageCounts_QA() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-3DHJKNH");
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
		System.out.println("============== pageCounts: ");
		List<PageCounts> pageCounts = LangUtil.notNull(result.getAsset()
				.getPageCounts());
		MiscTest.print("\t", pageCounts);
		assertTrue(pageCounts.size() > 0);
	}

	@Test
	public void testRetrieveDeviceDetail_QA_20130402_ConsumableAssetFlag()
			throws Exception {
		AssetContract contract = new AssetContract();
		contract.setContactId("1-50WCXE5");
		contract.setAssetId("1-4LA0JS4");

		long t0 = System.currentTimeMillis();
		try {
			AssetResult r = service.retrieveDeviceDetail(contract);
			MiscTest.print("", r.getAsset(), "serialNumber",
					"consumableAssetFlag");
			System.out.println("serialNumber = "
					+ r.getAsset().getSerialNumber());
			System.out.println("consumableAssetFlag = "
					+ r.getAsset().isConsumableAssetFlag());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveDeviceDetail_QA_Defect1566() {
		AssetContract contract = new AssetContract();
		long t0 = System.currentTimeMillis();
		try {
			// contract.setAssetId("1-3DHJKNH"); // without expeditOrderFlag,
			// Excec time=97.633 sec.
			// contract.setAssetId("1-51EPJXQ");// with expeditOrderFlag
			// contract.setAssetId("1-8BVT-6001"); // with installationOnlyFlag
			contract.setAssetId("1-8OUA-9462"); // with lastOrderPartList
			// contract.setAssetId("1-7CJ5-9461"); // without lastOrderPartList
			// contract.setAssetId("1-61UK-6067"); // without
			// installationOnlyFlag
			AssetResult result = service.retrieveDeviceDetail(contract);
			MiscTest.print("asset.installationOnlyFlag=", result.getAsset()
					.isInstallationOnlyFlag());
			MiscTest.print("asset.lastOrderPartList=", result.getAsset()
					.getLastOrderPartList());
			MiscTest.print("serialNumber = ", result.getAsset()
					.getSerialNumber());
			MiscTest.print("assetId = ", result.getAsset().getAssetId());

			Account acc = result.getAsset().getAccount();
			MiscTest.print("account.assetExpediteFlag=",
					acc.isAssetExpediteFlag());
			MiscTest.print("account.catalogExpediteFlag=",
					acc.isCatalogExpediteFlag());
			MiscTest.print("account.assetEntitlementFlag=",
					acc.isAssetEntitlementFlag());
			MiscTest.print("account.catalogEntitlementFlag=",
					acc.isCatalogEntitlementFlag());

		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveDeviceDetail_QA_lastOrderParts() {
		AssetContract contract = new AssetContract();
		long t0 = System.currentTimeMillis();
		try {
			// contract.setAssetId("1-8OUA-9462"); // with lastOrderPartList
			// contract.setAssetId("1-7CJ5-9461"); // without lastOrderPartList
			contract.setAssetId("1-9UVA4B"); // with lastOrderPartList, if
												// currentDate < 2012-Nov
			// contract.setAssetId("1-61UK-6067"); // without
			// installationOnlyFlag
			contract.setCurrentDate(new java.util.Date(-1));
			AssetResult result = service.retrieveDeviceDetail(contract);
			MiscTest.print("asset.assetExpediteFlag=", result.getAsset()
					.getAccount().isAssetExpediteFlag());
			// MiscTest.print("asset.lastOrderPartList=",
			// result.getAsset().getLastOrderPartList());
			MiscTest.print("asset.lastOrderPartList=", result.getAsset()
					.getLastOrderPartList(), "partNumber", "partType",
					"shippedDate", "description");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveDeviceDetail_QA_lastOrderParts_query()
			throws Exception {
		MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class,
		// "[serialNumber] = '0630605'"
				"[serialNumber] = '0630643'", 5);
	}

	@Test
	public void testRetrieveDeviceDetail_QA_Defect6602() {
		AssetContract contract = new AssetContract();
		long t0 = System.currentTimeMillis();
		try {
			contract.setAssetId("1-43GPLKE");
			AssetResult result = service.retrieveDeviceDetail(contract);
			// MiscTest.print("asset.account.assetExpediteFlag=",
			// result.getAsset().getAccount().isAssetExpediteFlag());
			// MiscTest.print("assetId = ", result.getAsset().getAssetId());
			// MiscTest.print("serialNumber = ",
			// result.getAsset().getSerialNumber());
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	/**
	 * @see com.lexmark.service.impl.real.domain.OrderSuppliesAssetDetailDoTest#queryOrderSuppliesAssetDetailDo_parts()
	 */
	@Test
	public void testRetrieveDeviceDetail_QA_Defect3753() {
		AssetContract contract = new AssetContract();
		// contract.setAssetId("1-69CM-1268");
		contract.setAssetId("1-5TH7TIO");
		contract.setCurrentDate(new Date());
		AssetResult result = service.retrieveDeviceDetail(contract);
		// MiscTest.print(result.getAsset().getPartList());
		// MiscTest.print("account = ", str(result.getAsset().getAccount()));
	}

	/**
	 * @see AmindContractedServiceRequestServiceTest#testRetrieveSiebelAccountList_QA_Defect4166()
	 */
	@Test
	public void testRetrieveDeviceDetail_QA_Defect4166() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-69CM-1268");
		AssetResult result = service.retrieveDeviceDetail(contract);
		MiscTest.print("account.expediteOrderFlag = ", str(result.getAsset()
				.getAccount().isAssetEntitlementFlag()));
	}

	@Test
	public void testRetrieveDeviceDetail_QA_Defect4166_2() {
		AssetContract contract = new AssetContract();
		contract.setMdmId("23593");
		contract.setMdmLevel("Legal");
		contract.setAssetId("1-69CM-1268");
		AssetResult result = service.retrieveDeviceDetail(contract);
		MiscTest.print("asset = ", str(result.getAsset()));
		MiscTest.print("asset.account = ", str(result.getAsset().getAccount()));
	}

	/**
	 * @see AmindContractedDeviceServiceTest#testRetrieveDeviceDetail_Production_Defect5750()
	 */
	@Test
	public void testRetrieveDeviceDetail_Production_Defect5750() {
		AssetContract contract = new AssetContract();
		// contract.setAssetId("1-4LFFOSO");
		contract.setAssetId("1-5TH7TIO");
		contract.setCurrentDate(new Date());
		AssetResult result = service.retrieveDeviceDetail(contract);
		MiscTest.print(result.getAsset().getPageCounts());
		MiscTest.print("asset.serialNumber = ", result.getAsset()
				.getSerialNumber());
	}

	@Test
	public void testRetrieveDeviceDetail_pageCounts_manual() {
		AssetContract contract = new AssetContract();
		// contract.setAssetId("1-3DHJKNH");
		contract.setAssetId("1-5HOGV");
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
		System.out.println("============== pageCounts: ");
		List<PageCounts> pageCounts = LangUtil.notNull(result.getAsset()
				.getPageCounts());
		MiscTest.print("\t", pageCounts);
		assertTrue(pageCounts.size() > 0);
	}

	@Test
	public void testRetrieveDeviceDetail_pageCounts_automatic() {
		AssetContract contract = new AssetContract();
		// contract.setAssetId("1-3DHJKNH");
		contract.setAssetId("1-5HOGV"); // todo
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
		System.out.println("============== pageCounts: ");
		List<PageCounts> pageCounts = LangUtil.notNull(result.getAsset()
				.getPageCounts());
		MiscTest.print("\t", pageCounts);
		assertTrue(pageCounts.size() > 0);
	}

	@Test
	public void queryOrderSuppliesAssetDetailDo() throws Exception {
		// MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class,
		// "[meterType] <> ''", 5);
		// MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class,
		// "[meterType] ~LIKE 'manual'", 5);
		MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class,
				"[meterType] ~LIKE 'automatic'", 5);
		// MiscTest.sampleSiebelQuery(OrderSuppliesAssetDetailDo.class,
		// "[meterType] = ''", 5);
	}

	@Test
	public void testRetrieveDeviceDetail_MPSQA_middleName() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-37WKZF");
		// contract.setMdmId("236295");
		// contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		AssetResult result = service.retrieveDeviceDetail(contract);
		MiscTest.print(result.getAsset().getPartList());
		System.out.println("assetContact.fistName="
				+ result.getAsset().getAssetContact().getFirstName());
		System.out.println("assetContact.middleName="
				+ result.getAsset().getAssetContact().getMiddleName());
		// print(result);
	}

	private void print(AssetResult assetResult) {
		if (assetResult == null) {
			logger.info("assetResult is null");
			return;
		}
		logger.info("result=" + assetResult);
		logger.info("result.getAsset()=" + str(assetResult.getAsset()));
	}

	@Test
	public void testRetrieveDeviceDetail2() {
		AssetContract contract = new AssetContract();
		// contract.setContactId("1-52Y7PN");
		contract.setAssetId("1-BPRKV6");
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
	}

	@Test
	public void testRetrieveDeviceDetailPageCounts() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-GKTUZK");
		AssetResult result = service.retrieveDeviceDetail(contract);
		print(result);
		for (PageCounts pc : result.getAsset().getPageCounts()) {
			System.out.println("pageCount=" + str(pc));
		}
		assertNotNull(result.getAsset().getPageCounts());
	}

	/**
	 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetDo()
	 */
	private static AssetListContract defaultAssetListContract() {
		AssetListContract contract = new AssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		// contract.setMdmId("57408");
		// contract.setMdmId("1-4IO9X");
		contract.setMdmId("1-52Y7PN");
		// contract.set
		contract.setMdmLevel("Siebel");

		contract.setStartRecordNumber(0);
		contract.setIncrement(20);
		contract.setNewQueryIndicator(true);
		// contract.setAgreementId("1");

		contract.setContactId("1-1LUIPWR");
		// contract.setContactId("1-1SU00J3");
		// contract.setContactId("1-52Y7PN");
		// contract.setFavoriteFlag(false);
		// contract.setFavoriteFlag(true);
		contract.setAssetType("ALL");
		contract.setChlNodeId("");
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		return contract;
	}

	/**
	 * Mapping file: do-consumableassetlocationdo-mapping.xml
	 * 
	 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryConsumableAssetLocationDo()
	 * @see com.lexmark.service.impl.real.domain.ConsumableAssetLocationDoTest
	 */
	@Test
	public void testRetrieveAssetLocations() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		// contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
		// contract.setMdmId("1-3QP5");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_ACCOUNT);
		contract.setMdmId("236295");
		contract.setEntitlementEndDate("11/06/2012");
		AssetLocationsResult resultWrapper = service
				.retrieveAssetLocations(contract);
		System.out.println("resultWrapper=" + str(resultWrapper));
		List<GenericAddress> result = resultWrapper.getAddressList();
		if (LangUtil.isNotEmpty(result)) {
			System.out.println("result.size()=" + result.size());
			MiscTest.print(result);
			// System.out.println("result=" + result);
		}
	}

	@Test
	public void testRetrieveAssetLocations_byChlNodeId() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setEntitlementEndDate("11/06/2012");
		contract.setChlNodeId("1-219TUCC");
		AssetLocationsResult resultWrapper = service
				.retrieveAssetLocations(contract);
		System.out.println("resultWrapper=" + str(resultWrapper));
		List<GenericAddress> result = resultWrapper.getAddressList();
		if (LangUtil.isNotEmpty(result)) {
			System.out.println("result.size()=" + result.size());
			MiscTest.print(result);
		}
	}

   @Test
    public void testRetrieveDeviceList_QA_defect10638() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setNewQueryIndicator(true); 
    	contract.setContactId("1-783R8NP");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("34121");
    	contract.setEntitlementEndDate("12/19/2013");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"serialNumber", "DESCENDING")); 
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	for (Asset a : LangUtil.notNull(result.getAssets())) {
    		System.out.println("Contract Number: " + a.getContractNumber());
    	}
    	System.out.println("totalCount=" + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveDeviceList_QA_defect10806() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setContactId("1-7WU2CR1");
    	contract.setMdmLevel("Domestic");
    	contract.setMdmId("275142552");
    	contract.setEntitlementEndDate("01/15/2014");
    	Map<String, Object> filterCriteria =  new HashMap<String, Object>();
    	filterCriteria.put("serialNumber", "94F6HHN");
		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
    	contract.setNewQueryIndicator(true); 
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	System.out.println("totalCount=" + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveDeviceList_QA_defect10856() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setNewQueryIndicator(true); 
    	contract.setContactId("1-7CNKRSH");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("10326");
    	contract.setEntitlementEndDate("12/27/2013");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
        
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
		
    	AssetListResult result = service.retrieveDeviceList(contract);
    	System.out.println("totalCount=" + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveDeviceList_QA_defect10966_list() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setNewQueryIndicator(true); 
    	contract.setContactId("1-7CUW7E9");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("58971");
    	contract.setEntitlementEndDate("01/07/2014");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("serialNumber", "TST200000669");
		contract.setFilterCriteria(filterCriteria );
    	
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		
		}
    	logger.debug("end");
    }
    
    @Test
    public void testRetrieveDeviceList_QA_defect11162() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setNewQueryIndicator(true); 
    	contract.setContactId("1-7WTFIAP");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("34509");
    	contract.setEntitlementEndDate("02/03/2014");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	logger.debug("Total " + result.getTotalCount());
    	logger.debug("end");
    }
    
    @Test
    public void testRetrieveDeviceList_QA_defect11464() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setContactId("1-85SLJIR");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("55838");
    	contract.setEntitlementEndDate("02/25/2014");
    	contract.setNewQueryIndicator(true); 
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	logger.debug("Total: " + result.getTotalCount());
    	logger.debug("end");
    }
    
    @Test
    public void testRetrieveDeviceList_QA_searchSpecTest() {
    	AssetListContract contract = defaultAssetListContract();
    	contract.setNewQueryIndicator(true); 
    	contract.setContactId("1-7C6YYWL");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("63019");
    	contract.setEntitlementEndDate("12/19/2013");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"installAddress.addressName", "DESCENDING"));
    	contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "72MYTK4"));
    	
    	AssetListResult result = service.retrieveDeviceList(contract);
    	for (Asset a : LangUtil.notNull(result.getAssets())) {
    		
    	}
    	System.out.println("totalCount=" + result.getTotalCount());
    }

	/**
	 * 
	 * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListServiceTest#testRetrieveAllDeviceList_QA()
	 */
	@Test
	public void testRetrieveAllDeviceList() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-5HR5CYV");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("10/27/2012 05:30:00");
		contract.setLoadAllFlag(false);
		// contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
		// "serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);

		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveAllDeviceListProduction() throws Exception {
		// AssetListContract contract = new AssetListContract();
		// contract.setMdmId("315000554");
		// contract.setMdmLevel("Domestic");
		// contract.setSessionHandle(crmSessionHandle);

		AssetListContract contract = new AssetListContract();
		contract.setMdmId("1-NH1BWR");
		contract.setMdmLevel("Siebel");
		contract.setSessionHandle(crmSessionHandle);

		// AssetListContract contract = new AssetListContract();
		// contract.setMdmId("66309");
		// contract.setMdmLevel("Legal");
		// contract.setSessionHandle(crmSessionHandle);

		contract.setContactId("1-5HR5CYV");
		contract.setEntitlementEndDate("10/27/2012 05:30:00");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

		System.out.println(result);
	}

	@Test
	public void testRetrieveConsumableAssetListProduction() throws Exception {
		// AssetListContract contract = new AssetListContract();
		// contract.setMdmId("315000554");
		// contract.setMdmLevel("Domestic");
		// contract.setSessionHandle(crmSessionHandle);

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmId("1-NH1BWR");
		contract.setMdmLevel("Siebel");
		contract.setSessionHandle(crmSessionHandle);

		// AssetListContract contract = new AssetListContract();
		// contract.setMdmId("66309");
		// contract.setMdmLevel("Legal");
		// contract.setSessionHandle(crmSessionHandle);

		contract.setContactId("1-5HR5CYV");
		contract.setEntitlementEndDate("10/27/2012 05:30:00");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

		// System.out.println(result);

		System.out.println((System.currentTimeMillis() - t) / 1000.0);
	}

	@Test
	public void testRetrieveDeviceListFailed() {
		AssetListContract contract = new AssetListContract();

		contract.setLocale(new Locale("en_US"));

		// contract.setMdmLevel("Global");
		// contract.setMdmId("315000554");
		// contract.setContactId("1-5HR5CYV");

		// contract.setMdmLevel("Legal");
		// contract.setMdmId("59098");
		// contract.setContactId("1-6ER9WJJ");

		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NH1BWR");
		contract.setContactId("1-50YEZZD");

		// contract.setMdmLevel("Siebel");
		// contract.setMdmId("1-YFNVUX");
		// contract.setContactId("1-5HR5CYV");

		contract.setFavoriteFlag(false);
		// contract.setFavoriteFlag(true);

		contract.setEntitlementEndDate("10/27/2012 05:30:00");
		contract.setLoadAllFlag(false);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveDeviceList(contract);
		// for (Asset a : LangUtil.notNull(result.getAssets())) {
		// System.out.println(str(a));
		// }
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}

	@Test
	public void testRetrieveDevicesMultipleSessions() throws Exception {
		// final AssetListContract contract = new AssetListContract();
		// contract.setLocale(new Locale("en_US"));
		// contract.setMdmLevel("Siebel");
		// contract.setMdmId("1-YFNVUX");
		// contract.setContactId("1-5HR5CYV");
		// contract.setEntitlementEndDate("06/28/2013");
		// contract.setIncrement(100);
		// contract.setStartRecordNumber(0);
		// contract.setNewQueryIndicator(true);
		// contract.setSessionHandle(crmSessionHandle);

		final AssetListContract contract2 = new AssetListContract();
		contract2.setLocale(new Locale("en_US"));
		contract2.setMdmLevel("Siebel");
		contract2.setMdmId("1-YFNVUX");
		contract2.setContactId("1-5HR5CYV");
		contract2.setEntitlementEndDate("06/28/2013");
		contract2.setIncrement(100);
		contract2.setStartRecordNumber(0);
		contract2.setNewQueryIndicator(true);
		contract2.setSessionHandle(crmSessionHandle);

		ExecutorService executor = Executors.newScheduledThreadPool(1);

		List<Callable<AssetListResult>> callables = new ArrayList<Callable<AssetListResult>>();

		// callables.add(new Callable<AssetListResult>() {
		//
		// @Override
		// public AssetListResult call() throws Exception {
		//
		// return service.retrieveAllDeviceList(contract);
		//
		// return new AssetListResult();
		// }
		// });

		callables.add(new Callable<AssetListResult>() {

			@Override
			public AssetListResult call() throws Exception {

				// Thread.sleep(2000);

				return service.retrieveDeviceList(contract2);
			}
		});

		List<Future<AssetListResult>> futures = executor.invokeAll(callables);

		executor.shutdown();

		AssetListResult allResult = null;
		AssetListResult detailResult = null;

		try {
			allResult = futures.get(0).get();
		} catch (Exception ex) {
			System.out.println("EXCEPTION : " + ex);
		}

		// try {
		// detailResult = futures.get(1).get();
		// }
		// catch (Exception ex) {
		// System.out.println("EXCEPTION : " + ex);
		// }

		// AssetListResult allResult = service.retrieveAllDeviceList(contract);;
		// AssetListResult detailResult =
		// service.retrieveDeviceList(contract2);;

		System.out.println("-----------ALL---------------");
		if (allResult != null) {
			System.out.println(allResult.getTotalCount());
			System.out.println(allResult.getAssets().size());
		}

		// System.out.println("-----------DETAIL---------------");
		// if(detailResult!=null) {
		// System.out.println(detailResult.getTotalCount());
		// System.out.println(detailResult.getAssets().size());
		// }
		//
		System.out.println("END");
	}

	@Test
	public void testRetrieveConsumableAssetList() {

		AssetListContract contract = new AssetListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AssetListResult result = service.retrieveConsumableAssetList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

	}

	@Test
	public void testRetrieveAllDeviceList2() throws Exception {
		// AssetListContract contract = new AssetListContract();
		// contract.setLocale(new Locale("en_US"));
		// contract.setMdmLevel("Global");
		// contract.setMdmId("315000554");
		// contract.setContactId("1-5HR5CYV");
		// contract.setFavoriteFlag(false);
		// contract.setEntitlementEndDate("10/27/2012 05:30:00");
		// contract.setLoadAllFlag(false);
		// // contract.setSortCriteria((Map<String, Object>)
		// MiscTest.newHashMap(
		// // "serialNumber", "ASCENDING"));
		// contract.setIncrement(40);
		// contract.setStartRecordNumber(0);
		// contract.setNewQueryIndicator(true);
		// AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		// handle.setSessionFactory(new StatefulSessionFactory());
		// contract.setSessionHandle(handle);

		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setContactId("1-6AH086J");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("09/11/2013");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"productLine", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveAllDeviceList_defect8763() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));

		// contract.setMdmLevel("Legal");
		// contract.setMdmId("59098");
		// contract.setContactId("1-6ER9WJJ");

		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setContactId("1-5HR5CYV");

		// contract.setMdmLevel("Global");
		// contract.setMdmId("315000554");
		// contract.setContactId("1-5HR5CYV");

		// contract.setFavoriteFlag(true);
		contract.setFavoriteFlag(false);

		contract.setEntitlementEndDate("09/11/2013");
		contract.setLoadAllFlag(false);
		// contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
		// "serialNumber", "ASCENDING"));
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"installAddress.state", "DESCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);

		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getTotalCount());

		for (Asset asset : result.getAssets()) {
			if (asset.getInstallAddress() != null) {
				System.out.println("Office number: "
						+ asset.getInstallAddress().getOfficeNumber());
				System.out.println("District: "
						+ asset.getInstallAddress().getDistrict());
				System.out.println("County: "
						+ asset.getInstallAddress().getCounty());
			}
		}
	}

	@Test
	public void testRetrieveAllDeviceList_defect8837() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("59098");
		contract.setContactId("1-6ER9WJJ");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("09/12/2013");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(-1);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		List<Asset> assets = result.getAssets();

		// for (Asset asset : assets) {
		// System.out.println("Asset id: " + asset.getAssetId());
		// }

		for (Asset asset : assets) {
			if (asset.getInstallAddress() != null) {
				System.out.println("Office number: "
						+ asset.getInstallAddress().getOfficeNumber());
				System.out.println("District: "
						+ asset.getInstallAddress().getDistrict());
				System.out.println("County: "
						+ asset.getInstallAddress().getCounty());
			}
		}

		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveAllDeviceList_defect8972() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Global");
		contract.setMdmId("014578892");
		contract.setContactId("1-6DUBPFL");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("09/19/2013");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		List<Asset> assets = result.getAssets();

		// for (Asset asset : assets) {
		// System.out.println("Product line: " + asset.getProductLine());
		// }

		for (Asset asset : assets) {
			if (asset.getInstallAddress() != null) {
				System.out.println("Office number: "
						+ asset.getInstallAddress().getOfficeNumber());
				System.out.println("District: "
						+ asset.getInstallAddress().getDistrict());
				System.out.println("County: "
						+ asset.getInstallAddress().getCounty());
			}
		}

		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveDeviceList_defect9286() {
		AssetListContract contract = new AssetListContract();

		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Account");
		contract.setMdmId("39696");
		contract.setContactId("1-6LCG7TZ");
		// contract.setChlNodeId("1-1S402KV");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/08/2013");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}

	@Test
	public void testRetrieveDeviceList_IssueInAssetSearch() {
		AssetListContract contract = new AssetListContract();

		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setContactId("1-6AH086J");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/08/2013");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "DESCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serialNumber", "75250094600Z");
		contract.setFilterCriteria(filterMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}

	@Test
	public void testRetrieveAllDeviceList_defect9438() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-16UQFAG");
		contract.setMdmLevel("Global");
		contract.setMdmId("374610558");
		contract.setFavoriteFlag(true);
		contract.setEntitlementEndDate("10/15/2013");
		contract.setLoadAllFlag(false);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setSessionHandle(crmSessionHandle);

		// AssetListResult result = service.retrieveAllDeviceList(contract);

		AssetListResult result = service.retrieveDeviceList(contract);

		List<Asset> assets = result.getAssets();

		System.out.println("Size: " + assets.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (Asset asset : assets) {
			if (asset.getAccount() != null) {
				System.out.println("Account name: "
						+ asset.getAccount().getAccountName());
				System.out.println("Account id: "
						+ asset.getAccount().getAccountId());
			}
		}

		// for (Asset asset : assets) {
		// if(asset.getInstallAddress()!=null) {
		// System.out.println("Office number: " +
		// asset.getInstallAddress().getOfficeNumber());
		// System.out.println("District: " +
		// asset.getInstallAddress().getDistrict());
		// System.out.println("County: " +
		// asset.getInstallAddress().getCounty());
		// }
		// }

	}

	@Test
	public void testRetrieveDeviceDetail_defect9450() {
		AssetContract contract = new AssetContract();
		contract.setSerialNumber("7949D6N");
		contract.setContactId("1-5298M2X");
		contract.setAssetId("1-1VC0WX8");
		contract.setMdmLevel("Global");
		contract.setMdmId("883862203");

		AssetResult result = service.retrieveDeviceDetail(contract);

		System.out.println("Agreement Id: "
				+ result.getAsset().getAgreementId());

		if (result.getAsset().getDeviceContact() != null) {
			System.out.println(result.getAsset().getDeviceContact().size());

			List<AccountContact> contacts = result.getAsset()
					.getDeviceContact();

			for (AccountContact accountContact : contacts) {
				System.out.println("First name: "
						+ accountContact.getFirstName());
				System.out
						.println("Last name: " + accountContact.getLastName());
				System.out.println("Work phone: "
						+ accountContact.getWorkPhone());
				System.out.println("Email address: "
						+ accountContact.getEmailAddress());
				System.out.println("Home phone: "
						+ accountContact.getHomePhone());
			}
		}

		// System.out.println(result.getAsset().getPageCounts().size());
		//
		// List<PageCounts> pageCounts = result.getAsset().getPageCounts();
		//
		// for (PageCounts pageCounts2 : pageCounts) {
		// System.out.println(pageCounts2.getName());
		// }

	}

	@Test
	public void testRetrieveDeviceList_defect9159() {
		AssetListContract contract = new AssetListContract();

		contract.setMdmLevel("Global");
		contract.setMdmId("205529410");
		contract.setContactId("1-50WCH05");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/16/2013");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		for (Asset asset : result.getAssets()) {
			System.out.println("Is favorite: " + asset.getUserFavoriteFlag());
		}
		System.out.println("Total: " + result.getTotalCount());
    }
    

    @Test
    public void testRetrieveAllDeviceList_defect10103() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Siebel");
    	contract.setMdmId("1-1HL5NJ5");
    	contract.setContactId("1-6DV03JT");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("11/26/2013");
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"installAddress.officeNumber", "ASCENDING"));
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
//    		System.out.println("City: " + asset.getInstallAddress().getCity());
//    		System.out.println("District: " + asset.getInstallAddress().getDistrict());
    		System.out.println("OfficeNumber: " + asset.getInstallAddress().getOfficeNumber());
    	}
//    	for (Asset asset : result.getAssets()) {
//			System.out.println("AccountName: " + asset.getAccount().getAccountName());
//		}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect10421() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Global");
    	contract.setMdmId("354001547");
    	contract.setContactId("1-7AQXQDL");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("11/26/2013");
    	contract.setLoadAllFlag(false);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"serialNumber", "ASCENDING"));
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect10625() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Siebel");
    	contract.setMdmId("1-S6L1PA");
    	contract.setContactId("1-783BNJ5");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("12/12/2013");
    	contract.setLoadAllFlag(false);
    	contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"serialNumber", "ASCENDING"));
    	contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "7463369903KB8"));
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect11259() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Global");
    	contract.setMdmId("617608104");
    	contract.setContactId("1-7XEPWTV");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("01/21/2014");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect11581() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("7924");
    	contract.setContactId("1-85S6RQV");
    	contract.setChlNodeId("1-1COB0VX");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("02/05/2014");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("productLine", "DESCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(40);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect11020() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("110899");
    	contract.setContactId("1-7JKV5W7");
    	contract.setChlNodeId("1-5P82UWD");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("01/28/2014");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect12618() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Domestic");
    	contract.setMdmId("428201529");
    	contract.setContactId("1-7WTFIA5");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("04/02/2014");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveAllDeviceList_defect12618_MADC() throws Exception {
    	AssetListContract contract = new AssetListContract();
    	contract.setLocale(new Locale("en_US"));
    	contract.setMdmLevel("Siebel");
    	contract.setMdmId("1-1CZ6ASB");
    	contract.setContactId("1-7WTFIA5");
    	contract.setFavoriteFlag(false);
    	contract.setEntitlementEndDate("04/02/2014");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serialNumber", "ASCENDING");
    	contract.setSortCriteria(sortCriteria );
    	contract.setIncrement(40);
    	contract.setStartRecordNumber(0);
    	contract.setNewQueryIndicator(true);
    	AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
    	handle.setSessionFactory(new StatefulSessionFactory());
    	contract.setSessionHandle(handle);
    	
    	AssetListResult result = service.retrieveAllDeviceList(contract);
    	for (Asset asset : result.getAssets()) {
    		System.out.println("Serial Number: " + asset.getSerialNumber());
    	}
    	System.out.println("Total: " + result.getTotalCount());
    }

    
    @Test
    public void testRetrieveDeviceDetail_MPS_QA_defect7790() {
     AssetContract contract = new AssetContract();
     contract.setAssetId("1-5WD55E3");
     AssetResult result = service.retrieveDeviceDetail(contract);
     print(result);
    }
    
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect9130() {
     AssetContract contract = new AssetContract();
     contract.setAssetId("1-AMBL-16581");
     contract.setSerialNumber("793P6K3");
     contract.setMdmLevel("Global");
     contract.setMdmId("883862203");
     contract.setContactId("1-5298M2X");
     
     AssetResult result = service.retrieveDeviceDetail(contract);
     for (PageCounts aset : result.getAsset().getPageCounts()) {
		System.out.println("Count: " + aset.getCount());
		System.out.println("Date: " + aset.getDate());
		System.out.println("Name: " + aset.getName());
		System.out.println("---------------------------");
	}
     print(result);
    }

    
    @Test
    public void testRetrieveDeviceDetail_QA_defect9970() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-16660714991");
    	
    	AssetResult result = service.retrieveDeviceDetail(contract);
//    	for (PageCounts aset : result.getAsset().getPageCounts()) {
//    		System.out.println("Count: " + aset.getCount());
//    		System.out.println("Date: " + aset.getDate());
//    		System.out.println("Name: " + aset.getName());
//    		System.out.println("---------------------------");
//    	}
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10856_1() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-7DLP7PU");
    	
    	AssetResult result = service.retrieveDeviceDetail(contract);
//    	for (PageCounts aset : result.getAsset().getPageCounts()) {
//    		System.out.println("Count: " + aset.getCount());
//    		System.out.println("Date: " + aset.getDate());
//    		System.out.println("Name: " + aset.getName());
//    		System.out.println("---------------------------");
//    	}
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10897() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-78R0DTR");
    	
    	AssetResult result = service.retrieveDeviceDetail(contract);
//    	for (PageCounts aset : result.getAsset().getPageCounts()) {
//    		System.out.println("Count: " + aset.getCount());
//    		System.out.println("Date: " + aset.getDate());
//    		System.out.println("Name: " + aset.getName());
//    		System.out.println("---------------------------");
//    	}
    	print(result);
    }
    
   @Test
    public void testRetrieveDeviceDetail_QA_defect11002() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-6W6F-17145");
    	contract.setCurrentDate(new java.util.Date());
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (PageCounts pageCount : result.getAsset().getPageCounts()) {
			logger.debug(pageCount.getName());
		}
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10966_detail() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-7GET0M0");
    	contract.setCurrentDate(new java.util.Date());
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (Part parts : result.getAsset().getPartList()) {
    		for (String paymentType : parts.getPaymentTypes()) {
				logger.debug("PaymentType: " + paymentType.toString());
			}
    	}
    	print(result);
    }

 @Test
    public void testRetrieveDeviceDetail_QA_defect11061() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-CCSD-4170");
    	contract.setCurrentDate(new java.util.Date());
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (Part parts : result.getAsset().getPartList()) {
    		logger.debug(parts.getPartNumber());
    	}
    	print(result);
    }


	@Test
	public void testRetrieveAllDeviceList_defect11706() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Global");
		contract.setMdmId("205529410");
		contract.setContactId("1-783BNS1");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("02/13/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"assetTag", "ASCENDING"));
		contract.setIncrement(-1);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		contract.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap(
				"productLine", "C782dtn"));

		// contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
		// "productLine", "C782dtn"));

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

		List<Asset> assets = result.getAssets();

		int prodLine = 0;
		int notProdLine = 0;
		for (Asset asset : assets) {
			if (asset.getProductLine().equalsIgnoreCase("C782dtn")) {
				System.out.println("Product line: " + asset.getProductLine());
				prodLine++;
			} else {
				notProdLine++;
			}
		}

		System.out.println("Prod line: " + prodLine);
		System.out.println("Not prod line: " + notProdLine);

	}

	@Test
	public void testRetrieveDeviceList_defect11431() {

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-783R8IZ");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/14/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveAllDeviceList_Defect11710() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-783R8IZ");
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("02/12/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"assetTag", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		contract.setFavoriteFlag(true);
		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println("Size: " + result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveAllDeviceList_Defect11581() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();

		// list.add(executor.submit(new Runnable() {
		//
		// @Override
		// public void run() {
		// AssetListContract contract = new AssetListContract();
		// contract.setLocale(new Locale("en_US"));
		// contract.setContactId("1-85S6RQV");
		// contract.setMdmLevel("Legal");
		// contract.setMdmId("7924");
		// contract.setChlNodeId("1-1COB0VX");
		// contract.setFavoriteFlag(false);
		// contract.setEntitlementEndDate("02/05/2014");
		// contract.setLoadAllFlag(false);
		// contract.setSortCriteria((Map<String, Object>) MiscTest
		// .newHashMap("productLine", "ASCENDING"));
		// contract.setIncrement(40);
		// contract.setStartRecordNumber(0);
		// contract.setNewQueryIndicator(true);
		// contract.setSessionHandle(crmSessionHandle);
		//
		// AssetListResult result = service
		// .retrieveAllDeviceList(contract);
		//
		// System.out.println("Size: " + result.getAssets().size());
		// System.out.println("Total count: " + result.getTotalCount());
		// }
		// }));

		list.add(executor.submit(new Runnable() {

			@Override
			public void run() {

				// try {
				// Thread.sleep(20000);
				// }catch(Exception ex){}

				AssetListContract contract = new AssetListContract();
				contract.setLocale(new Locale("en_US"));
				contract.setContactId("1-85S6RQV");
				contract.setMdmLevel("Legal");
				contract.setMdmId("7924");
				contract.setChlNodeId("1-1COB0VX");
				contract.setFavoriteFlag(false);
				contract.setEntitlementEndDate("02/05/2014");
				contract.setLoadAllFlag(false);
				contract.setSortCriteria((Map<String, Object>) MiscTest
						.newHashMap("productLine", "ASCENDING"));
				contract.setIncrement(40);
				contract.setStartRecordNumber(80);
				contract.setNewQueryIndicator(true);
				contract.setSessionHandle(crmSessionHandle);

				AssetListResult result = service
						.retrieveAllDeviceList(contract);

				System.out.println("Size: " + result.getAssets().size());
				System.out.println("Total count: " + result.getTotalCount());
			}
		}));

		for (Future<?> future : list) {
			future.get();
		}

		executor.shutdown();

		System.out.println("END");

	}

	@Test
	public void testRetrieveDeviceList_defect11712() {

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-MCUMCB");
		contract.setContactId("1-8AXZU73");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/12/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrievePageCounts_defect11487() throws Exception {
		PageCountsContract contract = new PageCountsContract();
		contract.setAssetId("1-832GUBG");
		PageCountsResult result = service.retrievePageCounts(contract);
		
		for (PageCounts pageCounts : result.getPageCounts()) {
			System.out.println("Name: " + pageCounts.getName());
			System.out.println("Count: " + pageCounts.getCount());
			System.out.println("Date: " + pageCounts.getDate());
			System.out.println("---------------");
		}
	}
	
	@Test
	public void testRetrievePageCounts_defect19556() throws Exception {
		PageCountsContract contract = new PageCountsContract();
		contract.setAssetId("1-JSB2-2852");
		PageCountsResult result = service.retrievePageCounts(contract);

		for (PageCounts pageCounts : result.getPageCounts()) {
			System.out.println("Name: " + pageCounts.getName());
			System.out.println("Count: " + pageCounts.getCount());
			System.out.println("Date: " + pageCounts.getDate());
			System.out.println("---------------");
		}
	}

	@Test
	public void testRetrieveDeviceDetail_defect11835() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-JYYB-19");
		contract.setContactId("1-7AQXQA9");
		contract.setPageName("CmDeviceDetail");

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		List<AccountContact> contacts = asset.getDeviceContact();

		System.out.println("Size: " + contacts.size());

		for (AccountContact accountContact : contacts) {
			System.out.println("Name: " + accountContact.getFirstName() + " "
					+ accountContact.getLastName());
			System.out.println(accountContact.getDeviceContactType());
		}
	}

	@Test
	public void testRetrieveAssetLocations_AllCallsTest() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		AssetLocationsResult result = service.retrieveAssetLocations(contract);

		System.out.println("Size: " + result.getAddressList().size());

	}

	@Test
	public void testRetrieveAllDeviceList_AllCalls() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-73RMK3T");
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("installAddress.city", "Paris");
		filterCriteria.put("installAddress.country", "France");
		contract.setFilterCriteria(filterCriteria);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println("Size: " + result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveAssetLocations_AllCallsTest_2() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);
		contract.setEntitlementEndDate("02/19/2014");

		AssetLocationsResult result = service.retrieveAssetLocations(contract);

		System.out.println("Size: " + result.getAddressList().size());

	}

	@Test
	public void testRetrieveAllDeviceList_AllCalls2() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-73RMK3T");
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setFavoriteFlag(false);
		contract.setChlNodeId("1-1UG0HUE");
		contract.setEntitlementEndDate("02/17/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println("Size: " + result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

   @Test
    public void testRetrieveDeviceDetail_QA_defect10077() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-6NQ01PS");
    	
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (PageCounts aset : result.getAsset().getPageCounts()) {
    		System.out.println("Count: " + aset.getCount());
    		System.out.println("Date: " + aset.getDate());
    		System.out.println("Name: " + aset.getName());
    		System.out.println("---------------------------");
    	}
    	for (AccountContact contact : result.getAsset().getDeviceContact()) {
    		System.out.println("FirstName: " + contact.getFirstName());
    		System.out.println("LastName: " + contact.getLastName());
    		System.out.println("ContactId: " + contact.getContactId());
    		System.out.println("Email: " + contact.getEmailAddress());
    		System.out.println("WorkPhone: " + contact.getWorkPhone());
    		System.out.println("AlternatePhone: " + contact.getAlternatePhone());
    		System.out.println("---------------------------");
    	}
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10318() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-8JA5-1696");
    	
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (PageCounts aset : result.getAsset().getPageCounts()) {
    		System.out.println("Count: " + aset.getCount());
    		System.out.println("Date: " + aset.getDate());
    		System.out.println("Name: " + aset.getName());
    		System.out.println("---------------------------");
    	}
    	for (AccountContact contact : result.getAsset().getDeviceContact()) {
    		System.out.println("FirstName: " + contact.getFirstName());
    		System.out.println("LastName: " + contact.getLastName());
    		System.out.println("ContactId: " + contact.getContactId());
    		System.out.println("Email: " + contact.getEmailAddress());
    		System.out.println("WorkPhone: " + contact.getWorkPhone());
    		System.out.println("AlternatePhone: " + contact.getAlternatePhone());
    		System.out.println("---------------------------");
    	}
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10342() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-5H9U6M0");
    	contract.setPageName("DeviceDetail");
    	contract.setContactId("1-7ENDH4D");
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	
    	System.out.println("Primary: " + result.getAsset().getAssetContact().getFirstName() + " " + result.getAsset().getAssetContact().getLastName());
    	for (AccountContact contact : result.getAsset().getDeviceContact()) {
			System.out.println("Secondary: " + contact.getFirstName() + " " + contact.getLastName());
		}
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10342_test() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-5H9U6M0");
    	contract.setPageName("ConsumableDeviceDetail");
    	contract.setCurrentDate(new java.util.Date());
    	contract.setEffectiveDate(new java.util.Date());
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	print(result);
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10551() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-VLR-13402");
    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (PageCounts page : result.getAsset().getPageCounts()) {
			logger.debug(page.getName());
		}
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10562() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-VLR-13402");
    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (PageCounts page : result.getAsset().getPageCounts()) {
    		logger.debug(page.getName());
    	}
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10861() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-RBFJEW");
//    	contract.setAssetId("1-OGNK-22");
    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10612() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-BHYJ-942");
    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect11027() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-672J-2290");
    	contract.setContactId("1-5395GPD");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect11173() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-BCTX-13469");
    	contract.setPageName("123");
//    	contract.setAssetId("1-5AV6-786");
//    	contract.setContactId("1-5395GPD");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect12232() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-863DIEO");
    	contract.setPageName("CmDeviceDetail");
    	contract.setContactId("1-783BNIB");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug("ChlNodeValue: " + result.getAsset().getChlNodeValue());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect11117() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-VLR-13402");
    	contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/01/2014 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/01/2014 17:0:55"));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	for (Part parts : result.getAsset().getPartList()) {
			logger.debug("VendorPartNumber: " + parts.getVendorPartNumber());
		}
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_noParts() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-7U7ID6P");
    	contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("02/26/2014 00:00:00"));
    	Date effectiveDate = new Date();
		contract.setEffectiveDate(effectiveDate );
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-5H9U6M0");
    	contract.setPageName("DeviceDetail");
    	contract.setContactId("1-7ENDH4D");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_QA_defect10869() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-MSK6-112");
    	contract.setSerialNumber("40632699043YN");
    	contract.setMdmId("110899");
    	contract.setMdmLevel("Legal");
    	contract.setContactId("1-783BNIL");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_propSet() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-5H9U6M0");
    	contract.setContactId("1-73RMK3T");
    	contract.setPageName("mDeviceDetail");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
    @Test
    public void testRetrieveDeviceDetail_defect16389() {
    	AssetContract contract = new AssetContract();
    	contract.setAssetId("1-N6ZC-137");
    	contract.setContactId("1-LMPR6YL");
    	contract.setPageName("CmDeviceDetail");
//    	contract.setCurrentDate(new java.util.Date(-1));
    	AssetResult result = service.retrieveDeviceDetail(contract);
    	logger.debug(result.getAsset().getAssetId());
    }
    
	@Test
	public void testRetrieveAssetLocations_AllCallsTest_30() {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setVendorFlag(false);
		contract.setEntitlementEndDate("02/24/2014");
		contract.setIncrement(0);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(false);

		AssetLocationsResult result = service.retrieveAssetLocations(contract);

		System.out.println("Size: " + result.getAddressList().size());

	}

	@Test
	public void testRetrieveDeviceList_defect30() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setFavoriteFlag(false);
		contract.setContactId("1-73RMK3T");
		contract.setChlNodeId("1-1UG0HUE");
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

//		Map<String, Object> sortMap = new HashMap<String, Object>();
//		sortMap.put("serialNumber", "ASCENDING");
//		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		List<Asset> assets = result.getAssets();

		System.out.println("totalCount = " + result.getTotalCount());
		System.out.println("assets count = " + assets.size());

	}

	@Test
	public void testRetrieveDeviceDetail_31() throws Exception {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-7YN2DFR");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil
				.convertStringToGMTDate("02/17/2014 00:00:00"));
		contract.setEffectiveDate(new Date());
		contract.setSessionHandle(crmSessionHandle);

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		System.out.println(asset == null ? "null" : ("Asset id: " + asset
				.getAssetId()));
	}

	@Test
	public void testRetrieveDeviceDetail_37() throws Exception {

		long t = System.currentTimeMillis();

		AssetContract contract = new AssetContract();
		contract.setAssetId("1-7YQTPEI");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil
				.convertStringToGMTDate("02/17/2014 00:00:00"));
		contract.setEffectiveDate(new Date());
		contract.setSessionHandle(crmSessionHandle);

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		System.out.println(asset == null);

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);

	}

	@Test
	public void testRetrieveDeviceList_defect11464() {

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("55838");
		contract.setContactId("1-85SLJIR");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/25/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveDeviceDetail_QA_Defect10631() {
		AssetContract contract = new AssetContract();
		contract.setContactId("1-7CXV7CT");
		contract.setAssetId("1-MFLE-24");
		contract.setPageName("CmDeviceDetail");
		contract.getSerialversionuid();
		contract.setSessionHandle(crmSessionHandle);
		AssetResult result = service.retrieveDeviceDetail(contract);
		System.out.println(">>result is "
				+ result.getAsset().getInstallAddress().getAddressId());
		for (AccountContact ac : result.getAsset().getDeviceContact()) {
			System.out.println(">>result is CONTACT ID " + ac.getContactId());
			System.out.println(">>result is ADDRESS ID "
					+ ac.getAddress().getAddressId());

		}

	}

	@Test
	public void testRetrieveDeviceList_defect12078() {

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-ORSN8P");
		contract.setContactId("1-8OBGLVF");
		contract.setFavoriteFlag(true);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/27/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveDeviceDetail_defect9952() throws Exception {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-8SBK-8564");
		contract.setCurrentDate(LangUtil
				.convertStringToGMTDate("28/02/2014 00:00:00"));
		contract.setPageName("ConsumableDeviceDetail");
		contract.setEffectiveDate(new Date());

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		System.out.println(asset.getPartList() == null ? "Null" : asset
				.getPartList().size());

		for (Part part : asset.getPartList()) {
			System.out.println(part.getPartNumber());
		}

	}
	
	
	@Test
	public void testRetrieveDeviceList_29() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("50801");
		contract.setFavoriteFlag(false);
		contract.setContactId("1-73RMK3T");
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/17/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);


		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("installAddress.country", "France");
		contract.setFilterCriteria(filterMap);
		
		AssetListResult result = service.retrieveDeviceList(contract);

		List<Asset> assets = result.getAssets();

		System.out.println("totalCount = " + result.getTotalCount());
		System.out.println("assets count = " + assets.size());

	}
	
	
	@Test
	public void testRetrieveAllDeviceList_10122() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Legal");
		contract.setMdmId("61630");
		contract.setContactId("1-7ZGWPJH");
		contract.setFavoriteFlag(false);
		contract.setChlNodeId("1-49L2DC1");
		contract.setEntitlementEndDate("02/28/2014");
		contract.setLoadAllFlag(false);
		
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		
		contract.setSessionHandle(crmSessionHandle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		
		System.out.println("Size: " + result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("END");
	}

	@Test
	public void testRetrieveDeviceList_defect12188() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("110899");
		contract.setFavoriteFlag(false);
		contract.setContactId("1-783BNIL");
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("03/04/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		List<Asset> assets = result.getAssets();

		System.out.println("totalCount = " + result.getTotalCount());
		System.out.println("assets count = " + assets.size());

	}
	
	
	@Test
	public void testRetrieveDeviceList_defect12203() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("34508");
		contract.setFavoriteFlag(false);
		contract.setContactId("1-7WU2YNX");
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("03/04/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		List<Asset> assets = result.getAssets();

		System.out.println("totalCount = " + result.getTotalCount());
		System.out.println("assets count = " + assets.size());

	}
	
	
	@Test
	public void testRetrieveDeviceDetail_defect12232() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-863DIEO");
		contract.setContactId("1-783BNIB");
		contract.setPageName("CmDeviceDetail");

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		System.out.println("Chl node value: " + asset.getChlNodeValue());
		System.out.println("Chl node id: " + asset.getChlNodeId());
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect16676() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-N5GB9I1");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("02/10/2015 00:00:00"));
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("02/11/2015 19:38:57"));
		
		AssetResult result = service.retrieveDeviceDetail(contract);
		System.out.println(result.getAsset());
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect16676_2() {
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-N5EU7WM");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("02/10/2015 06:54:45"));
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("02/17/2015 00:00:00"));
		
		AssetResult result = service.retrieveDeviceDetail(contract);
		System.out.println(result.getAsset());
	}
	
	
	@Test
	public void testRetrieveAllDeviceList_Defect10479() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-7E1VKC1");
		contract.setMdmLevel("Legal");
		contract.setMdmId("16442");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("03/14/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumbe", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String,Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serialNumber", "7944WLR");
		contract.setFilterCriteria(filterCriteria);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println("Size: " + result.getAssets().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveDeviceList_defect11431_2() {

		long t = System.currentTimeMillis();

		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-783R8IZ");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("03/12/2014");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}
	
	@Test
	public void testRetrieveAllDeviceList_defect12617() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Legal");
		contract.setMdmId("34514");
		contract.setContactId("1-7WTFIAP");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("04/02/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

	}
	
	@Test
	public void testRetrieveAllDeviceList_defect12619() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Account");
		contract.setMdmId("40392");
		contract.setContactId("1-7PFEAQR");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("04/02/2014");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveAllDeviceList(contract);

		System.out.println(result.getAssets().size());
		System.out.println(result.getTotalCount());

	}
	
	@Test
	public void testRetrieveDeviceDetail_defect12693(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-8QU3S56");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/11/2014 00:00:00"));
		contract.setPageName("ConsumableDeviceDetail");
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/11/2014 11:28:31"));

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		List<AccountContact> contacts = asset.getDeviceContact();

		System.out.println("Size: " + (contacts == null ? "null" : contacts.size()));

	}
	
	@Test
	   public void testRetrieveAllDeviceList_Test_For_Karpagam() throws Exception {
	    AssetListContract contract = new AssetListContract();
	    AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
	    handle.setSessionFactory(new StatefulSessionFactory());
	    contract.setSessionHandle(handle);
	    contract.setLocale(new Locale("en_US"));
	    contract.setMdmLevel("Global");
	    contract.setMdmId("315000554");
	    contract.setContactId("1-783R8IZ");
	    contract.setFavoriteFlag(false);
	    contract.setEntitlementEndDate("07/24/2014 05:30:00");
	    contract.setLoadAllFlag(false);
	     contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
	    contract.setIncrement(40);
	    contract.setStartRecordNumber(0);
	    contract.setNewQueryIndicator(true);

	    AssetListResult result = service.retrieveAllDeviceList(contract);
	    System.out.println(result.getTotalCount());
	    logger.info("loggerInfo");
	   }
	
	@Test
	public void testRetrieveAllDeviceList_defect16362() throws Exception {
		AssetListContract contract = new AssetListContract();
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-BS96OPR");
		contract.setFavoriteFlag(false);
		contract.setEntitlementEndDate("03/02/2015");
		contract.setLoadAllFlag(false);
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
		contract.setIncrement(-1);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("installAddress.country", "german");
		contract.setFilterCriteria(filterCriteria );
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getTotalCount());
		logger.info("loggerInfo");
	}
	
	
	
	@Test
	public void testRetrieveDeviceDetail_LBS_Address_Flag(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-5A53-270");
		contract.setContactId("1-EH1HBLZ");
		contract.setPageName("CmDeviceDetail");

		AssetResult result = service.retrieveDeviceDetail(contract);

		Asset asset = result.getAsset();

		List<AccountContact> contacts = asset.getDeviceContact();

		System.out.println("Size: " + (contacts == null ? "null" : contacts.size()));

	}

	@Test
	public void testRetrieveAllDeviceList_CHL() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Legal");
		contract.setMdmId("5166");
		contract.setContactId("1-EH1DIZ3");
		contract.setChlNodeId("1-2HGB0O1");
		contract.setEntitlementEndDate("01/07/2015");
		 contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
		 "serialNumber", "ASCENDING"));
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
	    AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
	    handle.setSessionFactory(new StatefulSessionFactory());
	    contract.setSessionHandle(handle);

		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_defect17720() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-NMDWLJL");
		contract.setMdmLevel("Global");
		contract.setMdmId("623331717");
		contract.setEntitlementEndDate("05/21/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_INC0112362() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-8NGPUBX");
		contract.setMdmLevel("Global");
		contract.setMdmId("219469751");
		contract.setEntitlementEndDate("06/02/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.city", "Luxemburg");
		filterCriteria.put("installAddress.country", "Luxembourg");
		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_PropertySet() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-1LXLKQR");
		contract.setMdmLevel("Global");
		contract.setMdmId("006415160");
		contract.setEntitlementEndDate("07/08/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.city", "Luxemburg");
//		filterCriteria.put("installAddress.country", "Luxembourg");
		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_CHG0004184() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-8NGPUBX");
		contract.setMdmLevel("Global");
		contract.setMdmId("219469751");
		contract.setEntitlementEndDate("09/01/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("installAddress.city", "Luxemburg");
//		filterCriteria.put("installAddress.country", "Luxembourg");
		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_INC0141420() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-3LEPYGX");
		contract.setMdmLevel("Global");
		contract.setMdmId("487201915");
		contract.setChlNodeId("1-14GEUTO5");
		contract.setEntitlementEndDate("09/03/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.city", "Luxemburg");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_INC0141420_2() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-3LEPYGX");
		contract.setMdmLevel("Global");
		contract.setMdmId("487201915");
		contract.setChlNodeId("1-18FQKH2");
		contract.setEntitlementEndDate("09/03/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.city", "Luxemburg");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
		for (Asset asset : result.getAssets()) {
			System.out.println("Serial Number: " + asset.getSerialNumber());
		}
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_LOD() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-16X93QX");
		contract.setMdmLevel("Global");
		contract.setMdmId("623331717");
//		contract.setChlNodeId("1-18FQKH2");
		contract.setEntitlementEndDate("09/08/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("installAddress.levelOfDetails", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.levelOfDetails", "xxxxxxxxxxxxxxxxxx");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		AssetListResult result = service.retrieveAllDeviceList(contract);
//		for (Asset asset : result.getAssets()) {
//			System.out.println("LOD: " + asset.getInstallAddress().getLevelOfDetails());
//		}
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_PerformanceTest_DeviceFinderTab() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-1DVZUXX");
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
//		contract.setChlNodeId("1-18FQKH2");
		contract.setEntitlementEndDate("10/26/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.levelOfDetails", "xxxxxxxxxxxxxxxxxx");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("Exec time: " + (System.currentTimeMillis() - t)	/ 1000.0);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_PerformanceTest_DeviceFinderTab2() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-1DVZUXX");
		contract.setMdmLevel("Global");
		contract.setMdmId("051957769");
//		contract.setChlNodeId("1-18FQKH2");
		contract.setEntitlementEndDate("10/26/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.levelOfDetails", "xxxxxxxxxxxxxxxxxx");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("Exec time: " + (System.currentTimeMillis() - t)	/ 1000.0);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveAllDeviceList_PerformanceTest_DeviceFinderTab3() throws Exception {
		AssetListContract contract = new AssetListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-1DVZUXX");
		contract.setMdmLevel("Global");
		contract.setMdmId("006415160");
//		contract.setChlNodeId("1-18FQKH2");
		contract.setEntitlementEndDate("10/26/2015");
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("installAddress.levelOfDetails", "xxxxxxxxxxxxxxxxxx");
//		filterCriteria.put("installAddress.country", "Luxembourg");
//		contract.setFilterCriteria(filterCriteria );
//		contract.setLbsFilterCriteriaFlag("N");
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		AmindCrmSessionHandle handle = new AmindCrmSessionHandle();
		handle.setSessionFactory(new StatefulSessionFactory());
		contract.setSessionHandle(handle);
		
		long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveAllDeviceList(contract);
		System.out.println("Exec time: " + (System.currentTimeMillis() - t)	/ 1000.0);
		System.out.println("ListSize: " + result.getAssets().size());
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveDeviceList_performanceTest() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-BS96OPR");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/16/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		AssetListResult result = service.retrieveDeviceList(contract);

		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
    }
	
	@Test
	public void testRetrieveDeviceList_performanceTest2() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("051957769");
		contract.setContactId("1-BJ1QTMX");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("02/16/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		AssetListResult result = service.retrieveDeviceList(contract);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_defect17111() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("483419636");
		contract.setContactId("1-BGXLRJJ");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("03/07/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("descriptionLocalLang", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		AssetListResult result = service.retrieveDeviceList(contract);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_defect18229() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-BS96OPR");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("06/22/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		AssetListResult result = service.retrieveDeviceList(contract);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_defect18471() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("232112404");
		contract.setContactId("1-16X93QX");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("08/18/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		AssetListResult result = service.retrieveDeviceList(contract);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_performanceTest_ConsumableAssetGrid() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("006415160");
		contract.setContactId("1-1DVZUXX");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/26/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		Long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveDeviceList(contract);
		System.out.println("Time: " + (System.currentTimeMillis() - t) / 1000.0);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_performanceTest_ConsumableAssetGrid2() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setContactId("1-1DVZUXX");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/26/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		Long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveDeviceList(contract);
		System.out.println("Time: " + (System.currentTimeMillis() - t) / 1000.0);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceList_performanceTest_ConsumableAssetGrid3() {
		AssetListContract contract = new AssetListContract();
		contract.setMdmLevel("Global");
		contract.setMdmId("051957769");
		contract.setContactId("1-1DVZUXX");
		contract.setFavoriteFlag(false);
		contract.setLoadAllFlag(false);
		contract.setEntitlementEndDate("10/26/2015");
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serialNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);
		
		Long t = System.currentTimeMillis();
		AssetListResult result = service.retrieveDeviceList(contract);
		System.out.println("Time: " + (System.currentTimeMillis() - t) / 1000.0);
		
		System.out.println("totalCount=" + result.getTotalCount());
		System.out.println("assets count=" + result.getAssets().size());
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect16794(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-JX5V-180");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/2/2015 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/2/2015 20:12:19"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		for (Part part : result.getAsset().getPartList()) {
			System.out.println("Max qty: " + part.getMaxQuantity());
		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveDeviceDetail_MoveToAddressChanges(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-N6ZC-137");
		contract.setContactId("1-LMPR6YL");
		contract.setPageName("CmDeviceDetail");
//		contract.setCurrentDate(LangUtil.convertStringToGMTDate("02/17/2015 00:00:00"));
//		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("02/18/2015 19:59:03"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		List<AccountContact> contacts = asset.getDeviceContact();
		System.out.println("Size: " + (contacts == null ? "null" : contacts.size()));
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect12006(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-M7M51CF");
//		contract.setContactId("1-EH1HBLZ");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("03/02/2015 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("03/02/2015 16:36:36"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		List<AccountContact> contacts = asset.getDeviceContact();
		System.out.println("Size: " + (contacts == null ? "null" : contacts.size()));
	}
	
	@Test
	public void testRetrieveDeviceDetail_INC0083174(){
		AssetContract contract = new AssetContract();
//		contract.setAssetId("1-LN2QI47");
		contract.setAssetId("1-KMB5B2H");
		contract.setContactId("1-8NGPUBX");
		contract.setPageName("CmDeviceDetail");
//		contract.setCurrentDate(LangUtil.convertStringToGMTDate("03/02/2015 00:00:00"));
//		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("03/02/2015 16:36:36"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		for (PageCounts pageCount : asset.getPageCounts()) {
			System.out.println(pageCount.getName());
		}
		System.out.println("PageCount size: " + asset.getPageCounts().size());
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect17184(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-NMEOPVZ");
		contract.setContactId("1-NMDWLJL");
		contract.setPageName("CmDeviceDetail");
//		contract.setCurrentDate(LangUtil.convertStringToGMTDate("03/02/2015 00:00:00"));
//		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("03/02/2015 16:36:36"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println("Coordinate X: " + asset.getInstallAddress().getCoordinatesXPreDebriefRFV());
		System.out.println("Coordinate Y: " + asset.getInstallAddress().getCoordinatesYPreDebriefRFV());
	}
	
	@Test
	public void testRetrieveDeviceDetail_error_QA(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-2RHY-448");
//		contract.setContactId("1-NMDWLJL");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("05/19/2015 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("05/19/2015 14:16:52"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println();
	}
	
	@Test
	public void testRetrieveDeviceDetail_15_7Integration(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-347BZRS");
//		contract.setContactId("1-NMDWLJL");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/23/2015 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/23/2015 21:00:40"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println();
	}
	
	@Test
	public void testRetrieveDeviceDetail_defect17943(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-51BR-4145");
		contract.setContactId("1-BGXLRJJ");
		contract.setPageName("DeviceDetail");
//		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/23/2015 00:00:00"));
//		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/23/2015 21:00:40"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println("HostNmae: " + asset.getHostName());
		System.out.println("AssetTag: " + asset.getAssetTag());
	}
	
	@Test
	public void testRetrieveDeviceDetail_LBS1_5(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-NEBZNRV");
		contract.setContactId("1-MP8Y1F1");
		contract.setPageName("CmDeviceDetail");
//		contract.setCurrentDate(LangUtil.convertStringToGMTDate("04/23/2015 00:00:00"));
//		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("04/23/2015 21:00:40"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println("LevelOfDetails: " + asset.getInstallAddress().getLevelOfDetails());
	}
	
	@Test
	public void testRetrieveDeviceDetail_INC0136640(){
		AssetContract contract = new AssetContract();
		contract.setAssetId("1-GYIZ-10");
//		contract.setContactId("1-MP8Y1F1");
		contract.setPageName("ConsumableDeviceDetail");
		contract.setCurrentDate(LangUtil.convertStringToGMTDate("08/21/2015 00:00:00"));
		contract.setEffectiveDate(LangUtil.convertStringToGMTDate("08/21/2015 10:10:04"));
		AssetResult result = service.retrieveDeviceDetail(contract);
		Asset asset = result.getAsset();
		System.out.println("splitterFlag: " + asset.getAccount().isAccountSplitterFlag());
	}
	
}
