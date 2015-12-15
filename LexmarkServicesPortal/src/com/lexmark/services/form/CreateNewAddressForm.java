package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

import com.lexmark.contract.GeographyCountryContract;

public class CreateNewAddressForm implements Serializable{

	private static final long serialVersionUID = -2825979055806312686L;
	private List<GeographyCountryContract> countries;
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
	 * @return List 
	 */
	public List<GeographyCountryContract> getCountries() {
		return countries;
	}
	/**
	 * 
	 * @param countries 
	 */
	public void setCountries(List<GeographyCountryContract> countries) {
		this.countries = countries;
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
}
