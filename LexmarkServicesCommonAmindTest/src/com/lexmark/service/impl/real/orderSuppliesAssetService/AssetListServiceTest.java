package com.lexmark.service.impl.real.orderSuppliesAssetService;


import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.AmindOrderSuppliesAssetService;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;


/**
 * TODO(Viktor) call {@linkplain AmindOrderSuppliesAssetService#retrieveAllDeviceList(AssetListContract)} for 
 * test (there added logging facilites). 
 * 
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-30
 */
public class AssetListServiceTest extends AmindServiceTest {
    
    static AssetListService service;
    static Session session;

    @BeforeClass
    public static void setUp() throws Exception {
        session = statefulSessionFactory.attachSession();
    }
    
    @AfterClass
    public  static void tearDown() throws Exception {
    	if(session != null){
    		session.release(); 
    	}
    }
    
    @Test
    public void testRetrieveAllDeviceList_QA_fav() {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Domestic");
        contract.setMdmId("023058159");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setContactId("1-42MX1P9");
        contract.setFavoriteFlag(true);
        contract.setFavoriteFlag(false);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        
        long t0 = System.currentTimeMillis();
        try {
        	service = new AssetListService(session, crmSessionHandle,contract); 
            AssetListResult result = service.retrieveAllDeviceList();
            MiscTest.print(result.getAssets(), 
                    "assetId", "productLine", "installAddress.addressName", "consumableAssetFlag", "productTLI", "account.accountId", "account.accountName");  
            logger.debug("Count: " + result.getTotalCount());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    
    @Test
    public void testRetrieveAllDeviceList_QA_Defect3047() {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Global");
        contract.setMdmId("023058159");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setContactId("1-42NMBKT");
//        contract.setFavoriteFlag(true);
        contract.setFavoriteFlag(false);
        contract.setEntitlementEndDate("09/21/2012");
        contract.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap(
//                "ipAddress", "10.36.23.213",
                "assetTag", "  CIS10142 "
                ));
        
//        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
////                "ipAddress", "10.36.23.213",
//                "assetTag", "CIS10142 "
//                ));
        
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
        
        long t0 = System.currentTimeMillis();
        try {
            service = new AssetListService(session, crmSessionHandle,contract); 
            AssetListResult result = service.retrieveAllDeviceList();
            MiscTest.print(result.getAssets(), "serialNumber", "assetId", "ipAddress", "assetTag", "account.accountId", "account.accountName" );  
            logger.debug("Count: " + result.getTotalCount());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }    
    
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveAllDeviceList_QA_Defect4003() {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Domestic");
        contract.setMdmId("200443976");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setContactId("1-4IHKPB1");
//        contract.setFavoriteFlag(true);
        contract.setFavoriteFlag(false);
        contract.setEntitlementEndDate("10/15/2012");
//        contract.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap(
//                "ipAddress", "10.36.23.213",
//                "assetTag", "  CIS10142 "
//                ));
        
        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
                "serialNumber", "793V2VR"
                ));
        
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
        
        long t0 = System.currentTimeMillis();
        try {
            service = new AssetListService(session, crmSessionHandle,contract); 
            AssetListResult result = service.retrieveAllDeviceList();
//            MiscTest.print(result.getAssets(), "serialNumber", "assetId", "ipAddress", "assetTag" );  
            MiscTest.print(result.getAssets());
            MiscTest.print(result.getAssets(), "account.accountId", "account.accountName" );
            logger.debug("Count: " + result.getTotalCount());
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }      
    
    
    @Test
    public void testRetrieveAllDeviceList_QA_20130402_ConsumableAssetFlag() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setMdmId("5989");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setNewQueryIndicator(true);
        contract.setContactId("1-50WCXE5");
        contract.setFavoriteFlag(false);
        contract.setAssetType("");
        contract.setChlNodeId("");
        contract.setLoadAllFlag(false);
        contract.setEntitlementEndDate("04/02/2013");
        
        String serialNumberFilter = "72HVB7T";
        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", serialNumberFilter));
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
        
        long t0 = System.currentTimeMillis();
        try {
            service = new AssetListService(session, crmSessionHandle,contract); 
            AssetListResult result =  service.retrieveAllDeviceList();
            MiscTest.print(result.getAssets(), "serialNumber", "consumableAssetFlag");
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }    
    
    @Test
    public void testRetrieveAllDeviceList_QA_20130408() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setMdmId("15023");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
        contract.setNewQueryIndicator(true);
        contract.setContactId("1-50WCH05");
        contract.setFavoriteFlag(false);
        contract.setLoadAllFlag(false);
        contract.setAssetType("");
        contract.setChlNodeId("");
        contract.setEntitlementEndDate("04/02/2013"); // caused an error
        
        String serialNumberFilter = "72HVB7T";
//        contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", serialNumberFilter));
        contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap("serialNumber", "ASCENDING"));
        
        long t0 = System.currentTimeMillis();
        try {
            service = new AssetListService(session, crmSessionHandle,contract); 
            AssetListResult result =  service.retrieveAllDeviceList();
            MiscTest.print(result.getAssets(), "serialNumber", "consumableAssetFlag");
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }    
}
