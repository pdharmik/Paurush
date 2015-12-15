package com.lexmark.domain;

import java.io.Serializable;

public class OptionExchange implements Serializable {
	private static final long serialVersionUID = 135715266084215102L;
	private String optionExchangeName;
	public String getOptionExchangeName() {
		return optionExchangeName;
	}
	public void setOptionExchangeName(String optionExchangeName) {
		this.optionExchangeName = optionExchangeName;
	}
	
}
