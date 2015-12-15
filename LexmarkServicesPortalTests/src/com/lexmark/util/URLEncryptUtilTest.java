package com.lexmark.util;

import junit.framework.Assert;

import org.junit.Test;


public class URLEncryptUtilTest {
	@Test
	public void testEncrypt() {
		String url = "http://dlxksport1.lex.lexmark.com:8003/files/servicesPortal/reports/Services%20Web%20Specifications-Meter%20Read.doc";
		String encryptedUrl = URLEncryptUtil.encrypt(url);
		
		
		Assert.assertTrue(encryptedUrl.indexOf(url)>-1);
		
		url = "http://dlxksport1.lex.lexmark.com:8003/files/servicesPortal/reports/test.doc?param=1";
		encryptedUrl = URLEncryptUtil.encrypt(url);
		
		
		Assert.assertTrue(encryptedUrl.indexOf(url)>-1);
		
		
	}
}
