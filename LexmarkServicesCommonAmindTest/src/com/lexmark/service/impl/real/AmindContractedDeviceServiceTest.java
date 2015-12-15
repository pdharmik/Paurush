package com.lexmark.service.impl.real;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.CHLNode;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.impl.real.domain.AccountAsset;
import com.lexmark.service.impl.real.domain.AccountAssetDetailDo;
import com.lexmark.util.LangUtil;

/**
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 *
 * @author vpetruchok
 * @version 1.0, 2012-03-05
 */
public class AmindContractedDeviceServiceTest  extends AmindServiceTest {

    static AmindContractedDeviceService service;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        logger.info("in setUp()");
        service = new AmindContractedDeviceService();
        service.setSessionFactory(statelessSessionFactory);
    }

    /**
     * Originally copied from AccountAssetTest#testRetrieveAssetsPagination
     *
     * @throws Exception
     */
//    @Test
//    public void testRetrieveDeviceList() throws Exception {
//        String mdmId = "57408";
//        String mdmLevel = "Legal";
//        String contactId = "1-1SU00J3";// "1-1LUIPWR";
//        // String contactId ="1-1LUIPWR";
//        String chlNodeId = "";
//        String assetType = "ALL";
//        boolean favoriteFlag = false;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
//        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        Map<String, Object> sortCriteria = new HashMap<String, Object>();
//        sortCriteria.put("serialNumber", "ASCENDING");
//        assetContract.setSortCriteria(sortCriteria);
//
//        AssetListResult result = null;
//        String ns = "[retrieveDeviceList] ";
//        logger.info(ns
//                + String.format("Search criteria: mdmId=%s, mdmLevel=%s, contactId=%s", mdmId, mdmLevel, contactId));
//        long startTime = System.currentTimeMillis();
//        result = service.retrieveDeviceList(assetContract);
//        long endTime = System.currentTimeMillis();
//        logger.info(ns + String.format("elapsed time = %sms", (endTime - startTime)));
//        logAssetList(ns, result);
//    }


   /** Test for "Asset List.xml"
    *
    * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest
    * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest#queryBySearchExpressions_fromTestData()
    */
//    @Test
//    public void testRetrieveDeviceList_FromTestData() throws Exception {
//        String mdmId = "1-TY0FYQ";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL; // TODO(Viktor) what level do we need ?
////        String contactId = null;
//        String contactId ="No Match Row Id";
////        String contactId ="1-TY0FYQ";
//        String chlNodeId = "";
//        String assetType = "ALL";
////        boolean favoriteFlag = false;
//        boolean favoriteFlag = true;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
//        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        Map<String, Object> sortCriteria = new HashMap<String, Object>();
//        sortCriteria.put("serialNumber", "ASCENDING");
//        assetContract.setSortCriteria(sortCriteria);
//
//        AssetListResult result = null;
//        String ns = "[retrieveDeviceList] ";
//        logger.info(ns
//                + String.format("Search criteria: mdmId=%s, mdmLevel=%s, contactId=%s", mdmId, mdmLevel, contactId));
//        long startTime = System.currentTimeMillis();
//        result = service.retrieveDeviceList(assetContract);
//        long endTime = System.currentTimeMillis();
//        logger.info(ns + String.format("elapsed time = %sms", (endTime - startTime)));
//        logAssetList(ns, result);
//        assertTrue(result.getAssets().size() > 0);
//    }

    
    /** 
    * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest
    */
//    @Test
//    public void testRetrieveDeviceList_withData() throws Exception {
//        String mdmId = "12864";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL; 
////        String contactId =  "1-UEH6FU";
//        String contactId ="<mock-not-used-contactId>";
//        String chlNodeId = "";
//        String assetType = "ALL";
////        boolean favoriteFlag = false;
//        boolean favoriteFlag = false;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
//        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        Map<String, Object> sortCriteria = new HashMap<String, Object>();
//        sortCriteria.put("serialNumber", "ASCENDING");
//        assetContract.setSortCriteria(sortCriteria);
//
//        AssetListResult result = null;
//        String ns = "[retrieveDeviceList] ";
//        logger.info(ns
//                + String.format("Search criteria: mdmId=%s, mdmLevel=%s, contactId=%s", mdmId, mdmLevel, contactId));
//        long startTime = System.currentTimeMillis();
//        result = service.retrieveDeviceList(assetContract);
//        long endTime = System.currentTimeMillis();
//        logger.info(ns + String.format("elapsed time = %sms", (endTime - startTime)));
//        logAssetList(ns, result);
//        assertTrue(result.getAssets().size() > 0);
//        assertTrue(result.getAssets().size() <= numRecords);
//    }
//    
//    @Test
//    public void testRetrieveDeviceList_productLine_QA() throws Exception {
//        String mdmId = "023058159";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_GLOBAL; 
//        String contactId ="1-42NMBKT";
//        String chlNodeId = "";
//        String assetType = "ALL";
////        boolean favoriteFlag = false;
//        boolean favoriteFlag = false;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
////        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        
//        assetContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("productLine", "360d"));
//        
//        AssetListResult result = service.retrieveDeviceList(assetContract);
//        if (LangUtil.isEmpty(result.getAssets())) {
//                System.out.printf("no data\n");
//        } else {
//            for (Asset a : LangUtil.notNull(result.getAssets())) {
//                System.out.printf("assetId=%s, productLine=%s\n", a.getAssetId(), a.getProductLine());
//            }
//        }
//    }
    
    
//    @Test
//    public void testRetrieveDeviceList_QA_Defect2640() throws Exception {
//        String mdmId = "023058159";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_GLOBAL; 
//        String contactId ="1-42NMBKT";
//        String chlNodeId = "";
//        String assetType = "ALL";
//        boolean favoriteFlag = true;
//        boolean newQueryIndicator = true;
//        int startIndex = 0;
//        int numRecords = 40;
//
//        AssetListContract contract = new AssetListContract();
//        contract.setSessionHandle(crmSessionHandle);
//       contract.setMdmId(mdmId);
//        contract.setMdmLevel(mdmLevel);
//        contract.setStartRecordNumber(startIndex);
//        contract.setIncrement(numRecords);
//        contract.setNewQueryIndicator(newQueryIndicator);
//        contract.setContactId(contactId);
//        contract.setFavoriteFlag(favoriteFlag);
//        contract.setAssetType(assetType);
//        contract.setChlNodeId(chlNodeId);
//        contract.setLoadAllFlag(false);
//        
//        
//        String serialNumberFilter = " 00  ";
//        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", serialNumberFilter));
//        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//        
//        AssetListResult result = service.retrieveDeviceList(contract);
//        MiscTest.print(result.getAssets(), "serialNumber", "assetTag");
//        
//        for (Asset a : result.getAssets()) {
//            String s = "" + a.getSerialNumber();
//            assertTrue(s.contains(LangUtil.trim(serialNumberFilter)));
//        }
//    }
    
//    @Test
//    public void testRetrieveDeviceList_QA_20130402_ConsumableAssetFlag() throws Exception {
//        AssetListContract contract = new AssetListContract();
//        contract.setSessionHandle(crmSessionHandle);
//        contract.setMdmId("5989");
//        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
//        contract.setStartRecordNumber(0);
//        contract.setIncrement(40);
//        contract.setNewQueryIndicator(true);
//        contract.setContactId("1-50WCXE5");
//        contract.setFavoriteFlag(false);
//        contract.setAssetType("");
//        contract.setChlNodeId("");
//        contract.setLoadAllFlag(false);
//        contract.setEntitlementEndDate("04/02/2013");
//        
//        String serialNumberFilter = "72HVB7T";
//        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", serialNumberFilter));
//        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
//        
//        long t0 = System.currentTimeMillis();
//        try {
//            AssetListResult result = service.retrieveDeviceList(contract);
//            MiscTest.print(result.getAssets(), "serialNumber", "consumableAssetFlag");
//        } finally {
//            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
//        }
//    }    
    
    
//    @Test
//    public void testRetrieveDeviceDetail_QA_20130402_ConsumableAssetFlag() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setContactId("1-50WCXE5"); 
//        contract.setAssetId("1-4LA0JS4");
//
//        long t0 = System.currentTimeMillis();
//        try {
//            AssetResult r = service.retrieveDeviceDetail(contract);
//            MiscTest.print("", r.getAsset(), "serialNumber", "consumableAssetFlag");
//            System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//            System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//        } finally {
//            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
//        }
//    }    
    
    
    @Test
    public void filterProductLine() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAsset.class,
//                "EXISTS ([LXK SW Agreement Account Global DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([LXK C Assignment Product Line] ~LIKE '*360d*') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')",
//                "EXISTS ([LXK SW Agreement Account Global DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([LXK C Assignment Product Line] = 'Lexmark T642n') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')",
//                "EXISTS ([LXK SW Agreement Account Global DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')",
                "EXISTS ([LXK SW Agreement Account Global DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([LXK Product Model Name] ~LIKE '*360d*') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')", 
                50);
    }
    
    /**
     * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest
     */
//    @Test
//    public void testRetrieveDeviceList_withData_assetFavFlagTrue() throws Exception {
//        String mdmId = "12864";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL; 
////        String contactId =  "1-UEH6FU";
//        String contactId ="<mock-not-used-contactId>";
//        String chlNodeId = "";
//        String assetType = "ALL";
////        boolean favoriteFlag = false;
//        boolean favoriteFlag = true;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
//        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        Map<String, Object> sortCriteria = new HashMap<String, Object>();
//        sortCriteria.put("serialNumber", "ASCENDING");
//        assetContract.setSortCriteria(sortCriteria);
//
//        AssetListResult result = null;
//        String ns = "[retrieveDeviceList] ";
//        logger.info(ns
//                + String.format("Search criteria: mdmId=%s, mdmLevel=%s, contactId=%s", mdmId, mdmLevel, contactId));
//        long startTime = System.currentTimeMillis();
//        result = service.retrieveDeviceList(assetContract);
//        long endTime = System.currentTimeMillis();
//        logger.info(ns + String.format("elapsed time = %sms", (endTime - startTime)));
//        logAssetList(ns, result);
////        assertTrue(result.getAssets().size() > 0);
//        assertTrue(result.getAssets().size() <= numRecords);
//    } 
    
    
    /**
    * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest
    */
//    @Test
//    public void testRetrieveDeviceList_withData_consumableAssetFlag() throws Exception {
//        String mdmId = "1-52Y7PN";
//        String mdmLevel = LexmarkConstants.MDM_LEVEL_SIEBEL;
////        String contactId =  "1-UEH6FU";
//        String contactId ="<mock-not-used-contactId>";
//        String chlNodeId = "";
//        String assetType = "ALL";
////        boolean favoriteFlag = false;
//        boolean favoriteFlag = false;
//        int startIndex = 0;
//        int numRecords = 20;
//
//        AssetListContract assetContract = new AssetListContract();
//        assetContract.setSessionHandle(crmSessionHandle);
//        assetContract.setMdmId(mdmId);
//        assetContract.setMdmLevel(mdmLevel);
//        assetContract.setStartRecordNumber(startIndex);
//        assetContract.setIncrement(numRecords);
//        assetContract.setNewQueryIndicator(true);
//        assetContract.setContactId(contactId);
//        assetContract.setFavoriteFlag(favoriteFlag);
//        assetContract.setAssetType(assetType);
//        assetContract.setChlNodeId(chlNodeId);
//        Map<String, Object> sortCriteria = new HashMap<String, Object>();
//        sortCriteria.put("serialNumber", "ASCENDING");
//        assetContract.setSortCriteria(sortCriteria);
//
//        AssetListResult result = null;
//        String ns = "[retrieveDeviceList] ";
//        logger.info(ns
//                + String.format("Search criteria: mdmId=%s, mdmLevel=%s, contactId=%s", mdmId, mdmLevel, contactId));
//        long startTime = System.currentTimeMillis();
//        result = service.retrieveDeviceList(assetContract);
//        long endTime = System.currentTimeMillis();
//        logger.info(ns + String.format("elapsed time = %sms", (endTime - startTime)));
//        logAssetList(ns, result);
////        assertTrue(result.getAssets().size() > 0);
//        assertTrue(result.getAssets().size() <= numRecords);
//    } 
    
    
    private void logAssetList(String ns, AssetListResult result) {
        System.out.println(ns + "[IN] logAssetList");
        List<com.lexmark.domain.Asset> assetList = result.getAssets();
        System.out.println(ns + "result=" + result);
        System.out.println(ns + "result.getAssets()=" + result.getAssets());
        System.out.println(ns + "result.getTotalCount()=" + result.getTotalCount());

        int i = 0;
        if (LangUtil.isNotEmpty(assetList)) {
            for (com.lexmark.domain.Asset asset : assetList) {
                System.out.printf("%d:\n", ++i);
                System.out.println(ns + "Asset ID: " + asset.getAssetId());
                System.out.println(ns + "Mac Address: " + asset.getMacAddress());
                System.out.println(ns + "Favorite: " + asset.getUserFavoriteFlag());
                System.out.println(ns + "Machine Model: " + asset.getModelNumber());
                System.out.println(ns + "Asset tag: " + asset.getAssetTag());
                System.out.println(ns + "SerialNumber: " + asset.getSerialNumber());
                System.out.println(ns + "ConsumableAssetFlag: " + asset.isConsumableAssetFlag());
            }
        }
    }

//    @Test
//    public void testRetrieveDeviceDetail_noData() throws Exception {
//        String ns = "[retrieveDeviceDetail] ";
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-4IO9X-not-exists");
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        logger.info(ns + "result=" + r);
//        logger.info(ns + "result.getAsset()=" + r.getAsset());
//        assertTrue(r.getAsset() == null);
////        MiscTest.showStructure(r.getAsset());
//    }

//    @Test
//    public void testRetrieveDeviceDetail() throws Exception {
//        String ns = "[retrieveDeviceDetail] ";
//        String assetId = "1-4IO9X";
//        AssetContract contract = new AssetContract();
//        contract.setAssetId(assetId);
////        contract.setAssetId("   \t");
//        contract.setContactId("No Match Row Id");
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        logger.info(ns + "result=" + r);
//        logger.info(ns + "result.getAsset()=" + r.getAsset());
//        assertTrue(r.getAsset() != null);
//        assertEquals(assetId, r.getAsset().getAssetId());
//        MiscTest.showStructure(r.getAsset());
//    }
   
//    @Test
//    public void testRetrieveDeviceDetail_withConsumableAssetFlag() throws Exception {
//        String ns = "[retrieveDeviceDetail] ";
//        String assetId = "1-GKVJEB";
//        AssetContract contract = new AssetContract();
//        contract.setAssetId(assetId);
//        contract.setContactId("No Match Row Id");
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        logger.info(ns + "result=" + r);
//        logger.info(ns + "result.getAsset()=" + r.getAsset());
//        assertTrue(r.getAsset() != null);
//        assertEquals(assetId, r.getAsset().getAssetId());
//        MiscTest.showStructure(r.getAsset());
//    }
    
//    @Test
//    public void testRetrieveDeviceDetail_QA_Defect3918() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-48HEYPV"); // consumableAssetFlag = true
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//        System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//    }
    
//    @Test
//    public void testRetrieveDeviceDetail_QA_Defect6752() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-4LA0JS4t"); 
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//        System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//    }
    
//    @Test
//    public void testRetrieveDeviceDetail_QA_Defect3918_2() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-PQO-3035"); 
//        contract.setContactId("1-42NMBKT");
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//        System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//    }
    
    /**
     * 
     * @see AmindRequestTypeServiceTest#testRetrieveSupplyRequestDetail_Production_defect5750() 
     */
//    @Test
//    public void testRetrieveDeviceDetail_Production_Defect5750() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-4LFFOSO"); 
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//        System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//        MiscTest.print("pageCounts = ", r.getAsset().getPageCounts());
//    }
    
    @Test
    public void query_Production_Defect5750() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAssetDetailDo.class,
                "[serialNumber] = '59236SI'"
                , 10);
    }
    
//    @Test
//    public void testRetrieveDeviceDetail_withData() throws Exception {
//        String ns = "[retrieveDeviceDetail] ";
//        String assetId = "1-5T4UTL";
//        AssetContract contract = new AssetContract();
//        contract.setAssetId(assetId);
////        contract.setContactId("No Match Row Id");
//
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        logger.info(ns + "result=" + r);
//        logger.info(ns + "result.getAsset()=" + r.getAsset());
//        assertTrue(r.getAsset() != null);
//        assertEquals(assetId, r.getAsset().getAssetId());
//        MiscTest.showStructure(r.getAsset());
//    }
    
    
    @Test
    public void testRetrieveAssetLocations() {
        String mdmId = "807049655";
        String mdmLevel = "Global";
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId(mdmId);
        contract.setMdmLevel(mdmLevel);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList());
    }
    
    @Test
    public void testRetrieveAssetLocations_vendorFlag() {
        String mdmId = "807049655";
        String mdmLevel = "Global";
        LocationReportingHierarchyContract assetConract = new LocationReportingHierarchyContract();
        assetConract.setSessionHandle(this.crmSessionHandle);
        assetConract.setMdmId(mdmId);
        assetConract.setMdmLevel(mdmLevel);
        assetConract.setVendorFlag(true);
//        assetConract.setChlNodeId(chlNodeId);
        AssetLocationsResult assetLocation;
        long t0 = System.currentTimeMillis();
        try {
            assetLocation = service.retrieveAssetLocations(assetConract);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        } 
        MiscTest.print(assetLocation.getAddressList());
    }
    
    @Test
    public void testRetrieveAssetLocations_QA() {
        String mdmId = "1-1G0U13D";
        String mdmLevel = "Siebel";
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId(mdmId);
        contract.setMdmLevel(mdmLevel);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
//        assetConract.setChlNodeId(chlNodeId);
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList());
    }
    
    @Test
    public void testRetrieveAssetLocations_QA_chlNodeId() {
        String mdmId = "1-1G0U13D";
        String mdmLevel = "Siebel";
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId(mdmId);
        contract.setMdmLevel(mdmLevel);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
//        contract.setChlNodeId("mock-chlNodeId");
        contract.setChlNodeId("1-219TUCC");
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList());
    }
    
    
    @Test
    public void testRetrieveAssetLocations_QA_Defect2171() {
        String mdmId = "023058159";
        String mdmLevel = "Global";
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId(mdmId);
        contract.setMdmLevel(mdmLevel);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList(), "country");
    }
    
    @Test
    public void testRetrieveAssetLocations_QA_Defect4941() {
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId("Lexmark");   
        contract.setMdmLevel("Employee");
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList(), "country", "province", "city");
    }
    
    
    @Test
    public void testRetrieveAssetLocations_QA_entitlementFieldMap() {
        String mdmId = "023058159";
        String mdmLevel = "Global";
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId(mdmId);
        contract.setMdmLevel(mdmLevel);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        contract.setStartRecordNumber(0);
        contract.setIncrement(10);  // not implemented
        
        AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
        MiscTest.print(assetLocation.getAddressList());
    }
    
    @Test
    public void testRetrieveAssetLocations_QA_searchspec_check() {
    	String mdmId = "110899";
    	String mdmLevel = "Legal";
    	LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
    	contract.setSessionHandle(this.crmSessionHandle);
    	contract.setMdmId(mdmId);
    	contract.setMdmLevel(mdmLevel);
    	contract.setEntitlementEndDate("08/04/2014");
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(0);  // not implemented
    	
    	AssetLocationsResult assetLocation =  service.retrieveAssetLocations(contract); 
    	MiscTest.print(assetLocation.getAddressList());
    }
    
    
    /**
     * Performance records (2012-08-31 12:00):
     *   Excec time=100.956 sec. 
     *   Excec time=123.818 sec. 
     *   
     */
    @Test
    public void testRetrieveAssetLocations_vendorFlag_QA() {
        String mdmId = "1-1G0U13D";
        String mdmLevel = "Siebel";
        LocationReportingHierarchyContract assetConract = new LocationReportingHierarchyContract();
        assetConract.setSessionHandle(this.crmSessionHandle);
        assetConract.setMdmId(mdmId);
        assetConract.setMdmLevel(mdmLevel);
        assetConract.setVendorFlag(true);
//        assetConract.setChlNodeId(chlNodeId);
        AssetLocationsResult assetLocation;
        long t0 = System.currentTimeMillis();
        try {
            assetLocation = service.retrieveAssetLocations(assetConract);
            MiscTest.print(assetLocation.getAddressList());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        } 
    }
    

    @Test
    public void testRetrieveAssetReportingHierarchy() throws Exception {
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setSessionHandle(this.crmSessionHandle);
        contract.setMdmId("1-1G0U13D");
        contract.setMdmLevel("Siebel");
//        contract.setChlNodeId("mock-chlNodeId");
        AssetReportingHierarchyResult r =  service.retrieveAssetReportingHierarchy(contract);
        MiscTest.print(r.getChlNodeList());
    }
    

    @Test
    public void testUpdateUserFavoriteAsset() throws Exception {
        UserFavoriteAssetContract c = new UserFavoriteAssetContract();
        FavoriteResult r = service.updateUserFavoriteAsset(c);
        System.out.println("result = " + r.isResult());
    }

    @Test
    public void testRetrieveFullAssetReportingHierarchy() throws Exception {
        LocationReportingHierarchyContract c = new LocationReportingHierarchyContract();
        c.setMdmId("mock-mdmId");
        c.setMdmLevel("Account");
        AssetReportingHierarchyResult r = service.retrieveFullAssetReportingHierarchy(c);
        MiscTest.print(r.getChlNodeList());
    }

    @Test
    public void testRetrieveGlobalAssetList() throws Exception {
        GlobalAssetListContract c = new GlobalAssetListContract();
        c.setSerialNumber("mock-serialNumber");
        AssetListResult r = service.retrieveGlobalAssetList(c);
        MiscTest.print("assets=" , r.getAssets()); 
    }    
    
//    @Test
//    public void testRetrieveDeviceDetail_JobRole() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-JOFQ-1"); 
//        contract.setContactId("1-50WCH05");
//        
//        AssetResult r = service.retrieveDeviceDetail(contract);
//        System.out.println("serialNumber = " + r.getAsset().getSerialNumber());
//        System.out.println("consumableAssetFlag = " + r.getAsset().isConsumableAssetFlag());
//    }
    
//    @Test
//    public void testRetrieveDeviceDetail_HeaderLevelContactOfAssetNotGettingFetched() throws Exception {
//        AssetContract contract = new AssetContract();
//        contract.setAssetId("1-7N8E-6715"); 
//        
//        AssetResult result = service.retrieveDeviceDetail(contract);
//        
//        Asset asset = result.getAsset();
//        
//        System.out.println(asset == null ? "Asset is null" : "Asset is not null");
//        System.out.println(asset.getAssetContact() == null ? "Yes" : "No");
//        System.out.println("First name: " + asset.getAssetContact().getFirstName());
//        System.out.println("Last name: " + asset.getAssetContact().getLastName());
//    }
    
    
    
    @Test
    public void testUpdateUserFavoriteAsset_ForDefect8093Checking() throws Exception {
        UserFavoriteAssetContract contract = new UserFavoriteAssetContract();
        contract.setContactId("1-7E1VKC1");
        contract.setFavoriteFlag(true);
        contract.setFavoriteAssetId("1-BHYJ-944");
        
        contract.setSessionHandle(crmSessionHandle);
        
        FavoriteResult r = service.updateUserFavoriteAsset(contract);
        
        System.out.println("result = " + r.isResult());
    }

    @Test
    public void testRetrieveAssetReportingHierarchy_defect10893() throws Exception {
        LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
        contract.setMdmId("30145");
        contract.setMdmLevel("Legal");
        contract.setVendorFlag(false);
        contract.setIncrement(0);
        contract.setStartRecordNumber(0);
        contract.setNewQueryIndicator(false);
        
        AssetReportingHierarchyResult result =  service.retrieveAssetReportingHierarchy(contract);
        
        List<CHLNode> list = result.getChlNodeList();
        
        System.out.println(list == null ? "Null" : list.size());
    }
    
}
