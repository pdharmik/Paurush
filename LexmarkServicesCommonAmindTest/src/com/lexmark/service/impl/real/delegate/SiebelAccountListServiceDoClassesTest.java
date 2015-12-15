package com.lexmark.service.impl.real.delegate;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lexmark.service.impl.real.AmindSiebelQueryTest;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDo;
import com.lexmark.service.impl.real.domain.AccountDo;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-29
 */
public class SiebelAccountListServiceDoClassesTest  extends AmindSiebelQueryTest {
    
    @Test
    public void queryAccountByVendorIdDo() throws Exception {
        sampleSiebelQuery(AccountByVendorIdDo.class, 5, 0);
    }
    
    @Test
    public void queryAccountDo() throws Exception {
        sampleSiebelQuery(AccountDo.class, 5, 0);
    }
}
