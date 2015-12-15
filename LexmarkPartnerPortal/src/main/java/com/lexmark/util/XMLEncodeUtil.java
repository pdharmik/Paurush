package com.lexmark.util;

import java.util.HashMap;
import java.util.Map;

public class XMLEncodeUtil {

	private static final Map<Character, String> xmlescapes = new HashMap<Character, String>();
	static {
		xmlescapes.put('<', "&lt;");
		xmlescapes.put('>', "&gt;");
		xmlescapes.put('&', "&amp;");
		xmlescapes.put('\'', "&#39;");
		xmlescapes.put('"', "&quot;");
		xmlescapes.put('\\', "&#92;");
		xmlescapes.put('\t', "");
		xmlescapes.put('\n', "");
		xmlescapes.put('\r', "");
		xmlescapes.put('\'', "&apos;");
	}

	private static final  String jsescapes = "\\'\"";
	public static void appendXML(StringBuffer buffer, String text) {
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			String escape = xmlescapes.get(c);
			if(escape!=null){
				buffer.append(escape);
			} else if(c>127){
				buffer.append("&#").append((int) c).append(";");
			} else {
				buffer.append(c);
			}
		}
	}
	public static String escapeXML(String text) {
		StringBuffer buffer = new StringBuffer();
		appendXML(buffer, text);
		return buffer.toString();
	}
	
	public static void appendJSON(StringBuffer buffer, String text) {
		
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			if(jsescapes.indexOf(c)>=0){
				buffer.append("\\").append(c);
			}// to Only decode CJK and above(Chinese, Japanese and Korea)
			 else if(c>0X2E80){
				buffer.append("\\u").append(Integer.toHexString(c));
			} else {
				buffer.append(c);
			}
		}
	}
	public static String escapeJSON(String text) {
		StringBuffer buffer = new StringBuffer();
		appendJSON(buffer, text);
		return buffer.toString();
	}
}
