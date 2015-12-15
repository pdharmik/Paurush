package com.lexmark.service.impl.real.domain;


import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-03
 */
public class SupplyRequestDetailShipmentItemOrderEntryDoTest {

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-supplyrequestdetailshipmentitemorderentry-mapping.xml");
    }

    FieldEntry[] fieldEntries;

    @Before
    public void setUp() {
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),
                xmltag("serialNumber",  "LXK MPS Serial Number"),

                // xmltag("",  ""),
        };
    }

    @Test
    public void testMappings() throws Exception {
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
