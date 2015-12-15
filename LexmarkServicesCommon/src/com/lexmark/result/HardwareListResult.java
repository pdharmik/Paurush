package com.lexmark.result;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Accessories;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.Hardware;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.Supplies;

public class HardwareListResult {
	private int totalCount;
	private Part hardware;
	private List<Bundle> bundlesList = new ArrayList<Bundle>();
	private List<Part> accessoriesList = new ArrayList<Part>();
	private List<Part> suppliesList = new ArrayList<Part>();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private String agreementId;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public Part getHardware() {
		return hardware;
	}
	public void setHardware(Part hardware) {
		this.hardware = hardware;
	}
	public List<Bundle> getBundlesList() {
		return bundlesList;
	}
	public void setBundlesList(List<Bundle> bundlesList) {
		this.bundlesList = bundlesList;
	}
	
	public List<Part> getAccessoriesList() {
		return accessoriesList;
	}
	public void setAccessoriesList(List<Part> accessoriesList) {
		this.accessoriesList = accessoriesList;
	}
	public List<Part> getSuppliesList() {
		return suppliesList;
	}
	public void setSuppliesList(List<Part> suppliesList) {
		this.suppliesList = suppliesList;
	}
	public List<ListOfValues> getLovList() {
		return lovList;
	}
	public void setLovList(List<ListOfValues> lovList) {
		this.lovList = lovList;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

}
