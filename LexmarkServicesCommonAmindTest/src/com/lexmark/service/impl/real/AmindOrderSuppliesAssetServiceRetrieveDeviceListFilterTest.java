package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.MiscTest.checkFilterResult;
import static com.lexmark.service.impl.real.MiscTest.toMap;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceRetrieveDeviceListFilterTest
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-18
 */
public class AmindOrderSuppliesAssetServiceRetrieveDeviceListFilterTest extends AmindServiceTest {

    static AmindOrderSuppliesAssetService service;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        logger.info("in setUp()");
        service = new AmindOrderSuppliesAssetService();
        service.setStatelessSessionFactory(statelessSessionFactory);
    }

    /** 
     * Based on MPS_OrderManagement_Consumables_Assets_Orders_05_07_12.docx
     * 
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListServiceFilterTest#testFilter_QA()
     */
    @Test
    public void testFilter_allFields() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "Owner Asset Number");
        filterSpec.put("serialNumber", "Serial Number");
        filterSpec.put("installAddress.addressName", "Install Address Name");
        filterSpec.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
        filterSpec.put("installAddress.state", "Install State");
        filterSpec.put("installAddress.city", "Install City");
        filterSpec.put("installAddress.province", "Install Province");
        filterSpec.put("installAddress.postalCode", "Install Postal Code");
        filterSpec.put("installAddress.country", "Install Country");
        filterSpec.put("assetContact.firstName", "LXK SW Primary Contact First Name");
        filterSpec.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
        filterSpec.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
        filterSpec.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
        filterSpec.put("productTLI", "Product Name");
        filterSpec.put("productLine", "LXK C Assignment Product Line");
        filterSpec.put("modelNumber", "LXK Machine Type Model");
        filterSpec.put("hostName", "Host Name");
        filterSpec.put("ipAddress", "IP Address");
//                filterSpec.put("deviceTag", "Device Tag Customer");  // not in Filter / Search / Sort Criteria
        filterSpec.put("devicePhase", "Device Phase");
        filterSpec.put("macAddress", "MAC Address");
//        filterSpec.put("createDate", "10/03/2012 20:00:00");
//        filterSpec.put("contractType", ""); // TODO(Viktor) discuss  

        AssetListResult r = _retrieveDeviceList(filterSpec);
        for (Asset a : r.getAssets()) {
            for (Map.Entry<String, Object> entry : filterSpec.entrySet()) {
                System.out.println(entry.getKey());
                checkFilterResult(filterSpec, entry.getKey(), SortUtil.readField(a, entry.getKey(), "."));
            }
        }
    }
    
    @Test
    public void testFilter_allFields_byOne() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "Owner Asset Number");
        filterSpec.put("serialNumber", "500005D");
        filterSpec.put("installAddress.addressName", "Install Address Name");
        filterSpec.put("installAddress.addressLine1", "LXK UP Install Address Line 1");
        filterSpec.put("installAddress.state", "Install State");
        filterSpec.put("installAddress.city", "Install City");
        filterSpec.put("installAddress.province", "Install Province");
        filterSpec.put("installAddress.postalCode", "Install Postal Code");
        filterSpec.put("installAddress.country", "Install Country");
        filterSpec.put("assetContact.firstName", "LXK SW Primary Contact First Name");
        filterSpec.put("assetContact.lastName", "LXK SW Primary Contact Last Name");
        filterSpec.put("assetContact.emailAddress", "LXK SW Primary Contact Email");
        filterSpec.put("assetContact.workPhone", "LXK SW Primary Contact Work Phone");
        filterSpec.put("productTLI", "17J0200");
        filterSpec.put("productLine", "LXK C Assignment Product Line");
        filterSpec.put("modelNumber", "222");
        filterSpec.put("hostName", "Host Name");
        filterSpec.put("ipAddress", "IP Address");
//                filterSpec.put("deviceTag", "Device Tag Customer");  // not in Filter / Search / Sort Criteria
        filterSpec.put("devicePhase", "Device Phase");
        filterSpec.put("macAddress", "MAC Address");
//        filterSpec.put("createDate", "10/03/2012 20:00:00");
//        filterSpec.put("contractType", ""); // TODO(Viktor) discuss  
        
        for (Map.Entry<String, Object> entry : filterSpec.entrySet()) {
            AssetListResult r = _retrieveDeviceList(toMap(entry));
            for (Asset a : r.getAssets()) {
                System.out.println("=>" +  entry.getKey());
                checkFilterResult(filterSpec, entry.getKey(), SortUtil.readField(a, entry.getKey(), "."));
            }
        }
    }
    
    @Test
    public void testFilter_issues() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
//        filterSpec.put("productModel", ""); // TODO(Viktor) discuss 
//        filterSpec.put("contractType", ""); // TODO(Viktor) discuss 
        filterSpec.put("installAddress.province", ""); // problem when empty
//        filterSpec.put("createDate", "10/03/2012 20:00:00"); // problem when empty

        AssetListResult r = _retrieveDeviceList(filterSpec);
        for (Asset a : r.getAssets()) {
//            checkFilterResult(filterSpec, "productModel", a.getProductModel()); //no such field
//            checkFilterResult(filterSpec, "productModel", a.getProductModel()); //no such field
            checkFilterResult(filterSpec, "installAddress.province", a.getInstallAddress().getProvince());
        }
    }
    
    /**
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDeviceListService
     * @see com.lexmark.service.impl.real.domain.AccountAssetFavorites
     * @see #testRetrieveDeviceList_QA_favList
     */
    @Test
    public void testFilter_fav_all() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "1000");
        filterSpec.put("serialNumber", "CIS");
        filterSpec.put("installAddress.addressName", "Install Address Name");
        filterSpec.put("installAddress.addressLine1", "Install Address Line 1");
        filterSpec.put("installAddress.state", "Install Address State");
        filterSpec.put("installAddress.city", "Install Address City");
        filterSpec.put("installAddress.province", "Install Address Province");
        filterSpec.put("installAddress.country", "Install Address Country");
        filterSpec.put("assetContact.firstName", "Primary Contact First Name");
        filterSpec.put("assetContact.lastName", "Primary Contact Last Name");
        filterSpec.put("assetContact.emailAddress", "Primary Contact Email Address");
        filterSpec.put("assetContact.workPhone", "Primary Contact Work Phone");
        filterSpec.put("productTLI", "250");
        filterSpec.put("hostName", "Host Name");
        filterSpec.put("modelNumber", "061");
        filterSpec.put("ipAddress", "0.1");
        filterSpec.put("devicePhase", "Accepted");
        filterSpec.put("macAddress", "__macAddress");
        filterSpec.put("productLine", "T64");
//        filterSpec.put("installPostalCode", "Install Postal Zip"); // an error

        AssetListResult r = _retrieveDeviceListFav(filterSpec);
        for (Asset a : r.getAssets()) {
            for (Map.Entry<String, Object> entry : filterSpec.entrySet()) {
                System.out.println(entry.getKey());
                checkFilterResult(filterSpec, entry.getKey(), SortUtil.readField(a, entry.getKey(), "."));
            }
        }
    }
    
    /**
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDeviceListService
     * @see com.lexmark.service.impl.real.domain.AccountAssetFavorites
     * @see #testRetrieveDeviceList_QA_favList
     */
    @Test
    public void testFilter_fav_byOne() throws Exception {
        Map<String, Object> filterSpec = new LinkedHashMap<String, Object>();
        filterSpec.put("assetTag", "1000");
        filterSpec.put("serialNumber", "CIS");
        filterSpec.put("installAddress.addressName", "Install Address Name");
        filterSpec.put("installAddress.addressLine1", "Install Address Line 1");
        filterSpec.put("installAddress.state", "Install Address State");
        filterSpec.put("installAddress.city", "Install Address City");
        filterSpec.put("installAddress.province", "Install Address Province");
        filterSpec.put("installAddress.country", "Install Address Country");
        filterSpec.put("assetContact.firstName", "Primary Contact First Name");
        filterSpec.put("assetContact.lastName", "Primary Contact Last Name");
        filterSpec.put("assetContact.emailAddress", "Primary Contact Email Address");
        filterSpec.put("assetContact.workPhone", "Primary Contact Work Phone");
        filterSpec.put("productTLI", "250");
        filterSpec.put("hostName", "Host Name");
        filterSpec.put("modelNumber", "061");
        filterSpec.put("ipAddress", "0.1");
        filterSpec.put("devicePhase", "Accepted");
        filterSpec.put("macAddress", "__macAddress");
        filterSpec.put("productLine", "T64");
//        filterSpec.put("installPostalCode", "Install Postal Zip"); // an error

        for (Map.Entry<String, Object> entry : filterSpec.entrySet()) {
            AssetListResult r = _retrieveDeviceListFav(toMap(entry));
            for (Asset a : r.getAssets()) {
                System.out.println("~> " + entry.getKey());
                checkFilterResult(filterSpec, entry.getKey(), SortUtil.readField(a, entry.getKey(), "."));
//            checkFilterResult(filterSpec, "assetContact.workPhone", a.getAssetContact().getWorkPhone());
            }
        }
    }    
    

    private AssetListResult _retrieveDeviceList(Map<String, Object> filterCriteria) throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("1-52Y7PN");
        contract.setMdmLevel("Siebel");
//        contract.setAgreementId("1");
        contract.setContactId("1-1LUIPWR");
        contract.setAssetType("ALL");
        contract.setChlNodeId("");

        contract.setNewQueryIndicator(true);
        contract.setSessionHandle(crmSessionHandle);

        contract.setStartRecordNumber(0);
        contract.setIncrement(20);
        contract.setFilterCriteria(filterCriteria);

        return service.retrieveDeviceList(contract);
    }
    
    private AssetListResult _retrieveDeviceListFav(Map<String, Object> filterCriteria) throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setSessionHandle(crmSessionHandle);
        contract.setFavoriteFlag(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(20);
        
        
        contract.setMdmId("023058159");
        contract.setMdmLevel("Domestic");
        contract.setContactId("1-42MX1P9");
        contract.setAssetType("ALL");
        contract.setChlNodeId("");
        contract.setNewQueryIndicator(true);
        contract.setFilterCriteria(filterCriteria);

        return service.retrieveDeviceList(contract);
    }    
    

    /**
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetDo()
     * 
     */
    @Test
    public void testRetrieveDeviceList_withData() throws Exception {
        String ns = "[retrieveDeviceDetail] ";
        AssetListResult r = _retrieveDeviceList(null);
        MiscTest.print(ns, r.getAssets());
        System.out.println(ns + "result.getTotalCount()=" + r.getTotalCount());
        System.out.println("=====");
        MiscTest.print(ns, r.getAssets(), "assetContact:str");
    }
    
    @Test
    public void testRetrieveDeviceList_QA_favList() throws Exception {
        AssetListResult r = _retrieveDeviceListFav(null);
        MiscTest.print("[assets] ", r.getAssets());
        System.out.println("result.getTotalCount()=" + r.getTotalCount());
    }
    
    @Test
    public void queryAccountAssetFavorites() throws Exception {
        MiscTest.sampleSiebelQuery(AccountAssetFavorites.class,
                "EXISTS([LXK MPS Ent Domestic DUNS] = '023058159' AND [LXK MPS Ent MDM Level] = 'Siebel') AND [LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '1-42MX1P9' AND EXISTS ([LXK MPS Ent Type] ~LIKE 'Consumable*')"
                , 5);
    }
}
