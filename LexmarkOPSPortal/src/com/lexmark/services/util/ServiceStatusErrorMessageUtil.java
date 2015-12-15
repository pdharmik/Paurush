package com.lexmark.services.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.lexmark.util.PropertiesMessageUtil;

public class ServiceStatusErrorMessageUtil {

	public static String getErrorMessage(String bundleName, String errorCode, Object params[], Locale locale){
		String errorMessage = null;
		ResourceBundle bundle = PropertiesMessageUtil.createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
		try{
			errorMessage = bundle.getString(errorCode);
		}catch(MissingResourceException e){
			errorMessage = "!! Error Code: " + errorCode + " not found !!";
		}
		if (params != null){
			MessageFormat mf = new MessageFormat(errorMessage, locale);
			errorMessage = mf.format(params, new StringBuffer(), null).toString();
		}
		return errorMessage;
	}
}
