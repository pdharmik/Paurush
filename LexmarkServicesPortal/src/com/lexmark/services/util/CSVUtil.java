package com.lexmark.services.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;


import com.lexmark.util.DateUtil;

public class CSVUtil {
	
	
	
	/**
	 * 
	 */
	public static final String INVALID_CELL_TYPE_OR_VALUE = new String(
	"!!!!!!!!___INVALID_CELL_TYPE_OR_VALUE____!!!!!!");
	
	/**
	 * @param _cell 
	 * @param _type 
	 * @param locale 
	 * @return String 
	 */
	public static String getCellValueAsString(String _cell, int _type,
			Locale locale) {
		if (_cell == null) {
			return null;
		}
		switch (_type) {
		case ColumnInfo.DATA_TYPE_NUMBER:
			return getNumberString(_cell);
		case ColumnInfo.DATA_TYPE_DATE:
			//logger.debug("Date = " + _cell);
			return getDateString(_cell ,locale);
		case ColumnInfo.DATA_TYPE_STRING:
			return getString(_cell);
		case ColumnInfo.DATA_TYPE_INT:
			return getNumberString(_cell);
		}
		return "INVALID_CELL_TYPE_OR_VALUE";
	}

	/**
	 * @param _cell 
	 * @return String 
	 */
	private static String getString(String _cell) {
		if(_cell != null && _cell.trim().startsWith("'")) {
			return _cell.trim().substring(1);
		}
		return _cell;
	}
	
	/**
	 * @param _cell 
	 * @param _locale 
	 * @return String 
	 */
	private static String getDateString(String _cell, Locale _locale) {
		
		try {
			String formatString = DateUtil.getDateFormatByLang(_locale.getLanguage());
			DateFormat formatter = null;
	        formatter = new SimpleDateFormat(formatString);
	        _cell=formatter.format(new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(_cell));
	        Date convertedDate = (Date) formatter.parse(_cell);
	        //logger.debug("convertedDate" + formatString);
			//Date date = df.parse(_cell);
			//logger.debug("DATE = " + CSV2AssetConverter.getDestDateFormat().format(convertedDate));
			return CSV2AssetConverter.getDestDateFormat().format(convertedDate);
			
		} catch (Exception ex) {
			ex.getMessage();
			return INVALID_CELL_TYPE_OR_VALUE;
			
		}
	}
	/**
	 * @param _cell 
	 * @return String 
	 */
	private static String getNumberString(String _cell) {
		try {
			if(Pattern.compile("^[0-9]*$").matcher(_cell).matches()){
				return _cell;
			}else {
				return INVALID_CELL_TYPE_OR_VALUE;
			}
		} catch (Exception ex) {
			ex.getMessage();
			return INVALID_CELL_TYPE_OR_VALUE;

		}
	}
}
