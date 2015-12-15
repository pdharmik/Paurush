package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-30
 */
public class AccountFavouriteContactDoTest {
    
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountFavouriteContactDo.class, "", 5);
    }
    
    @Test
    public void queryAccountFavouriteContactDo() throws Exception {
        MiscTest.sampleSiebelQuery(AccountFavouriteContactDo.class, "[alternatePhone] <> ''" , 5);
         
    }
    

}
