package com.lexmark.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class CSVUtil {
	
	public static final String INVALID_CELL_TYPE_OR_VALUE = new String(
	"!!!!!!!!___INVALID_CELL_TYPE_OR_VALUE____!!!!!!");
	
	public static String getCellValueAsString(String _cell, int _type,
			Locale locale) {
		if (_cell == null) {
			return null;
		}
		/**switch (_type) {
		case ColumnInfo.DATA_TYPE_NUMBER:
			return getNumberString(_cell);
		case ColumnInfo.DATA_TYPE_DATE:
			return getDateString(_cell ,locale);
		case ColumnInfo.DATA_TYPE_STRING:
			return getString(_cell);
		case ColumnInfo.DATA_TYPE_INT:
			return getNumberString(_cell);
		}*/
		return "INVALID_CELL_TYPE_OR_VALUE";
	}
/**
	private static String getString(String _cell) {
		if(_cell != null && _cell.trim().startsWith("'")) {
			return _cell.trim().substring(1);
		}
		return _cell;
	}
	private static String getDateString(String _cell, Locale _locale) {
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, _locale);
			Date date = df.parse(_cell);
			return CSV2AssetConverter.getDestDateFormat().format(date);
		} catch (Exception ex) {
			return INVALID_CELL_TYPE_OR_VALUE;
		}
	}
	private static String getNumberString(String _cell) {
		try {
			if(Pattern.compile("^[0-9]*$").matcher(_cell).matches()){
				return _cell;
			}else {
				return INVALID_CELL_TYPE_OR_VALUE;
			}
		} catch (Exception ex) {
			return INVALID_CELL_TYPE_OR_VALUE;
		}
	}
	*/
}
