package com.lexmark.util;

import java.util.StringTokenizer;

/**
 * The String Util for Lexmark Service Portal. 
 * @author roger.lin
 *
 */

public class StringUtil {

	private final static String DOUBLE_QUOTE = "\"";
	private final static String PAIR_DOUBLE_QUOTE = "\"\"";
	
	/**
	 * Append one more " before ".
	 * ex. before: example "A" for testing, after: example ""A"" for testing
	 * @param str
	 * @return string with pair of double-quote characters
	 * if given string contains any double-quote
	 */
	public static String appendDoubleQuote(String str) {
		return str.replaceAll(DOUBLE_QUOTE, PAIR_DOUBLE_QUOTE);
	}
	
	/**
	 * Enclose given string with double-quote characters.
	 * @param str
	 * @return enclosed string with double-quote characters
	 */
	public static String encloseWithDoubleQuote(String str) {
		return (DOUBLE_QUOTE + str + DOUBLE_QUOTE);
	}
	
	//Added for CI-7 Defect 7854
	public static String encloseWithDoubleQuoteWithSpace(String str) {
		return (DOUBLE_QUOTE +" "+ str + DOUBLE_QUOTE);
	}

	/**
	 * Utility method to check if a String is null or empty. If either case is
	 * true, the return value is false else true.
	 * 
	 * @param str the String to check
	 * @return boolean whether the String is empty, null or not
	 */
	public static boolean isStringEmpty(String str) {
		if (str == null) {
			return true;
		}

		str = str.trim();
		if (str.length() < 1) {
			return true;
		}

		return false;
	}
	
	public static String replaceDoubleQuote(String str){
		String retStr = str;
		if(str != null)
			//retStr = str.replace(DOUBLE_QUOTE, "'");
			retStr = str.replace(DOUBLE_QUOTE, "&quot;");
		return retStr;
	}
	
	public static String encodeSingleQuote(String str){
		if(str != null)
			str = str.replace("'", "&acute;");
		return str;
	}
	
	public static String formatString(String str){
		String retStr = str;
		if(str != null){
			retStr = replaceDoubleQuote(retStr);
			retStr = encodeSingleQuote(retStr);
		}
		return retStr;
	}
	public static String appendEllipsis(String str, int length){
		String retStr = str;
		if(str != null){
			if(str.length() >= length)
				retStr = str.substring(0, length) + "...";
		}
		return formatString(retStr);
	}
	
	public static String appendEllipsisModified(String str, int length){
		String retStr = str;
		if(str != null){
			if(str.length() >= length)
				retStr = str.substring(0, length) + "...";
		}
		return retStr;
	}
	
	public static String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()) t += st.nextElement();
		  return t;
		}
	
	public static String formatStringForJS(String originalStr){
		if(!isStringEmpty(originalStr)){
			originalStr = originalStr.replaceAll("\"", "\\\\\"");
			originalStr = originalStr.replaceAll("\'", "\\\\\'");
		}
		return originalStr;
	}
	
	//Added By Sourav for Null Check of any object
	
		/**
		 * Checks whether an object is empty or not.
		 * 
		 * @param Obj Object
		 * @return boolean
		 */
		public static boolean isEmpty(Object obj) {
			if (obj == null) {
				return true;
			}
			return false;
		}
		/* Added by sankha (BRD 14-02-14) */
		public static String isNull(String inputString)
	    {
	        if(inputString==null || inputString.length()==0)
	        {
	            return inputString="";
	        }
	        else
	        {
	            return inputString.trim();
	        }
	    }
		/* End */

}
