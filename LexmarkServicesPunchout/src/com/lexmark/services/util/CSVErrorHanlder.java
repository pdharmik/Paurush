package com.lexmark.services.util;

import java.util.ArrayList;


public class CSVErrorHanlder {
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_INVALID_CELL_VALUE = "invalid.cell";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_EMPTY_CELL = "empty.cell";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_TOO_FEW_COLUMNS = "too.few.columns";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_TOO_MUCH_COLUMNS = "too.much.columns";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_VALUE_TOO_LONG = "value.too.long";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_VALUE_TOO_SHORT = "value.too.short";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_VALUE_MUST_BE_71113 = "value.must.71113";
	/** Constant for CSVErrorHanlder */
	public static final String MSG_KEY_VALUE_MUST_BE_BELOW_64000 = "value.below.64000";
	
private ArrayList<CSVError> errors = new ArrayList<CSVError>();
	
	
	/**
	 * @return ArrayList 
	 */
	public ArrayList<CSVError> getErrors() {
		return errors;
	}
	/**
	 * @param _code 
	 */
	public void addError(String _code) {
		CSVError error = new CSVError();
		error.seterrorCode(_code);
		errors.add(error);
	}
	/**
	 * @param _code 
	 * @param errorValue 
	 * @param rowCount 
	 * @param column 
	 */ 
	public void addError(String _code, String errorValue ,int rowCount,int column) {
		CSVError error = new CSVError();
		error.seterrorCode(_code);
		error.setErrorValue(errorValue);
		error.setRow(rowCount);
		error.setColumn(column);
		errors.add(error);
	}
}
