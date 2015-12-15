package com.lexmark.service.impl.real.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.lexmark.service.impl.real.AmindSiebelQueryTest;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-18
 */
public class AmindServiceUtilSiebelTest extends AmindSiebelQueryTest {

    static SiebelBusinessServiceProxy businessServiceProxy;

    @BeforeClass
    public static void setUp() {
        businessServiceProxy = session.getSiebelBusServiceProxy();
    }

    @Test
    public void testGetTotalCount() {
        String businessObject = "LXK SW Contracted Asset - Service Web";
        String businessComponent = "LXK SW Contracted Asset - Service Web";

        {
            String searchExpression = ""; // will it query all records ?
            int count = AmindServiceUtil.getTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
            System.out.println("count=" + count); 
            assertEquals(21282650, count);
        }
        
        {
            String searchExpression = "[Owner Account Id]='1-4GVCA";
            int count = AmindServiceUtil.getTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
            System.out.println("count=" + count); 
            assertEquals(132, count);
        }
        
        try {  
            // cannot use qualified name 
            String searchExpression = "[LXK SW Contracted Asset - Service Web.Owner Account Id]='1-4GVCA";
            int count = AmindServiceUtil.getTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
            fail();
        } catch (Exception ok) {
        }
    }

}
