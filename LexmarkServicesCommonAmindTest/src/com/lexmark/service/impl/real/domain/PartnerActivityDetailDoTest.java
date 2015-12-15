package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;

/**
 * @author vpetruchok
 * @version 1.0, 2012-12-31
 */
public class PartnerActivityDetailDoTest {

    @Test
    public void query1() throws Exception {
//        MiscTest.sampleSiebelQuery(PartnerActivityDetailDo.class, "[Id] = [Id]", 5);
//        MiscTest.sampleSiebelQuery(PartnerActivityDetailDo.class, "1 = 1", 5);
        MiscTest.sampleSiebelQuery(PartnerActivityDetailDo.class, "(1 = 1)", 5);
    }
}
