package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

/**
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-02
 */
public class SupplyRequestDetailPendingShipmentDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailpendingshipment-mapping.xml");
    } 
    
    
    FieldEntry[] fieldEntries;
    
    @Before
    public void setUp() {
         fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),
                xmltag("partNum",  "Product"),
                xmltag("description",  "Product Description"),
                xmltag("status",  "Status"),
                xmltag("pendingQuantity",  "LXK MPS Pending Quantity"),
                xmltag("backOrderQuantity", "LXK MPS Back Order Quantity"),
                xmltag("orderedDate", "Created"),
                xmltag("pendingShipmentItems", "com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentItemDo"),

                // xmltag("",  ""),
        };
    }
    
    @Test
    public void  testMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void  genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }

    @Test
    public void querySupplyRequestDetailPendingShipmentDo() throws Exception {
        MiscTest.sampleSiebelQuery(SupplyRequestDetailPendingShipmentDo.class, "[vendorId] <> ''", 5);
    }
}
