package com.lexmark.contract;

import java.io.Serializable;

public class GeographyStateContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7390602260888129860L;
	private String stateName;
	private String stateCode;
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
