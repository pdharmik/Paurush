package com.lexmark.contract;

import java.util.List;

import com.lexmark.domain.Part;
import com.lexmark.domain.Price;

public class TaxContract {
	
	private String sourceReferenceId;
	private String soldToNumber;
	private String shipToNumber;
	private String salesOrganization;
	private String salesOffice;
	private String salesGroup;
	private String country;
	private String city;
	private String postalCode;
	private String region;
	private List<Price> lineInformationList;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getSourceReferenceId() {
		return sourceReferenceId;
	}
	public void setSourceReferenceId(String sourceReferenceId) {
		this.sourceReferenceId = sourceReferenceId;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getShipToNumber() {
		return shipToNumber;
	}
	public void setShipToNumber(String shipToNumber) {
		this.shipToNumber = shipToNumber;
	}
	public String getSalesOrganization() {
		return salesOrganization;
	}
	public void setSalesOrganization(String salesOrganization) {
		this.salesOrganization = salesOrganization;
	}
	public String getSalesOffice() {
		return salesOffice;
	}
	public void setSalesOffice(String salesOffice) {
		this.salesOffice = salesOffice;
	}
	public String getSalesGroup() {
		return salesGroup;
	}
	public void setSalesGroup(String salesGroup) {
		this.salesGroup = salesGroup;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<Price> getLineInformationList() {
		return lineInformationList;
	}
	public void setLineInformationList(List<Price> lineInformationList) {
		this.lineInformationList = lineInformationList;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	

}
