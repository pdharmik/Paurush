package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;

/**
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailDoTest {

    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/partnerService/do-accountpayabledetail-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/partnerService/do-accountpayabledetailactivity-mapping.xml");
    }
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class, 5);
    }

    @Test
    public void query2() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class, "[serviceRequestNumber] <> '' AND [vendorId] <> ''", 5);
    }    
    
    @Test
    public void query3() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class, "[serviceRequestNumber] <> '' AND [vendorId] <> ''", 5);
        for (AccountPayableDetailDo  a : MiscTest.querySiebel(AccountPayableDetailDo.class, "[serviceRequestNumber] <> '' AND [vendorId] <> ''", 
                5, 0)) {
            MiscTest.print(a.getActivities(), "partNumber", "eligibilityStatus","partFee", "fulfillmentFee");
        }
    }
    
    @Test
    public void query4() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class, "[serviceRequestNumber] <> '' AND [mdmLevel1AccountId] <> ''", 50);
    }        
                
    
    @Test
    public void query5() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailDo.class,
//                "[LXK SR Account Global DUNS Number] = '113631225' AND [LXK SW Request Detail - Partner Portal.SR Number] = '1-529611748'",
//                "[LXK SR Account Global DUNS Number] = '113631225' AND [serviceRequestNumber] = '1-529611748'",
//                "[LXK SR Account Global DUNS Number] = '113631225' AND [serviceRequestNumber] = '1-6096751607'", 
//                [partFee] <> ''" 
//                "[lxkMpsType] <> ''"
//                "[lxkMpsType] = ''"
//                 "[extendedLineTotal] = ''" 
                 "[extendedLineTotal] > 0" 
//                 "[extendedLineTotal] <> ''" 
//                "[SR Number] = '1-529611748'"
//                "[LXK SR Account Global DUNS Number] = '113631225'"
                ,5);
    }

    @Test
    public void testGetPartFee() throws Exception {
        AccountPayableDetailDo detailDo = new AccountPayableDetailDo();
        assertEquals(new BigDecimal("0.00"), detailDo.getPartFee());
        
        detailDo.setPartFee("abc");
        try {
            detailDo.getPartFee();
            fail();
        } catch (NumberFormatException ok) {
        }
    }        
    
}
