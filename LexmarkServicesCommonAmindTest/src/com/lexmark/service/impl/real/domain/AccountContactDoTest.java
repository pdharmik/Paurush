package com.lexmark.service.impl.real.domain;

import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-08-30
 */
public class AccountContactDoTest {

    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountContactDo.class, "", 5);
    }
    
    @Test
    public void queryAccountContactDo() throws Exception {
//        MiscTest.sampleSiebelQuery(AccountContactDo.class, "[alternatePhone] <> ''" , 5);
//        MiscTest.sampleSiebelQuery(AccountContactDo.class, "[id]='1-11Y6IX3'" , 5);
        List<AccountContactDo> r = MiscTest.sampleSiebelQuery(AccountContactDo.class, 
                "( [Account Id] = '1-P7PY6O' AND [LXK SW L5 Account Transactable Flag] = 'Y') AND[Id] = [LXK Contact Alias Id]"
                + " AND [alternatePhone] <> ''"
                , 5);
        
        MiscTest.print(r,  "id", "alternatePhone", "firstName", "lastName");
         
    } 
}
