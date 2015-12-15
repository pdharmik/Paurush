package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.SortUtil.checkSortOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.service.impl.real.SortUtil.AssetProvider;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-05
 */
public class AmindContractedDeviceServiceRetrieveDeviceListSortTest extends AmindServiceTest {

    static AmindContractedDeviceService service;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        logger.info("in setUp()");
        service = new AmindContractedDeviceService();
        service.setSessionFactory(statelessSessionFactory);
    }

    /**
     * Last check date with MPS_OrderManagement_ChangeManagement_03_14_12.docx -
     * Google Docs: 2012-06-19
     * 
     * @see com.lexmark.service.impl.real.util.AmindServiceUtilTest#testBuildSortString_searchOrder()
     * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceDoClassesTest
     */
    @Test
    public void testSortOrder() throws Exception {
        String[] sortFields = {
                "serialNumber",
                "assetTag",
                "productLine",
                "modelNumber",
                "hostName",
                "ipAddress",
                "deviceTag",
                "devicePhase",

                "installAddress.addressName",
                "installAddress.addressLine1",
                "installAddress.state",
                "installAddress.city",
                "installAddress.postalCode",
                "installAddress.country",

                "assetContact.firstName",
                "assetContact.lastName",
                "assetContact.emailAddress",
                "assetContact.workPhone"
        };

        SortUtil.checkSortOrder2(contractedDeviceProvider(), sortFields);
    }

    private AssetProvider contractedDeviceProvider() {
        return new AssetProvider() {
            @Override
            public AssetListResult get(SortCriteria sortCriteria) throws Exception {
                return _retrieveDeviceList(sortCriteria.toMap());
            }
        };
    }
    
    @Test
    public void testSortOrder_QA_Defect2640() throws Exception {
        String[] sortFields = {
                "serialNumber",
                "assetTag",
                "productLine",
                "modelNumber",
                "hostName",
                "ipAddress",
                "deviceTag",
                "devicePhase",

                "installAddress.addressName",
                "installAddress.addressLine1",
                "installAddress.state",
                "installAddress.city",
                "installAddress.postalCode",
                "installAddress.country",

                "assetContact.firstName",
                "assetContact.lastName",
                "assetContact.emailAddress",
                "assetContact.workPhone"
        };

        SortUtil.checkSortOrder2(contractedDeviceProvider_QA_Defect2640(), sortFields);
    }
    
    private AssetProvider contractedDeviceProvider_QA_Defect2640() {
        return new AssetProvider() {
            @Override
            public AssetListResult get(final SortCriteria sortCriteria) throws Exception {
                
                String mdmId = "023058159";
                String mdmLevel = LexmarkConstants.MDM_LEVEL_GLOBAL; 
                String contactId ="1-42NMBKT";
                String chlNodeId = "";
                String assetType = "ALL";
                boolean favoriteFlag = true;
                boolean newQueryIndicator = true;
                int startIndex = 0;
                int numRecords = 40;

                AssetListContract contract = new AssetListContract();
                contract.setSessionHandle(crmSessionHandle);
                contract.setMdmId(mdmId);
                contract.setMdmLevel(mdmLevel);
                contract.setStartRecordNumber(startIndex);
                contract.setIncrement(numRecords);
                contract.setNewQueryIndicator(newQueryIndicator);
                contract.setContactId(contactId);
                contract.setFavoriteFlag(favoriteFlag);
                contract.setAssetType(assetType);
                contract.setChlNodeId(chlNodeId);
                contract.setLoadAllFlag(false);
                contract.setSortCriteria(sortCriteria.toMap());
                return service.retrieveDeviceList(contract);                
            }
        };
    }    
    

    @Test
    public void testSortOrderByAssetTag() throws Exception {
        SortCriteria[] sortCriterias = {
                new SortCriteria("assetTag", true),
                new SortCriteria("assetTag", false)
        };

        for (SortCriteria sortCriteria : sortCriterias) {
            AssetListResult result = _retrieveDeviceList(sortCriteria.toMap());
            checkSortOrder(result.getAssets(), sortCriteria);
        }
    }

    private AssetListResult _retrieveDeviceList(Map<String, Object> sortCriteria) throws Exception {
        String mdmId = "12864";
        String mdmLevel = LexmarkConstants.MDM_LEVEL_LEGAL;
        String contactId = "<mock-not-used-contactId>";
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
        assetContract.setSortCriteria(sortCriteria);
        return service.retrieveDeviceList(assetContract);
    }

    private AssetListResult _retrieveDeviceListMpsQa(Map<String, Object> sortCriteria) throws Exception {
        String mdmId = "623331717";
        String mdmLevel = LexmarkConstants.MDM_LEVEL_GLOBAL;
        String contactId = "1-BGJ7-279";
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
        assetContract.setSortCriteria(sortCriteria);
        return service.retrieveDeviceList(assetContract);
    }

    @Test
    public void testRetrieveDeviceListMpsQa() throws Exception {
        AssetListResult result = _retrieveDeviceListMpsQa(null);
        print(result);
    }

    public static void print(AssetListResult result) {
        if (result == null) {
            System.out.println("result is null");
            return;
        }
        List<Asset> assets = result.getAssets();
        for (Asset a : assets) {
            System.out.println(str(a));
        }
        System.out.println("result.getTotalCount()=" + result.getTotalCount());
    }

    @Test
    public void testSortOrder_MpsQa() throws Exception {
        String[] sortFields = {
                "installAddress.addressName2",
                "installAddress.addressLine1",
                "assetContact.workPhone"
        };

        SortUtil.checkSortOrder2(new AssetProvider() {
            @Override
            public AssetListResult get(SortCriteria sortCriteria) throws Exception {
                return _retrieveDeviceListMpsQa(sortCriteria.toMap());
            }
        }, sortFields);
    }

    @Test
    public void testRetrieveDeviceDetail_withData() throws Exception {
        String ns = "[retrieveDeviceDetail] ";
        String assetId = "1-5T4UTL";
        AssetContract contract = new AssetContract();
        contract.setAssetId(assetId);
        //        contract.setContactId("No Match Row Id");

        AssetResult r = service.retrieveDeviceDetail(contract);
        logger.info(ns + "result=" + r);
        logger.info(ns + "result.getAsset()=" + r.getAsset());
        assertTrue(r.getAsset() != null);
        assertEquals(assetId, r.getAsset().getAssetId());
        MiscTest.showStructure(r.getAsset());
    }
    
    
    @Test
    public void testRetrieveDeviceDetail_withData2() throws Exception {
        AssetContract contract = new AssetContract();
        contract.setAssetId("1-8JA5-1696");

        AssetResult result = service.retrieveDeviceDetail(contract);
        
        System.out.println("Asset is null: " + result.getAsset() == null);
    }
}
