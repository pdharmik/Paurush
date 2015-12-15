package com.lexmark.service.impl.real.domain;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;


/**
 * @author vpetruchok
 * @version 1.0, 2013-02-19
 */
public class ContractedAndEntitledAssetDoTest {

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, "", 5);
    }
    
    /**
     * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetServiceTest#testRetrieveDeviceList_QA_Defect1722()
     */
    @Test
    public void queryBigQuery() throws Exception {
        String s = "EXISTS ([LXK SW Agreement Account LEGAL MDM ID]  = '168777' OR [LXK SW Agreement Account LEGAL MDM ID]  = '238713' OR [LXK SW Agreement Account LEGAL MDM ID]  = '242466' OR [LXK SW Agreement Account LEGAL MDM ID]  = '239792' OR [LXK SW Agreement Account LEGAL MDM ID]  = '233098' OR [LXK SW Agreement Account LEGAL MDM ID]  = '249349' OR [LXK SW Agreement Account LEGAL MDM ID]  = '194544' OR [LXK SW Agreement Account LEGAL MDM ID]  = '114258' OR [LXK SW Agreement Account LEGAL MDM ID]  = '249459' OR [LXK SW Agreement Account LEGAL MDM ID]  = '117923' OR [LXK SW Agreement Account LEGAL MDM ID]  = '113643' OR [LXK SW Agreement Account LEGAL MDM ID]  = '257343' OR [LXK SW Agreement Account LEGAL MDM ID]  = '249775' OR [LXK SW Agreement Account LEGAL MDM ID]  = '92610' OR [LXK SW Agreement Account LEGAL MDM ID]  = '103046' OR [LXK SW Agreement Account LEGAL MDM ID]  = '242877' OR [LXK SW Agreement Account LEGAL MDM ID]  = '230216' OR [LXK SW Agreement Account LEGAL MDM ID]  = '189051' OR [LXK SW Agreement Account LEGAL MDM ID]  = '238538' OR [LXK SW Agreement Account LEGAL MDM ID]  = '151214' OR [LXK SW Agreement Account LEGAL MDM ID]  = '188783' OR [LXK SW Agreement Account LEGAL MDM ID]  = '242504' OR [LXK SW Agreement Account LEGAL MDM ID]  = '243450' OR [LXK SW Agreement Account LEGAL MDM ID]  = '101524' OR [LXK SW Agreement Account LEGAL MDM ID]  = '104092' OR [LXK SW Agreement Account LEGAL MDM ID]  = '264546' OR [LXK SW Agreement Account LEGAL MDM ID]  = '257978' OR [LXK SW Agreement Account LEGAL MDM ID]  = '176135' OR [LXK SW Agreement Account LEGAL MDM ID]  = '176306' OR [LXK SW Agreement Account LEGAL MDM ID]  = '95652' OR [LXK SW Agreement Account LEGAL MDM ID]  = '132057' OR [LXK SW Agreement Account LEGAL MDM ID]  = '187796' OR [LXK SW Agreement Account LEGAL MDM ID]  = '133937' OR [LXK SW Agreement Account LEGAL MDM ID]  = '188317' OR [LXK SW Agreement Account LEGAL MDM ID]  = '134626' OR [LXK SW Agreement Account LEGAL MDM ID]  = '9591' OR [LXK SW Agreement Account LEGAL MDM ID]  = '91179' OR [LXK SW Agreement Account LEGAL MDM ID]  = '188525') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
        MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, s, 5);
    }
    
    @Test
    public void queryBigQuery1() throws Exception {
        String s = "([LXK SW Agreement Account LEGAL MDM ID]  = '168777' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '238713' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '242466' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '239792' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '233098' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '249349' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '194544' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '114258' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '249459' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '117923' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '113643' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '257343' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '249775' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '92610' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '103046' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '242877' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '230216' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '189051' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '238538' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '151214' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '188783' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '242504' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '243450' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '101524' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '104092' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '264546' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '257978' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '176135' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '176306' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '95652' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '132057' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '187796' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '133937' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '188317' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '134626' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '9591' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '91179' \r\n" + 
        		"OR [LXK SW Agreement Account LEGAL MDM ID]  = '188525') \r\n" + 
        		"AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')\r\n" + 
        		"\r\n" + 
        		"";
        MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, s, 5);
    }
    
    @Test
    public void query2() throws Exception {
        String s = "Exists([LXK SW Agreement Account LEGAL MDM ID]  <>  '')";
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, s, 5);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    @Test
    public void query21() throws Exception {
//        String s = "Exists([Id]  <>  '')";
        String s = "[Id]  <>  ''";
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, s, 5);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }    
    
    
    @Test
    public void query3() throws Exception {
        String s = "Exists([LXK SW Agreement Account LEGAL MDM ID] = '1188525')";
        long t0 = System.currentTimeMillis();
        try {
            MiscTest.sampleSiebelQuery(ContractedAndEntitledAssetDo.class, s, 5);
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
}
