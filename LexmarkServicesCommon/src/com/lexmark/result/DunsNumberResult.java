package com.lexmark.result;

import java.io.Serializable;

public class DunsNumberResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3311862948102325493L;

	private String dunsNumber;
	

	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}

	public String getDunsNumber() {
		return dunsNumber;
	}
}
