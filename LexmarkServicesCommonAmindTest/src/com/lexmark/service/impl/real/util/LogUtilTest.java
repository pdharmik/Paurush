package com.lexmark.service.impl.real.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.contract.source.OrderListContract;
import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-12-14
 */
public class LogUtilTest {

    @Test
    public void testLogAmindServiceCallException() throws Exception {
        LogUtil.logAmindServiceCallException("mock-source", new RuntimeException(), new OrderListContract(), new OrderDetailContract(), 2, "1", null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDump() throws Exception {
        assertEquals("{k1=v1, k2=Thu Jan 01 02:00:00 EET 1970}", LogUtil.dump(MiscTest.newHashMap("k1", "v1", "k2", new Date(0L))));
        assertEquals("[1, 2, 3, Thu Jan 01 02:00:00 EET 1970]", LogUtil.dump(MiscTest.newArrayList(1, 2, 3, new Date(0L))));
        assertEquals("<null>", LogUtil.dump(null));
    }
    
    @Test
    public void testGetStackTrace() throws Exception {
       Exception innerEx = new RuntimeException("<inner exception>"); 
       Exception outerEx = new RuntimeException("<outer exception>"); 
       outerEx.initCause(innerEx);  
       System.out.println(LogUtil.getStackTrace(outerEx));
    }
    
    @Test
    public void testStackTraceExecutionPoint() throws Exception {
        int i = 0;
        for (StackTraceElement st : Thread.currentThread().getStackTrace()) {
            System.out.printf("%2d\t%s\n", i++, st);
        }

        assertTrue(LangUtil.startsWith(
                     LogUtil.stackTraceExecutionPoint(),
                    "com.lexmark.service.impl.real.util.LogUtilTest.testStackTraceExecutionPoint(LogUtilTest.java:"));
    }
    
}
