package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;

/**
 * @author vpetruchok
 * @version 1.0, 2013-03-14
 */
public class PartnerValidateAssetDoTest {

    /**
     * 2013-03-14: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named
     * 'PartnerValidateAssetDo' is defined
     * 
     * @throws Exception
     */
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(PartnerValidateAssetDo.class, "", 5);
    }
}
