package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.UploadAttachmentService.replaceSpacesAndBrackets;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author vpetruchok
 * @version 1.0, 2012-09-12
 */
public class UploadAttachmentServiceTest {

    @Test
    public void testReplaceSpacesAndBrackets() throws Exception {
        assertEquals("", replaceSpacesAndBrackets("", "~"));
        assertEquals("a", replaceSpacesAndBrackets("a", "~"));
        assertEquals("a~", replaceSpacesAndBrackets("a ", "~"));
        assertEquals("~~a~~", replaceSpacesAndBrackets("\n\ra\t ", "~"));
        try {
            assertEquals(null, replaceSpacesAndBrackets(null, "~"));
            fail();
        } catch (NullPointerException ok) {
        }        

        assertEquals("~a", replaceSpacesAndBrackets("(a", "~"));
        assertEquals("~a", replaceSpacesAndBrackets("(a)", "~"));
        assertEquals("~aa", replaceSpacesAndBrackets("(aa)", "~"));
        assertEquals("~1~2", replaceSpacesAndBrackets("(1)(2)", "~"));
        assertEquals("aa~", replaceSpacesAndBrackets(")aa(", "~"));
        assertEquals("~~~~", replaceSpacesAndBrackets("( )( )", "~"));
    }
}
