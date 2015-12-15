package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocalizedExchangeOptionListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 534959060824641145L;
	private List<String> localizedValues = new ArrayList<String>(0);
	public List<String> getLocalizedValues() {
		return localizedValues;
	}
	public void setLocalizedValues(List<String> localizedValues) {
		this.localizedValues = localizedValues;
	}
}
