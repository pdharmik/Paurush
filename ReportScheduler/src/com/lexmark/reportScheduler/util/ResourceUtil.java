package com.lexmark.reportScheduler.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ResourceUtil {
	
	public static Map getBOAuthenticationDetails()
	{
	Map <String,String> accessDetails = new HashMap<String, String>();
	ResourceBundle rb = ResourceBundle.getBundle("boDetails");
	Enumeration <String> keys = rb.getKeys();
	while (keys.hasMoreElements()) {
		String key = keys.nextElement();
		String value = rb.getString(key);
		accessDetails.put(key, value);
	}
	return accessDetails;
}
}
