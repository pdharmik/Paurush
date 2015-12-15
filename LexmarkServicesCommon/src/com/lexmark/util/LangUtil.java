package com.lexmark.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.ObjectUtils;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-10
 */
public final class LangUtil {

	private LangUtil() {
	}

	public static boolean isEmpty(Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	public static boolean isEmpty(CharSequence charSequence) {
		return charSequence == null || charSequence.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence charSequence) {
		return !isEmpty(charSequence);
	}
	
	
	/**
	 *  Returns true if charSequence is null, empty or consists of only whitespace chars,
	 *  otherwise false.
	 */
	public static boolean isBlank(CharSequence charSequence) {
	    if (isEmpty(charSequence)) {
	        return true;
	    }
	    
	    for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
	    }
	    
		return true;
	}
	
	/**
	 * Returns true if charSequence has at least one non-whitespace character, otherwise false.
	 * 
	 */
	public static boolean isNotBlank(CharSequence charSequence) {
		return !isBlank(charSequence);
	}
	
	/**
	 *  Removes all whitespace characters from the right-hand side of a string.
	 */
    static String rtrim(String s) {
        if (s == null)
            return null;

        int i = s.length();
        while (--i >= 0) {
            if (!Character.isWhitespace(s.charAt(i)))
                break;
        }

        return i < 0 ? "" : s.substring(0, ++i);
    }

    static String ltrim(String s) {
        if (s == null)
            return null;

        int i = -1;
        while (++i < s.length()) {
            if (!Character.isWhitespace(s.charAt(i)))
                break;
        }

        return i >= s.length() ? "" : s.substring(i);
    }
	
    /**
     * Removes leading and trailing whitespace from a string.
     * 
     */
	public static String trim(String s) {
	    return rtrim(ltrim(s));
	}

	public static <T> List<T> notNull(List<T> list) {
		if (list == null) {
			return Collections.<T> emptyList();
		} else {
			return list;
		}
	}
	
    private static final ArrayList<?> EMPTY_ARRAY_LIST = new ArrayList<Object>();

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> notNull(ArrayList<T> list) {
        if (list == null) {
            return (ArrayList<T>) EMPTY_ARRAY_LIST;
        } else {
            return list;
        }
    }

    public static int hashCode(Object obj) {
        return (obj == null) ? 0 : obj.hashCode();
    } 
    
    public static boolean equal(Object obj1, Object obj2) {
       return ObjectUtils.equals(obj1, obj2); 
    }
    
    public static boolean equalIgnoreCase(String s1, String s2) {
        if (s1 == s2) 
            return true;
        
        if (s1 == null || s2 == null) 
            return false;
        
        return s1.equalsIgnoreCase(s2); 
    }
    
	/**
	 * Returns the first element of a collection or null if the collection is null or empty. 
	 */
	public static <T> T first(Collection<T> coll) {
	    if (LangUtil.isEmpty(coll)) {
	        return null;
	    }
	    
        return coll.iterator().next();
    }

	
    public static int toInt(String s, int defaultValue) {
        if (LangUtil.isEmpty(s)) {
            return defaultValue;
        }
        
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
         	throw ((ex));
           
        }
    }
	
    public static int toInt(String s) {
        return toInt(s, 0);
    }
    
    public static boolean startsWith(String s, String prefix) {
        return (s == null || prefix == null) 
                 ? false
                 : s.startsWith(prefix);
    }
    
    public static BigDecimal convertStringToBigDecimal(String value,int scale) {
    	if(value != null && !value.isEmpty()){
    		return new BigDecimal(value).setScale(scale, RoundingMode.DOWN);
    	   	} else {
    		return BigDecimal.ZERO.setScale(scale, RoundingMode.DOWN);
    	}
    }
    
    /**
     * Dates
     */
    
    public static Date convertStringToGMTDate(String stringDate) {

		if (isBlank(stringDate)) {
			return null;
		}
		
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			try {
				formatter = new SimpleDateFormat("MM/dd/yyyy");
				formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
				date = formatter.parse(stringDate);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return date;
	}
    
    public static Date convertStringToISTDate(String stringDate) {

		if (isBlank(stringDate)) {
			return null;
		}
		
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			try {
				formatter = new SimpleDateFormat("MM/dd/yyyy");
				formatter.setTimeZone(TimeZone.getTimeZone("IST"));
				date = formatter.parse(stringDate);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return date;
	}
    
    public static Date convertStringToUTCDate(String stringDate) {
    	
    	if (isBlank(stringDate)) {
    		return null;
    	}
    	
    	Date date = null;
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    	try {
    		date = formatter.parse(stringDate);
    	} catch (ParseException e) {
    		try {
    			formatter = new SimpleDateFormat("MM/dd/yyyy");
    			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    			date = formatter.parse(stringDate);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	
    	return date;
    }

    public static double convertStringToDouble(String number) {
    	
    	if(isBlank(number)) {
    		return 0;
    	}
    	
    	try {
    		return Double.parseDouble(number);
    	}
    	catch (NumberFormatException e) {
    		
    		return 0;
    	}
    }
    
	public static int convertStringToInt(String stringInt) {
		if (isBlank(stringInt)) {
			return 0;
		}
		try {
			return Integer.parseInt(stringInt);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
