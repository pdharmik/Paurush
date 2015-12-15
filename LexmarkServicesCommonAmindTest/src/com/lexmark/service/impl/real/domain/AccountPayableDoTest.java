package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.TestErrors;

/**
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AccountPayableDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/partnerService/do-accountpayable-mapping.xml");
    }

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDo.class, 5, 0);
    }

    @Test
    public void queryAccountPayableDo2() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            List r = MiscTest.querySiebel(AccountPayableDo.class, "([LXK SAP Vendor Class] <> 'CSS' AND  [LXK SAP Vendor Account Type] <> 'Customer')", 100, 0);
            MiscTest.print(r, "vendorClass", "vendorAccountType");
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }

    }

    @Test
    public void query3() throws Exception {
        TestErrors errors = new TestErrors();
        for (String field : Arrays.asList("vendorName", "vendorId", "companyCode")) {
            try {
                MiscTest.sampleSiebelQuery(AccountPayableDo.class, String.format("[%s] <> ''", field), 1, 0);
            } catch (Exception ex) {
                errors.add(ex, String.format("checking `%s'", field));
            }
        }
        errors.check();
    }
    
    
    @Test
    public void query4() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[vendorName] <> ''", 1, 0);  
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
        
    }

}
