package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import com.lexmark.service.impl.real.JUnitUtil;
import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2012-06-26
 */
public class AccountDoTest {
    
    @Rule
    public MethodRule rule1 = new JUnitUtil.TestExecutionInfoRule();
    
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/globalService/do-accountdo-mapping.xml");
    }
    
    @Test
    public void queryExpediteOrderFlag() throws Exception {
        MiscTest.sampleSiebelQuery(AccountDo.class, "[expediteOrderFlag] <> ''", 5);
    }
    
    /**
     * 
     * @see do-accountdo-mapping.xml
     */
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountDo.class, "[Id] <> ''", 5);
    }
    
    @Test
    public void query2() throws Exception {
        MiscTest.sampleSiebelQuery(AccountDo.class, "[LXM MDM Legal Entity ID #] <> ''", 5);
    }
}
