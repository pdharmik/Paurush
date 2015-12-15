package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-27
 */
public class RequestTypeDetailServiceTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-servicerequestdetailattachment-mapping.xml");
//        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetaildo-mapping.xml");
    }
    
    @Test
    public void testA() throws Exception {
        List<?> lst =  new ArrayList<Object>();
        try {
            lst.iterator().next();
            fail();
        } catch (Exception ok) {
        }
    }
}
