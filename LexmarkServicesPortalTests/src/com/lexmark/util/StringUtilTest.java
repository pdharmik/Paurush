package com.lexmark.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringUtilTest {
	
	@BeforeClass
	public static void setUp() throws Exception{
	}
	
	@Test
	public void testAppendDoubleQuote() {
		String str = "test\"A\" blah";
		Assert.assertEquals("test\"\"A\"\" blah", StringUtil.appendDoubleQuote(str));
	}
	
	@Test
	public void testEncloseWithDoubleQuote() {
		String str = "test1,test2 ";
		Assert.assertEquals("\"test1,test2 \"", StringUtil.encloseWithDoubleQuote(str));
	}
	
	/**
	 * the given String object is null
	 */
	@Test
	public void testIsStringEmpty1() {
		Assert.assertTrue(StringUtil.isStringEmpty(null));
	}
	
	/**
	 * the given String object is empty ""
	 */
	@Test
	public void testIsStringEmpty2() {
		Assert.assertTrue(StringUtil.isStringEmpty(" "));
	}
	
	/**
	 * the given String object is not empty
	 */
	@Test
	public void testIsStringEmpty3() {
		Assert.assertFalse(StringUtil.isStringEmpty("test"));
	}
}
