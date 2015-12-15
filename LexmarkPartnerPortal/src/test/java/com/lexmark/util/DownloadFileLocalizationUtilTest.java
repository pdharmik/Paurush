package com.lexmark.util;

import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

import com.lexmark.constants.LexmarkPPConstants;


public class DownloadFileLocalizationUtilTest {
	private final String yesMessage = "claim.lebel.returnRequired.yes";
	private final Locale local = Locale.ENGLISH;
	
	@Test
	public void testGetPropertyLocaleValue(){
		String message = DownloadFileLocalizationUtil.getPropertyLocaleValue(yesMessage, local);
		Assert.assertTrue(localizeMessage(yesMessage).equals(message));
	}
	
	@Test
	public void testGetPropertyLocaleValueNotFonud(){
		String label = "testGetPropertyLocaleValueNotFonud";
		String message = DownloadFileLocalizationUtil.getPropertyLocaleValue(label, local);
		String notFoundMessage = "!! Error:" + label + " not found !!";
		Assert.assertTrue(notFoundMessage.equals(message));
	}
	
	private String localizeMessage(String propertyAttribute) {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, Locale.ENGLISH));
	}	
}
