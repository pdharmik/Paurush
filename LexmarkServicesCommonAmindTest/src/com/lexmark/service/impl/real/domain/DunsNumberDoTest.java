package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-03
 */
public class DunsNumberDoTest {
    
    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(DunsNumberDo.class, 5, 0);
    }
}
