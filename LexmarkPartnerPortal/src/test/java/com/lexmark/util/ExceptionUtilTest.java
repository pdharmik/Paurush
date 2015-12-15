package com.lexmark.util;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;


public class ExceptionUtilTest extends EasyMockSupport{
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testGetLocalizedExceptionMessage(){
		String msgKey="exception.notAuthorized.title";
		Locale locale = new Locale("en");
		Exception e = new Exception("test Exception");
		String result = ExceptionUtil.getLocalizedExceptionMessage(msgKey, locale, e);
		assertTrue(result.contains("Access Denied"));
		assertTrue(result.contains("test Exception"));
	}
	
}
