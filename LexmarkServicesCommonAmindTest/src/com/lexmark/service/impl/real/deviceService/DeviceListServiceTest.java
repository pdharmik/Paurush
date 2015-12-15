package com.lexmark.service.impl.real.deviceService;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.service.impl.real.AmindServiceTest;


/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTest()
 * @see com.lexmark.service.impl.real.AmindContractedDeviceServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-31
 */
public class DeviceListServiceTest extends AmindServiceTest {
    
    @BeforeClass
    public static void setUp() throws Exception {
        com.lexmark.service.impl.real.AmindContractedDeviceServiceTest.setUpBeforeClass(); 
    }
 
    @Test
    public void testRetrieveDeviceList_withData() throws Exception {
        new com.lexmark.service.impl.real.AmindContractedDeviceServiceTest().testRetrieveDeviceList_withData(); 
    }
}
