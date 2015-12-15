package com.lexmark.service.impl.real.partnerOrderService;

import static com.lexmark.service.impl.real.partnerOrderService.OrderServiceUtil.buildOrderListSearchExpression;
import static com.lexmark.service.impl.real.partnerOrderService.OrderServiceUtil.filterByCommonKeys;
import static com.lexmark.service.impl.real.partnerOrderService.OrderServiceUtil.mdmSearchExpr;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-07-13
 */
public class OrderServiceUtilTest {

    @Test
    public void testMdmSearchExpr() throws Exception {
        OrderListContract contract = new OrderListContract();
        contract.setMdmId("12348789");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
        
        Map<String, String> fieldMap = OrderServiceList.fieldCriteria.allFieldMap();
        String expected = "[LXK MPS Global Account #] = '12348789' AND [LXK MPS Account Level] = 'Siebel'";
        assertEquals(expected, mdmSearchExpr(contract, fieldMap));
        
        contract.setStatus("mock-status");
        expected = "[LXK MPS Global Account #] = '12348789' AND [LXK MPS Account Level] = 'Siebel'";
        assertEquals(expected, mdmSearchExpr(contract, fieldMap));
        
    }
    
    @Test
    public void testStringReplace() throws Exception {
        assertEquals("a a", "(a) (a)".replace("(a)", "a"));
        assertEquals("null null", "a a".replace("a", "" + null));
    }

    @Test
    public void testBuildOrderListSearchExpression() throws Exception {
        OrderListContract contract = new OrderListContract();
        contract.setMdmId("12348789");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);

        Map<String, String> fieldMap = OrderServiceList.fieldCriteria.allFieldMap();

        String expected = " EXISTS ([LXK MPS Global Account #] = '12348789' AND [LXK MPS Account Level] = 'Siebel') AND ([Status] <> 'Ready for Order' AND [Status] <> 'Pending')";
        assertEquals(expected, buildOrderListSearchExpression(contract, OrderServiceList.fieldCriteria));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testFilterByCommonKeys() throws Exception {
        Map<String, Object> filterCriteria = (Map<String, Object>) MiscTest.newHashMap("k1", "v1", "k2", "v2", "k3", "v3");
        Map<String, String> fieldMap = (Map<String, String>) MiscTest.newHashMap("k1", "v1",  "k3", "v3");
        Map<String, Object> expected = (Map<String, Object>) MiscTest.newHashMap("k1", "v1", "k3", "v3");
        assertEquals(expected, filterByCommonKeys(filterCriteria, fieldMap));
    }
    
    @Test
    public void testMapRemove() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("1", "1");
        String s = m.remove("1"); 
        assertEquals(s, "1");
        assertEquals(0, m.size());
        
        assertEquals(null, Collections.emptyMap().remove("1"));
    }
}
