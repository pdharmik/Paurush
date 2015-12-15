package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.MiscTest.checkFilterResult;
import static com.lexmark.service.impl.real.MiscTest.toMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.SortUtil;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledAssetDo;
import com.lexmark.service.impl.real.util.TestErrors;

/**
 * @see  com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-09-18
 */
public class AssetListServiceFilterTest extends AmindServiceTest {

    static AssetListService service;
    static Session session;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        session = statefulSessionFactory.attachSession();
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if(session != null){
            session.release(); 
        }
    }
    
    @Test
    public void testFilter_QA_AllFields() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>(); // see AssetListService.ALL_FIELD_MAP
        filters.put("assetTag", "Owner Asset Number");
        filters.put("serialNumber", "Serial Number");
        filters.put("installAddress.addressName", "Install Address Name");
        filters.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
        filters.put("installAddress.state", "Install State");
        filters.put("installAddress.city", "Install City");
        filters.put("installAddress.province", "Install Province");
        filters.put("installAddress.postalCode", "Install Postal Code");
        filters.put("installAddress.country", "Install Country");
        filters.put("assetContact.firstName", "LXK SW Primary Contact First Name");
        filters.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
        filters.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
        filters.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
        filters.put("productTLI", "Product Name");
        filters.put("productLine", "LXK Product Model Name");
//        filters.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
//        filters.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
//        filters.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
//        filters.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
//        filters.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
//        filters.put("accountTransFlag", "LXK L5 Account Transactable Flag");
        filters.put("modelNumber", "LXK C MTM Name");
        filters.put("hostName", "Host Name");
        filters.put("ipAddress", "IP Address");
        filters.put("deviceTag", "Device Tag Customer");
        filters.put("devicePhase", "Device Phase");
        filters.put("macAddress", "MAC Address");
//        filters.put("createDate", "09/19/2012 05:30:00");        
        
        AssetListResult r = retrieveAllDeviceList_QA(false, filters);
        for (Asset asset : r.getAssets()) {
           for (Entry<String, Object> e : filters.entrySet()) {
                System.out.println("***** checking " + e.getValue());
                checkFilterResult(filters, e.getKey(), SortUtil.readField(asset, e.getKey(), "."));
            }
        }
    }        
    
   
    @Test
    public void testFilter_QA() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>(); // see AssetListService.ALL_FIELD_MAP
        filters.put("assetTag", "Owner Asset Number");
        filters.put("serialNumber", "Serial Number");
        filters.put("installAddress.addressName", "Install Address Name");
        filters.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
        filters.put("installAddress.state", "Install State");
        filters.put("installAddress.city", "Install City");
        filters.put("installAddress.province", "Install Province");
        filters.put("installAddress.postalCode", "Install Postal Code");
        filters.put("installAddress.country", "Install Country");
        filters.put("assetContact.firstName", "LXK SW Primary Contact First Name");
        filters.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
        filters.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
        filters.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
        filters.put("productTLI", "Product Name");
        filters.put("productLine", "LXK Product Model Name");
//        filters.put("mdmLevel1AccountId", "LXK SW Agreement Account Global DUNS");
//        filters.put("mdmLevel2AccountId", "LXK SW Agreement Account Domestic DUNS");
//        filters.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID");
//        filters.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account");
//        filters.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
//        filters.put("accountTransFlag", "LXK L5 Account Transactable Flag");
        filters.put("modelNumber", "LXK C MTM Name");
        filters.put("hostName", "Host Name");
        filters.put("ipAddress", "IP Address");
        filters.put("deviceTag", "Device Tag Customer");
        filters.put("devicePhase", "Device Phase");
        filters.put("macAddress", "MAC Address");
//        filters.put("createDate", "09/19/2012 05:30:00");        
        
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                AssetListResult r = retrieveAllDeviceList_QA(false, oneFilter);
                for (Asset asset : r.getAssets()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, e.getKey(), SortUtil.readField(asset, e.getKey(), "."));
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        errors.check();
    }    
    
    @Test
    public void testFilter_QA_Fav() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>(); // see AssetListService.FAVORITE_FIELD_MAP
        filters.put("assetTag", "Owner Asset Number");
        filters.put("serialNumber", "Serial Number");
        filters.put("installAddress.addressName", "Install Address Name");
        filters.put("installAddress.addressLine1", "Install Address Line 1");
        filters.put("installAddress.state", "Install Address State");
        filters.put("installAddress.city", "Install Address City");
        filters.put("installAddress.province", "Install Address Province");
        filters.put("installAddress.country", "Install Address Country");
        filters.put("assetContact.firstName", "Primary Contact First Name");
        filters.put("assetContact.lastName", "Primary Contact Last Name");
        filters.put("assetContact.emailAddress", "Primary Contact Email Address");
        filters.put("assetContact.workPhone", "Primary Contact Work Phone");
        filters.put("productTLI", "Product Name");
        filters.put("hostName", "Host Name");
        filters.put("modelNumber", "MTM Name");
        filters.put("ipAddress", "IP Address");
        filters.put("deviceTag", "Device Tag Customer");
        filters.put("devicePhase", "Device Phase");
        filters.put("macAddress", "MAC Address");
        filters.put("productLine", "LXK Product Model Name");
        filters.put("installAddress.postalCode", "4-378");
//        filters.put("mdmLevel5AccountId", "LXK SW Agreement Account Id");
        
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                AssetListResult r = retrieveAllDeviceList_QA(true, oneFilter);
                for (Asset asset : r.getAssets()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, e.getKey(), SortUtil.readField(asset, e.getKey(), "."));
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        errors.check();
    }    
    
    @Test
    public void testFilter_QA_selected() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>(); 
        filters.put("installAddress.addressName", "Install Address Name");
        filters.put("productTLI", "Product Name");
        filters.put("productLine", "LXK Product Model Name");
        
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                AssetListResult r = retrieveAllDeviceList_QA(false, oneFilter);
                for (Asset asset : r.getAssets()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, e.getKey(), SortUtil.readField(asset, e.getKey(), "."));
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        errors.check();
    }    
    
    @Test
    public void testFilter_QA_Fav_selected() throws Exception {
        Map<String, Object> filters = new LinkedHashMap<String, Object>(); // see AssetListService.FAVORITE_FIELD_MAP
        filters.put("installAddress.addressName", "Install Address Name");
        filters.put("productTLI", "Product Name");
        filters.put("productLine", "LXK Product Model Name");
        
        TestErrors errors  = new TestErrors();
        for (Entry<String, Object> e : filters.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            try {
                AssetListResult r = retrieveAllDeviceList_QA(true, oneFilter);
                for (Asset asset : r.getAssets()) {
                    System.out.println("***** checking " + e.getValue());
                    checkFilterResult(oneFilter, e.getKey(), SortUtil.readField(asset, e.getKey(), "."));
                }
            } catch (Throwable ex) {
                errors.add(ex, "" + oneFilter);
            }
        }        
        errors.check();
    }    
    
    private AssetListResult retrieveAllDeviceList_QA(boolean favFlag, Map<String, Object> filterCriteria) {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Domestic");
        contract.setMdmId("023058159");
//        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
//        contract.setIncrement(40);
        contract.setIncrement(40);
        contract.setContactId("1-42MX1P9");
        contract.setFavoriteFlag(favFlag);
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
//        contract.setSortCriteria(sortCriteria);
        contract.setFilterCriteria(filterCriteria);
        
        return new AssetListService(session, crmSessionHandle, contract).retrieveAllDeviceList();
    }
   
    @Test
    public void test_retrieveAllDeviceList_QA() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            AssetListResult result = retrieveAllDeviceList_QA(false, null);
            MiscTest.print(result.getAssets());  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
        
    }
    
    @Test
    public void test_retrieveAllDeviceList_QA_withFilter() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            AssetListResult result = retrieveAllDeviceList_QA(false, 
                     (Map<String, Object>) MiscTest.newHashMap("assetTag", "12333"));
//                     (Map<String, Object>) MiscTest.newHashMap("serialNumber", "11"));
            MiscTest.print(result.getAssets());  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    @Test
    public void query() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery( ContractedAndEntitledAssetDo.class, 
//                " EXISTS ([LXK SW Agreement Account Domestic DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] = 'Siebel') OR ((EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >= '09/12/2012'  AND  [LXK MPS Domestic DUNS] = '023058159' AND [LXK MPS Ent Account Level] = 'Siebel'))) AND ([Owner Asset Number] ~LIKE '*12333*')"
                    " EXISTS ([LXK SW Agreement Account Domestic DUNS] = '023058159' AND [LXK SW Agreement Account MDM Level] = 'Siebel') OR ((EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >= '09/12/2012'  AND  [LXK MPS Domestic DUNS] = '023058159' AND [LXK MPS Ent Account Level] = 'Siebel'))) AND ([Owner Asset Number] ~LIKE '*12333*')"
                    ,5);  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
        
    }
    
    
    @Test
    public void test_retrieveAllDeviceList_QA_fav_withFilter() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            AssetListResult result = retrieveAllDeviceList_QA(true, 
                     (Map<String, Object>) MiscTest.newHashMap("assetTag", "12333"));
//                     (Map<String, Object>) MiscTest.newHashMap("serialNumber", "11"));
            MiscTest.print(result.getAssets());  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    @Test
    public void test_retrieveAllDeviceList_QA_withFavFlag() throws Exception {
        AssetListResult result = retrieveAllDeviceList_QA(true, null);
        MiscTest.print(result.getAssets(), "installAddress.postalCode");
    }    
}
