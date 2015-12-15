package com.lexmark.util;

import java.util.ArrayList;


public class CSVErrorHanlder {
	public static final String MSG_KEY_INVALID_CELL_VALUE = "invalid.cell";
	public static final String MSG_KEY_EMPTY_CELL = "empty.cell";
	public static final String MSG_KEY_TOO_FEW_COLUMNS = "too.few.columns";
	public static final String MSG_KEY_TOO_MUCH_COLUMNS = "too.much.columns";
	public static final String MSG_KEY_VALUE_TOO_LONG = "value.too.long";
	public static final String MSG_KEY_VALUE_TOO_SHORT = "value.too.short";
	public static final String MSG_KEY_VALUE_MUST_BE_71113 = "value.must.71113";
	public static final String MSG_KEY_HEADER_NOT_MATCHING = "header.value.not.matching";
	public static final String MSG_KEY_MISSING_COLUMNS = "column.invalid";
	public static final String MSG_KEY_INVALID_FILE_TYPE = "invalid.file.header";
	public static final String MSG_KEY_PARTS_MORE_THAN_FIVE = "parts.more.than.5";
	public static final String MSG_KEY_TOO_MANY_ROWS = "rows.more.than.50";
	public static final String MSG_KEY_ACTIVITYID_NULL = "activityID.is.null";
	public static final String MSG_KEY_Claims_AssetProduct_NULL = "Claims.AssetProduct.is.null";
	public static final String MSG_KEY_Claims_AstSerialNumber_NULL = "Claims.AstSerialNumber.is.null";
	public static final String MSG_KEY_Claims_HEADER_NULL = "Column.Header.is.null";
	public static final String MSG_KEY_DATE_FORMAT = "Please.provide.date.in.correct.format";
	public static final String MSG_KEY_EMPTY_FILE = "file.is.empty";
	
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
