package com.lexmark.service.impl.real;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.BulkUploadContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.result.BulkUploadStatusListResult;


/**
 * @author vpetruchok
 * @version 1.0, 2012-12-27
 */
public class AmindPartnerBulkUploadServiceTest extends AmindServiceTest {
    
    static AmindPartnerBulkUploadService service;    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new AmindPartnerBulkUploadService();
        service.setStatelessSessionFactory(statelessSessionFactory);
    }

    @Test
    public void testBulkUploadFile() throws Exception {
        BulkUploadContract c = new BulkUploadContract();
        c.setMdmId("mock-mdmId");
        c.setMdmLevel("mock-mdmLevel");
        c.setUserFileName("mock-userFileName");
        BulkUploadFileResult r = service.bulkUploadFile(c);
        System.out.println("result = " + r.isUpDateSuccess());
    }

    @SuppressWarnings("unused")
    @Test
    public void testRetrieveBulkUploadStatusFile() throws Exception {
        BulkUploadStatusContract c = new BulkUploadStatusContract();
        BulkUploadStatusFileResult r = service.retrieveBulkUploadStatusFile(c);
    }

    @Test
    public void testRetrieveBulkUploadStatusList() throws Exception {
        BulkUploadStatusContract c = new BulkUploadStatusContract();
        c.setMdmId("mock-mdmId");
        BulkUploadStatusListResult r = service.retrieveBulkUploadStatusList(c);
        MiscTest.print(r.getBulkUploadStatusList());
    }

}
