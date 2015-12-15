package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-09-11
 */
public class ServiceRequestAttachmentDoTest {

    @Test
    public void query() throws Exception {
        MiscTest.sampleSiebelQuery(ServiceRequestAttachmentDo.class, 5, 0);
    }
}
