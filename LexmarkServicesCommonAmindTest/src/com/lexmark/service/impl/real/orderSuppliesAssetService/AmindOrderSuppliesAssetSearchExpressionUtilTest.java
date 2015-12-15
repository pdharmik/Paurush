package com.lexmark.service.impl.real.orderSuppliesAssetService;


import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetSearchExpressionUtil.buildAssetSearchExpression;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lexmark.contract.AssetListContract;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.util.MockDataManager;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-04
 */
public class AmindOrderSuppliesAssetSearchExpressionUtilTest {

    @Test
    public void testBuildAssetSearchExpression_Given_mdmId_and_agreementId() throws Exception {
        AssetListContract contract = newAssetListContract("12", "3");
        MockDataManager mockDataManager = new MockDataManager();
        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [mdmLevel5AccountId]='12' AND [LXK SW MDM Account Level] = 'Siebel'"; 
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager,  null));
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
        contract.setContactId("contact-id-2");
        
        MockDataManager mockDataManager = new MockDataManager();
        Map<String, String> fieldMap = stringMap("lxk-f1", "sbl-f1", 
                                                  "lxk-f2", "sbl-f2");
        Map<String, String> boFavFieldMap = stringMap("lxk-f1", "sbl-fav-f1",
                                                      "lxk-f2", "sbl-fav-f2");
        String actual = buildAssetSearchExpression(contract, mockDataManager, fieldMap);
         String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [null] = 'mock-mdmId' AND [LXK SW MDM Account Level] = 'Siebel' AND ([sbl-f2] ~LIKE '*lxk-f2-value*' AND [sbl-f1] ~LIKE '*lxk-f1-value*')"; 
        assertEquals(expected, actual);
    }
        
    @SuppressWarnings("unchecked")
    private static Map<String, String> stringMap(String... keysAndValues) {
        return (Map<String, String>) MiscTest.newHashMap(keysAndValues);
    }    
    
    @Test
    public void testBuildAssetSearchExpression_Given_only_mdmId() throws Exception {
        AssetListContract contract = newAssetListContract("12");
        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [mdmLevel5AccountId]='12' AND [LXK SW MDM Account Level] = 'Siebel'";
        assertEquals(expected, buildAssetSearchExpression(contract, null, null));
    }
    
    @Test(expected=SiebelCrmServiceException.class)
    public void testBuildAssetSearchExpression_Given_mdmId_and_chlNodeId_without_parentChain() throws Exception {
        AssetListContract contract = newAssetListContract("12");
        contract.setChlNodeId("14");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        mockDataManager.setQueryByIdResult(chlDo);
        assertEquals("fail", buildAssetSearchExpression(contract, mockDataManager, null));
    }
    
    @Test
    public void testBuildAssetSearchExpression_Given_mdmId_and_chl_with_parentChain() throws Exception {
        AssetListContract contract = newAssetListContract("12");
        contract.setChlNodeId("14");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        mockDataManager.setQueryByIdResult(chlDo);
        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [CHL Parent Chain] LIKE 'mock-parentChain*'";
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager, null));
    }
    
    @Test
    public void testBuildAssetSearchExpression_Given_chl_with_parentChain_with_topLevelAccountId() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setChlNodeId("14");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        chlDo.setTopLevelAccountId("122");
        mockDataManager.setQueryByIdResult(chlDo);
        
        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [Owner Account Id] = '122' AND [CHL Parent Chain] LIKE 'mock-parentChain*'";
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager, null));
    }
    
    @Test
    public void testBuildAssetSearchExpression_Given_assetType_and_chl_with_parentChain_with_topLevelAccountId() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setChlNodeId("14");
        contract.setAssetType("MPS");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        chlDo.setTopLevelAccountId("122");
        mockDataManager.setQueryByIdResult(chlDo);

        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [Owner Account Id] = '122' AND [CHL Parent Chain] LIKE 'mock-parentChain*'";
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager, null));
    }
   
    
    @Test
    public void testBuildAssetSearchExpression_Given_favoriteFlag_and_chl_with_parentChain_with_topLevelAccountId() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setMdmId("mock-mdmId");
        contract.setMdmLevel("Siebel");
        contract.setChlNodeId("14");
        contract.setAssetType("MPS");
        contract.setFavoriteFlag(true);
        contract.setContactId("mock-contact-id");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        chlDo.setTopLevelAccountId("122");
        mockDataManager.setQueryByIdResult(chlDo);

        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [mdmLevel5AccountId]='mock-mdmId' AND [LXK SW MDM Account Level] = 'Siebel'";
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager, null));
    }
    
    
    @Test
    public void testBuildAssetSearchExpression_Given_chl_with_parentChain_without_topLevelAccountId() throws Exception {
        AssetListContract contract = new AssetListContract();
        contract.setChlNodeId("14");
        MockDataManager mockDataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        mockDataManager.setQueryByIdResult(chlDo);
        
        String expected = " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*') AND [CHL Parent Chain] LIKE 'mock-parentChain*'";
        assertEquals(expected, buildAssetSearchExpression(contract, mockDataManager, null));
    }
    
    private AssetListContract newAssetListContract(String mdmId, String agreementId) {
        AssetListContract alc = new AssetListContract();
        alc.setMdmId(mdmId);
        alc.setMdmLevel("Siebel");
        return alc;
    }

    private AssetListContract newAssetListContract(String mdmId) {
        AssetListContract alc = new AssetListContract();
        alc.setMdmId(mdmId);
        alc.setMdmLevel("Siebel");
        return alc;
    } 
}
