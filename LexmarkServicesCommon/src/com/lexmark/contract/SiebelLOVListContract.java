package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.CachingContract;

public class SiebelLOVListContract extends CachingContract implements Serializable {
	private static final long serialVersionUID = -3401400564374728526L;

	private String lovName;
	private String errorCode1;
	private String parentName;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getErrorCode1() {
		return errorCode1;
	}

	public void setErrorCode1(String errorCode1) {
		this.errorCode1 = errorCode1;
	}

	public String getLovName() {
		return lovName;
	}

	public void setLovName(String lovName) {
		this.lovName = lovName;
	}

	@Override
	public String getCacheKey() {		
		return lovName + errorCode1;		
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
