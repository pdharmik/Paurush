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
	/**
	 * 
	 * @return List
	 */
	public List<String> getCities() {
		return cities;
	}
	/**
	 * 
	 * @param cities 
	 */
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<String> getCountries() {
		return countries;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<String> getStates() {
		return states;
	}
	/**
	 * 
	 * @param states 
	 */
	public void setStates(List<String> states) {
		this.states = states;
	}
	/**
	 * 
	 * @return List
	 */
	public List<String> getProvinces() {
		return provinces;
	}
	/**
	 * 
	 * @param provinces 
	 */
	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}
	/**
	 * 
	 * @param countries 
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
}
