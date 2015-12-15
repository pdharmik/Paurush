package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;


/**
 * @author vpetruchok
 * @version 1.0, 2012-07-03
 */
public class SupplyRequestDetailShipmentDoTest {
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipment-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipmentitem-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipmentitemorderentry-mapping.xml");
    } 
    
    @Test
    public void  testMappings() throws Exception {
        FieldEntry[] fieldEntries = {
                xmltag("id", "Id"),

                comment(""),
                xmltag("shipmentLineItems",  "com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemDo"),

                // xmltag("",  ""),
        };

        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void querySupplyRequestDetailShipmentDo() throws Exception {
         MiscTest.sampleSiebelQuery(SupplyRequestDetailShipmentDo.class, "vendorId <> ''", 5); 
    }
    
}
