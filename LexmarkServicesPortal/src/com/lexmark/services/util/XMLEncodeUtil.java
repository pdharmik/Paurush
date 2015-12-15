package com.lexmark.services.util;

import java.util.HashMap;
import java.util.Map;

public class XMLEncodeUtil {

	private static final Map<Character, String> xmlescapes = new HashMap<Character, String>();
	private static final Map<Character, String> xmlescapesForDm = new HashMap<Character, String>();
	static
	{	
		xmlescapesForDm.put('<', "&lt;");
		xmlescapesForDm.put('>', "&gt;");
		xmlescapesForDm.put('&', "&amp;");
		xmlescapesForDm.put('\'', "&apos;");
		xmlescapesForDm.put('"', "&quot;");
		xmlescapesForDm.put('\\', "&#92;");
		xmlescapesForDm.put('\t', "");
		xmlescapesForDm.put('\n', "");
		xmlescapesForDm.put('\r', "");
	
		xmlescapes.put('<', "&lt;");
		xmlescapes.put('>', "&gt;");
		xmlescapes.put('&', "&amp;");
		xmlescapes.put('\'', "&apos;");
		xmlescapes.put('"', "&quot;");
		xmlescapes.put('\\', "&#92;");
		xmlescapes.put('\t', "");
		xmlescapes.put('\n', "");
		xmlescapes.put('\r', "");
		
	}

	private static final  String jsescapes = "\\'\"";
	/**
	 * 
	 * @param buffer 
	 * @param text 
	 */
	public static void appendXML(StringBuffer buffer, String text) {
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			String escape = xmlescapes.get(c);
			if(escape!=null){
				buffer.append(escape);
			} else if(c>=127){
				buffer.append("&#").append((int) c).append(";");
			} else {
				buffer.append(c);
			}
		}
	}
	
	/**
	 * This method is specifically designed for Device Mgmt in MPS
	 * @param text 
	 * @param buffer 
	 */
	
	public static void appendXMLforDM(StringBuffer buffer, String text) {
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			String escape = xmlescapesForDm.get(c);
			if(escape!=null){
				buffer.append(escape);
			} else if(c>=127){
				buffer.append("&#").append((int) c).append(";");
			} else {
				buffer.append(c);
			}
		}
	}
	
	/**
	 * This method is specifically designed for Device Mgmt in MPS
	 * @param text 
	 * @return String 
	 */
	public static String escapeXMLForDMCM(String text) {
		StringBuffer buffer = new StringBuffer();
		appendXMLforDM(buffer, text);
		return buffer.toString();
	}
	/**
	 * 
	 * @param text 
	 * @return Static 
	 */
	public static String escapeXML(String text) {
		StringBuffer buffer = new StringBuffer();
		appendXML(buffer, text);
		return buffer.toString();
	}
	/**
	 * 
	 * @param buffer 
	 * @param text 
	 */
	public static void appendJSON(StringBuffer buffer, String text) {
		
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			if(jsescapes.indexOf(c)>=0){
				buffer.append("\\").append(c);
			}
			 else if(c>0X2E80){
				buffer.append("\\u").append(Integer.toHexString(c));
			} else {
				buffer.append(c);
			}
		}
	}
	/**
	 * 
	 * @param text 
	 * @return String 
	 */
	public static String escapeJSON(String text) {
		StringBuffer buffer = new StringBuffer();
		appendJSON(buffer, text);
		return buffer.toString();
	}
}
