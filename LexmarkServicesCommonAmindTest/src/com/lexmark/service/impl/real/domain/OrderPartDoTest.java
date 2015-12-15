package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-25
 */
public class OrderPartDoTest {
    
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(OrderPartDo.class, "", 5);
    }
    
    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(OrderPartDo.class,
                "[LXK MPS Agreement Id] = '1-42PVCHI' AND [LXK MPS Display On Portal] = 'Y'"
                , 100);  
    }
    

}
