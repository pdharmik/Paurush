package com.lexmark.util;

import java.util.HashMap;
import java.util.Map;

public class XMLGridEncodeUtil {

	private static final Map<Character, String> xmlescapes = new HashMap<Character, String>();
	static {
		xmlescapes.put('!', "&#33;");
		xmlescapes.put('#', "&#35;");
		xmlescapes.put('&', "&#38;");
		xmlescapes.put('\'', "&#8217;");
		xmlescapes.put('"', "&#34;");
		xmlescapes.put('\\', "&#92;");
		xmlescapes.put('\t', "");
		xmlescapes.put('\n', "");
		xmlescapes.put('\r', "");
		xmlescapes.put('$', "&#36;");
		xmlescapes.put('%', "&#37;");
		xmlescapes.put('(', "&#40;");
		xmlescapes.put(')', "&#41;");
		xmlescapes.put('*', "&#42;");
		xmlescapes.put('+', "&#43;");
		xmlescapes.put(',', "&#44;");
		xmlescapes.put('-', "&#45;");
		xmlescapes.put('.', "&#46;");
		xmlescapes.put(':', "&#58;");
		xmlescapes.put(';', "&#59;");
		xmlescapes.put('=', "&#61;");
		xmlescapes.put('?', "&#63;");
		xmlescapes.put('@', "&#64;");
		xmlescapes.put('^', "&#94;");
		xmlescapes.put('_', "&#95;");
		xmlescapes.put('`', "&#96;");
		xmlescapes.put('{', "&#123;");
		xmlescapes.put('|', "&#124;");
		xmlescapes.put('}', "&#125;");
		xmlescapes.put('~', "&#126;");
		xmlescapes.put('}', "&#125;");
		xmlescapes.put('}', "&#125;");
		xmlescapes.put('}', "&#125;");
	}

	private static final  String jsescapes = "\\'\"";
	public static void appendXML(StringBuffer buffer, String text) {
		for (int i = 0; text!=null && i < text.length(); i++) {
			char c = text.charAt(i);
			String escape = xmlescapes.get(c);
			if(escape!=null){
				buffer.append(escape);			
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
