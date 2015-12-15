package com.lexmark.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesMessageUtil {

	public static String getErrorMessage(String bundleName, String errorCode, Object params[], Locale locale){
		String errorMessage = null;
		ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
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
	/**
	 * retrieve the message from properties file.
	 * @param bundleName
	 * @param _label
	 * @param locale
	 * @return
	 * Sample: getPropertyMessage("com.lexmark.service.resource", "serviceRequest.title.service request", locale);
	 */
	public static String getPropertyMessage(String bundleName, String _label, Locale locale){
		String _value = null;
		if(bundleName == null)
			bundleName = "com.lexmark.services.resources.messages";
		ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
		try{
			_value = bundle.getString(_label);
		}catch(MissingResourceException e){
			return getPropertyMessage(bundleName, _label);
		}
		return _value;
	}
	
	
	private static String getPropertyMessage(String bundleName, String _label){
		String _value = null;
		if(bundleName == null)
			bundleName = "com.lexmark.services.resources.messages";
		ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, 
																				  Locale.getDefault(), 
																				  Thread.currentThread().getContextClassLoader()));
		
		try{
			_value = bundle.getString(_label);
		}catch(MissingResourceException e){
			_value = "!! Error:" + _label +" not found !!";		
		}
		return _value;			
	}
	
	
	
	
	public static List<String> getLocationList(String bundleName, String category, Locale locale){
		ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
		List<String> retList = new ArrayList<String>();
		int index = 0;
		String key = "";
		for (Enumeration<String> e = bundle.getKeys(); e.hasMoreElements();) {
			key = e.nextElement();
			if(key.startsWith(category)){
				String str = bundle.getString(key);
				if(str != null){
					retList.add(index,str);
				index++;
				}
			}
		}
		Collections.sort(retList);
		return retList;
	}
	public static String getLocalizedRoleName(String bundleName, String category, Locale locale){
		ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
		String str = "";
		int index = 0;
		String key = "";
		for (Enumeration<String> e = bundle.getKeys(); e.hasMoreElements();) {
			key = e.nextElement();
			if(key.contains(category)){
				str = bundle.getString(key);
			}
		}
		return str;
	}

	/**
 * retrieve the Address error message from properties file.
 * @param bundleName
 * @param _label
 * @param locale
 * @return
 */
public static String getAddressCleansingError(String bundleName, String _label, Locale locale){
	String _value = null;
	if(bundleName == null)
		bundleName = "com.lexmark.services.resources.AddressCleansingError";
	ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
	try{
		_value = bundle.getString(_label);
		
	}catch(MissingResourceException e){
		return getPropertyMessage(bundleName, _label);
	}
	return _value;
}

//Added for Address Cleansing CI
public static String getAddressCleansingErrorPartner(String bundleName, String _label, Locale locale){
	String _value = null;
	if(bundleName == null)
		bundleName = "com.lexmark.resources.AddressCleansingError";
	ResourceBundle bundle = createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
	try{
		_value = bundle.getString(_label);
		
	}catch(MissingResourceException e){
		return getPropertyMessage(bundleName, _label);
	}
	return _value;
}

	public static ResourceBundle createUTF8ResourceBundle(ResourceBundle bundle) {
		if(bundle instanceof PropertyResourceBundle)
			return new UTF8ResourceBundle((PropertyResourceBundle)bundle);
		return bundle;
	}
	/*
	 * Added for MPS 2.1 hardware debrief
	 * */
	public static String getCommonPorperty(String messageProp,String _label){
		return getPropertyMessage(messageProp,_label);
	}
}
