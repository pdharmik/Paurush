package com.lexmark.result;

import java.io.Serializable;


public class SRAdministrationOptionTypeListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String[] OptionTypes  = new String[0];

	public String[] getOptionTypes() {
		return OptionTypes;
	}

	public void setOptionTypes(String[] optionTypes) {
		OptionTypes = optionTypes;
	}


	
}
