package com.lexmark.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class DownloadFileLocalizationUtil {
	public static final String BUNDLE_NAME = "com.lexmark.resources.messages";

	private static String getPropertyValue(String bundleName, String _label, Locale locale) {
		String _value = null;
		ResourceBundle bundle = PropertiesMessageUtil.createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName,
				locale, Thread.currentThread().getContextClassLoader()));
		try {
			//Character encoding attached for Defect #7854
			_value = URLDecoder.decode(URLEncoder.encode(bundle.getString(_label), "UTF-8"), "ISO8859_1");
		} catch (UnsupportedEncodingException ue) {
			
			_value = "!! Error:" + _label + " not found !!";
		}
		return _value;
	}

	public static String getPropertyLocaleValue(String _label, Locale locale) {
		return getPropertyValue(BUNDLE_NAME, _label, locale);
	}
	
	public static String getPropertyNormal(String bundleName, String _label, Locale locale) {
		String _value = null;
		ResourceBundle bundle = PropertiesMessageUtil.createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName,
				locale, Thread.currentThread().getContextClassLoader()));
		try {
			
			_value = bundle.getString(_label);
		} catch (MissingResourceException e) {
			_value = "!! Error:" + _label + " not found !!";
		}
		return _value;
	}

}
