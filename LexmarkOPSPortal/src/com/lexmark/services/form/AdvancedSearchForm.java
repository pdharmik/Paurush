package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

public class AdvancedSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2825979055806312686L;
	private List<String> countries;
	private List<String> states;
	private List<String> provinces;
	private List<String> cities;
	
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	public List<String> getCountries() {
		return countries;
	}
	public List<String> getStates() {
		return states;
	}
	public void setStates(List<String> states) {
		this.states = states;
	}
	public List<String> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
}
