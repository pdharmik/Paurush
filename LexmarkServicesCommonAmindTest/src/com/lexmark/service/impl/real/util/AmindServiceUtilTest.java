package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildAssetTypeExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildChlNodeExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSimpleCriteria;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSimpleMdmSearchExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSortString;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildmdmSearchExpression;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.buildSearchCriteria;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.domain.CHLDo;

/**
 * @author vpetruchok
 * @version 1.0, Mar 20, 2012
 */
public class AmindServiceUtilTest {

    @Test
    public void testBuildCriteria() {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
        fieldMap.put("serialNumber", "Serial Number");
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", "Owner Asset Number");
            assertEquals(" AND ([Owner Asset Number] ~LIKE '*Owner Asset Number*')",
                          buildCriteria(filterMap, fieldMap, true, true));
        }
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", "Owner Asset Number");
            filterMap.put("serialNumber", "My Serial Number");
            assertEquals(" AND ([Owner Asset Number] LIKE '*Owner Asset Number*' AND [Serial Number] LIKE '*My Serial Number*')",
                         buildCriteria(filterMap, fieldMap, true, false));
        }
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("noSuchKey", "Owner Asset Number");
            assertEquals("", buildCriteria(filterMap, fieldMap, true, false));
        }
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", "" /* no value */);
            assertEquals("", buildCriteria(filterMap, fieldMap, true, false));
        }
        
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", "  abc  " /* trailing spaces */);
            assertEquals(" AND ([Owner Asset Number] LIKE '*abc*')", buildCriteria(filterMap, fieldMap, true, false));
        }
        
    }

    @Test
    public void testBuildmdmSearchExpression() throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
        fieldMap.put("serialNumber", "Serial Number");
        
        assertEquals("[null] = 'my-mdmId' AND [null] = 'Y' AND [LXK SW Agreement Account MDM Level] ='Siebel'",
                      buildmdmSearchExpression( "my-mdmId", "Global" /*mdmLevel*/, fieldMap, true, true));
        
        fieldMap.put("mdmLevel1AccountId", "mdmId-siebel-field");
        assertEquals("[mdmId-siebel-field] = 'my-mdmId' AND [LXK SW Agreement Account MDM Level] ='Siebel'",
                      buildmdmSearchExpression( "my-mdmId", "Global" /*mdmLevel*/, fieldMap, false, true));
        
        assertEquals("[mdmId-siebel-field] = 'my-mdmId'",
                buildmdmSearchExpression( "my-mdmId", "Global" /*mdmLevel*/, fieldMap, false, false));

    }

    @Test
    public void testBuildSortString() throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
        fieldMap.put("serialNumber", "Serial Number");
        
        Map<String, Object> sortFieldMap = new HashMap<String, Object>();
        sortFieldMap.put("assetTag", "ASCENDING");
        sortFieldMap.put("serialNumber", "DESCENDING");        
        
        assertEquals("Serial Number(DESCENDING),Owner Asset Number(ASCENDING)",
            buildSortString(sortFieldMap, fieldMap));
    }
    
    @Test
    public void testBuildSortString_oneField() throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
//        fieldMap.put("serialNumber", "Serial Number");
        
        Map<String, Object> sortFieldMap = new HashMap<String, Object>(); 
        sortFieldMap.put("assetTag", "ASCENDING");
//        sortFieldMap.put("serialNumber", "DESCENDING");        
        
        assertEquals("Owner Asset Number(ASCENDING)",
            buildSortString(sortFieldMap, fieldMap));
    }

    @Test
    public void testBuildSortString_manyFields_bug() throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
        fieldMap.put("serialNumber", "Serial Number");
        
        Map<String, Object> sortFieldMap = new HashMap<String, Object>(); 
        sortFieldMap.put("assetTag", "ASCENDING");
        sortFieldMap.put("serialNumber", "DESCENDING");        
        
        assertEquals("Owner Asset Number(ASCENDING),Serial Number(DESCENDING)",
            buildSortString(sortFieldMap, fieldMap));
    }
    
    @Test
    public void testBuildChlNodeExpression() throws Exception {
        String chlNodeId =  "mock-chlNodeId";
        MockDataManager dataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        dataManager.setQueryByIdResult(chlDo);
//        boolean serviceRequest = false;
        String assetType  =  "mock-assetType";
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*')" ,
                  buildChlNodeExpression(chlNodeId, dataManager, false, assetType));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*')" ,
                  buildChlNodeExpression(chlNodeId, dataManager, true, assetType));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*')" ,
                  buildChlNodeExpression(null, dataManager, false, null));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' AND [LXK SW Agreement Type] = 'MPS Agreement')",
                  buildChlNodeExpression(null, dataManager, false, "MPS"));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' AND [LXK SW Agreement Type] = 'CSS Agreement')",
                  buildChlNodeExpression(null, dataManager, false, "CSS"));
        
        chlDo.setTopLevelAccountId("mock-topLevelAccountId");
        assertEquals("[Owner Account Id]='mock-topLevelAccountId' AND EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' AND [LXK SW Agreement Type] = 'CSS Agreement')",
                  buildChlNodeExpression(null, dataManager, false, "CSS"));
    }
    
    @Test
    public void testBuildChlNodeExpression2() throws Exception {
        String chlNodeId =  "mock-chlNodeId";
        MockDataManager dataManager = new MockDataManager();
        CHLDo chlDo = new CHLDo();
        chlDo.setParentChain("mock-parentChain");
        dataManager.setQueryByIdResult(chlDo);
//        boolean serviceRequest = false;
        String assetType  =  "MPS";
        String agreementType = "LXK SW Agreement Type"; 
        String assetTypeExpr = buildAssetTypeExpression(assetType, agreementType);
        String parentChainStr = "LXK SW Covered Asset CHL Parent Chain";
        String ownerAccountId = "Owner Account Id";  
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*')" ,
                  buildChlNodeExpression(chlNodeId, dataManager, parentChainStr, ownerAccountId, null));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' AND [LXK SW Agreement Type] = 'MPS Agreement')",
                  buildChlNodeExpression(null, dataManager,  parentChainStr, ownerAccountId, assetTypeExpr));
        
        assertEquals("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE 'mock-parentChain*' AND [LXK SW Agreement Type] = 'CSS Agreement')",
                  buildChlNodeExpression(null, dataManager,  parentChainStr, ownerAccountId, buildAssetTypeExpression("CSS", "LXK SW Agreement Type")));
    }

    @Test
    public void testBuildSimpleMdmSearchExpression() throws Exception {
           assertEquals("[mdmLevel5AccountId]='mdmdId-a'",
                           buildSimpleMdmSearchExpression("mdmdId-a",  AmindServiceUtil.SIEBEL_MDMLEVEL)); 
    }

    @Test
    public void testBuildAssetTypeExpression() throws Exception {
        assertEquals("", buildAssetTypeExpression("abc", "abc"));
        assertEquals("[aggrementTypeField] = 'MPS Agreement'", buildAssetTypeExpression("MPS", "aggrementTypeField"));
    }

    @Test
    public void testBuildSearchCriteria() throws Exception {
        {
            Map<String, String> fieldMap = (Map<String, String>) MiscTest.newHashMap("lxk-fieldname1", "sbl-fieldname1", 
                    "lxk-fieldname2",  "sbl-fieldname2");
            Map<String, Object> argSearchMap = (Map<String, Object>) MiscTest.newHashMap("lxk-fieldname1", "lxk-fieldvalue1",
                    "lxk-fieldname2", "lxk-fieldvalue2");
            assertEquals(" AND ([sbl-fieldname2] ~ LIKE 'lxk-fieldvalue2*' OR [sbl-fieldname1] ~ LIKE 'lxk-fieldvalue1*')",
                    buildSearchCriteria(argSearchMap, fieldMap));
        }
        
        {
            Map<String, String> fieldMap = (Map<String, String>) MiscTest.newHashMap("lxk-fieldname1", "sbl-fieldname1", 
                    "lxk-fieldname2",  "sbl-fieldname2");
            Map<String, Object> argSearchMap = (Map<String, Object>) MiscTest.newHashMap("lxk-fieldname1", "lxk-fieldvalue1 ",
                    "lxk-fieldname2", "lxk-fieldvalue2   ");
            assertEquals(" AND ([sbl-fieldname2] ~ LIKE 'lxk-fieldvalue2*' OR [sbl-fieldname1] ~ LIKE 'lxk-fieldvalue1*')",
                    buildSearchCriteria(argSearchMap, fieldMap));
        }
    }

    @Test
    public void testBuildSimpleCriteria() throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("assetTag", "Owner Asset Number");
        fieldMap.put("serialNumber", "Serial Number");        
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", "" /* no value */);
            assertEquals("", buildSimpleCriteria(filterMap, fieldMap, true, false));
        }
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag-notExists", "1");
            assertEquals("", buildSimpleCriteria(filterMap, fieldMap, true, false));
        }
        
        {
            Map<String, Object> filterMap = new LinkedHashMap<String, Object>();
            filterMap.put("assetTag", " 1 ");
            assertEquals("[Owner Asset Number] LIKE '*1*'", buildSimpleCriteria(filterMap, fieldMap, true, false));
        }
    }
    
}
