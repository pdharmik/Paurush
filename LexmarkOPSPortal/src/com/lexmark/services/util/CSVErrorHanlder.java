package com.lexmark.services.util;

import java.util.ArrayList;


public class CSVErrorHanlder {
	public static final String MSG_KEY_INVALID_CELL_VALUE = "invalid.cell";
	public static final String MSG_KEY_EMPTY_CELL = "empty.cell";
	public static final String MSG_KEY_TOO_FEW_COLUMNS = "too.few.columns";
	public static final String MSG_KEY_TOO_MUCH_COLUMNS = "too.much.columns";
	public static final String MSG_KEY_VALUE_TOO_LONG = "value.too.long";
	public static final String MSG_KEY_VALUE_TOO_SHORT = "value.too.short";
	public static final String MSG_KEY_VALUE_MUST_BE_71113 = "value.must.71113";
	public static final String MSG_KEY_VALUE_MUST_BE_BELOW_64000 = "value.below.64000";
	
private ArrayList<CSVError> errors = new ArrayList<CSVError>();
	
	
	public ArrayList<CSVError> getErrors() {
		return errors;
	}
	public void addError(String _code) {
		CSVError error = new CSVError();
		error.seterrorCode(_code);
		errors.add(error);
	}
	public void addError(String _code, String errorValue ,int rowCount,int column) {
		CSVError error = new CSVError();
		error.seterrorCode(_code);
		error.setErrorValue(errorValue);
		error.setRow(rowCount);
		error.setColumn(column);
		errors.add(error);
	}
}
