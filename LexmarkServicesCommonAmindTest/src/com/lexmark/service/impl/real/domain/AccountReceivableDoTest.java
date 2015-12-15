package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;

/**
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AccountReceivableDoTest {

    
    private static Class doClass = AccountReceivableDo.class;
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/partnerService/do-accountreceivable-mapping.xml");
    }

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountReceivableDo.class, 5, 0);
    }

    @Test
    public void query2() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            //        MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[vendorId] <> ''", 5, 0);
//       MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[vendorName] <> ''", 5, 0);
//        MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[addressLine1] <> ''", 5, 0);
//        MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[companyCode] <> ''", 5, 0);
//        MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[companyCodeDescription] <> ''", 5, 0);
//            MiscTest.sampleSiebelQuery(AccountPayableDo.class, "[mdmLevel5AccountId] <> ''", 5, 0);
            MiscTest.sampleSiebelQuery(doClass, "[LXK Partner DUNS] <> '254173693'", 5, 0);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

    @Test
    public void query3() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
//            MiscTest.sampleSiebelQuery(doClass, "[vendorName] <> ''", 1, 0);
            MiscTest.sampleSiebelQuery(doClass, "[soldToNumber] <> ''", 1, 0);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
}
