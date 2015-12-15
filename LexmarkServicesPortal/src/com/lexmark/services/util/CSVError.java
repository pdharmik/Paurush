package com.lexmark.services.util;

public class CSVError {
	private String errorCode;
	private String errorValue;
	private int row;
	private int column;
	
	/**
	 * @return String 
	 */
	public String geterrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode 
	 */
	public void seterrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @param errorValue 
	 */
	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
	/**
	 * @return String 
	 */
	public String getErrorValue() {
		return errorValue;
	}
	/**
	 * @return int 
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row 
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return int 
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column 
	 */
	public void setColumn(int column) {
		this.column = column;
	}

}
