package com.lexmark.service.impl.real.domain;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-02
 */
public class SupplyRequestDetailPendingShipmentItemDoTest {

    FieldEntry[] fieldEntries;
    
    @Before
    public void setUp() throws Exception {
        
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),

                comment(""),
                xmltag("", "LXKMPSCarrier"),
                xmltag("", "LXKMPSTracking"),
                xmltag("", "DeliveryDate"),
                xmltag("", "Product2"),
                xmltag("", "ShipmentDate"),
                xmltag("", "ShipmentQuantity"),

                // xmltag("",  ""),
        };
    }
    
    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailpendingshipmentitem-mapping.xml");
    }
    
    @Test
    public void genXmlMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }
    
}
