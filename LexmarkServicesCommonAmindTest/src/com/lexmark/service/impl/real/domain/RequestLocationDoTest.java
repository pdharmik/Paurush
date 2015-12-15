package com.lexmark.service.impl.real.domain;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.comment;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.MiscTest;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;


/**
 * @author vpetruchok
 * @version 1.0, 2012-08-28
 */
public class RequestLocationDoTest {

    FieldEntry[] fieldEntries;

    @Before
    public void setUp() throws Exception {
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),

                xmltag("", "LXK MPS Vendor Account Domestic DUNS Number"),
                xmltag("", "LXK MPS Vendor Account Global DUNS Number"),
                xmltag("", "LXK MPS Vendor Account Id"),
                xmltag("", "LXK MPS Vendor Account Legal Entity Id"),
                xmltag("", "LXK MPS Vendor Account Level"),
                xmltag("", "LXK MPS Vendor Account MDM #"),
                xmltag("", ""),
        };
    }

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/requestService/do-requestlocation-mapping.xml");
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

    @Test
    public void queryRequestLocationDo() throws Exception {
        MiscTest.sampleSiebelQuery(RequestLocationDo.class, 5, 0);
    }
    
    @Test
    public void test_Defect2171() throws Exception {
//        MiscTest.sampleSiebelQuery(RequestLocationDo.class, "[LXK MPS Service Address - Service Web.LXK SW Account Global DUNS Number] = '023058159'",  5, 0);
        // mdmLevel1AccountId = Global level
        MiscTest.sampleSiebelQuery(RequestLocationDo.class, "[LXK SW Account Global DUNS Number] = '023058159'",  5, 0);
    }
    
}
