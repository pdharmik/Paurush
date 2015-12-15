package com.lexmark.service.impl.real.requestService;


import static com.lexmark.service.impl.real.requestService.RequestTypeListService.siebelFilterExpr;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.RequestListContract;
import com.lexmark.service.impl.real.requestService.RequestTypeListService;

/**
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTest
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTest#testRetrieveRequestList_QA()
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-25
 */
public class RequestTypeListServiceTest {

    @Test
    public void testBuildRequestTypeSearchExpression() throws Exception {
        RequestListContract contract = new RequestListContract();
        contract.setMdmId("mock-mdmdId");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);
        contract.setAssetFavoriteFlag(false);
        Map<String, String> fieldMap = new HashMap<String, String>();
        RequestTypeListService listService = new RequestTypeListService(contract, fieldMap);
        String expected = "(([mdmLevel5AccountId]='mock-mdmdId') OR ([null] = 'mock-mdmdId'))";
        assertEquals(expected, listService.buildRequestTypeSearchExpression());

        contract.setAssetFavoriteFlag(true);
        String expected2 = "(([mdmLevel5AccountId]='mock-mdmdId') OR ([null] = 'mock-mdmdId'))";
        assertEquals(expected2, listService.buildRequestTypeSearchExpression());

        assertEquals(expected, expected2); // ? suspicious

        contract.setAssetFavoriteFlag(false);
        contract.setAssetType("MPS");
        String expected3 = "(([mdmLevel5AccountId]='mock-mdmdId')" +
                " OR ([null] = 'mock-mdmdId'))" +
                " AND [LXK MPS Agreement Type] = 'MPS Agreement'";
        assertEquals(expected3, listService.buildRequestTypeSearchExpression());

        contract.setAssetFavoriteFlag(false);
        contract.setAssetType("CSS");
        String expected4 = "(([mdmLevel5AccountId]='mock-mdmdId')" +
                " OR ([null] = 'mock-mdmdId'))" +
                " AND [LXK MPS Agreement Type] = 'CSS Agreement'";
        assertEquals(expected4, listService.buildRequestTypeSearchExpression());

        contract.setContactId("mock-contactId");
        String expected5 = "([LXK R SR Contact Id] ='mock-contactId' OR (([Contact Id] = 'mock-contactId'     AND [LXK R SR Contact Id] <> 'mock-contactId')) OR (([LXK MPS Alternate Contact Id] = 'mock-contactId'     AND [LXK R SR Contact Id] <>'mock-contactId'     AND [Contact Id] <> 'mock-contactId'))) AND [LXK MPS Agreement Type] = 'CSS Agreement'";
        assertEquals(expected5, new RequestTypeListService(contract, fieldMap).buildRequestTypeSearchExpression());
    }

    @Test
    public void testContactIdSearchExpr() throws Exception {
        String expected = "([LXK R SR Contact Id] ='mock-contactId' OR (([Contact Id] = 'mock-contactId'     AND [LXK R SR Contact Id] <> 'mock-contactId')) OR (([LXK MPS Alternate Contact Id] = 'mock-contactId'     AND [LXK R SR Contact Id] <>'mock-contactId'     AND [Contact Id] <> 'mock-contactId')))";
        assertEquals(expected, RequestTypeListService.contactIdSearchExpr("mock-contactId"));
    }

    @Test
        public void testSiebelFilterExpr() throws Exception {
            assertEquals("", siebelFilterExpr("requestType", null));
            assertEquals("([requestType] = 'v1')", siebelFilterExpr("requestType", Arrays.asList("v1")));
            assertEquals("([requestType] = 'v1' OR [requestType] = 'v2')", siebelFilterExpr("requestType", Arrays.asList("v1", "v2")));
            assertEquals("([requestType] = 'v1' OR [requestType] = 'v2' OR [requestType] = 'v3')",
                    siebelFilterExpr("requestType", Arrays.asList("v1", "v2", "v3")));
        }
    
    @Test
    public void testStringBuilder() throws Exception {
        assertEquals("", new StringBuilder().toString());
        
    }
}
