package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-03
 */
public class GlobalContactDOTest {

    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(GlobalContactDO.class, 5, 0);
    }
    
    @Test
    public void query2() throws Exception {
        MiscTest.sampleSiebelQuery(GlobalContactDO.class, "1 = 1", 5);
//        MiscTest.sampleSiebelQuery(GlobalContactDO.class, "[Id] = [Id]", 5);
    } 
    
}
