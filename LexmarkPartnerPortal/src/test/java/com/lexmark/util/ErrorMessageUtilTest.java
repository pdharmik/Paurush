package com.lexmark.util;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.constants.LexmarkPPConstants;

public class ErrorMessageUtilTest {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testGetErrorMessage(){
		String bundleName = LexmarkPPConstants.ERROR_MESSAGE_BUNDLE;
		String errorCode="exception.notAuthorized.title";
		Locale locale = new Locale("en");
		String result = ErrorMessageUtil.getErrorMessage(bundleName, errorCode, locale);
		assertTrue(result.contains("Access Denied"));
	}
	
	@Test
	public void getErrorMessageForMissingResource(){
		String bundleName = LexmarkPPConstants.ERROR_MESSAGE_BUNDLE;
		String errorCode="exception.notAuthorized.title.nothave";
		Locale locale = new Locale("en");
		String result = ErrorMessageUtil.getErrorMessage(bundleName, errorCode, locale);
		assertTrue(result.equals("!! Error Code: exception.notAuthorized.title.nothave not found !!"));
	}
	
	@Test
	public void getErrorMessageForParameNotNull(){
		String bundleName = LexmarkPPConstants.ERROR_MESSAGE_BUNDLE;
		String errorCode="warning.maximumLengthExceed.field";
		Locale locale = new Locale("en");
		Object params[] = {"1","2"};
		String result = ErrorMessageUtil.getErrorMessage(bundleName, errorCode,params,locale);
		assertTrue(result.equals("Maximum request length exceeded:[1/2]"));
	}
}
