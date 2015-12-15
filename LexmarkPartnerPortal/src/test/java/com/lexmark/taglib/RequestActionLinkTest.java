package com.lexmark.taglib;

import static org.junit.Assert.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.lexmark.taglib.RequestActionLink;

public class RequestActionLinkTest extends EasyMockSupport{
	private MockServletContext mockServletContext;
	private MockPageContext mockPageContext;
	
	private static final int SKIP_BODY = 0;
	private static final int EVAL_BODY_INCLUDE = 1;

	@Before
	public void setUp() throws Exception {
		mockServletContext = new MockServletContext();
		mockPageContext = new MockPageContext(mockServletContext);
	}

	@Test
	public void testDoStartTagValueNull() throws JspException {
		RequestActionLink requestActionLink = new RequestActionLink();
		requestActionLink.setPageContext(mockPageContext);
		assertTrue(SKIP_BODY == requestActionLink.doStartTag());
	}
	
	@Test
	public void testDoStartTagUpdateTrue() throws JspException {
		RequestActionLink requestActionLink = new RequestActionLink();
		requestActionLink.setPageContext(mockPageContext);
		requestActionLink.setType("update");
		requestActionLink.setValue("value");
		assertTrue(EVAL_BODY_INCLUDE == requestActionLink.doStartTag());
	}
	
	@Test
	public void testDoStartTagUpdateFalse() throws JspException {
		RequestActionLink requestActionLink = new RequestActionLink();
		requestActionLink.setPageContext(mockPageContext);
		requestActionLink.setType("update");
		requestActionLink.setValue("Processing Request");
		assertTrue(SKIP_BODY == requestActionLink.doStartTag());
	}
	
	@Test
	public void testDoStartTagDebriefFalse() throws JspException {
		RequestActionLink requestActionLink = new RequestActionLink();
		requestActionLink.setPageContext(mockPageContext);
		requestActionLink.setType("debrief");
		requestActionLink.setValue("Processing Request");
		assertTrue(SKIP_BODY == requestActionLink.doStartTag());
	}
	
	@Test
	public void testDoStartTagDebriefTrue() throws JspException {
		RequestActionLink requestActionLink = new RequestActionLink();
		requestActionLink.setPageContext(mockPageContext);
		requestActionLink.setType("debrief");
		requestActionLink.setValue("value");
		assertTrue(EVAL_BODY_INCLUDE == requestActionLink.doStartTag());
	}
	
}
