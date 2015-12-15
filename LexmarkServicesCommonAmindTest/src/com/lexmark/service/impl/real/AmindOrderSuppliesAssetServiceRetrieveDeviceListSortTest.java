package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.SortUtil.checkSortOrder;
import static com.lexmark.service.impl.real.SortUtil.checkSortOrder2;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.SortUtil.AssetProvider;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;
import com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap;
import com.lexmark.service.impl.real.util.TestErrors;

/**
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceRetrieveDeviceListSortTest
 * 
 * @author vpetruchok
 * @version 1.0, 2012-05-18
 */
public class AmindOrderSuppliesAssetServiceRetrieveDeviceListSortTest extends AmindServiceTest {

    static AmindOrderSuppliesAssetService service;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        logger.info("in setUp()");
        service = new AmindOrderSuppliesAssetService();
        service.setStatelessSessionFactory(statelessSessionFactory);
    }

    /**
     * Last check date with MPS_OrderManagement_Consumables_Assets_Orders_03_21_12.docx - Google Docs: 2012-06-19
     * 
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap#allFieldMap()
     */
    @Test
    public void testSortOrder_QA_All() throws Exception {
        List<String> sortFields = new ArrayList<String>();
        for (String key : OrderSuppliesAssetSiebelFieldMap.allFieldMap().keySet()) {
            if (key.startsWith("mdmLevel")
                  || key.equals("accountTransFlag")
                  || key.equals("createDate")) {
                continue;
            }
            sortFields.add(key);
        }
        
        TestErrors errors = new TestErrors();
        for (String field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field, ascendingOrder, true);
                System.out.printf("***** processing %s\n",  sortCriteria);
                try {
                    AssetListResult result = _retrieveDeviceList(sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    /**
     * 
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetDeviceListService#favoriteFieldMap() 
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap#allFavoriteFieldMap()
     * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListServiceSortTest#testSortOrder_QA_FavFields()
     */
    @Test
    public void testSortOrder_QA_Fav() throws Exception {
        List<String> sortFields = new ArrayList<String>();
        for (String key : OrderSuppliesAssetSiebelFieldMap.allFavoriteFieldMap().keySet()) {
            if (key.startsWith("mdmLevel")) {
                continue;
            }
            sortFields.add(key);
        }
        
        TestErrors errors = new TestErrors();
        for (String field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field, ascendingOrder, true);
                System.out.printf("***** processing %s\n",  sortCriteria);
                try {
                    AssetListResult result = _retrieveDeviceListFav(sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_Fav_lab() throws Exception {
        String[] sortFields = {
                "assetContact.firstName",
        };
        
        
        TestErrors errors = new TestErrors();
        for (String field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field, ascendingOrder, true);
                System.out.printf("***** processing %s\n",  sortCriteria);
                try {
                    AssetListResult result = _retrieveDeviceListFav(sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }    
    
    
    /**
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceDoClassesTest#queryOrderSuppliesAssetDo_withSort()
     * @throws Exception
     */
    @Test
    public void testSortOrder_issues() throws Exception {
        String[] sortFields = {
//                "serialNumber",
//                "productModel", // no such field
//                "ipAddress", 
//                "devicePhase",
                "installAddress.addressName",
//                "contractType", no such Field
                ""
            };
        
        
        TestErrors errors = new TestErrors();
        for (String field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field, ascendingOrder, true);
                System.out.printf("***** processing %s\n",  sortCriteria);
                try {
                    AssetListResult result = _retrieveDeviceList(sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_issues2() throws Exception {
        checkSortOrder2(consumableAssetProvider(), 
                "ipAddress", 
                "devicePhase",
                "installAddress.addressName",
                ""
        );
    }

    private AssetProvider consumableAssetProvider() {
        return new AssetProvider() {
            public AssetListResult get(SortCriteria sortCriteria) throws Exception {
                return _retrieveDeviceList(sortCriteria.toMap());
            }
        };
    }
    
    @Test
    public void testStringComparator() throws Exception {
        assertTrue("".compareTo("12") < 0 );
    }

    private static AssetListResult _retrieveDeviceList(Map<String, Object> sortCriterias) throws Exception {
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
        contract.setSortCriteria(sortCriterias);
//        contract.setFilterCriteria(filterCriteria);

        return service.retrieveDeviceList(contract);
    }

    @Test
    public void testRetrieveDeviceList_withData() throws Exception {
        String ns = "[retrieveDeviceDetail] ";
        AssetListResult r = _retrieveDeviceList(null);
        MiscTest.print(r.getAssets());
        System.out.println(ns + "result.getTotalCount()=" + r.getTotalCount());
    }

    @Test
    public void testRetrieveDeviceList_withSortMap() throws Exception {
        String ns = "[retrieveDeviceDetail.withSortMap] ";
        // [fieldname=installAddress.addressName, ascendingOrder=true]
        // {installAddress.addressName=ASCENDING}
        Map<String, Object> sortSpec = new HashMap<String, Object>();
        sortSpec.put("installAddress.addressName", "ASCENDING");
        //        AssetListResult r = _retrieveDeviceList(null);
        AssetListResult r = _retrieveDeviceList(sortSpec);
        MiscTest.print(r.getAssets());
        System.out.println(ns + "result.getTotalCount()=" + r.getTotalCount());
    }
    
    private AssetListResult _retrieveDeviceListFav(Map<String, Object> sortCriteria) throws Exception {
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
        contract.setSortCriteria(sortCriteria);

        return service.retrieveDeviceList(contract);
    }    
    
    
    @Test
    public void testRetrieveDeviceList_QA_favList() throws Exception {
        AssetListResult r = _retrieveDeviceListFav(null);
        MiscTest.print("[assets] ", r.getAssets());
        System.out.println("result.getTotalCount()=" + r.getTotalCount());
    }

}
