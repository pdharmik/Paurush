package com.lexmark.service.impl.real;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.domain.GlobalAccount;

/**
 * @author vpetruchok
 * @version 1.0, 2012-12-24
 */
public class GlobalServiceFacadeImplTest extends AmindServiceTest {

    private static GlobalServiceFacadeImpl service;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        service = new GlobalServiceFacadeImpl();
    }
    
    @Test
    public void testRetriveGlobalAccount() {
        long t0 = System.currentTimeMillis();
        try {
            GlobalAccount r = service.retriveGlobalAccount("mock-mdmId", "mock-mdmLevel");
            System.out.println(str(r));  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

}
