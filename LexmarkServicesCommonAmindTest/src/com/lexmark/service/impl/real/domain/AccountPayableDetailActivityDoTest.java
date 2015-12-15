package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailActivityDoTest {
    
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountPayableDetailActivityDo.class, 5);
    }
    
    @Test
    public void query3() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountPayableDetailActivityDo.class, "[partFee] <> ''", 5);
        MiscTest.sampleSiebelQuery(AccountPayableDetailActivityDo.class, 5);
    }

}
