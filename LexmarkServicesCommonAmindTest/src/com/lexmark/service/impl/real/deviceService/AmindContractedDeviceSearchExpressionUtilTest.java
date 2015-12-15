package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceSearchExpressionUtil.assetTypeFilterExpr;
import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceSearchExpressionUtil.filterCriteriaExpr;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.amind.data.service.IDataManager;
import com.lexmark.contract.AssetListContract;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.util.MockDataManager;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-11
 */
public class AmindContractedDeviceSearchExpressionUtilTest {

    @Test
    public void testBuildAssetSearchExpression_chlNodeId() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");

        AssetListContract contract = c;
        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_mdm() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Global");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([null] = 'mock-mdmId' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_mdm_and_favorite() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Global");
        contract.setFavoriteFlag(true);
        contract.setContactId("contactId1");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'contactId1'";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testBuildAssetSearchExpression_without_filterCriteria() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Global");
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
        filterCriteria.put("lxk-f1", "lxk-f1-value");
        filterCriteria.put("lxk-f2", "lxk-f2-value");
//        contract.setFilterCriteria(filterCriteria);
        contract.setFavoriteFlag(false);

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = (Map<String, String>) 
                MiscTest.newHashMap("lxk-f1", "v1-fieldMap", 
                                    "lxk-f2", "v2-fieldMap");
        Map<String, String> boFavFieldMap = stringMap("lxk-f1", "v1-favfieldMap",
                                                      "lxk-f2", "v2-favfieldMap");
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([null] = 'mock-mdmId' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void testBuildAssetSearchExpression_filterCriteria() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Global");
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
        filterCriteria.put("lxk-f1", "lxk-f1-value");
        filterCriteria.put("lxk-f2", "lxk-f2-value");
        contract.setFilterCriteria(filterCriteria);
        contract.setFavoriteFlag(false);

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = (Map<String, String>) 
                MiscTest.newHashMap("lxk-f1", "v1-fieldMap", 
                                    "lxk-f2", "v2-fieldMap");
        Map<String, String> boFavFieldMap = stringMap("lxk-f1", "v1-favfieldMap",
                                                      "lxk-f2", "v2-favfieldMap");
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([null] = 'mock-mdmId' AND [LXK SW Agreement Account MDM Level] ='Siebel') AND ([v2-fieldMap] ~LIKE '*lxk-f2-value*' AND [v1-fieldMap] ~LIKE '*lxk-f1-value*') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void testBuildAssetSearchExpression_filterCriteria_favoriteFlag() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Global");
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
        filterCriteria.put("lxk-f1", "lxk-f1-value");
        filterCriteria.put("lxk-f2", "lxk-f2-value");
        contract.setFilterCriteria(filterCriteria);
        contract.setFavoriteFlag(true);
        contract.setContactId("contactId1");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = (Map<String, String>) 
                MiscTest.newHashMap("lxk-f1", "v1-fieldMap", 
                                    "lxk-f2", "v2-fieldMap");
        Map<String, String> boFavFieldMap = stringMap("lxk-f1", "v1-favfieldMap",
                                                      "lxk-f2", "v2-favfieldMap");
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(contract, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'contactId1' AND ([v2-favfieldMap] ~LIKE '*lxk-f2-value*' AND [v1-favfieldMap] ~LIKE '*lxk-f1-value*')";
        assertEquals(expected, actual);
    }
    
    private static MockDataManager mockDataManager() {
        MockDataManager mdm = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        mdm.setQueryByIdResult(chlDo);
        return mdm;
    }

    private Map<String, String> fieldMap() {
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("lxk-f1", "sbl-f1");
        return m;
    }

    private Map<String, String> favFieldMap() {
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("lxk-fav-f1", "sbl-fav-f1");
        return m;
    }
    
    
    @SuppressWarnings("unchecked")
    private static Map<String, String> stringMap(String... keysAndValues) {
        return (Map<String, String>) MiscTest.newHashMap(keysAndValues);
    }

    @Test
    public void testBuildAssetSearchExpression_searchCriteria_has_chlNodeId() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria = (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock");
        c.setSearchCriteria(searchCriteria);

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*'EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*') ) AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_searchCriteria_has_chlNodeId_and_chlNodeId2() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria =
                (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock",
                        "chlNodeId2", "searchCriteria-chlNodeId2-mock");
        c.setSearchCriteria(searchCriteria);

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' OR EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*') ) AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_searchCriteria_has_chlNodeId_and_chlNodeId2_and_filterCriteria_hasValue() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria =
                (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock",
                        "chlNodeId2", "searchCriteria-chlNodeId2-mock");
        c.setSearchCriteria(searchCriteria);

        Map<String, Object> filterCriteria =
                (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "filterCriteria-chlNodeId-mock",
                        "chlNodeId2", "filterCriteria-chlNodeId2-mock");
        c.setFilterCriteria(filterCriteria);

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' OR EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE '*mock-parentChain*') ) AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuildAssetSearchExpression_searchCriteria_has_chlNodeId_and_chlNodeId2_and_filterCriteria_hasValue_and_favFlag()
            throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria =
                (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock",
                        "chlNodeId2", "searchCriteria-chlNodeId2-mock",
                        "k1", "searchCriteria-v2");
        c.setSearchCriteria(searchCriteria);

        Map<String, Object> filterCriteria =
                (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "filterCriteria-chlNodeId-mock",
                        "chlNodeId2", "filterCriteria-chlNodeId2-mock",
                        "lxk-filter-key1", "lxk-filter-val1");
        c.setFilterCriteria(filterCriteria);
        c.setFavoriteFlag(true);
        c.setContactId("contactId1");
        c.setMdmId("mdmId-mock");
        c.setMdmLevel("Global");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = (Map<String, String>) MiscTest.newHashMap("k1", "v1-boFavFieldMap", "lxk-filter-key1", "sbl-key1"); 
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'contactId1' AND ([v1-boFavFieldMap] ~ LIKE 'searchCriteria-v2*') AND ([sbl-key1] ~LIKE '*lxk-filter-val1*')";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_favFlagIsTrue_mdmIdIsNotNull() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria = (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock");
        c.setSearchCriteria(searchCriteria);
        c.setFavoriteFlag(true);
        c.setContactId("cont1"); 
        c.setMdmId("mdmId-mock");
        c.setMdmLevel("Global");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'cont1'";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testBuildAssetSearchExpression_favFlagIsTrue_mdmIdIsNotNull_withFilterCriteria_withSearchString() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        c.setSearchCriteria((Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock"));
        c.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap("sbl-field1", " sbl-field1-filter-value"));
        c.setFavoriteFlag(true);
        c.setContactId("cont1"); 
        c.setMdmId("mdmId-mock");
        c.setMdmLevel("Global");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'cont1'";
        assertEquals(expected, actual);
    }    
    

    @Test
    public void testBuildAssetSearchExpression_favFlagIsTrue_mdmIdIsNotNull_assetTypeIsMPS() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria = (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock");
        c.setSearchCriteria(searchCriteria);
        c.setFavoriteFlag(true);
        c.setContactId("cont1"); 
        c.setMdmId("mdmId-mock");
        c.setMdmLevel("Global");
        c.setAssetType("MPS");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'cont1'";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_favFlagIsTrue_mdmIdIsNotNull_assetTypeIsCSS() throws Exception {
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        Map<String, Object> searchCriteria = (Map<String, Object>) MiscTest.newHashMap("chlNodeId", "searchCriteria-chlNodeId-mock");
        c.setSearchCriteria(searchCriteria);
        c.setFavoriteFlag(true);
        c.setContactId("mockcontact");
        
        c.setMdmId("mdmId-mock");
        c.setMdmLevel("Global");
        c.setAssetType("CSS");

        IDataManager dataManager = mockDataManager();
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> boFavFieldMap = favFieldMap();
        String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
        String expected = "[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'mockcontact'";
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildAssetSearchExpression_Exception() throws Exception {
        try {
            AssetListContract c = new AssetListContract();
            IDataManager dataManager = mockDataManager();
            Map<String, String> fieldMap = fieldMap();
            Map<String, String> boFavFieldMap = favFieldMap();
            String actual = AmindContractedDeviceSearchExpressionUtil.buildAssetSearchExpression(c, dataManager, fieldMap, boFavFieldMap);
            fail();
        } catch (Exception ok) {
        }
    }

    @Test
    public void testAssetTypeFilterExpr() throws Exception {
        {
            AssetListContract c = new AssetListContract();
            c.setAssetType("MPS");
            assertEquals(" AND [LXK SW Agreement Type] = 'MPS Agreement'", assetTypeFilterExpr(c));
        }

        {
            AssetListContract c = new AssetListContract();
            c.setAssetType("MPS-other");
            assertEquals("", assetTypeFilterExpr(c));
        }

    }

    @Test
    public void testFilterCriteriaExpr() throws Exception {
        Map<String, Object> filterCriteria = new HashMap<String, Object>();
        filterCriteria.put("k1", "filter-v1");
        filterCriteria.put("k2", "filter-v2");
        Map<String, String> fieldMap = fieldMap();
        Map<String, String> favFieldMap = favFieldMap();
        AssetListContract c = new AssetListContract();
        c.setChlNodeId("chlNodeId-1");
        c.setFavoriteFlag(true);
        c.setFilterCriteria(filterCriteria);
        
        favFieldMap.put("k1", "fav-v1"); 
        favFieldMap.put("k2", "fav-v2"); 
        assertEquals(" AND ([fav-v1] ~LIKE '*filter-v1*' AND [fav-v2] ~LIKE '*filter-v2*')",
                      filterCriteriaExpr(c, fieldMap, favFieldMap));
        
       String s  = filterCriteriaExpr(c, fieldMap, favFieldMap);
       StringBuilder sb = new StringBuilder(s);
       System.out.printf("1: `%s'\n2: `%s'\n", sb.toString(), sb.deleteCharAt(sb.length() - 1));
    
    }
    
    @Test
    public void testFavoriteListSearchExpression() throws Exception {
        {
            AssetListContract c = new AssetListContract();
            c.setContactId("contactId-1");
            assertEquals("[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = 'contactId-1'", AmindContractedDeviceSearchExpressionUtil.favoriteListSearchExpression(c));
        }
        
        {
            AssetListContract c = new AssetListContract();
            c.setContactId("");
            try {
                AmindContractedDeviceSearchExpressionUtil.favoriteListSearchExpression(c);
                fail();
            } catch (IllegalArgumentException ok) {
            }
        }
    }
}