package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.SortUtil.checkSortOrder;

import java.util.Arrays;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.AmindServiceTest;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;
import com.lexmark.service.impl.real.util.TestErrors;
import com.lexmark.util.LangUtil;

/**
 * @see  com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTestSuite
 * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-09-18
 */
public class AssetListServiceSortTest extends AmindServiceTest {
    
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
    public void testSortOrder_QA_AllFields() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath>: if empty, the same as the <portalField>
                {"assetTag",                    ""}, 
                {"serialNumber",                ""},
                {"installAddress.addressName",  ""},
                {"installAddress.addressLine1", ""},
                {"installAddress.state",        ""},
                {"installAddress.city",         ""},
                {"installAddress.province",     ""},
                {"installAddress.postalCode",   ""},
                {"installAddress.country",      ""},
                {"assetContact.firstName",      ""},
                {"assetContact.lastName",       ""},
                {"assetContact.emailAddress",   ""},
                {"assetContact.workPhone",      ""},
                {"productTLI",                  ""},
                {"productLine",                 ""},
//                {"mdmLevel1AccountId",          ""},
//                {"mdmLevel2AccountId",          ""},
//                {"mdmLevel3AccountId",          ""},
//                {"mdmLevel4AccountId",          ""},
//                {"mdmLevel5AccountId",          ""},
//                {"accountTransFlag",            ""},
                {"modelNumber",                 ""},
                {"hostName",                    ""},
                {"ipAddress",                   ""},
                {"deviceTag",                   ""},
                {"devicePhase",                 ""},
                {"macAddress",                  ""},
                {"createDate",                  ""},
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], LangUtil.isNotEmpty(field[1]) ? field[1]: field[0], ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                    AssetListResult result = retrieveAllDeviceList_QA(false, sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_QA_FavFields() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath>: if empty, the same as the <portalField>
                {"assetTag",                    ""},
                {"serialNumber",                ""},
                {"installAddress.addressName",  ""},
                {"installAddress.addressLine1", ""},
                {"installAddress.state",        ""},
                {"installAddress.city",         ""},
                {"installAddress.province",     ""},
                {"installAddress.country",      ""},
                {"assetContact.firstName",      ""},
                {"assetContact.lastName",       ""},
                {"assetContact.emailAddress",   ""},
                {"assetContact.workPhone",      ""},
                {"productTLI",                  ""},
                {"hostName",                    ""},
                {"modelNumber",                 ""},
                {"ipAddress",                   ""},
                {"deviceTag",                   ""},
                {"devicePhase",                 ""},
                {"macAddress",                  ""},
                {"productLine",                 ""},
                {"installAddress.postalCode",   ""},
//                {"mdmLevel5AccountId",          ""},
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], LangUtil.isNotEmpty(field[1]) ? field[1]: field[0], ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                    AssetListResult result = retrieveAllDeviceList_QA(true, sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }   
    
    
    @Test
    public void testSortOrder_QA_AllFields_selected() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath>: if empty, the same as the <portalField>
                {"installAddress.addressName",  ""}, 
//                {"productTLI",                  ""}, 
//                {"productLine",                 ""}, 
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], LangUtil.isNotEmpty(field[1]) ? field[1]: field[0], ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                    AssetListResult result = retrieveAllDeviceList_QA(false, sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    @Test
    public void testSortOrder_QA_FavFields_selected() throws Exception {
        String[][] sortFields = {
                // format: <portalField>, <aMind field xpath>: if empty, the same as the <portalField>
                {"installAddress.addressName",  ""},
//                {"productTLI",                  ""}, 
//                {"productLine",                 ""}, 
        };
        
        TestErrors errors = new TestErrors();
        for (String[] field:  sortFields) {
            for (Boolean ascendingOrder : Arrays.asList(Boolean.TRUE, Boolean.FALSE)) {
                SortCriteria sortCriteria = new SortCriteria(field[0], LangUtil.isNotEmpty(field[1]) ? field[1]: field[0], ascendingOrder, true);
                System.out.printf("***** processing %s\n",  str(field));
                try {
                    AssetListResult result = retrieveAllDeviceList_QA(true, sortCriteria.toMap());
                    checkSortOrder(result.getAssets(), sortCriteria);
                } catch (Throwable ex) {
                    errors.add(ex, "" + sortCriteria);
                }
            }
        }
        errors.check();
    }
    
    private AssetListResult retrieveAllDeviceList_QA(boolean favFlag, Map<String, Object> sortCriteria) {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Domestic");
        contract.setMdmId("023058159");
        contract.setNewQueryIndicator(true);
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setContactId("1-42MX1P9");
        contract.setEntitlementEndDate("09/19/2012 05:30:00");
        contract.setFavoriteFlag(favFlag);
        contract.setSortCriteria(sortCriteria);
        
        return new AssetListService(session, crmSessionHandle, contract).retrieveAllDeviceList();
    }
    
    private AssetListResult retrieveAllDeviceList_MPS_QA(boolean favFlag, Map<String, Object> sortCriteria) {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Legal");
        contract.setMdmId("5989");
        contract.setStartRecordNumber(0);
        contract.setIncrement(40);
        contract.setContactId("1-50WCXE5");
        contract.setEntitlementEndDate("06/19/2013");
        contract.setFavoriteFlag(false);
        contract.setSortCriteria(sortCriteria);
        contract.setLoadAllFlag(false);
        
        return new AssetListService(session, crmSessionHandle, contract).retrieveAllDeviceList();
    }
    
    private AssetListResult retrieveAllDeviceList_MPS_QA_defect7786(boolean favFlag, Map<String, Object> sortCriteria) {
        AssetListContract contract =  new AssetListContract();
        contract.setNewQueryIndicator(true);
        contract.setMdmLevel("Siebel");
        contract.setMdmId("1-YFNVUX");
        contract.setStartRecordNumber(0);
        contract.setIncrement(1);
        contract.setContactId("1-5HR5CYV");
        contract.setEntitlementEndDate("06/19/2013");
        contract.setFavoriteFlag(false);
        contract.setSortCriteria(sortCriteria);
        contract.setLoadAllFlag(false);
        
        return new AssetListService(session, crmSessionHandle, contract).retrieveAllDeviceList();
    }
    
    @Test
    public void retrieveAllDeviceList_MPS_QA_defect7786() throws Exception {
        AssetListResult result = retrieveAllDeviceList_MPS_QA_defect7786(false, null);
        MiscTest.print(result.getAssets());
        System.out.println("totalCount=" + result.getTotalCount());
    }
    
    @Test
    public void retrieveAllDeviceList_MPS_QA() throws Exception {
        AssetListResult result = retrieveAllDeviceList_MPS_QA(false, null);
        MiscTest.print(result.getAssets());
        System.out.println("totalCount=" + result.getTotalCount());
    }
   
    @Test
    public void test_retrieveAllDeviceList_QA() throws Exception {
        AssetListResult result = retrieveAllDeviceList_QA(false, null);
        MiscTest.print(result.getAssets());
    }
    
    @Test
    public void test_retrieveAllDeviceList_QA_withFavFlag() throws Exception {
        AssetListResult result = retrieveAllDeviceList_QA(true, null);
        MiscTest.print(result.getAssets());
    }
}
