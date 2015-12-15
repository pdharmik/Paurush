package com.lexmark.service.impl.real.util;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.checkDuplicates;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

/**
 * @author vpetruchok
 * @version 1.0, 2012-06-05
 */
public class DoFileUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCheckDuplicates() throws Exception {
        try { // same keys
            FieldEntry[] fields = {
                    xmltag("k1", ""),
                    xmltag("k1", "")
            };
            checkDuplicates(fields, "mock-filename.xml");
            fail();
        } catch (AssertionError ok) {
        }
        
        try { // same values
            FieldEntry[] fields = {
                    xmltag("k1", "v1"),
                    xmltag("k2", "v1")
            };
            checkDuplicates(fields, "mock-filename.xml");
            fail();
        } catch (AssertionError ok) {
        }
        
        {
            FieldEntry[] fields = {
                    xmltag("k1", ""),
                    xmltag("k2", "")
            };
            checkDuplicates(fields, "mock-filename.xml");
        }
    }
    
    @Test
    public void testDoProductTypeName() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-producttype-mapping.xml");
    }
    
    @Test
    public void testDoPageCount() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-ordersuppliesassetpagecount-mapping.xml");
    }
}
