package com.lexmark.service.impl.real.partnerPaymentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.lexmark.util.LangUtil;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailServiceTest {

    /**
     * @see AmindPartnerPaymentServiceTest#testRetrieveAccountPayableDetail()
     */
    @Test
    public void testName() throws Exception {
    }
    
    
    @Test
    public void testBigDecimalScale() throws Exception {
        try {
            new BigDecimal("1.223").setScale(2);
            fail();
        } catch (ArithmeticException ok) {
        }
        
        assertEquals(bd("1.22"), bd("1.223").setScale(2, RoundingMode.DOWN));
        assertEquals(bd("1.22"), bd("1.227").setScale(2, RoundingMode.DOWN));
        
        BigDecimal bd1 = bd("2.555").setScale(2, RoundingMode.DOWN);
        BigDecimal bd2 = bd("2.555").setScale(2, RoundingMode.DOWN);
        assertEquals(bd("5.10"), bd1.add(bd2));
        
        LangUtil.convertStringToBigDecimal("12.111", 2);
    }

    private BigDecimal bd(String val) {
        return new BigDecimal(val);
    }


    @Test
    public void testNormalize() throws Exception {
        assertEquals(AccountPayableDetailService.ZERO_MONEY_FORMAT, 
                AccountPayableDetailService.normalize(null));
    
        assertEquals(bd("1.34"), 
                AccountPayableDetailService.normalize(bd("1.345")));
    }
    
}
