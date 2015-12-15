package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

public class AccountFlag extends BaseEntity{
	
	private String type;
	private Boolean value;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean isValue() {
		if(value == null){
			value = false;
		}
		return value;
	}
	public void setValue(Boolean value) {
		this.value = value;
	}
	
	

}
