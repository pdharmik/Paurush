package com.lexmark.service.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.AssetListContract;

/**
 * 
 * This Class must be used carefully as it uses reflection which is very costly
 * in terms of memory, time and performance.
 * 
 * @author Souritra Das
 * 
 */
public class ObjectPropertiesPrint {
	private static Logger LOGGER = LogManager.getLogger(ObjectPropertiesPrint.class);
    public static void main(String args[]) {
	AssetListContract contact = new AssetListContract();
	contact.setAssetType("assetType");
	contact.setChlNodeId("chlNodeId");
	contact.setStartRecordNumber(20);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("AA", new Object());
	map.put("BB", "Test String");
	map.put("CC", new Integer(100));
	map.put("DD", contact);
	contact.setSearchCriteria(map);
	LOGGER.info(printObject(contact));
    }

    public static String printObject(Object object) {
	int callCount = 0;
	StringBuilder builder = new StringBuilder();
	try {
	    Class<?> oClass = object.getClass();
	    builder.append(printObject(oClass, object, callCount++));
	    Class<?> parent = oClass.getSuperclass();
	    while (parent != null && !parent.getName().equalsIgnoreCase("java.lang.Object")) {
		builder.append(printObject(parent, object, callCount++));
		parent = parent.getSuperclass();
	    }

	} catch (Exception ignore) {
		LOGGER.info(ignore);
	}

	return builder.toString();
    }

    public static String printObject(Class<?> oClass, Object object, int callCount) {
	StringBuilder builder = new StringBuilder();
	try {
	    builder.append("\n" + getTab(callCount) + oClass.getName());
	    Method[] methods = oClass.getDeclaredMethods();
	    for (Method method : methods) {
		method.setAccessible(true);
		String methodName = method.getName();
		// logger.info(methodName);
		if (methodName.startsWith("get")) {
		    Object obj = method.invoke(object);
		    if (method.getGenericReturnType().toString().contains("java.util.Map")) {
			try {			    
			    // Cast exception may occur.
			    //Map<String, String> map = (Map<String, String>) obj;
			    Map map = (Map) obj;
			    String value = "";
			    for (Object propertieName : map.keySet()) {
				value = value + "[" + propertieName.toString() + "=" + map.get(propertieName).toString() + "]";
			    }
			    builder.append("\n" + getTab(callCount + 1) + methodName + "() = {" + value + "}");
			} catch (Exception _ignore) {
				LOGGER.info(_ignore);
			}
		    } else {
			builder.append("\n" + getTab(callCount + 1) + methodName + "() = [" + (obj != null ? obj.toString() : "") + "]");
		    }
		} else if (methodName.startsWith("is")) { // for boolean object
							  // properties
		    Object obj = method.invoke(object);
		    builder.append("\n" + getTab(callCount + 1) + methodName + "() = [" + (obj != null ? obj.toString() : "") + "]");
		}
	    }
	} catch (Exception ignore) {
		LOGGER.info(ignore);
	}

	return builder.toString();
    }

    private static String getNewLine(int count) {
	StringBuilder builder = new StringBuilder();
	for (int index = 0; index <= count; index++) {
	    builder.append("\n");
	}
	return builder.toString();
    }

    private static String getTab(int count) {
	StringBuilder builder = new StringBuilder();
	for (int index = 0; index < count; index++) {
	    builder.append("\t");
	}
	return builder.toString();
    }
}
