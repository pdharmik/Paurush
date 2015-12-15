package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.compareFields;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;


/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-09
 */
public class AccountByVendorIdDoTest {
    
    FieldEntry[] fieldEntries;
    Class<?> doClass = AccountByVendorIdDo.class;
    
    @Before
    public void setUp() {
        
        fieldEntries = new FieldEntry[] {
//           xmltag("agreementName", ""), // already exists
           
//           xmltag("accountName", ""),
//           xmltag("accountSite", ""),
//           xmltag("accountOrganization", ""),
//           xmltag("countryCode", ""),
//           xmltag("displayName", ""),
           
           // mappings from Vendor_Management_OrderManagement_070612.docx: Account Selection with Agreement details
           xmltag("mdmLevel", "LXK MPS Account Level"),
           xmltag("partnerFlag", "LXK MPS Partner"),
           xmltag("id", "LXK MPS Primary Account Id"),
           xmltag("name", "LXK Primary Account"),
           xmltag("country", "LXK MPS Country"),
           xmltag("agreementName", "LXK MPS Agreement Name"),
           xmltag("vendorAccountId", "Account Id"),
           xmltag("", ""),
        };
    }
    
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/globalService/do-accountbyvendorid-mapping.xml");
    }
    
    @Test
    public void testMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
        
        compareFields(doClass, fieldEntries);
    }
    
    @Test
    public void genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }
    
    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AccountByVendorIdDo.class, "", 5);
    } 
    
    @Test
    public void query2() throws Exception {
        MiscTest.sampleSiebelQuery(AccountByVendorIdDo.class, "[LXK MPS Partner Flag] = 'Y'", 5);
    } 

}
