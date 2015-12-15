package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-11
 */
public class AttachmentDoTest {

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AttachmentDo.class, 5, 0);
    }
    
    @Test
    public void query2() throws Exception {
//        MiscTest.sampleSiebelQuery(AttachmentDo.class, 5, 0);
        MiscTest.sampleSiebelQuery(AttachmentDo.class, "[parentId] <> ''",  5, 0);
    }
    
}
