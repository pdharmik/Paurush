package com.lexmark.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;


public class XMLEncodeUtilTest {
	private static Logger logger = LogManager.getLogger(XMLEncodeUtilTest.class);

	@Test
	public void testEscapeXMLA(){
		String text = "text";
		String result = XMLEncodeUtil.escapeXML(text);
		Assert.assertTrue(text.equals(result));
	}
	
	@Test
	public void testEscapeXMLB(){
		String text = "<aa>";
		String result = XMLEncodeUtil.escapeXML(text);
		Assert.assertTrue("&lt;aa&gt;".equals(result));
	}
	
	@Test
	public void testEscapeXMLC(){
		char c = 128;
		String text = String.valueOf(c);
		String result = XMLEncodeUtil.escapeXML(text);
		logger.info(result);
		Assert.assertTrue("&#128;".equals(result));
	}
	
	@Test
	public void testEscapeJSONA(){
		String text = "\\";
		String result = XMLEncodeUtil.escapeJSON(text);
		Assert.assertTrue("\\\\".equals(result));
		
		text = "'";
		result = XMLEncodeUtil.escapeJSON(text);
		Assert.assertTrue("\\'".equals(result));
		
		text = "\"";
		result = XMLEncodeUtil.escapeJSON(text);
		Assert.assertTrue("\\\"".equals(result));
	}
	
	@Test
	public void testEscapeJSONB(){
		char c = 0X2E81;
		String text = String.valueOf(c);
		String result = XMLEncodeUtil.escapeJSON(text);
		Assert.assertTrue("\\u2e81".equals(result));
	}
	
	@Test
	public void testEscapeJSONC(){
		String text = "text";
		String result = XMLEncodeUtil.escapeJSON(text);
		Assert.assertTrue(text.equals(result));
	}
}
