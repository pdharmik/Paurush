package com.lexmark.taglib;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.tagext.TagSupport;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;

public class ReplaceRNTest extends EasyMockSupport {
	private ReplaceRN replaceRN;

	private MockServletContext mockServletContext;
	private MockPageContext mockPageContext;
	private WebApplicationContext mockWebApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockServletContext = new MockServletContext();

		mockWebApplicationContext = createMock(WebApplicationContext.class);

		mockServletContext.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				mockWebApplicationContext);

		mockPageContext = new MockPageContext(mockServletContext);

		replaceRN = new ReplaceRN();
		replaceRN.setPageContext(mockPageContext);
		resetAll();
	}

	@Test
	public void testReplaceRN() throws Exception {
		String value = "Jeremy\nhello\r";
		String expectedOutput = "Jeremy<br/>hello ";
		
		expect(mockWebApplicationContext.getServletContext()).andReturn(
				mockServletContext).anyTimes();
		
		replayAll();
		replaceRN.setValue(value);
		int tagReturnValue = replaceRN.doStartTag();
		
		String output = ((MockHttpServletResponse) mockPageContext
				.getResponse()).getContentAsString();
		assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
				tagReturnValue);
		assertEquals("Output should be 'Jeremy<br/>hello", expectedOutput, output);
		verifyAll();

	}
}