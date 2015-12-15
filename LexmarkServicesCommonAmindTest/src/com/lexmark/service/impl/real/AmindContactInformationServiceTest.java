package com.lexmark.service.impl.real;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.ContactInformationContract;
import com.lexmark.result.ContactInformationResult;
import com.lexmark.service.impl.real.domain.GlobalContactDO;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;


/**
 * @author vpetruchok
 * @version 1.0, 2012-10-25
 */
public class AmindContactInformationServiceTest extends AmindServiceTest {
    
    static AmindContactInformationService service;
    
    @BeforeClass
    public static void setUp() throws Exception {
         service = new AmindContactInformationService();
         service.setStatelessSessionFactory(statelessSessionFactory); 
    }
    
    @Test
    public void testRetrieveContactInformation_QA() throws Exception {
        ContactInformationContract c = new ContactInformationContract();
        c.setRoleName("Service and Support"); 
        c.setMdmId("1-IR7U-436");
        c.setMdmLevel("Siebel");
        
        ContactInformationResult r = service.retrieveContactInformation(c);
        System.out.println(str(r));
        MiscTest.print(r.getContactInfoList());
    
    }
    
    @Test
    public void testRetrieveContactInformation_QA_Defect4441() throws Exception {
        ContactInformationContract c = new ContactInformationContract();
        c.setRoleName("groups administrators"); 
        c.setMdmId("");
        c.setMdmLevel("Global");
        
        ContactInformationResult r = service.retrieveContactInformation(c);
        System.out.println(str(r));
        MiscTest.print(r.getContactInfoList());
    
    }
    
    
    @Test
    public void queryGlobalLegalEntityDO() throws Exception {
        MiscTest.sampleSiebelQuery(GlobalLegalEntityDO.class, 
                "[mdmLevel5AccountId] <> '' AND [mdmLevel3AccountId] = '12669'"
                ,  10);
        
    }

    @Test
    public void queryGlobalContactDO() throws Exception {
        MiscTest.sampleSiebelQuery(GlobalContactDO.class, 
                "[mdmLevel3AccountId] <> ''"
                ,  10);
    }
    
}
