package com.lexmark.service.impl.real;


import static com.lexmark.service.impl.real.MiscTest.checkFilterResult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.util.LangUtil;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-17
 */
public class AmindContractedDeviceServiceRetrieveDeviceListFilterTest  extends AmindServiceTest {
    
    static AmindContractedDeviceService service;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        logger.info("in setUp()");
        service = new AmindContractedDeviceService();
        service.setSessionFactory(statelessSessionFactory);
    }
    
    /**
     * @see com.lexmark.service.impl.real.RequestTypeListFilterTest 
     */
    @Test
    public void testFilter() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "1");
        filterSpec.put("serialNumber", "1");
        filterSpec.put("installAddress.addressName", "");
        filterSpec.put("installAddress.addressLine1", "");
        filterSpec.put("installAddress.state", "");
        filterSpec.put("installAddress.city", "");
        filterSpec.put("installAddress.postalCode", "");
        filterSpec.put("installAddress.country", "USa");
        filterSpec.put("assetContact.firstName", "");
        filterSpec.put("assetContact.lastName", "Cathey");
        filterSpec.put("assetContact.emailAddress", "");
        filterSpec.put("assetContact.workPhone", "774");
        filterSpec.put("productLine", "");
        filterSpec.put("modelNumber", "");
        filterSpec.put("hostName", ""); // not hostname 
        filterSpec.put("ipAddress", "");
        filterSpec.put("deviceTag", "");
        filterSpec.put("devicePhase", "");
        AssetListResult r = _retrieveDeviceList(filterSpec);
        logAssetList("testFilter", r);
        for (Asset a : r.getAssets()) {
           checkFilterResult(filterSpec, "serialNumber", a.getSerialNumber()); 
           checkFilterResult(filterSpec, "assetTag", a.getAssetTag()); 
           checkFilterResult(filterSpec, "installAddress.addressName", a.getInstallAddress().getAddressName()); 
           checkFilterResult(filterSpec, "installAddress.addressLine1", a.getInstallAddress().getAddressLine1());
           checkFilterResult(filterSpec, "installAddress.state", a.getInstallAddress().getState());
           checkFilterResult(filterSpec, "installAddress.city", a.getInstallAddress().getCity());
           checkFilterResult(filterSpec, "installAddress.postalCode", a.getInstallAddress().getPostalCode());
           checkFilterResult(filterSpec, "installAddress.country", a.getInstallAddress().getCountry(), false);
           checkFilterResult(filterSpec, "assetContact.firstName", a.getAssetContact().getFirstName());
           checkFilterResult(filterSpec, "assetContact.lastName", a.getAssetContact().getLastName());
           checkFilterResult(filterSpec, "assetContact.emailAddress", a.getAssetContact().getEmailAddress());
           checkFilterResult(filterSpec, "assetContact.workPhone", a.getAssetContact().getWorkPhone());
           checkFilterResult(filterSpec, "productLine", a.getProductLine());
           checkFilterResult(filterSpec, "modelNumber", a.getModelNumber());
           checkFilterResult(filterSpec, "hostName",  a.getHostName());
           checkFilterResult(filterSpec, "ipAddress", a.getIpAddress());
           checkFilterResult(filterSpec, "deviceTag", a.getDeviceTag());
           checkFilterResult(filterSpec, "devicePhase", a.getDevicePhase());
        }
    }
    
    @Test
    public void testFilter2() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "1");
        filterSpec.put("serialNumber", "1");
        filterSpec.put("installAddress.addressName", "");
        filterSpec.put("installAddress.addressLine1", "");
        filterSpec.put("installAddress.state", "");
        filterSpec.put("installAddress.city", "");
        filterSpec.put("installAddress.postalCode", "");
        filterSpec.put("installAddress.country", "USA");
        filterSpec.put("assetContact.firstName", "");
        filterSpec.put("assetContact.lastName", "Cathey");
        filterSpec.put("assetContact.emailAddress", "");
        filterSpec.put("assetContact.workPhone", "774");
        filterSpec.put("productLine", "");
        filterSpec.put("modelNumber", "");
        filterSpec.put("hostName", "a"); 
        filterSpec.put("ipAddress", "");
        filterSpec.put("deviceTag", "");
        filterSpec.put("devicePhase", "");
        
        for (Entry<String, Object> e : filterSpec.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            AssetListResult r = _retrieveDeviceList(oneFilter);
            logAssetList2("testFilter: ", r, e);
            for (Asset a : r.getAssets()) {
                Object val = SortUtil.readField(a, e.getKey(), ".");
                checkFilterResult(filterSpec, e.getKey(), val);
            }
        }
    }
    
    @Test
    public void testFilter3() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
//        filterSpec.put("assetTag", "1");
//        filterSpec.put("serialNumber", "1");
//        filterSpec.put("installAddress.addressName", "");
//        filterSpec.put("installAddress.addressLine1", "");
//        filterSpec.put("installAddress.state", "");
//        filterSpec.put("installAddress.city", "");
//        filterSpec.put("installAddress.postalCode", "");
//        filterSpec.put("installAddress.country", "USA");
//        filterSpec.put("assetContact.firstName", "");
//        filterSpec.put("assetContact.lastName", "Cathey");
//        filterSpec.put("assetContact.emailAddress", "");
//        filterSpec.put("assetContact.workPhone", "774");
//        filterSpec.put("productLine", "");
//        filterSpec.put("modelNumber", "");
        filterSpec.put("hostName", ""); 
//        filterSpec.put("ipAddress", "");
//        filterSpec.put("deviceTag", "");
//        filterSpec.put("devicePhase", "");
        
//        AssetListResult nonFilteringResult = _retrieveDeviceList(null);
//        logAssetList("testFilter: ", nonFilteringResult);
        
        for (Entry<String, Object> e : filterSpec.entrySet()) {
            Map<String, Object> oneFilter = toMap(e);
            AssetListResult r = _retrieveDeviceList(oneFilter);
            logAssetList2("testFilter: ", r, e);
            for (Asset a : r.getAssets()) {
                Object val = SortUtil.readField(a, e.getKey(), ".");
                checkFilterResult(filterSpec, e.getKey(), val);
            }
        }
    }
    
    private Map<String, Object> toMap(Entry<String, Object> entry) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(entry.getKey(), entry.getValue());
        return map;
    }

    private static void logAssetList(String ns, AssetListResult result) {
        System.out.println(ns + "[IN] logAssetList");
        List<com.lexmark.domain.Asset> assetList = result.getAssets();

        int i = 0;
        if (LangUtil.isNotEmpty(assetList)) {
            for (com.lexmark.domain.Asset a : assetList) {
                System.out.printf("%d:\n", ++i);
                System.out.printf("serialNumber = `%s'\n", a.getSerialNumber()); 
                System.out.printf("assetTag = `%s'\n", a.getAssetTag()); 
                System.out.printf("installAddress.addressName = `%s'\n", a.getInstallAddress().getAddressName()); 
                System.out.printf("installAddress.addressLine1 = `%s'\n", a.getInstallAddress().getAddressLine1());
                System.out.printf("installAddress.state = `%s'\n", a.getInstallAddress().getState());
                System.out.printf("installAddress.city = `%s'\n", a.getInstallAddress().getCity());
                System.out.printf("installAddress.postalCode = `%s'\n", a.getInstallAddress().getPostalCode());
                System.out.printf("installAddress.country = `%s'\n", a.getInstallAddress().getCountry());
                System.out.printf("assetContact.firstName = `%s'\n", a.getAssetContact().getFirstName());
                System.out.printf("assetContact.lastName = `%s'\n", a.getAssetContact().getLastName());
                System.out.printf("assetContact.emailAddress = `%s'\n", a.getAssetContact().getEmailAddress());
                System.out.printf("assetContact.workPhone = `%s'\n", a.getAssetContact().getWorkPhone());
                System.out.printf("productLine = `%s'\n", a.getProductLine());
                System.out.printf("modelNumber = `%s'\n", a.getModelNumber());
                System.out.printf("hostName = `%s'\n",  a.getHostName());
                System.out.printf("ipAddress = `%s'\n", a.getIpAddress());
                System.out.printf("deviceTag = `%s'\n", a.getDeviceTag());
                System.out.printf("devicePhase = `%s'\n", a.getDevicePhase());
            }
        }
        
        System.out.println(ns + "====");
        System.out.println(ns + "result.getAssets().size()=" + result.getAssets().size());
        System.out.println(ns + "result.getTotalCount()=" + result.getTotalCount());
    }
    
    private static void logAssetList2(String ns, AssetListResult result, Entry<String, Object> entry) throws IllegalAccessException {
        System.out.println(ns + "[IN] logAssetList");
        List<com.lexmark.domain.Asset> assetList = result.getAssets();

        int i = 0;
        if (LangUtil.isNotEmpty(assetList)) {
            for (com.lexmark.domain.Asset a : assetList) {
//                System.out.printf("%d:\n", ++i);
                Object val = SortUtil.readField(a, entry.getKey(), ".");
                System.out.printf("%d: %s = `%s' (fitler=`%s')\n", ++i, entry.getKey(), val, entry.getValue());
            }
        }
        
        System.out.println(ns + "====");
        System.out.println(ns + "result.getAssets().size()=" + result.getAssets().size());
        System.out.println(ns + "result.getTotalCount()=" + result.getTotalCount());
    }

    private AssetListResult _retrieveDeviceList(Map<String, Object> filterCriteria) throws Exception {
        String mdmId = "12864";
        String mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL;
        String contactId ="<mock-not-used-contactId>";
        String chlNodeId = "";
        String assetType = "ALL";
        //        boolean favoriteFlag = false;
        boolean favoriteFlag = false;
        int startIndex = 0;
        int numRecords = 20;

        AssetListContract assetContract = new AssetListContract();
        assetContract.setSessionHandle(crmSessionHandle);
        assetContract.setMdmId(mdmId);
        assetContract.setMdmLevel(mdmLevel);
        assetContract.setStartRecordNumber(startIndex);
        assetContract.setIncrement(numRecords);
        assetContract.setNewQueryIndicator(true);
        assetContract.setContactId(contactId);
        assetContract.setFavoriteFlag(favoriteFlag);
        assetContract.setAssetType(assetType);
        assetContract.setChlNodeId(chlNodeId);
        //        assetContract.setSortCriteria(sortCriteria);
        assetContract.setFilterCriteria(filterCriteria);
        return service.retrieveDeviceList(assetContract);
    }

    @Test
    public void testRetrieveDeviceDetail_withData() throws Exception {
        String ns = "[retrieveDeviceDetail] ";
        String assetId = "1-5T4UTL";
        AssetContract contract = new AssetContract();
        contract.setAssetId(assetId);

        AssetResult r = service.retrieveDeviceDetail(contract);
        logger.info(ns + "result=" + r);
        logger.info(ns + "result.getAsset()=" + r.getAsset());
        assertTrue(r.getAsset() != null);
        assertEquals(assetId, r.getAsset().getAssetId());
        MiscTest.showStructure(r.getAsset());
    }

}
