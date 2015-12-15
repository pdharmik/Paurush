package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;

public class GeographyListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5339156429733688003L;
	
	private List<GeographyCountryContract> countryList;
	private List<GeographyStateContract> stateList;

	
	public List<GeographyCountryContract> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<GeographyCountryContract> countryList) {
		this.countryList = countryList;
	}

	public List<GeographyStateContract> getStateList() {
		return stateList;
	}

	public void setStateList(List<GeographyStateContract> stateList) {
		this.stateList = stateList;
	}

	
}
