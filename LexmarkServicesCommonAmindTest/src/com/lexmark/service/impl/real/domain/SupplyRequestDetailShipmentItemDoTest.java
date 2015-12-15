package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;


/**
 * 
 * @see com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDoTest
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-03
 */
public class SupplyRequestDetailShipmentItemDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipmentitem-mapping.xml");
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipmentitemorderentry-mapping.xml");
    } 
    
    
    FieldEntry[] fieldEntries;
    
    @Before
    public void setUp() {
         fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),
                xmltag("carrier",  "LXK MPS Carrier"),
                xmltag("eta",  ""),
                xmltag("actualDeliveryDate",  "Delivery Date"),
                xmltag("productTLI",  "Product"),
                xmltag("description",  "Product Description"),
                xmltag("vendorProduct",  "LXKMPSVendorProduct"),
                xmltag("quantity",  ""),

                comment("portal: List<Subline>"),
                xmltag("shipmentOrderEntryItems",  "com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemOrderEntryDo"),

                xmltag("trackingNumber",  "LXK SD Tracking #"),
                xmltag("actualShipDate",  "Shipment Date"),
                xmltag("shippedQuantity", "Shipment Quantity"),
//                xmltag("",  ""),
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
}
