package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @see do-chldo-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2013-01-25
 */
public class CHLDoTest {

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(CHLDo.class, "", 5);
    }
    
    @Test
    public void queryWithParentChain() throws Exception {
//        MiscTest.sampleSiebelQuery(CHLDo.class, "[parentChain] <> ''", 50);
//        MiscTest.sampleSiebelQuery(CHLDo.class, "len([parentChain]) < 10", 50);
        MiscTest.sampleSiebelQuery(CHLDo.class, "[parentChain] = [id]", 50);
    }
}
