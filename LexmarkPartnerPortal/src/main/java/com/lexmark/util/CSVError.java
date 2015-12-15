package com.lexmark.util;

public class CSVError {
	private String errorCode;
	private String errorValue;
	private int row;
	private int column;
	
	public String geterrorCode() {
		return errorCode;
	}
	public void seterrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
	public String getErrorValue() {
		return errorValue;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

}
