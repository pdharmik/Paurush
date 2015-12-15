package com.lexmark.contract;

import java.io.Serializable;
import java.util.List;

public class CategoryHierarchyContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	private String mdmId;
	private String mdmLevel;
	private List<String> roles;
	private String locale;
	//added for BRD 14-07-04
	private List<String> partnerTypeList;
	private List<String> countryList;
	private Boolean servicesPortal;
	private Boolean partnerPortal;
	
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public List<String> getPartnerTypeList() {
		return partnerTypeList;
	}
	public void setPartnerTypeList(List<String> partnerTypeList) {
		this.partnerTypeList = partnerTypeList;
	}
	public List<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}
	public void setServicesPortal(Boolean servicesPortal) {
		this.servicesPortal = servicesPortal;
	}
	public Boolean getServicesPortal() {
		return servicesPortal;
	}
	public void setPartnerPortal(Boolean partnerPortal) {
		this.partnerPortal = partnerPortal;
	}
	public Boolean getPartnerPortal() {
		return partnerPortal;
	}
	
	
}
