package com.lexmark.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequestOrderLineItem;

public class BusinessObjectUtil {
	/**
	 * 
	 * @param bussinessObject
	 * @param pattern
	 *            like the "{requestDate,date,MM/dd/yyyy}some other content}"
	 * @return
	 */
	public static String formatColumn(Object bussinessObject, String pattern, Locale locale) {
		List<String> tokenList = findTokens(pattern);
		if (tokenList.isEmpty()) {
			boolean hasExtConfig = pattern.indexOf(":") != -1;
			String extConfig = null;
			if (hasExtConfig) {
				extConfig = pattern.substring(pattern.indexOf(":") + 1);
				pattern = pattern.substring(0, pattern.indexOf(":"));
			}
			Object propertyValue = readProperty(bussinessObject, pattern);
			return formatProperty(propertyValue, locale, extConfig);
		}
		String formatPattern = pattern;
		HashMap<String, String> columns = new LinkedHashMap<String, String>();
		int i = 0;
		Iterator<String> ite = tokenList.iterator();
		while (ite.hasNext()) {
			String pi = ite.next();
			String columnName;
			if (pi.indexOf(",") < 0) {
				// format like {columnName, date, 'yyyy-MM-dd'}
				columnName = pi.substring(1, pi.length() - 1).trim();
				// format like {columnName}
			} else {
				columnName = pi.substring(1, pi.indexOf(",")).trim();
			}
			if (!columns.containsKey(pi)) {
				columns.put(pi, columnName);
				String format = pi.replace(columnName, "" + i);

				formatPattern = formatPattern.replaceAll(normalize(pi), format);
				i++;
			}
		}
		Object[] objects = new Object[columns.size()];
		int index = 0;
		Iterator<String> columnIte = columns.values().iterator();
		while (columnIte.hasNext()) {
			objects[index] = readProperty(bussinessObject, columnIte.next());
			index++;
		}
		return MessageFormat.format(formatPattern, objects);
	}

	private static String formatProperty(Object object, Locale locale, String extConfig) {
		if (object == null) {
			return null;
		}
		if (object instanceof String) {
			return (String) object;
		}
		if (object instanceof Date) {
			if ("showTime".equals(extConfig)) {
				return DateLocalizationUtil.formatDateTimeToLocale((Date) object, locale);
			} 
			//Added for CI-7 Defect 7854
			else if ("formatDate".equals(extConfig)) {
				return DateLocalizationUtil.localizeDateTime((Date) object, false, locale);
			}else if("gmtString".equals(extConfig)){
				return DateUtil.convertDateToGMTString((Date) object);
			}else {
//				return DateLocalizationUtil.formatDateLocale((Date) object, locale);	// commented by nelson for CI5
				return DateLocalizationUtil.formatDateTimeOnSRGridColumn((Date)object, locale);	// added by nelson for CI5
			}
		}
		if (object instanceof ListOfValues)
			return ((ListOfValues) object).getName();

		return object.toString();
	}
	
	public static Object readPropertyList(Object target,String property){
		try {
			return PropertyUtils.getProperty(target, property);
		} catch (NestedNullException npe) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Object readProperty(Object target, String property) {
		try {				
			String orderedParts = "";			
			if(property.contains("pendingShipments"))
			{		
				int noOfParts = StringUtils.countMatches(PropertyUtils.getProperty(target, property).toString(), ",");		
				for(int i=0;i<=noOfParts;i++)
				{
					property = "pendingShipments["+i+"].partnumber";
					orderedParts = orderedParts.concat(PropertyUtils.getProperty(target, property).toString()).concat(",");
				}
			}
			else{
			return PropertyUtils.getProperty(target, property);
			}
			return orderedParts;
		} catch (NestedNullException npe) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String normalize(String token) {
		return token.replaceAll("\\{", "\\\\{").replaceAll("\\}", "\\\\}");
	}

	private static List<String> findTokens(String pattern) {
		Pattern p = Pattern.compile("\\{[^\\{]*\\}");
		Matcher m = p.matcher(pattern);
		List<String> retVal = new ArrayList<String>();
		while (m.find()) {
			int start = m.start();
			int end = m.end();
			retVal.add(pattern.substring(start, end));
		}
		return retVal;
	}
}
